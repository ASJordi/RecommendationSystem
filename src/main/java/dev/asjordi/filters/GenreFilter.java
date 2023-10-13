package dev.asjordi.filters;

import dev.asjordi.MovieDatabase;

/**
 * GenreFilter can be used to extract movies in specified genre in the method parameter.
 * @author ASJordi
 * @version 0.0.1
 */

public class GenreFilter implements Filter {

    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(this.genre.trim());
    }

}
