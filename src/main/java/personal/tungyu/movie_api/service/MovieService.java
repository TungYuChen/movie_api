package personal.tungyu.movie_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import personal.tungyu.movie_api.entity.Movie;
import personal.tungyu.movie_api.dto.ModifyMovieRequest;
import personal.tungyu.movie_api.dto.MovieRequest;
import personal.tungyu.movie_api.repository.MovieRepository;

import javax.transaction.Transactional;
import java.lang.module.FindException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;

    /**
     * Find movies contains input string in the title
     * @param title
     * @return a list contains movies
     */
    public List<Movie> findMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.findAllByTitleContains(title);
        if (movies.isEmpty()) {
            throw new FindException("No movie title contains input string");
        }
        return movieRepository.findAllByTitleContains(title);
    }

    /**
     * Find a movie by movie's id
     * @param id
     * @return the specific movie
     */
    public Movie findMovieById(long id) {
        return movieRepository.findById(id).orElseThrow(() -> new FindException("It's not a valid id"));
    }

    /**
     * Find a movie by title, have to matched exactly
     * @param title
     * @return the specific movie
     */
    public Movie findMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title).orElseThrow(() -> new FindException("No title matched"));
    }

    /**
     * Create a movie with input
     * @param movieRequest
     * @return the movie which just be created
     */
    public Movie createMovie(MovieRequest movieRequest) {
        Movie newMovie = new Movie(
                movieRequest.getTitle(),
                movieRequest.getDescription(),
                movieRequest.getReleaseYear(),
                movieRequest.getDuration(),
                movieRequest.getRating()
        );
        return movieRepository.save(newMovie);
    }

    /**
     * Update the movie
     * @param movieRequest
     * @return the movie which was updated
     */
    @Transactional
    public Movie modifyMovie(ModifyMovieRequest movieRequest) {
        Movie targetMovie = movieRepository.findById(movieRequest.getId()).orElseThrow(() -> new FindException("It's not a valid id"));
        if (movieRequest.getTitle() != null)
            targetMovie.setTitle(movieRequest.getTitle());
        if (movieRequest.getDescription() != null)
            targetMovie.setDescription(movieRequest.getDescription());
        if (movieRequest.getDuration() != null)
            targetMovie.setDuration(movieRequest.getDuration());
        if (movieRequest.getRating() != null)
            targetMovie.setRating(movieRequest.getRating());
        if (movieRequest.getReleaseYear() != null)
            targetMovie.setReleaseYear(movieRequest.getReleaseYear());

        return movieRepository.save(targetMovie);

    }

    /**
     * Delete the movie with input id
     * @param id
     * @return delete successfully or not
     */
    public boolean deleteMovieById(long id) {
        try {
            movieRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }

    }

    /**
     * Like the movie and increase the like amount
     * @param id
     * @return like successfully or not
     */
    @Transactional
    public boolean likeMovie(long id) {
        Movie targetMovie = movieRepository.findById(id).orElseThrow(() -> new FindException("It's not a valid id"));
        targetMovie.setLikes(targetMovie.getLikes() + 1);
        movieRepository.save(targetMovie);
        return true;
    }

    /**
     * Dislike the movie and increase the dislike amount
     * @param id
     * @return dislike successfully or not
     */
    @Transactional
    public boolean dislikeMovie(long id) {
        Movie targetMovie = movieRepository.findById(id).orElseThrow(() -> new FindException("It's not a valid id"));
        targetMovie.setDislikes(targetMovie.getDislikes() + 1);
        movieRepository.save(targetMovie);
        return true;
    }

}
