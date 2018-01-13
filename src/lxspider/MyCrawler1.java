/*package lxspider;

import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MyCrawler1 {
	public final Object singal = new Object();
	int threadcount =10;
	int count =0;
	static String a="http://lib-wf.sgst.cn/C/S-root.T.TV-best-1.aspx";
	static String b="http://lib-wf.sgst.cn/C/S-root.T.TX-best-1.aspx";
	static String c="http://lib-wf.sgst.cn/C/S-root.T.TY-best-1.aspx";
	static String d ="http://lib-wf.sgst.cn/C/S-root.T.TZ-best-1.aspx";
	
	public String geturl()
	{   
		if (LinkQueue.unVistiedUrlIsEmpty()) 
		{
			try {

				count ++;
				System.out.println("当前有"+count+"个线程在等待中");
				singal.wait();
			


			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String tmpurl =(String)LinkQueue.unVistiedUrlDequeue();
		return tmpurl;

	}


	public  void addurl(String tmp) {
		LinkFilter filter = new LinkFilter(){
			public boolean accept(String url){
				Pattern p1=Pattern.compile("http://lib-wf.sgst.cn/C/periodical/.+/201[0-6].*\\.aspx");
				Matcher m1=p1.matcher(url);
				Pattern p2 =Pattern.compile("http://lib-wf.sgst.cn/D/Periodical_[a-z]+201[0-6][0-9]+\\.aspx");
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
		if (count >0){
			synchronized(singal){

			count --;
			singal.notify();
			}

		}



	}
	public void crawling(String[] seeds)
	{
		for( int i=0;i<seeds.length;i++)
		{
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
		for (int j =0;j<threadcount;j++ )
		{
			new Thread(new Runnable() {//此处相当于是一个类

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true){
						String tmp = geturl();
						if (tmp !=null){

							Pattern p2=Pattern.compile("http://lib-wf.sgst.cn/D/Periodical_[a-z]+201[0-6][0-9]+\\.aspx");
							Matcher m2=p2.matcher(tmp);
							if(m2.matches()){
								int b = 5000;
								Random random = new Random();
								int a = random.nextInt(5000)+b;
								try {
									Thread.sleep(a);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								DownLoadFile downLoader = new DownLoadFile();
								downLoader.downloadFile(tmp);
								addurl(tmp);

							}else{

								addurl(tmp);


							}
						}



					}
				}
			},"thread-"+j).start();
		}


	}
	public static void main(String args[])
	{
		MyCrawler1 crawler1 =new MyCrawler1();
		String []urls={a,b,c,d};
		crawler1.crawling(urls);

		while(true){  
			if(LinkQueue.unVistiedUrlIsEmpty()&& Thread.activeCount() == 1||crawler1.count==crawler1.threadcount){//等待的线程数量为设定的线程数或者没有爬取的set为空且当前只有一个线程在工作  
				System.exit(1);  
			}  
		} 
	}


}*/
