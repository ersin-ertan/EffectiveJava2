package com.nullcognition.effectivejava2.chapter04;
// ersin 28/10/15 Copyright (c) 2015+ All rights reserved.

// prefer class hierarchies to tagged classes
// tagged class suck, cluttered, do not have the affordances of proper type, memory footprint

public class Item20{

	enum Shape{A, B}


	final Shape shape;

	Item20(int i){
		shape = Shape.A;
	}

	Item20(){
		shape = Shape.B;
	}

	void logicBasedOnShapesType(){
		switch(shape){
			case A:
				break;
			case B:
				break;
			default:
				break;
		}
	}
}
