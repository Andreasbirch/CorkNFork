package temporary_datebase;

import java.util.ArrayList;

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
        this.name = name;
        this.img = img;
        this.rating = rating;
        this.type = type;
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
    public String getImg(){return img;}
    public String getCountry() {
        return  country;
    }


}
