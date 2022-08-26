package personal.tungyu.movie_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.tungyu.movie_api.entity.Movie;
import personal.tungyu.movie_api.model.ModifyMovieRequest;
import personal.tungyu.movie_api.model.MovieRequest;
import personal.tungyu.movie_api.repository.MovieRepository;

import java.lang.module.FindException;
import java.util.List;

@Service
public record MovieService(MovieRepository movieRepository) {

    public List<Movie> findMoviesByTitle(String title) {
        return movieRepository.findMoviesByTitle(title).orElseThrow(() -> new FindException("No relative movie be found"));
    }

    public Movie findMovieById(long id) {
        return movieRepository.findById(id).orElseThrow(() -> new FindException("It's not a valid id"));
    }

    public Movie findMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title).orElseThrow(() -> new FindException("No title matched"));
    }

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

    public Movie modifyMovie(long id, ModifyMovieRequest movieRequest) {
        Movie targetMovie = movieRepository.findById(id).orElseThrow(() -> new FindException("It's not a valid id"));
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

    public boolean deleteMovieById(long id) {
        try {
            movieRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
