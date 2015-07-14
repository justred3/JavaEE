package day06_02_parseXML.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

//演示获取金瓶梅的作者
public class SAX_parseXML2 {
	public static void main(String[] args)throws Exception{
		//创建sax的解析器
		SAXParser sax = SAXParserFactory.newInstance().newSAXParser();
		//获取内容读取器
		XMLReader xml = sax.getXMLReader();
		//注册一个内容处理器
		xml.setContentHandler(new DefaultHandler(){
			String curName=""; //记录当前是哪个标签
			int index =0;    //记录当前读到了哪个作者
			@Override
			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				if(qName.equals("作者")){
					curName = "作者";
					index++;
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				curName="";
			}

			@Override
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				if("作者".equals(curName)&&index==2){
					//说明读取到了第二本书的作者
					System.out.println(new String(ch,start,length));
				}
			}});
		//加载xml文档
		xml.parse("src/book.xml");
	}
}
