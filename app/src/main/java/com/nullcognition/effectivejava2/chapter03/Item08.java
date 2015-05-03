package com.nullcognition.effectivejava2.chapter03;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ersin on 24/04/15 at 11:53 PM
 */

// obey the general contract when overriding equal. Equivalence relation - reflexive, symmetric, transitive, consistent


public class Item08 {

    // CaseInsensitiveString must equal itself else collections and other checks will not be referable.
    public void reflexive() {
        List<CaseInsensitiveString> list = new LinkedList<>();
        CaseInsensitiveString caseInsensitiveString = new CaseInsensitiveString("s");

        list.add(caseInsensitiveString);
        list.contains(caseInsensitiveString);
    }

    public final class CaseInsensitiveString {
        private final String s;

        public CaseInsensitiveString(String s) {
            if (s == null) throw new NullPointerException();
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
        public boolean equals(Object o) {
            return o instanceof CaseInsensitiveString && ((CaseInsensitiveString) o).s.equalsIgnoreCase(s); // one return statement with boolean logic
        }
    }

}
