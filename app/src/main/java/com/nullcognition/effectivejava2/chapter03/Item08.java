package com.nullcognition.effectivejava2.chapter03;

import android.graphics.Color;

import java.util.LinkedList;
import java.util.List;

/**
 Created by ersin on 24/04/15 at 11:53 PM
 */

// obey the general contract when overriding equal.
// Equivalence relation -
// reflexive(x.equals(x) == true for non null),
// symmetric(x.equals(y) == y.equals(x)),
// transitive x.equals(y), y.equals(z), z.equals(x),
// consistent x.equals(y) consistently idempotent
// x.equals(null) false always

//    writing a good equals - use == to check if the argument is a reference to the object
//    use instanceof operator to check if the argument has the correct type
//    cast the argument to the correct type
//    test significant field in class with that of the arguments fields for matches

//    always override hashCode

//    don't do this public boolean equals(MyClass o) {
//    using the MyClass instead of object in not sufficient for @Override -ing the equals method

// == to check if the argument is a reference to the same object
// instanceof to check if the argument has the correct type
// if prior succeeds, cast the argument to the correct type, check that each significant field matches the equals


public class Item08{

	// CaseInsensitiveString must equal itself else collections and other checks will not be referable.
	public void reflexive(){
		List<CaseInsensitiveString> list = new LinkedList<>();
		CaseInsensitiveString caseInsensitiveString = new CaseInsensitiveString("s");

		list.add(caseInsensitiveString);
		list.contains(caseInsensitiveString);
	}

	public final class CaseInsensitiveString{
		private final String s;

		public CaseInsensitiveString(String s){
			if(s == null){ throw new NullPointerException(); }
			this.s = s;
		}

		// broken - violates symmetry
//        @Override
//        public boolean equals(Object o) {
//            if (o instanceof CaseInsensitiveString)
//                return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
//            if (o instanceof String) // one-way interoperability
//                return s.equalsIgnoreCase((String) o);
//            return false;
//        }

		@Override
		public boolean equals(Object o){ // remember that equals argument is an object, else the method is not overridden
			return o instanceof CaseInsensitiveString && ((CaseInsensitiveString) o).s.equalsIgnoreCase(s); // one return statement with boolean logic
		}
	}

	// transitive - with point class
	class Point{
		protected final int x;
		protected final int y;

		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o){
			if(!(o instanceof Point)){
				return false;
			}
			Point p = (Point) o;
			return p.x == x && p.y == y;
		}
	}

	public class ColorPoint extends Point{
		private final Color color;

		public ColorPoint(int x, int y, Color c){
			super(x, y);
			color = c;
		}
	}
//----------------------------------------------------------------------------------------------------
//        @Override // violates symmetry - different results from point to color point and using type will always return false
//        public boolean equals(Object o) {
//            if (!(o instanceof ColorPoint)) return false;
//            return super.equals(o) && ((ColorPoint) o).color == color;
//        }
//          Point p = new Point(1, 2);
//          ColorPoint cp = new ColorPoint(1, 2, Color.RED);
//----------------------------------------------------------------------------------------------------
	//        // Broken - violates transitivity first two compares are color blind while last from p3 to p1 returns mismatch
//        @Override
//        public boolean equals(Object o) {
//            if (!(o instanceof Point))
//                return false;
//// If o is a normal Point, do a color-blind comparison
//            if (!(o instanceof ColorPoint))
//                return o.equals(this);
//// o is a ColorPoint; do a full comparison
//            return super.equals(o) && ((ColorPoint) o).color == color;
//        }
//        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
//        Point p2 = new Point(1, 2);
//        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
//----------------------------------------------------------------------------------------------------
//        @Override // broken - violates liskov substitution principle
//  any important property of a type should also hold for its subtypes, so that any method written for the type should work equally well on its subtype
//        public boolean equals(Object o){
//            if(o == null || o.getClass() != getClass()) return false;
//            Point p = (Point)o;
//            return p.x == x && p.y == y;
//        }
// ex point in a hashset use equals to test containment with the getClass, therefore Color point is not a point
// unless you use instanceof based equals, by composing the class instead of extending it - color point has a point
// which returns the point-ized version of the color point

}
