import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in); //принимаем ввод с клавиатуры
    static int number1, number2; //объявляем переменные для чисел
    static char operation; //объявляем переменную для знака операции
    static int result;//объявляем переменную для результата

    public static void main(String[] args) throws InputMismatchException {
        System.out.println("Введите выражение");//выводим пользователю сообщение
        String input = scanner.nextLine(); //считываем строку, которую ввел пользователь
        String userInput = input.replaceAll(" ", "");//пробелы убраит надо))
        char[] chars = new char[10]; //создаем пустой массив длиной 10 символов(!)
        int count = 0;//нулевая переменная для исключения 3 чисел прри вводе
            for (int i = 0; i < userInput.length(); i++) { //выполняем заполнение массива символами пользователя и выявляем знак операции
                chars[i] = userInput.charAt(i);
                if (chars[i] == '+') {
                    operation = '+';
                    count += 1;
                }
                if (chars[i] == '-') {
                    operation = '-';
                    count += 1;
                }
                if (chars[i] == '*') {
                    operation = '*';
                    count += 1;
                }
                if (chars[i] == '/') {
                    operation = '/';
                    count += 1;
                }
            }
        if (count >=2) {
                throw new InputMismatchException(); //ошибка если введено 3 числа
            }

        String charsString = String.valueOf(chars); //преобразовываем строку в символы
        String[] blacks = charsString.split("[+-/*]");//разбиваем charsString на 2 строки, знак выступает в качестве разделителя
        String stable00 = blacks[0];//первая цифра пользователя уходит в stable00
        String stable01 = blacks[1];//вторая цифра пользователя уходит в stable01
        String string03 = stable01.trim();//удаляем пробелы(если пользователь введет после второго числа)
        char c = string03.charAt(0);//с = символьная

        if (Character.isDigit(c)) { //проверка на арабское число
            number1 = Integer.parseInt(stable00);//если проверка прошла - выполяем это и заносим в number1 1 цифру
            number2 = Integer.parseInt(string03);//заносим в number2 2 цифру
            if (number1 < 11 && number1 >= 0 && number2 < 11 && number2 >= 0) {//проверка на то, чтобы цифра была от 0 до 10
                result = calculated(number1, number2, operation);//запрашиваем calculated, он выполняет операцию и записывает результат в result
                System.out.println(number1 + " " + operation + " " + number2 + " = " + result);//дублируем пользователю запршенную строку и выводим результат
            } else {
                System.out.println("От 0 до 10");//не подходит под "от 0 до 10"
            }
        } else {                                    //если условие на арабское не проходит - программа прыгает сюда и начинает действие с римскими
            number1 = romanToNumber(stable00);
            number2 = romanToNumber(string03);
            if (number1 < 0 && number2 < 0) { //если число 0
                System.out.println("Число указано неверно / превышает диапазон [I-X]");
            } else {
                result = calculated(number1, number2, operation); //результируем
                if (result == 0) {//если результат получается 0
                    System.out.println("Ошибка! Результат не может быть равен 0!");
                } else {
                    String resultRoman = convertNumToRoman(result); //конвертируем обратно
                    System.out.println(stable00 + " " + operation + " " + string03 + " = " + resultRoman);
                }
            }
        }
    }


    static String convertNumToRoman(int numArabian) { //массив с числами
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }


    static int romanToNumber(@NotNull String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }

    static int calculated(int num1, int num2, char op) {   //принимаем значения
        int result = 0;             //зануляем переменную
        switch (op) {    //выбираем небоходимое действие по знаку
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':

                result = num1 / num2;

                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;     //возвращаем результат
    }
}