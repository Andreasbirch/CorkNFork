package temporary_datebase;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Wine extends Drink {
    public int img;
    public float rating;
    public int noOfRatings;
    ArrayList<String> phonesThatHasVoted = new ArrayList<String>();

    public Wine(String name, int img, float rating, String type) {
        //img 2131230879
        this.name = name;
        this.img = img;
        this.rating = rating;
        this.type = type;
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
    public String getCountry(){return country;}
    public int getImg(){return img;}

    public void addRatingToWine(float userRating) {
        this.noOfRatings += 1;
        this.rating = (rating + userRating) / noOfRatings;
    }

    public void addPhoneIdentifier(String id) {
        if (!searchIdentifiers(id)) {
            phonesThatHasVoted.add(id);
        }
    }

    public boolean searchIdentifiers(String id) {
        boolean exist = false;

        for(String s : phonesThatHasVoted) {
            if(s.equals(id)) {
                exist = true;
            }
        }

        return exist;
    }
}
