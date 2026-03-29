package io.huangsam.trial.features.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternMatcherTest {

    @Test
    void testProcessPayment() {
        PatternMatcher matcher = new PatternMatcher();
        
        // Test Credit Card
        Object cc = new PatternMatcher.CreditCard("1234567890123456", "Visa");
        assertEquals("Processing Visa card: ****3456", matcher.processPayment(cc));

        // Test Bank Transfer
        Object bt = new PatternMatcher.BankTransfer("DE1234567890", "Sparkasse");
        assertEquals("Processing Transfer from Sparkasse: ****7890", matcher.processPayment(bt));

        // Test Crypto
        Object crypto = new PatternMatcher.Crypto("0x1234abcd5678efgh", "BTC");
        assertEquals("Processing BTC payment to: ****efgh", matcher.processPayment(crypto));

        // Test default and null
        assertEquals("No payment provided", matcher.processPayment(null));
        assertEquals("Unknown payment type", matcher.processPayment("JustAString"));
    }
}
