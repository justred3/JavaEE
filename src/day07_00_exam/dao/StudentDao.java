package day07_00_exam.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import day07_00_exam.bean.Student;
import day07_00_exam.util.JaxpUtils;

///业务逻辑处理类
public class StudentDao {

	/**
	 * 将学生添加到XML文件中
	 * @param student 要添加的学生
	 * @return 成功返回TRUE，否则FALSE
	 */
	public boolean add(Student student){
		//加载dom树
		Document document = JaxpUtils.getDocument();
		//拿到根节点
		Element root = document.getDocumentElement();
		//创建一个Student节点及其子节点
		Element stu = document.createElement("student");
		
		Element nameEl = document.createElement("name");
		Element locationEl = document.createElement("location");
		Element gradeEl = document.createElement("grade");
		
		//为student子节点及其属性赋值
		stu.setAttribute("examid", student.getExamid());
		stu.setAttribute("idcard", student.getIdcard());
		
		nameEl.setTextContent(student.getName());
		locationEl.setTextContent(student.getLocation());
		gradeEl.setTextContent(student.getGrade()+ "");
		
		//组合各个节点
		stu.appendChild(nameEl);
		stu.appendChild(locationEl);
		stu.appendChild(gradeEl);
		
		//将student节点添加到根节点上
		root.appendChild(stu);
		
		//将内存中的dom树保存到硬盘上
		JaxpUtils.write2xml(document);
		
		return true;
	}
	/**
	 * 根据学生的姓名删除学生
	 * @param name  要删除学生的姓名
	 * @return 成功返回TRUE，否则返回FALSE
	 */
	public boolean delete(String name){
		//加载dom树
		Document document = JaxpUtils.getDocument();
		//获取所有的name节点
		NodeList n1=document.getElementsByTagName("name");
		//循环判断
		for(int i =0;i<n1.getLength();i++){
			Node n = n1.item(i);
			if(n.getTextContent().equals(name)){
				//说明找到了要删除的学生
				//爷爷干掉父亲
				n.getParentNode().getParentNode().removeChild(n.getParentNode());
				//将结果保存到硬盘上
				JaxpUtils.write2xml(document);
				
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据准考证号查询学生
	 * @param examid 要查询学生的准考证号
	 * @return 成功返回此学生，否则返回null
	 */
	public Student findByExamId(String examid){
		//加载dom树
		Document document = JaxpUtils.getDocument();
		//拿到所有的学生节点
		NodeList n1 = document.getElementsByTagName("student");
		//循环判断每个取出的节点的examid属性
		for(int i = 0;i<n1.getLength();i++){
			Element stuNode = (Element) n1.item(i);
			if(stuNode.getAttribute("examid").equals(examid)){
				//找到了此学生
				//开始封装数据
				Student s = new Student() ;
				
				s.setExamid(examid) ;
				s.setIdcard(stuNode.getAttribute("idcard")) ;
				s.setName(stuNode.getElementsByTagName("name").item(0).getTextContent()) ;
				s.setLocation(stuNode.getElementsByTagName("location").item(0).getTextContent()) ;
				s.setGrade(Float.parseFloat(stuNode.getElementsByTagName("grade").item(0).getTextContent())) ;
				return s;
			}
		}
		return null;
	}
}
