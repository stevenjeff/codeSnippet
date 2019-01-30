package org.fangrui;

import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//		System.out.println(stackTraceElements);
//		new Exception().printStackTrace();
		b();


	}
	
	public static void a() {
//		StackWalker stack = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
//		stack.forEach(System.out::println);
		java.lang.StackWalker.getInstance(java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE)
		.walk(s -> s.skip(1).limit(1).collect(Collectors.toList()))
		.forEach(System.out::println);
	}

	public static void b() {
		a();
	}
}
