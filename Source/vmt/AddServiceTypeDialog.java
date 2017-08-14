
//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:      Your Company
//Description:  Your description

package vmt;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;

public class AddServiceTypeDialog extends JDialog {

    RecordsPanel recordsPanel;
  ServiceTypeDB stDB = new ServiceTypeDB();
  JPanel panel1 = new JPanel();
  XYLayout xYLayout1 = new XYLayout();
  JLabel serviceTypeLabel = new JLabel();
  JTextField serviceTypeTextField = new JTextField();
  JButton addButton = new JButton();
  JButton cancelButton = new JButton();
  TitledBorder titledBorder1;
  public AddServiceTypeDialog(Frame frame, RecordsPanel p) {
    super(frame);
    recordsPanel = p;
    try  {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public AddServiceTypeDialog(RecordsPanel p) {
    this(new Frame(), p);
    recordsPanel = p;
  }

  void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    xYLayout1.setHeight(116);
    xYLayout1.setWidth(400);
    serviceTypeLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    serviceTypeLabel.setText("Service Type");
    addButton.setText("Add Service Type");
    addButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addButton_actionPerformed(e);
      }
    });
    cancelButton.setText("Cancel   ");
    cancelButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        cancelButton_actionPerformed(e);
      }
    });
    panel1.setBorder(BorderFactory.createLineBorder(Color.black));
    getContentPane().add(panel1);
    panel1.add(serviceTypeTextField, new XYConstraints(125, 31, 241, 24));
    panel1.add(cancelButton, new XYConstraints(289, 70, -1, -1));
    panel1.add(addButton, new XYConstraints(123, 70, 156, -1));
    panel1.add(serviceTypeLabel, new XYConstraints(22, 30, 89, 21));
  }

    //==========================================================================
    //Accessor Method(s)
    //==========================================================================
    /**
    * get Service Type  field
    *
    * @return String the ServiceType name
    */
    public String getServiceTypeName() {
        return serviceTypeTextField.getText();
    }

  void cancelButton_actionPerformed(ActionEvent e) {
    dispose();
  }

  void addButton_actionPerformed(ActionEvent e) {
          String name = getServiceTypeName();
          if ( name.length() == 0 ) {
                 JOptionPane.showMessageDialog
                        (
                            this,
                            "Failed to update database",
                            "Database Error",
                            JOptionPane.WARNING_MESSAGE
                        );
            return;
          }
          if ( stDB.addServiceType(name) ){
                //update the combobox
              recordsPanel.setComboBoxDataList(stDB.getData());
              dispose();
              return;
          }
          else {
                JOptionPane.showMessageDialog
                        (
                            this,
                            "Failed to update database",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                        );
          }
  }

}

 