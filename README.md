# Nonograms

Summative for ICS4U (Grade 12 Computer Science)
Uses Java and Java Swing

## Introduction
“Nonograms” is a program which allows the user to play randomly generated nonograms. The program can also solve simple user-inputted nonograms. The user can also create and share their own custom nonograms. 
## What is a nonogram?
A nonogram is a puzzle consisting of a grid of squares. Each square can filled in or marked with a cross. Beside each row, the lengths of the runs of filled squares for that row are listed in order. Above each column, the lengths of the runs of filled squares for that column are listed in order. The player’s goal is to find all the filled squares.

## Instructions
### How to play a randomly generated nonogram
To play a randomly generated nonogram, click “Play” on the Main Menu.

The game screen should appear.

Left click a square to fill it. Left click a filled square to unfill it. The progress bar at the top should change to indicate the current number of filled squares out of the total number of filled squares in the solution. 

Right click a square to cross it. Right click a crossed square to uncross it. 

To clear the grid and restart, click “Restart”.

Once you think you have solved the nonogram, click “Done”. If you have correctly solved the nonogram, a dialog box will appear informing you that you have completed the nonogram successfully. 

To play another randomly generated nonogram (with the same size and number of filled squares as the current nonogram), click “Play new”.
To return to the Main Menu, click “Return to menu”.
To close the dialog box, click “Cancel”.

If you have incorrectly solved the nonogram, a dialog box will appear informing you that you have completed the nonogram unsuccessfully. 

To continue solving the current nonogram, click “Keep trying”.
To return to the Main Menu, click “Return to menu”.
To close the dialog box, click “Cancel”.

To generate a new nonogram of a new size, use the buttons to adjust the number of rows and columns. The minimum number of rows/columns is 1 and the maximum is 10. You can optionally type in a number into the field (just remember to press Enter afterwards to ensure the program has accepted the value). If the nonogram is too large, it will be cut off and the progress bar will be shrunken. To avoid this, either increase the size of the application window or choose a smaller nonogram size.

To adjust the percentage of the grid filled in the completed solution, adjust the Fullness % slider. 
Click “Generate” to create a new nonogram with the specified characteristics. 


### How to use the program to solve a nonogram
To have the program solve a nonogram, click “Auto-Solve” on the Main Menu.

The Auto Solver screen will appear.

Use the buttons to adjust the number of rows and columns of the puzzle. The minimum number of rows/columns is 1 and the maximum is 10.

Click on a row label to enter the lengths of the horizontal runs of filled squares (separated by a space) for that specific row. 
Click on a column label to enter the lengths of the vertical runs of filled squares (one on each line) for that specific column.

If you know certain squares are filled, you can left click the square to fill it like in the Game screen. Likewise, if you know certain squares are not filled, you can right click the square to cross it. 


Click “Solve” to let the program solve the nonogram.

If the nonogram has no solution with the given labels and filled/crossed squares, an error dialog box will appear. 

If the nonogram has multiple solutions, an error dialog box will appear.

## How to create a custom nonogram
To create a custom nonogram, click “Create” in the Main Menu. 

The Creator screen will appear.

Left click squares to fill/unfill them. 

To reset all the squares back to their default state, click “Clear”.

Once you have filled in all of your squares, click “Create” to generate the labels.

To change the size of the grid, use the buttons to adjust the number of rows or columns. The minimum number of rows/columns is 1 and the maximum is 10. 

To download your custom nonogram to share with others, click “Download”. If the download is successful, a dialog box will appear informing you that the puzzle file “puzzle.txt” has been downloaded into the same directory as the JAR file of this program. 

### How to play a custom nonogram
To play a custom nonogram, click “Load” on the Game screen.
The file chooser will appear allowing you to select the puzzle file. You can filter for text documents by selecting “Text Documents” under “Files of Type”. 

Click “Open” after selecting the file. The puzzle should appear on the grid.

If an invalid file was loaded, an error message will appear.

### How to change the colour of the nonogram
To change the colour of the nonogram, click “Settings” on the Main Menu to open the Settings screen.

Select the colour of the grid from the colour chooser. The colours of all the grids will change automatically.


### How to hide the progress bar 
To hide the progress bar on the Game screen, select “Hide Progress Bar” on the Settings screen. The progress bar will become hidden.

