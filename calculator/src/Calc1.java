public class Calc1 extends BaseCalculator {
    public Calc1() {
        this.currentBase = 10;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("\nТекущая СС: " + currentBase);
            System.out.println("1. Сменить СС");
            System.out.println("2. Вычисления");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    changeNumberBase();
                    break;
                case "2":
                    safePerformCalculation();
                    break;
                case "3":
                    System.out.println("Выход из программы");
                    return;
                default:
                    System.out.println("Неверный ввод!");
                    break;
            }
        }
    }

    @Override
    protected void performCalculation() {
        System.out.print("Введите первое число (в текущей СС " + currentBase + "): ");
        String num1Str = scanner.nextLine();
        double num1 = parseNumber(num1Str);

        System.out.print("Введите оператор (+, -, *, /): ");
        String operator = scanner.nextLine();

        System.out.print("Введите второе число (в текущей СС " + currentBase + "): ");
        String num2Str = scanner.nextLine();
        double num2 = parseNumber(num2Str);

        double result = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> {
                if (num2 == 0) throw new ArithmeticException("Деление на ноль");
                yield num1 / num2;
            }
            default -> throw new IllegalArgumentException("Неверный оператор");
        };

        System.out.println("\nРезультат в разных СС:");
        System.out.println("2: " + converter.convertFromDecimal(result, 2));
        System.out.println("8: " + converter.convertFromDecimal(result, 8));
        System.out.println("10: " + result);
        System.out.println("16: " + converter.convertFromDecimal(result, 16));
    }
}