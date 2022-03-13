package Controller;
import Model.Model;
import View.GUIForTest;
import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * The controller class interacts with the class Model and GUIForTest.
 */
public class Controller {
    private GUIForTest viewForUser;
    private Model model;

    /**
     * Calls for method runSwing
     */
    public Controller() {
        runSwing();
    }

    /**
     * Updates and views the GUI on event dispatch thread
     */
    public void runSwing() {
        SwingUtilities.invokeLater(() -> {
            viewForUser = new GUIForTest(actionEvent -> runTestButton());
            viewForUser.show();
        });
    }

    /**
     * Runs the update of GUI on the worker thread
     */
    public void runTestButton() {
        new SwingWorker<String, Void> () {
            @Override
            protected String doInBackground() {
                String className = viewForUser.getTextInput();
                model = new Model();
                model.controlTest(className);
                return model.getTextToGUI();
            }

           @Override
            protected void done()
            {
                try {
                    String string = get();
                    viewForUser.getTextOutput(string);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("An exception was thrown: " + e.getCause());
                }
            }
        }.execute();
    }
}

