package temporary_datebase;

import java.util.ArrayList;

public class Drink {

    public String name;
    public String type;
    public String country;
    public float rating;
    public float price;
    public int ratingAmount;
    public int img;

    public Drink(){
        this.name = name;
        this.img = img;
        this.rating = rating;
        this.type = type;
    }

    public Drink(String name, int img, float rating, String type ){
        this.name = name;
        this.img = img;
        this.rating = rating;
        this.type = type;
    }

    public Drink(String name, int img, String type, String country, float rating, float price, int ratingAmount) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.rating = rating;
        this.price = price;
        this.ratingAmount = ratingAmount;
        this.img = img;
    }

    public String getName(){
        return name;
    }
    public float getRating(){
        return rating;
    }
    public void setRating(float rating){
        this.rating = rating;
    }
    public String getType(){return type;}
    public int getImg(){return img;}
    public String getCountry() {
        return  country;
    }


}
