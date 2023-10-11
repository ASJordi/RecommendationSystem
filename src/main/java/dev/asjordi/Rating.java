package dev.asjordi;

/**
 * An immutable passive data object (PDO) to represent the rating data
 * @author ASJordi
 * @version 0.0.1
 */

public class Rating implements Comparable<Rating> {

    private String item;
    private double value;

    public Rating() {}

    public Rating(String item, double value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "item='" + item + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Rating o) {
        return Double.compare(this.value, o.getValue());
    }

}
