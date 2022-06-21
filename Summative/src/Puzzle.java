
public class Puzzle {
	private int numRows;
	private int numCols;
	
	private int[][] tileStates;
	
	private String[] colLabels;
	private String[] rowLabels;
	
	private int maxColLabelSize = 2;
	private int maxRowLabelSize = 3;
	
	private int tileCount = 0;
	
	
	public Puzzle(int rows, int columns) {
		numRows = rows;
		numCols = columns;
		
		tileStates = new int[rows][columns];
		
		colLabels = new String[columns];
		rowLabels = new String[rows];
		
	}
	

	public void setColLabels(String[] labels) { //Set the column labels and update the maximum column label size
		colLabels = labels;
		
		maxColLabelSize = getMaxColLabelSize(labels);
	}
	
	public void setRowLabels(String[] labels) { //Set the row labels and update the maximum row label size
		rowLabels = labels;
		
		maxRowLabelSize = getMaxRowLabelSize(labels);
	}
	
	public static int getMaxRowLabelSize(String[] labels) { //Determines the maximum size of a list of row labels
		int maxRowLabelSize = 3; 
		
		for(String lbl: labels) {
			maxRowLabelSize = Math.max(maxRowLabelSize, lbl.length());
		}
		
		return maxRowLabelSize;
		
	}
	
	public static int getMaxColLabelSize(String[] labels) { //Determines the maximum size of a list of column labels
		int maxColLabelSize = 2;
		
		for(String lbl : labels) {
			maxColLabelSize = Math.max(maxColLabelSize, findVertLength(lbl));
		}
		
		
		return maxColLabelSize;
		
	}
	
	//ACCESSOR METHODS
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumCols() {
		return numCols;
	}
	
	public int[][] getTiles() {
		return tileStates;
	}
	
	public String[] getColLabels() {
		return colLabels;
		
	}
	
	public String[] getRowLabels() {
		return rowLabels;
	}
	public int getMaxRowLabelSize() {
		return maxRowLabelSize;
	}
	
	public int getMaxColLabelSize() {
		return maxColLabelSize;
	}
	
	public int getTileCount() {
		return tileCount;
		
	}
	
	public void setTileCount(int n) {
		tileCount = n;
		
	}
	
	
	public void setTiles(int[][] tiles) {
		this.tileStates = tiles;
		
	}
	
	public static int findVertLength(String str) { //Determines the vertical size of a column label (number of lines)
		//Check for empty or null label cases
		if(str == null || str.length() == 0) return 0;
	
		//Count the number of new line characters in the array
		int i = 1;
		for(char c : str.toCharArray()) {
			if(c == '\n') {
				i++;
			}
		}
		
		return i;
		
	}
	
	
	
	
	
}
