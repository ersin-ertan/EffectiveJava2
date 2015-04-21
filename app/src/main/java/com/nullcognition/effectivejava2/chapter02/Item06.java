package com.nullcognition.effectivejava2.chapter02;

import java.util.EmptyStackException;

/**
 * Created by ersin on 18/04/15 at 8:02 PM
 */

// eliminate obsolete object references

// obsolete references will never be dereferenced (unintentional object retentions), be sure to null out obsolete references
// nulling out object references should be the exception rather than the norm
// classes that manage their own memory should be on alert for leaks, ex. caches
// listeners and other callbacks may leak, keys should be stored in a WeakHashMap

public class Item06 {


}

class CappedStack {

   private Object[] objects;
   private              int size             = 0;
   private static final int DEFAULT_CAPACITY = 8; // managing its own memory with the objects array

   public CappedStack(){ objects = new Object[DEFAULT_CAPACITY]; }

   public void push(Object object){

	  if(size >= DEFAULT_CAPACITY){ throw new StackOverflowError(); }
	  objects[size++] = object;
   }

   // objects popped off the stack are not garbage collected, elements outside of the poppable region are kept but will never be dereferenced
   // because their data will be overwritten with push operations, normal stack implementations have growing capacities and during long term
   // persistent use, may collect a large amount of objects to be popped to another stack structure due to an abritrary serlialization limitation
   // pop != null, so the size of the stack will persist even though the objects are not logically reachable
   public Object pop(){

	  if(size == 0){ throw new EmptyStackException(); }
	  return objects[-- size];
   }

   public Object safePop(){

	  if(size == 0){ throw new EmptyStackException(); }

	  Object result = objects[-- size];
	  objects[size] = null; // null values are garbage collected

	  return result;
   }
}
