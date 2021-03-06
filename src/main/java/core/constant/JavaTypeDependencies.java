package core.constant;

public enum JavaTypeDependencies {
    X, /*class A extends class B*/
    I, /*class A implements class B*/


    /**
     * high level behavioral relations extracted from sequence diagrams.
     */
    C, /* class A create object of class B */
    R, /* class A has the return type of class B*/
    MC, /*class A call a method of class B */


    F, /* class A has the field type of class B*/


    MR, /*class A has a method with return type of class B*/
    MI, /*class A has a method that has an input parameter with the type of Class B*/
    ML, /*class A has a method that defines a local variable with the type of class B*/




    G, /*class A uses class B in a generic type declaration*/
    M, /* class A has related with its method of class B*/
    O, /* class A overrides of class B */
}
