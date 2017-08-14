
//Title:        Vehicle Maintenance Tracker 1.1
//Version:
//Copyright:    Copyright (c) 2001
//Author:       Todd E. Isaacs
//Company:
//Description:  Track Maintence Records.

package vmt;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;
import java.util.*;
import java.text.*;
import java.text.DecimalFormat.*;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.plaf.basic.*;
import com.borland.jbcl.control.*;
import java.io.*;

public class ReportsPanel extends JPanel implements Printable, Observer {

    final static DecimalFormat DF = new DecimalFormat("###,###.00");

    BorderLayout borderLayout1 = new BorderLayout();
    GridLayout gridLayout1 = new GridLayout();
    JPanel buttonPanel = new JPanel();
    JButton report1 = new JButton();
    JButton save = new JButton();
    JButton open = new JButton();
    JButton print = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    protected PrintView  m_printView;
    private RTFEditorKit editKit = new RTFEditorKit();
    ServiceTypeDB serviceTypeDB = new ServiceTypeDB();

    StyleContext context = new StyleContext();

    StyledDocument document = new DefaultStyledDocument();
    Style style = context.getStyle(StyleContext.DEFAULT_STYLE);

    ServiceRecordsDB srDB = new ServiceRecordsDB();
    JTextPane jTextPane1 = new JTextPane(document);

    //objects with data
    Vehicle vehicle;
    Vector serviceRecords;

    //setup printer
    PrinterJob printerJob = PrinterJob.getPrinterJob();
    Book book = new Book();
    Filer filer1 = new Filer();
    //AttributedString mStyledText;

