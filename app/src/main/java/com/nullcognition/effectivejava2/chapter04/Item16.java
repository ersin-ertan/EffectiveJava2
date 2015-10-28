package com.nullcognition.effectivejava2.chapter04;
// ersin 28/10/15 Copyright (c) 2015+ All rights reserved.

// Favor composition over inheritance


public class Item16{

	public class A{

		protected int i;

		protected void superMethod(int k){
			i += k; // modifies i
		}
	}


	public class B extends A{

		private int a = i + 3; // violates encapsulation

		void subMethod(int h){
			super.superMethod(h); // modifies i without our knowledge
		}

		// it is better to override

		@Override protected void superMethod(final int k){
			// do things my way without super call, but some variables may be private in the super class
			// thus to write an implementation means need access to it, thus self use is an unfortunate
			// necessity
		}
	}


	public class C{

		private A a; // wrapper class using composition

		protected void superMethod(int k){
			a.superMethod(k); // forwarding methods
		}

		public int getI(){return a.i; }
	}


	interface Meths{

		void m1();
		void m2(int i);
	}


	public class D implements Meths{

		@Override public void m1(){
			// do something
		}

		@Override public void m2(final int i){
			// do something
		}
	}

	// to keep type compatibility implement the method in your wrapper class


	public class E implements Meths{ // not suited for callback frame works, passing this may not
		// be of the right type, or might not know of the callback handling methods

		protected D d;

		@Override public void m1(){ d.m1();}

		@Override public void m2(final int i){d.m2(i);}
	}


}
