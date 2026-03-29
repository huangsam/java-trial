package io.huangsam.trial.modern;

/**
 * Demonstrates modern Java features: Switch Pattern Matching and Record Patterns.
 */
public class PatternMatcher {

    /**
     * A record representing a Credit Card payment.
     *
     * @param cardNumber the card number
     * @param network    the network (Visa, Mastercard, etc.)
     */
    public record CreditCard(String cardNumber, String network) {
    }

    /**
     * A record representing a Bank Transfer.
     *
     * @param iban    the IBAN
     * @param bankName the bank name
     */
    public record BankTransfer(String iban, String bankName) {
    }

    /**
     * A record representing a Crypto payment.
     *
     * @param walletAddress the wallet address
     * @param currency      the currency (BTC, ETH, etc.)
     */
    public record Crypto(String walletAddress, String currency) {
    }

    /**
     * Processes a payment using switch pattern matching and record patterns (Java 21+).
     *
     * @param payment the payment object
     * @return a description of the processed payment
     */
    public String processPayment(Object payment) {
        return switch (payment) {
            case CreditCard(String number, String network) -> 
                "Processing %s card: %s".formatted(network, mask(number));
            case BankTransfer(String iban, String name) -> 
                "Processing Transfer from %s: %s".formatted(name, mask(iban));
            case Crypto(String address, String coin) -> 
                "Processing %s payment to: %s".formatted(coin, mask(address));
            case null -> "No payment provided";
            default -> "Unknown payment type";
        };
    }

    private String mask(String value) {
        if (value == null || value.length() <= 4) {
            return "****";
        }
        return "****" + value.substring(value.length() - 4);
    }
}
