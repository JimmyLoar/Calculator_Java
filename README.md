# Calculator_Java
This is a test assignment for kata.academy

Description:
Create a console application called "Calculator". The application must read from the console the strings, numbers, arithmetic operations between them and output the result to the console.
Implement class Main with method public static String calc(String input). The method should accept a string with an arithmetic expression between the two numbers and return a string with the result of their execution. You can add your own imports, classes, and methods. Added classes must not have access modifiers (public or otherwise)


Requirements:
1. The calculator can perform addition, subtraction, multiplication and division operations with two numbers: a + b, a - b, a * b, a / b. The data is transmitted in one line (see example)! Solutions in which each number and arithmetic operation is passed on a new line are considered incorrect.
2. The calculator can work both with Arabic (1,2,3,4,5...) and Roman (I,II,III,IV,V...) numbers.
3. The calculator should be able to accept numbers from 1 to 10 inclusive. The output numbers are not limited in size and may be any.
4. The calculator can only work with integers.
5. The calculator can only work with Arabic or Roman numbers at the same time, if the user enters a string like 3 + II, the calculator must throw an exception and stop its work.
6. When entering Roman numbers, the answer should be printed in Roman numerals, respectively, when entering Arabic numerals, the answer is expected in Arabic numerals.
7. If the user enters unsuitable numbers, the application throws an exception and terminates.
8. If the user enters a string that does not conform to one of the arithmetic operations described above, the application throws an exception and terminates.
9. The result of a division operation is an integer, the remainder is discarded. 
10. The result of the calculator operations with Arabic numbers can be negative numbers and zero. The result of the calculator with Roman numbers can only be positive numbers, if the result is less than one, an exception is thrown.


Here is an example of how a program works


Input:
1 + 2

Output:
3



Input:
VI / III

Output:
II



Input:
I - II

Output:
throws Exception //because there are no negative numbers in the Roman system



Input:
I + 1

Output:
throws Exception //because different number systems are used simultaneously



Input:
1

Output:
throws Exception //because the string is not a mathematical operation



Input:
1 + 2 + 3

Output:
throws Exception //because the format of the mathematical operation does not satisfy the task - two operands and one operator (+, -, /, *)
