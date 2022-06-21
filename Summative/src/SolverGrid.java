
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class SolverGrid extends Grid{

	public SolverGrid(JPanel padderPanel) { 
		// Construct a normal grid, allow labels to be editable, and set the grid size to its 5x5 default
		super(padderPanel);
		isLabelsEditable = true;
		
		setGridSize(5, 5);
	}
	
	public void setGridSize(int rows, int cols) { //Set the grid size to a specific size
		super.setGridSize(rows, cols);
		
		//Set the column label size (row labels have a minimum size of 2)
		maxColLabelSize = 3;
		maxRowLabelSize = Math.max(cols, 2);
		
	}
	
	
	public void clearGrid() { //Set all the tiles to white and empty all the labels
		super.clearGrid();
		
		for(JTextPane label: colLabelField) {
			label.setText("");
		}
		for(JTextField label: rowLabelField) {
			label.setText("");
		}
		
	}
	
	public void rightMouseButtonPressEvent(Tile tile) {// Runs when a tile is right clicked 
		if (tile.getState() == -1) {
			tile.setState(0);
			
		} else  {
			tile.setState(-1);
		} 
		
		tileStates[tile.getRow()][tile.getColumn()] = tile.getState();
		
	}
	
	public void leftMouseButtonPressEvent(Tile tile) { //Runs when a tile is left clicked
		if (tile.getState() == 1) {
			tile.setState(0);
		} else {
			tile.setState(1);
		}
		
		tileStates[tile.getRow()][tile.getColumn()] = tile.getState();
	}
	
	public void mouseButtonPressEvent() {
	}
	
	public void drawSolution(int[][] solution) { //Display an arrangement of tiles on the grid
		
		for(int i = 0; i < solution.length; i++) {
			for(int j = 0; j < solution[0].length; j++) {
				
				tiles[i][j].setState((solution[i][j] == 1) ? 1 : 0);
				tileStates[i][j] = tiles[i][j].getState();
				
			}
		}
	}
	
}
