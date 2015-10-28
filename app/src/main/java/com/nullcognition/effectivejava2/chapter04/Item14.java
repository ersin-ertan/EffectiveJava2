package com.nullcognition.effectivejava2.chapter04;
// ersin 27/10/15 Copyright (c) 2015+ All rights reserved.


// In public classes, use accessor methods, not public fields


public class Item14{

	private int a;
	int b; // nothing wrong with exposing is this scope

	Item14(int aa){
		a = aa;
		f = 0;
	}

	public int getA(){ return a; } // and can trigger background processes, or modify the return
	// like Collections.unmodifiableList()

	private int c; // this is ok to be accessed by the inner class


	public class D{

		int e;

		D(){e = c;}
	}


	// while exposing public final may be less dangerous
	// it does lead to invariants

	public final double f; // must be initialized in all constructors

	// though final, not deterministic based on constructor usage

	Item14(double ff){f = ff;}
}
