// --== CS400 File Header Information ==--
// Name: Xianfu Luo
// Email: xluo96@wisc.edu
// Team: Blue
// Group: CG
// TA: Xi Chen
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.Arrays;
import java.util.List;

/**
 * This class defines MovieObject for store movie.
 * @author Xianfu Luo
 */
public class MovieObject implements MovieInterface{
    // constructor
    public MovieObject(String title, int year, String genres, String director, String description, Float avgVote){
        this.title = title;
        this.year = year;
        this.avgVote = avgVote;
        this.director = director;
        this.description = description;
        this.genres = Arrays.asList(genres.split(", "));
    }
    private String title;
    private int year;
    private List<String> genres;
    private String director;
    private String description;
    private Float avgVote;
    @Override public String getTitle() {

        return title;
    }

    @Override public Integer getYear() {
        return year;
    }

    @Override public List<String> getGenres() {
        return genres;
    }

    @Override public String getDirector() {
        return director;
    }

    @Override public String getDescription() {
        return description;
    }

    @Override public Float getAvgVote() {
        return avgVote;
    }

    @Override public int compareTo(MovieInterface otherMovie) {

        return otherMovie.getAvgVote().compareTo(this.getAvgVote());
    }
    
    @Override
    public String toString() {
        String result = "title: " + title + "\n";
        result += "director: " + director + "\n";
        result += "description: " + description + "\n";
        result += "year: " + year + "\n";
        result += "avgVote: " + avgVote + "\n";
        result += "genre:";
        for(int i = 0; i < genres.size(); i++) result += " " + genres.get(i);
        return result;
    }
}
