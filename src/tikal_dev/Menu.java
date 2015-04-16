package tikal_dev;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JPanel {
	Player[] _player;
	Move _move;
	JPanel stats;
	JPanel actions;
	JPanel preview;
	JButton tile;
	JButton pyramid;
	JButton explorer;
	JButton move;
	JLabel tileText;
	JLabel pyramidText;
	JLabel explorerText;
	JLabel moveText;
	JLabel tileCost;
	JLabel pyramidCost;
	JLabel explorerCost;
	JLabel moveCost;
	JLabel playerName;
	JLabel playerPointsText;
	JLabel playerPoints;
	JLabel playerExplorersText;
	JLabel playerExplorers;
	JLabel currentlyExploringText;
	JLabel currentlyExploring;
	JLabel instructions;
	JPanel tileImg;
	JButton clockwise;
	JButton counter;
	JPanel turnEnd;
	JButton next;
	JButton end;
	JButton save;
	JButton load;
	Player current;
	TileData[] gameTileData;
	TileData blank;
	Tile previewTile;
	TileData[][] _BoardData;
	int _numberOfTiles;
	int _numberOfPlayers;
	int _size;

	public Menu(Player[] player, Move move, TileData[][] _boardData,
			int numberOfPlayers, int size, int numberOfTiles) {
		_numberOfTiles = numberOfTiles;
		_numberOfPlayers = numberOfPlayers;
		_size = size;
		int[] temp = new int[] { 0, 0, 0, 0, 0, 0 };
		blank = new TileData(0, 0, temp, false, true, false, numberOfPlayers);
		previewTile = new Tile(blank);
		_player = player; 
				/*new Player[player.length];
		for (int i = 0; i < player.length; i++) {
			_player[i] = player[i];
		}*/
		current = _player[0];
		_move = move;
		_move.setCurrentPlayer(current);
		_BoardData = _boardData;
		setup();
		CreateTileData();
	}

	public void setup() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(300, 600);
		// Setting up the 'Player Stats" Panel
		// this is where the stats for the current player are displayed
		stats = new JPanel();
		this.add(stats);
		stats.setMinimumSize(new Dimension(300, 200));
		stats.setLayout(null);
		
		playerName = new JLabel(_move.getCurrentPlayer().getName());
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setFont(new Font("Serif", Font.BOLD, 18));
		stats.add(playerName);
		playerName.setBounds(0, 0, 300, 50);

		playerPointsText = new JLabel("Action Points");
		playerPointsText.setHorizontalAlignment(SwingConstants.CENTER);
		playerPointsText.setFont(new Font("Serif", Font.BOLD, 16));
		stats.add(playerPointsText);
		playerPointsText.setBounds(0, 50, 150, 25);

		playerPoints = new JLabel(String.valueOf(_move.getCpActionPoints()));
		playerPoints.setHorizontalAlignment(SwingConstants.CENTER);
		playerPoints.setFont(new Font("Serif", Font.BOLD, 24));
		stats.add(playerPoints);
		playerPoints.setBounds(200, 50, 100, 25);

		playerExplorersText = new JLabel("Available Explorers");
		playerExplorersText.setHorizontalAlignment(SwingConstants.CENTER);
		playerExplorersText.setFont(new Font("Serif", Font.BOLD, 16));
		stats.add(playerExplorersText);
		playerExplorersText.setBounds(0, 100, 150, 25);

		playerExplorers = new JLabel(String.valueOf(_move
				.getAvailableExplorer()));
		playerExplorers.setHorizontalAlignment(SwingConstants.CENTER);
		playerExplorers.setFont(new Font("Serif", Font.BOLD, 24));
		stats.add(playerExplorers);
		playerExplorers.setBounds(200, 100, 100, 25);

		currentlyExploringText = new JLabel("Currently Exploring");
		currentlyExploringText.setHorizontalAlignment(SwingConstants.CENTER);
		currentlyExploringText.setFont(new Font("Serif", Font.BOLD, 16));
		stats.add(currentlyExploringText);
		currentlyExploringText.setBounds(0, 150, 150, 25);

		currentlyExploring = new JLabel(String.valueOf(10 - _move
				.getAvailableExplorer()));
		currentlyExploring.setHorizontalAlignment(SwingConstants.CENTER);
		currentlyExploring.setFont(new Font("Serif", Font.BOLD, 24));
		stats.add(currentlyExploring);
		currentlyExploring.setBounds(200, 150, 100, 25);

		instructions = new JLabel("");
		instructions.setHorizontalAlignment(SwingConstants.CENTER);
		instructions.setFont(new Font("Serif", Font.BOLD, 14));
		stats.add(instructions);
		instructions.setBounds(0, 200, 300, 50);
		// End of 'Player Stats' Panel

		// Setting up 'Actions' Panel
		// this is where the player can spend their action points for the turn
		actions = new JPanel();
		this.add(actions);
		actions.setMaximumSize(new Dimension(300, 100));
		actions.setLayout(new GridLayout(2, 2));

		// Setting up the 'Place a Tile' Button
		// Cosmetics
		tile = new JButton();
		tile.setLayout(new BorderLayout());
		tileText = new JLabel("Place a Tile");
		tileText.setFont(new Font("Serif", Font.BOLD, 16));
		tileText.setHorizontalAlignment(SwingConstants.CENTER);
		tile.add(tileText, BorderLayout.CENTER);
		tileCost = new JLabel("(6AP)");
		tileCost.setHorizontalAlignment(SwingConstants.CENTER);
		tile.add(tileCost, BorderLayout.SOUTH);
		actions.add(tile);
		// Functionality
		tile.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				PlaceTileClick(e);
			}
		});
		// End of 'Place a tile' Button

		// Setting up the 'Place a Pyramid' Button
		// Cosmetics
		pyramid = new JButton();
		pyramid.setLayout(new BorderLayout());
		pyramidText = new JLabel("Place a Pyramid");
		pyramidText.setFont(new Font("Serif", Font.BOLD, 16));
		pyramidText.setHorizontalAlignment(SwingConstants.CENTER);
		pyramid.add(pyramidText, BorderLayout.CENTER);
		pyramidCost = new JLabel("(3AP)");
		pyramidCost.setHorizontalAlignment(SwingConstants.CENTER);
		pyramid.add(pyramidCost, BorderLayout.SOUTH);
		actions.add(pyramid);
		// Functionality
		pyramid.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				PlacePyramidClick(e);
			}
		});
		// End of 'Place a Pyramid' Button

		// Setting up the 'Place an Explorer Button
		// Cosmetics
		explorer = new JButton();
		explorer.setLayout(new BorderLayout());
		explorerText = new JLabel("Place an Explorer");
		explorerText.setFont(new Font("Serif", Font.BOLD, 14));
		explorerText.setHorizontalAlignment(SwingConstants.CENTER);
		explorer.add(explorerText, BorderLayout.CENTER);
		explorerCost = new JLabel("(2AP)");
		explorerCost.setHorizontalAlignment(SwingConstants.CENTER);
		explorer.add(explorerCost, BorderLayout.SOUTH);
		actions.add(explorer);
		// Functionality
		explorer.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				PlaceExplorerClick(e);
			}
		});
		// End of 'Place an Explorer' Button

		// Setting up the 'Move an Explorer' Button
		// Cosmetics
		move = new JButton();
		move.setLayout(new BorderLayout());
		moveText = new JLabel("Move an Explorer");
		moveText.setFont(new Font("Serif", Font.BOLD, 14));
		moveText.setHorizontalAlignment(SwingConstants.CENTER);
		move.add(moveText, BorderLayout.CENTER);
		moveCost = new JLabel("(1AP)");
		moveCost.setHorizontalAlignment(SwingConstants.CENTER);
		move.add(moveCost, BorderLayout.SOUTH);
		actions.add(move);
		// Functionality
		move.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MoveExplorerClick(e);
			}
		});
		// End of 'Move an Explorer' Button
		// End of 'Actions' Panel

		// Setting up the 'Preview' Panel
		// this is where the next Tile to place will be shown and manipulated
		// before placement
		preview = new JPanel();
		this.add(preview);
		preview.setSize(300, 100);
		preview.setLayout(null);

		// Setting up the Preview image
		tileImg = new JPanel();
		tileImg.setSize(100, 100);
		preview.add(tileImg);
		tileImg.setBounds(100, 0, 100, 100);
		// tileImg.add(previewTile);
		// previewTile.setVisible(false);

		// Setting up the 'Rotate' Buttons
		// Clockwise Rotation
		// Cosmetics
		clockwise = new JButton();
		clockwise.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/tikal_dev/Clockwise.png")));
		preview.add(clockwise);
		clockwise.setSize(40, 40);
		clockwise.setBounds(30, 40, 40, 40);
		// Functionality
		clockwise.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ClockwiseClick(e);
			}
		});

		// Counterclockwise Rotation
		// Cosmetics
		counter = new JButton();
		counter.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/tikal_dev/Counterclockwise.png")));
		preview.add(counter);
		counter.setSize(40, 40);
		counter.setBounds(230, 40, 40, 40);
		// Functionality
		counter.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CounterClick(e);
			}
		});

		// Adds the 'Next Turn' Button to the preview Panel
		next = new JButton("End Turn");
		preview.add(next);
		next.setBounds(0, 125, 150, 50);
		next.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				NextClick(e);
			}
		});

		// Add an end game button to end the game if no more tiles can be placed
		// but not all 40 have been placed
		end = new JButton("End Game");
		preview.add(end);
		end.setBounds(150, 125, 150, 50);
		end.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				EndClick(e);
			}
		});

		save = new JButton("Save");
		preview.add(save);
		save.setBounds(0, 175, 150, 50);
		save.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Saveclick(e);
			}
		});

		load = new JButton("Load");
		preview.add(load);
		load.setBounds(150, 175, 150, 50);
		load.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Loadclick(e);
			}
		});

		// End of the 'Preview' panel

	}
	

	// These are methods that handle each what happens when each menu button is
	// clicked
	public void Saveclick(MouseEvent e){
		FileIO file = new FileIO(_player,_move,_BoardData, _numberOfPlayers, _size, _numberOfTiles);
		file.write();
	}
	public void Loadclick(MouseEvent e){
		FileIO file = new FileIO(_player,_move,_BoardData, _numberOfPlayers, _size, _numberOfTiles);
		file.read();
		this.RefreshStats();
	}
	public void PlaceTileClick(MouseEvent e) {
		if (_move.getCpActionPoints() > 5)// checks if player has enough action
											// points
		{

			if (_move.getCurrentTilePlaced() < _numberOfTiles)// checks if there
																// are tiles
																// remaining to
																// be placed
			{
				if (previewTile.tileData == blank)// checks to see if a tile has
													// already been drawn but
													// not placed
				{
					// pulls a tile from the tile[] and sets if in the preview
					// for rotation and placement
					// and updates move to the placing a tile state
					UpdateInstructions(1);
					previewTile.setTileData(gameTileData[_move
							.getCurrentTilePlaced()]);
					//if(previewTile.tileData._PMV)previewTile.tileData.PM.IMG.setVisible(true);
					//else previewTile.tileData.PM.IMG.setVisible(false);
					previewTile.RotateClockwise();
					tileImg.add(previewTile);
					tileImg.repaint();
					_move.setNexttoPlace(previewTile);
					_move.setMoves(1);
					// System.out.println("Placed a tile!");
					_move.setCurrentTilePlaced(_move.getCurrentTilePlaced() + 1);
				} else {
					JOptionPane.showMessageDialog(null,
							"You already have a tile");
				}

			} else {
				JOptionPane.showMessageDialog(null, "No More Tiles!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "You need more Action Points!");
		}
	}

	public void PlacePyramidClick(MouseEvent e) {
		if (previewTile.tileData == blank)// checks to make sure you don't have
											// a tile pending placement
		{
			if (_move.getPyramidsThisTurn() < 2)// checks how many pyramids
												// already placed this turn
			{
				if (_move.getCpActionPoints() > 2)// checks action points
				{
					// updates move to placing a pyramid state
					UpdateInstructions(1);
					_move.setMoves(3);
					// System.out.println("Placed a Pyramid!");
				} else {
					JOptionPane.showMessageDialog(null,
							"You need more Action Points!");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Max pyramids placed this turn");
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"You must place your tile first");
		}

	}

	public void PlaceExplorerClick(MouseEvent e) {
		if (previewTile.tileData == blank) {
			if (_move.getCpActionPoints() > 1) {
				if (_move.getFirstTile() != null) {
					if (_move.getAvailableExplorer() > 0) {
						_move.setAvailableExplorer(_move.getAvailableExplorer() - 1);
						_move.setCpActionPoints(_move.getCpActionPoints() - 2);
						this.RefreshStats();
						_move.getFirstTile()
								.setTexttoExplorers(
										_move.getFirstTile().tileData
												.GetExplorers(_move
														.getCurrentPlayer()
														.getID()) + 1,
										_move.getCurrentPlayer().getID());
					} else {
						JOptionPane
								.showMessageDialog(null, "No More Explorers");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"A Tile must be placed first!");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"You need more Action Points!");
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"You must place your Tile fisrt");
		}
	}

	public void MoveExplorerClick(MouseEvent e) {
		if (previewTile.tileData == blank)// checks to see if a tile is pending
											// placement
		{
			// updates move to the moveing an explorer state
			UpdateInstructions(2);
			_move.setMoves(2);
			// System.out.println("Moved and Explorer!");
		} else {
			JOptionPane.showMessageDialog(null,"You must place your tile first");
		}
	}

	public void ClockwiseClick(MouseEvent e) {
		if (previewTile.tileData != blank)// checks to make sure there is a tile
											// pending placement
		{
			// rotates the tile that is pending placement
			previewTile.RotateClockwise();
			tileImg.repaint();
			_move.setNexttoPlace(previewTile);
			// System.out.println("Rotated clockwise!");
		} else {
			JOptionPane.showMessageDialog(null,"No preview to rotate, Click 'Place a Tile' first.");
		}
	}

	public void CounterClick(MouseEvent e) {
		if (previewTile.tileData != blank)
			// checks to make sure there is a tile pendinig placement
		{
			previewTile.RotateCounterClockwise();
			tileImg.repaint();
			_move.setNexttoPlace(previewTile);
			// System.out.println("Rotated counterclockwise!");
		} else {
			JOptionPane.showMessageDialog(null,
					"No preview to rotate, Click 'Place a Tile' first.");
		}
	}

	public void NextClick(MouseEvent e) {
		if (previewTile.tileData == blank)// checks if there is a tile pending
											// placement
		{
			if (_move.getCurrentTilePlaced() == this._numberOfTiles)
			{
				// ends the game and calculates score
				score();
			} else {
				// changes the player
				current = _player[(current.getID() + 1) % _player.length];
				_move.setCpActionPoints(12);
				_move.setPyramidsThisTurn(0);
			}
			_move.setCurrentPlayer(current);
			playerName.setText(_move.getCurrentPlayer().getName());
			_move.setMoves(0);
			if (_move.GetVolcano()) {
				score();
				_move.SetVolcano(false);
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"You must place your Tile first!");
		}
		this.RefreshStats();
	}

	public void EndClick(MouseEvent e) {
		// ends the game and calculates score
		score();
	}
	// end of button handling methods

	// methods that refresh the menu portion of the GUI
	public void Clear() {
		// clears the preview after the tile is placed
		previewTile.setTileData(blank);
		tileImg.remove(previewTile);
		preview.repaint();
	}

	public void RefreshStats() {
		// updates the stats menu with current stats
		// should be called after each action taken
		UpdateInstructions(0);
		this.playerName.setText(_move.getCurrentPlayer().getName());
		playerPoints.setText(String.valueOf(_move.getCpActionPoints()));
		playerExplorers.setText(String.valueOf(_move.getAvailableExplorer()));
		currentlyExploring.setText(String.valueOf(10 - _move
				.getAvailableExplorer()));
		stats.repaint();
	}

	public void UpdateInstructions(int i) {
		// updates the instructions based on what is clicked
		int instructionState = i;

		switch (instructionState) {
		case 0:
			instructions.setText("");
			break;
		case 1:
			instructions.setText("Click where you would like to place");
			break;
		case 2:
			instructions.setText("Click where you want to move from");
			break;
		case 3:
			instructions.setText("Click where you want to move to");

		}
	}

	// Method that creates the terrain tiles for the game
	public void CreateTileData() {
		gameTileData = new TileData[_numberOfTiles];
		ArrayList<TileData> gameTileDataTemp = new ArrayList<TileData>();
		TileData Cur;

		for (int i = 0; i < _numberOfTiles; i++) {
			if (i < _size + _numberOfPlayers) {
				// creates 8 tiles with pyramid bases
				Cur = new TileData(0, 0, RandomTilePaths(), true, false, false,
						_numberOfPlayers);
				gameTileDataTemp.add(Cur);
			} else if (i >= _size + (_numberOfPlayers)
					&& i < _size + (_numberOfPlayers * 2)) {
				int[] temp = new int[] { 0, 0, 0, 0, 0, 0 };
				Cur = new TileData(0, 0, temp, false, false, true,
						_numberOfPlayers);
				gameTileDataTemp.add(Cur);
			} else {
				// creates the rest of the tiles without pyramid bases
				Cur = new TileData(0, 0, RandomTilePaths(), false, false,
						false, _numberOfPlayers);
				gameTileDataTemp.add(Cur);
			}
		}

		// shuffles the array so the tiles are drawn in random order
		Collections.shuffle(gameTileDataTemp);

		for (int w = 0; w < _numberOfTiles; w++) {
			gameTileData[w] = gameTileDataTemp.get(w);
		}
	}

	public int[] RandomTilePaths() {
		Random rng = new Random();
		// creates an array of 6 ints to determine the number of paths
		int[] paths = new int[6];
		ArrayList<Integer> pathsList = new ArrayList<Integer>();
		pathsList.add(rng.nextInt(2) + 1);
		for (int j = 0; j < 3; j++) {
			pathsList.add(0);
		}
		for (int k = 0; k < 2; k++) {
			// adds 3 random numbers (0-2) to determine the remaining sides and
			// how many paths they have
			pathsList.add(rng.nextInt(3));
		}

		Collections.shuffle(pathsList);

		for (int q = 0; q < 6; q++) {
			paths[q] = pathsList.get(q);
		}

		return paths;
	}

	public void score() {
		for (int x = 0; x < _size; x++) {
			if (x % 2 == 0) {
				for (int y = 0; y < _size - 1; y++) {
					TileData temp = _BoardData[x][y];
					int large_ID = 0;
					int second = 0;
					int large = temp.GetExplorers(0);
					//System.out.print(" P"+0+": "+ temp.GetExplorers(0));
					for (int i = 1; i < _player.length; i++) {
						if (large <= temp.GetExplorers(i) && temp.GetExplorers(i) != 0) {
							second = large;
							large_ID = i;
							large = temp.GetExplorers(i);
						}
						//System.out.print(" P"+i+": "+ temp.GetExplorers(i));
					}
					if (temp.PM.getValue() == -1 && large != 0 && large != second) {
						// checks if there is a pyramid on the tile being scored
						_player[large_ID]
								.setScore(_player[large_ID].getScore() + 1);
					} 
					else if (temp.PM.getValue() > 0 && large != 0&& large != second)// checks the value the pyramid
						// on the tile bing scored
						_player[large_ID].setScore(_player[large_ID].getScore()
								+ temp.PM.getValue());
					else;
					//System.out.println(" largeID: "+ large_ID+ " score: "+ _player[large_ID].getScore());
				}
			} else {
				for (int y = 0; y < _size; y++) {
					TileData temp = _BoardData[x][y];
					int large_ID = 0;
					int large = temp.GetExplorers(0);
					int second = 0;
					//System.out.print(" P"+0+": "+ temp.GetExplorers(0));
					for (int i = 1; i < _player.length; i++) {
						if (large <= temp.GetExplorers(i) && temp.GetExplorers(i) != 0) {
							second = large;
							large_ID = i;
							large = temp.GetExplorers(i);
						}
						//System.out.print(" P"+i+": "+ temp.GetExplorers(i));
					}
					if (temp.PM.getValue() == -1 && large != 0 && large != second) {
						_player[large_ID].setScore(_player[large_ID].getScore() + 1);
					} 
					else if (temp.PM == null);
					else if (temp.PM.getValue() > 0 && large != 0
							&& large != second)// checks the value the pyramid
												// on the tile bing scored
						_player[large_ID].setScore(_player[large_ID].getScore()
								+ temp.PM.getValue());
					else;
					//System.out.println(" largeID: "+ large_ID+ " score: "+ _player[large_ID].getScore());
				}
			}
		}
		JOptionPane.showMessageDialog(null, Sostring());
		for(int i =0;i< this._numberOfPlayers;i++)_player[i].setScore(0);
	}

	public String Sostring() {
		String s = "";
		for (int i = _player.length; i > 0; i--) {
			s = "Player " + i + " scroe: " + _player[i - 1].getScore() + "\n"
					+ s;
		}
		return s;
	}

}
