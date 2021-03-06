import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class DataSource {
	
	private static DataSource obj;
	private Connection conn;
	
	private DataSource() throws SQLException {
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());

	    String user, pass;
	    user = "thuffman14";
	    pass = "Skater81215";
	    try {
	        conn = DriverManager.getConnection("jdbc:oracle:thin:@Worf.radford.edu:1521:itec3", user, pass);	        
	    }
	    catch (SQLException e){
	    	System.out.println ("Could not load the db" + e);
	    }
	};

	public static DataSource getInstance() throws SQLException {
		if (obj == null) {
			obj = new DataSource();
		}
		return obj;
	}
	
	public MedicationDAO getSummary(String query) {
		ArrayList<String> name = new ArrayList<>();
		ArrayList<String> NDC = new ArrayList<>();
		ArrayList<String> FormType = new ArrayList<>();
		ArrayList<Integer> FormFactor = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
	        ResultSet rset = stmt.executeQuery(query);
	        rset.absolute(0);
	        while (rset.next()) { 
	        	
	            name.add(rset.getString("Name"));
	            NDC.add(rset.getString("NDC"));
	            FormType.add(rset.getString("FormType"));
	            FormFactor.add(rset.getInt("FormFactor"));
	        }
		}
		catch (SQLException e) {
			System.out.println("Error querying the db " + e);
		}

		return new MedicationDAO(name, NDC, FormType, FormFactor);
	}
	
	
	public String[][] executeQuery(String query) {
		String[][] results = null;
		
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
	        ResultSet rset = stmt.executeQuery(query);
	        
	        rset.last();
	        int rows = rset.getRow();
	        rset.beforeFirst();
	        
	        ResultSetMetaData tableData = rset.getMetaData();
	        int columns = tableData.getColumnCount();
	        results = new String[rows+1][columns];
	        
	        for (int i = 1; i <= columns; i++) {
	        	results[0][i-1] = tableData.getColumnLabel(i);
	        }
	        int r = 1;
	        while (rset.next()) {
	        	for (int c = 1; c <= columns; c++) {
	        		results[r][c-1] = rset.getString(c);
	        	}
	        	r++;
	        }
	        
		} 
		catch (SQLException e) {
			System.out.println("Error querying the db " + e);
		}
		
        return results;
	}
	
	public void close() {
		try {
			conn.close();
		}
		catch (SQLException e) {
			System.out.println("Error closing connection" + e);
		}
	}
}
