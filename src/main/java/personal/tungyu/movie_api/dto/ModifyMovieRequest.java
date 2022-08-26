package personal.tungyu.movie_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyMovieRequest {
    @Nullable
    private String title;
    @Nullable
    private String description;
    @Nullable
    private Integer releaseYear;
    @Nullable
    private Float duration;
    @Nullable
    private Float rating;
}
