//==============================================================================
//Title:        Vehicle Maintenance Tracker
//Version:
//Copyright:    Copyright (c) 2001
//Author:       Todd Isaacs
//Company:
//Description:  Main DB class all DB objects will be extended from
//==============================================================================
package vmt;

import java.sql.*;


public abstract class CloudscapeDB {

    // To DO make this read from a properties file
    protected Connection con;
    protected String DBurl = "jdbc:cloudscape:CloudscapeDB;create=False";
    protected String DBDriver = "COM.cloudscape.core.JDBCDriver";

    //==========================================================================
    //Constructor(s)
    //==========================================================================
    public CloudscapeDB() {

    }
}
