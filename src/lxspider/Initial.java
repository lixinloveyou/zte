package lxspider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
public class Initial {
	public static String getPropertiesdInit(String k)
	{
		Properties p = new Properties();
		String q ="";
		try {
			p.loadFromXML(new FileInputStream("/home/lixin/lx/properties.xml"));
			q=p.getProperty(k);
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
	}
	
}


