import java.util.Scanner;

class Lab1 {
	public static void main(String[] args) {
		System.out.println(Prob6(55));
	}

	public static void Prob1() {
		for (int i = 1; i < 100; i += 2) {
			System.out.println(i);
		}
	}

	public static void Prob2() {
		Scanner scanner = new Scanner(System.in);

		int firstNumber = scanner.nextInt();
		int secondNumber = scanner.nextInt();
		
		boolean equal = (firstNumber == secondNumber);

		// equality
		System.out.print(firstNumber + " ");
		if (!equal) System.out.print ("!");  
		System.out.println("= " + secondNumber);

		// comparing
		System.out.print(firstNumber + " ");
		if (firstNumber < secondNumber) 
			System.out.print("<");
		else if (firstNumber > secondNumber)
			System.out.print(">"); 
		System.out.println(" " + secondNumber);

		// comparing with equality
		// comparing
		System.out.print(firstNumber + " ");
		if (firstNumber < secondNumber) 
			System.out.print("<");
		else if (firstNumber > secondNumber)
			System.out.print(">");
		System.out.println("= " + secondNumber);
	}


	public static int Prob3(int n) {
		int sum = 0;
		for (int i = 1; i < n; i++) {
			if ((i % 3 != 0) || (i % 5 != 0)) {
				sum += i;
			}
		}
		return sum;
	}

	public static int Prob4(int n) {
		int factorial = 1;
		for (int i = 2; i < n; i++) 
			factorial *= i;
		return factorial;
	}

	public static boolean Prob5(int n) {
		for (int i = 2; i * i < n; i++)
			if (n % i != 0)
				return false;
		return true;
	}

	public static int Prob6(int n) {
		return Math.round(2.078087F * (float) Math.log(n) + 1.672276F); 
	}

	public static int Prob7(int n) {
		int i, copy = n; 
		for (i = 2; i <= copy; i++) { 
			if (copy % i == 0) { 
				copy /= i; 
				i--; 
			} 
		} 
		return i;
	}
}