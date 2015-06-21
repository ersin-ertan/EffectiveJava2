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

		pract00();


		int i = 0; // debug point
	}

	private void pract00(){

//		Pract00.testI01();
//		Pract00.testI01TryFinallyReturn();
//		Pract00.testI02Builder();

	}


}
