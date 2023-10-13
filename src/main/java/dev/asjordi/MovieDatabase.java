package dev.asjordi;

import dev.asjordi.filters.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is an efficient way to get information about movies.
 * @author ASJordi
 * @version 0.0.1
 */

public class MovieDatabase {

    private static Map<String, Movie> ourMovies;

    public static void initialize(String movieFile){
        if (ourMovies == null) {
            ourMovies = new HashMap<>();
            loadMovies("data/" + movieFile);
        }
    }

    private static void initialize(){
        if (ourMovies == null){
            ourMovies = new HashMap<>();
            loadMovies("data/ratedmoviesfull.csv");
        }
    }

    private static void loadMovies(String filename){
        FirstRatings firstRatings = new FirstRatings();
        List<Movie> list = firstRatings.loadMovies(filename);
        for (Movie m : list) ourMovies.put(m.getId(), m);
    }

    public static boolean containsID(String id){
        initialize();
        return ourMovies.containsKey(id);
    }

    public static int getYear(String id){
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id){
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id){
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id){
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id){
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id){
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id){
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id){
        initialize();
        return ourMovies.get(id).getDirector();
    }

    public static int getSize(){
        return ourMovies.size();
    }

    public static List<String> filterBy(Filter f){
        initialize();
        List<String> list = new ArrayList<>();

        for (String id : ourMovies.keySet()){
            if (f.satisfies(id)) list.add(id);
        }

        return list;
    }

}
