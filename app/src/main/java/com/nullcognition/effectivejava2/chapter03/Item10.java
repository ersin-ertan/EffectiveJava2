package com.nullcognition.effectivejava2.chapter03;

import java.util.Stack;

/**
 Created by ersin on 24/04/15 at 11:54 PM
 */

// always override toString
// decide and document if format is immutable thus usable for parsing (as a protocol)
// don't turn the output string to the api, provide access to data presented


public class Item10{

	// toSting

	public class InformativeToString{
		protected int i;
		protected String s;
		protected Stack<Integer> stack = new Stack<>();

		{
			stack.add(1);
			stack.add(2);
			stack.add(3);
			stack.add(4);
		}

		public int getI(){ return i;}

		public String getS(){ return s;}

		public Integer getHead(){ return stack.peek();}

		@Override
		public String toString(){
			return this.getClass().getSimpleName() + " i:" + i + " s:" + s + " head:" + stack.peek();
		} // returns "Item10 i:3 s:helloWorld head:4"
	}
}
