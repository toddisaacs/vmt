//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:
//Description:  Retrieves Vehicle data from DB
//==============================================================================
package vmt;

import java.sql.*;
import java.util.*;

public class VehicleDB extends CloudscapeDB {
    private Connection con;
    //private String DBurl = "jdbc:odbc:vmt";
    //private String DBClass = "sun.jdbc.odbc.JdbcOdbcDriver";
    //private String DBurl = "jdbc:cloudscape:";
   // private String DBDriver = "COM.cloudscape.core.JDBCDriver";
    private Statement stmt;
    private ResultSet RS;
    //==========================================================================
    // Constructor(s)
    //==========================================================================
    public VehicleDB() {}

    //==========================================================================
    // Misc. Methods
    //==========================================================================
    public Vehicle getData(String vin) {
        //get vehicle data
         Vehicle motorThing = new Vehicle();
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();

            RS = stmt.executeQuery("SELECT * FROM VEHICLE WHERE  VID = '" + vin + "'");

            // move to first row
            RS.next();
            //System.out.println(RS.getRow());
            //set vehicle data

            motorThing.setVin(RS.getString("VID"));
            motorThing.setName(RS.getString("VNAME"));
            motorThing.setYear(RS.getInt("VYEAR"));
            motorThing.setMake(RS.getString("MAKE"));
            motorThing.setModel(RS.getString("MODEL"));
            motorThing.setColor(RS.getString("COLOR"));
            motorThing.setMileage(RS.getLong("MILEAGE"));
            motorThing.setImagePath(RS.getString("IMAGE"));

            //set data to null
            RS.close();
            con.close();
            stmt.close();
            con = null;
            stmt = null;
            RS = null;
         }

         catch (Exception e)  {
           System.out.println("Error " + e);
        }
        return  motorThing;
    }

     public Vector getAllVehicles() {
        Vector allVehicles = new Vector();
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            RS = stmt.executeQuery("SELECT * FROM VEHICLE");
            // move to first row
            while(RS.next()) {
                Vehicle motorThing = new Vehicle();
                //set vehicle data
                motorThing.setVin(RS.getString("VID"));
                motorThing.setName(RS.getString("VNAME"));
                motorThing.setYear(RS.getInt("VYEAR"));
                motorThing.setMake(RS.getString("MAKE"));
                motorThing.setModel(RS.getString("MODEL"));
                motorThing.setColor(RS.getString("COLOR"));
                motorThing.setMileage(RS.getLong("MILEAGE"));
                motorThing.setImagePath(RS.getString("IMAGE"));
                //add vehicle to vector
                allVehicles.add(motorThing);
            }

            //set data to null
            RS.close();
            con.close();
            stmt.close();
            con = null;
            stmt = null;
            RS = null;
         }

         catch (Exception e)  {
           System.out.println("Error " + e);
        }
        return  allVehicles;
    }

    public HashMap getAllVehiclesMap() {
        HashMap allVehiclesMap = new HashMap();
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            RS = stmt.executeQuery("SELECT * FROM VEHICLE");
            // move to first row
            while(RS.next()) {
                Vehicle motorThing = new Vehicle();
                //set vehicle data
                motorThing.setVin(RS.getString("VID"));
                motorThing.setName(RS.getString("VNAME"));
                motorThing.setYear(RS.getInt("VYEAR"));
                motorThing.setMake(RS.getString("MAKE"));
                motorThing.setModel(RS.getString("MODEL"));
                motorThing.setColor(RS.getString("COLOR"));
                motorThing.setMileage(RS.getLong("MILEAGE"));
                motorThing.setImagePath(RS.getString("IMAGE"));
                //add vehicle to vector
                allVehiclesMap.put(motorThing.getVin(), motorThing);
            }

            //set data to null
            RS.close();
            con.close();
            stmt.close();
            con = null;
            stmt = null;
            RS = null;
         }

         catch (Exception e)  {
           System.out.println("Error " + e);
        }
        return  allVehiclesMap;
    }

    public boolean addVehicle(String vin, String name, int year, String make , String model, String color, long mileage, String path) {
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("INSERT INTO VEHICLE (VID, VNAME, VYEAR, MAKE, MODEL, COLOR, MILEAGE, IMAGE)"
                + "VALUES (" + "'" + vin + "',"
                +  "'" + name + "',"
                +  year + ","
                +  "'" + make + "',"
                +  "'" + model + "',"
                +  "'" + color + "',"
                + mileage  + ","
                + "'" + path + "'"
                + ")" );
            System.out.print(query);
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

     public boolean updateVehicleOdometer(String vin, long miles) {
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("UPDATE VEHICLE "
                + "SET "
                + "MILEAGE = " + miles
                + " WHERE VID = '" + vin + "'");
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

     public boolean updateVehicle(String vin, long miles, String name) {
         try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = new String("UPDATE VEHICLE "
                + "SET "
                + "MILEAGE = " + miles + ","
                + "VNAME = '" + name + "'"
                + " WHERE VID = '" + vin + "'");
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

    public boolean removeVehicle(String vin) {
        try {
            Class.forName(DBDriver);
            con = DriverManager.getConnection(DBurl);
            stmt = con.createStatement();
            String query = "DELETE FROM VEHICLE WHERE VID = '" + vin + "'";
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
        Vehicle vehicle = new Vehicle("asdfasd23423");
        System.out.println("Vehicle; " + vehicle.getName());
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {

        }
    }
}