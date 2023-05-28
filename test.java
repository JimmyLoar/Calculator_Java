import java.util.Scanner;
import java.util.*;

// cd /D E:\Docks\Godot Progects\Calculator_Java\

class Main{
	static enum ERR {
		err_ok, err_noOperation, err_format, err_negativeNumbers, err_differentNumbers, err_arabicRange, err_romanRange
	};
	static boolean isDebug = true;
	static String[] oparation_array = {"+", "-", "*", "/"};
    static int[] intervals={0, 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    static String[] numerals={"", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		String input = new String();

		while (true){
			System.out.print("input: ");
			input = in.nextLine();
			if (input.equals("exit") || input.equals("stop") || input.equals("break")) {
				System.out.println("exited...");
				System.exit(0);
			}
			else {
				System.out.printf("output: %s\n\n", calc(input));
			}
		}
	};


	public static String calc(String input){
		String[] input_segmants = divideIntoComponents(input);

		try{
		input_segmants[0].trim();
		input_segmants[2].trim();

		} catch(NullPointerException err){
			debugPrint("throws Exception //err trim");
			pushError(ERR.err_format);
		};


		String string_a = input_segmants[0].trim();
		String string_b = input_segmants[2].trim();
		String oparation = input_segmants[1];


		if (string_a == "" || string_b == "") {
			pushError(ERR.err_format);
		}

		debugPrint(String.format("	str a = '%s'\n	str b = '%s'\n", string_a, string_b));

		int a = toArabic(string_a);
		int b = toArabic(string_b);
		var aIsArabic = a == -1;
		var bIsArabic = b == -1;

		if (aIsArabic){
			try{Integer.valueOf(string_a);} 
			catch (Exception e) {pushError(ERR.err_format);}
			a = Integer.valueOf(string_a);
		}	
		if (bIsArabic){
			try{Integer.valueOf(string_a);} 
			catch (Exception e) {pushError(ERR.err_format);}
			b = Integer.valueOf(string_b);
		}

		debugPrint(String.format("	a = %s\n	b = %s\n	op = %s\n", a, b, oparation));

		if (aIsArabic != bIsArabic){
			pushError(ERR.err_differentNumbers);
		}

		else if (!aIsArabic) {
			if (oparation == "-" && a < b) {
				pushError(ERR.err_negativeNumbers);


			}
			else if ((oparation == "/" || oparation == "*") && (a <= 0 || b <= 0)) {
				pushError(ERR.err_negativeNumbers);

			}
		}

		if (a <= 0 || a > 10) {
			if (aIsArabic) {
				pushError(ERR.err_arabicRange);
				
			}
			pushError(ERR.err_romanRange);
		}
		else if (b <= 0 || b > 10) {
			if (bIsArabic) {
				pushError(ERR.err_arabicRange);
				
			}
			pushError(ERR.err_romanRange);
		}
		
		int output = count(a, b, oparation);
		if (!aIsArabic) {
			return toRoman(output);
		}
		return String.valueOf(output);
	};


	static void debugPrint(String text){
		if (isDebug) {
			System.out.println(text);
		}
	}


	static void pushError(ERR error_type){
		debugPrint(String.format("[error %s]", error_type));	
		switch(error_type){
		case err_format:
			System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
			break;
		case err_differentNumbers:
			System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
			break;
		case err_negativeNumbers:
			System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
			break;
		case err_arabicRange:
			System.out.println("throws Exception //т.к. операнда 'a' выходят за приделы диапозона (0, 10)");
			break;
		case err_romanRange:
			System.out.println("throws Exception //т.к. операнда 'a' выходят за приделы диапозона (I, X)");
			break;
		case err_noOperation:
			System.out.println("throws Exception //т.к. строка не является математической операцией");
			break;
		};
		System.exit(0);
	}


	static String[] divideIntoComponents(String string){
		String[] array = {};
		String[] r_array = new String[3];

		int amount_op = 0;

		for (int i = 0; i < oparation_array.length; i++){
			array = string.split(String.format("\\%s", oparation_array[i]));
			debugPrint(String.format("	'%s' | %s | (%d) | %s\n", string, array, array.length, oparation_array[i]));
			if (array.length == 2){
				r_array[0] = array[0];
				r_array[1] = String.valueOf(oparation_array[i]);
				r_array[2] = array[1];
				amount_op ++;
				continue;		

			} else if (array.length > 2) {
				return new String[3];
			}

		};
		if (amount_op > 1) {
			return new String[3];
		}
		else if (amount_op < 1) {
			pushError(ERR.err_noOperation);
		}
		return r_array;
	};


	static int count(int a, int b, String oparation){
		switch (oparation){
			case "+": return a + b;
			case "-": return a - b;
			case "*": return a * b;
			case "/": return a / b;
		};	
		return 0;
	};


// Taken from https://ru.stackoverflow.com/questions/1234189/Как-преобразовать-римские-цифры-в-арабские-на-java
	static int findFloor(final int number, final int firstIndex, final int lastIndex) {
        if(firstIndex==lastIndex)
            return firstIndex;
        if(intervals[firstIndex]==number)
            return firstIndex;
        if(intervals[lastIndex]==number)
            return lastIndex;
        final int median=(lastIndex+firstIndex)/2;
        if(median==firstIndex)
            return firstIndex;
        if(number == intervals[median])
            return median;
        if(number > intervals[median])
            return findFloor(number, median, lastIndex);
        else
            return findFloor(number, firstIndex, median);

    };

    static String toRoman(final int number) {
        int floorIndex=findFloor(number, 0, intervals.length-1);
        if(number==intervals[floorIndex])
            return numerals[floorIndex];
        return numerals[floorIndex]+toRoman(number-intervals[floorIndex]);
    }
    
    static int toArabic(String roman) {
        int result = -1;
        for (int i = intervals.length-1; i >= 0; i-- ) {
            while (roman.indexOf(numerals[i]) == 0 && numerals[i].length() > 0) {
                result += intervals[i];
                roman = roman.substring(numerals[i].length());
            }
        }
        if (result != -1){
        	result ++;
        }
        return result;
    }
}

