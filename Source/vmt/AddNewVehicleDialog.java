//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:      
//Description:  Add New Vehicle Dialog
//==============================================================================
package vmt;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import com.borland.jbcl.control.*;
import java.io.*;

public class AddNewVehicleDialog extends JDialog {

    VehicleDB vDB = new VehicleDB();
    JPanel panel1 = new JPanel();
    XYLayout xYLayout1 = new XYLayout();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JTextField vinField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField yearField = new JTextField();
    JTextField makeField = new JTextField();
    JTextField modelField = new JTextField();
    JTextField colorField = new JTextField();
    JTextField mileageField = new JTextField();
    JLabel jLabel8 = new JLabel();
    JButton addButton = new JButton();
    JButton cancelButton = new JButton();
    JLabel jLabel9 = new JLabel();
    JTextField imagePath = new JTextField();
    JButton jButton1 = new JButton();
    JLabel imageLabel = new JLabel();
    JLabel vehicleImageLabel = new JLabel();
    Filer filer1 = new Filer();
    Frame f;
    //==========================================================================
    //Constructor(s)
    //==========================================================================
     public AddNewVehicleDialog(Frame frame) {
        super(frame, "Add New Vehicle");
        f = frame;
        //panel = infoPanel;
        try  {
            jbInit();
            pack();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    //==========================================================================
    //Misc. Method(s)
    //==========================================================================
    /**
    * JBuilder init method.  Constructs the Add Vehicle Dialog.  Using XYLayout
    * to allow rapid building, final version will use swing layout manager(s).
    */
    void jbInit() throws Exception {
        //setup file dialog
        filer1.setFrame(f);
        filer1.setMode(FileDialog.LOAD);
        filer1.setTitle("Select Vehicle Image");

        panel1.setLayout(xYLayout1);
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel1.setText("VIN");
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel2.setText("Name");
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel3.setText("Year");
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel4.setText("Make");
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel5.setText("Model");
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel6.setText("Color");
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel7.setText("Mileage");
        jLabel8.setForeground(Color.darkGray);
        jLabel8.setText("(Required)");
        addButton.setText("Add Vehicle");
    addButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addButton_actionPerformed(e);
      }
    });
        cancelButton.setText("Cancel ");
    cancelButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        cancelButton_actionPerformed(e);
      }
    });
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel9.setForeground(Color.darkGray);
        jLabel9.setText("(Required)");
        xYLayout1.setHeight(300);
        xYLayout1.setWidth(462);
        imagePath.setText("Path to vehicle image");
        jButton1.setText("jButton1");
        imageLabel.setFont(new java.awt.Font("SansSerif", 1, 12));
        imageLabel.setText("Vehicle Image");
        getContentPane().add(panel1);
        panel1.add(jLabel2, new XYConstraints(11, 56, 45, 26));
        panel1.add(jLabel1, new XYConstraints(11, 21, 45, 26));
        panel1.add(jLabel8, new XYConstraints(306, 21, 76, 20));
        panel1.add(vinField, new XYConstraints(92, 21, 196, 26));
        panel1.add(nameField, new XYConstraints(92, 56, 159, 26));
        panel1.add(mileageField, new XYConstraints(92, 232, 130, 26));
        panel1.add(jLabel7, new XYConstraints(11, 232, 45, 26));
        panel1.add(jLabel3, new XYConstraints(11, 91, 45, 26));
        panel1.add(jLabel5, new XYConstraints(11, 165, 45, 26));
        panel1.add(jLabel6, new XYConstraints(11, 200, 45, 26));
        panel1.add(yearField, new XYConstraints(92, 91, 74, 26));
        panel1.add(modelField, new XYConstraints(92, 165, 130, 26));
        panel1.add(colorField, new XYConstraints(92, 200, 130, 26));
        panel1.add(makeField, new XYConstraints(92, 128, 130, 26));
        panel1.add(jLabel4, new XYConstraints(11, 128, 45, 26));
        panel1.add(cancelButton, new XYConstraints(233, 267, 102, 29));
        panel1.add(addButton, new XYConstraints(50, 267, 102, 29));
        panel1.add(jLabel9, new XYConstraints(306, 60, 76, 20));
        panel1.add(imagePath, new XYConstraints(241, 128, 180, 26));
        panel1.add(imageLabel, new XYConstraints(241, 103, 97, 22));
        panel1.add(jButton1, new XYConstraints(428, 129, 25, 24));
        panel1.add(vehicleImageLabel, new XYConstraints(242, 163, 209, 95));

        //add listeners
        imagePath.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(FocusEvent e) {
              imagePath_focusLost(e);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
              jButton1_actionPerformed(e);
            }
        });
    }

    /**
    * Add ActionListener(s) to this object's components.
    */
    public void addActionlistener(ActionListener l) {
        cancelButton.addActionListener(l);
        addButton.addActionListener(l);
    }

    //==========================================================================
    //Accessor Method(s)
    //==========================================================================
    /**
    * get VIN field
    *
    * @return String the vin number
    */
    public String getVinField() {
        return vinField.getText();
    }

    /**
    * get Name field
    *
    * @return String the Name(vehicle alias)
    */
    public String getNameField() {
        return nameField.getText();
    }

    /**
    * get Mileage/Odometer field
    *
    * @return String the Mileage/Odometer
    */
    public String getMileageField() {
        return mileageField.getText();
    }

    /**
    * get Make field
    *
    * @return String the Make
    */
    public String getMakeField() {
        return makeField.getText();
    }

    /**
    * get Model field
    *
    * @return String the Model
    */
    public String getModelField() {
        return modelField.getText();
    }

    /**
    * get Color field
    *
    * @return String the Color
    */
    public String getColorField() {
        return colorField.getText();
    }

    /**
    * get Year field
    *
    * @return String the Year
    */
    public String getYearField() {
        return yearField.getText();
    }

     /**
    * get Image field
    *
    * @return String the Image Path
    */
    public String getImagePath() {
        return imagePath.getText();
    }

  void jButton1_actionPerformed(ActionEvent e) {
     filer1.setVisible(true);
     this.imagePath.setText(filer1.getDirectory() + filer1.getFile());
     try {
        ImageIcon icon = new ImageIcon(this.imagePath.getText());
        this.vehicleImageLabel.setIcon(icon);
        this.vehicleImageLabel.setText("");
     } catch (Exception ex) {
        this.vehicleImageLabel.setText("Image Not Found");
     }

  }

  void imagePath_focusLost(FocusEvent e) {
   try {
        ImageIcon icon = new ImageIcon(this.imagePath.getText());
        this.vehicleImageLabel.setIcon(icon);
        this.vehicleImageLabel.setText("");
     } catch (Exception ex) {
        this.vehicleImageLabel.setText("Image Not Found");
     }

  }

  /**
  * Stream copy.
  * @param in Source Stream
  * @param out Target Stream
  */
  private void copyImage( File file) throws IOException {
    FileOutputStream out = new FileOutputStream("images\\" + file.getName());
    FileInputStream in = new FileInputStream(file.getAbsoluteFile());
       	  synchronized(in)	{
            synchronized(out)	{
            		byte [] buffer = new byte[256];
                while(true)		{
                  int bytesRead = in.read(buffer);
                  if(bytesRead == -1)
                  break;
                  out.write(buffer,0,bytesRead);
           			}
            }
          }
  }

  void cancelButton_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void addButton_actionPerformed(ActionEvent e) {
  System.out.print("adding vehicle");
     boolean valid = true;
            String vin = getVinField();
            String name = getNameField();
            String make = getMakeField();
            String model = getModelField();
            String color = getColorField();
            String year = getYearField();
            String miles = getMileageField();
            String path = getImagePath();

            //validata data
            if ( vin.length() == 0 ) valid = false;
            if ( name.length() == 0 ) valid = false;
            if ( make.length() == 0 ) make = " ";
            if ( model.length() == 0 ) model = " ";
            if ( color.length() == 0 ) color = " ";

            //convert year
            int yearInt = 0;
            try {
                yearInt = Integer.parseInt(year);
            }
            catch ( NumberFormatException ne ) {
                JOptionPane.showMessageDialog
                        (
                            this,
                            "Year must not be null and can only contain numbers",
                            "Validation Message",
                            JOptionPane.WARNING_MESSAGE
                        );
                return;
            }
            //convert miles
            long milesLong = 0;
            try {
                milesLong = Long.parseLong(miles);
            }
            catch (NumberFormatException ne2) {
                JOptionPane.showMessageDialog
                    (
                            this,
                            "Milage must not be null and can only contain numbers",
                            "Validation Message",
                            JOptionPane.WARNING_MESSAGE
                        );
                return;
            }
            if ( valid ) {
             if ( vDB.addVehicle(vin, name, yearInt, make, model, color, milesLong, path) ) {
                    //vehicle added so add data to model
                    VehicleInfoPanel.addVehicle(vDB.getData(vin));
                    VehicleInfoPanel.setLastVehicleSelected();
                   dispose();
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
                JOptionPane.showMessageDialog
                    (
                            this,
                            "Please Include Required Fields",
                            "Validation Message",
                            JOptionPane.WARNING_MESSAGE
                        );
  }
}

