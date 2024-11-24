package com.srv.sumit.dynamodb_repository_demo.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.srv.sumit.dynamodb_repository_demo.entity.MovieDetails;
import com.srv.sumit.dynamodb_repository_demo.mapper.MovieDetailsMapper;
import com.srv.sumit.dynamodb_repository_demo.model.MovieDetailsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceWithDynamoDBMapper {

    private final DynamoDBMapper dynamoDBMapper;
    private final MovieDetailsMapper movieDetailsMapper;

    public MovieServiceWithDynamoDBMapper(DynamoDBMapper dynamoDBMapper, MovieDetailsMapper movieDetailsMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.movieDetailsMapper = movieDetailsMapper;
    }

    @Transactional
    public MovieDetails addMovie(MovieDetailsDTO movieDetailsDTO) {
        MovieDetails movieDetails = movieDetailsMapper.movieDetailsDTOToMovieDetails(movieDetailsDTO);
        dynamoDBMapper.save(movieDetails);
        return movieDetails;
    }

    public MovieDetails retrieveMovie(String id) {
        return dynamoDBMapper.load(MovieDetails.class, id);
    }

    public List<MovieDetails> retrieveAllMovies() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<MovieDetails> paginatedMovies = dynamoDBMapper.scan(MovieDetails.class, scanExpression);

        // Materialize results into a list
        List<MovieDetails> movies = new ArrayList<>(paginatedMovies.size());
        movies.addAll(paginatedMovies);
        return movies;
    }

    @Transactional
    public MovieDetails removeMovie(String id) {
        MovieDetails movieDetails = dynamoDBMapper.load(MovieDetails.class, id);
        if (movieDetails == null) {
            throw new RuntimeException("Unable to find movie with id " + id);
        }
        dynamoDBMapper.delete(movieDetails);
        return movieDetails;
    }

    @Transactional
    public MovieDetails updateMovieDetails(String id, MovieDetailsDTO movieDetails) {
        MovieDetails existingMovie = dynamoDBMapper.load(MovieDetails.class, id);
        if (existingMovie == null) {
            throw new RuntimeException("Unable to find movie with id " + id);
        }

        // Use the mapper to update only the provided fields
        movieDetailsMapper.updateMovieFromDto(movieDetails, existingMovie);

        dynamoDBMapper.save(existingMovie);
        return existingMovie;
    }
}
