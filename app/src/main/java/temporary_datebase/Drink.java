package temporary_datebase;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Drink {

    public String name;
    public String type;
    public String country;
    public float rating;
    public float price;
    public int ratingAmount;
    public int img;
    public int id;


    public Drink(){
    }

    public Drink(String name, int img, float rating, String type, int id){
        this.name = name;
        this.img = img;
        this.rating = rating;
        this.type = type;
        this.id = id;
    }

    public Drink(String name, int img, String type, String country, float rating, float price, int ratingAmount, int id) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.rating = rating;
        this.price = price;
        this.ratingAmount = ratingAmount;
        this.img = img;
        this.id = id;
    }

    public String getName(){ return name; }
    public float getRating(){ return rating; }
    public String getType(){return type;}
    public int getImg(){return img;}
    public String getCountry() { return  country; }
    public float getPrice() { return price; }
    public int getRatingAmount() { return ratingAmount; }


    public void setRating(float rating){ this.rating = rating; }
    public void setName(String name){this.name = name;}
    public void setCountry(String country){this.country = country;}
    public void setType(String type) { this.type = type; }
    public void setImg(int img) { this.img = img; }
    public void setPrice(float price) { this.price = price; }
    public void setRatingAmount(int ratingAmount) { this.ratingAmount = ratingAmount; }
}
