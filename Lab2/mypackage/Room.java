package prob3;

public class Room {

	private int floor;
	private String type;
	private String number;

	public Room(String number, String type, int floor) {
		this.type = type;
		this.floor = floor;
		this.number = number;
	}

	@Override
	public String toString() {
		return number + " " + type + " " + floor;
	}
	
	public int getFloor() { return floor; }
	public String getType() { return type; }
	public String getNumber() { return number; }
	
	public void setType(String type) { this.type = type; }
	public void setFloor(int floor) { this.floor = floor; }
	public void setNumber(String number) { this.number = number; }
}