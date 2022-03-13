package Model;
import se.umu.cs.unittest.TestClass;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A class Model that runs test methods and sets the test result.
 */
public class Model {
    private String name;
    private Class<?> className;
    private Object instance;
    private Method[] testMethods;
    private int setUp;
    private int tearDown;
    private String textToGUI = "";
    private Integer success = 0;
    private Integer fail = 0;
    private Integer failByException = 0;

    public Model(){

    }

    /**
     * A method that checks if the class is a correct test class.
     * Runs the test if correctly implemented class, otherwise an exception is thrown.
     * @param name a string from text field
     */
    public void controlTest(String name) {
        try {
            this.name = name;
            className = Class.forName(name);

            if (className.isInterface()) {
                textToGUI += "Class should not be an interface";
            } else{
                instance = className.getDeclaredConstructor().newInstance();

                if ((instance instanceof TestClass) &&
                        (className.getConstructor().getParameterCount() == 0)){
                    runTest();
                } else{
                    textToGUI += "Class " +name+ " has no interface or has parameter in constructor";
                }
            }
        }catch(NoSuchMethodException exception){
            textToGUI += "Class " +name+ " does not implement correct interface or takes parameters in constructor";
        } catch (InstantiationException e) {
            textToGUI += "Class " +name+ " can not create an instance";
        } catch (InvocationTargetException e) {
            textToGUI += "Class " +name+ " can not get the constructor";
        } catch (IllegalAccessException e) {
            textToGUI += "Class " +name+ " can not create an instance";
        }catch(ClassNotFoundException exception){
            textToGUI += "Class " +name+ " does not exist";
        }
    }

    /**
     * Runs the test class by looping through every test method in the class.
     * Creates a setup and/or teardown if these exist.
     * Calls for runMethod if the test method is implemented correctly.
     */
    public void runTest() throws InvocationTargetException, IllegalAccessException {
        testMethods = className.getDeclaredMethods();

        for(int i = 0; i < testMethods.length; i++) {

            String testString = testMethods[i].getName();

            if(testString.startsWith("test")
                    && (testMethods[i].getParameterCount() == 0)
                    && testMethods[i].getReturnType().equals(Boolean.TYPE)){

                if(checkSetUp()) {
                    testMethods[setUp].invoke(instance);
                }

                runMethod(i);

                if(checkTearDown()) {
                    testMethods[tearDown].invoke(instance);
                }
            }
        }


        printSummarizeTest();
    }

    /**
     * Loops through methods in test class to find setup.
     * @return if setup exist, return true, otherwise false
     */
    private boolean checkSetUp() {
        for(int i = 0; i < testMethods.length; i++) {
            if (testMethods[i].getName().equals("setUp")) {
                setUp = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Loops through methods in test class to find teardown.
     * @return if teardown exist, return true, otherwise false
     */
    private boolean checkTearDown() {
        for(int i = 0; i < testMethods.length; i++) {
            if (testMethods[i].getName().equals("tearDown")) {
                tearDown = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Calls the method at run time and saves the result from method in testToGUI.
     * @param i an integer representing the index of array of test methods
     */
    public void runMethod(int i){
        Boolean resultTrue;

        try {
            resultTrue = (Boolean) testMethods[i].invoke(instance);

            //If resultTrue is boolean of type true, the method is successful, otherwise a fail
            if (resultTrue) {
                textToGUI += testMethods[i].getName() + ": SUCCESS\n";
                success++;
            } else {
                textToGUI += testMethods[i].getName() + ": FAIL\n";
                fail++;
            }

        } catch (IllegalAccessException e) {
            textToGUI += "Can not invoke the test method of test class";
        } catch (InvocationTargetException e) {
            textToGUI += testMethods[i].getName() + ": FAIL Generated a" + e.getCause().toString() + "\n";
            failByException++;
        }
    }

    /**
     * Adds the summarize of the running test class to textToGUI.
     */
    public void printSummarizeTest(){
        textToGUI += ("\n" + success + " tests succeeded\n"
                        + fail + " tests failed\n"
                        + failByException + " tests failed because of an exception\n\n");
    }

    /**
     * Gets the string with test result and summarize.
     * @return a string to show in the view for user
     */
    public String getTextToGUI(){
        return textToGUI;
    }

    public int getNumSuccess(){
        return success;
    }

    public int getNumFails(){
        return fail;
    }

    public int getNumFailsByException(){
        return failByException;
    }
}
