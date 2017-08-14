//==============================================================================
//Title:      Vehicle Maintenance Tracker 1.1
//Version:    
//Copyright:  Copyright (c) 2001
//Author:     Todd E. Isaacs
//Company:    
//Description: Track Vehicle Maintenance Records.
//==============================================================================
package vmt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.util.*;
import java.io.*;
//import javax.swing.UIManager;


public class MainFrame extends JFrame implements ActionListener, ListSelectionListener, ChangeListener {
    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    JLabel statusBar = new JLabel();
    BorderLayout borderLayout1 = new BorderLayout();


    //Check DB and rebuild if needed
           //create new class to test DB



     //Database objects
    VehicleDB vDB = new VehicleDB();  //move to the vInfo component
    //ServiceRecordsDB srDB = new ServiceRecordsDB();
    //ServiceTypeDB stDB = new ServiceTypeDB();
    //GasMileageRecordDB gmrDB = new GasMileageRecordDB();

    //Components
    MainMenu mainMenu;
    VehicleInfoPanel vInfoPanel;
    RecordsPanel recordsPanel;
    TabbedPane tabbedPane;
    AddNewVehicleDialog addNewVehicleDlg;
    GasMileagePanel gasMileagePanel;
    WarrentyPartsPanel warrentyPartsPanel;
    ReportsPanel reportsPanel;
    AddServiceTypeDialog addNewServiceType;


