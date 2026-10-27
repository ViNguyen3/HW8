package part2_decorator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    @Test
    void burgerHasCorrectDescriptionAndCost() {
        FoodItem burger = new Burger();

        assertEquals("Burger", burger.getDescription());
        assertEquals(6.00, burger.getCost(), 0.001);
    }

    @Test
    void friesHaveCorrectDescriptionAndCost() {
        FoodItem fries = new Fries();

        assertEquals("Fries", fries.getDescription());
        assertEquals(3.00, fries.getCost(), 0.001);
    }

    @Test
    void hotDogHasCorrectDescriptionAndCost() {
        FoodItem hotDog = new HotDog();

        assertEquals("Hot Dog", hotDog.getDescription());
        assertEquals(4.50, hotDog.getCost(), 0.001);
    }

    @Test
    void ketchupDecoratorAddsCostAndDescription() {
        FoodItem burger =
                new Ketchup(new Burger());

        assertEquals(
                "Burger, Ketchup",
                burger.getDescription()
        );

        assertEquals(
                6.25,
                burger.getCost(),
                0.001
        );
    }

    @Test
    void cheeseDecoratorAddsCostAndDescription() {
        FoodItem burger =
                new Cheese(new Burger());

        assertEquals(
                "Burger, Cheese",
                burger.getDescription()
        );

        assertEquals(
                7.00,
                burger.getCost(),
                0.001
        );
    }

    @Test
    void onionDecoratorAddsCostAndDescription() {
        FoodItem hotDog =
                new Onions(new HotDog());

        assertEquals(
                "Hot Dog, Onions",
                hotDog.getDescription()
        );

        assertEquals(
                5.00,
                hotDog.getCost(),
                0.001
        );
    }

    @Test
    void orderAddsItemsAndCalculatesTotal() {
        Order order = new Order();

        order.addItem(new Burger());
        order.addItem(new Fries());

        assertEquals(
                9.00,
                order.getTotal(),
                0.001
        );
    }

    @Test
    void printOrderPrintsItems() {
        Order order = new Order();
        order.addItem(new Burger());

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        PrintStream original = System.out;
        System.setOut(new PrintStream(output));

        order.printOrder();

        System.setOut(original);

        assertTrue(
                output.toString().contains("Burger")
        );
    }

    @Test
    void goldCustomerReceivesDiscount() {
        LoyaltyStatus loyalty =
                new LoyaltyStatus("Gold");

        double discounted =
                loyalty.applyDiscount(100.00);

        assertEquals(
                90.00,
                discounted,
                0.001
        );
    }
}