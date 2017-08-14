//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:
//Copyright:    Copyright (c) 2001
//Author:       Todd E. Isaacs
//Company:
//Description:  Main Menu
//==============================================================================
package  vmt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class MainMenu extends JMenuBar {
  JMenu fileMenu = new JMenu();
  JMenu viewMenu = new JMenu();
  JMenu helpMenu = new JMenu();
  ButtonGroup lf = new ButtonGroup();
  ButtonGroup bg = new ButtonGroup();

  JMenuItem fileMenuNewVehicle = new JMenuItem();
  JMenuItem fileMenuExit = new JMenuItem();
  JMenuItem helpMenuAbout = new JMenuItem();
  JMenu viewLookAndFeel = new JMenu();
  JRadioButtonMenuItem viewLFWindows = new JRadioButtonMenuItem();
  JRadioButtonMenuItem viewLFCrossPlatForm = new JRadioButtonMenuItem();
  JCheckBoxMenuItem viewMenuMaintenance = new JCheckBoxMenuItem();
  JCheckBoxMenuItem viewMenuGasMileage = new JCheckBoxMenuItem();
  JCheckBoxMenuItem viewMenuWarrenty = new JCheckBoxMenuItem();
  JCheckBoxMenuItem viewMenuReports = new JCheckBoxMenuItem();
  JMenuItem helpMenuDesign = new JMenuItem();



    public MainMenu() {
        try {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //==========================================================================
    // initialization (Needed for JBuilder)
    //==========================================================================
    private void jbInit() throws Exception {
        fileMenu.setText("File");
        helpMenu.setText("System Information");

        fileMenu.setMnemonic('F');
        helpMenu.setMnemonic('H');

        fileMenuNewVehicle.setText("New Vehicle");
        fileMenuNewVehicle.setMnemonic('N');


        fileMenuExit.setText("Exit");
        fileMenuExit.setMnemonic('x');


        helpMenuAbout.setText("About");
        helpMenuAbout.setMnemonic('A');


        viewMenu.setText("View");
        viewLookAndFeel.setText("Look & Feel");
        viewLFWindows.setSelected(true);
        viewLFWindows.setText("Windows");
        viewLFCrossPlatForm.setText("Cross Platform");

        viewMenuMaintenance.setSelected(true);
        viewMenuMaintenance.setText("Maintenance Records");
        viewMenuGasMileage.setText("Gas Mileage");
        viewMenuWarrenty.setText("Warranty Parts");
        viewMenuReports.setText("Reports");

        helpMenuDesign.setText("System Design");
        fileMenu.add(fileMenuNewVehicle);
        fileMenu.addSeparator();
        fileMenu.add(fileMenuExit);
        helpMenu.add(helpMenuAbout);
        helpMenu.addSeparator();
        helpMenu.add(helpMenuDesign);

        lf.add(viewLFWindows);
        lf.add(viewLFCrossPlatForm);

        bg.add(viewMenuMaintenance);
        bg.add(viewMenuGasMileage);
        bg.add(viewMenuWarrenty);
        bg.add(viewMenuReports);

        this.add(fileMenu);
        this.add(viewMenu);
        this.add(helpMenu);
        
        viewMenu.add(viewLookAndFeel);
        viewMenu.addSeparator();
        viewMenu.add(viewMenuMaintenance);
        viewMenu.add(viewMenuGasMileage);
        viewMenu.add(viewMenuWarrenty);
        viewMenu.add(viewMenuReports);
        viewLookAndFeel.add(viewLFWindows);
        viewLookAndFeel.add(viewLFCrossPlatForm);
      }

    //==========================================================================
    //Override ActionListener Method
    //==========================================================================
    public void addActionListener(ActionListener l) {
        //file menu
        fileMenuNewVehicle.addActionListener(l);
        fileMenuExit.addActionListener(l);

        //view
        viewLFCrossPlatForm.addActionListener(l);
        viewLFWindows.addActionListener(l);

        viewMenuMaintenance.addActionListener(l);
        viewMenuGasMileage.addActionListener(l);
        viewMenuWarrenty.addActionListener(l);
        viewMenuReports.addActionListener(l);

        //Help Menu
        helpMenuAbout.addActionListener(l);
        helpMenuDesign.addActionListener(l);
    }

    //**************************************************************************
    // Misc methods
    //**************************************************************************

    public void setSelectedTab(String s) {

        Enumeration e = bg.getElements();
        while ( e.hasMoreElements() ) {

        JCheckBoxMenuItem item =  (JCheckBoxMenuItem)e.nextElement();
        //System.out.println(item.getText());
          if ( s.equals(item.getText()) ) {
            item.setSelected(true);
          }
        }
    }

}
