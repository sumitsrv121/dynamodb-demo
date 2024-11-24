package com.srv.sumit.dynamodb_repository_demo.service;

import com.srv.sumit.dynamodb_repository_demo.entity.MovieDetails;
import com.srv.sumit.dynamodb_repository_demo.mapper.MovieDetailsMapper;
import com.srv.sumit.dynamodb_repository_demo.model.MovieDetailsDTO;
import com.srv.sumit.dynamodb_repository_demo.repository.MovieDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieDetailRepository movieRepository;
    private final MovieDetailsMapper movieDetailsMapper;

    public MovieService(MovieDetailRepository movieRepository, MovieDetailsMapper movieDetailsMapper) {
        this.movieRepository = movieRepository;
        this.movieDetailsMapper = movieDetailsMapper;
    }

    @Transactional
    public MovieDetails addMovie(MovieDetailsDTO movieDetailsDTO) {
        MovieDetails movieDetails = movieDetailsMapper.movieDetailsDTOToMovieDetails(movieDetailsDTO);
        return movieRepository.save(movieDetails);
    }

    public MovieDetails retrieveMovie(String id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to find Movie with id " + id));
    }

    public List<MovieDetails> retrieveAllMovies() {
        List<MovieDetails> movieDetailsList = new ArrayList<>();
        movieRepository.findAll().forEach(movieDetailsList::add);
        return movieDetailsList;
    }

    @Transactional
    public MovieDetails removeMovie(String id) {
        Optional<MovieDetails> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            throw new RuntimeException("Unable to find Movie with id " + id);
        }

        MovieDetails movie = optionalMovie.get();
        movieRepository.delete(movie);
        return movie;
    }

    @Transactional
    public MovieDetails updateMovieDetails(String id, MovieDetailsDTO movieDetails) {
        Optional<MovieDetails> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            throw new RuntimeException("Unable to find Movie with id " + id);
        }

        MovieDetails existingMovie = optionalMovie.get();
        // Use the mapper to update only the provided fields
        movieDetailsMapper.updateMovieFromDto(movieDetails, existingMovie);

        return movieRepository.save(existingMovie);
    }
}
