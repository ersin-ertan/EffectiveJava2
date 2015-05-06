package com.nullcognition.effectivejava2.chapter02.pract;// Created by ersin on 06/05/15

import android.util.Log;

public class Pract00{

	public static void testI01(){
		I01 i01 = I01.newIO1(true);
		Log.e("logErr", "s is " + i01.s); // "init"
		Log.e("logErr", "s is " + i01.getString()); // "returning"
		Log.e("logErr", "s is " + i01.s); // "new ref" after return finally changes s
	}

	public static void testI01TryFinallyReturn(){
		I01 i01 = I01.newIO1(true);
		Log.e("logErr", "s is " + i01.s); // "init"
		if(i01.tryFinally().equals("try")){
			Log.e("logErr", "s is " + "try"); // testing to see if "try" was ever seen
		}
		Log.e("logErr", "s is " + i01.tryFinally()); // "finally" after return finally changes s
		Log.e("logErr", "s is " + i01.s); // "new ref" after return finally changes s

		// testing try did not work, check for stack based interception methods from i01's call stack
	}

	public static void testI02Builder(){
		I02 i02 = new I02.Builder(2, 3).build();
		i02 = new I02.Builder(3, 4).c('e').s("hello").build();
		i02 = I02.Builder.create();
	}

}

