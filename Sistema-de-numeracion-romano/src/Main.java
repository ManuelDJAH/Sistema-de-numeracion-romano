import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static int romano_a_decimal(String romano) {
        int decimal = 0;
        int lastNumber = 0;
        String Numeral_romano = romano.toUpperCase();

        for (int i = Numeral_romano.length() - 1; i >= 0; i--) {
            char currentChar = Numeral_romano.charAt(i);
            int currentNumber = Char_romanos(currentChar);

            if (currentNumber < lastNumber) {
                decimal -= currentNumber;
            } else {
                decimal += currentNumber;
            }
            lastNumber = currentNumber;
        }
        return decimal;
    }

    private static int Char_romanos(char romanChar) {
        return switch (romanChar) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Proporciona al menos un número romano como argumento.");
            return;
        }

        StringBuilder csvContent = new StringBuilder("Número Romano,Número Decimal\n");

        for (String romano : args) {
            int decimal = romano_a_decimal(romano);
            System.out.println("El equivalente de " + romano + " es " + decimal);
            csvContent.append(romano).append(",").append(decimal).append("\n");
        }

        try (FileWriter fileWriter = new FileWriter("numeros_romanos.csv")) {
            fileWriter.write(csvContent.toString());
            System.out.println("El archivo CSV se generó como 'numeros_romanos.csv'.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo CSV.");
            e.printStackTrace();
        }
    }
}
