//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd E. Isaacs
//Company:      
//Description:  Records Panel.  Used to view, update and delete maintenance records
//==============================================================================
package vmt;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
//import com.klg.jclass.field.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.beans.*;
import java.text.DecimalFormat.*;

public class RecordsPanel extends JPanel implements Observer {

    ServiceTypeDB stDB = new ServiceTypeDB();
    ServiceRecordsDB srDB = new ServiceRecordsDB();
    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    final static DecimalFormat DF = new DecimalFormat("###,###.00");
    Date today = new Date();
    private boolean isEditMode = false;
    ServiceTypeCombo serviceTypeCombo = new ServiceTypeCombo();

    XYLayout xYLayoutMain = new XYLayout();
    XYLayout xYLayoutDetail = new XYLayout();
    JScrollPane jScrollPaneList = new JScrollPane();
    JPanel recordDetailPanel = new JPanel();
    TitledBorder titledBorder1;
    TitledBorder titledBorder2;
    DefaultListModel listData = new DefaultListModel();
    JList recordList = new JList(listData);
    JLabel jLabel1 = new JLabel();
    JTextField odometerTextField = new JTextField();
    JLabel jLabel2 = new JLabel();
    JTextField dateTextField = new JTextField();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JScrollPane jScrollPaneNotes = new JScrollPane();
    JTextArea notesTextArea = new JTextArea();
    JButton updateRecordsButton = new JButton();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JButton editRecordButton = new JButton();
    JButton addRecordButton = new JButton();
    JButton deleteRecordButton = new JButton();
    JButton cancelUpdateRecordButton = new JButton();
    JLabel jLabel7 = new JLabel();
    JTextField priceTextField = new JTextField();
    JButton addServiceTypeButton = new JButton();
    JLabel requiredStart3 = new JLabel();
    JLabel requiredStart4 = new JLabel();
    JLabel requiredStart5 = new JLabel();
    JLabel requiredMessageLabel = new JLabel();
    JLabel requiredStart6 = new JLabel();
    JButton editServiceType = new JButton();
    JTextField serviceTypeField = new JTextField();
    JTextField tempField = new JTextField();

