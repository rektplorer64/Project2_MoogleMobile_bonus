package tanawinwichitcom.android.mooglemobile.moviefetcher;// Name: Tanawin Wichit
// Student ID: 6088221
// Section: 1

import java.util.*;

public class Movie{
    private int mid;
    private String title;
    private int year;
    private Set<String> tags;
    private Map<Integer, Rating> ratings;    //mapping userID -> rating
    private Double avgRating;
    //additional

    public Movie(int _mid, String _title, int _year){
        this.mid = _mid;
        this.title = _title;
        this.year = _year;

        tags = new HashSet<>();
        ratings = new HashMap<>();
    }

    public int getID(){
        return mid;
    }

    public String getTitle(){
        return title;
    }

    public Set<String> getTags(){
        return tags;
    }

    public void addTag(String tag){
        tags.add(tag);
    }

    public int getYear(){
        return year;
    }

    public String toString(){
        avgRating = calMeanRating();
        return "[mid: " + mid + ":" + title + " (" + year + ") " + tags + "] -> avg rating: " + avgRating;
    }

    public String toQueryString(boolean wantTitle, boolean wantTag, boolean wantYear, boolean wantRatings){
        avgRating = calMeanRating();
        return ((wantTitle) ? title : "") + ((wantYear) ? (" (" + year + ") ") : "") +
                ((wantTag) ? (" " + tags + " ") : "") + " " + ((wantRatings) ? (" " + avgRating + " ") : "");
    }

    public double calMeanRating(){
        double sum = 0;
        //System.out.println("rating size " + ratings.size());
        for(Rating f : ratings.values()){
            sum += f.rating;
        }
        avgRating = sum / ratings.size();
        return avgRating;
    }

    public Double getMeanRating(){
        avgRating = calMeanRating();
        return avgRating;
    }

    public void addRating(User user, Movie movie, double rating, long timestamp){
        ratings.put(user.getID(), new Rating(user, movie, rating, timestamp));
    }

    public Map<Integer, Rating> getRating(){
        return ratings;
    }

    public static boolean isAvailableInTheDatabase(int movieID, Map<Integer, Movie> moviesMap){
        return moviesMap.get(movieID) != null && movieID == moviesMap.get(movieID).mid;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        Movie movie = (Movie) o;

        if(mid != movie.mid){
            return false;
        }
        if(year != movie.year){
            return false;
        }
        if(!title.equals(movie.title)){
            return false;
        }
        if(!tags.equals(movie.tags)){
            return false;
        }
        if(!ratings.equals(movie.ratings)){
            return false;
        }
        return avgRating.equals(movie.avgRating);
    }

    @Override
    public int hashCode(){
        int result = mid;
        result = 31 * result + title.hashCode();
        result = 31 * result + year;
        result = 31 * result + tags.hashCode();
        result = 31 * result + ratings.hashCode();
        result = 31 * result + avgRating.hashCode();
        return result;
    }
}
