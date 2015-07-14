package day06_00_debug;

import org.junit.Test;

public class Demo {
	String name = "何力文";
	String address = null;
	
	public void print(){
		System.out.println(address.length());
		int a = 0;
		for(int i =0;i<=100;i++){
			a+=i;
		}
		System.out.println(a);
	}
	
	public static void main(String[] args){
		Demo d = new Demo();
		d.print();
	}
}
