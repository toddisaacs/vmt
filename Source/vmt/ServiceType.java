//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:      
//Description:  Track maintenance records for multiple vehicles
//==============================================================================
package vmt;

import java.util.*;

public class ServiceType {

    private int serviceTypeId;
    private String descr;

    //==========================================================================
    // Constructor(s)
    //==========================================================================
    public ServiceType() {}

    public ServiceType(String name) {
      descr = name;
    }

    //==========================================================================
    // Accessor Methods
    //==========================================================================
    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public String getDescr() {
        return descr;
    }

    //==========================================================================
    // Mutator Method(s)
    //==========================================================================
    /**
    * Sets the Service Type ID
    *
    * @param aServiceTypeId
    */
    public void setServiceTypeId(int aServiceTypeId) {
        serviceTypeId = aServiceTypeId;
    }

    /**
    * Sets the Description of the type of service.  This also use in the display
    * in the combobox.
    *
    * @param aDescr String description of the service type
    */
    public void setDescr(String aDescr) {
        descr = aDescr;
    }
    //==========================================================================
    // Misc. Method(s)
    //==========================================================================
    //override method to print correctly in combobox
    public String toString() {
        return descr;
    }

    //==========================================================================
    // Test Method(s)
    //==========================================================================
    public static void main(String[] args) {
        ServiceType serviceType = new ServiceType();
    }
}