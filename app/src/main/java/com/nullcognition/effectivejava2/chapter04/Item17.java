package com.nullcognition.effectivejava2.chapter04;
// ersin 28/10/15 Copyright (c) 2015+ All rights reserved.

// Design and document for inheritance or else prohibit it


public class Item17{

	public class A{

		private int internal = 1;

		private final Object manipulationObject = new Object(){

			public void someMethod(){}
		};

		public int m1(int j){
			j += m2(j);
			return j;
		}

		private int m2(final int j){
			return internal < j ? internal * j : 0;
		}

		// design doc: this implementation adds a value from the method call which compares the
		// argument with an internal integer, if argument is larger then the internal value is
		// multiplied by the argument and return to the calling method to be added to the argument
		// to which the sum value is returned from the public method call

		// if another object was used then that would also be documented
		// judiciously choose when to expose internals

		public Object getManipulationObject(){return manipulationObject; }
	}


	// to test write subclasses three levels deep
	class B extends A{

		protected void sm1(){
			sm2(getManipulationObject().toString().compareTo("nothing"));
		}

		private void sm2(int i){
			int j = m1(i);
		}
	}


	class C extends B{

		void ssm1(){
			getManipulationObject();
		}

		void over(){
			super.m1(3);
		}
	}

	// constructor should not invoke override-able method as the object will still be in mid construction
	// and could read and write to values which pend being set before and after construction


	class D extends C{

		@Override void over(){
			super.over();
		}

		D(){
			over();
		}
	}
}
