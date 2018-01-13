/*package lxspider;

public class MyCrawler2 {

	public void initialUrls(String seeds[]){
		for (int i =0;i<seeds.length;i++){

			LinkQueue.addUnvisitedUrl(seeds[i]);
		}

	}

	
	
	public static void main(String args[]){

		int threadCount = 10;
		String a ="http://lib-wf.sgst.cn/C/S-root.T.TL-best-1.aspx";
		
		String url[]={a};

		new MyCrawler2().initialUrls(url);
		SpiderThread spiderThread = new SpiderThread();
		for (int i= 0; i<threadCount;i++){

			new Thread(spiderThread,"thread"+i).start();

		}
		while(true){  
			if(LinkQueue.unVistiedUrlIsEmpty()&& Thread.activeCount() == 1||spiderThread.count==threadCount){//等待的线程数量为设定的线程数或者没有爬取的set为空且当前只有一个线程在工作  
				System.exit(1);  
			}  
		} 
	}


}*/
