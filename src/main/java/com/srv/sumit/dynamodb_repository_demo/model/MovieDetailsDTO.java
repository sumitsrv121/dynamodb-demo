package com.srv.sumit.dynamodb_repository_demo.model;

import java.util.Date;


public class MovieDetailsDTO {
    private String title;

    private Date year;

    private String genre;

    private String country;

    private Integer duration;

    private String language;

    public MovieDetailsDTO() {
    }

    public MovieDetailsDTO(String title, Date year, String genre, String country, Integer duration, String language) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.country = country;
        this.duration = duration;
        this.language = language;
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
