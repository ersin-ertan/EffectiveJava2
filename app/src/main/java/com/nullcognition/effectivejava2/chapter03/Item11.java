package com.nullcognition.effectivejava2.chapter03;

/**
 Created by ersin on 24/04/15 at 11:54 PM
 */

// override clone judiciously
// Cloneable interface was intended as a mixin - a defined amount of functionality which can be added to a class

//	problem with creating a clone without calling the constructor but by having the same super class, but
//	the clone could not have been a child class of the original super class because the constructor, which
//	calls the super.constructor() will not be used. x.clone() != x

public class Item11{

	// never make the client do something that the library can
	// Cloneable does not offer clone method!


	@Override
	protected Item11 clone(){
		// covariant return types allowed
		try{return (Item11) super.clone();} // return the object obtained by super.clone
		catch(CloneNotSupportedException e){ e.printStackTrace();}
		return null;
	}

	// to clone a stack, clone acts as another constructor, but must no harm the original object
	// thus you must call the fields clone methods as well(recursively), however if you try to clone some types like
	// a hash, you maintain the hashed value which would need a deep copy so the array does not reference
	// the same linked list as the original

	// recursive deep copy may cause stack overflow so you can use iterative copy

	// extending a Cloneable requires a new copy method, otherwise use another means or don't provided the capability
	// ex. copy constructor - constructor takes in its own type
	// copy factory - public static Type newInstance(Type type)

	// interface versions of the above two: conversion constructors/factories allow the client to choose
	// the implementation type of the copy

	// most often used with arrays. if the base class does not offer a clone method, then inherited classes
	// cannot implement their own clone
}

