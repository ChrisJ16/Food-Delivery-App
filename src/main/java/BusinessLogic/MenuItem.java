package BusinessLogic;

import java.io.Serializable;

public interface MenuItem extends Serializable {
    public String getTitle();

    public void setTitle(String title);

    public double getRating();

    public void setRating(double rating);

    public int getCalories();

    public void setCalories(int calories);

    public int getProtein();

    public void setProtein(int protein);

    public int getFat();

    public void setFat(int fat);

    public int getSodium();

    public void setSodium(int sodium);

    public int getPrice();

    public void setPrice(int price);

    public int getOrdersNumbers();

    public void setOrdersNumbers(int ordersNumbers);

    public String toString();
}
