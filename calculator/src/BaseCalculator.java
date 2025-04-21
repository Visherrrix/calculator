import java.util.Scanner;

public abstract class BaseCalculator {
    protected final Scanner scanner = new Scanner(System.in);
    protected int currentBase;
    protected final NumberConverter converter = new NumberConverter();

    public abstract void run();

    protected abstract void performCalculation();

    protected void changeNumberBase() {
        System.out.print("Выберите СС (2/8/10/16): ");
        String input = scanner.nextLine();
        try {
            int base = Integer.parseInt(input);
            if (base == 2 || base == 8 || base == 10 || base == 16) {
                currentBase = base;
                System.out.println("Система счисления изменена на " + base);
            } else {
                System.out.println("Ошибка: поддерживаются только СС 2, 8, 10, 16");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите число (2, 8, 10 или 16)");
        }
    }

    protected void safePerformCalculation() {
        try {
            performCalculation();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    protected double parseNumber(String numberStr) {
        try {
            if (currentBase == 10) {
                return Double.parseDouble(numberStr);
            } else {
                return converter.converrtToDecimal(numberStr , currentBase);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат числа для СС " + currentBase);
        }
    }
}