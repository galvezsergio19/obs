package com.smg.utility;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Property {
   
    public static Properties getProperties(String propFileName) throws SQLException {
    	Properties props = null;
    	
    	InputStream is = Property.class.getResourceAsStream("/" + propFileName);
    	if (is == null) {
    		throw new SQLException("Unable to load property file: " + propFileName);
    	}
    	
    	if(props == null) {
    		props = new Properties();
    		try {
    			props.load(is);
    		} catch (IOException e) {
    			throw new SQLException("Unable to load property file: " + propFileName + "\n" + e.getMessage());
    		}
    	}
    	return props;
    }

    /***********************************************************************/
    // CONFIGURATION PROPERTIES FILE
    /***********************************************************************/
    
    public static String getConfig(String config) throws SQLException{
    	return getProperties("config.properties").getProperty(config);
    }
    
    /***********************************************************************/
    // QUERY PROPERTIES FILE
    /***********************************************************************/

    public static String getQuery(String query) throws SQLException{
    	return getProperties("queries.properties").getProperty(query);
    }
    
    /***********************************************************************/
    // ERRORS PROPERTIES FILE
    /***********************************************************************/

    public static String getError(String errorcode) throws SQLException{
    	return getProperties("error.properties").getProperty(errorcode);
    }
    
    /***********************************************************************/
    // WEB PAGES LOCATION FILE
    /***********************************************************************/

    public static String getPage(String page) throws SQLException{
    	return getProperties("pages.properties").getProperty(page);
    }
    
}