    //==========================================================================
    //Constructor(s)
    //==========================================================================
    public RecordsPanel() {
        try  {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    //==========================================================================
    //JBuilder initilization method (Required for IDE)
    //==========================================================================
    /**
    * JBuilder init method.  Constructs the Add Vehicle Dialog.  Using XYLayout
    * to allow rapid building, final version will use swing layout manager(s).
    */
    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder("Record Detail");
        titledBorder2 = new TitledBorder("Vehicle Records");
        this.setLayout(xYLayoutMain);
        this.setFont(new java.awt.Font("Dialog", 1, 12));
        this.setBorder(titledBorder2);
        xYLayoutMain.setHeight(351);
        xYLayoutMain.setWidth(639);
        recordDetailPanel.setBorder(titledBorder1);
        recordDetailPanel.setLayout(xYLayoutDetail);
        recordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recordList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

      public void valueChanged(ListSelectionEvent e) {
        recordList_valueChanged(e);
      }
    });
      

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel1.setText("Odometer");
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel2.setText("Date");
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel3.setText("Service Type");
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel4.setText("Notes");
        updateRecordsButton.setText("Update Records");
        updateRecordsButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        updateRecordsButton_actionPerformed(e);
      }
    });
        jLabel5.setText("Date");
        jLabel6.setText("Odometer");
        editRecordButton.setText("Edit");
    editRecordButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        editRecordButton_actionPerformed(e);
      }
    });
        addRecordButton.setText("Add");
        addRecordButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addRecordButton_actionPerformed(e);
      }
    });
        deleteRecordButton.setText("Delete");
    deleteRecordButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        deleteRecordButton_actionPerformed(e);
      }
    });
        cancelUpdateRecordButton.setText("Cancel");
    cancelUpdateRecordButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        cancelUpdateRecordButton_actionPerformed(e);
      }
    });
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel7.setText("Price  $");
        addServiceTypeButton.setText("Add New Service Type");
        addServiceTypeButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addServiceTypeButton_actionPerformed(e);
      }
    });
        requiredStart3.setVisible(true);

        requiredMessageLabel.setForeground(Color.darkGray);
        requiredMessageLabel.setText("Required");
        editServiceType.setText("Edit Service Type");
    editServiceType.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        editServiceType_actionPerformed(e);
      }
    });

    this.add(jScrollPaneList, new XYConstraints(11, 20, 195, 263));
        jScrollPaneList.getViewport().add(recordList, null);
        this.add(recordDetailPanel, new XYConstraints(225, 0, 398, 314));
        recordDetailPanel.add(jLabel4, new XYConstraints(12, 58, 69, 17));
        recordDetailPanel.add(jScrollPaneNotes, new XYConstraints(12, 77, 370, 159));
        recordDetailPanel.add(jLabel7, new XYConstraints(242, 30, 55, -1));
        recordDetailPanel.add(jLabel2, new XYConstraints(241, 6, 37, -1));
        recordDetailPanel.add(jLabel3, new XYConstraints(4, 30, 81, -1));
        recordDetailPanel.add(jLabel1, new XYConstraints(3, 5, 64, -1));
        recordDetailPanel.add(odometerTextField, new XYConstraints(84, 6, 99, 17));
        recordDetailPanel.add(serviceTypeCombo, new XYConstraints(84, 30, 124, 17));
        recordDetailPanel.add(updateRecordsButton, new XYConstraints(44, 242, 132, 24));
        recordDetailPanel.add(cancelUpdateRecordButton, new XYConstraints(210, 242, 124, 22));
        recordDetailPanel.add(requiredStart5, new XYConstraints(6, 266, 13, 19));
        recordDetailPanel.add(requiredMessageLabel, new XYConstraints(26, 267, 114, 18));
        recordDetailPanel.add(requiredStart4, new XYConstraints(213, 29, 13, 19));
        recordDetailPanel.add(requiredStart3, new XYConstraints(190, 5, 13, 19));
        recordDetailPanel.add(priceTextField, new XYConstraints(290, 29, 74, 18));
        recordDetailPanel.add(dateTextField, new XYConstraints(289, 6, 81, 17));
        recordDetailPanel.add(requiredStart6, new XYConstraints(373, 5, 13, 19));
    recordDetailPanel.add(addServiceTypeButton, new XYConstraints(58, 51, -1, 24));
    recordDetailPanel.add(editServiceType, new XYConstraints(227, 51, 155, 24));
    recordDetailPanel.add(serviceTypeField, new XYConstraints(80, 30, 124, 17));
    recordDetailPanel.add(tempField, new XYConstraints(216, 0, 14, 10));
        this.add(addRecordButton, new XYConstraints(0, 292, 70, 21));
        this.add(editRecordButton, new XYConstraints(74, 292, 70, 21));
        this.add(deleteRecordButton, new XYConstraints(147, 292, 70, 21));
        this.add(jLabel6, new XYConstraints(102, 0, 90, -1));
        this.add(jLabel5, new XYConstraints(15, 1, 79, 13));
        jScrollPaneNotes.getViewport().add(notesTextArea, null);
        //add required image
        ImageIcon icon = new ImageIcon("images/required.gif");
        requiredStart3.setIcon(icon);
        requiredStart3.setVisible(true);
        requiredStart4.setIcon(icon);
        requiredStart4.setVisible(true);
        requiredStart5.setIcon(icon);
        requiredStart5.setVisible(true);
        requiredStart6.setIcon(icon);
        requiredStart6.setVisible(true);
        serviceTypeField.setVisible(false);
        tempField.setVisible(false);
        setEditable(false);

  }

    public void update(Observable obs, Object o){
        //received notice that there was an update to the Vinfopanel
        // only get data if there is a non-null vehicle object
        if ( VehicleInfoPanel.getSelectedVehicle() != null ) {
            setListData(srDB.getData(VehicleInfoPanel.getSelectedVehicle().getVin()));
            updatePaneldata();
        }
            //System.out.println("RecordsPanel observing a change!");
    }

    //==========================================================================
    // Accessor Method(s)
    //==========================================================================
    /**
    * get Mileage/Odometer field
    *
    * @return String the Mileage/Odometer
    */
    public String getOdometer() {
        return odometerTextField.getText();
    }

    /**
    * get Date field
    *
    * @return Date the Date
    */
    public String getDate() {
        return dateTextField.getText();
    }

    /**
    * get Service Type Combo value
    *
    * @return int the ServiceTypeID
    */
    public int getServiceType() {
        return serviceTypeCombo.getServiceTypeID();
    }

    /**
    * get Notes field
    *
    * @return String the Notes
    */
    public String getNotes() {
        return notesTextArea.getText();
    }

    /**
    * get Price field
    *
    * @return String the price
    */
    public String getPrice() {
        return priceTextField.getText();
    }
    
    /**
    * get current record
    *
    * @return ServiceRecord if record collectio populated otherwise null;
    */
    public ServiceRecord getCurrentRecord() {
        return (ServiceRecord)recordList.getSelectedValue();
    }

    /**
    * get ServiceRecordId
    *
    * @return long if record list is populated otherwise -1
    */

    public long getServiceRecordId() {
        ServiceRecord sr;
        sr = (ServiceRecord)recordList.getSelectedValue();
        return sr.getServiceRecordId();
    }

    public Vector getServiceRecords() {
        Vector v = new Vector();
        Enumeration e = listData.elements();
        while (e.hasMoreElements()) {
          v.add(e.nextElement());
        }
        return v;
    }
    
    //==========================================================================
    //Mutator Method(s)
    //==========================================================================
    /**
    * Used to set fields editable.  JDK bug setVisible(true) does not work when
    * setVisible(false) is initially set for the componenet.  I used revalidate
    * as a workaround.
    *
    * @return boolean
    */
    public void setEditable(boolean aFlag) {
        updateRecordsButton.setEnabled(aFlag);
        cancelUpdateRecordButton.setEnabled(aFlag);
        odometerTextField.setEnabled(aFlag);;
        dateTextField.setEnabled(aFlag);
        notesTextArea.setEnabled(aFlag);
        serviceTypeCombo.setEnabled(aFlag);
        priceTextField.setEnabled(aFlag);
        addServiceTypeButton.setEnabled(aFlag);
       
        requiredMessageLabel.setVisible(aFlag);
        requiredStart3.setVisible(aFlag);
        requiredStart4.setVisible(aFlag);
        requiredStart5.setVisible(aFlag);
        requiredStart6.setVisible(aFlag);

        revalidate();
        repaint();
    }

    /**
    * Flag to indicate mode
    *
    * @param boolean true for edit mode false for add mode
    */
    public void setEditMode(boolean aFlag) {
       isEditMode = aFlag;
    }

    /**
    * set list data model.
    *
    * @param Vector of maintenance records
    */
    public void setListData(Vector aVector) {
        Enumeration e = aVector.elements();
        listData.clear();
        while (e.hasMoreElements()) {
            listData.addElement(e.nextElement());
        }
    }

    /**
    * set combobox data model.  This is a complete overwrite of data
    *
    * @param Vector of ServiceTypes
    */
    public void setComboBoxDataList(Vector aVector) {
        if (serviceTypeCombo.getItemCount() != 0 ){
          serviceTypeCombo.removeAllItems();
        }
        serviceTypeCombo.setComboBoxDataList(aVector);
    }


    //==========================================================================
    //Misc Method(s)
    //==========================================================================
    /**
    * Remove a record from the list data model
    *
    * @param ServiceRecord service record to be removed
    */
    public void removeServiceRecord(ServiceRecord sr) {
        listData.removeElement(sr);
    }



    /**
    * Flag to check for edit mode this panel can be used for edit and adding of
    * a record.
    *
    * @return boolean true for edit mode, false for add mode.
    */
    public boolean isEditMode() {
        return isEditMode;
    }

    /**
    * update all components on panel.  Called after every update
    */
    public void updatePaneldata() {
        setEditable(false);
        if (recordList.getSelectedIndex() == -1 && recordList.getModel().getSize() > 0) {
            //select first element
            recordList.setSelectedIndex(0);
        }
        if (recordList.getModel().getSize() == 0 ){
            clearPanelData();
            return;
        }

        ServiceRecord sr = (ServiceRecord)recordList.getSelectedValue();
        odometerTextField.setText("" + sr.getMileage());
        dateTextField.setText("" + sr.getServiceDateString());
        notesTextArea.setText(sr.getNotes());
        serviceTypeCombo.setServiceType(sr.getServiceTypeId());
        //make sure price has two decimal places
        
        priceTextField.setText("" + DF.format(sr.getPrice()));
    }

    /**
    * Clears components used when adding a record
    */
    public void clearPanelData() {
        odometerTextField.setText("");
        dateTextField.setText("");
        notesTextArea.setText("");
        priceTextField.setText("");
    }

  void addRecordButton_actionPerformed(ActionEvent e) {
    //make panel editable
    setEditMode(false);
    setEditable(true);
    dateTextField.setText(DATE_FORMAT.format(today));
    odometerTextField.setText("" + VehicleInfoPanel.getSelectedVehicleOdometer());
  }

  void updateRecordsButton_actionPerformed(ActionEvent e) {
        boolean valid = true;
        String vin = VehicleInfoPanel.getSelectedVehicleVIN();
        String date = getDate();
        String miles = getOdometer();
        int serviceTypeId = getServiceType();
        String notes = getNotes();
        boolean isMaintenance = true;
        String priceText = getPrice();
        double price = 0.00;

        if (notes.length() == 0) notes = " ";
        if (vin.length() == 0 ) valid = false;
        if (miles.length() == 0) valid = false;

        //make sure data is in correct format
        try {
                price = Double.parseDouble(priceText);
        }
        catch (Exception pe){
              JOptionPane.showMessageDialog
                        (
                            this,
                            "Price Error",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                return;
        }

        try {
			          Date d = DATE_FORMAT.parse(date);
                date = DATE_FORMAT.format(d);
            }
		    catch (ParseException pe) {
               JOptionPane.showMessageDialog
                        (
                            this,
                            "Date Not In Correct Format " + DATE_FORMAT.toPattern(),
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                return;
        }
        //convert miles to long
        long milesLong = 0;
        try {
                milesLong = Long.parseLong(miles);
        }
        catch (NumberFormatException ne3) {
                JOptionPane.showMessageDialog
                        (
                            this,
                            "Odometer is Required and Must Contain Only Numbers",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                  return;
        }

            if (valid) {
                if (isEditMode()) {
                    long serviceRecordId = getServiceRecordId();
                    if (srDB.updateServiceRecord(vin, serviceRecordId , serviceTypeId, date, isMaintenance, milesLong, notes, price)) {
                        setListData(srDB.getData(vin));
                        updatePaneldata();
                    }
                    else {
                        JOptionPane.showMessageDialog
                        (
                            this,
                            "Database Update Failed",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }
                else {
                    //update Vehicle Odometer
                    if (milesLong  < VehicleInfoPanel.getSelectedVehicleOdometer() ) {
                        //submit warning
                        int selection = JOptionPane.showConfirmDialog
                        (
                            this,
                            "Odomter Will Be Set To a Smaller Value : \n"
                            + VehicleInfoPanel.getSelectedVehicleOdometer()
                            + " To " + milesLong
                            + "\nContinue With Update ?",
                            "Confirmation Message",
                            JOptionPane.WARNING_MESSAGE
                        );

                        if (selection == 0) {
                        //update Vehicle
                        //Fall through to the update below
                        } else {
                            //Cancel update
                            return;
                        }
                    }

                    //update Vehicle odometer
                    if (VehicleInfoPanel.updateVehicleOdometer(milesLong)) {
                      VehicleInfoPanel.getSelectedVehicleOdometer();
                    }
                    else {
                      JOptionPane.showMessageDialog
                        (
                            this,
                            "Database Update Failed, Updating Odometer",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    if (srDB.addServiceRecord(vin, serviceTypeId,date, isMaintenance, milesLong, notes, price)) {
                        setListData(srDB.getData(vin));
                        updatePaneldata();
                    }
                    else {
                       JOptionPane.showMessageDialog
                        (
                            this,
                            "Database Insert Failed",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog
                        (
                            this,
                            "Odometer Is Required",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE
                        );
            }
            return;

  }

  void addServiceTypeButton_actionPerformed(ActionEvent e) {
            AddServiceTypeDialog addNewServiceType = new AddServiceTypeDialog(this);
            //addNewServiceType.addActionlistener(this);
            Dimension dlgSize = addNewServiceType.getPreferredSize();
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension frmSize = tk.getScreenSize();
            Point loc = getLocation();
            addNewServiceType.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
            addNewServiceType.setModal(true);
            addNewServiceType.show();
  }

  void cancelUpdateRecordButton_actionPerformed(ActionEvent e) {
      clearPanelData();
      setEditMode(false);
      setEditable(false);
      editServiceType.setText("Edit Service Type");
      serviceTypeCombo.setVisible(true);
      serviceTypeField.setVisible(false);
      updatePaneldata();
  }

  void recordList_valueChanged(ListSelectionEvent e) {

    //update panel after a list selection event
    if (!e.getValueIsAdjusting()) {
      updatePaneldata();
    }

  }

  void editRecordButton_actionPerformed(ActionEvent e) {
    if (recordList.getModel().getSize() == 0 || recordList.getSelectedIndex() == -1 ) {
                JOptionPane.showMessageDialog
                   (
                       this,
                       "No Record To Edit",
                       "Edit Records Warning",
                       JOptionPane.WARNING_MESSAGE
                   );
                return;
            }
            else {
                setEditable(true);
                setEditMode(true);
                return;
            }
  }

  void deleteRecordButton_actionPerformed(ActionEvent e) {

          if (recordList.getModel().getSize() == 0 || recordList.getSelectedIndex() == -1 ) {
                 JOptionPane.showMessageDialog
                   (
                       this,
                       "No Record To Edit",
                       "Edit Records Warning",
                       JOptionPane.WARNING_MESSAGE
                   );
                return;
            }
            else {
                    int selection = JOptionPane.showConfirmDialog
                        (
                            this,
                            "Delete Current Record?",
                            "Confirmation Message",
                            JOptionPane.WARNING_MESSAGE
                        );
                    if (selection == 0) {
                        ServiceRecord sr = getCurrentRecord();
                        if (srDB.removeServiceRecord(sr.getServiceRecordId())) {
                            //update datamodel
                            removeServiceRecord(sr);
                            updatePaneldata();
                        }
                        else {
                           JOptionPane.showMessageDialog
                              (
                                this,
                                "Update Failed",
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE
                              );
                        }
                    return;
                }
            }
          }

    void editServiceType_actionPerformed(ActionEvent e) {
      if (serviceTypeField.isVisible()) {
        //update information
        //int id = serviceTypeCombo.getServiceTypeID();
        //System.out.println(tempField.getText());
        int id = Integer.parseInt(tempField.getText());
        //System.out.println(id2);
        serviceTypeCombo.updateServiceType(serviceTypeField.getText(), id);
        setComboBoxDataList(stDB.getData());

        serviceTypeField.setVisible(false);
        serviceTypeCombo.setVisible(true);
        editServiceType.setText("Edit Service Type");
        serviceTypeCombo.validate();
      } else {
        //set combo box up for edit
        ServiceType sType = (ServiceType)serviceTypeCombo.getSelectedItem();
        serviceTypeField.setText(sType.getDescr());
        serviceTypeCombo.setVisible(false);
        serviceTypeField.setVisible(true);
        serviceTypeField.revalidate();
        editServiceType.setText("Update Service Type");
        tempField.setText("" + sType.getServiceTypeId());
      }



    }

  }





