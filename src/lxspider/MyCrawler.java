package lxspider;
import java.util.Random;
//import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MyCrawler {


	static String a=Initial.getPropertiesdInit("aurl");
	static String b=Initial.getPropertiesdInit("burl");
	static String c=Initial.getPropertiesdInit("curl");
	static String d=Initial.getPropertiesdInit("durl");
	String zhengzeC =Initial.getPropertiesdInit("ZhengZe")+"/"+Initial.getPropertiesdInit("FromtimeTotime");
	String zhengzeD=Initial.getPropertiesdInit("ZhengZe")+Initial.getPropertiesdInit("FromtimeTotime");
	//static String c="http://lib-wf.sgst.cn/C/S-root.T.TE-best-1.aspx";
	//static String d ="http://lib-wf.sgst.cn/C/S-root.T.TD-best-1.aspx";
	//static Initial initial = new Initial();


	public static final Object signal = new Object();
	int threadCount =Integer.parseInt(Initial.getPropertiesdInit("threadCount"));
	int count=0;
	private void initCrawlerWithSeeds(String seeds[])
	{
		for(int i = 0; i < seeds.length; i++)
		{
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}

	}
	private synchronized  void InitialaddUrl(String tmp)//给队列中加入已访问过的url;
	{	
		LinkFilter filter = new LinkFilter(){
			public boolean accept(String url){
				
				Pattern p1=Pattern.compile("http://lib-wf.sgst.cn/C/periodical/"+zhengzeC+".+\\.aspx");
				Matcher m1=p1.matcher(url);
				Pattern p2 =Pattern.compile("http://lib-wf.sgst.cn/D/Periodical_"+zhengzeD+".+\\.aspx");
				Matcher m2 = p2.matcher(url);
				if(url.startsWith("http://lib-wf.sgst.cn/C/periodical-")||m1.matches()||m2.matches())
					return true;
				else 
					return false;
			}
		};
		LinkQueue.addVisitedUrl(tmp);
		Set<String> links=HtmlParserTool.extracLinks(tmp, filter);
		for(String link:links)
		{
			LinkQueue.addUnvisitedUrl(link);
		}

	}

	private void addUrl(String tmp) {
		InitialaddUrl(tmp);
		if(count>0){
			synchronized (signal) {
				count--;
				signal.notify();

			}
		}


	}  


	private synchronized  String getAUrl() {  
		if(LinkQueue.unVistiedUrlIsEmpty())  
			return null;
		String tmpAUrl;  
		tmpAUrl= (String)LinkQueue.unVistiedUrlDequeue();  
		return tmpAUrl;  
	}  

	private void crawling(String[] seeds)
	{

		initCrawlerWithSeeds(seeds);
		for(int i=0; i<threadCount; i++){
			try{	new Thread(new Runnable(){ //此处相当于是实现runnable接口的一个类 
				public void run() {  
					while (true) {    
						String tmp = getAUrl();  
						if(tmp!=null){
							Pattern p2=Pattern.compile("http://lib-wf.sgst.cn/D/Periodical_"+zhengzeD+".+\\.aspx");
							Matcher m2=p2.matcher(tmp);


							if(m2.matches()){
								try {

									int b = Integer.parseInt(Initial.getPropertiesdInit("sleeptime"));
									Random random = new Random();
									int a= random.nextInt(13000)+ b;
									Thread.sleep(a);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}

								DownLoadFile downLoader = new DownLoadFile();
								downLoader.downloadFile(tmp);

								addUrl(tmp);
							}
							else{

								addUrl(tmp);
							}

						}


						else{  
							synchronized(signal) {  
								try {  
									count++;  
									System.out.println("当前有"+count+"个线程在等待");  
									signal.wait();  
								} catch (InterruptedException e) {  

									e.printStackTrace();  
								}  
							}  


						} 

					}  
				}


			},"thread-"+i).start(); 
			} 
			catch (Exception e) {
				e.printStackTrace();
			}





		}
	}


	public static void main(String[] args) {
		MyCrawler crawler = new MyCrawler();

		String[] urls = {a,b,c,d};

		crawler.crawling(urls);
		while(true){  
			if(LinkQueue.unVistiedUrlIsEmpty()&& Thread.activeCount() == 1||crawler.count==crawler.threadCount){//等待的线程数量为设定的线程数或者没有爬取的set为空且当前只有一个线程在工作  
				System.exit(1);  
			}  
		} 



	}

}
