package dev.asjordi.filters;

import dev.asjordi.MovieDatabase;

/**
 * YearsAfterFilter is a filter for a specified year, it selects only those movies that were created on that year or created later than that year.
 * @author ASJordi
 * @version 0.0.1
 */

public class YearAfterFilter implements Filter{

    private final int myYear;

    public YearAfterFilter(int myYear) {
        this.myYear = myYear;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= this.myYear;
    }

}
