
//Title:        Vehicle Maintenance Tracker 1.1
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Todd E. Isaacs
//Company:      
//Description:  Track Maintence Records.

package vmt;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import java.text.DecimalFormat.*;

public class GasMileagePanel extends JPanel implements Observer {

    boolean isEditMode = false;
    DefaultListModel gasListData = new DefaultListModel();
    JList mileageList = new JList(gasListData);
    final static DecimalFormat DF = new DecimalFormat("###,###.00");
    final static DecimalFormat DF2 = new DecimalFormat("######.00");
    GasMileageRecordDB gmrDB = new GasMileageRecordDB();

    XYLayout xYLayout1 = new XYLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JLabel jLabel1 = new JLabel();
    JButton addButton = new JButton();
    JButton editButton = new JButton();
    JButton deleteButton = new JButton();
    JPanel GasMilageDetail = new JPanel();
    TitledBorder titledBorder1;
    XYLayout xYLayout2 = new XYLayout();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JTextField startMileTextField = new JTextField();
    JTextField endMilesTextField = new JTextField();
    JTextField quantityTextField = new JTextField();
    JLabel jLabel5 = new JLabel();
    JTextField priceTextField = new JTextField();
    JLabel jLabel6 = new JLabel();
    JLabel MilesPerGallon = new JLabel();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JLabel aveMilesPerGallon = new JLabel();
    JButton addGasRecordButton = new JButton();
    JButton CancelGasRecordButton = new JButton();
    JLabel requiredStart = new JLabel();
    JLabel requiredStart1 = new JLabel();
    JLabel requiredStart2 = new JLabel();
    JLabel requiredStart3 = new JLabel();
    JLabel requiredMessageLabel = new JLabel();

