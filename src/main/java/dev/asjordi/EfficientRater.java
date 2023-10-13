package dev.asjordi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EfficientRater class contains various methods that are used to add and extract ratings from the ratings HashMap.
 * @author ASJordi
 * @version 0.0.1
 */

public class EfficientRater implements Rater {

    private String myID;
    private Map<String, Rating> ratings;

    public EfficientRater(String myID) {
        this.myID = myID;
        ratings = new HashMap<>();
    }

    @Override
    public void addRating(String item, double rating){
        this.ratings.put(item, new Rating(item, rating));
    }

    @Override
    public boolean hasRating(String item){
        return ratings.containsKey(item);
    }

    @Override
    public String getMyID() {
        return myID;
    }

    @Override
    public double getRating(String item){

        for (Map.Entry<String, Rating> entry : ratings.entrySet()){
            if (entry.getValue().getItem().equals(item)) return entry.getValue().getValue();
        }

        return  - 1;

    }

    @Override
    public int numRatings(){
        return this.ratings.size();
    }

    @Override
    public List<String> getItemsRated(){
        List<String> list = new ArrayList<>();

        for (Map.Entry<String, Rating> entry : ratings.entrySet()){
            list.add(entry.getValue().getItem());
        }

        return list;
    }

    @Override
    public String toString() {
        return "EfficientRater{" +
                "myID='" + myID + '\'' +
                ", ratings=" + ratings +
                '}';
    }
}
