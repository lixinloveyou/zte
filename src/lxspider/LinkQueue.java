package lxspider;

//import java.util;
public class LinkQueue {


	private static Queue unVisitedUrl = new Queue();

	public static void addVisitedUrl(String url){
		
		MysqlConnection.addtUrlToMysql(MD5.getMD5(url.getBytes()));	
	}

	public static Object unVistiedUrlDequeue()
	{
		return unVisitedUrl.deQueue();
	}

	public static void addUnvisitedUrl(String url)
	{
		
	if(url!= null && !url.trim().equals("")&&!MysqlConnection.selectUrlFromMysql(MD5.getMD5(url.getBytes()))&&! unVisitedUrl.contains(url)&&!url.endsWith("#header")&&!url.equals("http://lib-wf.sgst.cn/C/periodical-dq-e.aspx")&&!url.equals("http://lib-wf.sgst.cn/C/periodical-hjs-e.aspx")&&!url.equals("http://lib-wf.sgst.cn/C/periodical-hkxb-e.aspx"))
			unVisitedUrl.enQueue(url);
		/*else
			System.out.println("此url已经爬取过");*/
	}
	
	public static boolean unVistiedUrlIsEmpty()
	{
		return unVisitedUrl.empty();
	}

}
