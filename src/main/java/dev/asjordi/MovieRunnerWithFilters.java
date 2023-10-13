package dev.asjordi;

import dev.asjordi.filters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MovieRunnerWithFilters contains tests for ThirdRatings class.
 * @author ASJordi
 * @version 0.0.1
 */

public class MovieRunnerWithFilters {

    private final String raterFilename = "data/ratings.csv";
    private final String movieFilename = "ratedmoviesfull.csv";

    public void printAverageRatings(){

        ThirdRatings thirdRatings = new ThirdRatings(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        System.out.println(thirdRatings.getAverageRatings(35).size());

//        System.out.println("Title of the movie: " + secondRatings.getTitle("6414"));

//        System.out.println("ID of the movie: " + secondRatings.getID("Heat"));

        // Print Ranking of movies
//        List<Rating> ratingList = thirdRatings.getAverageRatings(1);
//        Collections.sort(ratingList);
//
//        System.out.println("# of movies rated: " + ratingList.size());

//        for (Rating rating : ratingList){
//            System.out.println("Rating: " + rating.getValue() + " Movie: " + MovieDatabase.getTitle(rating.getItem()));
//        }

    }

    public void printAverageRatingsByYear(){

        YearAfterFilter yearAfterFilter = new YearAfterFilter(2000);
        ThirdRatings thirdRatings = new ThirdRatings(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        List<Rating> list = thirdRatings.getAverageRatingsByFilter(20, yearAfterFilter);
        Collections.sort(list);

        System.out.println("# of movies rated: " + list.size());

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " Year: " + MovieDatabase.getYear(rating.getItem()) +
                    " Title: " + MovieDatabase.getTitle(rating.getItem()));
        }

    }

    public void printAverageRatingsByGenre(){
        GenreFilter genreFilter = new GenreFilter("Comedy");
        ThirdRatings thirdRatings = new ThirdRatings(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        List<Rating> list = thirdRatings.getAverageRatingsByFilter(20, genreFilter);
        Collections.sort(list);

        System.out.println("# of movies rated: " + list.size());

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " Title: " + MovieDatabase.getTitle(rating.getItem()) + " Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByMinutes(){
        MinutesFilter minutesFilter = new MinutesFilter(105, 135);
        ThirdRatings thirdRatings = new ThirdRatings(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        List<Rating> list = thirdRatings.getAverageRatingsByFilter(5, minutesFilter);
        Collections.sort(list);

        System.out.println("# of movies rated: " + list.size());

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " Title: " + MovieDatabase.getTitle(rating.getItem()));
        }

    }

    public void printAverageRatingsByDirectors(){
        List<String> directors = new ArrayList<>(Arrays.asList("Clint Eastwood", "Joel Coen", "Martin Scorsese", "Roman Polanski", "Nora Ephron", "Ridley Scott", "Sydney Pollack"));
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        ThirdRatings thirdRatings = new ThirdRatings(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        List<Rating> list = thirdRatings.getAverageRatingsByFilter(4, directorsFilter);
        Collections.sort(list);

        System.out.println("# of movies rated: " + list.size());

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " Title: " + MovieDatabase.getTitle(rating.getItem()) +
                    " Directors: " + MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters allFilters = new AllFilters();
        YearAfterFilter yearAfterFilter = new YearAfterFilter(1990);
        GenreFilter genreFilter = new GenreFilter("Drama");
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(genreFilter);

        ThirdRatings thirdRatings = new ThirdRatings(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        List<Rating> list = thirdRatings.getAverageRatingsByFilter(8, allFilters);
        Collections.sort(list);

        System.out.println("# of movies rated: " + list.size());

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " Year: " + MovieDatabase.getYear(rating.getItem()) +
                    " Title: " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(){
        AllFilters allFilters = new AllFilters();
        MinutesFilter minutesFilter = new MinutesFilter(90, 180);
        List<String> directors = new ArrayList<>(Arrays.asList("Clint Eastwood", "Joel Coen", "Tim Burton", "Ron Howard", "Nora Ephron", "Sydney Pollack"));
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(directorsFilter);

        ThirdRatings thirdRatings = new ThirdRatings(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        List<Rating> list = thirdRatings.getAverageRatingsByFilter(3, allFilters);
        Collections.sort(list);

        System.out.println("# of movies rated: " + list.size());

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " Time: " + MovieDatabase.getMinutes(rating.getItem()) +
                    " Title: " + MovieDatabase.getTitle(rating.getItem()) +
                    " Directors: " + MovieDatabase.getDirector(rating.getItem()));
        }
    }

}
