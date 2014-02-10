package dusty;


import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import dusty.DBResults;

@ManagedBean     //(eager=true)
@SessionScoped
public class DBUtils {
		
	//@Resource(name="jdbc/fbResource")
	//private static DataSource ds;

	public static String DB_NAME="dusty";
	public static String DB_USER="root";
	public static String DB_PW="xnynzn987";
	public static String DB_URL="jdbc:mysql://localhost:3306/";
	public static String DB_DRIVER="com.mysql.jdbc.Driver";
	
	
	
	public static Connection getConnection(){
		  Connection conn = null;
		      try {
			        Class.forName(DB_DRIVER).newInstance();
			        conn = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PW);
System.out.println("41 DBU Connecting to the database");
	           }catch (Exception e) {
		             e.printStackTrace();
		      }
	           
	    return conn;       
		
	}

	
	public static void closeConnection(Connection conn){
		try{
			conn.close();
System.out.println("40 DBUtil Disconnected from database");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
    
	public static DBResults selectSQL (String strQuery) {
System.out.println("49  DBUtil strQuery="+strQuery);

   		Connection conn = null;
		      DBResults dbRs = new DBResults();
System.out.println("58  DBUtil strQuery="+strQuery);

		      try {
			        Class.forName(DB_DRIVER).newInstance();
			        conn = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PW);
System.out.println("63 DBUtils Connecting to the database");
			        Statement st=null;
				    ResultSet rs=null;
				    st = conn.createStatement();
			        rs = st.executeQuery(strQuery);
			    	
			        int columns = rs.getMetaData().getColumnCount();
			        
			        while(rs.next()){
System.out.println("72  DBUtil strQuery="+strQuery);
			        	
			    		ArrayList<String> row = new ArrayList<String>();
			    		for(int i = 1; i <= columns; i++){

			    			row.add(rs.getString(i));
			    		}
			    		dbRs.addRow(row);
			    	}
			        
			        rs.close();
			        conn.close();
		           }catch (Exception e) {
		             e.printStackTrace();
		      }

		      return dbRs;    
	}
			    		
	
	
	public static void executeSQL(String strSQL){ //delete or update
		  
		  Connection conn = null;
System.out.println("120 DBUtil executeSQL");
	
			try {
				Class.forName(DB_DRIVER).newInstance();
				conn = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PW);
System.out.println("  93 DBUtils Connecting to the database");
      			Statement st=null;
      			st = conn.createStatement();
      			st.executeUpdate(strSQL);
      			conn.close();
     System.out.println("Disconnected from database");
      
				}catch (Exception e) {
					e.printStackTrace();
				}

	}
	
	
	
	
	public static DBResults insertSQL(String strSQL){
		
System.out.println("140 DBUtils: "+strSQL);
		  DBResults dbRs = new DBResults();
		  Connection conn = null;

		  try {
				conn = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PW);
System.out.println("146  DBUtil Connecting ....");
			    Statement st=null;
			    ResultSet rs=null;
			    st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                            				ResultSet.CONCUR_UPDATABLE);
			   st.executeUpdate(strSQL,Statement.RETURN_GENERATED_KEYS);
			   rs = st.executeQuery("SELECT LAST_INSERT_ID()");

			   if (rs.next()) {
				   ArrayList<String> row = new ArrayList<String>();
				   row.add(rs.getString(1));
				   dbRs.addRow(row);
			   	    } 
			    rs.close();
			        
			    conn.close();
System.out.println("162 DBUtils Disconnected from database");
			        
		        }catch (Exception e) 
		        {
		             e.printStackTrace();
		        }
		  	return dbRs;     
		         
	}
	
}

