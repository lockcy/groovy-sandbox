package org.example;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;

import java.util.Arrays;

public class AstGroovySandbox {
    public static void main(String[] args) {
        // 创建编译器配置
        CompilerConfiguration config = new CompilerConfiguration();

        SecureASTCustomizer secure = new SecureASTCustomizer();
        // 禁用所有系统级调用
        secure.setImportsBlacklist(Arrays.asList("java.lang.Runtime"));
        secure.setIndirectImportCheckEnabled(true);
        secure.setMethodDefinitionAllowed(false);  // 禁止定义新的方法
        config.addCompilationCustomizers(secure);

        // 使用 ImportCustomizer 限制导入
        ImportCustomizer importCustomizer = new ImportCustomizer();
        importCustomizer.addStarImports("java.util");  // 只允许导入java.util包
        config.addCompilationCustomizers(importCustomizer);

        // 禁止危险类
        config.setScriptBaseClass("groovy.lang.Script");
        Binding binding = new Binding();

        // 创建GroovyShell实例
        GroovyShell shell = new GroovyShell(binding, config);

        // 尝试执行恶意脚本（会被拦截）
        try {;
            String unsafeScript = "def c = Class\n" +
                    "def f =c.&('forN'+'ame')\n" +
                    "def clazz = f(\"groovy.ui.GroovyMain\")\n" +
                    "def func = clazz.&main\n" +
                    "func(new String[]{\"-e\", \"Runtime.getRuntime().exec(\\\"calc\\\")\"})";
            shell.evaluate(unsafeScript);
        } catch (Exception e) {
            System.out.println("危险代码被拦截: " + e.getMessage());
        }
    }
}
