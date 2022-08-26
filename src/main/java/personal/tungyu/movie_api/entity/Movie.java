package personal.tungyu.movie_api.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Movie {

    public Movie(String title, String description, int releaseYear, float duration, float rating) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.rating = rating;
        this.likes = 0;
        this.dislikes = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    private int releaseYear;
    private float duration;
    private float rating;
    private int likes;
    private int dislikes;
}
