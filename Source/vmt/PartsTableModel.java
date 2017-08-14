package vmt;

import javax.swing.table.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.*;
import javax.swing.*;

class PartsTableModel extends AbstractTableModel {

 /* the default framework is embedded*/
	private String framework = "embedded";
	private String driver = "COM.cloudscape.core.JDBCDriver";
	private String url = "jdbc:cloudscape:CloudscapeDB;create=False";
  private Connection con;
  private Statement stmt;
  private ResultSet rs;
  private ResultSet tmp;

  private Vector main = new Vector();
  private Vector columns;

  public PartsTableModel() {
       columns = new Vector();
       //Setup column heading manually
       columns.add(new String("ID"));
       columns.add(new String("Desc"));
       columns.add(new String("Install Date"));
       columns.add(new String("Expire Date"));
       columns.add(new String("Install Miles"));
       columns.add(new String("Expire Miles"));
       columns.add(new String("Supplier"));

       setData();
  }

  public void setData() {
    Vehicle v = VehicleInfoPanel.getSelectedVehicle();
    if (v == null) {
        return;
    }
    String vin = v.getVin();
    main.removeAllElements();
      try {

        Class.forName(driver);
        con = DriverManager.getConnection(url);
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("SELECT * FROM WARRANTY_PART WHERE VID = '" + vin + "'");
        //Clear Model

      while (rs.next()) {
        Vector lineItem = new Vector();
        lineItem.add(rs.getObject(1));
        lineItem.add(rs.getString(3));
        lineItem.add(rs.getString(4));
        lineItem.add(rs.getString(5));
        lineItem.add(rs.getObject(6));
        lineItem.add(rs.getObject(7));
        lineItem.add(rs.getString(8));

        main.add(lineItem);
        System.out.println(lineItem + "END ITEM");
        //System.out.println(rs.getString(3));

      }
      //Notify table changed
      fireTableDataChanged();
      rs.close();
      stmt.close();              
      con.close();

    } catch (Exception e) {
      System.out.println("Error populating Table Model :" + e.getMessage());
    }

  }

  public boolean isCellEditable(int row, int col) {
    if (col == 0) {
      return false;
    } else {
      return true;
    }
  }


  public String getColumnName(int column) {
      return (String)columns.get(column);
  }

  public int getRowCount(){
      return main.size();
  }

  public int getColumnCount(){
    return columns.size();
  }

  public Object getValueAt(int vRow, int column){
    Vector row = (Vector)main.get(vRow);
    return row.get(column);
  }

  public void  AddNewRecord(String descr, String startDate, String endDate,
                      int startMiles, int endMiles, String supplier) {
    //get current vehicle
    Vehicle v = VehicleInfoPanel.getSelectedVehicle();
    if (v == null) {
        return;
    }
    String vin = v.getVin();

    //update vehicle odometer if startMiles is larger
    if (startMiles > v.getMileage()) {
        VehicleInfoPanel.updateVehicleOdometer(startMiles);
    }

    try {
        Vector lineItem = new Vector();
        int id = 0;
        if (main.size() != 0) {
            Vector lastLine = (Vector)main.lastElement();
            id = Integer.parseInt((lastLine.elementAt(0).toString()));
        }
        System.out.println("id = " + id);

        Class.forName(driver);
        con = DriverManager.getConnection(url);
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
        stmt.execute("INSERT INTO WARRANTY_PART (VID, DESCR, INSTALL_DATE, EXPIRE_DATE, INSTALL_MILES, EXPIRE_MILES, SUPPLIER) values ('" + vin + "', '" + descr + "', '" + startDate + "', '" + endDate + "', " + startMiles + ", " + endMiles + ", '" + supplier + "')" );
        System.out.println("INSERT INTO WARRANTY_PART (VID, DESCR, INSTALL_DATE, EXPIRE_DATE, INSTALL_MILES, EXPIRE_MILES, SUPPLIER) values ('" + vin + "', '" + descr + "', '" + startDate + "', '" + endDate + "', " + startMiles + ", " + endMiles + ", '" + supplier + "')" );

        closeDB();
        setData();
        fireTableDataChanged();
    } catch (Exception e) {
      closeDB();
      System.out.println("Failed to insert new row");
      System.out.println(e.getMessage());

    }
  }
  
  public void setValueAt(Object o, int row, int column) {
      //update Database
      String sql = "";
      switch (column) {
        case 1 : sql = "UPDATE WARRANTY_PART SET DESCR = '" + o.toString() + "' WHERE PART_ID = " + Integer.parseInt((getValueAt(row , 0)).toString());
          break;
        case 2 : sql = "UPDATE WARRANTY_PART SET INSTALL_DATE = '" + o.toString() + "' WHERE PART_ID = " + Integer.parseInt((getValueAt(row , 0)).toString());
          break;
        case 3 : sql = "UPDATE WARRANTY_PART SET EXPIRE_DATE = '" + o.toString() + "' WHERE PART_ID = " + Integer.parseInt((getValueAt(row , 0)).toString());
          break;
        case 4 :
            int installMiles = 0;
          try {
             installMiles = Integer.parseInt(o.toString());
          } catch (NumberFormatException e) {
              numberErrorMessage("Error Formatting Number");
              return;
          }
          sql = "UPDATE WARRANTY_PART SET INSTALL_MILES = '" + installMiles + "' WHERE PART_ID = " + Integer.parseInt((getValueAt(row , 0)).toString());
          break;
        case 5 :
          int expireMiles = 0;
          try {
             expireMiles = Integer.parseInt(o.toString());
          } catch (NumberFormatException e) {
              numberErrorMessage("Error Formatting Number");
              return;
          }
          sql = "UPDATE WARRANTY_PART SET EXPIRE_MILES = '" + expireMiles + "' WHERE PART_ID = " + Integer.parseInt((getValueAt(row , 0)).toString());
          break;
        case 6 : sql = "UPDATE WARRANTY_PART SET SUPPLIER = '" + o.toString() + "' WHERE PART_ID = " + Integer.parseInt((getValueAt(row , 0)).toString());
      }
      //System.out.println(sql);
       try {
          Class.forName(driver);
          con = DriverManager.getConnection(url);
          stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
          stmt.execute(sql);
          closeDB();
          //update data model
            Vector tmp = (Vector)main.get(row);
            tmp.remove(column);
            tmp.add(column, o);
       } catch (Exception e) {
          closeDB();
          databaseErrorMessage("Database Update Failed /n" + e.getMessage());
          System.out.println(e.getMessage());

       }

  }

  public void removeItem(int row) throws Exception {
        //get the database id
        int id = Integer.parseInt((getValueAt(row, 0).toString()));
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
            String sql = "DELETE FROM WARRANTY_PART WHERE PART_ID = ";
            sql += id;
            System.out.println(sql);
            int i = stmt.executeUpdate(sql);
            //stmt.executeQuery(sql);

            closeDB();
            //remove from model
            main.removeElementAt(row);
            fireTableDataChanged();
        }
        catch (Exception e) {
          closeDB();
          throw e;
        }
  }

   private void databaseErrorMessage(String s) {
        JOptionPane.showMessageDialog
                    (
                      null,
                      s,
                      "Database Error",
                      JOptionPane.ERROR_MESSAGE
                    );
    }

    private void numberErrorMessage(String s) {
        JOptionPane.showMessageDialog
                    (
                      null,
                      s,
                      "Number Format Error",
                      JOptionPane.ERROR_MESSAGE
                    );
    }

   private void closeDB() {
      try {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (con != null) con.close();
      } catch (SQLException sqle) {
          System.out.println(sqle.getMessage());
      }
   }
}