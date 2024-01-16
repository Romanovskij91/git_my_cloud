package calculatorkata;

class TypeAndValue {
	static int number;
	static String str;

	public static boolean getOperand(String input) throws MyException {
		//Метод возвращает TRUE, если операнд - корректное число (от 1 до 10), 
		//возвращает FALSE, если операнд - корректная строка (I, или II, ... или X)
		boolean result = true;
			if(getType(input) == true) 
			{
				if(getValue(number) == true) 
					return result;
				else 
				{
					result = false;
					return result;
				}
			}
			else
			{
				if(getValue(str) == false)
				{
					result = false;
					return result;	
				}
				else return result;
			}
	}
	
	public static boolean getType(String input) {
		//Метод определяет тип операнда - int или String,
		//если int - вернёт TRUE, если String - вернёт FALSE 
		boolean result = true;
		try {
			number = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			str = input;
			result = false;
		}
		return result;
	}

	public static boolean getValue(int input) throws MyException {
		//Т.к. метод принимает int, то он вернёт TRUE, 
		//если значение операнда будет корректным (число от 1 до 10)
		boolean result = true;
		if(input < 1 || input > 10) {
			throw new MyException("Invalid expression. Possible reasons:\n"
					+ "-- arabic number(s) is less than 1 or greater than 10\n"
					+ "-- roman number(s) is not found - please, use it from I to X\n"
					+ "-- an attempt was made to use invalid symbols");
		}
		return result;
	}
	
	public static boolean getValue(String input) throws MyException {
		//Т.к. метод принимает String, то он вернёт FALSE, 
		//если значение операнда будет корректным (римская цифра от I до X)
		boolean result = false;
		try {
			RomanDigit.valueOf(input);
		} catch (IllegalArgumentException e) {
			throw new MyException("Invalid expression. Possible reasons:\n"
					+ "-- arabic number(s) is less than 1 or greater than 10\n"
					+ "-- roman number(s) is not found - please, use it from I to X\n"
					+ "-- an attempt was made to use invalid symbols");
		}
		return result;
	}
}
