package dev.asjordi;

import java.util.Collections;
import java.util.List;

/**
 * Test the methods of SecondsRatings class.
 * @author ASJordi
 * @version 0.0.1
 */

public class MovieRunnerAverage {

    public void printAverageRatings(){
        String movieFilename = "data/ratedmoviesfull.csv";
        String raterFilename = "data/ratings.csv";

        SecondRatings secondRatings = new SecondRatings(movieFilename, raterFilename);

//        System.out.println("Number of movies: " + secondRatings.getMovieSize());
//        System.out.println("Number of raters: " + secondRatings.getRaterSize());

//        System.out.println(secondRatings.getAverageRatings(1));

//        System.out.println("Title of the movie: " + secondRatings.getTitle("6414"));

//        System.out.println("ID of the movie: " + secondRatings.getID("Heat"));

        // Print Ranking of movies
        List<Rating> ratingList = secondRatings.getAverageRatings(12);
        Collections.sort(ratingList);

        System.out.println("# of movies rated: " + ratingList.size());

        for (Rating rating : ratingList){
            System.out.println("Rating: " + rating.getValue() + " Movie: " + secondRatings.getTitle(rating.getItem()));
        }

    }

    public void getAverageRatingOneMovie(){
        String movieFilename = "data/ratedmoviesfull.csv";
        String raterFilename = "data/ratings.csv";
        String movieTitle = "Vacation";

        SecondRatings secondRatings = new SecondRatings(movieFilename, raterFilename);
        List<Rating> ratingList = secondRatings.getAverageRatings(3);

        for (Rating rating : ratingList){
            if (secondRatings.getTitle(rating.getItem()).equals(movieTitle)) System.out.println(rating.getValue());
        }

    }

}
