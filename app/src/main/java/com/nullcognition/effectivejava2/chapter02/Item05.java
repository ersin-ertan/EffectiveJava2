package com.nullcognition.effectivejava2.chapter02;

/**
 * Created by ersin on 18/04/15 at 8:02 PM
 */

// avoid creating unnecessary objects

// an object can always be reused if it is immutable
// use static factory methods
// prefer primitives to boxed primitives, watch out fo unintentional autoboxing

public class Item05 {

   String s  = new String("no need"); // extra object instantiation
   String ss = "just change the text";

   boolean b  = new Boolean("false");
   boolean bb = Boolean.valueOf("true"); // returns a value with out creation
}

enum SingletonThatHasALongRunningOperationAndIsExpensiveToCreate {
   INSTANCE;

   byte[] aByte = new byte[1024];

   public int longRunningCalculation(){

	  Integer sum = 0; // note the use of long
	  for(int i = 0; i < Integer.MAX_VALUE; i++){
		 sum += i; // each primitive int i will be autoboxed(unboxing is from Object to primitive) to a Integer Object Integer.MAX_VALUE times
	  }
	  return sum; // performance may be worse from long to Long
   }
}

class UnnecessaryPerInstanceAllocation {

   private final int internalValue = 0;

   public boolean checkValue(){

	  SingletonThatHasALongRunningOperationAndIsExpensiveToCreate s = SingletonThatHasALongRunningOperationAndIsExpensiveToCreate.INSTANCE;
	  int result = s.longRunningCalculation();
	  // create more variables, compare with the result, return the final value
	  int resultSquared = result * result;
	  return resultSquared == internalValue || resultSquared - result < internalValue;
   }
}

class BetterWithSingleStaticAllocation {

   private final int internalValue = 0;

   private static final int RESULT; // created as static value, once for class instance
   private static final int RESULTSQUARED; // adds clarity with constants

   static{
	  SingletonThatHasALongRunningOperationAndIsExpensiveToCreate s = SingletonThatHasALongRunningOperationAndIsExpensiveToCreate.INSTANCE;
	  RESULT = s.longRunningCalculation();
	  // assigning variables once
	  RESULTSQUARED = RESULT * RESULT;
   }

   public boolean checkValue(){ return RESULTSQUARED == internalValue || RESULTSQUARED - RESULT < internalValue;}
}
