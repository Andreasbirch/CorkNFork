package temporary_datebase;

import android.util.Log;

import java.util.ArrayList;

public class Wine {
    public int id;
    public String title;
    public int img;
    public float rating;
    public ArrayList<Float> allRatingsList;
    public ArrayList<String> phonesThatHasVoted;

    public Wine(int id, String title, int img) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.rating = 0;
        this.allRatingsList = new ArrayList<>();
        this.phonesThatHasVoted = new ArrayList<String>();
    }

    public void addRatingToWine(float userRating) {
        allRatingsList.add(userRating);

        //CALCULATING THE AVERAGE RATING OF THE WINE
        float sum = 0;
        for(float r : allRatingsList) {
            sum += r;
        }
        this.rating = sum / allRatingsList.size();
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
