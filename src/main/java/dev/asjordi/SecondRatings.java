package dev.asjordi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to process data analysis regarding movies, raters and ratings
 * @author ASJordi
 * @version 0.0.1
 */

public class SecondRatings {

    private List<Movie> myMovies;
    private List<Rater> myRaters;

    public SecondRatings() {
        myMovies = new ArrayList<>();
        myRaters = new ArrayList<>();
    }

    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    /**
     * @return Returns the number of movies that were read in and stored in the ArrayList of type Movie.
     */
    public int getMovieSize(){
        return this.myMovies.size();
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
            for (String movieID : rater.getItemsRated()){
                if (movieID.equals(id)) {
                    sumOfRatings += rater.getRating(id);
                    countNumRatings++;
                }
            }
        }
        return countNumRatings < minimalRaters ? 0 : sumOfRatings / countNumRatings;
    }


    /**
     * This method find the average rating for every movie that has been rated by at least minimalRaters raters.
     * @param minimalRaters
     * @return Return a List of all the Rating objects for movies that have at least the minimal number of raters supplying a rating.
     */
    public List<Rating> getAverageRatings(int minimalRaters){
        List<Rating> ratingsList = new ArrayList<>();
        Map<String, Integer> ratedMovies = new HashMap<>();

        for (Rater rater : myRaters){
            String currRaterID = rater.getMyID();
            for (String movie : rater.getItemsRated()){
                if (!ratedMovies.containsKey(movie)) ratedMovies.put(movie, 1);
                else ratedMovies.replace(movie, ratedMovies.get(movie) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : ratedMovies.entrySet()){
            if (entry.getValue() >= minimalRaters){
                double avg = getAverageByID(entry.getKey(), minimalRaters);
                Rating rating = new Rating(entry.getKey(), avg);
                ratingsList.add(rating);
            }
        }


        return ratingsList;
    }

    /**
     * This method returns the title of the movie with that ID.
     * If the movie ID does not exist, then this method should return a String indicating the ID was not found.
     * @param id Represent the ID of a movie
     * @return
     */
    public String getTitle(String id){
        id = formatId(id, 7);

        for (Movie movie : myMovies){
            if (movie.getId().equals(id)) return movie.getTitle();
        }

        return "ID was not found";
    }

    /**
     * This method returns the movie ID of this movie.
     * @param title Represent the title of a movie
     * @return
     */
    public String getID(String title){
        for (Movie movie : myMovies){
            if (movie.getTitle().equals(title)) return movie.getId();
        }

        return "No Such Title";
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
