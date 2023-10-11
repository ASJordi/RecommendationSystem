package dev.asjordi;

/**
 * Class to process the movie and ratings data
 * @author ASJordi
 * @version 0.0.1
 */

import java.util.*;
import dev.asjordi.utils.FileResource;
import org.apache.commons.csv.*;

public class FirstRatings {

    /**
     * This method process every record from the CSV file whose name is filename, a file of movie information.
     * @param filename
     * @return Return an ArrayList of type Movie with all of the movie data from the file.
     */
    public List<Movie> loadMovies(String filename){
        List<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord r : parser){
            String id = r.get("id");
            String title = r.get("title");
            String year = r.get("year");
            String country = r.get("country");
            String genre = r.get("genre");
            String director = r.get("director");
            int minutes = Integer.parseInt(r.get("minutes"));
            String poster = r.get("poster");
            Movie movie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(movie);
        }

        return movies;
    }

    /**
     * This method process every record from the CSV file whose name is filename,
     * a file of raters and their ratings.
     * @param filename
     * @return Return an ArrayList of type Rater with all the rater data from the file.
     */
    public List<Rater> loadRaters(String filename){
        List<Rater> raters = new ArrayList<>();
        List<String> listID = new ArrayList<>();

        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord r : parser){

            String raterId = r.get("rater_id");
            String movieId = r.get("movie_id");
            double rating = Double.parseDouble(r.get("rating"));

            if (!listID.contains(raterId)){
                Rater rater = new Rater(raterId);
                rater.addRating(movieId, rating);
                raters.add(rater);
            } else{
                for (Rater rater : raters) {
                    if (rater.getMyID().equals(raterId)) {
                        rater.addRating(movieId, rating);
                    }
                }
            }

            listID.add(raterId);
        }

        return raters;
    }

    public void testLoadMovies(){
        String filename = "data/ratedmoviesfull.csv";
        String genre = "Comedy";
        int duration = 150;
        int filterByGenreCount = 0;
        int filterByDurationCount = 0;
        List<Movie> movies = loadMovies(filename);
        Map<String, Integer> directorsMap = new HashMap<>();
        List<String> maxMoviesPerDirector = new ArrayList<>();

        System.out.println("# of movies: " + movies.size());

        for (Movie m : movies) {
            if (m.getGenres().contains(genre)) filterByGenreCount++;
            if (m.getMinutes() > duration) filterByDurationCount++;

            String[] directors = m.getDirector().split(",");

            for (String d : directors){
                if (!directorsMap.containsKey(d)) directorsMap.put(d, 1);
                else directorsMap.replace(d, directorsMap.get(d) + 1);
            }

        }

        int maxNumOfMoviesPerDirector = directorsMap.values().stream().max(Integer::compareTo).get();

        for (Map.Entry<String, Integer> d : directorsMap.entrySet()){
            if (d.getValue() == maxNumOfMoviesPerDirector) maxMoviesPerDirector.add(d.getKey());
        }

        System.out.println("# of movies that contains " + genre + " genre: " + filterByGenreCount);
        System.out.println("# of movies that are greater than " + duration + ": " + filterByDurationCount);
        System.out.println("Maximum number of movies by any director: " + maxNumOfMoviesPerDirector);
        System.out.println("Directors who direct the maximum number of films: " + maxMoviesPerDirector);
        System.out.println("# directors that have the maximun number of films: " + maxMoviesPerDirector.size());

    }

    public void testLoadRaters(){
        String filename = "data/ratings.csv";
        String raterID = "193";
        String movieID = "1798709";
        int maxNumOfRatings = 0;
        int numOfRatingsForParticularMovie = 0;
        List<Rater> raters = loadRaters(filename);
        List<String> ratersWithMaxRatingsList = new ArrayList<>();
        List<String> differentRatedMovies = new ArrayList<>();

        System.out.println("Total number of raters: " + raters.size());

        for (Rater rater : raters){
//            System.out.println("Rater’s ID: " + rater.getMyID() + " # of ratings: " + rater.numRatings() + " " + "Rating id: " + rater.getItemsRated());
            if (rater.getMyID().equals(raterID)) System.out.println("# of ratings for ID " + raterID + ": " + rater.getItemsRated().size());
            if (rater.getItemsRated().size() > maxNumOfRatings) maxNumOfRatings = rater.getItemsRated().size();
        }

        for (Rater rater : raters){
            if (rater.getItemsRated().size() == maxNumOfRatings) ratersWithMaxRatingsList.add(rater.getMyID());
            if (rater.hasRating(movieID)) numOfRatingsForParticularMovie++;
            for (String movie : rater.getItemsRated()){
                if (!differentRatedMovies.contains(movie)) differentRatedMovies.add(movie);
            }
        }

        System.out.println("Maximum number of ratings by any rater: " + maxNumOfRatings);
        System.out.println("# of raters who have the Maximum number of ratings: " + ratersWithMaxRatingsList.size());
        System.out.println("Raters who have the Maximum number of ratings: " + ratersWithMaxRatingsList);
        System.out.println("Num of ratings for movie " + movieID + ": " + numOfRatingsForParticularMovie);
        System.out.println("# of different movies rated: " + differentRatedMovies.size());
    }

}
