package dev.asjordi;

import dev.asjordi.filters.Filter;
import dev.asjordi.filters.TrueFilter;

import java.util.*;

public class FourthRatings {

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

        for (Rater rater : RaterDatabase.getRaters()){
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
     * This method should first translate a rating from the scale 0 to 10
     * to the scale -5 to 5 and return the dot product of the ratings of movies that they both rated.
     * @param me
     * @param r
     * @return
     */
    private double dotProduct(Rater me, Rater r){
        double dotProduct = 0.0;

        for (String item : me.getItemsRated()){
            if (r.getItemsRated().contains(item)){
                double currentRatingR = r.getRating(item);
                double currentRatingMe = me.getRating(item);
                dotProduct += (currentRatingR - 5.0) * (currentRatingMe - 5.0);
            }
        }

        return dotProduct;
    }

    /**
     * This method computes a similarity rating for each rater in the RaterDatabase (except the rater with the ID given by the parameter)
     * to see how similar they are to the Rater whose ID is the parameter to getSimilarities.
     * @param id
     * @return Return a List of type Rating sorted by ratings from highest to lowest rating with the highest rating first and only including those raters who have a positive similarity rating since those with negative values are not similar in any way.
     */
    private List<Rating> getSimilarities(String id){
        List<Rating> list = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);

        for (Rater currentRater : RaterDatabase.getRaters()){
            if (!currentRater.getMyID().equals(id)){
                double dotProduct = dotProduct(me, currentRater);
                if (dotProduct >= 0) list.add(new Rating(currentRater.getMyID(), dotProduct));
            }
        }

        list.sort(Collections.reverseOrder());
        return list;
    }

    /**
     * This method return a List of type Rating,
     * of movies and their weighted average ratings using only the top numSimilarRaters with positive ratings
     * and including only those movies that have at least minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall)
     * These Rating objects should be returned in sorted order by weighted average rating from largest to smallest ratings.
     * @param id Represent a rater ID
     * @param numSimilarRaters
     * @param minimalRaters
     * @return
     */
    public List<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    /**
     *  This method is similar to the getSimilarRatings method but has one additional Filter parameter named filterCriteria and uses that filter to access and rate only those movies that match the filter criteria.
     * @param id
     * @param numSimilarRaters
     * @param minimalRaters
     * @param filterCriteria
     * @return
     */
    public List<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        List<Rating> weightedRatings = new ArrayList<> ();
        List<Rating> similarRaters = getSimilarities(id);
        List<String> movies = MovieDatabase.filterBy(filterCriteria);
        Map<String, Double> accumulatedRating = new HashMap<>();
        Map<String, Integer> accumulatedCount = new HashMap<>();

        for (String movieID : movies){
            double currentRating = 0.0;
            int count = 0;

            for (int i = 0; i < numSimilarRaters; i++) {
                Rating r = similarRaters.get(i);
                String raterID = r.getItem();
                double weight = r.getValue();

                Rater rater = RaterDatabase.getRater(raterID);

                if (rater.hasRating(movieID)){
                    double rating = rater.getRating(movieID) * weight;
                    currentRating += rating;
                    count++;
                }
            }

            if (count >= minimalRaters){
                accumulatedRating.put(movieID, currentRating);
                accumulatedCount.put(movieID, count);
            }
        }

        for (String movieID : accumulatedRating.keySet()){
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating(movieID, weightedRating);
            weightedRatings.add(rating);
        }

        weightedRatings.sort(Collections.reverseOrder());
        return weightedRatings;
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
