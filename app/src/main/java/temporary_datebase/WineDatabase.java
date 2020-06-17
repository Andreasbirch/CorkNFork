package temporary_datebase;

import android.util.Log;

import com.example.andreas.cork.R;
import java.util.ArrayList;
import java.util.Random;

public class WineDatabase {
    ArrayList<Wine> wines = new ArrayList<Wine>();
    private static WineDatabase instance;

    //Singleton
    public static WineDatabase getInstance() {
        if(null == instance) {
            instance = new WineDatabase();
            return instance;
        } else {
            return instance;
        }
    }

    private WineDatabase() {
        //CREATE SAMPLE DATA
        wines.add(new Wine(0, "Sauvignon Blanc", R.drawable.wine_sauvignon_blanc));
        wines.add(new Wine(1, "Cuvée Vieilles Vignes", R.drawable.wine_cuvee_vieilles_vignes));
        wines.add(new Wine(2, "Reserve Zinfandel", R.drawable.wine_reserve_zinfandel));
        wines.add(new Wine(3, "Flétris", R.drawable.wine_fletris));

        simulateRatings();
    }

    public ArrayList<Wine> getWinesThatGoWith(String category) {
        ArrayList<Wine> holder = new ArrayList<Wine>();
        Log.i("tag", category);
        switch (category) {
            case "cow":
                holder.add(getWine("Sauvignon Blanc"));
                holder.add(getWine("Cuvée Vieilles Vignes"));
                holder.add(getWine("Reserve Zinfandel"));
                holder.add(getWine("Flétris"));
                break;
           case "pork":
                holder.add(getWine("Sauvignon Blanc"));
                holder.add(getWine("Flétris"));
                break;
            case "chicken":
                holder.add(getWine("Sauvignon Blanc"));
                holder.add(getWine("Flétris"));
                holder.add(getWine("Reserve Zinfandel"));
                break;
            case "vegan":
                holder.add(getWine("Reserve Zinfandel"));
                break;
            case "fish":
                holder.add(getWine("Sauvignon Blanc"));
                holder.add(getWine("Cuvée Vieilles Vignes"));
                holder.add(getWine("Reserve Zinfandel"));
                holder.add(getWine("Flétris"));
                break;
            case "shellfish":
                holder.add(getWine("Sauvignon Blanc"));
                holder.add(getWine("Cuvée Vieilles Vignes"));
                holder.add(getWine("Reserve Zinfandel"));
                holder.add(getWine("Flétris"));
                break;
        }
        return holder;
    }

    public String[] getTitles() {
        String[] holder = new String[wines.size()];
        for(int i = 0; i < wines.size(); i++) {
            holder[i] = wines.get(i).title;
        }
        return holder;
    }

    public int[] getImgs() {
        int[] holder = new int[wines.size()];
        for(int i = 0; i < wines.size(); i++) {
            holder[i] = wines.get(i).img;
        }
        return holder;
    }

    public float[] getRating() {
        float[] holder = new float[wines.size()];
        for(int i = 0; i < wines.size(); i++) {
            holder[i] = wines.get(i).rating;
        }
        return holder;
    }

    public Wine getWine(String title) {
        for (Wine wine : wines) {
            if(wine.title.equals(title)) {
                return wine;
            }
        }

        return null;
    }

    //SIMULATE RANDOM RATINGS OBSOLETE IN FINAL VERSION
    public void simulateRatings() {
        Random random = new Random();

        for(Wine wine : wines) {
            for(int i = 0; i < random.nextInt(10); i++) {
                float randomRating = random.nextFloat() * 5;

                wine.addRatingToWine(randomRating);
            }
        }
    }
}


