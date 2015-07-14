package day06_02_parseXML.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

//演示事件处理的时间(什么时候调用哪个方法)
public class SAX_parseXML1 {
	public static void main(String[] args) throws Exception {
		// 创建sax解析器
		SAXParser sax = SAXParserFactory.newInstance().newSAXParser();
		// 获取内容读取器
		XMLReader xml = sax.getXMLReader();
		// 注册一个内容处理器
		xml.setContentHandler(new DefaultHandler() {
			// 当解析文档开始时调用
			@Override
			public void startDocument() throws SAXException {
				System.out.println("文档开始解析");
			}

			// 文档解析结束时调用
			@Override
			public void endDocument() throws SAXException {
				System.out.println("文档结束解析");
			}

			// 解析到开始标签的时候调用
			@Override
			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				System.out.println(qName + " 标签解析开始了...");
			}

			// 解析到结束标签的时候调用
			@Override
			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				System.out.println(qName + " 标签解析结束了...");
			}

			// 读取到文本节点时调用
			@Override
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				System.out.println(new String(ch, start, length) + "文本");
			}
		});
		// 加载xml文档
		xml.parse("src/book.xml");
	}
}
