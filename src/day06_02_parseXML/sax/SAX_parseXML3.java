package day06_02_parseXML.sax;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import day06_02_parseXML.bean.Book;

//演示封装数据到JavaBean中
public class SAX_parseXML3 {
	public static void main(String[] args) throws Exception{
		//创建sax解析器
		SAXParser sax = SAXParserFactory.newInstance().newSAXParser() ;
		//获取内容读取器
		XMLReader xml =  sax.getXMLReader() ;
		//创建集合对象用来放置所有的书
		final List<Book> list = new ArrayList<Book>() ;
		//注册一个内容处理器
		xml.setContentHandler(new DefaultHandler(){
			String curName = "" ;  //记录当前是那个标签
			int index = 0 ;  //记录读取到了那个作者
			Book book = null ;

			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				if(qName.equals("书")){
					book = new Book() ;
				}
				curName = qName ;
			}

			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				if(qName.equals("书")){
					list.add(book) ;
				}
				curName = null ;
			}

			public void characters(char[] ch, int start, int length)
					throws SAXException {
				if("书名".equals(curName))
					book.setBookName(new String(ch,start,length)) ;
				if("作者".equals(curName))
					book.setAuthor(new String(ch,start,length)) ;
				if("售价".equals(curName))
					book.setPrice(Float.parseFloat(new String(ch,start,length))) ;
			}
		}) ;
		//加载xml文档
		xml.parse("src/book.xml") ;
		
		//打印集合数据
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
}


