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
    public String img;
    public int id;


    public Drink(){
    }

    public Drink(String name, float rating, String type, int id){
        this.name = name;
        this.rating = rating;
        this.type = type;
        this.id = id;
        this.img = id + ".png";
    }

    public Drink(String name, String type, String country, float rating, float price, int ratingAmount, int id) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.rating = rating;
        this.price = price;
        this.ratingAmount = ratingAmount;
        this.id = id;
        this.img = id + ".png";
    }

    public String getName(){ return name; }
    public float getRating(){ return rating; }
    public String getType(){return type;}
<<<<<<< HEAD
    public String getImg(){return img;}
    public String getCountry() {
        return  country;
    }


=======
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
>>>>>>> 56c00bbba52c7ec3f2ea31993ea2f313c329e10b
}
