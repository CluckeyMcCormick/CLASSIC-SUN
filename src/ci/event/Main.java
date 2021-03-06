package ci.event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author nicolas.fredrickson1
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame;
        final ViewApplet app;

        frame = new JFrame();
        app = new ViewApplet();

        frame.setSize(600, 500);

        frame.getContentPane().add(app);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                app.stop();
                app.destroy();
                System.exit(0);
            }
        });
        
        try {
            app.init();
            app.start();
            frame.setVisible(true);
            System.out.println("We're off the ground!");
        }
        catch (Exception E)
        {
            app.appWideMessage("Encountered unexpected error:\n" + E.getMessage());
        }
    }
}
