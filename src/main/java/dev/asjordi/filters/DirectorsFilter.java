package dev.asjordi.filters;

/**
 * DirectorsFilter can be used to extract movies directed by either of the specified directors
 * @author ASJordi
 * @version 0.0.1
 */

import dev.asjordi.Movie;
import dev.asjordi.MovieDatabase;

import java.util.List;

public class DirectorsFilter implements Filter {

    private List<String> directors;

    public DirectorsFilter(List<String> directors) {
        this.directors = directors;
    }

    @Override
    public boolean satisfies(String id) {
        Movie movie = MovieDatabase.getMovie(id);
        String[] directorMovie = movie.getDirector().split(",");

        for (String director : directorMovie){
            if (this.directors.contains(director)) return true;
        }

        return false;
    }

}
