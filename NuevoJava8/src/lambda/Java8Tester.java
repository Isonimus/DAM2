package lambda;

public class Java8Tester {
	public static void main(String args[]) {
		Java8Tester tester = new Java8Tester();

		// 1. with return statement along with curly braces
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// 2. with type declaration
		MathOperation division = (int a, int b) -> {
			return a / b;
		};

		// 3. without return statement and without curly braces
		MathOperation addition = (int a, int b) -> a + b;

		// with out type declaration
		MathOperation subtraction = (a, b) -> a - b;

		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));

		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));

		System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));

		System.out.println("10 / 5 = " + tester.operate(10, 5, division));

		// without parenthesis
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		// with parenthesis
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Mahesh");
		greetService2.sayMessage("Suresh");
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}
