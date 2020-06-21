package temporary_datebase;

import android.util.Log;
import java.util.ArrayList;

public class WineDatabase {
    public static boolean dataHasBeenPulled = false;
    ArrayList<Drink> drinks = new ArrayList<Drink>();
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
        drinks = new ArrayList<Drink>();
    }

    private WineDatabase() {}

    public void addWine(String name, int img, float rating, String type, int id) {
        drinks.add(new Drink(name, img, rating, type, id));
    }

    public ArrayList<Drink> getWinesThatGoWith(String category) {
        ArrayList<Drink> holder = new ArrayList<Drink>();
        switch (category) {
            case "cow":
                for(Drink drink : drinks) {
                    if(drink.type.equals("red")) {
                        holder.add(drink);
                    }
                }
                break;
           case "pork":
               for(Drink drink : drinks) {
                   if(drink.type.equals("white")) {
                       holder.add(drink);
                   }
               }
                break;
            case "chicken":
                for(Drink drink : drinks) {
                    if(drink.type.equals("white")) {
                        holder.add(drink);
                    }
                }
                break;
            case "vegan":
                for(Drink drink : drinks) {
                    if(drink.type.equals("white")) {
                        holder.add(drink);
                    }
                }
                break;
            case "fish":
                for(Drink drink : drinks) {
                    if(drink.type.equals("rosé")) {
                        holder.add(drink);
                    }
                }
                break;
            case "shellfish":
                for(Drink drink : drinks) {
                    if(drink.type.equals("rosé")) {
                        holder.add(drink);
                    }
                }
                break;
        }
        return holder;
    }

    public Drink getWine(String title) {
        for (Drink drink : drinks) {
            if(drink.name.equals(title)) {
                return drink;
            }
        }

        return null;
    }
}


