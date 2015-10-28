package com.nullcognition.effectivejava2.chapter04;
// ersin 27/10/15 Copyright (c) 2015+ All rights reserved.

// Minimize mutability in five rules

// don't provide any methods that modify the object's state
// ensure that the class can't be extended
// make all fields final
// make all fields private
// ensure exclusive access to any mutable components


// thread safe, shared freely, no synchronization
// can share internals, can construct safe objects

import android.content.ClipData;

public class Item15{

	Item15(int i){}

	private final String  s       = "final";
	private final int     i       = 1;
	private final Integer integer = Integer.valueOf(3);

	public void noStateInternalModification(int a){
		int i = 3;
		i += a;
	}

	public int returnInteger(){ return integer;} // unboxing will do this for you

	// better yet be more functional via

	public Item15 doOperation(Item15 i){ return new Item15(i.returnInteger() + 3); }

	// thus the new operation is constructing the new object

	public static final Item15 item15 = new Item15(5);
	// public statics for frequently used constants

	// or static factories cache frequently requested instances to avoid creating new instances
	// or to share instances giving out the same one to each request
}
