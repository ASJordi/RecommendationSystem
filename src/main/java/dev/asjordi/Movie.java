package dev.asjordi;

/**
 * An immutable passive data object (PDO) to represent item data
 * @author ASJordi
 * @version 0.0.1
 */

public class Movie {

    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;

    public Movie() {}

    public Movie(String id, String title, String year, String genres) {
        this.id = id.trim();
        this.title = title.trim();
        this.year = Integer.parseInt(year.trim());
        this.genres = genres;
    }

    public Movie(String id, String title, String year, String genres, String director, String country, String poster, int minutes) {
        this.id = id.trim();
        this.title = title.trim();
        this.year = Integer.parseInt(year.trim());
        this.genres = genres;
        this.director = director;
        this.country = country;
        this.poster = poster;
        this.minutes = minutes;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public String getPoster() {
        return poster;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genres='" + genres + '\'' +
                ", director='" + director + '\'' +
                ", country='" + country + '\'' +
                ", poster='" + poster + '\'' +
                ", minutes=" + minutes +
                '}';
    }
}
