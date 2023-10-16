package dev.asjordi;

/**
 * This class is an efficient way to get information about raters.
 * @author ASJordi
 * @version 0.0.1
 */

import dev.asjordi.utils.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaterDatabase {

    private static Map<String, Rater> ourRaters;

    public static void initialize(String filename){
        if (ourRaters == null){
            ourRaters = new HashMap<>();
            addRatings("data/" + filename);
        }
    }

    private static void initialize(){
        if (ourRaters == null) ourRaters = new HashMap<>();
    }

    public static void addRatings(String filename){
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord r : parser){
            String id = r.get("rater_id");
            String item = r.get("movie_id");
            String rating = r.get("rating");
            addRaterRating(id, item, Double.parseDouble(rating));
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating){
        initialize();
        Rater rater = null;

        if (ourRaters.containsKey(raterID)) rater = ourRaters.get(raterID);
        else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID, rater);
        }

        rater.addRating(movieID, rating);
    }

    public static Rater getRater(String id){
        initialize();
        return ourRaters.get(id);
    }

    public static List<Rater> getRaters(){
        initialize();
        return new ArrayList<>(ourRaters.values());
    }

    public static int size(){
        return ourRaters.size();
    }

}
