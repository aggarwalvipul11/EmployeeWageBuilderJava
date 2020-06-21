import java.util.Random;
public class EmployeeWageUC1 {
	public static void main(String[] args) {
		Random rand = new Random();
		int empCheck = rand.nextInt(2);
		if (empCheck == 1) 
			System.out.println("Employee is Present");
		else
			System.out.println("Employee is Abscent");
	}
}

