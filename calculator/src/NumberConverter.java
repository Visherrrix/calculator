class NumberConverter {
    public double converrtToDecimal(String number, int base) {
        if (base == 10) {
            return Double.parseDouble(number);
        }

        String[] parts = number.split("\\.");
        int integerPart = Integer.parseInt(parts[0], base);

        if (parts.length == 1) {
            return integerPart;
        }

        String fractionalPart = parts[1];
        double fractionalValue = 0.0;
        for (int i = 0; i < fractionalPart.length(); i++) {
            int digit = Character.digit(fractionalPart.charAt(i), base);
            fractionalValue += digit / Math.pow(base, i + 1);
        }

        return integerPart + fractionalValue;
    }

    public String convertFromDecimal(double number, int base) {
        if (base == 10) {
            return Double.toString(number);
        }

        int integerPart = (int) number;
        String integerStr = Integer.toString(integerPart, base);

        double fractionalPart = number - integerPart;
        if (fractionalPart == 0) {
            return integerStr;
        }

        StringBuilder fractionalStr = new StringBuilder(".");
        for (int i = 0; i < 10 && fractionalPart > 0; i++) {
            fractionalPart *= base;
            int digit = (int) fractionalPart;
            fractionalStr.append(Integer.toString(digit, base));
            fractionalPart -= digit;
        }

        return integerStr + fractionalStr.toString();
    }
}