package temporary_datebase;

public class Drink {

    public String name;
    public String type;
    public String country;
    public float rating;
    public float price;
    public int ratingAmount;
    public int img;
    public Drink(){

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


}
