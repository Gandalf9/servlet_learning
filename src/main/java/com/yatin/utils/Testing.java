package com.yatin.utils;

import java.util.ArrayList;


public class Testing {
	
	public final static String variable;
	
	static {
		variable = "HelloWorld";
	}
	
	public Testing() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		Testing t = new Testing();
		System.out.println(t.variable);
		
		ArrayList<String> strings = new ArrayList<>();
	}
		

}
