import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum Type {
    MEAT, FISH, OTHER
}

public class Test {
    public static void main(String[] args) {
        List<Food> menu = Arrays.asList(
            new Food("Salad", true, 150, Type.OTHER),
            new Food("Chicken", false, 250, Type.MEAT),
            new Food("Broccoli", true, 50, Type.OTHER),
            new Food("Salmon", false, 300, Type.FISH),
            new Food("Pasta", true, 400, Type.OTHER)
        );

        List<String> vegetarianLowCalorieFoods = menu.stream()
                .filter(food -> food.isVege() && food.getCalories() <= 300)
                .map(Food::getName)
                .collect(Collectors.toList());

        System.out.println("칼로리가 300이하인 채식 음식 " + vegetarianLowCalorieFoods);
    }
}

class Food {
    String name;
    boolean Vege;
    int calories;
    Type type;

    public Food(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.Vege = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVege() {
        return Vege;
    }

    public void setVege(boolean vege) {
        Vege = vege;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public String toString() {
        return name;
    }
}
