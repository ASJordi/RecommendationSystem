package dev.asjordi.filters;

import dev.asjordi.Movie;
import dev.asjordi.MovieDatabase;

/**
 * MinutesFilter can be used to extract movies with length in between minimum and maximum specified minutes
 * @author ASJordi
 * @version 0.0.1
 */

public class MinutesFilter implements Filter {

    private int min;
    private int max;

    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean satisfies(String id) {
        Movie movie = MovieDatabase.getMovie(id);
        return movie.getMinutes() >= min && movie.getMinutes() <= max;
    }

}
