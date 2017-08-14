//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:      
//Description:  DB object for ServiceTypes
//==============================================================================
package vmt;

import java.sql.*;
import java.util.*;

public class ServiceTypeDB extends CloudscapeDB {
    Statement stmt;
    ResultSet RS;

    public ServiceTypeDB() {}

    /**
    * Populates a collection of ServiceTypes.
    *
    * @return serviceTypes a Vector of all the serviceTypes in DB.
    */
    public Vector getData() {
        //get vehicle data

         Vector serviceTypes = new Vector();
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            RS = stmt.executeQuery("SELECT * FROM SERVICE_TYPE ORDER BY DESCR");
            //loop through ResultSet and create records
            while (RS.next()) {
                //create this servoce record
                ServiceType serviceType = new ServiceType();
                serviceType.setServiceTypeId(RS.getInt("SERVICE_TYPE_ID"));
                serviceType.setDescr(RS.getString("DESCR"));
                
                //add to collection
                serviceTypes.add(serviceType);
            }

            //set data to null
            con = null;
            stmt = null;
            RS = null;
         }

         catch (Exception e)  {
           System.out.println("Error " + e);
        }
        return  serviceTypes;
    }

    /**
    * Gets an individual ServiceType
    *
    * @return ServiceType
    */
    public ServiceType getServiceType(int id) {
        ServiceType serviceType = new ServiceType();
        try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            RS = stmt.executeQuery("SELECT * FROM SERVICE_TYPE WHERE SERVICE_TYPE_ID = " + id );
            //loop through ResultSet and create records
            while (RS.next()) {
                //create this service record
                serviceType.setServiceTypeId(RS.getInt("SERVICE_TYPE_ID"));
                serviceType.setDescr(RS.getString("DESCR"));
            }

            //set data to null
            con = null;
            stmt = null;
            RS = null;
         }

         catch (Exception e)  {
           System.out.println("Error " + e);
        }
        return  serviceType;
    }

    public boolean addServiceType(String name) {
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("INSERT INTO SERVICE_TYPE (DESCR)"
                + "VALUES (" + "'" + name + "')");
            //System.out.print(query);
            int i = stmt.executeUpdate(query);

             //clean up objects
            stmt.close();
            con.close();
            con = null;
            stmt = null;

         }  catch (Exception e)  {
              System.out.println("Error " + e);
              return false;
          }

          return true;

    }

    public boolean updateServiceType(String name, int id) {
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("UPDATE SERVICE_TYPE SET DESCR = '" + name + "' "
                + "WHERE SERVICE_TYPE_ID = " + id );
            //System.out.print(query);
            int i = stmt.executeUpdate(query);

             //clean up objects
            stmt.close();
            con.close();
            con = null;
            stmt = null;

         }  catch (Exception e)  {
              System.out.println("Error " + e);
              return false;
          }

          return true;

    }
    //==========================================================================
    public static void main(String[] args) {
        ServiceTypeDB serviceTypeDB = new ServiceTypeDB();
        Vector serviceRecords = serviceTypeDB.getData();
        System.out.println(serviceRecords.toString());

        if(serviceTypeDB.addServiceType("Replace Break Pads")) {
          System.out.println("Item added");
          serviceRecords = serviceTypeDB.getData();
           System.out.println(serviceRecords.toString());
        }
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {

        }
    }
} 