    //==========================================================================
    //Constructor(s)
    //==========================================================================
  public MainFrame() {

        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try  {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //==========================================================================
    // initialization (Needed for JBuilder)
    //==========================================================================
    /**
    * JBuilder init method.  Constructs the main frame for the application.
    * This frame is setup as an ActionListener and ListSelectionListener.  All
    * Components added to this frame will include and addAcionListener
    * (ActionListener l) or addListSelectionListener(ListSelectionListener l)
    * method.  This will allow the MainFrame to control the whole application
    * and it will allow components to be swapped in and out very easily.
    *
    */
    private void jbInit() throws Exception  {
        SplashScreen splash = new SplashScreen("images\\VMT_splash_blue.jpg");
        splash.openWindow();
        //Thread t = Thread.currentThread();

        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Vehicle Maintenance Tracker");
        statusBar.setText("Welcome");
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);

        //create Components
        mainMenu = new MainMenu();
        vInfoPanel = new VehicleInfoPanel();
        recordsPanel = new RecordsPanel();
        gasMileagePanel = new GasMileagePanel();
        warrentyPartsPanel = new WarrentyPartsPanel();
        reportsPanel = new ReportsPanel();
        tabbedPane = new TabbedPane();

        //add observers
        vInfoPanel.manager.addObserver(recordsPanel);
        vInfoPanel.manager.addObserver(vInfoPanel);
        vInfoPanel.manager.addObserver(gasMileagePanel);
        vInfoPanel.manager.addObserver(warrentyPartsPanel);
        vInfoPanel.manager.addObserver(reportsPanel);
        //Add Components
       setJMenuBar( mainMenu );
        //splash.updateStatus("Menu Added");
        this.getContentPane().add(vInfoPanel, BorderLayout.NORTH);
        //splash.updateStatus("Information Panel Added");
       this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        //splash.updateStatus("Tabbed Panel Added");
        tabbedPane.addTab("Maintenance Records", recordsPanel);
        //splash.updateStatus("Maintenance Panel Added");
       tabbedPane.addTab("Gas Mileage", gasMileagePanel);
        //splash.updateStatus("Gas Mileage Panel Added");
        tabbedPane.addTab("Warranty Parts", warrentyPartsPanel);
        //splash.updateStatus("Warranty Panel Added");
        tabbedPane.addTab("Reports", reportsPanel);
       // splash.updateStatus("Reports Panel Added");
        //this.getContentPane().add(recordsPanel, BorderLayout.CENTER);

        //splash.updateStatus("Setting up listeners");
        //add listener(s)
        mainMenu.addActionListener(this);
        tabbedPane.addChangeListener(this);
        //***********************************
        //vInfoPanel.addActionListener(this);
        //recordsPanel.addListSelectionListener(this);
        //recordsPanel.addActionListener(this);
        ///****************************


        //gasMileagePanel.addActionListener(this);
        //gasMileagePanel.addListSelectionListener(this);

        //splash.updateStatus("Initilizing Objects");
        //add data to panels
        //vInfoPanel.addVehicles(vDB.getAllVehicles());
        //recordsPanel.setComboBoxDataList(stDB.getData());
      // reportsPanel.setData(vInfoPanel.getSelectedVehicle(), recordsPanel.getServiceRecords() );

        //get prefered size
        this.setSize(new Dimension(662, 635));

      /**
        try {
            t.sleep(1000);
        }
        catch (Exception  e) {

        }
        splash.closeWindow();
      */
      //initilize panels with data
      vInfoPanel.loadData();
      splash.closeWindow();
    }

    //==========================================================================
    // Process Window Events
    //==========================================================================

    //Overridden so we can exit on System Close
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
             System.exit(0);
        }
    }

    //==========================================================================
    // override change listener
    //==========================================================================

    /**
    *   This method is used to keep the menu button group with the correct tab
    *   selection.  Uses the menu's public method setSelectedTab() to set the
    *   select tab for the menu button group.
    */
    public void stateChanged(ChangeEvent c) {
        mainMenu.setSelectedTab(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
    }

    //==========================================================================
    //Override ListSelectionListener
    //==========================================================================
    public void valueChanged(ListSelectionEvent se) {
        //RecordsPanel**********************************************************
         if (se.getSource() == recordsPanel.recordList) {
            recordsPanel.updatePaneldata();
            return;
        }

      /**  if (se.getSource() == gasMileagePanel.mileageList) {
          gasMileagePanel.updatePanelData();
          return;
        }
        */


    }

    //==========================================================================
    //Override ActionListener Method
    //==========================================================================
    public void actionPerformed(ActionEvent e) {

        //AddNewVehicle Dialog**************************************************
        //Add Vehicle button
       //System.out.println(e.getSource());


        //VehicleInfoPanel *****************************************************
        //Delete Button
        if ( e.getSource() == vInfoPanel.deleteButton ) {

        }

        //Add Button
        if ( e.getSource() == vInfoPanel.addButton ) {
            this.showAddNewVehicleDialog();
            return;
        }

        //**********************************************************************
        //MainMenu**************************************************************
        //**********************************************************************
        
        //MENU - File / New Vehicle
        if (e.getSource() == mainMenu.fileMenuNewVehicle) {
            showAddNewVehicleDialog();
            return;
        }

        //MENU - File /  Exit
        if (e.getSource() == mainMenu.fileMenuExit) {
            System.exit(0);
        }

        //MENU View/Look&Feel - Cross PLatform
        if (e.getSource() == mainMenu.viewLFCrossPlatForm) {
            try  {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(this);
            }
            catch(Exception lfe) {}
            return;
        }

        //MENU View/Look&Feel - Metal
        if (e.getSource() == mainMenu.viewLFWindows) {
            try  {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(this);
            }
            catch(Exception lfe) {System.out.println("L&F excaptin");}
            return;
        }

        //MENU View/Maintenance
        if (e.getSource() == mainMenu.viewMenuMaintenance) {
            tabbedPane.setSelectedComponent(recordsPanel);
            return;
        }

        //MENU View/Maintenance
        if (e.getSource() == mainMenu.viewMenuGasMileage) {
            tabbedPane.setSelectedComponent(gasMileagePanel);
            return;
        }

        //MENU View/Maintenance
        if (e.getSource() == mainMenu.viewMenuWarrenty) {
            tabbedPane.setSelectedComponent(warrentyPartsPanel);
            return;
        }

        //MENU View/Maintenance
        if (e.getSource() == mainMenu.viewMenuReports) {
            tabbedPane.setSelectedComponent(reportsPanel);
            return;
        }

        //MENU - Help / About
        if (e.getSource() == mainMenu.helpMenuAbout) {
           MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
            Dimension dlgSize = dlg.getPreferredSize();
            Dimension frmSize = getSize();
            Point loc = getLocation();
            dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
            dlg.setModal(true);
            dlg.show();

            return;
        }

        //MENU - Help / Design
        if (e.getSource() == mainMenu.helpMenuDesign) {
          DesignInfo dlg = new DesignInfo(this);
           Dimension dlgSize = dlg.getPreferredSize();
           Dimension frmSize = getSize();
           Point loc = getLocation();
           dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
           dlg.setModal(true);
           dlg.show();

            return;
        }

    }

    //==========================================================================
    //Misc Methods
    //==========================================================================
    private void validationErrorMessage(String s) {
        JOptionPane.showMessageDialog
        (
            this,
            s,
            "Validation Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private void databaseErrorMessage(String s) {
        JOptionPane.showMessageDialog
                    (
                      this,
                      s,
                      "Database Error",
                      JOptionPane.ERROR_MESSAGE
                    );
    }

    private void databaseUpdatedMessage (String s) {
        JOptionPane.showMessageDialog
                   (
                       this,
                       s,
                       "Database Message",
                       JOptionPane.WARNING_MESSAGE
                   );
    }


    private void showAddNewVehicleDialog() {
            addNewVehicleDlg = new AddNewVehicleDialog(this);
            addNewVehicleDlg.addActionlistener(this);
            Dimension dlgSize = addNewVehicleDlg.getPreferredSize();
            Dimension frmSize = getSize();
            Point loc = getLocation();
            addNewVehicleDlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
            addNewVehicleDlg.setModal(true);
            addNewVehicleDlg.show();
        }
}