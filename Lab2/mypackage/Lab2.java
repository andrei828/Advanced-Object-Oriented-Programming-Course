import prob3.Room;
import prob2.Person;
import prob4.Subject;
import java.util.Scanner;

class Lab2 {

	public static void main(String[] args) {
		// prob1();

		Person person1 = new Person("John", "Doe", "24", 1123444, "student");
		Person person2 = new Person("Jane", "Roe", "56", 2233444, "teacher");

		Room room1 = new Room("12A", "normal", 3);
		Room room2 = new Room("12B", "tech", 7);

		System.out.println(room1);
		System.out.println(room2);

		Subject subject1 = new Subject(room1, person1, 21);
		Subject subject2 = new Subject(room2, person2, 32);

		System.out.println(subject1);
		System.out.println(subject2);
	}

	public static void prob1() {

		int[] studentGrades = new int[25];
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		
		for (int i = 0; i < studentGrades.length; i++) {
			int value = scanner.nextInt();
			if (value == -1) {
				break;
			} else {
				studentGrades[i] = value;
				sum += value;
			}
		}

		System.out.println("The grades average is: " + sum / studentGrades.length);

	}
}