    public ReportsPanel() {
        try  {
            jbInit();
            //createHeader();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
    report1.setText("Maintenance Report");

    open.setText("Open");
    save.setText("Save");
    print.setText("Print");

    jTextPane1.setEditable(false);

    this.setLayout(borderLayout1);
    buttonPanel.setLayout(gridLayout1);
    buttonPanel.add(report1);
    buttonPanel.add(save);
    buttonPanel.add(print);
    buttonPanel.add(open);
    this.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTextPane1, null);
    this.add(buttonPanel, BorderLayout.SOUTH);

    filer1.setFrame(new Frame());
    filer1.setMode(FileDialog.SAVE);
    filer1.setTitle("Save Report");
    filer1.setDirectory("reports");

    //add listeners
    save.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        save_actionPerformed(e);
      }
    });

    report1.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        report1_actionPerformed(e);
      }
    });

    open.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        open_actionPerformed(e);
      }
    });

    print.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        print_actionPerformed(e);
      }
    });
  }

    public void setData(Vehicle v, Vector r) {
      vehicle = v;
      serviceRecords = r;
    }

    public int print(Graphics pg, PageFormat pageFormat, int pageIndex)
        throws PrinterException {

        pg.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
        int wPage = (int)pageFormat.getImageableWidth();
        int hPage = (int)pageFormat.getImageableHeight();
        pg.setClip(0,0, wPage, hPage);

        if (m_printView == null) {
            BasicTextUI btui = (BasicTextUI)jTextPane1.getUI();
            View root = btui.getRootView(jTextPane1);
            m_printView = new PrintView(document.getDefaultRootElement(), root, wPage, hPage);
        }

        boolean bContinue = m_printView.paintPage(pg, hPage, pageIndex);
        System.gc();

        if (bContinue) return PAGE_EXISTS;
        else {
            m_printView = null;
            return NO_SUCH_PAGE;
        }
    }


    public void printData() {
      try {
          PrinterJob prnJob = PrinterJob.getPrinterJob();
          prnJob.setPrintable(this);
          if (!prnJob.printDialog()) return;
          setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          prnJob.print();
          setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

      } catch (PrinterException e) {
          e.printStackTrace();
          System.err.println("Printing error: " + e.toString());
      }
    }

   public void createHeader() {
      SimpleAttributeSet attr = new SimpleAttributeSet();
      StyleConstants.setBold(attr, true);
      StyleConstants.setAlignment(attr, StyleConstants.ALIGN_CENTER);
      StyleConstants.setFontFamily(attr, "Arial");
      StyleConstants.setFontSize(attr, 18);

      Date date = new Date();
      DateFormat df = new SimpleDateFormat("MMMM d, yyyy");
      String string1 = "Maintenance Report - " + df.format(date);

      try {
        document.insertString(document.getLength(), string1, attr);
      } catch (BadLocationException bl) {

      }

      if (vehicle != null) {
          StyleConstants.setBold(attr, false);
          StyleConstants.setFontSize(attr, 12);
          String string2 = "\n\nVehicle : \t" + vehicle.getName() + "\t\t" + "VIN/SN   : \t" + vehicle.getVin();
          String string3 = "\nMake    : \t" + vehicle.getMake() + "\t\t" + "Year     : \t" + vehicle.getYear();
          String string4 = "\nModel   : \t" + vehicle.getModel() + "\t\t" + "Odometer : \t" + vehicle.getMileage();
          String string5 = "\nColor   : \t" + vehicle.getColor();
          String string6 = "\n\nService Records\n";

          try {
            document.insertString(document.getLength(), string2, attr);
            document.insertString(document.getLength(), string3, attr);
            document.insertString(document.getLength(), string4, attr);
            document.insertString(document.getLength(), string5, attr);
            document.insertString(document.getLength(), string6, attr);
          } catch (BadLocationException bl) {

          }
        }
      }

      public void createMaintenanceReport(boolean nDetails, int serviceType) {
          SimpleAttributeSet attr = new SimpleAttributeSet();
          StyleConstants.setBold(attr, true);

          String string7 = "Date \tMileage/Hours \tService Type \t\tPrice\n";

          try {
             document.insertString(document.getLength(), string7, attr);
          } catch (BadLocationException bl) {

          }

          StyleConstants.setBold(attr, false);
          StyleConstants.setFontSize(attr, 12);
          SimpleAttributeSet attr2 = new SimpleAttributeSet();
          StyleConstants.setFontSize(attr, 10);
          StyleConstants.setItalic(attr2, true);
          StyleConstants.setAlignment(attr2, StyleConstants.ALIGN_LEFT);
          StyleConstants.setLeftIndent(attr2, 100);
          StyleConstants.setForeground(attr2, Color.lightGray);
          try {
          ServiceRecord s = new ServiceRecord();
          Enumeration e = serviceRecords.elements();
            while (e.hasMoreElements()) {
              s = (ServiceRecord)e.nextElement();

              //set service type length to 30 chars
              String sType = serviceTypeDB.getServiceType(s.getServiceTypeId()).getDescr();
              if (sType.length() > 30) {
                sType = sType.substring(0, 29);
              } else {
                int length = sType.length();
                int newStringLength = 30 - length;
                StringBuffer sb = new StringBuffer(newStringLength);
                for (int i = 0; i < sb.capacity(); i++ ) {
                  sb.insert(i, " ");
                }
                sType += sb.toString();

              }
              if (serviceType == -1) {
                  document.insertString(document.getLength(), "" + s.getServiceDateString() + "\t" + s.getMileage() + "\t\t" + sType + "\t" + DF.format(s.getPrice()) + "\n", attr);
                   if (nDetails)  {
                      document.insertString(document.getLength(), s.getNotes() + "\n", attr2);
                  }
              } else {
                  if ( s.getServiceTypeId() == serviceType ) {
                    document.insertString(document.getLength(), "" + s.getServiceDateString() + "\t" + s.getMileage() + "\t\t" + sType + "\t" + DF.format(s.getPrice()) + "\n", attr);
                     if (nDetails)  {
                        document.insertString(document.getLength(), s.getNotes() + "\n", attr2);
                    }
                  }
              }
            }
          } catch (BadLocationException bl) {

          }
    }

    void report1_actionPerformed(ActionEvent e) {

        ReportSelectionDialog rDlg = new ReportSelectionDialog(new Frame(),"Report Options Dialog", true);
        rDlg.setVisible(true);
        jTextPane1.setText("");

        if (!rDlg.getContinue()) {
          //exit
          return;
        }

        //run report
        boolean detailsFlag = true;
        if (!rDlg.getShowDetail()) detailsFlag = false;
        switch (rDlg.getRadioSelection()) {
          case 1:
            createHeader();
            createMaintenanceReport(detailsFlag, -1);
            break;
          case 2:
            createHeader();
            createMaintenanceReport(detailsFlag, rDlg.getServiceTypeID());
            break;
          case 3:
            break;
          case 4:
            break;
    }
  }

  void print_actionPerformed(ActionEvent e) {
    Thread t = new Thread() {
      public void run() {
          printData();
      }
    };
    t.start();
  }

  private void saveReport() {
  filer1.setMode(Filer.SAVE);
    filer1.setVisible(true);
    try {
      FileOutputStream out = new FileOutputStream(filer1.getDirectory() + filer1.getFile());
       editKit.write(out, document, 0, document.getLength());
      //jTextPane1.getEditorKit().write(out, document, 0, document.getLength() );
    } catch (Exception e) {
       JOptionPane.showMessageDialog (this, "Error creating output stream. \nFile not saved." , "File Error", JOptionPane.WARNING_MESSAGE);
    }
  }

  private void openReport() {
    filer1.setMode(Filer.LOAD);
    filer1.setVisible(true);
    try {
        FileInputStream in = new FileInputStream(filer1.getDirectory() + filer1.getFile());
        jTextPane1.setText("");
        editKit.read(in, document, 0);
    } catch (Exception e) {
       JOptionPane.showMessageDialog (this, "Error opening file." , "File Error", JOptionPane.WARNING_MESSAGE);
    }
  }

  public void updatePanelData() {
    jTextPane1.setText("");
    vehicle = VehicleInfoPanel.getSelectedVehicle();
    serviceRecords = srDB.getData(VehicleInfoPanel.getSelectedVehicleVIN());

  }

  //create class for printing
  class PrintView extends BoxView {
      protected int m_firstOnPage = 0;
      protected int m_lastOnPage = 0;
      protected int m_pageIndex = 0;

      public PrintView(Element elem, View root, int w, int h) {
          super(elem, Y_AXIS);
          setParent(root);
          setSize(w, h);
          layout(w, h);
      }

      public boolean paintPage(Graphics g, int hPage, int pageIndex) {
          if (pageIndex > m_pageIndex) {
              m_firstOnPage = m_lastOnPage + 1;
              if (m_firstOnPage >= getViewCount()) return false;
          }
          int yMin = getOffset(Y_AXIS, m_firstOnPage);
          int yMax = yMin + hPage;
          Rectangle rc = new Rectangle();

          for (int k = m_firstOnPage; k < getViewCount(); k++) {
              rc.x = getOffset(X_AXIS, k);
              rc.y = getOffset(Y_AXIS, k);
              rc.width = getSpan(X_AXIS, k);
              rc.height = getSpan(Y_AXIS, k);
              if (rc.y + rc.height > yMax) break;
              m_lastOnPage = k;
              rc.y -= yMin;
              paintChild(g, rc, k);
          }
          return true;
      }
  }

  public void update(Observable obs, Object o){
        //received notice that there was an update to the Vinfopanel
        // only get data if there is a non-null vehicle object
        if ( VehicleInfoPanel.getSelectedVehicle() != null ) {
          updatePanelData();
        }
            //System.out.println("Reports Panel observing a change!");
    }

  void save_actionPerformed(ActionEvent e) {
     saveReport();
  }

  void open_actionPerformed(ActionEvent e) {
      openReport();
  }
} 