    public GasMileagePanel() {
        try  {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder("");
        this.setLayout(xYLayout1);
        xYLayout1.setHeight(309);
        xYLayout1.setWidth(638);
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Mileage Log");
        addButton.setHorizontalAlignment(SwingConstants.LEFT);
        addButton.setHorizontalTextPosition(SwingConstants.LEFT);
        addButton.setText("Add");
    addButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addButton_actionPerformed(e);
      }
    });
        editButton.setHorizontalAlignment(SwingConstants.LEFT);
        editButton.setHorizontalTextPosition(SwingConstants.LEFT);
        editButton.setText("Edit");
    editButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        editButton_actionPerformed(e);
      }
    });
        deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
        deleteButton.setHorizontalTextPosition(SwingConstants.LEFT);
        deleteButton.setMnemonic('0');
        deleteButton.setText("Delete");
    deleteButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        deleteButton_actionPerformed(e);
      }
    });
        
        GasMilageDetail.setBorder(titledBorder1);
        GasMilageDetail.setLayout(xYLayout2);
        jLabel2.setText("Start Odometer");
        jLabel3.setText("End Odometer");
        jLabel4.setText("Quantity");
        jLabel5.setText("Price");
        jLabel6.setText("Miles/Gallon");
        MilesPerGallon.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel8.setText("Average");
        jLabel10.setText("Miles/Gallon");
        aveMilesPerGallon.setBorder(BorderFactory.createLineBorder(Color.black));
        addGasRecordButton.setText("Update Gas Record");
    addGasRecordButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addGasRecordButton_actionPerformed(e);
      }
    });
        CancelGasRecordButton.setText("Cancel");
    CancelGasRecordButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        CancelGasRecordButton_actionPerformed(e);
      }
    });

        requiredMessageLabel.setForeground(Color.darkGray);
        requiredMessageLabel.setText("Required Fields");
        mileageList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

      public void valueChanged(ListSelectionEvent e) {
        mileageList_valueChanged(e);
      }
    });
    this.add(jScrollPane1, new XYConstraints(21, 33, 166, 206));
        this.add(jLabel1, new XYConstraints(44, 11, 96, 15));
        this.add(addButton, new XYConstraints(9, 253, 56, 25));
        this.add(deleteButton, new XYConstraints(132, 253, 72, 25));
        this.add(editButton, new XYConstraints(69, 253, 59, 25));
        this.add(GasMilageDetail, new XYConstraints(213, 19, 263, 259));
        GasMilageDetail.add(jLabel2, new XYConstraints(9, 25, 85, 25));
        GasMilageDetail.add(jLabel3, new XYConstraints(9, 58, 85, 25));
        GasMilageDetail.add(jLabel4, new XYConstraints(9, 91, 85, 25));
        GasMilageDetail.add(startMileTextField, new XYConstraints(115, 25, 119, 19));
        GasMilageDetail.add(endMilesTextField, new XYConstraints(115, 58, 119, 19));
        GasMilageDetail.add(jLabel5, new XYConstraints(9, 124, 58, 25));
        GasMilageDetail.add(priceTextField, new XYConstraints(115, 124, 88, 19));
        GasMilageDetail.add(quantityTextField, new XYConstraints(115, 91, 50, 17));
        GasMilageDetail.add(MilesPerGallon, new XYConstraints(115, 175, 61, 18));
        GasMilageDetail.add(jLabel6, new XYConstraints(8, 175, 70, 19));
        GasMilageDetail.add(CancelGasRecordButton, new XYConstraints(164, 211, 81, 24));
        GasMilageDetail.add(requiredStart, new XYConstraints(239, 26, 13, 19));
        GasMilageDetail.add(requiredStart2, new XYConstraints(170, 91, 13, 19));
        GasMilageDetail.add(requiredStart1, new XYConstraints(239, 59, 13, 19));
        GasMilageDetail.add(addGasRecordButton, new XYConstraints(2, 211, 152, 24));
        this.add(jLabel8, new XYConstraints(492, 20, 87, 19));
        this.add(jLabel10, new XYConstraints(490, 45, 70, 19));
        this.add(aveMilesPerGallon, new XYConstraints(564, 47, 70, 18));
        this.add(requiredStart3, new XYConstraints(278, 284, 13, 19));
        this.add(requiredMessageLabel, new XYConstraints(297, 284, 142, 18));
        jScrollPane1.getViewport().add(mileageList, null);

        //add required image
        ImageIcon icon = new ImageIcon("images/required.gif");
        requiredStart.setIcon(icon);
        requiredStart1.setIcon(icon);
        requiredStart2.setIcon(icon);
        requiredStart3.setIcon(icon);
        
        setEditable(false);
    }

    //==========================================================================
    // Accessor Method(s)
    //==========================================================================

  public GasMileageRecord getCurrentRecord() {
        return (GasMileageRecord)mileageList.getSelectedValue();
    }

  public long getCurrentRecordID() {
        return ((GasMileageRecord)mileageList.getSelectedValue()).getGasMileageId();
  }

  public void calculateTotals() {
        GasMileageRecord gmr = (GasMileageRecord)this.mileageList.getSelectedValue();
        double milesPerUnit = (gmr.getEndOdometer() - gmr.getStartOdometer()) / gmr.getUnits();
        this.MilesPerGallon.setText(DF2.format(milesPerUnit));
    }

  public void calculateGrandTotal() {
    Enumeration e = gasListData.elements();
    double totalMilesPerUnit = 0;
    while (e.hasMoreElements()) {
        GasMileageRecord gmr = (GasMileageRecord)e.nextElement();
        double milesPerUnit = (gmr.getEndOdometer() - gmr.getStartOdometer()) / gmr.getUnits();
        totalMilesPerUnit += milesPerUnit;
    }
    double aveMilesPerUnit = totalMilesPerUnit/gasListData.getSize();
    aveMilesPerGallon.setText(DF2.format(aveMilesPerUnit));
  }

  public long getStartOdometer() {
      String start = startMileTextField.getText();
      long l = 0;
      try {
        l = Long.parseLong(start);
      } catch (NumberFormatException e) {
         //allow l to = 0;
      }
     return  l;
   }

   public long getEndOdometer() {
      String  end = endMilesTextField.getText();
      long l = 0;
      try {
        l = Long.parseLong(end);
      } catch (NumberFormatException e) {
        //allow l to = 0
      }
      return l;
   }

   public double getQuantity() {
      String qty = quantityTextField.getText();
      double d = 0;
      try {
        d = Double.parseDouble(qty);
      } catch (NumberFormatException e) {
        //allow d to = 0
      }
      return d;
   }

   public double getPrice() {
      String price = priceTextField.getText();
      double d = 0;
       try {
          d = Double.parseDouble(price);
       } catch (NumberFormatException e) {
          //allow d to = 0
       }
      return d;
   }

   /**
    * set list data model.
    *
    * @param Vector of maintenance records
    */
    public void setListData(Vector aVector) {
        Enumeration e = aVector.elements();
        gasListData.clear();
        while (e.hasMoreElements()) {
            gasListData.addElement(e.nextElement());
        }
    }

    public boolean removeItem(int i) {
        gasListData.removeElementAt(i);
        return true;
    }

    public int getSelectedItemIndex() {
      return  mileageList.getSelectedIndex();
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
    * Flag to check for edit mode this panel can be used for edit and adding of
    * a record.
    *
    * @return boolean true for edit mode, false for add mode.
    */
    public boolean isEditMode() {
        return isEditMode;
    }

    public void setEditable(boolean f) {
      startMileTextField.setEditable(f);
      endMilesTextField.setEditable(f);
      priceTextField.setEditable(f);
      quantityTextField.setEditable(f);
      addGasRecordButton.setEnabled(f);
      CancelGasRecordButton.setEnabled(f);

      requiredMessageLabel.setVisible(f);
      requiredStart.setVisible(f);
      requiredStart1.setVisible(f);
      requiredStart2.setVisible(f);
      requiredStart3.setVisible(f);

      revalidate();
    }

    /**
    * update all components on panel.  Called after every update
    */
    public void updatePanelData() {
        setEditable(false);
        setEditMode(false);
        if (mileageList.getSelectedIndex() == -1 && mileageList.getModel().getSize() > 0) {
            //select first element
            mileageList.setSelectedIndex(0);
        }
        if (mileageList.getModel().getSize() == 0 ){
            clearPanelData();
            return;
        }

        GasMileageRecord gr = (GasMileageRecord)mileageList.getSelectedValue();
        startMileTextField.setText("" + gr.getStartOdometer());
        endMilesTextField.setText("" + gr.getEndOdometer());
        quantityTextField.setText("" + gr.getUnits());

        priceTextField.setText(DF.format(gr.getPricePerGal()));

        calculateTotals();
        calculateGrandTotal();
    }

    public void setCurrentMileage(String s) {
        startMileTextField.setText(s);
    }

    /**
    * Clears components used when adding a record
    */
    public void clearPanelData() {
       startMileTextField.setText("");
       endMilesTextField.setText("");
       quantityTextField.setText("");
       priceTextField.setText("");
       MilesPerGallon.setText("");
       aveMilesPerGallon.setText("");
    }

    public void update(Observable obs, Object o){
        //received notice that there was an update to the Vinfopanel
        // only get data if there is a non-null vehicle object
        if ( VehicleInfoPanel.getSelectedVehicle() != null ) {
          setListData(gmrDB.getData(VehicleInfoPanel.getSelectedVehicleVIN()));
          updatePanelData();
        }
            //System.out.println("GasMileagePanel observing a change!");
    }
   //***************************************************************************
   // Add Action listener
   //***************************************************************************
  // public void addActionListener(ActionListener l) {
    //  addButton.addActionListener(l);
    //  editButton.addActionListener(l);
    //  deleteButton.addActionListener(l);
     // addGasRecordButton.addActionListener(l);
     // CancelGasRecordButton.addActionListener(l);

   //}

  // public void addListSelectionListener(ListSelectionListener l) {
   //   mileageList.addListSelectionListener(l);
   //}

  void addButton_actionPerformed(ActionEvent e) {
    clearPanelData();
    setEditMode(false);
    setEditable(true);
    setCurrentMileage("" + VehicleInfoPanel.getSelectedVehicleOdometer());
  }

  void editButton_actionPerformed(ActionEvent e) {
      if (mileageList.getModel().getSize() == 0 || mileageList.getSelectedIndex() == -1 ) {
          JOptionPane.showMessageDialog
            (
              this,
              "No Record Selected",
              "Edit Records Warning",
              JOptionPane.WARNING_MESSAGE
            );
            return;
            } else {
                setEditMode(true);
                setEditable(true);
            }


  }

  void deleteButton_actionPerformed(ActionEvent e) {
      if (mileageList.getModel().getSize() == 0 || mileageList.getSelectedIndex() == -1 ) {
                  JOptionPane.showMessageDialog
                   (
                       this,
                       "No Record Selected",
                       "Edit Records Warning",
                       JOptionPane.WARNING_MESSAGE
                   );
                  return;
              } else {
                  int selection = JOptionPane.showConfirmDialog
                        (
                            this,
                            "Delete Current Record?",
                            "Confirmation Message",
                            JOptionPane.WARNING_MESSAGE
                        );
                   if (selection == 0) {
                      long id =  ((GasMileageRecord)mileageList.getSelectedValue()).getGasMileageId();
                        if (!gasListData.isEmpty()) {
                            if (removeItem(getSelectedItemIndex())) {
                                gmrDB.removeRecord(id);
                                updatePanelData();
                                return;
                            }
                        } else {
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
              }
  }

  void addGasRecordButton_actionPerformed(ActionEvent e) {
       //add to DB then if successful add to data model
            //required fields are Start and End odometer, and qty
            long startMiles = getStartOdometer();
            long endMiles = getEndOdometer();
            double qty = getQuantity();
            double price = getPrice();
            //validate data
            if (startMiles <= 0) {
              JOptionPane.showMessageDialog
                  (
                      this,
                      "Database Update Failed",
                      "Database Error",
                      JOptionPane.ERROR_MESSAGE
                  );
              // validationErrorMessage("Start Odometer must be a positive number");
               return;
            }

            if (endMiles <= 0) {
              JOptionPane.showMessageDialog
                  (
                      this,
                      "Database Update Failed",
                      "Database Error",
                      JOptionPane.ERROR_MESSAGE
                  );
               //validationErrorMessage("End Odometer must be a positive number");
               return;
            }

            if (qty <= 0) {
              JOptionPane.showMessageDialog
                  (
                      this,
                      "Database Update Failed",
                      "Database Error",
                      JOptionPane.ERROR_MESSAGE
                  );
               //validationErrorMessage("Quantity Odometer must be a positive number");
               return;
            }
            if (isEditMode) {
                //edit record
                if (gmrDB.updateRecord(getCurrentRecordID(), VehicleInfoPanel.getSelectedVehicleVIN(), startMiles, endMiles, price, qty)) {
                  //update model
                  setListData(gmrDB.getData(VehicleInfoPanel.getSelectedVehicleVIN()));
                  updatePanelData();
                }


            } else {
                //add to DB
                if (gmrDB.addRecord(VehicleInfoPanel.getSelectedVehicleVIN(), startMiles, endMiles, price, qty)) {
                  setListData(gmrDB.getData(VehicleInfoPanel.getSelectedVehicleVIN()));
                  updatePanelData();
                  //update vehicle odometer in model and db if it is more than current value
                  if (endMiles > VehicleInfoPanel.getSelectedVehicleOdometer() ) {
                      VehicleInfoPanel.updateVehicleOdometer(endMiles);
                      VehicleInfoPanel.setSelectedVehicleOdometer(endMiles);
                  }
                } else {
                    JOptionPane.showMessageDialog
                    (
                      this,
                      "Database Update Failed",
                      "Database Error",
                      JOptionPane.ERROR_MESSAGE
                    );
                }
            }
  }

  void CancelGasRecordButton_actionPerformed(ActionEvent e) {
    setEditMode(false);
    setEditable(false);
    clearPanelData();
    updatePanelData();
  }

  void mileageList_valueChanged(ListSelectionEvent e) {

     //update panel after a list selection event
     if (!e.getValueIsAdjusting()) {
      updatePanelData();
    }
  }

}