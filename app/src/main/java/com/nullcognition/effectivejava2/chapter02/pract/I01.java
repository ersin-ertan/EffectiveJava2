package com.nullcognition.effectivejava2.chapter02.pract;// Created by ersin on 06/05/15

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class I01{
	// use static factory methods
	private boolean isCachable = false;
	private static volatile long initBool_callCounter = 0;
	private static volatile ConcurrentMap<Long, Date> initBool_callDate = new ConcurrentHashMap<>();
	public String s = "init";

	private I01(){}

	private I01(boolean initBool){}

	// new... can be parameterized ex. string, such that a backing concurrent data structure can test for containment
	// creating and returning a service based on the the parameter, assuming the service is registered with the
	// factory class

	public static I01 newIO1(boolean canTakeInVariables){
		if(canTakeInVariables){
			initBool_callDate.put(initBool_callCounter++, new Date());
			return new I01(true);
		}
		return new I01();
	}

	public static Date getDate(long key){
		return initBool_callDate.get(key);
	}

	public String getString(){
		try{
			s = "returning";
			return s;
		} finally{s = "new ref";}
	}

	public String tryFinally(){
		try{
			s = "try";
			return s;
		} finally{
			s = "finally";
			return s; // will overwrite s = returning, but try still returns
			// which leads to the question, is the returned variable ever seen by the caller such that
			// a thread could receive a signal, start a computation on the called object and expect
			// the value to be that of s = new ref

			// result no, try is not seen in the calling code, but it would be of use to understand
			// at what point in the compilation or runtime the variable return try to the stack, and
			// try to intercept the code to call another method while the giving the finally return
		}
	}
}
