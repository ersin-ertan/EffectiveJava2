package com.nullcognition.effectivejava2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nullcognition.effectivejava2.chapter02.pract.I01;
import com.nullcognition.effectivejava2.chapter02.pract.Pract00;


public class MainActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		testI01TryFinallyReturn();

		int i = 0; // debug point
	}

	private void testPract00(){
		I01 i01 = I01.newIO1(true);
		Log.e("logErr", "s is " + i01.s); // "init"
		Log.e("logErr", "s is " + i01.getString()); // "returning"
		Log.e("logErr", "s is " + i01.s); // "new ref" after return finally changes s
	}

	private void testI01TryFinallyReturn(){
		I01 i01 = I01.newIO1(true);
		Log.e("logErr", "s is " + i01.s); // "init"
		if(i01.tryFinally().equals("try")){
			Log.e("logErr", "s is " + "try"); // testing to see if "try" was ever seen
		}
		Log.e("logErr", "s is " + i01.tryFinally()); // "finally" after return finally changes s
		Log.e("logErr", "s is " + i01.s); // "new ref" after return finally changes s

		// testing try did not work, check for stack based interception methods from i01's call stack

	}
}
