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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMovie(@RequestBody MovieRequest movieRequest) {
        Movie newMovie = movieService.createMovie(movieRequest);
        if (newMovie.getTitle() != null)
            return "Create movie successfully! " + newMovie;
        else
            return "Create movie failed";
    }

    @GetMapping("/id={id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findMovieById(@PathVariable Long id) {
        return movieService.findMovieById(id);
    }

    @GetMapping("/title={title}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findMovieByTitle(@PathVariable String title) {
        return movieService.findMovieByTitle(title);
    }

    @PatchMapping("/update/id={id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movie modifyMovieById(@PathVariable long id, @RequestBody ModifyMovieRequest movieRequest) {
        return movieService.modifyMovie(id, movieRequest);
    }

    @DeleteMapping("delete/id={id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteMovieById(@PathVariable long id) {
        if (movieService.deleteMovieById(id))
            return "Delete Movie successfully";
        else
            return "No such movie id";
    }

    @PatchMapping("/like/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean likeMovie(@RequestBody IdInputRequest input) {
        long id = input.getId();
        return movieService.likeMovie(id);
    }

    @PatchMapping("/dislike/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean dislikeMovie(@PathVariable IdInputRequest input) {
        long id = input.getId();
        return movieService.dislikeMovie(id);
    }


    @GetMapping("/movies/t={title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> findMoviesByTitle(@PathVariable String title) {
        return movieService.findMoviesByTitle(title.trim());
    }
}
