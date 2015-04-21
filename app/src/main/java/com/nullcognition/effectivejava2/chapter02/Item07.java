package com.nullcognition.effectivejava2.chapter02;

import android.util.Log;

/**
 * Created by ersin on 18/04/15 at 8:02 PM
 */

// avoid finalizers

// finalizers are unpredictable, dangerous, unnecessary
// finalizers are not for time critical, persistent state update critical, operations
// finalizers have a performance penalty
// provide explicity termination methods for files/threads in combiniation with try-finally
// finalizers should log a warning if it finds that a resource has not been terminated

public class Item07 {

   String s = "hello world";

   @Override
   protected void finalize(){ // non deteriministic, can be accessed from from other threads thus keeping the object alive, but finalized

	  try{
		 super.finalize(); // when finalizing, always finalized the parent object
	  }
	  catch(Throwable inThrowable){inThrowable.printStackTrace();}
	  if(s != null){ Log.e("Item07", "String not null, class did not terminateSelf");}
   }

   public void terminateSelf(){

	  s = null;

	  try{ finalize();}
	  catch(Throwable inThrowable){ inThrowable.printStackTrace();}

   }
}
