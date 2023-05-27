import java.util.Scanner;
import java.util.*;

class Main{
	static String[] oparation_array = {"+", "-", "*", "/"};
    static int[] intervals={0, 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    static String[] numerals={"", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		String input = new String();
		System.out.print("input:");
		input = in.nextLine();
		System.out.printf("output: %s\n\n", calc(input));
	};


	public static String calc(String input){
		String[] input_segmants = divideIntoComponents(input);
		String string_a = input_segmants[0].trim();
		String string_b = input_segmants[2].trim();
		System.out.printf(" str a = '%s'\n str b = '%s'\n", string_a, string_b);

		int a = toArabic(string_a);
		int b = toArabic(string_b);
		var aIsRoman = a == 0;
		var bIsRoman = b == 0;

		if (aIsRoman){
			a = Integer.valueOf(string_a);
		}	
		if (bIsRoman){
			b = Integer.valueOf(string_b);
		}

		if (aIsRoman != bIsRoman){
			return "different number systems are used simultaneously";
		}

		String oparation = input_segmants[1];
		System.out.printf(" a = %s\n b = %s\n op = %s\n", a, b, oparation);

		int output = count(a, b, oparation);
		return String.valueOf(output);

	};


	static String[] divideIntoComponents(String string){
		String[] array = {};
		for (int i = 0; i < oparation_array.length; i++){
			array = string.split(String.format("\\%s", oparation_array[i]));
			System.out.printf("%s = %s | (%d) | %s\n", string, array, array.length, oparation_array[i]);
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
        int result = 0;
        for (int i = intervals.length-1; i >= 0; i-- ) {
            while (roman.indexOf(numerals[i]) == 0 && numerals[i].length() > 0) {
                result += intervals[i];
                roman = roman.substring(numerals[i].length());
            }
        }
        // if (result == 0){
        // return Integer.valueOf(roman);
        // }
        // else{
        // 	return result;
        // }
        return result;
    }
}

// cd /D E:\Docks\Godot Progects\Calculator_Java\