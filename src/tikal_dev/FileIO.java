package tikal_dev;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileIO extends JFrame {
	Player[] _player;
	Move _move;
	TileData[][] _boardData;
	int _numberOfP;
	int _size;
	int _numberOfTiles;
	JButton button;
	JFileChooser chooser;
	final String cancelButtonTextKey;
	PrintWriter out;

	public FileIO(Player[] player, Move move, TileData[][] boardData,
			int numberOfPlayers, int size, int numberOfTiles) {
		_player = player;
		_move = move;
		_boardData = boardData;
		_numberOfP = numberOfPlayers;
		_size = size;
		_numberOfTiles = numberOfTiles;
		chooser = new JFileChooser();
		cancelButtonTextKey = "FileChooser.cancelButtonText";
		// chooser.setAcceptAllFileFilterUsed(false);
	}

	public void read() {
		UIDefaults uid = UIManager.getDefaults();
		uid.put(cancelButtonTextKey, "Canel");
		SwingUtilities.updateComponentTreeUI(chooser);
		chooser.setFileFilter(new FileNameExtensionFilter("Text file", "txt"));
		chooser.setCurrentDirectory(new File("./"));
		chooser.setDialogTitle("Load file ");
		chooser.setFileHidingEnabled(false);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setApproveButtonText("Load");
		int confirm = chooser.showOpenDialog(null);
		if (confirm == chooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null,
					"Your file: " + chooser.getSelectedFile() + "\n,in "
							+ chooser.getCurrentDirectory() + ".", "Message",
					JOptionPane.INFORMATION_MESSAGE);

			File file = chooser.getSelectedFile();
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(new File(
						file.getAbsolutePath())));
				String str = new String("");
				String tmp;
				int i = 0, x = 0, y = 0;
				int numP = Integer.valueOf(br.readLine());
				int size = Integer.valueOf(br.readLine());
				int numT = Integer.valueOf(br.readLine());
				while ((tmp = br.readLine()) != null) {
					// read player
					if (i < numP) {
						_player[i].setName(tmp);
						tmp = br.readLine();
						_player[i].setActionPoints(Integer.valueOf(tmp));
						tmp = br.readLine();
						_player[i].setAvailableExplores(Integer.valueOf(tmp));
						tmp = br.readLine();
						_player[i].setID(Integer.valueOf(tmp));
						tmp = br.readLine();
						_player[i].setScore(Integer.valueOf(tmp));
						_player[i].setNextTile(null);
					}
					// read move
					if (i == this._numberOfP) {
						_move.setMoves(Integer.valueOf(tmp));
						tmp = br.readLine();
						_move.setCurrentTilePlaced(Integer.valueOf(tmp));
						tmp = br.readLine();
						_move.setPyramidsThisTurn(Integer.valueOf(tmp));
						tmp = br.readLine();
						_move.setPyrimidNumber(0, Integer.valueOf(tmp));
						tmp = br.readLine();
						_move.setPyrimidNumber(1, Integer.valueOf(tmp));
						tmp = br.readLine();
						_move.setPyrimidNumber(2, Integer.valueOf(tmp));
						tmp = br.readLine();
						_move.setPyrimidNumber(3, Integer.valueOf(tmp));
						tmp = br.readLine();
						_move.SetVolcano(Boolean.valueOf(tmp));
						tmp = br.readLine();
						_move.setCurrentPlayer(_player[Integer.valueOf(tmp)]);
						_move.getCurrentPlayer().getActionPoints();
						_move._size = size;
						_move._numberOfPlayers = numP;
						tmp = br.readLine();
						if (tmp == "null")
							_move.setFirstTileNull();
						else {
							// FirstTile data
							// paths
							int[] temppath = new int[6];
							for (int j = 0; j < 6; j++) {
								temppath[j] = Integer.valueOf(tmp);
								tmp = br.readLine();
							}
							// number of Explorers
							int NumEp = Integer.valueOf(tmp);
							// x and y
							tmp = br.readLine();
							int LocX = Integer.valueOf(tmp);
							tmp = br.readLine();
							int LocY = Integer.valueOf(tmp);
							// volcano
							tmp = br.readLine();
							Boolean volcano = Boolean.valueOf(tmp);
							// empty
							tmp = br.readLine();
							Boolean empty = Boolean.valueOf(tmp);
							// Pyramid
							tmp = br.readLine();
							Boolean pyramid = Boolean.valueOf(tmp);
							TileData tmpTile = new TileData(LocX, LocY,
									temppath, pyramid, empty, volcano, NumEp);
							if (pyramid) {
								tmp = br.readLine();
								tmpTile.PM.setValue(Integer.valueOf(tmp));
							}
							// explorers
							for (int j = 0; j < this._numberOfP; j++) {
								tmp = br.readLine();
								System.out.println("j: " + j + " tmp: " + tmp);
								tmpTile.SetExplorer(j, Integer.valueOf(tmp));
							}
							_move.setFirstTile(new Tile(tmpTile));
						}
					}
					if (i > numP) {
						// Tiledata
						// paths
						int[] temppath = new int[6];
						for (int j = 0; j < 6; j++) {
							temppath[j] = Integer.valueOf(tmp);
							tmp = br.readLine();
						}
						// number of Explorers
						int NumEp = Integer.valueOf(tmp);
						// x and y
						tmp = br.readLine();
						int LocX = Integer.valueOf(tmp);
						tmp = br.readLine();
						int LocY = Integer.valueOf(tmp);
						// volcano
						tmp = br.readLine();
						Boolean volcano = Boolean.valueOf(tmp);
						// empty
						tmp = br.readLine();
						Boolean empty = Boolean.valueOf(tmp);
						// System.out.println("Empty: "+empty);
						// Pyramid
						tmp = br.readLine();
						Boolean pyramid = Boolean.valueOf(tmp);
						TileData tmpTile = new TileData(LocX, LocY, temppath,
								pyramid, empty, volcano, NumEp);
						if (pyramid) {
							tmp = br.readLine();
							tmpTile.PM.setValue(Integer.valueOf(tmp));
						}
						// explorers
						for (int j = 0; j < this._numberOfP; j++) {
							tmp = br.readLine();

							tmpTile.SetExplorer(j, Integer.valueOf(tmp));
							System.out.println("j: " + j + " tmp: "
									+ tmpTile.GetExplorers(j));
						}
						System.out.println("X: " + x + " Y: " + y);
						_move.getBoard()[x][y].setTileData(tmpTile);
						y++;
						if (x % 2 == 0 && y == size - 1) {
							x++;
							y = 0;
						} else if (x % 2 == 1 && y == size) {
							x++;
							y = 0;
						}
					}

					i++;
				}

				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void write() {
		UIDefaults uid = UIManager.getDefaults();
		uid.put(cancelButtonTextKey, "Canel");
		SwingUtilities.updateComponentTreeUI(chooser);
		chooser.setFileFilter(new FileNameExtensionFilter("Text file", "txt"));
		chooser.setCurrentDirectory(new File("./"));
		chooser.setDialogTitle("Save file");
		chooser.setFileHidingEnabled(false);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setApproveButtonText("Save");
		int confirm = chooser.showSaveDialog(null);
		if (confirm == chooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null,
					"Your file: " + chooser.getSelectedFile() + "\n,in "
							+ chooser.getCurrentDirectory() + ".", "Message",
					JOptionPane.INFORMATION_MESSAGE);

			File file = chooser.getSelectedFile();
			File file_m;
			String filepath = file.getAbsolutePath();
			try {
				boolean isFile = false;
				if (!file.exists())
					isFile = file.createNewFile();
				if (!file.getName().toLowerCase().endsWith(".txt")) {
					file_m = new File(filepath + ".txt");
					file_m.createNewFile();
					file.delete();
					boolean success = file.delete();
				} else {
					file_m = new File(filepath);
					file_m.createNewFile();
					file.delete();
				}
				FileWriter outFile = new FileWriter(file_m);
				out = new PrintWriter(outFile, true);
				// save all data!!!!!!!!!!!!!
				// players
				out.println(this._numberOfP);
				out.println(this._size);
				out.println(this._numberOfTiles);
				for (int i = 0; i < this._numberOfP; i++) {
					out.println(_player[i].getName());
					out.println(_player[i].getActionPoints());
					out.println(_player[i].getAvailableExplores());
					out.println(_player[i].getID());
					out.println(_player[i].getScore());
					// out.println(_player[i].getNextTile());
				}
				// move
				out.println(_move.getMoves());
				out.println(_move.getCurrentTilePlaced());
				out.println(_move.getPyramidsThisTurn());
				out.println(_move.getPyrimidNumber(0));
				out.println(_move.getPyrimidNumber(1));
				out.println(_move.getPyrimidNumber(2));
				out.println(_move.getPyrimidNumber(3));
				out.println(_move.GetVolcano());
				out.println(_move.getCurrentPlayer().getID());
				if (_move.getFirstTile() == null)
					out.println(_move.getFirstTile());
				else
					this.accessdata(_move.getFirstTile().getTileData());
				// tileData
				for (int i = 0; i < _size; i++) {
					if (i % 2 == 0)
						for (int j = 0; j < _size - 1; j++) {
							this.accessdata(this._boardData[i][j]);
						}
					else
						for (int j = 0; j < _size; j++) {
							this.accessdata(this._boardData[i][j]);
						}
				}
				out.close();

			} catch (IOException e) {
				System.out.println("you all really wrong!!!!!!");
			}
		}
	}

	public void accessdata(TileData td) {

		// paths
		for (int i = 0; i < 6; i++)
			out.println(td.getPathValue(i));
		// number of Explorers
		out.println(td.getnumofExplorers());
		// x and y
		out.println(td.GetX());
		out.println(td.GetY());
		// vocano
		out.println(td._volcano);
		// exmpty
		out.println(td._empty);
		// Pyramid
		out.println(td._PMV);
		if (td._PMV)
			out.println(td.PM.currentValue);
		// explorers
		for (int i = 0; i < td.getnumofExplorers(); i++)
			out.println(td.GetExplorers(i));

	}

}
