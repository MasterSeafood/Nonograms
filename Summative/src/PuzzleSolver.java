import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.JTextPane;

public class PuzzleSolver {
	
	
	//A method that returns a solution to a puzzle given the labels and any known tile states
	public static int[][] solvePuzzle(String[] colLabels, String[] rowLabels, int[][] input) {
		
		//Create a 2D array for tile states and copy the known tile states over
		int[][] tileStates = new int[input.length][input[0].length];
	
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[0].length; j++) {
				tileStates[i][j] = input[i][j];
			}
		}
		
		//Set the current loop limit to the number of labels 
		int limit = colLabels.length + rowLabels.length;

		//Used to count the number of rows/columns checked since the last time progress has been made
		int numChecksSinceLatestProgress = 0;


		while (numChecksSinceLatestProgress <= limit) { //While the number of checks is less than the limit

			for (int c = 0; c < colLabels.length; c++) { //Loop through the columns
				
				//Convert the column label into an array of stretch sizes
				int[] labelNums = colLabelToIntArr(colLabels[c]);
				
				//Get an array of known tile states
				int[] req = PuzzleEvaluator.getColumn(tileStates, c);

				//Generate a list of all the possible arrangement of stretches given the label and the number of rows
				int[][] allPoss = genAllPoss(labelNums, rowLabels.length);

				//Filter the list with the known tile states
				int[][] filteredPoss = filterPoss(allPoss, req);

				
				if (filteredPoss.length == 0) { //If there is nothing in the final list
					// Puzzle cannot be solved
					// Return an error output
					int[][] output = new int[1][1];
					output[0][0] = Integer.MAX_VALUE;
					return output;
					
				} else if (filteredPoss.length == 1) { //If there is only one possibility
					
					//If the absolute solution is different than the current known tile state, add it to the known tile state grid and reset checks
					if(!areTheSame(getAbsoluteSolution(filteredPoss[0]), req)) {
						putColumn(tileStates, c, getAbsoluteSolution(filteredPoss[0]));
						numChecksSinceLatestProgress = 0;
						
					}
					
				} else { //If there are multiple possibilities
					//Find the tile states that are common to all the possibilities
					int[] commonSolution = findCommonTiles(filteredPoss);
					// If the common tile states are not the same as the currently known states 
					if (!areTheSame(commonSolution, req)) {
						//Add the common tile states to the known tile states and reset checks
						numChecksSinceLatestProgress = 0;
						putColumn(tileStates, c, commonSolution);
					} else {
						//Otherwise increment the number of checks 
						numChecksSinceLatestProgress++;
						continue;
					}

					

				}

				//If the puzzle has been solved, return the known tile states 
				if (PuzzleEvaluator.evaluate(tileStates, rowLabels, colLabels)) {
					return tileStates;
				}
				
			}

			
			for (int r = 0; r < rowLabels.length; r++) { //Loop through the rows

				//Convert the row label to an array of stretch lengths
				int[] labelNums = rowLabelToIntArr(rowLabels[r]);

				//Get this row's known tile states
				int[] req = tileStates[r];

				//Find all possibilities of this row's tile states given the stretch lengths and the number of columns
				int[][] allPoss = genAllPoss(labelNums, colLabels.length);

				//Filter all possibilities that match the known tile states
				int[][] filteredPoss = filterPoss(allPoss, req);

				if (filteredPoss.length == 0) { //If there are no possibilities left, return an error output
					//Cannot solve puzzle
					int[][] output = new int[1][1];
					output[0][0] = Integer.MAX_VALUE;
					return output;
				} else if (filteredPoss.length == 1) { //If there is one possibility
					
					
					//If the absolute solution is different than the current known tile states, add it to the known tile states
					if(!areTheSame(getAbsoluteSolution(filteredPoss[0]), req)) {
						tileStates[r] = getAbsoluteSolution(filteredPoss[0]);
						numChecksSinceLatestProgress = 0;
						
					}
				} else { //If there are multiple possibilities 
					
					//Find the common tile states between all the possibilities
					int[] commonSolution = findCommonTiles(filteredPoss);

					//If the common tile states is different than the current known tile states
					if (!areTheSame(commonSolution, req)) {
						//Add it to the tile states grid
						tileStates[r] = commonSolution;
						numChecksSinceLatestProgress = 0;
					} else { //Otherwise increment the check count
						numChecksSinceLatestProgress++;
						continue;
					}

				}
				
				//If the puzzle is fully solved, return the current tile states
				if (PuzzleEvaluator.evaluate(tileStates, rowLabels, colLabels)) {
					return tileStates;
				}

			}
		}

		//If the loop quit, then the program timed out. Return an error output
		int[][] output = new int[1][1];
		output[0][0] = Integer.MIN_VALUE;
		return output;
		


	}

	public static String[] colLabelsToStringArr(JTextPane[] colLabelFields) { //Convert column label fields into column labels

		String[] outputLabels = new String[colLabelFields.length];

		//For each label field
		for (int p = 0; p < colLabelFields.length; p++) {
			//Get the input label
			String labelIn = colLabelFields[p].getText().strip();
			String labelOut = "";
			
			//For each character in the input label
			for (int i = 0; i < labelIn.length(); i++) {
				char c = labelIn.charAt(i);
				
				//If it is a digit OR a new line character after a digit then add it to the output label
				if ((c >= '0' && c <= '9') || (c == '\n' && labelOut.charAt(labelOut.length()-1) != '\n') ) { 
					labelOut += c;
				} 

			}

			//Add the output label to the array
			outputLabels[p] = labelOut;

		}

		return outputLabels;

	}
	public static String[] rowLabelsToStringArr(JTextField[] rowLabelFields) { //Convert row label fields into row labels

		String[] outputLabels = new String[rowLabelFields.length];

		//For each label field
		for (int p = 0; p < rowLabelFields.length; p++) {
			//Get the input label
			String labelIn = rowLabelFields[p].getText().strip();
			String labelOut = "";

			//For each character in the input label
			for (int i = 0; i < labelIn.length(); i++) {
				char c = labelIn.charAt(i);
				//If it is a digit OR a new line character after a digit then add it to the output label
				if ((c >= '0' && c <= '9') || (c == ' ' && labelOut.charAt(labelOut.length()-1) != ' ')) {
					labelOut += c;
				} 

			}

			//Add the output label to the array
			outputLabels[p] = labelOut;

		}

		return outputLabels;

	}
	


	public static int[] getAbsoluteSolution(int[] sol) { //Convert a row/column of tile states (1 and 0) into an absolute solution (only 1 and -1)
		int[] output = new int[sol.length];

		for (int i = 0; i < sol.length; i++) {
			output[i] = (sol[i] == 1) ? 1 : -1;
		}
		return output;
	}

	public static boolean areTheSame(int[] a, int[] b) { //Return whether two arrays of the same length are identical
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}

		return true;
	}

	public static void putColumn(int[][] tileStates, int c, int[] column) { //Put a column of states in a specific column of a 2D array
		for (int i = 0; i < column.length; i++) {
			tileStates[i][c] = column[i];
		}
	}

	public static int[] findCommonTiles(int[][] poss) { //Determine an row/column of all common tiles states in a list of possibilities
		//Make a new boolean array with the length of the row/column
		boolean[] mask = new boolean[poss[0].length];

		
		//All are set to true by default
		for (int i = 0; i < mask.length; i++) {
			mask[i] = true;
		}

		//Let the first possibility be the reference 
		int[] ref = poss[0];

		for (int i = 1; i < poss.length; i++) {
			//Loop through all other possibilities
			int[] comp = poss[i];

			
			for (int j = 0; j < ref.length; j++) {
				if (ref[j] != comp[j]) { //If the possibility doesn't match the reference at a specific index, set the mask to false at that index
					mask[j] = false;
				}
			}

		}

		//Make the output array; all set the 0 by default
		int[] output = new int[mask.length];

		
		for (int i = 0; i < ref.length; i++) {

			//If the mask is true at that index (then all possibilities share the same state as the reference)
			if (mask[i]) {

				//Set the tile state to absolutely filled or unfilled 
				output[i] = (ref[i] == 1) ? 1 : -1;
			}

		}

		return output;

	}

	public static int[][] filterPoss(int[][] allPoss, int[] req) { //Return a list of all possibilities that match a filter  

		ArrayList<int[]> filteredPoss = new ArrayList<int[]>();
		
		possLoop: for (int[] poss : allPoss) { //Loop through each possibility
			for (int i = 0; i < poss.length; i++) { //Loop through each state in each possibility
				int reqState = req[i];
				int possState = poss[i];
			
				//if the possibility state conflicts with the required state, skip this possibility
				if ((reqState == -1 && possState == 1) || (reqState == 1 && possState == 0)) {
					continue possLoop;

				}

			}

			//Add to the arraylist if there is no conflict
			filteredPoss.add(poss);

		}


		//Convert the arraylist of possibilities into an array of possibilities
		int[][] output = new int[filteredPoss.size()][req.length];

		for (int i = 0; i < filteredPoss.size(); i++) {
			output[i] = filteredPoss.get(i);
		}

		return output;

	}

	public static int[][] genAllPoss(int[] nums, int size) {
		// Given a label and a size, it will generate all the possible combinations of
		// tile states that satisfy the label and size requirement
		
		

		int numBlackTiles = 0;

		for (int n : nums) {
			numBlackTiles += n;
		}

		int numGaps = nums.length + 1;

		int numWhiteTiles = size - numBlackTiles - (nums.length - 1);
		
		//Get number of filled tiles, empty tiles used for variability, and gaps (places for empty tile variability)

		//Essentially finding all possibilities of the equation a + b + c + d + ... = C ; a,b,c,... >= 0 ; C is a positive integer 
		//The total number of variables is the number of gaps
		//C is the number of empty tiles used for variability (number of total empty tiles - number of gaps between black tile stretches since they have a minimum of 1)

		//Used to store all empty tile stretch length variations (solutions to the equation above)
		ArrayList<ArrayList<Integer>> whiteSpaceCombos = new ArrayList<ArrayList<Integer>>();

		//Generate and store all possible variations
		recursGenPoss(whiteSpaceCombos, new ArrayList<Integer>(), numWhiteTiles, numGaps, numGaps);


		//Used to store all possibilities
		ArrayList<ArrayList<Integer>> poss = new ArrayList<ArrayList<Integer>>();

		for (ArrayList<Integer> whiteCombo : whiteSpaceCombos) { //For each white tile stretch combo

			ArrayList<Integer> fullCombo = new ArrayList<>(); 
			
			for (int i = 0; i < whiteCombo.size(); i++) { //for each stretch of white tiles
				if (i != 0 && i != whiteCombo.size() - 1) { //if it is not on the edges of the grid, add 0

					fullCombo.add(0);
				}
				for (int j = 0; j < whiteCombo.get(i); j++) { //Add 0s based on the length of the white tile stretch
					fullCombo.add(0);
				}

				if (i != whiteCombo.size() - 1) { //Add 1s based on the length of the black tile stretch after the white stretch (if it is not the last white stretch)
					for (int j = 0; j < nums[i]; j++) {
						fullCombo.add(1);
					}

				}

			}

			//Add the combo to the list of possibilities 
			poss.add(fullCombo);
		}

		//Copy the arraylist of arraylists into an array of arrays
		int[][] output = new int[whiteSpaceCombos.size()][size];

		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < size; j++) {
				output[i][j] = poss.get(i).get(j);
			}
		}

		return output;

	}

	//A recursive method used to generate all possible variations of white space stretches
	public static void recursGenPoss(ArrayList<ArrayList<Integer>> stack, ArrayList<Integer> curr, int total, int n,
			int index) {
		
		//Stack is the list of variations
		//curr is root of the current variation (initially empty)
		//total is the number that is the remaining variables must sum up to (initially C)
		//n is the number of variables/terms/gaps/indices
		//index is the current index
		
		if (index == 1) { //This is the last variable
			ArrayList<Integer> a = new ArrayList<Integer>(curr); //Copy the root
			a.add(total); //Add the remaining total onto the root
			stack.add(a); //add the root to the list of possibilities
			return;
		}
		
		for (int c = 0; c <= total; c++) { //Loop through all possible numbers from 0 to the total
			ArrayList<Integer> a = new ArrayList<Integer>(curr); //copy the root
			a.add(c); //Add the number to the root
			recursGenPoss(stack, a, total - c, n, index - 1); //call the method recursively with the new root, new total, and new index
		}
	}


	public static int[] colLabelToIntArr(String label) { //Convert a column label into an array of stretch lengths (integers)
		String[] txtArr = label.strip().split("\n"); //Split the string based on new line characters
		ArrayList<Integer> nums = new ArrayList<Integer>();

		for (String s : txtArr) { //For each part of the string
			if (s.strip().length() != 0) { //If it is a number, parse it and add it to the arraylist
				nums.add(Integer.parseInt(s.strip())); 
			}
		}

		//Copy the arraylist to an array
		int[] output = new int[nums.size()]; 
		for (int i = 0; i < output.length; i++) {
			output[i] = nums.get(i);
		}

		return output;

	}

	public static int[] rowLabelToIntArr(String label) { //Convert a row label into an array of stretch lengths (integers)
		String[] txtArr = label.strip().split(" "); //Split the string based on spaces
		ArrayList<Integer> nums = new ArrayList<Integer>();

		for (String s : txtArr) { //For each part of the string
			if (s.length() != 0) { //If it is a number, parse it and add it to the arraylist
				nums.add(Integer.parseInt(s.strip()));
			}
		}

		//Copy the arraylist to an array
		int[] output = new int[nums.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = nums.get(i);
		}

		return output;

	}

	public static boolean checkLabels(String[] colLabels, String[] rowLabels) { //Checks if the labels fit the size of the grid
		for (String label : colLabels) {

			//Calculate the minimum length a column would have to be based on the label
			//and return false if the minimum is greater than the current length
			int[] nums = colLabelToIntArr(label);

			int count = nums.length - 1;
			for (int n : nums) {
				count += n;
			}
			

			if (count > rowLabels.length) { 
				return false;
			}

		}

		for (String label : rowLabels) {
			
			//Calculate the minimum length a row would have to be based on the label
			//and return false if the minimum is greater than the current length

			int[] nums = rowLabelToIntArr(label);
			int count = nums.length - 1;
			for (int n : nums) {
				count += n;
			}

			if (count > colLabels.length) {
				return false;
			}

		}
		
		//Return true if there is no error

		return true;

	}
}
