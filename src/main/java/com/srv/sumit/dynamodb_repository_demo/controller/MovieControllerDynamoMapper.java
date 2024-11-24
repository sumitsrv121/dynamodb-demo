package com.srv.sumit.dynamodb_repository_demo.controller;

import com.srv.sumit.dynamodb_repository_demo.entity.MovieDetails;
import com.srv.sumit.dynamodb_repository_demo.model.MovieDetailsDTO;
import com.srv.sumit.dynamodb_repository_demo.service.MovieServiceWithDynamoDBMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/v2/movies")
public class MovieControllerDynamoMapper {
    private final MovieServiceWithDynamoDBMapper movieService;

    public MovieControllerDynamoMapper(MovieServiceWithDynamoDBMapper movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public MovieDetails addMovie(@RequestBody MovieDetailsDTO movieDetails) {
        return movieService.addMovie(movieDetails);
    }

    @GetMapping
    public List<MovieDetails> getAllMovie() {
        return movieService.retrieveAllMovies();
    }

    @GetMapping("/{movieId}")
    public MovieDetails getMovieById(@PathVariable("movieId") String movieId) {
        return movieService.retrieveMovie(movieId);
    }

    @PatchMapping("/{movieId}")
    public MovieDetails updateMovieDetails(@PathVariable("movieId") String movieId,
                                           @RequestBody MovieDetailsDTO movieDetailsDTO) {
        return movieService.updateMovieDetails(movieId, movieDetailsDTO);
    }

    @DeleteMapping("/{movieId}")
    public MovieDetails deleteMovieDetails(@PathVariable("movieId") String movieId) {
        return movieService.removeMovie(movieId);
    }
}
