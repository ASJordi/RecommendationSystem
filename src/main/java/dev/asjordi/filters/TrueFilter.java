package dev.asjordi.filters;

/**
 * Class TrueFilter can be used to select every movie from MovieDatabase. It’s satisfies method always returns true.
 * @author ASJordi
 * @version 0.0.1
 */

public class TrueFilter implements Filter {

    @Override
    public boolean satisfies(String id) {
        return true;
    }

}
