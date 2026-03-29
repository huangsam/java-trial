package io.huangsam.trial.features.modern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPatternExplorer {

    @Test
    void testProcessPayment() {
        PatternExplorer explorer = new PatternExplorer();
        
        // Test Credit Card
        Object cc = new PatternExplorer.CreditCard("1234567890123456", "Visa");
        assertEquals("Processing Visa card: ****3456", explorer.processPayment(cc));

        // Test Bank Transfer
        Object bt = new PatternExplorer.BankTransfer("DE1234567890", "Sparkasse");
        assertEquals("Processing Transfer from Sparkasse: ****7890", explorer.processPayment(bt));

        // Test Crypto
        Object crypto = new PatternExplorer.Crypto("0x1234abcd5678efgh", "BTC");
        assertEquals("Processing BTC payment to: ****efgh", explorer.processPayment(crypto));

        // Test default and null
        assertEquals("No payment provided", explorer.processPayment(null));
        assertEquals("Unknown payment type", explorer.processPayment("JustAString"));
    }
}
