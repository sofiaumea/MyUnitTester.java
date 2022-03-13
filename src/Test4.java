import ClassToTest.MyInt;
import se.umu.cs.unittest.TestClass;

/*
 *Non-correctly implemented test class, takes parameter in method
 */

public class Test4 implements TestClass {
    private MyInt myInt;

    public Test4() {
    }

    public void setUp() {
        myInt=new MyInt();
    }

    public void tearDown() {
        myInt=null;
    }

    //Test that should succeed
    public boolean testInitialisation(int i) {
        return myInt.value()==0;
    }

    //Test that should succeed
    public boolean testIncrement() {
        myInt.increment();
        myInt.increment();
        return myInt.value()==2;

    }

    //Test that should succeed
    public boolean testDecrement() {
        myInt.increment();
        myInt.decrement();
        return myInt.value()==0;
    }

    //Test that should fail
    public boolean testFailingByException() {
        myInt=null;
        myInt.decrement();
        return true;

    }

    //Test that should fail
    public boolean testFailing() {
        return false;

    }


}
