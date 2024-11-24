package com.srv.sumit.dynamodb_repository_demo.repository;

import com.srv.sumit.dynamodb_repository_demo.entity.MovieDetails;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MovieDetailRepository extends CrudRepository<MovieDetails, String> {
}
