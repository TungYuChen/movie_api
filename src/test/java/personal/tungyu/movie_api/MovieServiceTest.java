package personal.tungyu.movie_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import personal.tungyu.movie_api.controller.MovieController;
import personal.tungyu.movie_api.dto.IdInputRequest;
import personal.tungyu.movie_api.entity.Movie;
import personal.tungyu.movie_api.dto.ModifyMovieRequest;
import personal.tungyu.movie_api.dto.MovieRequest;

import java.util.List;
import java.util.Random;


@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieController movieController;

    @BeforeEach
    void initialData() {
        while (movieController.findMovieById((long) 6) == null) {
            MovieRequest movieRequest = new MovieRequest();
            movieRequest.setTitle("Test Movie");
            movieRequest.setReleaseYear(2017);
            movieRequest.setDescription("This is a testing movie");
            movieRequest.setDuration(120.00f);
            movieRequest.setRating(4.7f);
        }

        ModifyMovieRequest modifyRequest = new ModifyMovieRequest();
        modifyRequest.setId((long) 6);
        modifyRequest.setRating(4.7f);
        movieController.modifyMovieById(modifyRequest);
    }

    @Test
    void findMovie() {
        Movie movie = movieController.findMovieById((long) 6);
        assert (movie.getTitle()).equals("Test Movie");
        assert (movie.getDescription()).equals("This is a testing movie");
        assert (movie.getDuration()) == 120.00f;
        assert (movie.getRating()) == 4.7f;
        assert (movie.getReleaseYear()) == 2017;
    }

    @Test
    void findMovies() {
        List<Movie> movies = movieController.findMoviesByTitle("Test");
        assert (movies.get(0).getTitle()).equals("Test Movie");
        assert (movies.get(0).getDescription()).equals("This is a testing movie");
        assert (movies.get(0).getDuration()) == 120.00f;
        assert (movies.get(0).getRating()) == 4.7f;
        assert (movies.get(0).getReleaseYear()) == 2017;
    }

    @Test
    void changeMovie() {
        Random random = new Random();
        float rating = random.nextInt(51) / 10;
        ModifyMovieRequest movieRequest = new ModifyMovieRequest();
        movieRequest.setId((long) 6);
        movieRequest.setRating(rating);
        movieController.modifyMovieById(movieRequest);
        Movie movie = movieController.findMovieById((long) 6);

        assert (movie.getRating()) == rating;
    }

    @Test
    void checkLike() {
        Movie movie = movieController.findMovieById((long) 6);
        int likes = movie.getLikes();
        int dislikes = movie.getDislikes();

        movieController.likeMovie(new IdInputRequest(6));
        movieController.dislikeMovie(new IdInputRequest(6));

        movie = movieController.findMovieById((long) 6);

        assert (movie.getLikes()) == likes + 1;
        assert (movie.getDislikes()) == dislikes + 1;
    }
}
