import ClassToTest.Person;
import se.umu.cs.unittest.TestClass;

/*
 * A non-correctly implemented test class which tests the class Person.
 * Takes parameters in constructor.
 */

public class PersonTest2 implements TestClass {
    private Person newPerson;

    public PersonTest2(String name, int age){
    }

    public void setUp() {
        newPerson = new Person("Johannes", 28);
    }

    public void tearDown() {
        newPerson = null;
    }

    //Test that should succeed
    public boolean testInitializeName() {
        return newPerson.getName().equals("Johannes");
    }

    //Test that should succeed
    public boolean testInitializeAge() {
        return newPerson.getAge() == 28;
    }

    //Test that should succeed
    public boolean testAddName() {
        newPerson.setName("Sofia");
        return newPerson.getName().equals("Sofia");
    }

    //Test that should succeed
    public boolean testAddAge() {
        newPerson.setAge(25);
        return newPerson.getAge() == 25;
    }

    //Test that should fail
    public boolean testFailingByException() {
        newPerson = null;
        newPerson.setName("Johanna");
        return true;
    }

    //Test that should fail
    public boolean testFailing() {
        return newPerson.getName().equals("Johanna");
    }

}

