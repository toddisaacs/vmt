//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:
//Description:  Vehicle
//==============================================================================
package vmt;

import java.util.*;

public class Vehicle {
    private String vin;
    private String name;
    private int year;
    private String make;
    private String model;
    private String color;
    private long mileage;
    private String imagePath;

    private Vector serviceRecords;

    //==========================================================================
    // Constructor(s)
    //==========================================================================
    public Vehicle() {
        //used to create new vehicle, initilize fields
        vin = new String("Vehicle ID");
        name = new String("Common Name");
        year = 1900;
        make = new String("");
        model = new String("");
        color =  new String("");
        mileage = 0;

    }

    public Vehicle(String vin) {
        Vehicle temp;
        VehicleDB vehicleDB = new VehicleDB();
        temp = vehicleDB.getData(vin);
        this.vin = temp.getVin();
        this.name = temp.getName();
        this.year = temp.getYear();
        this.make = temp.getMake();
        this.model = temp.getModel();
        this.color = temp.getColor();
        this.mileage = temp.getMileage();
        this.imagePath = temp.getImagePath();
    }

    //==========================================================================
    // Mutator Methods
    //==========================================================================
    public void setVin(String aVin) {
        vin = aVin;
    }

    public void setName(String aName) {
        name = aName;
    }

    public void setYear(int aYear) {
        year = aYear;
    }

    public void setMake(String aMake) {
        make = aMake;
    }

    public void setModel(String aModel) {
        model = aModel;
    }

    public void setColor(String aColor) {
        color = aColor;
    }

    public void setMileage(long aMileage) {
        mileage = aMileage;
    }

     public void setImagePath(String path) {
        imagePath = path;
    }

    //==========================================================================
    // Accessor Methods
    //==========================================================================
    public String getVin() {
        return vin;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public long getMileage() {
        return mileage;
    }

    public String getImagePath() {
        if (imagePath == null) {
          return "";
        } else {
          return imagePath;
        }
    }

    //==========================================================================
    // Misc Methods
    //==========================================================================
    public String toString() {
        return name;
    }

    //==========================================================================
    // Testing method
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