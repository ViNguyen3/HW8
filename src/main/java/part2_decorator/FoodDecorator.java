package part2_decorator;

public abstract class FoodDecorator implements FoodItem {

    protected FoodItem foodItem;

    public FoodDecorator(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}