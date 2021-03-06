/**
 * This class handles all user interaction. 
 * @author Jeff Pittges
 * @version 29-MAY-2021
 */

import java.util.*;

public class TextIO {

  private Scanner scan;

  public TextIO() 
  {
    this.scan = new Scanner(System.in); 
  }

  public void display(String msg) 
  {
    System.out.println(msg);
  }

  public String prompt(String msg) 
  {
    this.display(msg); 
    return this.scan.nextLine(); 
  }
  
  public int[] calculateColumnWidths(String[][] results) {
	  int[] widths = new int[results[0].length];
	  
	  for (int r = 0; r < results.length; r++) {
		  for (int c = 0; c < results[r].length; c++) {
			  if (results[r][c].length() > widths[c]) {
				  widths[c] = results[r][c].length();
			  }
		  }
	  }
	  return widths;
  }
  
  public String formatReport(int[] widths, String[][] results, int pad) {
	  StringBuilder output = new StringBuilder();
	  for (int r = 0; r < results.length; r++) {
		  StringBuilder line = new StringBuilder();
		  for (int c = 0; c < widths.length; c++) {
			  line.append(String.format("%-" + (widths[c] + pad) + "s", results[r][c]));
			  
		  }
		  output.append(line + "\n");
	  }
	  return output.toString();
  }
  
}

