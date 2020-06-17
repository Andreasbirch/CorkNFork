package temporary_datebase;

import android.util.Log;

import com.example.andreas.cork.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        wines.add(new Wine(0, "Sauvignon Blanc", R.drawable.wine_sauvignon_blanc, (float) 2.5));
        wines.add(new Wine(1, "Cuvée Vieilles Vignes", R.drawable.wine_cuvee_vieilles_vignes, (float) 4));
        wines.add(new Wine(2, "Reserve Zinfandel", R.drawable.wine_reserve_zinfandel, (float) 5));
        wines.add(new Wine(3, "Flétris", R.drawable.wine_fletris, (float) 1));
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

   /* public String[] getTitles() {
        String[] stringHolder = new String[wines.size()];
        for(int i = 0; i < holder.size(); i++) {
            stringHolder[i] = holder.get(i).title;
        }
        return stringHolder;
    }*/

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
}


