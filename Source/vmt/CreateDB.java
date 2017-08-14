
//Title:        Vehicle Maintenance Tracker
//Version:
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:
//Description:  Track Maintenance
package vmt;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CreateDB  {

  /* the default framework is embedded*/
	private String framework = "embedded";
	private String driver = "COM.cloudscape.core.JDBCDriver";
	private String protocol = "jdbc:cloudscape:";
  private Connection con;
  private Statement stmt;
  public ResultSet rs;
  private boolean flag = true;


  public boolean createDB() {
    DBConnect();
    dropAllTables();
    System.out.println("Creating Tables");
    if (!createGasRecordTBL()) flag = false;
    if (!createServiceRecordTBL()) flag = false;
    if (!createServiceTypeTBL()) flag = false;
    if (!createVehicleTBL()) flag = false;
    if (!createWarrantyPartTBL()) flag = false;

    DBClose();
    return flag;
  }

  //create a vector of all the users tables
  public Vector checkDB() {
    Vector tables = new Vector();
     DBConnect();
     try  {
        rs = stmt.executeQuery("SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE = 'T'");

        while (rs.next())  {
          tables.add(rs.getString("TABLENAME"));
        }
     } catch (SQLException e) {
        System.out.println("Database Error, SYSTABLE Query failed");
     }

     DBClose();
    return tables;
  }


public void dropAllTables() {
  try {
    stmt.execute("DROP TABLE SERVICE_RECORD");
  } catch (SQLException e) {
      System.out.println("Failed to drop SERVICE_RECORD, " + e.getMessage());
  }
    try {
     stmt.execute("DROP TABLE VEHICLE");
  } catch (SQLException e) {
      System.out.println("Failed to drop VEHICLE, " + e.getMessage());
  }
    try {
    stmt.execute("DROP TABLE GAS_RECORD");
  } catch (SQLException e) {
      System.out.println("Failed to drop GAS_RECORD, " + e.getMessage());
  }
  try {
    stmt.execute("DROP TABLE SERVICE_TYPE");
  } catch (SQLException e) {
      System.out.println("Failed to drop SERVICE_TYPE, " + e.getMessage());
  }

  try {
    stmt.execute("DROP TABLE WARRANTY_PART");
  } catch (SQLException e) {
      System.out.println("Failed to drop WARRANTY_PART, " + e.getMessage());
  }
}




//Open database connections
public void DBConnect() {

      try {
          Class.forName(driver).newInstance();
      } catch (Exception ex) {
          System.out.println("Error, class not found " + driver);
      }
      try  {
        con = DriverManager.getConnection(protocol + "CloudscapeDB;create=True");
        stmt = con.createStatement();
      }  catch (SQLException e) {
        System.out.println("Error connecting to DB");
        }
  }

//Close database Connections
public void DBClose() {
    try {
      if (rs != null) rs.close();
      if (stmt != null) stmt.close();
      if (con != null) con.close();
    } catch (SQLException sqle) {
      System.out.println("Database Error, Resources not Released");
    }
 }

public boolean createServiceRecordTBL() {
   //Create SERVICE_RECORD table
    try {
      String sql = "CREATE TABLE SERVICE_RECORD ( ";
      sql = sql + "SERVICE_RECORD_ID INT DEFAULT AUTOINCREMENT,";
      sql = sql + "VID varchar (200) NOT NULL, ";
      sql = sql + "SERVICE_TYPE_ID int NOT NULL,";
      sql = sql + "SERVICE_DATE varchar (50) NULL, ";
      sql = sql + "IS_MAINTENANCE boolean NULL, ";
      sql = sql + "MILEAGE float NULL, ";
      sql = sql + "NOTES varchar (500) NULL, ";
      sql = sql + "SERVICE_CENTER_ID INT NULL, ";
      sql = sql + "PRICE float NULL, ";
      sql = sql + "PRIMARY KEY (SERVICE_RECORD_ID) )";


      stmt.execute(sql);

    } catch (Exception e) {
       System.out.println("Create SERVICE_RERCORD table failed");
       System.out.println(e.getMessage());
       flag = false;
    }
  return flag;
}

 public boolean createVehicleTBL() {
  //Create VEHICLE table
    try {
      String sql = "CREATE TABLE VEHICLE ( ";
      sql = sql + "VID varchar (200) NULL,";
      sql = sql + "VNAME varchar (30) NULL, ";
      sql = sql + "VYEAR int NULL,";
      sql = sql + "MAKE varchar (30) NULL, ";
      sql = sql + "MODEL varchar (30) NULL, ";
      sql = sql + "COLOR varchar (30) NULL, ";
      sql = sql + "MILEAGE float NULL, ";
      sql = sql + "IMAGE varchar (75) ";
      sql = sql + ")";


      stmt.execute(sql);

    } catch (Exception e) {
       System.out.println("Create VEHICLE table failed");
       System.out.println(e.getMessage());
       flag = false;

    }
    return flag;
  }

  public boolean createGasRecordTBL() {
   //Create GAS_RECORD table
    try {
      String sql = "CREATE TABLE GAS_RECORD ( ";
      sql = sql + "GAS_RECORD_ID INT DEFAULT AUTOINCREMENT, ";
      sql = sql + "VID varchar (200) NULL,";
      sql = sql + "STARTMILE int NULL, ";
      sql = sql + "ENDMILE int NULL,";
      sql = sql + "PRICE_PER_UNIT float NULL, ";
      sql = sql + "UNITS float NULL, ";
      sql = sql + "RECORD_DATE varchar (50) NULL, ";
      sql = sql + "PRIMARY KEY (GAS_RECORD_ID) )";

      stmt.execute(sql);
    } catch (Exception e) {
       System.out.println("Create GAS_RECORD table failed");
       System.out.println(e.getMessage());
       flag = false;
    }
   return flag;
  }

   public boolean createServiceTypeTBL() {
    //Create SERVICE_TYPE table
    try {
      String sql = "CREATE TABLE SERVICE_TYPE ( ";
      sql = sql + "SERVICE_TYPE_ID INT DEFAULT AUTOINCREMENT,";
      sql = sql + "DESCR varchar (50) NOT NULL ";
      sql = sql + ")";


      stmt.execute(sql);

    } catch (Exception e) {
       System.out.println("Create SERVICE_TYPE table failed");
       System.out.println(e.getMessage());
       flag = false;

    }
   return flag;
  }

  public boolean createWarrantyPartTBL() {
  //Create WARRANT_PART table
    try {
      String sql = "CREATE TABLE WARRANTY_PART ( ";
      sql = sql + "PART_ID INT DEFAULT AUTOINCREMENT,";
      sql = sql + "VID varchar (200) NULL,";
      sql = sql + "DESCR varchar (50) NULL, ";
      sql = sql + "INSTALL_DATE varchar (50) NULL, ";
      sql = sql + "EXPIRE_DATE varchar (50) NULL, ";
      sql = sql + "INSTALL_MILES INT NULL, ";
      sql = sql + "EXPIRE_MILES INT NULL, ";
      sql = sql + "SUPPLIER varchar (100) NULL";
      sql = sql + ")";

      stmt.execute(sql);

    } catch (Exception e) {
       System.out.println("Create WARRANT_PART table failed");
       System.out.println(e.getMessage());
       flag = false;
    }
  return flag;
}

  public static void main(String[] args) {
    CreateDB newDB = new CreateDB();
    newDB.invokedStandalone = true;
    //System.out.println("Creating Warranty Part table");
    newDB.DBConnect();

     try {
        newDB.stmt.execute("DROP TABLE WARRANTY_PART");
    } catch (SQLException e) {
        System.out.println("Failed to drop SERVICE_TYPE, " + e.getMessage());
    }

    newDB.createWarrantyPartTBL();

    newDB.DBClose();
    try {
      Thread.currentThread().sleep(2000);
    } catch (Exception e) {
        newDB.DBClose();
    }
  }
  private boolean invokedStandalone = false;
}