package dev.asjordi;

import dev.asjordi.filters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MovieRunnerSimilarRatings {

    private final String raterFilename = "ratings.csv";
    private final String movieFilename = "ratedmoviesfull.csv";

    public void printAverageRatings(){

        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.initialize(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + RaterDatabase.size());

        System.out.println(fourthRatings.getAverageRatings(35).size());
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters allFilters = new AllFilters();
        YearAfterFilter yearAfterFilter = new YearAfterFilter(1990);
        GenreFilter genreFilter = new GenreFilter("Drama");
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(genreFilter);

        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.initialize(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + RaterDatabase.size());

        List<Rating> list = fourthRatings.getAverageRatingsByFilter(8, allFilters);
        Collections.sort(list);

        System.out.println("# of movies rated: " + list.size());

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() +
                    " Year: " + MovieDatabase.getYear(rating.getItem()) +
                    " Title: " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatings(){
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.initialize(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.getSize() + " movies");

        List<Rating> list = fourthRatings.getSimilarRatings("71", 20, 5);

        for (Rating rating : list) System.out.println(MovieDatabase.getTitle(rating.getItem()));

    }

    public void printSimilarRatingsByGenre(){
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.initialize(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + RaterDatabase.size());


        String genre = "Mystery";
        GenreFilter filter = new GenreFilter(genre);

        List<Rating> list = fourthRatings.getSimilarRatingsByFilter("964", 20, 5, filter);

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " Movie: " + MovieDatabase.getTitle(rating.getItem()) + " Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector(){
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.initialize(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + RaterDatabase.size());


        List<String> directors = new ArrayList<>(Arrays.asList("Clint Eastwood", "J.J. Abrams", "Alfred Hitchcock", "Sydney Pollack", "David Cronenberg", "Oliver Stone", "Mike Leigh"));
        DirectorsFilter filter = new DirectorsFilter(directors);

        List<Rating> list = fourthRatings.getSimilarRatingsByFilter("120", 10, 2, filter);

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " Movie: " + MovieDatabase.getTitle(rating.getItem()) + " Directors: " + MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.initialize(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + RaterDatabase.size());


        String genre = "Drama";
        GenreFilter genreFilter = new GenreFilter(genre);
        MinutesFilter minutesFilter = new MinutesFilter(80, 160);
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(genreFilter);
        allFilters.addFilter(minutesFilter);

        List<Rating> list = fourthRatings.getSimilarRatingsByFilter("168", 10, 3, allFilters);

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " Movie: " + MovieDatabase.getTitle(rating.getItem()) + " Duration: " + MovieDatabase.getMinutes(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.initialize(raterFilename);
        MovieDatabase.initialize(movieFilename);

        System.out.println("Number of movies: " + MovieDatabase.getSize());
        System.out.println("Number of raters: " + RaterDatabase.size());


        YearAfterFilter yearAfterFilter = new YearAfterFilter(1975);
        MinutesFilter minutesFilter = new MinutesFilter(70, 200);
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(minutesFilter);

        List<Rating> list = fourthRatings.getSimilarRatingsByFilter("314", 10, 5, allFilters);

        for (Rating rating : list){
            System.out.println("Rating: " + rating.getValue() + " Movie: " + MovieDatabase.getTitle(rating.getItem()) + " Year: " + MovieDatabase.getYear(rating.getItem()) + " Duration: " + MovieDatabase.getMinutes(rating.getItem()));
        }
    }


}
