package personal.tungyu.movie_api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import personal.tungyu.movie_api.dto.IdInputRequest;
import personal.tungyu.movie_api.entity.Movie;
import personal.tungyu.movie_api.dto.ModifyMovieRequest;
import personal.tungyu.movie_api.dto.MovieRequest;
import personal.tungyu.movie_api.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * Create Movie with some basic information
     * @param movieRequest
     * @return String to show the result and feedback
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMovie(@RequestBody MovieRequest movieRequest) {
        Movie newMovie = movieService.createMovie(movieRequest);
        if (newMovie.getTitle() != null)
            return "Create movie successfully! " + newMovie;
        else
            return "Create movie failed";
    }

    /**
     * Get one movie by id
     * @param id
     * @return Movie details if extist
     */
    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findMovieById(@PathVariable Long id) {
        return movieService.findMovieById(id);
    }

    /**
     * Get a movie by title (should match all)
     * @param title
     * @return Movie details if exist
     */
    @GetMapping("/title={title}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findMovieByTitle(@PathVariable String title) {
        return movieService.findMovieByTitle(title);
    }

    /**
     * Modify specific movie
     * @param id
     * @param movieRequest
     * @return Movie details if success
     */
    @PatchMapping("/update/id={id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movie modifyMovieById(@PathVariable long id, @RequestBody ModifyMovieRequest movieRequest) {
        return movieService.modifyMovie(id, movieRequest);
    }

    /**
     * Delete a movie by id
     * @param id
     * @return result string
     */
    @DeleteMapping("delete/id={id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteMovieById(@PathVariable long id) {
        if (movieService.deleteMovieById(id))
            return "Delete Movie successfully";
        else
            return "No such movie id";
    }

    /**
     * Add like count to a movie
     * @param input
     * @return boolean to tell successful or not
     */
    @PatchMapping("/like/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean likeMovie(@RequestBody IdInputRequest input) {
        long id = input.getId();
        return movieService.likeMovie(id);
    }

    /**
     * Add dislike count to a movie
     * @param input
     * @return boolean to tell successful or not
     */
    @PatchMapping("/dislike/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean dislikeMovie(@PathVariable IdInputRequest input) {
        long id = input.getId();
        return movieService.dislikeMovie(id);
    }


    /**
     * Get movies including the string in title
     * @param title
     * @return a list of movies
     */
    @GetMapping("/movies/t={title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> findMoviesByTitle(@PathVariable String title) {
        return movieService.findMoviesByTitle(title.trim());
    }
}
