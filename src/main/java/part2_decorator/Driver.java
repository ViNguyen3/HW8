package part2_decorator;

public class Driver {

    public static void main(String[] args) {

        FoodItem burger =
                new Cheese(
                    new Ketchup(
                        new Burger()
                    )
                );

        FoodItem fries =
                new Ketchup(
                    new Fries()
                );

        FoodItem hotDog =
                new Onions(
                    new Cheese(
                        new HotDog()
                    )
                );

        Order order = new Order();

        order.addItem(burger);
        order.addItem(fries);
        order.addItem(hotDog);

        System.out.println("--- Customer Order ---");

        order.printOrder();

        double total = order.getTotal();

        System.out.printf(
                "%nTotal before discount: $%.2f%n",
                total
        );

        LoyaltyStatus customer =
                new LoyaltyStatus("Gold");

        double discountedTotal =
                customer.applyDiscount(total);

        System.out.printf(
                "Gold loyalty total: $%.2f%n",
                discountedTotal
        );
    }
}   