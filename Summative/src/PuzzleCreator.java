import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PuzzleCreator {
	public static Puzzle createPuzzle(int[][] tileStates) { //Create a puzzle based on tile states
	
		
		//Copy the tile states into a 2D array and count the number of filled tiles
		int[][] states = new int[tileStates.length][tileStates[0].length];
		
		int count = 0;
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < states[0].length; j++) {
				states[i][j] = tileStates[i][j];
				if(states[i][j] == 1) {
					count++;
				}
			}
		}

	
		//Generate the labels
		String[] colLabels = new String[states[0].length];
		String[] rowLabels = new String[states.length];

		for (int r = 0; r < rowLabels.length; r++) {
			rowLabels[r] = PuzzleGenerator.genRowLabel(states[r]).strip();

		}

		for (int c = 0; c < colLabels.length; c++) {
			colLabels[c] = PuzzleGenerator.genColLabel(PuzzleEvaluator.getColumn(states, c)).strip();
		}

		//Create the output puzzle, load all the attributes and return it
		Puzzle output = new Puzzle(rowLabels.length, colLabels.length);
		output.setRowLabels(rowLabels);
		output.setColLabels(colLabels);
		output.setTileCount(count);
		output.setTiles(states);

		return output;

	}

	public static Puzzle loadPuzzle(Component parent) { //Returns a puzzle from a user-chosen text file
	
		
		JFileChooser fileChooser = new JFileChooser();
		//Allow a filter for text documents
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents", "txt"));

		//Display the file chooser dialog
		int result = fileChooser.showOpenDialog(parent);
		
		if (result == JFileChooser.APPROVE_OPTION) { //If the user selected a file
		

			File file = fileChooser.getSelectedFile();
			
			try {
				Scanner s = new Scanner(file);
			
				//Get the row count and the row labels from the file
				int rowCount = Integer.parseInt(s.nextLine());
				
				String[] rowLabels = new String[rowCount];
				for (int i = 0; i < rowCount; i++) {
					
					rowLabels[i] = s.nextLine();

				}
				
				//Get the column count and the column labels from the file
				int colCount = Integer.parseInt(s.nextLine());
				
				
				String[] colLabels = new String[colCount];
				
				for (int i = 0; i < colCount; i++) {

					String input = s.nextLine();
					
					String output = "";
					
					//Replace the spaces in the file with new lines 
					for (int c = 0; c < input.length(); c++) {
						if (input.charAt(c) == ' ') {

							output += '\n';
						} else {
							output += input.charAt(c);
						}
					}

					colLabels[i] = output;
				}
				
				//Get the tile count
				int tileCount = Integer.parseInt(s.nextLine());

				//Make the output puzzle, set its attributes, and return it
				Puzzle puzzle = new Puzzle(rowCount, colCount);
				puzzle.setRowLabels(rowLabels);
				puzzle.setColLabels(colLabels);
				puzzle.setTileCount(tileCount);
				return puzzle;

			} catch (FileNotFoundException e) {
				DialogGenerator.genCustomError("No file has been selected.", 16);
				return null;
			} catch (NoSuchElementException e) {
				DialogGenerator.genCustomError("The file has improper formatting and cannot be read.", 16);
				return null;
			} catch (NumberFormatException e) {
				DialogGenerator.genCustomError("The file has improper formatting and cannot be read.", 16);
				return null;
			}
			
			
		} else {
			return null;
		}
	}
	
	public static boolean isLabelsEmpty(String[] rowLabels, String[] colLabels) { //Check if the labels are empty
		for(String label : rowLabels) {
			if (label == null || label.strip().length() == 0) {
				return true;
			}
		}
		
		for(String label : colLabels) {
			if (label == null || label.strip().length() == 0) {
	
				return true;
			}
		}
		
		return false;
		
	}

	public static void writePuzzle(Component parent, CreatorGrid grid) { //Write a puzzle into a text file
		
		//Get the relevant data from the creator grid
		String[] rowLabels = grid.getRowLabels();
		String[] colLabels = grid.getColLabels();
		int tileCount = grid.getTileCount();
		
	
		
		if(isLabelsEmpty(rowLabels, colLabels)) { //If the labels are empty, show an error dialog
			DialogGenerator.genCustomError("The labels must be created before the puzzle can be downloaded.", 16);
			return;
		}

		
		try {
			
			//Write into the file "puzzle.txt"
			PrintWriter p = new PrintWriter("puzzle.txt", "UTF-8");
			
			//Write the number of rows and the row labels
			p.println(rowLabels.length);
			for (String lbl : rowLabels) {
				p.println(lbl);
			}
			
			//Write the number of columns and column labels
			p.println(colLabels.length);
			for (String lbl : colLabels) {
				for (int i = 0; i < lbl.length(); i++) { //Replace all the new line characters with spaces to avoid ambiguity when decoding
					if (lbl.charAt(i) == '\n') {
						p.print(" ");
					} else {
						p.print(lbl.charAt(i));

					}
				}
				p.println();

			}
		
			//Print the tile count
			p.println(tileCount);
			p.close();

			//Show a successful dialog message
			DialogGenerator.genSuccessfulDownload();
		} catch (FileNotFoundException e) {
			DialogGenerator.genCustomError("Something went wrong. Please try again.", 16);
		} catch (UnsupportedEncodingException e) {
			DialogGenerator.genCustomError("Something went wrong. Please try again.", 16);
			
		}

	}
}
