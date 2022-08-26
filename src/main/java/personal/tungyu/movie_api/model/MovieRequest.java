package personal.tungyu.movie_api.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    @NonNull
    private String title;
    @NonNull
    private String description;
    private int releaseYear;
    private float duration;
    private float rating;
}
