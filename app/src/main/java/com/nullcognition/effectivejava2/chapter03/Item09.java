package com.nullcognition.effectivejava2.chapter03;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ersin on 24/04/15 at 11:54 PM
 */

// always override hashCode when you override equals
// all hash based collections: map, table, set; will not work
// equal objects must have equal hash codes

public class Item09 {

    public class Num {
        protected int i;
        protected int importantInt = 3;
        protected char importantChar = 'c';

        public Num(int i) {
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            return i == (int) o;
        }
    }

    public void usingHash() {
        Map<String, Num> numMap = new HashMap<>();
        Num n = new Num(3);
        numMap.put("a", n);
        Log.e("logErr", "Hashmap contains value? " + numMap.containsValue(n)); // should be true
        // is false, due to no hash code. Or...

        if (numMap.get("a") == null) Log.e("logErr", "value is null");
    }

    public class HashableNum extends Num {

        public HashableNum(int i) {
            super(i);
        }

        @Override
        public int hashCode() {
            int result = 17;

            result = 31 * result + i;
            result = 31 * result + importantInt;
            result = 31 * result + importantChar;

            return result;
        }
    }

    public class LazyHashableNum extends Num {

        private volatile int hashCode;

        public LazyHashableNum(int i) {
            super(i);
        }

        @Override
        public int hashCode() {
            int result = hashCode;

            if (result == 0) {
                result = 31 * result + i;
                result = 31 * result + importantInt;
                result = 31 * result + importantChar;
                hashCode = result;
            }
            return result;
        }
    }
}

/*
* Steps for a good hash code: advanced theory not necessary lest it collisions be a problem

1. Store some constant nonzero value, say, 17, in an int variable called result .

2. For each significant field f in your object (each field taken into account by the
equals method, that is), do the following:

a. Compute an int hash code c for the field:

i. If the field is a boolean , compute (f ? 1 : 0) .

ii. If the field is a byte , char , short , or int , compute (int) f .

iii. If the field is a long , compute (int) (f ^ (f >>> 32)) .

iv. If the field is a float , compute Float.floatToIntBits(f) .

v. If the field is a double , compute Double.doubleToLongBits(f) , and
then hash the resulting long as in step 2.a.iii.

vi. If the field is an object reference and this class’s equals method
compares the field by recursively invoking equals , recursively
invoke hashCode on the field. If a more complex comparison is
required, compute a “canonical representation” for this field and
invoke hashCode on the canonical representation. If the value of the
field is null , return 0 (or some other constant, but 0 is traditional).

vii. If the field is an array, treat it as if each element were a separate field.
That is, compute a hash code for each significant element by applying
these rules recursively, and combine these values per step 2.b. If every
element in an array field is significant, you can use one of the
Arrays.hashCode methods added in release 1.5.

b. Combine the hash code c computed in step 2.a into result as follows:
result = 31 * result + c;

3. Return result .

4. When you are finished writing the hashCode method, ask yourself whether
equal instances have equal hash codes. Write unit tests to verify your intuition!
If equal instances have unequal hash codes, figure out why and fix the problem.
* */
