
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Todd Isaacs
//Company:      
//Description:  Track Maintenance

package vmt;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;

public class ReportSelectionDialog extends JDialog {

  boolean cont = true;

  private ServiceTypeDB sDB = new ServiceTypeDB();
  private JPanel panel1 = new JPanel();
  private ButtonGroup group = new ButtonGroup();
  private XYLayout xYLayout1 = new XYLayout();
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder1;
  private JRadioButton mainAll = new JRadioButton();
  private JRadioButton mainSelect = new JRadioButton();
  private JRadioButton warranty = new JRadioButton();
  private JRadioButton gasMilage = new JRadioButton();
  private VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();


  private ServiceTypeCombo serviceTypeCombo = new ServiceTypeCombo();
  private JLabel jLabel1 = new JLabel();
  private JButton createReportButton = new JButton();
  private JButton cancelButton = new JButton();
  private JLabel jLabel2 = new JLabel();
  private JCheckBox showDetail = new JCheckBox();

  public ReportSelectionDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try  {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public ReportSelectionDialog() {
    this(null, "Report Option Dialog", false);
  }

  void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("Report Options");
    panel1.setLayout(xYLayout1);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(verticalFlowLayout1);

    mainAll.setText("Maintenance Report - All Records");
    mainSelect.setText("Maintenance Report - Selected Service Type");
    warranty.setText("Warranty Report");
    gasMilage.setText("Gas Milage report");

    //disable the un-implemented options
    warranty.setEnabled(false);
    gasMilage.setEnabled(false);

    jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel1.setText("Service Type");

    serviceTypeCombo.setEditor(null);
    createReportButton.setText("Create report");

    cancelButton.setText("Cancel");

    jLabel2.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel2.setText("Select Report Options");

    showDetail.setText("Show Detailed Information");
    xYLayout1.setHeight(368);
    xYLayout1.setWidth(461);
    getContentPane().add(panel1);
    panel1.add(jPanel1, new XYConstraints(55, 31, 301, 165));
    jPanel1.add(mainAll, null);
    jPanel1.add(mainSelect, null);
    jPanel1.add(warranty, null);
    jPanel1.add(gasMilage, null);
    panel1.add(jLabel2, new XYConstraints(62, 5, 288, 21));
    panel1.add(cancelButton, new XYConstraints(243, 305, 129, 30));
    panel1.add(createReportButton, new XYConstraints(52, 302, 138, 31));
    panel1.add(showDetail, new XYConstraints(78, 253, -1, -1));
    panel1.add(serviceTypeCombo, new XYConstraints(158, 215, 200, 28));
    panel1.add(jLabel1, new XYConstraints(63, 220, 83, 21));

    group.add(mainAll);
    group.add(mainSelect);
    group.add(warranty);
    group.add(gasMilage);

    //add listeners
    mainSelect.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        mainSelect_actionPerformed(e);
      }
    });

    warranty.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        warranty_actionPerformed(e);
      }
    });

    gasMilage.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        gasMilage_actionPerformed(e);
      }
    });

    createReportButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        createReportButton_actionPerformed(e);
      }
    });

    mainAll.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        mainAll_actionPerformed(e);
      }
    });

     cancelButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        cancelButton_actionPerformed(e);
      }
    });

    mainAll.setSelected(true);

    //update the serviceTypeCombo
    if (serviceTypeCombo.getItemCount() != 0 ){
          serviceTypeCombo.removeAllItems();
        }
        serviceTypeCombo.setComboBoxDataList(sDB.getData());

    setComboEnabled(false);
    setDetailEnabled(true);
  }

  //Accessor Methods

  public int getServiceTypeID() {
      return serviceTypeCombo.getServiceTypeID();
  }

  public boolean getContinue() {
    return cont;
  }

  public int getRadioSelection () {
      if (mainAll.isSelected()) {
        return 1;
      }
      if (mainSelect.isSelected()) {
        return 2;
      }
      if (warranty.isSelected()) {
        return 3;
      }
      if (gasMilage.isSelected()) {
        return 4;
      }

      return -1;
  }

  public boolean getShowDetail() {
      return showDetail.isSelected();
  }


  //mutator methods
  private void setComboEnabled(boolean flag) {
    jLabel1.setEnabled(flag);
    serviceTypeCombo.setEnabled(flag);
    validate();
  }

  private void setDetailEnabled(boolean flag) {
    showDetail.setEnabled(flag);
    validate();
  }

  //action performed
  void mainSelect_actionPerformed(ActionEvent e) {
     setComboEnabled(true);
     setDetailEnabled(true);
  }

  void mainAll_actionPerformed(ActionEvent e) {
    setComboEnabled(false);
    setDetailEnabled(true);
  }

  void warranty_actionPerformed(ActionEvent e) {
    setComboEnabled(false);
    setDetailEnabled(false);
  }

  void gasMilage_actionPerformed(ActionEvent e) {
    setComboEnabled(false);
    setDetailEnabled(false);
  }

  void createReportButton_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void cancelButton_actionPerformed(ActionEvent e) {
    cont = false;
    this.dispose();
  }

}
