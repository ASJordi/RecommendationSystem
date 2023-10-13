package dev.asjordi;

/**
 * Rater is an interface for PlainRater and EfficientRater classes
 * @author ASJordi
 * @version 0.0.1
 */

import java.util.List;

public interface Rater {

    void addRating(String item, double rating);
    boolean hasRating(String item);
    String getMyID();
    double getRating(String item);
    int numRatings();
    List<String> getItemsRated();

}
