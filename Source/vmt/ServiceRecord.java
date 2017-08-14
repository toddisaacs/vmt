//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:      
//Description:  Maintenance record
//==============================================================================
package vmt;

import java.util.*;
import java.text.*;

public class ServiceRecord {

    private long serviceRecordId;
    private String vin;
    private int serviceTypeId;
    private Date serviceDate;
    private boolean isMaintenance;
    private long mileage;
    private String notes;
    private int warPartlistId;
    private int serviceCenterId;
    private double price;

    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    //==========================================================================
    // Constructor(s)
    //==========================================================================
    public ServiceRecord() {
    }

    //==========================================================================
    // Accessor Methods
    //==========================================================================
    public long getServiceRecordId() {
        return serviceRecordId;
    }

    public String getVin() {
        return vin;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public String getServiceDateString() {
        return DATE_FORMAT.format(serviceDate);
    }

    public boolean isMaintenance() {
        return isMaintenance;
    }

    public long getMileage() {
        return mileage;
    }

    public String getNotes() {
        return notes;
    }

    public double getPrice() {
        return price;
    }

    //==========================================================================
    // Mutator Methods
    //==========================================================================
    /**
    * Sets the service record ID.
    *
    * @param aServiceRecordId a long that is the key of the service reocrd from DB.
    */
    public void setServiceRecordId(long aServiceRecordId) {
        serviceRecordId = aServiceRecordId;
    }

    /**
    * Sets the vehicle identification number
    *
    * @param aVin a String represents the VIN.
    */
    public void setVin(String aVin) {
        vin = aVin;
    }

    /**
    * Sets the service type ID.
    *
    * @param aServiceTypeId This int corresponds to the service type iD key in DB.
    */
    public void setServiceTypeId(int aServiceTypeId) {
        serviceTypeId = aServiceTypeId;
    }

    /**
    * Sets the service date
    *
    * @param aServiceDate Date of service as a String in MM/dd/yyyy format.
    */
    public void setServiceDate(String aServiceDate) {
        try {
			serviceDate = DATE_FORMAT.parse(aServiceDate);
		}
		catch (ParseException e) {
			System.out.println("parse exeption. Date not in correct format "
                + DATE_FORMAT.toPattern()
                + "Date: " + aServiceDate);
		}
    }

    /**
    * Sets the Maintenance flag
    *
    * @param aFlag A flag indicating this record is a maintenance record
    */
    public void setIsMaintenance(boolean aFlag) {
        isMaintenance = aFlag;
    }

    /**
    * Sets the Odometer/Milage
    *
    * @param aMileage
    */
    public void setMileage(long aMileage) {
        mileage = aMileage;
    }

    /**
    * Sets the Odometer/Milage
    *
    * @param aNote The notes for the service record
    */
    public void setNotes(String aNote) {
        notes = aNote;
    }

    /**
    * Sets the price
    *
    * @param aPrice Total service Price
    */
    public void setPrice(double aPrice) {
       price = aPrice;
    }

    //==========================================================================
    // Misc Method(s)
    //==========================================================================
    // override method to print correct info in JList
    public String toString() {
         return new String(DATE_FORMAT.format(serviceDate) + "        " + mileage);
    }

   //===========================================================================
   // Test Methods
   //===========================================================================
    public static void main(String[] args) {
        ServiceRecord serviceRecord = new ServiceRecord();
    }
} 