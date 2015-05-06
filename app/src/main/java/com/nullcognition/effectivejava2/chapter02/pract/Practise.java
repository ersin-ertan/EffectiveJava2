package com.nullcognition.effectivejava2.chapter02.pract;

/**
 * Created by ersin on 21/04/15 at 11:30 PM
 */
public class Practise {

   {
	  ToBeBuilt toBeBuilt = new ToBeBuilt.TheBuilder(1, 0L).aString("optional")
														   .build();
	  int bc = ToBeBuilt.TheBuilder.buildCount();
	  // or
	  toBeBuilt = new ToBeBuilt.TheBuilder(0, 1L).build();
	  bc = ToBeBuilt.TheBuilder.buildCount();
   }
}

class ToBeBuilt {

   private final int    anInt;
   private final long   aLong;
   private final String aString;


   private ToBeBuilt(TheBuilder builder){

	  anInt = builder.theInt;
	  aLong = builder.theLong;
	  aString = builder.theString;
   }

   protected static class TheBuilder {

	  private static int counter     = 0;
	  public final   int buildNumber = counter;

	  private final int  theInt;
	  private final long theLong;
	  private String theString = "some sensible default as this parameter is optional";

	  public ToBeBuilt build(){return new ToBeBuilt(this);}

	  public TheBuilder(int ii, long ll){

		 theInt = ii;
		 theLong = ll;
		 ++ counter;
	  }

	  public TheBuilder aString(String ss){

		 theString = ss;
		 return this;
	  }

	  public static int buildCount(){ return counter;} // use static returns
   }
}
