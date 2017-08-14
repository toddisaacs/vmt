
//Title:        Vehicle Maintenance Tracker 1.1
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Todd E. Isaacs
//Company:      
//Description:  Track Maintence Records.
package vmt;

public class GasMileageRecord {

    private long gasMileageRecordId;
    private String vin;
    private long startOdometer;
    private long endOdometer;
    private double pricePerGal;
    private double units;

    public GasMileageRecord() {
    }

    //==========================================================================
    // Accessor Method(s)
    //==========================================================================
    public long getStartOdometer() {
        return startOdometer;
    }

    public long getEndOdometer() {
        return endOdometer;
    }

    public double getPricePerGal() {
        return pricePerGal;
    }

    public String getVin() {
        return vin;
    }

    public long getGasMileageId() {
        return gasMileageRecordId;
    }

    public double getUnits() {
        return units;
    }

    //==========================================================================
    // Mutator Method(s)
    //==========================================================================

    public void setStartOdometer(long start) {
        startOdometer = start;
    }

    public void setEndOdometer(long end) {
        endOdometer = end;
    }

    public void serPricePerGal(double pricePerGal) {
          this.pricePerGal = pricePerGal;
    }

    public void setVin(String aVin) {
        vin = aVin;
    }

    public void setGasMileageRecordId(long anId) {
        gasMileageRecordId = anId;
    }

    public void setUnits(double aUnit) {
        units = aUnit;
    }

    // Overrride toString() to display correctly in list

    public String toString() {
        return "" + startOdometer + " to " + endOdometer;
    }
}