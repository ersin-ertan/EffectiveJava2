package com.nullcognition.effectivejava2.chapter03.practice00;// Created by ersin on 22/06/15

public class Chap03Concepts{

	class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(final Object o){
			if(o == null || !(o instanceof Point)){ return false; }
			Point p = (Point) o;
			return p.x == this.x && p.y == this.y;
		}
	}

	class Color{
		int r, g, b;
		@Override
		public boolean equals(final Object o){
			if(o == null || !(o instanceof Color)){ return false; }
			Color c = (Color) o;
			return c.r == this.r && c.g == this.g && c.b == this.b;
		}
	}

	class Point_Color extends Point{ // composition for equivalence checking
		private Color color;
		Point_Color(int x, int y, Color color){
			super(x, y);
			this.color = color;
		}
		@Override
		public boolean equals(final Object o){
			if(o == null || !(o instanceof Point_Color) && !(super.equals(o))){ return false; }
			return this.color.equals(((Point_Color) o).color);
//			if specifics were not relevant then color would be disregarded, and so would the instanceof Point_Color
			// in favor of just matching points
		}
	}
}

//#creativeCode
class M<a>{void o(char u,int i){}}
class k<a> extends M{int L;static M e(int Y){return new M();}{((
		M<a>)k.e(L)).o('v',3);}}
