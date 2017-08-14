//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:      
//Description:  a GUI representation of the service types as a drop down.
//==============================================================================
package vmt;

import javax.swing.*;
import java.util.*;

public class ServiceTypeCombo extends JComboBox  {

    //datamodel
    private DefaultComboBoxModel dataModel = new DefaultComboBoxModel();
    ServiceTypeDB stDB = new ServiceTypeDB();

    public ServiceTypeCombo() {
        super();
        this.setModel(dataModel);
        this.setComboBoxDataList(stDB.getData());
    }

   //methods to manipulate datamodel
    public void addServiceType(Object aServiceType) {
        dataModel.addElement(aServiceType);
        //update combo box
        this.setComboBoxDataList(stDB.getData());
    }

    public void updateServiceType(String sType, int id) {
        stDB.updateServiceType(sType, id);
    }

    public int getServiceTypeID() {
        ServiceType st = (ServiceType)dataModel.getSelectedItem();
        return st.getServiceTypeId();
    }

    public void removeServiceType(Object aServiceType) {
        dataModel.removeElement(aServiceType);
    }

    public void setComboBoxDataList(Vector aVector) {
        dataModel.removeAllElements();
        Enumeration e = aVector.elements();
        while (e.hasMoreElements()) {
            dataModel.addElement(e.nextElement());
        }
    }
    
    public void setServiceType(int anIndex) {
       int size = dataModel.getSize();
       ServiceType st;
       for ( int i = 0; i < size; i++ ) {
            st = (ServiceType)dataModel.getElementAt(i);
            if ( st.getServiceTypeId() == anIndex ) {
                //Match found, set this as selected item
                dataModel.setSelectedItem(st);
            }
       }
    }
}
