package com.nullcognition.effectivejava2.chapter02;

/**
 * Created by ersin on 18/04/15 at 8:01 PM
 */

// consider a builder when faced with many constructor paramaters

// hard to read/write telescoping constructors/static factories with four or more parameters
// java bean pattern(empty constructor, multiple set... methods for fields), may be in an inconsistant state partway through
//    |-> immutabiliy is impossible, requires modification for thread safety

// simulates named optional parameters

public class Item02 {

   // initialized in the constructor, else error: variable '' might not have been initialized
   private final int    one;
   private final String two;
   private final char   three;
   private final Object four;

   private Item02(Builder inBuilder){

	  one = inBuilder.one;
	  two = inBuilder.two;
	  three = inBuilder.three;
	  four = inBuilder.four;
   }

   public static class Builder {

	  // required params
	  private final int    one;
	  private final String two;

	  // optional, initialized to defaults
	  private char   three = 'c';
	  private Object four  = Object.class;

	  public Item02 build(){ return new Item02(this); } // when the optional parameters are not set, will use fields initialized values

	  public Builder(int inOne, String inTwo){

		 one = inOne;
		 two = inTwo;
	  }

	  public Builder three(char inThree){

		 three = inThree;
		 return this;
	  }

	  public Builder four(Object inFour){

		 four = inFour;
		 return this;
	  }
   }
}

class UsingBuilder {

   // above builder
   UsingBuilder(){
	  // optional and build methods
	  Item02 item02 = new Item02.Builder(1, "two").three('3')
												  .four(new Object())
												  .build();
   }

   // below builder interface
   Item02 builtObject(Builder<? extends Item02> item02Builder){

	  return item02Builder.build(); // not sure how this is used with the other build methods like one, two, three
   }
}

// A builder for objects of type T
interface Builder <T> {

   T build();
}

class TelescopingConstructor {

   int i, c, d, e;

   TelescopingConstructor(int i){this.i = i;}

   TelescopingConstructor(int i, int c){
	  this.i = i;
	  this.c = c;
   }

   TelescopingConstructor(int i, int c, int d){
	  this.i = i;
	  this.c = c;
	  this.d = d;
   }

   TelescopingConstructor(int i, int c, int d, int e){
	  this.i = i;
	  this.c = c;
	  this.d = d;
	  this.e = e;
   }
   // TelescopingConstructor(int i, int c, int e){} // three int argument already defined
}

// leaves class construction in inconsistent state
class JavaBean {

   int required;
   int a = 1; // sensible default
   long   b; // optional
   String c;

   JavaBean(int required){this.required = required;}

   public void setA(int a){this.a = a;}

   public void setB(long b){this.b = b;}

   public void setC(String c){this.c = c;}

}
