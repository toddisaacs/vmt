package vmt;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class DBProgress extends JDialog {

  CreateDB cDB = new CreateDB();
  Vector tables;
  XYLayout xYLayout1 = new XYLayout();
  JLabel titleLabel = new JLabel();
  JLabel statusLabel = new JLabel();
  JLabel questionLabel = new JLabel();
  JButton rebuildButton = new JButton();
  JButton exitButton = new JButton();
  JButton contButton = new JButton();

  public DBProgress() {

    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String [] args) {
    DBProgress myP = new DBProgress();
  }

  private void jbInit() throws Exception {
    setModal(true);

    titleLabel.setFont(new java.awt.Font("SansSerif", 1, 12));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    statusLabel.setForeground(Color.black);
    titleLabel.setText("Checking Database Tables");
    this.getContentPane().setLayout(xYLayout1);
    statusLabel.setBorder(BorderFactory.createLineBorder(Color.black));
    xYLayout1.setHeight(184);
    xYLayout1.setWidth(351);
    questionLabel.setForeground(Color.red);
    questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

    statusLabel.setText("Analyzing Database ....");


    rebuildButton.setText("Rebuild");
    rebuildButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        rebuildButton_actionPerformed(e);
      }
    });

    exitButton.setText("Exit");
    exitButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        exitButton_actionPerformed(e);
      }
    });

    contButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        contButton_actionPerformed(e);
      }
    });
    contButton.setMaximumSize(new Dimension(45, 11));
    contButton.setMinimumSize(new Dimension(45, 11));
    contButton.setPreferredSize(new Dimension(45, 11));
    this.getContentPane().add(titleLabel, new XYConstraints(45, 12, 243, 28));
    this.getContentPane().add(statusLabel, new XYConstraints(75, 45, 187, 16));
    this.getContentPane().add(questionLabel, new XYConstraints(38, 78, 248, 22));
    this.getContentPane().add(rebuildButton, new XYConstraints(36, 114, 98, 30));
    this.getContentPane().add(exitButton, new XYConstraints(186, 115, 103, 30));
    this.getContentPane().add(contButton, new XYConstraints(36, 114, 98, 30));
    contButton.setVisible(false);


     // Center our window
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     Dimension frameSize = getSize();
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
     setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

     // Hide our Window



     pack();
     checkDB();
     setVisible( true );





  }

  public boolean  checkDB() {
    boolean flag = true;
    tables = cDB.checkDB();
    if (tables.size() != 5) {
      statusLabel.setForeground(Color.red);
      statusLabel.setText("Tables Not Found");
      questionLabel.setText("Would you like to rebuild the Database?");
      pack();
      flag = false;
    } else {
      statusLabel.setForeground(Color.blue);
      statusLabel.setText("Database Analysis Finished Successful");
      questionLabel.setText("Please Select Continue");
      exitButton.setVisible(false);
      rebuildButton.setVisible(false);
      contButton.setVisible(true);
      pack();
    }
    return flag;
  }

  private void rebuildDB() {
    exitButton.setEnabled(false);
    rebuildButton.setEnabled(false);
    if (!cDB.createDB()) {
      questionLabel.setText("Rebuild failed, lease contact support");
      exitButton.setEnabled(true);
      this.pack();
    } else {
      statusLabel.setForeground(Color.green);
      statusLabel.setText("Rebuild Successful");
      questionLabel.setForeground(Color.black);
      questionLabel.setText("Select Continue");
      exitButton.setVisible(false);
      rebuildButton.setVisible(false);
      contButton.setVisible(true);
      this.pack();
    }

  }

  void rebuildButton_actionPerformed(ActionEvent e) {
      rebuildDB();
  }


  void exitButton_actionPerformed(ActionEvent e) {
    dispose();
  }

  void contButton_actionPerformed(ActionEvent e) {
    dispose();
  }


}
