package com.nullcognition.effectivejava2.chapter02;

import java.io.Serializable;

/**
 * Created by ersin on 18/04/15 at 8:02 PM
 */

// enforce the singleton property with a private constructor or an enum type

// difficult to mock a singleton, must implement an interface that serves as its type

public class Item03 {

   PreferredSingleton        preferredSingleton        = PreferredSingleton.INSTANCE;
   PublicFinalFieldSingleton publicFinalFieldSingleton = PublicFinalFieldSingleton.INSTANCE;
   StaticFactorySingleton    staticFactorySingleton    = StaticFactorySingleton.getInstance();

   {
	  preferredSingleton.someMethod();
	  publicFinalFieldSingleton.someMethod();
	  staticFactorySingleton.someMethod();
   }
}

enum PreferredSingleton { // best approach, auto serialized, protects against refelection attacks
   INSTANCE;

   public void someMethod(){ }
}

// declare all instance fields transient and implement the readResolve method, because each time a serialized instance is deserialized, a new
// instance will be created
class PublicFinalFieldSingleton implements Serializable {

   transient public static final PublicFinalFieldSingleton INSTANCE = new PublicFinalFieldSingleton();

   transient private SerializableSingleton<PublicFinalFieldSingleton> serializableSingleton;

   private PublicFinalFieldSingleton(){ serializableSingleton = new SerializableSingleton<>(this); }

   public void someMethod(){ }
}

class StaticFactorySingleton implements Serializable {

   transient private static final StaticFactorySingleton INSTANCE = new StaticFactorySingleton();

   transient private SerializableSingleton<StaticFactorySingleton> serializableSingleton;

   private StaticFactorySingleton(){ serializableSingleton = new SerializableSingleton<>(this); }

   public static StaticFactorySingleton getInstance(){ return INSTANCE; }

   public void someMethod(){ }
}

class SerializableSingleton <T> implements Serializable { // then does this become serializable, does this instance need transience?

   transient T INSTANCE;

   SerializableSingleton(T inInstance){INSTANCE = inInstance;}

   private T readResolve(){
	  // return the this instance, all other instances will be gc - this method of encapsulating the method in a class may cause
	  // a reference leak, otherwise it would be implemented in the singleton class
	  return INSTANCE;
   }

}
