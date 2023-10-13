package dev.asjordi.filters;

import java.util.ArrayList;
import java.util.List;

/**
 * Class AllFilters combines several filters.
 * @author ASJordi
 * @version 0.0.1
 */

public class AllFilters implements Filter {

    private List<Filter> filters;

    public AllFilters() {
        this.filters = new ArrayList<>();
    }

    /**
     * This method allows one to add a Filter to the ArrayList filters.
     * @param f
     */
    public void addFilter(Filter f){
        this.filters.add(f);
    }

    /**
     * This method returns true if the movie satisfies the criteria of all the filters in the filters ArrayList.
     * Otherwise this method returns false.
     * @param id
     * @return
     */
    @Override
    public boolean satisfies(String id) {
        for (Filter f : filters) if (!f.satisfies(id)) return false;
        return true;
    }

}
