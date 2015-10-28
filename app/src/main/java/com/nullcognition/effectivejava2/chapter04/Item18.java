package com.nullcognition.effectivejava2.chapter04;
// ersin 28/10/15 Copyright (c) 2015+ All rights reserved.

// Prefer interfaces to abstract classes


public class Item18{

	class OldClass{ }


	interface NewInterface{ }


	class NewClass implements NewInterface{ } // easy to append


	abstract class AbstClass{ } // will break all subclasses


	interface AndOfThisTypeTooMixin{ }


	class TypeOfClass implements AndOfThisTypeTooMixin{ } // using the mixin now seen as two types


	interface Singer{

		void sing();
	}


	interface Songwriter{

		void write();
	}


	interface SingerSongwriter extends Singer, Songwriter{

		void serenade();
	}

	// construction of non hierarchical type framework


	abstract class Cupid implements SingerSongwriter{

		@Override public void serenade(){
			sing();
			write();
		}
	}


	class Person extends Cupid{

		@Override public void serenade(){
			super.serenade();
			String love = "is in the air";
		}

		@Override public void sing(){}

		@Override public void write(){}
	}

	// used in conjunction with the wrapper idiom


	class Wrapper{

		SingerSongwriter singerSongwriter;

		Wrapper(Cupid cupid){
			singerSongwriter = cupid; //
		}

		public void serenade(){
			singerSongwriter.serenade();
		}

		public void sing(){ singerSongwriter.sing();}

		public void write(){singerSongwriter.write();}
	}


	// designing classes with skeletal implementations for complex and structured inheritance/implementation
	// adapter idiom where a value is adapted to another value thus your skeletal concrete implementations
	// can often have the adaptation code embedded using the skeletal framework of how to structure the
	// implementation
	interface Skele{

		void m1();
		void m2(int i);
	}

	// standard implementation


	class SkeleClass implements Skele{

		@Override public void m1(){
			// do something
		}

		@Override public void m2(int i){
			// do but with no direction how
		}
	}

	// skeletal implementation for more structure


	abstract class SkeleAbst implements Skele{

		@Override public void m1(){

		}

		@Override public void m2(int i){
			int j = doMeth() + i;
		}

		public abstract int doMeth(); // directed to do here
	}


	SkeleAbst sa = new SkeleAbst(){ // often declared as an anonymous class within the static factory
		@Override public int doMeth(){
			return 0;
		}
	};


	class CantExtend implements Skele{

		// but can wrap: simulated multiple inheritance, either of the anon class or a concrete which has
		// its methods defined, this class is now a CantExtend and Skele type
		SkeleAbst skeleAbst = new SkeleAbst(){
			@Override public int doMeth(){
				return 0;
			}

			@Override public void m1(){
				super.m1();
				String doSomething = "as I define it to be done";
			}
		};

		@Override public void m1(){
			skeleAbst.m1();
		}

		@Override public void m2(final int i){
			skeleAbst.m2(i);
		}
	}

	// the simple allows us do implement the base/default/easy case


	class SimpleSkele extends SkeleAbst{

		@Override public void m1(){
			super.m1();
			int c = 9;
		}

		@Override public void m2(final int i){
			super.m2(i);
			int j = 8;
		}

		@Override public int doMeth(){
			return 3;
		}
	}


	class CantExtendSimple implements Skele{

		SimpleSkele sk = new SimpleSkele();

		@Override public void m1(){
			sk.m1();
		}

		@Override public void m2(final int i){
			sk.m2(i);
		}
	}

	// but it is easier to evolve the abstract class than an interface... as the interface is

	// impossible to change once released without breaking code, which could be ok for internal/private
}
