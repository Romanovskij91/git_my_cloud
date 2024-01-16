package calculatorkata;
import java.util.Scanner;

public class Main {
	
	public static String calc(String input) throws MyException {
		
		String result ="", answer = "";
		//Отправляем введённую пользователем строку на анализ и разделение в метод splitLine()
		String[] preResult = splitLine(input);
		//Проверяем, что оба операнда - только арабские или только римские числа
		if(TypeAndValue.getOperand(preResult[0]) != TypeAndValue.getOperand(preResult[1]))
			throw new MyException("Operand values ​​are correct,\n"
					+ "but Calculator works with only arabic or only roman numbers");
		else
		{
			boolean whatType = TypeAndValue.getOperand(preResult[0]);
			//TRUE = арабское число
			if(whatType == true)
			{
				switch(preResult[2])
				{
				case "+" -> result += String.valueOf(Integer.parseInt(preResult[0]) + 
						Integer.parseInt(preResult[1]));
				case "-" -> result += String.valueOf(Integer.parseInt(preResult[0]) - 
						Integer.parseInt(preResult[1]));
				case "*" -> result += String.valueOf(Integer.parseInt(preResult[0]) * 
						Integer.parseInt(preResult[1]));
				case "/" -> result += String.valueOf(Integer.parseInt(preResult[0]) / 
						Integer.parseInt(preResult[1]));
				}
			}
			//FALSE - римское число
			else result += RomanCalculator.getResult(preResult[0], preResult[1], preResult[2]);
		}
		answer += preResult[0] + " " + preResult[2] + " " + preResult[1] + " " + "= " + result;
		return answer;
	}
	
	public static String[] splitLine(String input) throws MyException {
		
		//Избавляемся от пробелов в строке
		String lineWithoutSpace = input.replaceAll(" ", "");
		//Учитываем, что символ оператора может стоять в начале и/или в конце строки
		//Если это так, то бросаем исключение
			char firstSym = lineWithoutSpace.charAt(0);
			char lastSym = lineWithoutSpace.charAt(lineWithoutSpace.length()-1);
			switch(firstSym) 
			{
			case '*', '/', '+', '-' -> 
				throw new MyException("Your expression must not begin or end with operator's symbol");
			}
			switch(lastSym) 
			{
			case '*', '/', '+', '-' -> 
				throw new MyException("Your expression must not begin or end with operator's symbol");
			}
		//Если первый и последний символ строки не являются символом одного из необходимых операторов,
		//то проверяем остальные символы на совпадение
		boolean sym = true;
		//Переменная operator нужна для "поимки" символа оператора
		char operator = 0;
		if(sym == lineWithoutSpace.contains("*") ^
				sym == lineWithoutSpace.contains("/") ^
				sym == lineWithoutSpace.contains("+") ^
				sym == lineWithoutSpace.contains("-")) 
		{
			//Устанавливаем счётчик - он подсчитает количество символов оператора в строке 
			int count = 0;
			for(int i = 0; i < lineWithoutSpace.length(); i++) 
			{
				switch(lineWithoutSpace.charAt(i)) 
				{
				case '*', '/', '+', '-' -> 
					{
					operator = lineWithoutSpace.charAt(i);
					count++;
					}
				}
					
			}
			//Если символов оператора больше одного - бросаем исключение
			if(count != 1)
				throw new MyException("Invalid expression. Possible reasons:\n"
						+ "-- operator's symbol is not found\n"
						+ "-- an attempt was made to use more than one allowed operator\n"
						+ "-- an attempt was made to use invalid symbols");
			//Символ оператора встречается в строке один раз (при этом он не в начале и не в конце строки,
			//а точно между двумя операндами), значит теперь его можно использовать, 
			//чтобы "вынуть" из строки два операнда
			//В итоге метод вернёт массив строк, который гарантированно содержит символ оператора
			//и два операнда (порядок следования операндов сохранён)
			//Операнды ещё предстоит проверить на соответствие условиям задачи 
			else
			{
				String[] twoOperands = lineWithoutSpace.split("\\" + String.valueOf(operator));
				String[] result = new String[twoOperands.length + 1];
				for(int i = 0; i < twoOperands.length; i++)
					result[i] = twoOperands[i];
				result[twoOperands.length] = String.valueOf(operator);
				return result;
			}
		}
		//Символов оператора в строке не найдено - бросаем исключение
		else 
			throw new MyException("Invalid expression. Possible reasons:\n"
					+ "-- operator's symbol is not found\n"
					+ "-- an attempt was made to use more than one allowed operator\n"
					+ "-- an attempt was made to use invalid symbols");
	}
		
	public static void greet() {
		//Метод содержит инструкцию для пользователя калькулятора
		System.out.println("Calculator is running\n"
				+ "You can use only one of the following operators:\n"
				+ "\'+\' a + b\n"
				+ "\'-\' a - b\n"
				+ "\'*\' a * b\n"
				+ "\'/\' a / b\n"
				+ "You can use only arabic (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)\n"
				+ "or only roman (I, II, III, IV, V, VI, VII, VIII, IX, X) numbers from 1 to 10\n"
				+ "To exit the program enter 'e' (or 'E')");
	}

	public static void main(String[] args) throws MyException {
		//Вызываем метод с инструкцией к использованию калькулятора
		greet();
		try (Scanner scanner = new Scanner(System.in)) 
		{
			short exit = 0;
			String trimedLine;
			//Запускаем вечный цикл для комфортной работы калькулятора
			for(;;) 
			{
				String input = scanner.nextLine();
				//Создаём возможность выхода из калькулятора в любой момент,
				//используя для этого ввод какого-нибудь спецсимвола - для этого мы 
				//объявили и инициализировали нулём переменную exit
				//Учитываем возможное наличие пробелов в начале и конце строки -
				//если они есть, то метод trim() удалит их 
				trimedLine = input.trim();
				if(trimedLine.length() == 1) 
				{
					exit = (short) trimedLine.charAt(0);
					if(exit == 'e' || exit == 'E' || exit == 'е' || exit == 'Е') 
					{
						System.out.println("Calculator has stopped");
						break;
					}
				}
				try 
				{
					System.out.println(calc(input));
				} 
				catch (IndexOutOfBoundsException e) 
				{
					System.out.println("Please, enter your expression or enter " + "\'e\'" + " (or " + "\'E\')");
					//break;
					continue;
				} 
				catch (MyException e) 
				{
					System.out.println(e.getMessage());
					//break;
					continue;
				}
			}
		}	
	}
}
