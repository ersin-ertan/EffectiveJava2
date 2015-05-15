package com.nullcognition.effectivejava2.chapter03;

/**
 * Created by ersin on 24/04/15 at 11:54 PM
 */

// Consider implementing Comparable
// compareTo method and indicates that class has a natural ordering ex. Arrays.sort(input);
// allows the class to interoperate with generic algorithms and collection implementations that depend on the interface
//

public class Item12 implements Comparable<Item12>{
	public int i = 0;

	public void t(){
	}
	@Override
	public int compareTo(final Item12 another){// this > another ? 1 : this < another ? -1 : 0
		return this.i > another.i ? 1 : this.i < another.i ? -1 : 0;
	}
}

// signum function wil return -1, 0, 1 based on the false, zero, positive value
// x.compareTo(y) == -sgn(y.compareTo(x))
// x.compareTo(y) > 0 && y.compareTo(z) > 0 implying that x.compareTo(z) > 0
// x.compareTo(y) == 0, sgn(x.compareTo(y)) == sgn(y.compareTo(z)) for all of z
// and if (x.compareTo(y) == 0) == (x.equals(y)) is not true, make a not that natural ordering is inconsistent with equals
