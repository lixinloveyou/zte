package lxspider;
import java.io.*;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
public class DownLoadFile {
	public String getFileNameByUrl (String url/*,String contentType*/){
		url = url.substring(7);
		return url = MD5.getMD5(url.getBytes())+Initial.getPropertiesdInit("format");
	

	}
	private void saveTolocal(String data, String filePath){
		try
		{	
			File file  = new File(filePath);
			FileWriter fileWriter =new FileWriter(file);
			PrintWriter out = new PrintWriter(fileWriter);
			out.write(data);
			out.flush();
			out.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public String downloadFile(String url){
		String filePath = null ;
		GetMethod getMethod= null;
		HttpClient httpClient= null;

		httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(8000);
		try{
			getMethod = new GetMethod(url);
		}catch(Exception exception){
			filePath = null;
			System.err.println("getmethod异常");
			
		}

		try{
			getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 8000);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			int statusCode = httpClient.executeMethod(getMethod);
			if(statusCode!=HttpStatus.SC_OK){
				System.err.println("Method failed"+getMethod.getStatusLine());
				filePath = null;
			}else{
				BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream()));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while((str = reader.readLine())!=null){
					stringBuffer.append(str);
				}

				String tS = stringBuffer.toString();
				String reString = DoRegex.getFilterString(tS);
				filePath =Initial.getPropertiesdInit("filePath")+getFileNameByUrl(url/*/home/lixin/lx/spider/,getMethod.getResponseHeader("Content-Type").getValue()*/);
				saveTolocal(reString,filePath);
				getMethod.releaseConnection();
			}
		}

		catch(HttpException e)
		{
			e.printStackTrace();
			System.out.println("1处异常");
			getMethod.releaseConnection();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("2处异常");
			getMethod.releaseConnection();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("3处异常");
		}

		return filePath;

	}
}



