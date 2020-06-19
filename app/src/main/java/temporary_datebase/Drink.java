package temporary_datebase;

public class Drink {

    public String name;
    public String type;
    public String country;
    public double rating;
    public double price;
    public int ratingAmount;

    public Drink(){

    }
    public Drink(String name, String type, String country, double rating, double price, int ratingAmount) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.rating = rating;
        this.price = price;
        this.ratingAmount = ratingAmount;
    }

}
