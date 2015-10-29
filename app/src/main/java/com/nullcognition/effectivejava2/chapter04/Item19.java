package com.nullcognition.effectivejava2.chapter04;
// ersin 28/10/15 Copyright (c) 2015+ All rights reserved.

// use interfaces only to define types

import static com.nullcognition.effectivejava2.chapter04.UtilityClass.*;

public class Item19{

	public interface Constants{

		double d = 9.9; // can be changed base on the type/precision
		// may want to add/remove more constants later, which would break
	}

	// else use enum types or utility classes


	enum Thing{
		A, B;
		int a;


		class B{

			int a;
		}
	}


	Thing en = Thing.A;

	{
		en = Thing.B;
		en.compareTo(Thing.A);
		int i = CONSTANT + 2; // Utility class used here
	}

}


class UtilityClass{

	public static final int CONSTANT = 3;
}
