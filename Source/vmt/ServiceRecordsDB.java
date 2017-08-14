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

public class ServiceRecordsDB extends CloudscapeDB {
    Statement stmt;
    ResultSet RS;
    //==========================================================================
    // Constructor(s)
    //==========================================================================
    public ServiceRecordsDB() {}

    //==========================================================================
    // Misc. Methods
    //==========================================================================
    public Vector getData(String vin) {
        //get vehicle data

         Vector serviceRecords = new Vector();
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            RS = stmt.executeQuery("SELECT * FROM SERVICE_RECORD WHERE  VID = '" + vin + "'");
            //loop through ResultSet and create records
            while (RS.next()) {
                //create this servoce record
                ServiceRecord record = new ServiceRecord();

                //set data
                record.setServiceRecordId(RS.getLong("SERVICE_RECORD_ID"));
                record.setVin(RS.getString("VID"));
                record.setServiceTypeId(RS.getInt("SERVICE_TYPE_ID"));
                record.setServiceDate(RS.getString("SERVICE_DATE"));
                record.setIsMaintenance(RS.getBoolean("IS_MAINTENANCE"));
                record.setMileage(RS.getLong("MILEAGE"));
                record.setNotes(RS.getString("NOTES"));
                record.setPrice(RS.getDouble("PRICE"));
                //future items
                //record.setWarPartListId(RS.getInt("WAR_PARTLIST_ID"));
                //record.setServiceCenterId(RS.getInt("SERVICE_CENTER_ID"));

                //add to collection
                serviceRecords.add(record);
            }

            //set data to null
            con = null;
            stmt = null;
            RS = null;
         }

         catch (Exception e)  {
           System.out.println("Error " + e);
        }
        return  serviceRecords;
    }

    /**
    * Add a service record to the database
    *
    * @return boolean False if update was not successful
    * @param vin The vehicle identification number.  This number must be unique.
    * @param ServiceTypeId  the key of service performed
    * @param serviceDate the data of service, currently a String this might change
    * in the future.
    * @param isMaintenance A flag indicating a maintenance record.
    * @param miles Represents the odometer.
    * @param notes Service Record Notes.
    */
    public boolean addServiceRecord(String vin, int serviceTypeId, String serviceDate, boolean isMaintenance , long miles, String notes, double price) {
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("INSERT INTO SERVICE_RECORD (VID, SERVICE_TYPE_ID, SERVICE_DATE, IS_MAINTENANCE, MILEAGE, NOTES, PRICE)"
                + "VALUES (" + "'" + vin + "',"
                + serviceTypeId + ","
                +  "'" + serviceDate + "',"
                +  isMaintenance +  ","
                + miles + ","
                +  "'" + notes + "',"
                + price
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

    /**
    * update a service record.
    *
    * @return boolean False if update was not successful
    * @param vin The vehicle identification number.  This number must be unique.
    * @param ServiceTypeId  the key of service performed
    * @param serviceDate the data of service, currently a String this might change
    * in the future.
    * @param isMaintenance A flag indicating a maintenance record.
    * @param miles Represents the odometer.
    * @param notes Service Record Notes.
    */
    public boolean updateServiceRecord(String vin, long serviceRecordId, int serviceTypeId, String serviceDate, boolean isMaintenance , long miles, String notes, double price) {
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("UPDATE SERVICE_RECORD "
                + "SET "
                + "SERVICE_TYPE_ID = " + serviceTypeId + ","
                + "SERVICE_DATE = '" + serviceDate + "',"
                + "IS_MAINTENANCE = " + isMaintenance + ","
                + "MILEAGE = " + miles + ","
                + "NOTES = '" + notes + "', "
                + "PRICE = " + price
                + " WHERE VID = '" + vin + "'"
                + " AND  SERVICE_RECORD_ID = " + serviceRecordId);
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

    /**
    * Remove a service record from the database
    *
    * @return boolean False if update was not successful
    * @param vin The vehicle identification number.  This number must be unique.
    * @param ServiceTypeId  the key of service performed
    * @param serviceDate the data of service, currently a String this might change
    * in the future.
    * @param isMaintenance A flag indicating a maintenance record.
    * @param miles Represents the odometer.
    * @param notes Service Record Notes.
    */
     public boolean removeServiceRecord(long serviceRecordId) {
        try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = "DELETE FROM SERVICE_RECORD WHERE SERVICE_RECORD_ID = " + serviceRecordId ;
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
        ServiceRecordsDB serviceRecordsDB = new ServiceRecordsDB();
        Vector serviceRecords = serviceRecordsDB.getData("test");
        System.out.println(serviceRecords.toString());

        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {

        }
    }
}
