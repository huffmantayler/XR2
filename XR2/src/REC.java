import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class REC {
	
	private DataSource DBconn;
	private TextIO textIO;
	
	public REC() {
		try {
			DBconn = DataSource.getInstance();
		} 
		catch (SQLException e) {
			System.out.println("Error connecting to DB " + e);
		}
		textIO = new TextIO();
	}
	
	public void run() throws SQLException {
		String command = "";
		while (!(command = textIO.prompt("Enter Command:")).equalsIgnoreCase("quit")) {
			if (command.equalsIgnoreCase("help")) {
				textIO.display("help - display list of commands\n"
						+ "quit - close program\n"
						+ "drugs - get report"
						+ "insert - insert data");
			}
			else if (command.startsWith("drugs")) {
					String query = String.format("SELECT name, NDC, FormType, FormFactor" 
							+ " FROM Medications ");
					
					MedicationDAO DAO = DBconn.getSummary(query);
					
					String[][] result = new String[DAO.getRowCount()][4];
					int i = 0;
					
					for (int r = 0; r < DAO.getRowCount(); r++) {
						result[i] = DAO.getRow(r);
						i++;
					}			
					textIO.display(textIO.formatReport(textIO.calculateColumnWidths(result), result, 5));
				
							
			}
			
			else {
				textIO.display("Invalid arguments for command rpt, use command \"help\" for usages.");
			}
		}
		DBconn.close();
	}
	
}


