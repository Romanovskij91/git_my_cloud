package calculatorkata;

class RomanCalculator {
	//Этот класс мне хотелось сделать подробным и самостоятельным,
	//с охватом бОльших римских чисел в качестве результата,
	//но боялся не успеть к зачислению на курс,
	//потому просто подогнал класс к условиям ТЗ
	public static String getResult(String operand1, String operand2, String operator) throws MyException {
		//Используя enum RomanDigit, проверяем аргументы метода на совпадение
		getArabicNum(operand1);
		getArabicNum(operand2);
		int result = 0;
		switch(operator)
		{
		case "+" -> result += getArabicNum(operand1) + getArabicNum(operand2);
		case "-" -> result += getArabicNum(operand1) - getArabicNum(operand2);
		case "*" -> result += getArabicNum(operand1) * getArabicNum(operand2);
		case "/" -> result += getArabicNum(operand1) / getArabicNum(operand2);
		}
		String answer = getRomanDigit(result);
		return answer;
	}
	
	public static String getRomanDigit(int input) throws MyException {
		//Метод возвращает результат операции в виде римского числа
		String result = "";
		if(input <= 0)
			throw new MyException("Result is 0 or less than 0,\n"
					+ "but roman numbers cannot be 0 or less than 0");
		else
		{
			String[] arrayUnit = {"0", "I", "II", "III", "IV", "V",
					"VI", "VII", "VIII", "IX", "X"};
			String[] arrayTen = {"0", "X", "XX", "XXX", "XL", "L",
					"LX", "LXX", "LXXX", "XC", "C"};
			if(input > 10)
				result += (input % 10 != 0) ?
						(arrayTen[input/10] + arrayUnit[input % 10]) :
						(arrayTen[input/10]);
			else result += arrayUnit[input];
		}
		return result;
	}
	
	public static int getArabicNum(String input) throws MyException {
		//Если совпадения нет, выбрасываем исключение
		int result = 0;
		try {
			RomanDigit obj = RomanDigit.valueOf(input);
			result += obj.getSecondName();
		} catch (IllegalArgumentException e) {
			throw new MyException("Roman number(s) not found (only from I to X)");
		}
		return result;
	}
}
