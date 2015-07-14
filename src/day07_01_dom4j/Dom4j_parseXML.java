package day07_01_dom4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4j_parseXML {
	public static void main(String[] args) throws Exception {
		// 创建dom4j的解析器
		SAXReader reader = new SAXReader();
		// 加载document对象
		Document document = reader.read("src/book.xml");
		// test1(document);
		// test2(document);
		// test3(document);
		// test4(document);
		test6(document);
	}

	// 1、得到某个具体的节点内容 : eg: 拿到金瓶梅的售价
	public static void test1(Document document) {
		// 拿到根节点
		Element root = document.getRootElement();
		// 拿到所有书的节点
		List<Element> list = root.elements("书");
		// 拿到第二本书
		Element bookEl = list.get(1);
		// 获取售价文件
		System.out.println(bookEl.elementText("售价"));
	}

	// 2、遍历所有元素节点
	public static void test2(Document document) {
		treeWalk(document.getRootElement());
	}

	public static void treeWalk(Element element) {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				System.out.println(node.getName());
				treeWalk((Element) node);
			}
		}
	}

	// 3、修改某个元素节点的主体内容 eg: 修改金瓶梅的售价为70
	public static void test3(Document document) throws Exception {
		// 获取根节点
		Element root = document.getRootElement();
		// 拿到第二本书的节点
		Element bookEl = (Element) root.elements("书").get(1);
		// 拿到售价的节点
		Element priceEl = bookEl.element("售价");
		// 改变主体内容
		priceEl.setText("70");
		// 将结果保存到硬盘上
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),
				OutputFormat.createPrettyPrint());
		writer.write(document);
		writer.close();
	}

	// 4、向指定元素节点中增加子元素节点:eg:给葵花宝典的售价增加一个内部价节点 :50
	public static void test4(Document document) throws Exception {
		// 获取根节点
		Element root = document.getRootElement();
		// 获取第一本书的节点
		Element bookEl = (Element) root.elements("书").get(0);
		// 获取售价节点
		Element priceEl = bookEl.element("售价");
		// 添加子节点
		priceEl.addElement("内部价").setText("50");

		// 将结果保存到硬盘上
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),
				OutputFormat.createPrettyPrint());
		writer.write(document);
		writer.close();
	}

	// 5、向指定元素节点上增加同级元素节点 : eg:给葵花宝典的售价增加一个批发价节点 :60
	public static void test5(Document document) throws Exception {
		// 获取根节点
		Element root = document.getRootElement();
		// 拿到第一种本节点
		Element bookEL = (Element) root.elements("书").get(0);

		// 给书节点添加子节点
		bookEL.addElement("批发价").setText("60");

		// 将结果保存到硬盘上
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),
				OutputFormat.createPrettyPrint());
		writer.write(document);
		writer.close();
	}

	// 6、删除指定元素节点 eg: 删除内部价节点
	public static void test6(Document document) throws Exception {
		// 获取根节点
		Element root = document.getRootElement();
		// 拿到第一种本节点
		Element bookEL = (Element) root.elements("书").get(0);
		// 拿到内部价节点
		Element neibuEL = bookEL.element("售价").element("内部价");
		// 父亲干掉儿子
		neibuEL.getParent().remove(neibuEL);

		// 将修改的结果保存到硬盘上
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),
				OutputFormat.createPrettyPrint());
		writer.write(document);
		writer.close();
	}

	// 7、操作XML文件属性: eg: 给葵花宝典的书几点增加一个属性： ISBN : "黑马程序员"
	public static void test7(Document document) throws Exception {
		// 获取根节点
		Element root = document.getRootElement();
		// 拿到第一种本节点
		Element bookEL = (Element) root.elements("书").get(0);

		// 增加一个属性
		bookEL.addAttribute("ISBN", "黑马程序员");

		// 将修改的结果保存到硬盘上
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),
				OutputFormat.createPrettyPrint());
		writer.write(document);
		writer.close();
	}
}
