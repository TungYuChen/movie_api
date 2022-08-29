package personal.tungyu.movie_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.tungyu.movie_api.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByTitleContains(String title);
    Optional<Movie> findMovieByTitle(String title);
}
