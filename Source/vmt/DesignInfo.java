
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Todd Isaacs
//Company:      
//Description:  

package vmt;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;
import java.io.*;
import com.borland.jbcl.control.*;
import java.awt.event.*;

public class DesignInfo extends JDialog {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel topPanel = new JPanel();
  JPanel bottmPanel = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JButton closeButton = new JButton();
  JLabel headerLabel = new JLabel();

  StyledDocument document = new DefaultStyledDocument();
  private RTFEditorKit editKit = new RTFEditorKit();
  JTextPane jTextPane1 = new JTextPane(document);


  public DesignInfo(Frame frame) {
    super(frame, "Design Information", false);
    try  {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DesignInfo() {
    this(null);
  }

  void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    closeButton.setText("Close");
    closeButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        closeButton_actionPerformed(e);
      }
    });
    headerLabel.setFont(new java.awt.Font("Serif", 1, 14));
    headerLabel.setForeground(Color.blue);
    headerLabel.setPreferredSize(new Dimension(200, 17));
    headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    headerLabel.setText("Design Information");
    panel1.setPreferredSize(new Dimension(600, 400));
    getContentPane().add(panel1);
    panel1.add(topPanel, BorderLayout.NORTH);
    topPanel.add(headerLabel, null);
    panel1.add(bottmPanel, BorderLayout.SOUTH);
    bottmPanel.add(closeButton, null);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTextPane1, null);
    jTextPane1.setEditable(false);

    //Load Design.rtf document
    try {
        FileInputStream in = new FileInputStream("design.rtf");
        jTextPane1.setText("");
        editKit.read(in, document, 0);
        jTextPane1.setCaretPosition(0);
        
    } catch (Exception e) {
       //JOptionPane.showMessageDialog (this, "Error opening file." , "File Error", JOptionPane.WARNING_MESSAGE);
        jTextPane1.setText("Error opening design document");
    }
  }

  void closeButton_actionPerformed(ActionEvent e) {
    this.dispose();
  }
}

