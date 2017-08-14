//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:      
//Description:  service record
//==============================================================================
package vmt;

import java.sql.*;
import java.util.*;
import java.text.*;

public class GasMileageRecordDB extends CloudscapeDB {
    Statement stmt;
    ResultSet RS;
    //==========================================================================
    // Constructor(s)
    //==========================================================================
    public GasMileageRecordDB() {}

    //==========================================================================
    // Misc. Methods
    //==========================================================================
    public Vector getData(String vin) {
        //get all Gas Records

         Vector gasRecords = new Vector();
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            RS = stmt.executeQuery("SELECT * FROM GAS_RECORD WHERE  VID = '" + vin + "'");
            //loop through ResultSet and create records
            while (RS.next()) {
                //create this servoce record
                GasMileageRecord record = new GasMileageRecord();

                //set data
                record.setGasMileageRecordId(RS.getLong("GAS_RECORD_ID"));
                record.setVin(RS.getString("VID"));
                record.setStartOdometer(RS.getLong("STARTMILE"));
                record.setEndOdometer(RS.getLong("ENDMILE"));
                record.serPricePerGal(RS.getDouble("PRICE_PER_UNIT"));
                record.setUnits(RS.getDouble("UNITS"));
                //add to collection
                gasRecords.add(record);
            }

            //set data to null
            con = null;
            stmt = null;
            RS = null;
         }

         catch (Exception e)  {
           System.out.println("Error :" + e);
        }
        return  gasRecords;
    }

    /**
    * Add a service record to the database
    *
    * @return boolean False if update was not successful
    * @param vin The vehicle identification number.  This number must be unique.
    * @param startMiles initial odometer value
    * @param endMiles  ending odometer value
    * @param pricePerGal price per unit of gas
    */
    public boolean addRecord(String vin, long startMiles, long endMiles, double pricePerGal, double units) {
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("INSERT INTO GAS_RECORD (VID, STARTMILE, ENDMILE, PRICE_PER_UNIT, UNITS)"
                + "VALUES (" + "'" + vin + "',"
                + startMiles + ","
                + endMiles + ","
                + pricePerGal + ","
                + units
                + ")" );
            //System.out.print(query);
            int i = stmt.executeUpdate(query);

             //set data to null
            stmt.close();
            con.close();
            con = null;
            stmt = null;

         }

         catch (Exception e)  {
           System.out.println("Error " + e);

           return false;
        }

        return true;
    }


    public boolean updateRecord(long gasRecordId, String vin, long startMiles, long endMiles, double pricePerGal, double units) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("UPDATE GAS_RECORD "
                + "SET "
                + "STARTMILE = " + startMiles + ","
                + "ENDMILE = " + endMiles + ","
                + "PRICE_PER_UNIT = " + pricePerGal + ","
                + "UNITS = " + units
                + " WHERE VID = '" + vin + "'"
                + " AND  GAS_RECORD_ID = " + gasRecordId);
            System.out.print(query);
            int i = stmt.executeUpdate(query);

             //set data to null
            stmt.close();
            con.close();
            con = null;
            stmt = null;

            return true;
         } catch (Exception e)  {
           System.out.println("Error " + e);

           return false;
        }


    }


     public boolean removeRecord(long gasRecordId) {
        try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = "DELETE  FROM GAS_RECORD WHERE GAS_RECORD_ID = " + gasRecordId ;
            int i = stmt.executeUpdate(query);

            //set data to null
            stmt.close();
            con.close();
            con = null;
            stmt = null;
        }

         catch (Exception e)  {
           System.out.println("Error " + e);
           return false;
        }
        return true;
    }

    //==========================================================================
    // Test Method
    //==========================================================================
    public static void main(String[] args) {
        GasMileageRecordDB r = new GasMileageRecordDB();
        Vector v = r.getData("test");
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {

        }
    }
}
