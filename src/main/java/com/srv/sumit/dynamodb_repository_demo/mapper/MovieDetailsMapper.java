package com.srv.sumit.dynamodb_repository_demo.mapper;

import com.srv.sumit.dynamodb_repository_demo.entity.MovieDetails;
import com.srv.sumit.dynamodb_repository_demo.model.MovieDetailsDTO;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MovieDetailsMapper {

    // Updates the target object with non-null and non-empty values from the source
    void updateMovieFromDto(MovieDetailsDTO source, @MappingTarget MovieDetails target);

    MovieDetails movieDetailsDTOToMovieDetails(MovieDetailsDTO movieDetailsDTO);

    // Custom condition to ignore null or empty strings
    @Condition
    default boolean isNotNullOrEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Custom condition to ignore null or empty collections
    @Condition
    default boolean isNotNullOrEmptyCollection(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }
}
