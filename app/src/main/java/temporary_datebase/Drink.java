package temporary_datebase;

import java.util.List;

public class Drink {

    public String name;
    public String type;
    public String country;
    public float rating;
    public float price;
    public int ratingAmount;
    public String img;
    public int id;
    public String description;
    public List<String> goesWith;


    public Drink(){
    }

    public Drink(String name, String type, String country, float rating, float price, int ratingAmount, int id, String description, List<String> goesWith) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.rating = rating;
        this.price = price;
        this.ratingAmount = ratingAmount;
        this.id = id;
        this.img = id + ".png";
        this.description = description;
        this.goesWith = goesWith;
    }

    public String getName(){ return name; }
    public float getRating(){ return rating; }
    public String getType(){return type;}
    public String getImg(){return img;}
    public String getCountry() {return  country;}
    public int getId() {return id;}
    public String getDescription() {return this.description;}
    public int getRatingAmount(){return ratingAmount;}
    public float getPrice() {return price;}
    public List<String> getGoesWith() {return goesWith;}

    public void setRating(float rating){ this.rating = rating; }
    public void setName(String name){this.name = name;}
    public void setCountry(String country){this.country = country;}
    public void setType(String type) { this.type = type; }
    public void setImg(String img) { this.img = img; }
    public void setPrice(float price) { this.price = price; }
    public void setRatingAmount(int ratingAmount) { this.ratingAmount = ratingAmount; }
    public void setDecription(String decription) {this.description = decription;}
    public void setId(int id) {this.id = id;}
    public void setGoesWith(List<String> goesWith) {this.goesWith = goesWith;}
}
