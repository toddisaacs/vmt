//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd E. Isaacs
//Company:      
//Description:  Displays Vehicle information
//==============================================================================
package vmt;

import java.awt.*;
import java.awt.event.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;

public class VehicleInfoPanel extends JPanel implements Observer  {

    static Vmanager manager = new Vmanager();
    VehicleDB vDB = new VehicleDB();
    XYLayout xYLayout1 = new XYLayout();
    JButton addButton = new JButton();
    JButton deleteButton = new JButton();

    TitledBorder titledBorder1;
    JLabel jLabel1 = new JLabel();
    JLabel vinLabel = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel odometerLabel = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel makeLabel = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel modelLabel = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel colorLabel = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel yearLabel = new JLabel();

    JLabel imageLabel = new JLabel();
    JLabel cloudscapeLogoLabel = new JLabel();
    JTextField odometerTextField = new JTextField();
    JButton editVehicleInfoButton = new JButton();
    JButton updateVehicleInfoButton = new JButton();
    JTextField nameTextField = new JTextField();

    boolean isEditMode = false;

    //DataModel for ComboBox
    protected static JComboBox vehicleComboBox = new JComboBox(manager.dataModel);




    public VehicleInfoPanel() {
        try  {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder("");
        addButton.setText("Add");

        xYLayout1.setHeight(137);
        xYLayout1.setWidth(601);
        setLayout(xYLayout1);
        deleteButton.setText("Delete");

        setBorder(titledBorder1);
        setMinimumSize(new Dimension(613, 133));
        setPreferredSize(new Dimension(613, 133));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel1.setText("VIN");
        vinLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel2.setText("Name");
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel4.setText("Odometer");
        odometerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel3.setText("Make");
        makeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel5.setText("Model");
        modelLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel6.setText("Color");
        colorLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel7.setText("Year");
        yearLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        cloudscapeLogoLabel.setToolTipText("Built with Cloudscape");
        odometerTextField.setVisible(false);
        nameTextField.setVisible(false);
        updateVehicleInfoButton.setVisible(false);

        editVehicleInfoButton.setText("Edit Vehicle Information");
    editVehicleInfoButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        editVehicleInfoButton_actionPerformed(e);
      }
    });
    updateVehicleInfoButton.setText("Update Vehicle Information");
    updateVehicleInfoButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        updateVehicleInfoButton_actionPerformed(e);
      }
    });
    add(addButton, new XYConstraints(8, 7, 82, 19));
        add(deleteButton, new XYConstraints(98, 7, 82, 19));
        add(vehicleComboBox, new XYConstraints(8, 31, 172, 19));
        add(jLabel1, new XYConstraints(213, 4, 32, 21));
        add(vinLabel, new XYConstraints(279, 4, 303, 21));
        add(jLabel2, new XYConstraints(213, 30, 55, 21));
        add(nameLabel, new XYConstraints(279, 29, 113, 21));
        add(odometerLabel, new XYConstraints(462, 29, 120, 21));
        add(jLabel4, new XYConstraints(400, 29, 61, 21));
        add(jLabel3, new XYConstraints(213, 54, 55, 21));
        add(makeLabel, new XYConstraints(279, 54, 113, 21));
        add(jLabel5, new XYConstraints(400, 54, 61, 21));
        add(modelLabel, new XYConstraints(462, 54, 120, 21));
        add(jLabel6, new XYConstraints(213, 79, 55, 21));
        add(colorLabel, new XYConstraints(280, 79, 113, 21));
        add(jLabel7, new XYConstraints(400, 79, 61, 21));
        add(yearLabel, new XYConstraints(462, 79, 120, 21));
        add(cloudscapeLogoLabel, new XYConstraints(462, 106, 119, 23));
        add(odometerTextField, new XYConstraints(460, 27, 123, 24));
    this.add(editVehicleInfoButton, new XYConstraints(277, 106, 175, 18));
    this.add(updateVehicleInfoButton, new XYConstraints(273, 105, 182, 17));
    this.add(imageLabel, new XYConstraints(1, 50, 193, 71));
    this.add(nameTextField, new XYConstraints(279, 29, 113, -1));

        //add logo images
        ImageIcon icon = new ImageIcon("images/cloudscape.gif");
        cloudscapeLogoLabel.setIcon(icon);
        cloudscapeLogoLabel.setVisible(true);

        // Add Combo Listener
        vehicleComboBox.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            vehicleComboBox_actionPerformed(e);
          }
        });

        //Add Delete Listener
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            deleteButton_actionPerformed(e);
          }
        });

        //Add Add Vehicle Button Listener
        addButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            addButton_actionPerformed(e);
          }
        });
    }

    //Add Listener to the combobox
    public void addActionListener(ActionListener l) {
        vehicleComboBox.addActionListener(l);
        addButton.addActionListener(l);
        deleteButton.addActionListener(l);
    }

    //methods to manipulate datamodel
    public static void addVehicle(Object aVehicle) {
        manager.dataModel.addElement(aVehicle);
         manager.update();
    }

    public static void setLastVehicleSelected() {
        Vehicle v = (Vehicle)manager.dataModel.getElementAt(manager.dataModel.getSize() - 1);
        manager.dataModel.setSelectedItem(v);
    }

    public void addVehicles(Vector aVector) {
      manager.addVehicles(aVector);
    }

    public void loadData() {
        //get data
        addVehicles(vDB.getAllVehicles());
    }

    public void removeVehicle() {
        Object aVehicle = manager.dataModel.getSelectedItem();
        manager.dataModel.removeElement(aVehicle);
    }

    public void setSelectedIndex(int anIndex) {
        vehicleComboBox.setSelectedIndex(anIndex);
    }

    public void setSelected(Object aVehicle) {
        vehicleComboBox.setSelectedItem(aVehicle);
    }

    public static Vehicle getSelectedVehicle() {
      return (Vehicle)vehicleComboBox.getSelectedItem();
    }


    public static void setSelectedVehicleOdometer(long miles) {
        Vehicle v = (Vehicle)manager.dataModel.getSelectedItem();
        v.setMileage(miles);
        manager.update();
    }



    public static boolean updateVehicleOdometer(long miles) {
        VehicleDB tmpVDB = new VehicleDB();
        if (tmpVDB.updateVehicleOdometer(getSelectedVehicleVIN(), miles)) {
          setSelectedVehicleOdometer(miles);
          return true;
        } else {
           return false;
        }

    }


    public static String getSelectedVehicleVIN() {
        return ((Vehicle)manager.dataModel.getSelectedItem()).getVin();
    }

    public static long getSelectedVehicleOdometer() {
       Vehicle v = (Vehicle)manager.dataModel.getSelectedItem();
       return v.getMileage();
    }

    public void update(Observable obs, Object o){
      updatePanelData();
    }
    //method(s) to update display
    public void updatePanelData() {
           
        if (VehicleInfoPanel.getSelectedVehicle() != null) {
            Vehicle v = (Vehicle)manager.dataModel.getSelectedItem();
            vinLabel.setText(v.getVin());
            nameLabel.setText(v.getName());
            odometerLabel.setText( "" + v.getMileage() );
            makeLabel.setText(v.getMake());
            modelLabel.setText(v.getModel());
            colorLabel.setText(v.getColor());
            yearLabel.setText("" + v.getYear());

            //initialize edit fields
            odometerTextField.setText( "" + v.getMileage() );
            nameTextField.setText( "" + v.getName() );

            try {
                if (v.getImagePath() != "") {
                ImageIcon icon = new ImageIcon(v.getImagePath());
                imageLabel.setIcon(icon);
                imageLabel.setText("");
                imageLabel.setVisible(true);
            }  else {
                imageLabel.setVisible(false);
            }

            } catch (Exception e)   {
                imageLabel.setText("Image Not Found");
                imageLabel.setVisible(true);
            }
        }
        else {
        //all vehicle removed
            vinLabel.setText("");
            nameLabel.setText("");
            odometerLabel.setText("");
            makeLabel.setText("");
            modelLabel.setText("");
            colorLabel.setText("");
            yearLabel.setText("");
            imageLabel.setVisible(false);
        }
    }


  void vehicleComboBox_actionPerformed(ActionEvent e) {
        //Panel data will update because we are calling the update methos
        // on the obserable and the Vinfo panel is an observable
       manager.update();
  }

  void deleteButton_actionPerformed(ActionEvent e) {
     int selection = JOptionPane.showConfirmDialog(
                    this,
                    "Delete Current Vehicle?",
                    "Confirmation Message",
                    JOptionPane.WARNING_MESSAGE
                );
            if ( selection == 0 ) {
                //remove from DB and model if available
                if (vehicleComboBox.getModel().getSize() != 0) {
                    if (vDB.removeVehicle(this.getSelectedVehicleVIN())){
                        this.removeVehicle();
                        JOptionPane.showMessageDialog
                        (
                            this,
                            "Database has been updated",
                            "Database Message",
                            JOptionPane.WARNING_MESSAGE
                        );
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
                    return;
                }
            }
  }

  void addButton_actionPerformed(ActionEvent e) {
      AddNewVehicleDialog addNewVehicleDlg = new AddNewVehicleDialog(new JFrame());
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension dlgSize = addNewVehicleDlg.getPreferredSize();
      Dimension frmSize = tk.getScreenSize();
      Point loc = getLocation();
      addNewVehicleDlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      addNewVehicleDlg.setModal(true);
      addNewVehicleDlg.show();
  }

  void editVehicleInfoButton_actionPerformed(ActionEvent e) {
    //set text field visible for edit
    setEditable(true);


  }

  void updateVehicleInfoButton_actionPerformed(ActionEvent e) {
    setEditable(false);
    //update model
    Vehicle v = (Vehicle)manager.dataModel.getSelectedItem();
    v.setMileage(Long.parseLong(odometerTextField.getText()));
    v.setName(nameTextField.getText());

    vDB.updateVehicle(v.getVin(), Long.parseLong(odometerTextField.getText()), nameTextField.getText());

   updatePanelData();
  }

  void setEditable(boolean f) {

    odometerTextField.setVisible(f);
    nameTextField.setVisible(f);

    isEditMode = f;
    if ( isEditMode ) {
      editVehicleInfoButton.setVisible(false);
      updateVehicleInfoButton.setVisible(true);

      odometerLabel.setVisible(false);
      nameLabel.setVisible(false);

    }  else {
      editVehicleInfoButton.setVisible(true);
      updateVehicleInfoButton.setVisible(false);

      odometerLabel.setVisible(true);
      nameLabel.setVisible(true);
    }

     revalidate();
     //repaint();
  }

}
