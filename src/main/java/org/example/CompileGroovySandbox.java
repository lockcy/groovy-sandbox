package org.example;

import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.kohsuke.groovy.sandbox.SandboxTransformer;

public class CompileGroovySandbox {
    public static void main(String[] args) {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new SandboxTransformer());

        GroovyShell shell = new GroovyShell(config);
        String cmd = "def m4 = 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!'==~/(.*a){10000}/";
        cmd = "String a = \"123\"\n" +
                "println a";
        shell.evaluate(cmd);
    }
}
