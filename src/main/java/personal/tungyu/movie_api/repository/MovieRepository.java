package personal.tungyu.movie_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.tungyu.movie_api.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT * FROM movies WHERE title LIKE %?1%",
    nativeQuery = true)
    Optional<List<Movie>> findMoviesByTitle(String title);
    Optional<Movie> findMovieByTitle(String title);
}
