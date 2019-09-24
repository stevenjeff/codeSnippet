package org.fangrui;

import gnu.trove.list.array.TIntArrayList;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//		System.out.println(stackTraceElements);
//		new Exception().printStackTrace();
		multiline();
		mapTest();
	}

	public static void a() {
//		StackWalker stack = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
//		stack.forEach(System.out::println);
		java.lang.StackWalker.getInstance(java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE)
		.walk(s -> s.skip(1).limit(1).collect(Collectors.toList()))
		.forEach(System.out::println);
	}

	public static void c() {
		CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
			    CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
			    CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
			    assertTrue("Was not canceled", cf.cancel(true));
			    assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
			    assertEquals("canceled message", cf2.join());
			    assertEquals("canceled message", cf2.join());

	}

	public static void b() {
		a();
	}

	public static void multiline() {
		var multiline = "This     \r\nis a\r\nmultiline\r\nstring".strip();
		multiline.lines()
		    // we now have a `Stream<String>`
		    .map(line -> "// " + line)
		    .forEach(System.out::println);
				// OUTPUT:
				// This
				// is a
				// multiline
				// string
	}

	public static void mapTest() {
		TIntArrayList intList = new TIntArrayList();
		intList.add(1);
		intList.add(1);
		intList.add(1);
		intList.add(1);
		for (int i = 0; i < intList.size(); i++) {
			System.out.println(i);
		}
	}
}
