package dev.asjordi;

import java.util.ArrayList;
import java.util.List;

/**
 * An immutable passive data object (PDO) to represent the rater data
 * @author ASJordi
 * @version 0.0.1
 */

public class Rater {

    private String myID;
    private List<Rating> myRatings;

    public Rater(String myID) {
        this.myID = myID;
        myRatings = new ArrayList<>();
    }

    public void addRating(String item, double rating){
        this.myRatings.add(new Rating(item, rating));
    }

    public boolean hasRating(String item){

        for (Rating myRating : this.myRatings) {
            if (myRating.getItem().equals(item)) return true;
        }

        return false;

    }

    public String getMyID() {
        return myID;
    }

    public double getRating(String item){

        for (Rating myRating : myRatings) {
            if (myRating.getItem().equals(item)) return myRating.getValue();
        }

        return  - 1;

    }

    public int numRatings(){
        return this.myRatings.size();
    }


    public List<String> getItemsRated(){
        List<String> list = new ArrayList<>();

        for (Rating myRating : myRatings) {
            list.add(myRating.getItem());
        }

        return list;
    }

    @Override
    public String toString() {
        return "Rater{" +
                "myID='" + myID + '\'' +
                ", myRatings=" + myRatings +
                '}';
    }
}
