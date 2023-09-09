import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put("I", 1);
        dictionary.put("V", 5);
        dictionary.put("X", 10);
        dictionary.put("L", 50);
        dictionary.put("C", 100);
        dictionary.put("D", 500);
        dictionary.put("M", 1000);

        Scanner input = new Scanner(System.in);
        InputHandler(input.nextLine());
    }

    static void InputHandler(String input) throws Exception {
        String[] buffer =  input.split(" ");
        String operation = buffer[1];

        int value1 = 0;
        int value2 = 0;

        if (buffer.length != 3){
            throw new Exception("Некорректная длина операции");
        }

        try {
            value1 = Integer.parseInt(buffer[0]);
        }
        catch (NumberFormatException e){
            throw e;
        }

        try {
            value2 = Integer.parseInt(buffer[2]);
        }
        catch (NumberFormatException e){
            throw e;
        }

        Calculate(value1, value2, operation);
    }

    static void Calculate(int value1, int value2, String operation) throws Exception {
        long result = 0;
        switch (operation) {
            case ("+") -> result = value1 + value2;
            case ("-") -> result = value1 - value2;
            case ("*") -> result = (long) value1 * value2;
            case ("/") -> result = value1 / value2;
            default -> throw new Exception("Не описанная операция");
        }
        System.out.println(result);
    }
}



