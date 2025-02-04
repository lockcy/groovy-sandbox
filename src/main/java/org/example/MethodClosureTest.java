package org.example;

import org.codehaus.groovy.runtime.MethodClosure;

public class MethodClosureTest {
    public static void main(String[] args) {
        MethodClosure mc = new MethodClosure("1".getClass(), "forName");
        mc.call("java.lang.Runtime");
    }
}
