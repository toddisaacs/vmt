package vmt;

import javax.swing.*;
import java.util.*;

class Vmanager extends Observable {

   static DefaultComboBoxModel dataModel = new DefaultComboBoxModel();

   public void update() {
      setChanged();
      notifyObservers();
   }

   public void addVehicles(Vector aVector) {
        Enumeration e = aVector.elements();
        while (e.hasMoreElements()) {
            dataModel.addElement(e.nextElement());
        }
        update();
    }

} 