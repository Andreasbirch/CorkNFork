package temporary_datebase;

import android.util.Log;
import java.util.ArrayList;

public class WineDatabase {
    public static boolean dataHasBeenPulled = false;
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

    public void restartDatabase(){
        wines = new ArrayList<Wine>();
    }

    private WineDatabase() {}

    public void addWine(String name, int img, float rating, String type) {
        wines.add(new Wine(name, img, rating, type));
    }

    public ArrayList<Wine> getWinesThatGoWith(String category) {
        ArrayList<Wine> holder = new ArrayList<Wine>();
        switch (category) {
            case "cow":
                for(Wine wine : wines) {
                    if(wine.type.equals("red")) {
                        holder.add(wine);
                    }
                }
                break;
           case "pork":
               for(Wine wine : wines) {
                   if(wine.type.equals("white")) {
                       holder.add(wine);
                   }
               }
                break;
            case "chicken":
                for(Wine wine : wines) {
                    if(wine.type.equals("white")) {
                        holder.add(wine);
                    }
                }
                break;
            case "vegan":
                for(Wine wine : wines) {
                    if(wine.type.equals("white")) {
                        holder.add(wine);
                    }
                }
                break;
            case "fish":
                for(Wine wine : wines) {
                    if(wine.type.equals("rosé")) {
                        holder.add(wine);
                    }
                }
                break;
            case "shellfish":
                for(Wine wine : wines) {
                    if(wine.type.equals("rosé")) {
                        holder.add(wine);
                    }
                }
                break;
        }
        return holder;
    }

    public Wine getWine(String title) {
        for (Wine wine : wines) {
            if(wine.name.equals(title)) {
                return wine;
            }
        }

        return null;
    }
}


