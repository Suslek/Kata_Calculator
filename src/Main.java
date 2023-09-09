import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String printedString = input.nextLine();

        System.out.println(calc(printedString));
    }

    public static String calc(String input) throws Exception {
        String[] buffer =  input.split(" ");
        String operation = buffer[1];

        boolean isRoman;

        int value1;
        int value2;

        if (buffer.length != 3){
            throw new Exception("Undefined operation length");
        }

        if (input.contains("I") || input.contains("V") || input.contains("X")){
            value1 = romanToInt(buffer[0]);
            value2 = romanToInt(buffer[2]);
            isRoman = true;
        }
        else{
            value1 = inputToInt(buffer[0]);
            value2 = inputToInt(buffer[2]);
            isRoman = false;
        }

        if (value1 > 10 || value2 > 10){
            throw new Exception("one of the values is more than 10");
        }

        int result;
        switch (operation) {
            case ("+") -> result = value1 + value2;
            case ("-") -> result = value1 - value2;
            case ("*") -> result = value1 * value2;
            case ("/") -> result = value1 / value2;
            default -> throw new Exception("Undefined operation");
        }

        if (isRoman){
            if (result < 1){
                throw new Exception("Less than zero");
            }
            return intToRoman(result);
        }
        else{
            return String.valueOf(result);
        }
    }

    static int inputToInt(String input) throws Exception {
        int result;

        try {
            result = Integer.parseInt(input);
        }
        catch (NumberFormatException e){
            throw new Exception("The format of the number is not correct");
        }

        return result;
    }

    static int romanToInt(String romanNumber) throws Exception {
        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put("I", 1);
        dictionary.put("V", 5);
        dictionary.put("X", 10);

        if (!romanNumber.contains("I") && !romanNumber.contains("V") && !romanNumber.contains("X")){
            throw new Exception("Incorrect variable type");
        }

        int result = 0;
        int repetitionsCount = 1;

        String[] romanNumberSeparated = romanNumber.split("");
        int[] romanToArabicBuffer = new int[romanNumberSeparated.length+1];

        for (int i = 0; i < romanNumberSeparated.length; i++){
            for (int j = i+1; j < romanNumberSeparated.length; j++){
                if (dictionary.get(romanNumberSeparated[j]).equals(dictionary.get(romanNumberSeparated[i])) && ((j - i) <= 3)){
                    repetitionsCount++;
                }

                if (repetitionsCount > 3){
                    throw new Exception("incorrect number: more than 3 identical symbols in a row!");
                }

                if (dictionary.get(romanNumberSeparated[j]) > dictionary.get(romanNumberSeparated[i]) && (((j-i)) >= 2)){
                    throw new Exception("incorrect number: the maximum value does not come first!");
                }
            }
            repetitionsCount = 1;
        }

        for (int i = 0; i < romanNumberSeparated.length; i++){
            romanToArabicBuffer[i] = dictionary.get(romanNumberSeparated[i]);
        }

        for (int i = 0; i < romanToArabicBuffer.length-1; i++){
            if(romanToArabicBuffer[i] < romanToArabicBuffer[i+1]){
                result += romanToArabicBuffer[i+1] - romanToArabicBuffer[i];
                i++;
            }
            else{
                result += romanToArabicBuffer[i];
            }
        }

        return result;
    }

    static String intToRoman(int number) {
        if (number >= 4000 || number <= 0)
            return null;
        StringBuilder result = new StringBuilder();
        for(Integer key : units.descendingKeySet()) {
            while (number >= key) {
                number -= key;
                result.append(units.get(key));
            }
        }
        return result.toString();
    }

    private static final NavigableMap<Integer, String> units;
    static {
        NavigableMap<Integer, String> initMap = new TreeMap<>();
        initMap.put(1000, "M");
        initMap.put(900, "CM");
        initMap.put(500, "D");
        initMap.put(400, "CD");
        initMap.put(100, "C");
        initMap.put(90, "XC");
        initMap.put(50, "L");
        initMap.put(40, "XL");
        initMap.put(10, "X");
        initMap.put(9, "IX");
        initMap.put(5, "V");
        initMap.put(4, "IV");
        initMap.put(1, "I");
        units = Collections.unmodifiableNavigableMap(initMap);
    }
}