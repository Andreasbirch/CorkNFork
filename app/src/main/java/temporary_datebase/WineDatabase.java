package temporary_datebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.andreas.cork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public void addWine(String name, String type, String country, float rating, float price, int ratingAmount, int id, String description) {
        drinks.add(new Drink(name, type, country, rating, price, ratingAmount, id, description));

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

    public void refresh() {
        instance.restartDatabase();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
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

                    instance.addWine(name, type, country, rating, price, ratingAmount, id, description);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}


