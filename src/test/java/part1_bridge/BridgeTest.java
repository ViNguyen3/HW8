package part1_bridge;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BridgeTest {

    private String captureOutput(Runnable action) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;

        System.setOut(new PrintStream(output));
        action.run();
        System.setOut(original);

        return output.toString();
    }

    @Test
    void emailChannelSendsEmailMessage() {
        EmailChannel channel = new EmailChannel();

        String output = captureOutput(
                () -> channel.send("Payment complete")
        );

        assertTrue(output.contains("EMAIL: Payment complete"));
    }

    @Test
    void smsChannelSendsSmsMessage() {
        SmsChannel channel = new SmsChannel();

        String output = captureOutput(
                () -> channel.send("Payment complete")
        );

        assertTrue(output.contains("SMS: Payment complete"));
    }

    @Test
    void pushChannelSendsPushMessage() {
        PushChannel channel = new PushChannel();

        String output = captureOutput(
                () -> channel.send("Payment complete")
        );

        assertTrue(output.contains("PUSH: Payment complete"));
    }

    @Test
    void onlinePaymentUsesNotificationChannel() {
        String output = captureOutput(() -> {
            PaymentNotification payment =
                    new OnlinePaymentNotification(new EmailChannel());

            payment.notifyCustomer();
        });

        assertTrue(output.contains("online payment"));
    }

    @Test
    void cashOnDeliveryUsesNotificationChannel() {
        String output = captureOutput(() -> {
            PaymentNotification payment =
                    new CashOnDeliveryPayment(new SmsChannel());

            payment.notifyCustomer();
        });

        assertTrue(output.contains("cash on delivery"));
    }

    @Test
    void bitcoinPaymentUsesNotificationChannel() {
        String output = captureOutput(() -> {
            PaymentNotification payment =
                    new BitcoinPayment(new EmailChannel());

            payment.notifyCustomer();
        });

        assertTrue(output.contains("Bitcoin payment"));
    }
}