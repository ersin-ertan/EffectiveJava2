package com.nullcognition.effectivejava2.chapter02;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ersin on 18/04/15 at 8:01 PM
 */

// consider static factory methods instead of constructors

// Advantages -
// are named and express functionality
// not required to create a new object when invoked, allowing for object caching and preconstructed instances (instance controlled)
// can return an object of any subtype of their defined return type, compact api, interface based framework
// reduce verbosity of parameterized type instances

// Disadvantages -
// classes without public/protected constructors cannot be subclassed
// not distinguishable from static methods

public class Item01 {

   // an example of static factory with the boxed primitive class
   public static Boolean valueOf(boolean b){ return b ? Boolean.TRUE : Boolean.FALSE; }
}

interface Service {

   void doServiceStuff();
}

interface Provider {

   Service newService();
}

class Services {

   private static final Map<String, Provider> providers             = new ConcurrentHashMap<>();
   public static final  String                DEFAULT_PROVIDER_NAME = "<def>";

   private Services(){} // uninstantiable

   public static void registerDefaultProvider(Provider p){registerProvider(DEFAULT_PROVIDER_NAME, p);}

   public static void registerProvider(String name, Provider p){providers.put(name, p);}

   public static Service newInstance(){ return newInstance(DEFAULT_PROVIDER_NAME); }

   public static Service newInstance(String name){

	  Provider p = providers.get(name);
	  if(p == null){throw new IllegalArgumentException("no provdier registered with name:" + name);}
	  return p.newService();
   }
}
