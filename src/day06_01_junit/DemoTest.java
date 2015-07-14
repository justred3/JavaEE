package day06_01_junit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class DemoTest {
	/**
	 * 测试方法的写法：
	 * 		  1. 方法必须是公有的
	 *  	  2. 方法返回值必须是void
	 *  	  3. 方法必须是无参
	 *  	  4. 必须加@Test注解
	 *  
	 *       @before  :是每个测试方法执行之前都必须要调用一次此方法
	 *       @beforeclass: 是所有测试方法执行之前都必须要调用一次此方法(只会调用一次)
	 *       
	 *       @after:
	 *       @afterclass:
	 * @author Administrator
	 *
	 */
	static Demo d ;

//	@Before
//	public void before(){
//		System.out.println("我先执行了");
//		d = new Demo() ;
//	}
	
	@BeforeClass
	public static void beforeclass(){
		System.out.println("我是静态的方法");
		d = new Demo() ;
	}
	
	@Test(timeout=100)
	public void testAdd(){
	//	demo() ;
		Assert.assertEquals(10, d.add(4, 6)) ;
	}
	
	@Test(expected=java.lang.ArithmeticException.class)
	public void testDevide(){
		//demo() ;
		Assert.assertEquals(2, d.devide(10, 2)) ;
	}
	
//	public void demo(){
//		//System.out.println("啊啊啊啊啊啊啊啊啊");
//		d = new Demo() ;
//	}
	
	@After
	public void after(){
		System.out.println("我执行完了");
	}
}


