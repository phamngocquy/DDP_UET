package test;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;

public class VisitorAdapterTest {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("/home/haku/IdeaProjects/DDP_UET/examples/Builder Example/ItalianMealBuilder.java");

        // parse it
        CompilationUnit cu = JavaParser.parse(in);
//        System.out.println(cu.getPrimaryTypeName());
//        System.out.println(cu);
//        System.out.println(cu.getType(0).getName());

        // visit and print the methods names

        ClassOrInterfaceDeclaration c = (ClassOrInterfaceDeclaration) cu.getType(0);
        System.out.println(c);
        System.out.println(c.isInterface());
        System.out.println(c.isAbstract());

//        System.out.println(c.getExtendedTypes());
//        System.out.println("Class Name: " + c.getName());
//        System.out.println("Class Modifier: " + c.getModifiers());
//        System.out.println(c.getImplementedTypes());
//        System.out.println(c.getExtendedTypes());
//        c.accept(new MethodVisitor(), null);
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */
            System.out.println(n.getName());
            System.out.println(n.getType());
            System.out.println(n.getDeclarationAsString());
            System.out.println(n.getDeclarationAsString(false, false));
            System.out.println(n.getModifiers());
            System.out.println("//==================//");
            /*
                done
             */
            super.visit(n, arg);
        }
    }
}
