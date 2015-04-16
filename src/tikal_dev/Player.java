package tikal_dev;

public class Player {
	private String _Name;
	private int _ActionPoints;
	private int _AvailableExplores;
	private int _Score;
	private Tile _Next_to_place;
	private int _ID;
	public Player(String name, int id){
		_ID = id;
		_Name = name;
		_ActionPoints = 12;
		_AvailableExplores = 10;
		_Next_to_place = null;
		_Score = 0;
	}
	
	public int getActionPoints(){
		return _ActionPoints;
	}
	
	public void setActionPoints(int AP){
		_ActionPoints = AP;
	}
	
	public void setNextTile(Tile tile){
		_Next_to_place = tile; 
	}
	
	public Tile getNextTile(){
		return _Next_to_place;
	}
	public String getName(){
		return _Name;
	}
	public int getAvailableExplores(){
		return _AvailableExplores;
	}
	
	public void setAvailableExplores(int AE){
		_AvailableExplores = AE;
	}
	public int getScore(){
		return _Score;
	}
	public void setScore(int val){
		_Score = val;
	}
	public int getID(){
		return _ID;
	}
	public void setName(String name){
		this._Name = name;
	}
	public void setID(int id){
		_ID = id;
	}
}
