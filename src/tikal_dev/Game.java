package tikal_dev;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * 
 * @authors Yuhui Wu William Henry Jessica Dorismond ChunWei Chang Vicky Zheng
 */

public class Game {

	TileData[][] BoardData;
	Move _move;
	Menu MU;
	GUI _GUI;
	int size;
	int rows;
	int numberOfPlayers;

	JFrame TF;
	JPanel MN;
	ArrayList<JPanel> THA = new ArrayList<JPanel>();
	Player[] _player;
	int numberOfTiles;

	public Game(String args[]) {
		// Creates a new 2d storage array for tile data [x][y]
		numberOfPlayers = args.length;
		size = ((((int) Math.sqrt(60 * numberOfPlayers)) / 2) + 1);
		BoardData = new TileData[size][];
		InitBoardData();
		_move = new Move(numberOfPlayers, size);
		_player = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			_player[i] = new Player(args[i], i);
		}
		_move.setCurrentPlayer(_player[0]);
		MU = new Menu(_player, _move, BoardData, numberOfPlayers, size,
				numberOfTiles);
		_GUI = new GUI(MU, BoardData, _move, _player, size);
	}

	public void InitBoardData() {

		// Adding columns in t
		for (int x = 0; x < size; x++) {
			if (x % 2 == 0) {
				TileData[] col = new TileData[size - 1];
				for (int y = 0; y < size - 1; y++) {
					int[] none = new int[] { 0, 0, 0, 0, 0, 0 };
					TileData unplaced = new TileData(x, y, none, false, true,
							false, numberOfPlayers);
					col[y] = unplaced;
				}
				BoardData[x] = col;
			} else {
				TileData[] col = new TileData[size];
				for (int y = 0; y < size; y++) {
					int[] none = new int[] { 0, 0, 0, 0, 0, 0 };
					TileData unplaced = new TileData(x, y, none, false, true,
							false, numberOfPlayers);
					col[y] = unplaced;
				}
				BoardData[x] = col;
			}

		}
		for (TileData[] ta : BoardData) {
			for (TileData td : ta) {
				numberOfTiles++;
			}

		}
	}
	

	public static void main(final String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Game(args);
			}
		});
	}
}
