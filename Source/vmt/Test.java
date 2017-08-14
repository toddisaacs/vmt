package vmt;

import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Test {
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  public Test() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    Test test1 = new Test();
  }
  private void jbInit() throws Exception {
    jLabel1.setText("jLabel1");
    jPanel1.setDebugGraphicsOptions(0);
    jPanel1.setLayout(xYLayout1);
    jPanel1.add(jLabel1, new XYConstraints(0, 0, -1, -1));
  }

}