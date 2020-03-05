package prob4;

import prob3.Room;
import prob2.Person;

public class Subject {

	private Room room;
	private Person teacher;
	private int noOfStudents;

	public Subject(Room room, Person teacher, int noOfStudents) {
		this.room = room;
		this.teacher = teacher;
		this.noOfStudents = noOfStudents;
	}

	@Override
	public String toString() {
		return room.toString() + " " + teacher.toString() + " " + noOfStudents;
	}

	public void setRoom(Room room) { this.room = room; }
	public void setPerson(Person teacher) { this.teacher = teacher; }
	public void setNoOfStudents(int noOfStudents) { this.noOfStudents = noOfStudents; }

	public Room getRoom() { return room; }
	public Person getTeacher() { return teacher; }
	public int getNoOfStudents() { return noOfStudents; } 

}