import ClassToTest.MyInt;
import se.umu.cs.unittest.TestClass;

/*
* Test class that does not work correctly.
* Methods does not return the correct value.
*/
public class Test2 implements TestClass {
    private MyInt myInt;

    public Test2() {
    }

    public void setUp() {
        myInt = new MyInt();
    }

    public void tearDown() {
        myInt = null;
    }

    //Test that should succeed but does not
    public boolean testInitialisation() {
        return myInt.value() == 1;
    }

    //Test that should succeed but returns false
    public boolean testIncrement() {
        myInt.increment();
        myInt.increment();
        return myInt.value() == 3;
    }

    //Test that should succeed but returns false
    public boolean testDecrement(){
        myInt.increment();
        myInt.decrement();
        return myInt.value() == 10;
    }

    //Test that should fail
    public boolean testFailingByException() {
        myInt = null;
        myInt.decrement();
        return true;
    }

    //Test that should fail
    public boolean testFailing() {
        return false;
    }


}
