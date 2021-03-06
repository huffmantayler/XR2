import java.util.ArrayList;

public class MedicationDAO {
	
	private ArrayList<String> name;
	private ArrayList<String> NDC;
	private ArrayList<String> FormType;
	private ArrayList<Integer> FormFactor;
	private ArrayList<Integer> counts;
	
	public MedicationDAO(ArrayList<String> name, ArrayList<String> NDC, ArrayList<String> FormType, ArrayList<Integer> FormFactor) {
		this.name = name;
		this.NDC = NDC;
		this.FormType = FormType;
		this.FormFactor = FormFactor;
	}
	
	public int getRowCount() {
		return name.size();
	}
	
	public String[] getRow(int i) {
		String[] row = new String[4];
		row[0] = name.get(i);
		row[1] = NDC.get(i).toString();
		row[2] = FormType.get(i).toString();
		row[3] = FormFactor.get(i).toString();
		return row;
	}
	
	public String[] getFirstRow() {
		String[] columns = new String[4];
		for (int i = 0; i < 3; i++) {
			columns[i] = name.get(i);
		}
		return columns;
	}
	

	
	private Integer getTotalCount() {
		int total = 0;
		for (int count: counts) {
			total += count;
		}
		return total;
	}
	
}
