package com.nullcognition.effectivejava2.chapter04;
// ersin 28/10/15 Copyright (c) 2015+ All rights reserved.

// use function objects to represent strategies

// other languages use function pointers, delegates, lambda expressions


import java.util.Arrays;

public class Item21{

	static class SomeComparator{

		// stateless, no fields
		// can be an singleton
		private SomeComparator(){throw new IllegalAccessError();}

		public static final SomeComparator INSTANCE = new SomeComparator();

		public int compare(String a, String b){
			return a.length() - b.length();
		}
	}

	// enums are implicitly singletons, but cannot be extended and are not suited for the
	// typing needs of the strategy pattern


	public enum SomeOtherComparator{
		;

		public int compare(String a, String b){
			return 1;
		}
	}

	// thus we can use an interface to define the type for comparators, with generic parameters


	interface Comparator<T>{

		public int compare(T t, T tt);
	}


	private static class MyComparator implements Comparator<String>{

		@Override public int compare(final String s, final String tt){
			return 0;
		}
	}


	{
		// you could even use it in an anonymous class definition, but if used heavily
		// consider caching the instance

		Arrays.sort(new String[]{ "a", "c", "b" }, new java.util.Comparator<String>(){
			@Override public int compare(final String lhs, final String rhs){
				// custom logic
				return 1;
			}
		});

	}

	// cached instance

	public static final Comparator<String> STRING_MY_COMPARATOR = new MyComparator();
}
