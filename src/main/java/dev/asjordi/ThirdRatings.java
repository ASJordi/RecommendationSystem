package dev.asjordi;

/**
 * ThirdRatings contains methods that can be used to get average ratings (with / without filters).
 * @author ASJordi
 * @version 0.0.1
 */

import dev.asjordi.filters.Filter;
import dev.asjordi.filters.TrueFilter;

import java.util.ArrayList;
import java.util.List;

public class ThirdRatings {

    private List<Rater> myRaters;

    public ThirdRatings() {
        myRaters = new ArrayList<>();
    }

    public ThirdRatings(String ratingsfile){
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    /**
     *
     * @return Returns the number of raters that were read in and stored in the ArrayList of type Rater.
     */
    public int getRaterSize(){
        return this.myRaters.size();
    }

    /**
     * This method returns a double representing the average movie rating for this ID if there are at least minimalRaters ratings.
     * If there are not minimalRaters ratings, then it returns 0.0.
     * @param id Represent a movie ID
     * @param minimalRaters
     * @return
     */
    private double getAverageByID(String id, int minimalRaters){
        id = formatId(id, 7);

        int countNumRatings = 0;
        double sumOfRatings = 0.0;

        for (Rater rater : myRaters){
            if (rater.hasRating(id)){
                sumOfRatings += rater.getRating(id);
                countNumRatings++;
            }
        }

        return countNumRatings >= minimalRaters ? sumOfRatings / countNumRatings : 0;
    }


    /**
     * This method find the average rating for every movie that has been rated by at least minimalRaters raters.
     * @param minimalRaters
     * @return Return a List of all the Rating objects for movies that have at least the minimal number of raters supplying a rating.
     */
    public List<Rating> getAverageRatings(int minimalRaters){
        List<String> movies = MovieDatabase.filterBy(new TrueFilter());
        List<Rating> ratingsList = new ArrayList<>();

        for (String movieID : movies){
            double avg = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (avg != 0.0){
                Rating rating = new Rating(movieID, avg);
                ratingsList.add(rating);
            }
        }

        return ratingsList;
    }

    /**
     * This method return an ArrayList of type Rating of all the movies that have at least minimalRaters ratings and satisfies the filter criteria.
     * @param minimalRaters Minimum number of ratings a movie must have
     * @param filterCriteria
     * @return
     */
    public List<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        List<Rating> list = new ArrayList<>();
        List<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String movieID : movies){
            double avg = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (avg != 0.0){
                Rating rating = new Rating(movieID, avg);
                list.add(rating);
            }
        }

        return list;
    }

    /**
     * Formats an movie id by padding it with leading zeros to achieve the desired length.
     * @param id The original movie id to be formatted
     * @param desiredLength The desired length of the formatted ID
     * @return The formatted ID with leading zeros, if necessary
     */
    public static String formatId(String id, int desiredLength) {
        if (id.length() < desiredLength) return "0".repeat(desiredLength - id.length()) + id;
        else return id;
    }

}
