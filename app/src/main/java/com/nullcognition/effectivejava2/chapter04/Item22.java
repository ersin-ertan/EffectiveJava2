package com.nullcognition.effectivejava2.chapter04;
// ersin 28/10/15 Copyright (c) 2015+ All rights reserved.

// Favor static member classes over non static

// Types of nested classes: static member class,
// (all others are inner classes) non static member class, anonymous class, local class


public class Item22{

	private void enclosing(NonStaticMember nsm){
		NonStaticMember nonStaticMember = new NonStaticMember();
		// ^^ implicitly attached to this instance

		Item22          item22           = new Item22();
		NonStaticMember nonStaticMember1 = item22.new NonStaticMember();
		// now item22's non static member has a new reference

		// there is no way to get, this or an Item22's instance of the
		// implicit NonStaticMember, you can only change it
//		item22.new nsm; attempt with new syntax
//		item22.new NonStaticMember(nsm); // attempt with copy constructor

	}

	private static int accessible = 2;
	private        int notThis    = 2;

	public static int m1(){return 1;}

	{
		new StaticMember().meth();
		int j = Item22.StaticMember.i;
	}

	private void m2(){}

	static class StaticMember{ // used as a public helper class

		public static final int i = accessible;
		private int j;

		public void meth(){
			j = m1();
		}
	}


	class NonStaticMember{
		// implicitly associated with host class

		NonStaticMember(){}

//		NonStaticMember(NonStaticMember nsm){
//			this = nsm;
//		}

		public void sm1(){
			Item22.this.m1();
			// or
			m1();

			Item22.this.m2(); // enclosing class
		}
	}

	// common use case is for allowing the outer class to be of needed type and to implement
	// the inner private class as an adapter pattern doing the host classes specialized work


	public interface Inter{

		int doSpecializedTask();
	}


	public class Host{

		private int hostsValue;

		Inter inter(){ return new SpecializedClass();}

		void method(){ int k = inter().doSpecializedTask();}

		// non static as it needs the hosts value
		private class SpecializedClass implements Inter{

			@Override public int doSpecializedTask(){ return hostsValue += 3; }
		}
	}


	// private static with Item22's as host, represents an internal component


	private static class SomeComponent{

		public static void doSomething(int i){ }
	}


	public void anonClass(){

		new Inter(){
			@Override public int doSpecializedTask(){
				return 3;
			}
		};

		// short and used to do a non frequent operation due to instantiation
	}

	private Inter localClass = new Inter(){
		int a;

		@Override public int doSpecializedTask(){
			++a;
			return 1 + a;
		}
	};

	// local class that is define as a member to be used as a cached instance, perhaps with
	// persistent variables

}
