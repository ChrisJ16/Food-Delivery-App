package BusinessLogic;

import java.util.Objects;

public class BaseProduct implements MenuItem{
    //Title,Rating,Calories,Protein,Fat,Sodium,Price
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    private static final long serialVersionUID = 6529685098267757690L;
    private int ordersNumbers;

    public BaseProduct(){}

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
        this.ordersNumbers=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseProduct)) return false;
        BaseProduct that = (BaseProduct) o;
        return Double.compare(that.getRating(), getRating()) == 0 && getCalories() == that.getCalories() && getProtein() == that.getProtein() && getFat() == that.getFat() && getSodium() == that.getSodium() && getPrice() == that.getPrice() && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getRating(), getCalories(), getProtein(), getFat(), getSodium(), getPrice());
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }

    public int getOrdersNumbers() {
        return ordersNumbers;
    }

    public void setOrdersNumbers(int ordersNumbers) {
        this.ordersNumbers = ordersNumbers;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {this.title = title;}

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public int getProtein() {
        return protein;
    }

    @Override
    public void setProtein(int protein) {
        this.protein = protein;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public void setFat(int fat) {
        this.fat = fat;
    }

    @Override
    public int getSodium() {
        return sodium;
    }

    @Override
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }
}
