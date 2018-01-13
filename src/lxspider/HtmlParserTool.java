package lxspider;

import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParserTool{
	public static Set<String>extracLinks(String url,LinkFilter filter){
		Set<String> links = new HashSet<String>();
		try{
			Document document = Jsoup.connect(url).timeout(8000).get();
			
			Elements elements = document.select("a");
			for (Element element: elements)
			{	if(filter.accept(element.attr("abs:href"))){
				links.add(element.attr("abs:href"));
				}
			}
			}
		catch (Exception e)
		{
		e.printStackTrace();
		}

		return links;
	}
}





