package dev.asjordi;

/**
 *
 * @author ASJordi
 * @version 0.0.1
 */

import dev.asjordi.filters.TrueFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommendationRunner implements Recommender {

    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> itemsToRate = new ArrayList<>();
        List<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (int i = 0; i < 20; i++) {
            Random random =  new Random();
            int r = random.nextInt(movies.size());
            if (!itemsToRate.contains(movies.get(r))) itemsToRate.add(movies.get(r));
        }

        return itemsToRate;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("<p>Read data for " + RaterDatabase.size() + " raters</p>");
        System.out.println("<p>Read data for " + MovieDatabase.getSize() + " movies</p>");

        int numSimilarRaters = 50;
        int minNumOfRatings = 5;
        List<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minNumOfRatings);

        if (similarRatings.size() == 0) System.out.println("No matching movies were found");
        else {
            String header = ("<table> <tr> <th>Movie</th> <th>Rating</th>  <th>Genres</th> </tr>");
            StringBuilder body = new StringBuilder();

            for (Rating rating : similarRatings) {
                body.append("<tr> <td>").append(MovieDatabase.getTitle(rating.getItem())).append("</td> <td>").append(rating.getValue()).append("</td> <td>").append(MovieDatabase.getGenres(rating.getItem())).append("</td> </tr> ");
            }

            System.out.println(header  + body + "</table>");
        }

    }
}
