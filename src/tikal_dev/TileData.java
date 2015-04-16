package tikal_dev;

import java.util.ArrayList;

public class TileData {

	// Pyramid Initialized to Null
	Pyramid PM;
	boolean _PMV;

	// Location of the Tile
	int LocX, LocY;

	// Size of path in each six directions;
	int[] _paths;

	// Values of Player Explorers on this tile

	int[] explorers;
	static int _numofExplorers;
	boolean _empty;
	boolean _volcano;

	public TileData(int x, int y, int[] paths, boolean PMV, boolean empty,
			boolean volcano, int numofExplorers) {
		PM = new Pyramid(-1);
		LocX = x;
		LocY = y;
		_paths = paths;
		_PMV = PMV;
		_empty = empty;
		_volcano = volcano;
		_numofExplorers = numofExplorers;
		explorers = new int[numofExplorers];
	}

	public int getPathValue(int i) {
		return _paths[i];
	}

	public int GetX() {
		return LocX;
	}

	public void SetX(int x) {
		LocX = x;
	}

	public int GetY() {
		return LocY;
	}

	public void SetY(int y) {
		LocY = y;
	}

	public void SetExplorer(int playerIndex, int e) {
		explorers[playerIndex] = e;
	}

	public int GetExplorers(int e) {
		return explorers[e];

	}

	// Rotates the wall array counter clockwise and updates the visuals
	public void RotateClockwise() {
		int temp = _paths[5];
		_paths[5] = _paths[4];
		_paths[4] = _paths[3];
		_paths[3] = _paths[2];
		_paths[2] = _paths[1];
		_paths[1] = _paths[0];
		_paths[0] = temp;
		// UpdatePaths();

	}

	// Rotates the wall array counter clockwise and updates the visuals
	public void RotateCounterClockwise() {
		int temp = _paths[0];
		_paths[0] = _paths[1];
		_paths[1] = _paths[2];
		_paths[2] = _paths[3];
		_paths[3] = _paths[4];
		_paths[4] = _paths[5];
		_paths[5] = temp;
		// UpdatePaths();

	}

	public int getnumofExplorers() {
		return _numofExplorers;
	}

}
