package com.nullcognition.effectivejava2.chapter02;

/**
 * Created by ersin on 18/04/15 at 8:02 PM
 */

// enforce noninstantiability with a private constructor

// attempting to ennforce noninstantiablity by abstract class does not work

public class Item04 {}

class NonInstantiable {

   private NonInstantiable(){ throw new AssertionError();}
}
