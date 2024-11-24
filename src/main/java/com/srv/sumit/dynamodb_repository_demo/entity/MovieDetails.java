package com.srv.sumit.dynamodb_repository_demo.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.data.annotation.Id;

import java.util.Date;

/* @DynamoDBTable Identifies the target table in DynamoDB. */
@DynamoDBTable(tableName = "tbl_movie_dtl")
public class MovieDetails {
    /* @DynamoDBHashKey – Maps a class property to the partition key of the table. */

    @Id
    @DynamoDBHashKey
    /* @DynamoDBAutoGeneratedKey – Marks a partition key or sort key property as being autogenerated.
    DynamoDBMapper generates a random UUID when saving these attributes. Only String properties can be
    marked as autogenerated keys. */
    @DynamoDBAutoGeneratedKey
    private String id;

    /* @DynamoDBAttribute – Maps a property to a table attribute. */
    @DynamoDBAttribute(attributeName = "title")
    private String title;

    @DynamoDBAttribute(attributeName = "year_released")
    private Date year;

    @DynamoDBAttribute(attributeName = "genre")
    private String genre;

    @DynamoDBAttribute(attributeName = "country")
    private String country;

    @DynamoDBAttribute(attributeName = "duration")
    private Integer duration;

    @DynamoDBAttribute(attributeName = "language")
    private String language;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
