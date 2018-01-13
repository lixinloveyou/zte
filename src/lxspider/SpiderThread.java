/*package lxspider;

import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderThread implements Runnable {

	int count =0;
	
	Object signal = new Object();

	public  synchronized void addUrl(String tmp) {

		LinkFilter filter = new LinkFilter(){
			public boolean accept(String url){
				Pattern p1=Pattern.compile("http://lib-wf.sgst.cn/C/periodical/./201[0-6].*\\.aspx");
				Matcher m1=p1.matcher(url);
				Pattern p2 =Pattern.compile("http://lib-wf.sgst.cn/D/Periodical_.+201[0-6][0-9]+\\.aspx");
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
		if (count>0)
			notify();
		


	}
	
	

	public  synchronized String getAUrl() {  
		if(LinkQueue.unVistiedUrlIsEmpty()){
			count++;
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
			
			
			}
					
		String tmpAUrl;  
		tmpAUrl= (String)LinkQueue.unVistiedUrlDequeue();  
		return tmpAUrl;  
		
	}
	

	public void run() {
		
		while(true){
			String tmp = getAUrl();  
			if(tmp!=null){
				Pattern p2=Pattern.compile("http://lib-wf.sgst.cn/D/Periodical_.+201[0-6][0-9]+\\.aspx");
				Matcher m2=p2.matcher(tmp);

				if(m2.matches()){
					try {
						int b = 1000;
						Random random = new Random();
						int a= random.nextInt(2000)+ b;
						Thread.sleep(a);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					DownLoadFile downLoader = new DownLoadFile();
					downLoader.downloadFile(tmp);
					addUrl(tmp);
					/*if(count>0){
						synchronized (signal) {
							count--;
							signal.notify();*/

//						}
//				else{
//					addUrl(tmp);
//				}
//					}
				//}
				/*else{
					addUrl(tmp);
					if(count>0){
						synchronized (signal) {
							count--;
							signal.notify();

						}
					}
				}*/

			//}


			/*else{  
				synchronized(signal) {  
					try {  
						count++;  
						System.out.println("当前有"+count+"个线程在等待");  
						signal.wait();  
					} catch (InterruptedException e) {  

						e.printStackTrace();  
					}  
				}  


			}*/

//		}


//	}

//}*/
