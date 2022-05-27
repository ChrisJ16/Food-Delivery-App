package BusinessLogic;

import java.awt.*;
import java.util.ArrayList;

public class CompositeProduct implements MenuItem {
    private String title;
    private ArrayList<MenuItem> listaProduse;
    private int ordersNumbers;

    public CompositeProduct(String title, ArrayList<MenuItem> products)
    {
        this.title = title;
        this.listaProduse = products;
        this.ordersNumbers=0;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title=title;
    }

    public int getOrdersNumbers() {
        return ordersNumbers;
    }

    public void setOrdersNumbers(int ordersNumbers) {
        this.ordersNumbers = ordersNumbers;
    }

    @Override
    public double getRating() {
        double rez=0;
        int k=0;
        for(MenuItem mi : listaProduse){
            if(mi!=null){
                rez+=mi.getRating();
                k++;
            }
        }
        rez/=k;
        return rez;
    }

    @Override
    public void setRating(double rating) {

    }

    @Override
    public int getCalories() {
        int rez=0;
        for(MenuItem mi : listaProduse){
            if(mi!=null)
                rez+=mi.getCalories();
        }
        return rez;
    }

    @Override
    public void setCalories(int calories) {

    }

    @Override
    public int getProtein() {
        int rez=0;
        for(MenuItem mi : listaProduse){
            if(mi!=null)
                rez+=mi.getProtein();
        }
        return rez;
    }

    @Override
    public void setProtein(int protein) {

    }

    @Override
    public int getFat() {
        int rez=0;
        for(MenuItem mi : listaProduse){
            if(mi!=null)
                rez+=mi.getFat();
        }
        return rez;
    }

    @Override
    public void setFat(int fat) {

    }

    @Override
    public int getSodium() {
        int rez=0;
        for(MenuItem mi : listaProduse){
            if(mi!=null)
                rez+=mi.getSodium();
        }
        return rez;
    }

    @Override
    public void setSodium(int sodium) {

    }

    @Override
    public int getPrice() {
        int rez=0;
        for(MenuItem mi : listaProduse){
            if(mi!=null)
                rez+=mi.getPrice();
        }
        return rez;
    }

    @Override
    public void setPrice(int price) {

    }

    @Override
    public String toString(){
        String rez = new String("Produs compus: " + getTitle() + " ,pret: " + getPrice()
                                + " ,compus din:\n");
        for(MenuItem mi: listaProduse){
            if(mi!=null)
                rez+= mi.toString() + "\n";
        }
        return rez;
    }
}
