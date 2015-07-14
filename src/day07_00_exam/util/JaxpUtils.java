package day07_00_exam.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

//工具类
public class JaxpUtils {
	//获取dom树
	public static Document getDocument(){
		//创建dom解析器
		try{
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = db.parse("src/exam.xml");
			return document;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//保存dom树到硬盘上
	public static void write2xml(Document document){
		//创建transformer对象
		try{
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			
			tf.transform(new DOMSource(document), new StreamResult("src/exam.xml"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
