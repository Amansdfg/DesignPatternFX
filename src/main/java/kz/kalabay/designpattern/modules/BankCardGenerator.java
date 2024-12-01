public class BankCardGenerator {
    public static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();

        cardNumber.append("4");

        for (int i = 1; i < 15; i++) {
            cardNumber.append((int) (Math.random() * 10));
        }

        cardNumber.append(generateLuhnChecksum(cardNumber.toString()));

        return cardNumber.toString();
    }

    private static int generateLuhnChecksum(String cardNumberWithoutChecksum) {
        int sum = 0;
        boolean alternate = true;

        for (int i = cardNumberWithoutChecksum.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(cardNumberWithoutChecksum.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }
}
