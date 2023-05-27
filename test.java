import java.util.Scanner;
import java.util.Arrays;

class Main{
	static String[] oparation_array = {"+", "-", "*", "/"};


	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		String input = new String();
		System.out.print("input:");
		input = in.nextLine();
		System.out.printf("output: %s\n\n", calc(input));
	};


	public static String calc(String input){
		String[] input_segmants = divideIntoComponents(input);
		var string_a = input_segmants[0].trim();
		var string_b = input_segmants[2].trim();

		int a = convertInt(string_a);
		int b = convertInt(string_b);

		String oparation = input_segmants[1];
		System.out.printf(" a = %s\n b = %s\n op = %s\n", a, b, oparation);

		int output = count(a, b, oparation);
		return convertString(output);

	};


	static String[] divideIntoComponents(String string){
		String[] array = {};
		for (int i = 0; i < oparation_array.length; i++){
			array = string.split(String.format("\\%s", oparation_array[i]));
			// System.out.printf("%s = %s | (%d) | %s\n", string, array, array.length, oparation_array[i]);
			if (array.length == 2){
				String[] r_array = new String[3];
				r_array[0] = array[0];
				r_array[1] = String.valueOf(oparation_array[i]);
				r_array[2] = array[1];
				return r_array;				
			}

		};
		return null;
	}


	static int count(int a, int b, String oparation){
		switch (oparation){
			case "+": return a + b;
			case "-": return a - b;
			case "*": return a * b;
			case "/": return a / b;
		};	
		return 0;
	};
	

	static int convertInt(String input){
		return  Integer.valueOf(input);
	};


	static String convertString(int input){
		return  String.valueOf(input);
	};

}

/// cd /d E:\Java ТЗ
// java test.java