package lxspider;
//import java.util.Properties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class DoRegex {
	public static String getFilterString(String pageSource)
	{	//Properties properties = Init.getPropertiesdInit();
		String aString ="";

		Document doct =Jsoup.parse(pageSource);
		Elements ts = doct.select("title");
		StringBuffer titlecontent = new StringBuffer();
		titlecontent.append("<Document>"+"\n");
		titlecontent.append("<title>");
		for(Element t: ts )
		{
			titlecontent.append(t.text());
		}
		titlecontent.append("</title>"+"\n");


		Document doc =Jsoup.parse(pageSource);
		Elements ps = doc.select("dl dd:nth-of-type(1) a"/*"/*.summaryRight p:nth-child(1) a"*//*properties.getProperty("Z")*//*".article-body p"*/);
		titlecontent.append("<BasicInformation>"+"\n"+"<Authors>"+"\n");
		int i=1;
		for(Element p: ps )
		{	
			if(match(p.text())){
			titlecontent.append("<author"+i+">"+p.text()+"</author"+i+">"+"\n") ;
			i++;
			}
			
		}
		titlecontent.append("</Authors>"+"\n");
		
		
		titlecontent.append("<AuthorsEng>"+"\n");
		
		int i1 =1;
		for(Element p: ps )
		{	
			if(!match(p.text())){
			titlecontent.append("<authoreng"+i1+">"+p.text()+"</authoreng"+i1+">"+"\n") ;
			i1++;
			}
			
		}
		titlecontent.append("</AuthorsEng>"+"\n");
		
		

		Document doc1 =Jsoup.parse(pageSource);
		Elements ps1 = doc1.select("dl dd:nth-of-type(2)"/*properties.getProperty("Z")*//*".article-body p"*/);

		titlecontent.append("<Organization>"+"\n");
		for(Element p1: ps1 )
		{
			titlecontent.append("<organization>"+p1.text()+"</organization>"+"\n");
		}
		titlecontent.append("</Organization>"+"\n");


		Document doc2 =Jsoup.parse(pageSource);
		Elements ps2 = doc2.select("dl dd:nth-of-type(3) [style=text-decoration:none;]"/*properties.getProperty("Z")*//*".article-body p"*/);

		titlecontent.append("<PublishingHouse>"+"\n");
		for(Element p2: ps2 )
		{
			titlecontent.append("<publishinghouse>"+p2.text()+"</publishinghouse>"+"\n");
		}
		titlecontent.append("</PublishingHouse>"+"\n");
		
		
		Document doc8 =Jsoup.parse(pageSource);
		Elements ps8 = doc8.select("dl dd:nth-of-type(3) span span"/*properties.getProperty("Z")*//*".article-body p"*/);

		titlecontent.append("<Index>"+"\n");
		for(Element p8: ps8 )
		{
			if(!p8.text().equals(""))
			titlecontent.append("<index>"+p8.text()+"</index>"+"\n");
			
		}
		titlecontent.append("</Index>"+"\n");
		
		
		Document doc3 =Jsoup.parse(pageSource);
		Elements ps3 = doc3.select("dl dd:nth-of-type(5) a "/*properties.getProperty("Z")*//*".article-body p"*/);

		titlecontent.append("<Time>"+"\n");
		for(Element p3: ps3 )
		{
			titlecontent.append("<time>"+p3.text()+"</time>"+"\n");
		}
		titlecontent.append("</Time>"+"\n");
		Document doc4 =Jsoup.parse(pageSource);
		Element ps4 = doc4.select("dl dd:nth-of-type(6) "/*properties.getProperty("Z")*//*".article-body p"*/).first();
		titlecontent.append("<Sortnumber>"+"\n");
		titlecontent.append("<sortnumber>"+ps4.text()+"</sortnumber>"+"\n");
		titlecontent.append("</Sortnumber>"+"\n");

		Document doc5 =Jsoup.parse(pageSource);
		Elements ps5 = doc5.select("dl dd:nth-of-type(7) a  "/*properties.getProperty("Z")*//*".article-body p"*/);
		titlecontent.append("<Keyword>"+"\n");
		
		for(Element p5: ps5 )
		{
			if(!p5.text().equals("") && match(p5.text()))
			{
				titlecontent.append("<keyword>"+p5.text()+"</keyword>"+"\n");
			}

		}
		titlecontent.append("</Keyword>"+"\n");

		Document doc6=Jsoup.parse(pageSource);
		Elements ps6 = doc6.select("dl dd:nth-of-type(10)");
		titlecontent.append("<Fundsproject>"+"\n");
		titlecontent.append("<fundsproject>"+ps6.text()+"</fundsproject>"+"\n");
		titlecontent.append("</Fundsproject>"+"\n");
		Document doc7=Jsoup.parse(pageSource);
		Elements ps7 = doc7.select(".abstracts");
		titlecontent.append("<Abstracts>"+"\n");
		titlecontent.append("<abstracts>"+ps7.text()+"</abstracts>"+"\n");
		titlecontent.append("</Abstracts>"+"\n");
		titlecontent.append("</BasicInformation>"+"\n");
		titlecontent.append("</Document>"+"\n");
		aString=titlecontent.toString();

		return aString;

	}
	public static boolean match(String str){
		String str1= "[\\u4e00-\\u9fa5]";//此Asics码值正是汉子的范围
		Pattern p1=Pattern.compile(str1);
		Matcher m1=p1.matcher(str);
		boolean bool=m1.find();
		return bool;
	}


}
