package day07_00_exam.ui;

import java.util.Scanner;

import day07_00_exam.bean.Student;
import day07_00_exam.dao.StudentDao;

public class StudentUI {
	static Scanner sc = new Scanner(System.in);
	static StudentDao dao = new StudentDao();
	
	public static void main(String[] args){
		enterSystem();
	}
	//进入系统
	private static void enterSystem(){
		System.out.println("请输入你的选择:a)添加学生     b)删除学生      c)查找学生      d)退出系统");
		String op = sc.next();
		if(op.equalsIgnoreCase("a")){
			addStudent();
		}else if("b".equalsIgnoreCase(op)){
			removeStudentByName();
		}else if("c".equalsIgnoreCase(op)){
			findStudentByExamId();
		}else if("d".equalsIgnoreCase(op)){
			return;
		}else{
			System.out.println("输入错误，请重新输入：");
			enterSystem();
		}
	}
	//查找学生
	private static void findStudentByExamId() {
		System.out.println("请输入准考证号码:");
		String examid = sc.next();
		
		//调用业务逻辑层完成
		Student stu =dao.findByExamId(examid);
		if(stu!=null){
			System.out.println(stu);
		}else{
			System.out.println("没有此学生");
		}
		enterSystem();
	}
	//删除学生
	private static void removeStudentByName() {
		System.out.println("请输入学生姓名：");
		String name = sc.next();
		
		//调用业务逻辑层完成
		boolean flag =dao.delete(name);
		if(flag)
			System.out.println("---------删除成功-------------");
		else
			System.out.println("---------删除失败-------------");
		enterSystem();
	}
	//添加学生
	private static void addStudent() {
		Student s = new Student();
		System.out.println("请输入姓名:");
		String name = sc.next();
		s.setName(name);
		
		System.out.println("请输入身份证号:");
		String idcard = sc.next();
		s.setIdcard(idcard);
		
		System.out.println("请输入准备考证号:");
		String examid = sc.next();
		s.setExamid(examid);
		
		System.out.println("请输入地址:");
		String location = sc.next();
		s.setLocation(location);
		
		System.out.println("请输入分数:");
		float grade = sc.nextFloat();
		s.setGrade(grade);
		//调用dao层完成功能
		boolean flag=dao.add(s);
		if(flag){
			System.out.println("——————————添加成功————————————————");
		}else{
			System.out.println("——————————添加失败————————————————");
		}
		enterSystem();
	}
}