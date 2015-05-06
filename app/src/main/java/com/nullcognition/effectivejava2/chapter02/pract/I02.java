package com.nullcognition.effectivejava2.chapter02.pract;// Created by ersin on 06/05/15

import android.util.Log;

public class I02{
	final private int i;
	final private long l;
	final private String s;
	final private char c;

	private I02(Builder b){
		i = b.i;
		l = b.l;
		s = b.s;
		c = b.c;
		Log.e("logErr", "i:" + i + " l:" + l + " s:" + s + " c:" + c);

	}

	public static class Builder{
		private int i;
		private long l;
		private String s = "optional";
		private char c = 'c';

		public I02 build(){return new I02(this);} // building the outer class is delegated

		// required parameters must be set and is enforced in the constructor
		public Builder(int i, long l){
			this.i = i;
			this.l = l;
		}

		// optional parameters require their own methods for setting, for they need not be called
		public Builder s(String s){
			this.s = s;
			return this;
		}

		public Builder c(char c){
			this.c = c;
			return this;
		}

		// preconfigured with full sensible defaults, or get values dynamically from system/config
		public static I02 create(){
			// dynamicConfig = getSystem.findCurrentConfig();
			// int i = dynamicConfig.getI();
			// long l = dynamicConfig.getL();
//			return new Builder(i,l).build();
			return new Builder(1, 2).build(); // s and c are preconfigured

		}
	}
}
