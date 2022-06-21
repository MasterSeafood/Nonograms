import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.event.ChangeEvent;


/**
 * 
 * @author George Wang
 * 
 * Nonograms is an application where you can play and create nonogram puzzles. 
 * You can also let the application solve simple nonogram puzzles.
 *
 */
public class Nonograms {

	
	//Declare the three grids and the main frame
	private JFrame frmNonograms;
	private GameGrid gameGrid;
	private SolverGrid solverGrid;
	private CreatorGrid creatorGrid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nonograms window = new Nonograms();
					window.frmNonograms.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Nonograms() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Format the dialog boxes
		UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 255));
		UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));
		UIManager.put("OptionPane.buttonFont", new Font("Segoe UI Light", Font.PLAIN, 15));
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("OptionPane.buttonBorder", new MatteBorder(1, 1, 1, 1, Color.BLACK));

		//Create and format frame
		frmNonograms = new JFrame();
		frmNonograms.getContentPane().setBackground(Color.WHITE);
		frmNonograms.setTitle("Nonograms");
		frmNonograms.setBounds(100, 100, 650, 600);
		frmNonograms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNonograms.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		
		//Instantiate a URLGenerator (for getting image URLs)
		URLGenerator urlGen = new URLGenerator();
		
		//Get the nonogram icon and set it as the frame icon
		ImageIcon icon = new ImageIcon(urlGen.getURL("nonogram3.png"));
		frmNonograms.setIconImage(icon.getImage());

		
		//Create the panel that holds all the cards
		JPanel pnlCards = new JPanel();
		frmNonograms.getContentPane().add(pnlCards);
		pnlCards.setLayout(new CardLayout(0, 0));

		/*
		 * ------------------------------------ 
		 * START MENU
		 * ------------------------------------
		 */
		
		
		//Create and format the card for the Start Menu
		JPanel pnlStartMenu = new JPanel();
		pnlStartMenu.setBackground(Color.WHITE);
		pnlCards.add(pnlStartMenu, "StartMenuCard");
		GridBagLayout gbl_pnlStartMenu = new GridBagLayout();
		gbl_pnlStartMenu.columnWidths = new int[] { 30, 0, 30, 0 };
		gbl_pnlStartMenu.rowHeights = new int[] { 30, 0, 10, 0, 0, 0, 0, 0, 0 };
		gbl_pnlStartMenu.columnWeights = new double[] { 0, 1.0, 0, Double.MIN_VALUE };
		gbl_pnlStartMenu.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pnlStartMenu.setLayout(gbl_pnlStartMenu);
		
		//Draw the components of the Start Menu
		JPanel pnlLogoPadder = new JPanel();
		pnlLogoPadder.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlLogoPadder = new GridBagConstraints();
		gbc_pnlLogoPadder.insets = new Insets(0, 0, 5, 5);
		gbc_pnlLogoPadder.fill = GridBagConstraints.BOTH;
		gbc_pnlLogoPadder.gridx = 1;
		gbc_pnlLogoPadder.gridy = 1;
		pnlStartMenu.add(pnlLogoPadder, gbc_pnlLogoPadder);

		GridBagLayout gbl_pnlLogoPadder = new GridBagLayout();
		gbl_pnlLogoPadder.columnWidths = new int[] { 0, 6 * 60, 0, 0 };
		gbl_pnlLogoPadder.rowHeights = new int[] { 0, 2 * 60, 0, 0 };
		gbl_pnlLogoPadder.columnWeights = new double[] { 1, 0, 1.0, Double.MIN_VALUE };
		gbl_pnlLogoPadder.rowWeights = new double[] { 1, 0, 1.0, Double.MIN_VALUE };
		pnlLogoPadder.setLayout(gbl_pnlLogoPadder);

		JPanel pnlLogo = new JPanel();
		pnlLogo.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlLogo = new GridBagConstraints();
		gbc_pnlLogo.insets = new Insets(0, 0, 5, 5);
		gbc_pnlLogo.fill = GridBagConstraints.BOTH;
		gbc_pnlLogo.gridx = 1;
		gbc_pnlLogo.gridy = 1;
		pnlLogoPadder.add(pnlLogo, gbc_pnlLogo);
		pnlLogo.setLayout(new GridLayout(2, 6, 0, 0));

		JLabel lblLogo1 = new JLabel("N");
		lblLogo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo1.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo1.setMaximumSize(new Dimension(20, 20));
		lblLogo1.setPreferredSize(new Dimension(20, 20));
		lblLogo1.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));
		pnlLogo.add(lblLogo1);

		JLabel lblLogo2 = new JLabel("O");
		lblLogo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo2.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo2.setMaximumSize(new Dimension(20, 20));
		lblLogo2.setPreferredSize(new Dimension(20, 20));
		lblLogo2.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));
		pnlLogo.add(lblLogo2);

		JLabel lblLogo3 = new JLabel("");
		lblLogo3.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo3.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo3.setMaximumSize(new Dimension(20, 20));
		lblLogo3.setPreferredSize(new Dimension(20, 20));
		lblLogo3.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));
		pnlLogo.add(lblLogo3);

		JLabel lblLogo4 = new JLabel("N");
		lblLogo4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo4.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo4.setMaximumSize(new Dimension(20, 20));
		lblLogo4.setPreferredSize(new Dimension(20, 20));
		lblLogo4.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));
		pnlLogo.add(lblLogo4);

		JLabel lblLogo5 = new JLabel("O");
		lblLogo5.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo5.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo5.setMaximumSize(new Dimension(20, 20));
		lblLogo5.setPreferredSize(new Dimension(20, 20));
		lblLogo5.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));
		pnlLogo.add(lblLogo5);

		JLabel lblLogo6 = new JLabel("");
		lblLogo6.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo6.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo6.setMaximumSize(new Dimension(20, 20));
		lblLogo6.setPreferredSize(new Dimension(20, 20));
		lblLogo6.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.BLACK));
		pnlLogo.add(lblLogo6);

		JLabel lblLogo7 = new JLabel("");
		lblLogo7.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo7.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo7.setMaximumSize(new Dimension(20, 20));
		lblLogo7.setPreferredSize(new Dimension(20, 20));
		lblLogo7.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
		pnlLogo.add(lblLogo7);

		JLabel lblLogo8 = new JLabel("G");
		lblLogo8.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo8.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo8.setMaximumSize(new Dimension(20, 20));
		lblLogo8.setPreferredSize(new Dimension(20, 20));
		lblLogo8.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
		pnlLogo.add(lblLogo8);

		JLabel lblLogo9 = new JLabel("R");
		lblLogo9.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo9.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo9.setMaximumSize(new Dimension(20, 20));
		lblLogo9.setPreferredSize(new Dimension(20, 20));
		lblLogo9.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
		pnlLogo.add(lblLogo9);

		JLabel lblLogo10 = new JLabel("A");
		lblLogo10.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo10.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo10.setMaximumSize(new Dimension(20, 20));
		lblLogo10.setPreferredSize(new Dimension(20, 20));
		lblLogo10.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
		pnlLogo.add(lblLogo10);

		JLabel lblLogo11 = new JLabel("M");
		lblLogo11.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo11.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo11.setMaximumSize(new Dimension(20, 20));
		lblLogo11.setPreferredSize(new Dimension(20, 20));
		lblLogo11.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
		pnlLogo.add(lblLogo11);

		JLabel lblLogo12 = new JLabel("S");
		lblLogo12.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo12.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		lblLogo12.setMaximumSize(new Dimension(20, 20));
		lblLogo12.setPreferredSize(new Dimension(20, 20));
		lblLogo12.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		pnlLogo.add(lblLogo12);

		JButton btnToGame = new JButton("Play");
		btnToGame.addMouseListener(new MouseAdapter() { //Code to change hover colour
			@Override
			public void mouseEntered(MouseEvent e) {
				btnToGame.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnToGame.setBackground(Color.WHITE);
			}
		});
		btnToGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnToGame.setBackground(Color.WHITE);
		btnToGame.setFocusable(false);
		btnToGame.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		btnToGame.setPreferredSize(new Dimension(200, 50));
		GridBagConstraints gbc_btnToGame = new GridBagConstraints();
		gbc_btnToGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnToGame.gridx = 1;
		gbc_btnToGame.gridy = 3;
		pnlStartMenu.add(btnToGame, gbc_btnToGame);

		JButton btnAutoSolve = new JButton("Auto-solve");

		btnAutoSolve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAutoSolve.setBackground(Color.WHITE);
		btnAutoSolve.setFocusable(false);
		btnAutoSolve.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		btnAutoSolve.setPreferredSize(new Dimension(200, 50));
		btnAutoSolve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAutoSolve.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnAutoSolve.setBackground(Color.WHITE);
			}
		});
		GridBagConstraints gbc_btnAutoSolve = new GridBagConstraints();
		gbc_btnAutoSolve.insets = new Insets(0, 0, 5, 5);
		gbc_btnAutoSolve.gridx = 1;
		gbc_btnAutoSolve.gridy = 4;
		pnlStartMenu.add(btnAutoSolve, gbc_btnAutoSolve);

		JButton btnCreateCustom = new JButton("Create");
		btnCreateCustom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreateCustom.setBackground(Color.WHITE);
		btnCreateCustom.setFocusable(false);
		btnCreateCustom.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		btnCreateCustom.setPreferredSize(new Dimension(200, 50));
		btnCreateCustom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreateCustom.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnCreateCustom.setBackground(Color.WHITE);
			}
		});
		GridBagConstraints gbc_btnCreateCustom = new GridBagConstraints();
		gbc_btnCreateCustom.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateCustom.gridx = 1;
		gbc_btnCreateCustom.gridy = 5;
		pnlStartMenu.add(btnCreateCustom, gbc_btnCreateCustom);

		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Switch to Settings Card
				((CardLayout) (pnlCards.getLayout())).show(pnlCards, "SettingsCard");
			}
		});
		btnSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSettings.setBackground(Color.WHITE);
		btnSettings.setFocusable(false);
		btnSettings.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		btnSettings.setPreferredSize(new Dimension(200, 50));
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSettings.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnSettings.setBackground(Color.WHITE);
			}
		});
		GridBagConstraints gbc_btnSettings = new GridBagConstraints();
		gbc_btnSettings.insets = new Insets(0, 0, 5, 5);
		gbc_btnSettings.gridx = 1;
		gbc_btnSettings.gridy = 6;
		pnlStartMenu.add(btnSettings, gbc_btnSettings);

		/*
		 * --------------------------------- 
		 * GAME PANEL
		 * ---------------------------------
		 */

		//Draw the Game panel and its components
		JPanel pnlGame = new JPanel();
		pnlGame.setBackground(Color.WHITE);
		pnlCards.add(pnlGame, "GameCard");
		GridBagLayout gbl_pnlGame = new GridBagLayout();
		gbl_pnlGame.columnWidths = new int[] { 30, 0, 0, 0, 30, 0 };
		gbl_pnlGame.rowHeights = new int[] { 30, 0, 0, 100, 0, 0, 0, 0, 30, 30, 0 };
		gbl_pnlGame.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlGame.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0, Double.MIN_VALUE };
		pnlGame.setLayout(gbl_pnlGame);

		JLabel lblGameTitle = new JLabel("Play");
		lblGameTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
		GridBagConstraints gbc_lblGameTitle = new GridBagConstraints();
		gbc_lblGameTitle.gridwidth = 3;
		gbc_lblGameTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblGameTitle.gridx = 1;
		gbc_lblGameTitle.gridy = 1;
		pnlGame.add(lblGameTitle, gbc_lblGameTitle);

		JProgressBar pgbProgress = new JProgressBar();
		pgbProgress.setBackground(Color.WHITE);
		pgbProgress.setForeground(Color.BLACK);
		pgbProgress.setString("0");
		GridBagConstraints gbc_pgbProgress = new GridBagConstraints();
		gbc_pgbProgress.insets = new Insets(0, 0, 5, 5);
		gbc_pgbProgress.gridx = 2;
		gbc_pgbProgress.gridy = 2;
		pnlGame.add(pgbProgress, gbc_pgbProgress);

		JPanel pnlGrid = new JPanel();
		gameGrid = new GameGrid(pnlGrid, pgbProgress);
		gameGrid.loadPuzzle(PuzzleGenerator.genPuzzle(5, 5, 13));
		gameGrid.drawGrid();
		GridBagConstraints gbc_pnlGrid = new GridBagConstraints();
		gbc_pnlGrid.gridwidth = 3;
		gbc_pnlGrid.insets = new Insets(0, 0, 5, 5);
		gbc_pnlGrid.fill = GridBagConstraints.BOTH;
		gbc_pnlGrid.gridx = 1;
		gbc_pnlGrid.gridy = 3;
		pnlGame.add(pnlGrid, gbc_pnlGrid);
		

		JPanel pnlButtonPanel = new JPanel();
		pnlButtonPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlButtonPanel = new GridBagConstraints();
		gbc_pnlButtonPanel.gridwidth = 3;
		gbc_pnlButtonPanel.insets = new Insets(0, 0, 5, 5);
		gbc_pnlButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_pnlButtonPanel.gridx = 1;
		gbc_pnlButtonPanel.gridy = 5;
		pnlGame.add(pnlButtonPanel, gbc_pnlButtonPanel);

		JButton btnGameBackToMenu = new JButton("Back to menu");
		btnGameBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) (pnlCards.getLayout())).show(pnlCards, "StartMenuCard");
			}
		});
		btnGameBackToMenu.setFocusable(false);
		btnGameBackToMenu.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnGameBackToMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGameBackToMenu.setBackground(Color.WHITE);
		btnGameBackToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGameBackToMenu.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnGameBackToMenu.setBackground(Color.WHITE);
			}
		});
		pnlButtonPanel.add(btnGameBackToMenu);

		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameGrid.clearGrid();
			}
		});
		btnRestart.setFocusable(false);
		btnRestart.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnRestart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRestart.setBackground(Color.WHITE);
		btnRestart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRestart.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnRestart.setBackground(Color.WHITE);
			}
		});

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Puzzle puzzle = PuzzleCreator.loadPuzzle(pnlGame);
				if (puzzle != null) { //Print error dialog if puzzle is invalid
					
					if(!PuzzleSolver.checkLabels(puzzle.getColLabels(), puzzle.getRowLabels())) {
						DialogGenerator.genCustomError("The loaded puzzle is invalid.", 16);
						
					}else { //otherwise load and show it
						gameGrid.clearPanel();
						gameGrid.loadPuzzle(puzzle);
						gameGrid.drawGrid();
						
					}
				} 			

			}
		});
		btnLoad.setFocusable(false);
		btnLoad.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnLoad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoad.setBackground(Color.WHITE);
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLoad.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnLoad.setBackground(Color.WHITE);
			}
		});

		pnlButtonPanel.add(btnLoad);

		pnlButtonPanel.add(btnRestart);

		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (PuzzleEvaluator.evaluate(gameGrid.getTileStates(), gameGrid.getRowLabels(),
						gameGrid.getColLabels())) { //Check if puzzle has been solved correctly
					
					
					int resp = DialogGenerator.genSuccessSolve(); //Show dialog and get response

					if (resp == 0) {  //If they want to play a new puzzle
						//Load and draw a puzzle with the same size and number of tiles as the previous puzzle
						gameGrid.clearPanel();
						gameGrid.loadPuzzle(PuzzleGenerator.genPuzzle(gameGrid.getNumRows(), gameGrid.getNumCols(),
								gameGrid.getTileCount()));
						gameGrid.drawGrid();

					} else if (resp == 1) { //If they want to return to menu
						//Load a puzzle with the same size and number of tiles as the previous puzzle
						gameGrid.clearPanel();
						gameGrid.loadPuzzle(PuzzleGenerator.genPuzzle(gameGrid.getNumRows(), gameGrid.getNumCols(),
								gameGrid.getTileCount()));
						gameGrid.drawGrid();
						
						//Go back to the Start Menu
						((CardLayout) (pnlCards.getLayout())).show(pnlCards, "StartMenuCard");

					}

				} else { //If they didn't solve the puzzle correctly
					
					int resp = DialogGenerator.genFailedSolve(); //Show dialog and get response

					if (resp == 1) { //If they want to return to menu
						
						((CardLayout) (pnlCards.getLayout())).show(pnlCards, "StartMenuCard");

					}

				}

			}
		});
		btnDone.setFocusable(false);
		btnDone.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnDone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDone.setBackground(Color.WHITE);
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDone.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnDone.setBackground(Color.WHITE);
			}
		});

		pnlButtonPanel.add(btnDone);

		JLabel lblGenNewPuzzle = new JLabel("or generate a new puzzle:");
		lblGenNewPuzzle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		GridBagConstraints gbc_lblGenNewPuzzle = new GridBagConstraints();
		gbc_lblGenNewPuzzle.gridwidth = 3;
		gbc_lblGenNewPuzzle.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenNewPuzzle.gridx = 1;
		gbc_lblGenNewPuzzle.gridy = 6;
		pnlGame.add(lblGenNewPuzzle, gbc_lblGenNewPuzzle);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 7;
		pnlGame.add(panel, gbc_panel);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel.add(lblSize);

		JSpinner spnRows = new JSpinner();
		spnRows.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spnRows.setBackground(Color.WHITE);
		spnRows.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		spnRows.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		panel.add(spnRows);

		JLabel lblRows = new JLabel("rows by");
		lblRows.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel.add(lblRows);

		JSpinner spnCols = new JSpinner();
		spnCols.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spnCols.setBackground(Color.WHITE);
		spnCols.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		spnCols.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		panel.add(spnCols);

		JLabel lblColumns = new JLabel("columns");
		lblColumns.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel.add(lblColumns);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 8;
		pnlGame.add(panel_1, gbc_panel_1);

		JLabel lblFullness = new JLabel("Fullness %");
		lblFullness.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel_1.add(lblFullness);

		JSlider sldFullness = new JSlider();
		sldFullness.setPaintLabels(true);
		sldFullness.setMaximum(90);
		sldFullness.setMinimum(10);
		sldFullness.setMajorTickSpacing(10);
		sldFullness.setValue(50);
		sldFullness.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		sldFullness.setOpaque(false);
		sldFullness.setMinorTickSpacing(5);
		panel_1.add(sldFullness);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get the values from the size spinners and fullness slider

				int gridRows = (int) spnRows.getValue();
				int gridCols = (int) spnCols.getValue();
				int gridTileCount = (int) (sldFullness.getValue() / 100.0 * gridRows * gridCols);

				//Draw a puzzle with those specifications
				gameGrid.clearPanel();
				gameGrid.loadPuzzle(PuzzleGenerator.genPuzzle(gridRows, gridCols, gridTileCount));
				gameGrid.drawGrid();
			}
		});
		btnGenerate.setFocusable(false);
		btnGenerate.setBackground(Color.WHITE);
		btnGenerate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerate.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGenerate.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnGenerate.setBackground(Color.WHITE);
			}
		});

		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.gridwidth = 3;
		gbc_btnGenerate.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerate.gridx = 1;
		gbc_btnGenerate.gridy = 9;

		pnlGame.add(btnGenerate, gbc_btnGenerate);

		/*------------------------------
		 * SETTINGS PANEL
		 * -----------------------------
		 */

		//Draw the Settings panel and its components
		JPanel pnlSettings = new JPanel();
		pnlSettings.setBackground(Color.WHITE);
		pnlCards.add(pnlSettings, "SettingsCard");
		GridBagLayout gbl_pnlSettings = new GridBagLayout();
		gbl_pnlSettings.columnWidths = new int[] { 30, 0, 30, 0 };
		gbl_pnlSettings.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 30, 0 };
		gbl_pnlSettings.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlSettings.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlSettings.setLayout(gbl_pnlSettings);

		JLabel lblSettingsTitle = new JLabel("Settings");
		lblSettingsTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
		GridBagConstraints gbc_lblSettingsTitle = new GridBagConstraints();
		gbc_lblSettingsTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblSettingsTitle.gridx = 1;
		gbc_lblSettingsTitle.gridy = 1;
		pnlSettings.add(lblSettingsTitle, gbc_lblSettingsTitle);
		
		JLabel lblGridColour = new JLabel("Grid Colour: ");
		lblGridColour.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		GridBagConstraints gbc_lblGridColour = new GridBagConstraints();
		gbc_lblGridColour.insets = new Insets(0, 0, 5, 5);
		gbc_lblGridColour.gridx = 1;
		gbc_lblGridColour.gridy = 2;
		pnlSettings.add(lblGridColour, gbc_lblGridColour);

		JColorChooser cchPuzzleColorChooser = new JColorChooser(Color.BLACK);
		cchPuzzleColorChooser.setBackground(Color.WHITE);
		cchPuzzleColorChooser.setSize(450, 450);
		GridBagConstraints gbc_cchPuzzleColorChooser = new GridBagConstraints();
		gbc_cchPuzzleColorChooser.insets = new Insets(0, 0, 5, 5);
		gbc_cchPuzzleColorChooser.gridx = 1;
		gbc_cchPuzzleColorChooser.gridy = 3;
		pnlSettings.add(cchPuzzleColorChooser, gbc_cchPuzzleColorChooser);

		JRadioButton rdbtnHideProgressBar = new JRadioButton("Hide Progress Bar");
		rdbtnHideProgressBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnHideProgressBar.setFocusable(false);
		rdbtnHideProgressBar.setHorizontalTextPosition(SwingConstants.LEADING);
		rdbtnHideProgressBar.setBackground(Color.WHITE);
		rdbtnHideProgressBar.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		GridBagConstraints gbc_rdbtnHideProgressBar = new GridBagConstraints();
		gbc_rdbtnHideProgressBar.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHideProgressBar.gridx = 1;
		gbc_rdbtnHideProgressBar.gridy = 4;
		pnlSettings.add(rdbtnHideProgressBar, gbc_rdbtnHideProgressBar);

		JButton btnSettingsBackToMenu = new JButton("Back to menu");
		btnSettingsBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Hide/Show the progress bar and return to the Start Menu
				pgbProgress.setVisible(!rdbtnHideProgressBar.isSelected());
				((CardLayout) (pnlCards.getLayout())).show(pnlCards, "StartMenuCard");
			}
		});
		btnSettingsBackToMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSettingsBackToMenu.setBackground(Color.WHITE);
		btnSettingsBackToMenu.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnSettingsBackToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSettingsBackToMenu.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnSettingsBackToMenu.setBackground(Color.WHITE);
			}
		});

		GridBagConstraints gbc_btnSettingsBackToMenu = new GridBagConstraints();
		gbc_btnSettingsBackToMenu.insets = new Insets(0, 0, 5, 5);
		gbc_btnSettingsBackToMenu.gridx = 1;
		gbc_btnSettingsBackToMenu.gridy = 5;
		pnlSettings.add(btnSettingsBackToMenu, gbc_btnSettingsBackToMenu);

		/*
		 * ---------------------- 
		 * SOLVER PANEL 
		 * ----------------------
		 */

		//Draw the solver panel and its components
		JPanel pnlSolver = new JPanel();
		pnlSolver.setBackground(Color.WHITE);
		pnlCards.add(pnlSolver, "SolverCard");
		GridBagLayout gbl_pnlSolver = new GridBagLayout();
		gbl_pnlSolver.columnWidths = new int[] { 30, 0, 0, 0, 30, 0 };
		gbl_pnlSolver.rowHeights = new int[] { 30, 0, 100, 0, 0, 0, 30, 0 };
		gbl_pnlSolver.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlSolver.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlSolver.setLayout(gbl_pnlSolver);

		JLabel lblAutoSolver = new JLabel("Auto Solver");
		lblAutoSolver.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
		GridBagConstraints gbc_lblAutoSolver = new GridBagConstraints();
		gbc_lblAutoSolver.gridwidth = 3;
		gbc_lblAutoSolver.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoSolver.gridx = 1;
		gbc_lblAutoSolver.gridy = 1;
		pnlSolver.add(lblAutoSolver, gbc_lblAutoSolver);

		JPanel pnlGrid_1 = new JPanel();
		solverGrid = new SolverGrid(pnlGrid_1);
		solverGrid.setGridSize(5, 5);
		solverGrid.drawGrid();
		GridBagConstraints gbc_pnlGrid_1 = new GridBagConstraints();
		gbc_pnlGrid_1.fill = GridBagConstraints.BOTH;
		gbc_pnlGrid_1.gridwidth = 3;
		gbc_pnlGrid_1.insets = new Insets(0, 0, 5, 5);
		gbc_pnlGrid_1.gridx = 1;
		gbc_pnlGrid_1.gridy = 2;
		pnlSolver.add(pnlGrid_1, gbc_pnlGrid_1);

		JPanel pnlButtonPanel_1 = new JPanel();
		pnlButtonPanel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlButtonPanel_1 = new GridBagConstraints();
		gbc_pnlButtonPanel_1.fill = GridBagConstraints.BOTH;
		gbc_pnlButtonPanel_1.gridwidth = 3;
		gbc_pnlButtonPanel_1.insets = new Insets(0, 0, 5, 5);
		gbc_pnlButtonPanel_1.gridx = 1;
		gbc_pnlButtonPanel_1.gridy = 3;
		pnlSolver.add(pnlButtonPanel_1, gbc_pnlButtonPanel_1);

		JButton btnSolveBackToMenu = new JButton("Back to menu");
		btnSolveBackToMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSolveBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Save the current fields in the solver grid and return to the Start Menu
				solverGrid.saveLabelField();
				((CardLayout) (pnlCards.getLayout())).show(pnlCards, "StartMenuCard");
			}
		});
		btnSolveBackToMenu.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnSolveBackToMenu.setFocusable(false);
		btnSolveBackToMenu.setBackground(Color.WHITE);
		btnSolveBackToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSolveBackToMenu.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnSolveBackToMenu.setBackground(Color.WHITE);
			}
		});

		pnlButtonPanel_1.add(btnSolveBackToMenu);

		JButton btnClearSolver = new JButton("Clear");
		btnClearSolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solverGrid.clearGrid();
			}
		});
		btnClearSolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearSolver.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnClearSolver.setFocusable(false);
		btnClearSolver.setBackground(Color.WHITE);
		btnClearSolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClearSolver.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnClearSolver.setBackground(Color.WHITE);
			}
		});

		pnlButtonPanel_1.add(btnClearSolver);

		JButton btnSolve = new JButton("Solve");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Save the text in the fields 
				solverGrid.saveLabelField();
				
				
				//Print an error dialog if the labels are empty
				if (PuzzleCreator.isLabelsEmpty(solverGrid.getRowLabels(), solverGrid.getColLabels())) {
					DialogGenerator.genCustomError("The labels must be filled before solving.", 16);
					return;
				}

				//Print an error dialog if the labels are invalid
				if (!PuzzleSolver.checkLabels(solverGrid.getColLabels(), solverGrid.getRowLabels())) {
					DialogGenerator.genCustomError("The labels are incompatible with the current puzzle size.", 16);
					return;
				}

				//Solve the puzzle and get the solution
				int[][] output = PuzzleSolver.solvePuzzle(solverGrid.getColLabels(), solverGrid.getRowLabels(),
						solverGrid.getTileStates());


				if (output[0][0] == Integer.MAX_VALUE) { //Print an error dialog if the puzzle has no solution
					DialogGenerator.genCustomError("The puzzle has no solutions with the given labels and tiles.", 16);
				} else if (output[0][0] == Integer.MIN_VALUE) { //Print an error dialog if the puzzle is unsolvable for the computer
					DialogGenerator.genCustomError("The computer was unable to solve this puzzle. "
				+ "This may be because the puzzle has multiple solutions or is too complex. "
				+ "Try placing some known black squares or crosses.", 14);
				} else { //Otherwise, draw the solution to the puzzle

					solverGrid.drawSolution(output);

				}

			}
		});
		btnSolve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSolve.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnSolve.setFocusable(false);
		btnSolve.setBackground(Color.WHITE);
		btnSolve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSolve.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnSolve.setBackground(Color.WHITE);
			}
		});

		pnlButtonPanel_1.add(btnSolve);

		JLabel lblSizeSpec = new JLabel("Size Specifications");
		lblSizeSpec.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		GridBagConstraints gbc_lblSizeSpec = new GridBagConstraints();
		gbc_lblSizeSpec.gridwidth = 3;
		gbc_lblSizeSpec.insets = new Insets(0, 0, 5, 5);
		gbc_lblSizeSpec.gridx = 1;
		gbc_lblSizeSpec.gridy = 4;
		pnlSolver.add(lblSizeSpec, gbc_lblSizeSpec);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 5;
		pnlSolver.add(panel_2, gbc_panel_2);

		JLabel lblSizeSolver = new JLabel("Size:");
		lblSizeSolver.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel_2.add(lblSizeSolver);

		JSpinner spnRowsSolver = new JSpinner();
		spnRowsSolver.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		spnRowsSolver.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		spnRowsSolver.setBackground(Color.WHITE);
		panel_2.add(spnRowsSolver);

		JLabel lblRowsSolver = new JLabel("rows by");
		lblRowsSolver.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel_2.add(lblRowsSolver);

		JSpinner spnColsSolver = new JSpinner();
		spnColsSolver.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		spnColsSolver.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		spnColsSolver.setBackground(Color.WHITE);
		panel_2.add(spnColsSolver);

		JLabel lblColumnsSolver = new JLabel("columns");
		lblColumnsSolver.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel_2.add(lblColumnsSolver);

		spnRowsSolver.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				//Get the spinner value and update the size of the grid
				JSpinner spinner = (JSpinner) e.getSource();
				solverGrid.clearPanel();
				solverGrid.setGridSize((int) spinner.getValue(), (int) spnColsSolver.getValue());
				solverGrid.drawGrid();
			}
		});

		spnColsSolver.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				//Get the spinner value and update the size of the grid
				JSpinner spinner = (JSpinner) e.getSource();
				solverGrid.clearPanel();
				solverGrid.setGridSize((int) spnRowsSolver.getValue(), (int) spinner.getValue());
				solverGrid.drawGrid();
			}
		});

		/*
		 * ---------------------- 
		 * CREATOR PANEL
		 * ----------------------
		 */
		
		//Draw the creator panel and its components
		JPanel pnlCreator = new JPanel();
		pnlCreator.setBackground(Color.WHITE);
		pnlCards.add(pnlCreator, "CreatorCard");
		GridBagLayout gbl_pnlCreator = new GridBagLayout();
		gbl_pnlCreator.columnWidths = new int[] { 30, 0, 0, 0, 30, 0 };
		gbl_pnlCreator.rowHeights = new int[] { 30, 0, 100, 0, 0, 0, 30, 0 };
		gbl_pnlCreator.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlCreator.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlCreator.setLayout(gbl_pnlCreator);

		JLabel lblCreatorTitle = new JLabel("Creator");
		lblCreatorTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
		GridBagConstraints gbc_lblCreatorTitle = new GridBagConstraints();
		gbc_lblCreatorTitle.gridwidth = 3;
		gbc_lblCreatorTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreatorTitle.gridx = 1;
		gbc_lblCreatorTitle.gridy = 1;
		pnlCreator.add(lblCreatorTitle, gbc_lblCreatorTitle);

		JPanel pnlCreatorGrid = new JPanel();
		creatorGrid = new CreatorGrid(pnlCreatorGrid);
		creatorGrid.drawGrid();
		GridBagConstraints gbc_pnlCreatorGrid = new GridBagConstraints();
		gbc_pnlCreatorGrid.fill = GridBagConstraints.BOTH;
		gbc_pnlCreatorGrid.gridwidth = 3;
		gbc_pnlCreatorGrid.insets = new Insets(0, 0, 5, 5);
		gbc_pnlCreatorGrid.gridx = 1;
		gbc_pnlCreatorGrid.gridy = 2;
		pnlCreator.add(pnlCreatorGrid, gbc_pnlCreatorGrid);

		JPanel pnlButtonPanel_2 = new JPanel();
		pnlButtonPanel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlButtonPanel_2 = new GridBagConstraints();
		gbc_pnlButtonPanel_2.fill = GridBagConstraints.BOTH;
		gbc_pnlButtonPanel_2.gridwidth = 3;
		gbc_pnlButtonPanel_2.insets = new Insets(0, 0, 5, 5);
		gbc_pnlButtonPanel_2.gridx = 1;
		gbc_pnlButtonPanel_2.gridy = 3;
		pnlCreator.add(pnlButtonPanel_2, gbc_pnlButtonPanel_2);

		JButton btnCreatorBackToMenu = new JButton("Back to menu");
		btnCreatorBackToMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreatorBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) (pnlCards.getLayout())).show(pnlCards, "StartMenuCard");
			}
		});
		btnCreatorBackToMenu.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnCreatorBackToMenu.setFocusable(false);
		btnCreatorBackToMenu.setBackground(Color.WHITE);
		btnCreatorBackToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreatorBackToMenu.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnCreatorBackToMenu.setBackground(Color.WHITE);
			}

		});

		pnlButtonPanel_2.add(btnCreatorBackToMenu);

		JButton btnClearCreator = new JButton("Clear");
		btnClearCreator.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearCreator.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnClearCreator.setFocusable(false);
		btnClearCreator.setBackground(Color.WHITE);
		btnClearCreator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClearCreator.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnClearCreator.setBackground(Color.WHITE);
			}
		});

		pnlButtonPanel_2.add(btnClearCreator);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get the state of the grid, create a puzzle based on it, and draw it on the grid
				int[][] states = creatorGrid.getTileStates();
				creatorGrid.loadPuzzle(PuzzleCreator.createPuzzle(states));
				creatorGrid.clearPanel();
				creatorGrid.drawGrid();

			}
		});
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnCreate.setFocusable(false);
		btnCreate.setBackground(Color.WHITE);
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreate.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnCreate.setBackground(Color.WHITE);
			}
		});

		pnlButtonPanel_2.add(btnCreate);

		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PuzzleCreator.writePuzzle(pnlCreator, creatorGrid);
			}
		});
		btnDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDownload.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		btnDownload.setFocusable(false);
		btnDownload.setBackground(Color.WHITE);
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDownload.setBackground(Color.getHSBColor(0, 0, 0.95f));
			}

			public void mouseExited(MouseEvent e) {
				btnDownload.setBackground(Color.WHITE);
			}
		});

		pnlButtonPanel_2.add(btnDownload);

		JLabel lblSizeSpecCreator = new JLabel("Size Specifications");
		lblSizeSpecCreator.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		GridBagConstraints gbc_lblSizeSpecCreator = new GridBagConstraints();
		gbc_lblSizeSpecCreator.gridwidth = 3;
		gbc_lblSizeSpecCreator.insets = new Insets(0, 0, 5, 5);
		gbc_lblSizeSpecCreator.gridx = 1;
		gbc_lblSizeSpecCreator.gridy = 4;
		pnlCreator.add(lblSizeSpecCreator, gbc_lblSizeSpecCreator);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridwidth = 3;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 5;
		pnlCreator.add(panel_3, gbc_panel_3);

		JLabel lblSizeCreator = new JLabel("Size:");
		lblSizeCreator.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel_3.add(lblSizeCreator);

		JSpinner spnRowsCreator = new JSpinner();
		spnRowsCreator.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		spnRowsCreator.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		spnRowsCreator.setBackground(Color.WHITE);
		panel_3.add(spnRowsCreator);

		JLabel lblXCreator = new JLabel("rows by");
		lblXCreator.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel_3.add(lblXCreator);

		JSpinner spnColsCreator = new JSpinner();
		spnColsCreator.setModel(new SpinnerNumberModel(5, 1, 10, 1));
		spnColsCreator.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		spnColsCreator.setBackground(Color.WHITE);
		panel_3.add(spnColsCreator);

		JLabel lblColsCreator = new JLabel("columns");
		lblColsCreator.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel_3.add(lblColsCreator);

		spnRowsCreator.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				//Get the spinner value and update the size of the grid
				JSpinner spinner = (JSpinner) e.getSource();
				creatorGrid.clearPanel();
				creatorGrid.setGridSize((int) spinner.getValue(), (int) spnColsCreator.getValue());
				creatorGrid.drawGrid();
			}
		});

		spnColsCreator.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				//Get the spinner value and update the size of the grid
				JSpinner spinner = (JSpinner) e.getSource();
				creatorGrid.clearPanel();
				creatorGrid.setGridSize((int) spnRowsCreator.getValue(), (int) spinner.getValue());
				creatorGrid.drawGrid();
			}
		});

		btnClearCreator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creatorGrid.clearPanel();
				creatorGrid.clearGrid();
				creatorGrid.drawGrid();
			}
		});

		btnToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Switch to the game card, update the grid colour, and redraw the grid
				((CardLayout) (pnlCards.getLayout())).show(pnlCards, "GameCard");
				gameGrid.setGridColour(cchPuzzleColorChooser.getColor());

				gameGrid.clearPanel();
				gameGrid.drawGrid();

			}
		});

		btnAutoSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Switch to the solver card, update the grid colour, and redraw the grid
				((CardLayout) (pnlCards.getLayout())).show(pnlCards, "SolverCard");
				solverGrid.setGridColour(cchPuzzleColorChooser.getColor());

				solverGrid.clearPanel();
				solverGrid.drawGrid();
			}
		});
		btnCreateCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Switch to the creator card, update the grid colour, and redraw the grid
				((CardLayout) (pnlCards.getLayout())).show(pnlCards, "CreatorCard");
				creatorGrid.setGridColour(cchPuzzleColorChooser.getColor());

				creatorGrid.clearPanel();
				creatorGrid.drawGrid();
			}
		});

	}
	

}
