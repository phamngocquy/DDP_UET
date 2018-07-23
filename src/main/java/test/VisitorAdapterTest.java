package test;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;

public class VisitorAdapterTest {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("C:\\Users\\Haku\\IdeaProjects\\DDP_UET\\examples\\Builder Example\\MealDirector.java");

        // parse it
        CompilationUnit cu = JavaParser.parse(in);
//        System.out.println(cu.getPrimaryTypeName());
//        System.out.println(cu);
//        System.out.println(cu.getType(0).getName());

        // visit and print the methods names

        ClassOrInterfaceDeclaration c = (ClassOrInterfaceDeclaration) cu.getType(0);

//        System.out.println(c);
//        System.out.println(c.isInterface());
//        System.out.println(c.isAbstract());

//        System.out.println(c.getExtendedTypes());
//        System.out.println("Class Name: " + c.getName());
//        System.out.println("Class Modifier: " + c.getModifiers());
//        System.out.println(c.getImplementedTypes());
//        System.out.println(c.getExtendedTypes());
        c.accept(new MethodVisitor(), null);
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            System.out.println(n.getParameters().get(0).getName());
            System.out.println(n.getName());
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */
           /* System.out.println(n.getName());
            System.out.println(n.getType());
            System.out.println(n.getDeclarationAsString());
            System.out.println(n.getDeclarationAsString(false, false));
            System.out.println(n.getModifiers());
            System.out.println("//==================//");*/
            /*
                done
             */
            super.visit(n, arg);
        }

        @Override
        public void visit(FieldDeclaration n, Void arg) {
         /*   System.out.println(n);
            System.out.println(n.getMetaModel());
            System.out.println(n.getVariables().toString());
            System.out.println(n.getModifiers());
            System.out.println(n.getCommonType());*/
            super.visit(n, arg);
        }
    }
}
