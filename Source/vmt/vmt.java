
//Title:      Vehicle Maintenance Tracker 1.1
//Version:    
//Copyright:  Copyright (c) 2001
//Author:     Todd E. Isaacs
//Company:    
//Description:Track Maintence Records.
package vmt;

import javax.swing.UIManager;
import java.awt.*;

public class vmt {
    boolean packFrame = false;

    //Construct the application
  public vmt() {

        MainFrame frame = new MainFrame();
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame)
            frame.pack();
        else
            frame.validate();
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    //Main method
  public static void main(String[] args) {
        //check database
       // DBProgress check = new DBProgress();
        try  {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
        }
        new vmt();
    }
} 