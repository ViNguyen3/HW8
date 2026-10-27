package part2_decorator;

public class Cheese extends FoodDecorator {

    public Cheese(FoodItem foodItem) {
        super(foodItem);
    }

    @Override
    public String getDescription() {
        return foodItem.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return foodItem.getCost() + 1.00;
    }
}