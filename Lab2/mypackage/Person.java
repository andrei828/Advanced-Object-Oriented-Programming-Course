package prob2;


public class Person {

	private String age;
	private String name;
	private String type;
	private String surname;
	private long identityNumber;

	public Person(String name, String surname, String age, 
		long identityNumber, String type) {
		
		this.age = age;
		this.name = name;
		this.type = type;
		this.surname = surname;
		this.identityNumber = identityNumber;
		
	}

	@Override
	public String toString() {
		return name + " " + surname + " " + age + " " + type + " " + identityNumber;
	}
	
	public String getAge() { return age; }
	public String getName() { return name; }
	public String getType() { return type; }
	public String getSurname() { return surname; }
	public long getIdentityNumber() { return identityNumber; }

	public void setAge(String age) { this.age = age; }
	public void setName(String name) { this.name = name; }
	public void setType(String type) { this.type = type; }
	public void setSurname(String surname) { this.surname = surname; }
	public void setIdentityNumber(long identityNumber) {this.identityNumber = identityNumber; }

}