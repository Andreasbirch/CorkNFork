package temporary_datebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.andreas.cork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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

    public void addWine(String name, String type, String country, float rating, float price, int ratingAmount, int id, String description, List<String> goesWith, String goesWithMeals) {
        drinks.add(new Drink(name, type, country, rating, price, ratingAmount, id, description, goesWith, goesWithMeals));

    }

    public ArrayList<Drink> getWinesThatGoWith(String category) {
        ArrayList<Drink> holder = new ArrayList<Drink>();
        switch (category) {
            case "cow":
                for(Drink drink : drinks) {
                    if(drink.goesWith.contains("Beef")){
                        holder.add(drink);
                    }
                }
                break;
           case "pork":
               for(Drink drink : drinks) {
                   if(drink.goesWith.contains("Pork")){
                       holder.add(drink);
                   }
               }
                break;
            case "chicken":
                for(Drink drink : drinks) {
                    if(drink.goesWith.contains("Poultry")){
                        holder.add(drink);
                    }
                }
                break;
            case "vegan":
                for(Drink drink : drinks) {
                    if(drink.goesWith.contains("Vegetarian")){
                        holder.add(drink);
                    }
                }
                break;
            case "fish":
                for(Drink drink : drinks) {
                    if(drink.goesWith.contains("Fish")){
                        holder.add(drink);
                    }
                }
                break;
            case "shellfish":
                for(Drink drink : drinks) {
                    if(drink.goesWith.contains("Shellfish")){
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

    public void refresh() {
        instance.restartDatabase();
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("drinks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // add wines from firebase
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String name = snapshot.child("name").getValue(String.class);
                    float rating = snapshot.child("rating").getValue(Float.class);
                    String type = snapshot.child("type").getValue(String.class);
                    int id = snapshot.child("id").getValue(Integer.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String country = snapshot.child("country").getValue(String.class);
                    int price = snapshot.child("price").getValue(Integer.class);
                    int ratingAmount = snapshot.child("ratingAmount").getValue(Integer.class);
                    List<String> goesWith = new ArrayList<>();
                    String goesWithMeals = snapshot.child("goesWithMeals").getValue(String.class);
                    char[] c = goesWithMeals.toCharArray();

                    if(c[0]== '1'){
                        goesWith.add("Beef");
                    }
                    if(c[1]== '1'){
                        goesWith.add("Pork");
                    }
                    if(c[2]== '1'){
                        goesWith.add("Poultry");
                    }
                    if(c[3]== '1'){
                        goesWith.add("Vegetarian");
                    }
                    if(c[4]== '1'){
                        goesWith.add("Fish");
                    }
                    if(c[5]== '1'){
                        goesWith.add("Shellfish");
                    }

                    instance.addWine(name, type, country, rating, price, ratingAmount, id, description, goesWith, goesWithMeals);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}


