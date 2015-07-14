package day06_02_parseXML.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//演示dom方式解析XML文件
public class DOM_parseXML {
	public static void main(String[] args) throws Exception {
		// 创建解析器对象
		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		// 加载XML文档
		Document document = db.parse("src/book.xml");
		// test1(document);
		// test2(document);
		// test3(document);
		// test4(document);
		// test5(document);
		// test6(document);
		test7(document);
	}

	// 1.得到某个具体的节点内容 eg:拿到金瓶梅的售价
	public static void test1(Document document) {
		// 拿到所有的售价节点
		NodeList n1 = document.getElementsByTagName("售价");
		// 获得金瓶梅售价的节点
		Node n = n1.item(1);
		// 获取售价文件
		System.out.println(n.getTextContent());
	}

	// 2、遍历所有元素节点
	public static void test2(Node node) {
		// 对Node节点进行循环
		NodeList n1 = node.getChildNodes();
		// 循环判断
		for (int i = 0; i < n1.getLength(); i++) {
			Node n = n1.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				// 说明此节点就是标签节点
				System.out.println(n.getNodeName());
				test2(n);
			}
		}
	}

	// 3、修改某个元素节点的主体内容 eg: 修改金瓶梅的售价为80
	public static void test3(Document document) throws Exception {
		// 拿到所有售价节点
		NodeList nl = document.getElementsByTagName("售价");
		// 获得金瓶梅的售价节点
		Node n = nl.item(1);
		// 修改内容
		n.setTextContent("80");

		// 将结果保存到电脑硬盘上
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.transform(new DOMSource(document), new StreamResult("src/book.xml"));

	}

	// 4、向指定元素节点中增加子元素节点:eg:给葵花宝典的售价增加一个内部价节点 :50
	public static void test4(Document document) throws Exception {
		// 拿到所有售价节点
		NodeList nl = document.getElementsByTagName("售价");
		// 获得葵花宝典的售价节点
		Node n = nl.item(0);
		// 创建新的节点
		Element e1 = document.createElement("内部价");
		// 设置节点的内容
		e1.setTextContent("50");

		// 将内部价节点挂到接到售价节点上
		n.appendChild(e1);
		// 将结果保存到电脑硬盘上
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.transform(new DOMSource(document), new StreamResult("src/book.xml"));
	}

	// 5、向指定元素节点上增加同级元素节点 : eg:给葵花宝典的售价增加一个批发价节点 :60
	public static void test5(Document document) throws Exception {
		// 拿到所有书节点
		NodeList nl = document.getElementsByTagName("书");
		// 获得葵花宝典的书节点
		Node n = nl.item(0);
		// 创建批发价的节点
		Element e1 = document.createElement("批发价");
		// 设置节点的内容
		e1.setTextContent("60");
		// 将批发价节点挂接到书点上
		n.appendChild(e1);
		// 将结果保存到电脑硬盘上
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.transform(new DOMSource(document), new StreamResult("src/book.xml"));
	}

	// 6、删除指定元素节点 eg: 删除内部价节点
	public static void test6(Document document) throws Exception {
		// 拿到所有内部价节点
		NodeList nl = document.getElementsByTagName("内部价");
		// 拿到葵花宝典的内部价节点
		Node node = nl.item(0);

		// 父亲干掉儿子
		node.getParentNode().removeChild(node);
		// 将结果保存到电脑硬盘上
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.transform(new DOMSource(document), new StreamResult("src/book.xml"));

	}

	// 7、操作XML文件属性: eg: 给葵花宝典的书几点增加一个属性： ISBN : "黑马程序员"
	public static void test7(Document document) throws Exception {
		// 拿到所有书节点
		NodeList nl = document.getElementsByTagName("书");
		// 获得葵花宝典的书节点
		Node n = nl.item(0);
		// 增加一个属性
		((Element) n).setAttribute("ISBN", "黑马");
		// 将修改的结果保存到硬盘上
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.transform(new DOMSource(document), new StreamResult("src/book.xml"));
	}
}
