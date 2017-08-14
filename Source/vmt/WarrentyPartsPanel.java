
//Title:        Vehicle Maintenance Tracker 1.1
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Todd E. Isaacs
//Company:      
//Description:  Track Maintence Records.

package vmt;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class WarrentyPartsPanel extends JPanel implements Observer   {
    XYLayout xYLayout1 = new XYLayout();
    JTable table;
    JScrollPane scrollPane;

    PartsTableModel tableModel = new PartsTableModel();
    JButton addWarrantyRecord = new JButton();
    JTextField installDateField = new JTextField();
    JTextField expireDateField = new JTextField();
    JTextField installMileageField = new JTextField();
    JTextField expireMileageField = new JTextField();
    JTextField supplierField = new JTextField();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JTextField descrField = new JTextField();
    JLabel jLabel6 = new JLabel();
    JButton deleteButton = new JButton();

    public WarrentyPartsPanel() {
        try  {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(xYLayout1);

        //table information
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        scrollPane = new JScrollPane(table);
        xYLayout1.setHeight(399);
        xYLayout1.setWidth(660);
        addWarrantyRecord.setText("Add");
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(5);
        column.setResizable(false);

        addWarrantyRecord.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addWarrantyRecord_actionPerformed(e);
      }
    });

    jLabel1.setText("Install Date");
    jLabel2.setText("Expire Date");
    jLabel3.setText("Install Mileage");
    jLabel4.setText("Expire Mileage");
    jLabel5.setText("Supplier");
    jLabel6.setText("Part Description");
    deleteButton.setText("Delete Selected Row");
    deleteButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        deleteButton_actionPerformed(e);
      }
    });
    this.add(scrollPane, new XYConstraints(9, 8, 642, 255));
    this.add(expireDateField, new XYConstraints(136, 320, 88, 25));
    this.add(installMileageField, new XYConstraints(239, 320, 118, 25));
    this.add(expireMileageField, new XYConstraints(371, 320, 111, 25));
    this.add(supplierField, new XYConstraints(491, 320, 132, 25));
    this.add(jLabel1, new XYConstraints(30, 299, 94, 16));
    this.add(jLabel2, new XYConstraints(135, 299, 83, 18));
    this.add(jLabel3, new XYConstraints(241, 299, 106, 15));
    this.add(jLabel4, new XYConstraints(373, 299, 102, -1));
    this.add(jLabel5, new XYConstraints(495, 299, 120, 18));
    this.add(installDateField, new XYConstraints(24, 320, 97, 25));
    this.add(addWarrantyRecord, new XYConstraints(27, 360, 89, 21));
    this.add(deleteButton, new XYConstraints(134, 360, 158, 20));
    this.add(descrField, new XYConstraints(136, 274, 178, 20));
    this.add(jLabel6, new XYConstraints(25, 274, 90, 18));

    updatePanelData();
    }


  public void updatePanelData() {
    tableModel.setData();
  }

  void addWarrantyRecord_actionPerformed(ActionEvent e) {
     //verify data
    String descr = descrField.getText();
    String startDate = installDateField.getText();
    String endDate = expireDateField.getText();
    String supplier = supplierField.getText();

    int startMile = 0;
    try {
        startMile = Integer.parseInt(installMileageField.getText());
    } catch (NumberFormatException ex) {}

    int endMile = 0;
    try {
        endMile = Integer.parseInt(expireMileageField.getText());
    } catch (NumberFormatException ex) {}

    System.out.println("Adding record");
    tableModel.AddNewRecord(descr, startDate, endDate, startMile, endMile, supplier);
  }

  void deleteButton_actionPerformed(ActionEvent e) {
    //make sure the model is not empty and something is selected
    if (tableModel.getRowCount() > 0 && table.getSelectedRow() != -1) {
      int selection = JOptionPane.showConfirmDialog
                        (
                            this,
                            "Delete Current Record?",
                            "Confirmation Message",
                            JOptionPane.WARNING_MESSAGE
                        );
      if (selection == 0) {
        try {
            tableModel.removeItem(table.getSelectedRow());
            table.validate();
        } catch (Exception ex) {
            errorMessage("Error Deleting Row");
        }
      }
    } else {
        errorMessage("No Row Selected");
    }
    return;
  }

   public void update(Observable obs, Object o){
        //received notice that there was an update to the Vinfopanel
        // only get data if there is a non-null vehicle object
        if ( VehicleInfoPanel.getSelectedVehicle() != null ) {
           updatePanelData();
        }
           // System.out.println("Warrenty Panel Observing a change!");
    }

  private void errorMessage(String s) {
        JOptionPane.showMessageDialog
                    (
                      null,
                      s,
                      "System Error",
                      JOptionPane.ERROR_MESSAGE
                    );
    }

}