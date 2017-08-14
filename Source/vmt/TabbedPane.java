
//Title:        Vehicle Maintenance Tracker 1.1
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Todd E. Isaacs
//Company:      
//Description:  Track Maintence Records.

package vmt;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class TabbedPane extends JTabbedPane {
    //BorderLayout borderLayout1 = new BorderLayout();

    public TabbedPane() {
        try  {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
       // this.setLayout(borderLayout1);

    }
}   
