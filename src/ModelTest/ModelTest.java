package ModelTest;
import Model.Model;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;

public class ModelTest {
    @Test
    public void testIfClassExists(){
        Model model = new Model();
        model.controlTest("Test1");
    }

    @Test
    public void testIfClassNotExists(){
        Model model = new Model();
        model.controlTest("Test123");
        String expectedMessage = "Class Test123 does not exist";
        String actualMessage = model.getTextToGUI();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testIfClassDoesNotHaveInterface(){
        Model model1 = new Model();
        model1.controlTest("PersonTest2");
        String expectedMessage = "Class PersonTest2 does not implement correct interface";
        String actualMessage = model1.getTextToGUI();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testClassReturnsSuccess(){
        Model model2 = new Model();
        model2.controlTest("Test1");
        assert model2.getNumSuccess() == 3;
    }

    @Test
    public void testClassReturnsFail(){
        Model model3 = new Model();
        model3.controlTest("PersonTest1");
        assert model3.getNumFails() == 1;
    }

    @Test
    public void testClassReturnsFailByException(){
        Model model4 = new Model();
        model4.controlTest("Test1");
        assert model4.getNumFailsByException() == 1;
    }
}
