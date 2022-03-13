package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A class that creates the graphical user interface. This is the view for the end-user.
 * Views the result different tests.
 */
public class GUIForTest {
    private final JFrame f;
    private JTextField textField;
    private JTextArea displayText;
    private final ActionListener listen;
    JScrollPane scrollBar;

    /**
     * Builds the GUIForTest object from top, middle and bottom panel.
     * @param listen an action listener used for buttonTest
     */
    public GUIForTest(ActionListener listen) {
        this.listen = listen;
        f = new JFrame("My Unit Tester");

        JPanel topPanel = createTopPanel();
        JPanel middlePanel = createMiddlePanel();
        JPanel bottomPanel = createBottomPanel();

        f.add(topPanel, BorderLayout.NORTH);
        f.add(middlePanel, BorderLayout.CENTER);
        f.add(bottomPanel, BorderLayout.SOUTH);

        f.add(scrollBar);
        f.setPreferredSize(new Dimension(650, 650));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates the top panel including text field to enter desired test and run button.
     * @return the JPanel with text field and button
     */
    private JPanel createTopPanel(){
        JPanel top = new JPanel(new BorderLayout());
        top.setPreferredSize(new Dimension(600, 50));

        JButton buttonTest = new JButton("Run test");
        textField = new JTextField(20);

        JRootPane jf = f.getRootPane();
        jf.setDefaultButton(buttonTest);

        top.add(buttonTest, BorderLayout.EAST);
        top.add(textField, BorderLayout.CENTER);
        top.setBorder(BorderFactory.createTitledBorder("Enter desired test in text field"));
        buttonTest.addActionListener(listen);

        return top;
    }

    /**
     * Creates the middle panel including the text area for displaying test result.
     * @return the JPanel with text area
     */
    private JPanel createMiddlePanel(){
        JPanel middle = new JPanel();
        middle.setBorder(BorderFactory.createTitledBorder("Result from test"));
        middle.setLayout(new BorderLayout());

        displayText = new JTextArea();
        displayText.setEditable(false);

        middle.add(displayText, BorderLayout.CENTER);
        middle.setPreferredSize(new Dimension(600, 600));

        scrollBar = new JScrollPane(displayText,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return middle;
    }

    /**
     * Creates the bottom panel including the clear button.
     * @return the JPanel with a button
     */
    private JPanel createBottomPanel(){
        JPanel bottom = new JPanel();
        bottom.setBorder(BorderFactory.createEmptyBorder());
        bottom.setPreferredSize(new Dimension(600, 40));

        JButton buttonClear = new JButton("Clear");
        bottom.add(buttonClear, BorderLayout.CENTER);

        //User clicks and removes test result
        buttonClear.addActionListener(e -> displayText.setText(null));

        return bottom;
    }

    /**
     * Makes the window view for user visible.
     */
    public void show() {
        f.setVisible(true);
    }

    /**
     * Gets the user input from text field, should be a test class.
     * @return a string containing name of test class
     */
    public String getTextInput(){
        return textField.getText();
    }

    /**
     * Gets the output(result from test) from text area.
     * @param s a string representing result from test methods
     */
    public void getTextOutput(String s){
        displayText.setText(s);
    }
}
