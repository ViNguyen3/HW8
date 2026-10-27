package part1_bridge;

public class Driver {

    public static void main(String[] args) {

        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel push = new PushChannel();

        System.out.println("--- Part 1 ---");

        PaymentNotification onlineEmail =
                new OnlinePaymentNotification(email);

        PaymentNotification onlineSms =
                new OnlinePaymentNotification(sms);

        PaymentNotification cashEmail =
                new CashOnDeliveryPayment(email);

        PaymentNotification cashSms =
                new CashOnDeliveryPayment(sms);

        onlineEmail.notifyCustomer();
        onlineSms.notifyCustomer();
        cashEmail.notifyCustomer();
        cashSms.notifyCustomer();


        System.out.println("\n--- Part 2: Bitcoin ---");

        PaymentNotification bitcoinEmail =
                new BitcoinPayment(email);

        PaymentNotification bitcoinSms =
                new BitcoinPayment(sms);

        bitcoinEmail.notifyCustomer();
        bitcoinSms.notifyCustomer();


        System.out.println("\n--- Part 3: Push Channel ---");

        PaymentNotification onlinePush =
                new OnlinePaymentNotification(push);

        PaymentNotification cashPush =
                new CashOnDeliveryPayment(push);

        PaymentNotification bitcoinPush =
                new BitcoinPayment(push);

        onlinePush.notifyCustomer();
        cashPush.notifyCustomer();
        bitcoinPush.notifyCustomer();
    }
}