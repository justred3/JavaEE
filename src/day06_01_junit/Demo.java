package day06_01_junit;

public class Demo {
	public int add(int a,int b){
		try{
			Thread.sleep(105);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return a+b;
	}	
	public int devide(int a,int b){
		return a/b;
	}
}
