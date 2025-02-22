package com.mochatitan;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 200;
    private static final String FRAME_TITLE = "My JFrame";
    private static final String LABEL_TEXT = "Hello, this is a JFrame!";

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        // Create a label with text
        JLabel label = new JLabel(LABEL_TEXT, SwingConstants.CENTER);
        
        // Add the label to the frame
        frame.add(label);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
