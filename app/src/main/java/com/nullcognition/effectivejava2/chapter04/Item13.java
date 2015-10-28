package com.nullcognition.effectivejava2.chapter04;
// ersin 27/10/15 Copyright (c) 2015+ All rights reserved.


// Minimize the accessibility of classes and members


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Item13{

	public int a;
	// package-private
	int b;
	protected int c;
	private   int d;

	public Item13(long l){}

	Item13(String s){}

	protected Item13(int i){}

	private Item13(){}

	private static final String[]     strings     = { "a", "b" };
	public static final  List<String> listStrings =
			Collections.unmodifiableList(Arrays.asList(strings));

	public static final String[] strings(){ return strings.clone(); }
	// will clone primitive values, and the object references and change to referenced value
	// will be noticed in both original and clone, but if the reference is change to point to
	// another object, the association is also lost

}
