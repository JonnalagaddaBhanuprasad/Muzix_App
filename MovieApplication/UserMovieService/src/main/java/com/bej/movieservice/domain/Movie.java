package com.bej.movieservice.domain;

import org.springframework.data.annotation.Id;


public class Movie {
    @Id
    private String  movieId;
    private String   movieTitle;
    private String  movieImage;
    private int movieRating;
    private String  movieGenre;
    private String  movieOverview;
    private String  movieLanguage;
    private String  movieRuntime;
    private Artist artist;

    public Movie() {
    }

    public Movie(String movieId, String movieTitle, String movieImage,
                 int movieRating, String movieGenre, String movieOverview,
                 String movieLanguage, String movieRuntime,Artist artist) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieImage = movieImage;
        this.movieRating = movieRating;
        this.movieGenre = movieGenre;
        this.movieOverview = movieOverview;
        this.movieLanguage = movieLanguage;
        this.movieRuntime = movieRuntime;
        this.artist=artist;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieRuntime() {
        return movieRuntime;
    }

    public void setMovieRuntime(String movieRuntime) {
        this.movieRuntime = movieRuntime;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieImage='" + movieImage + '\'' +
                ", movieRating=" + movieRating +
                ", movieGenre='" + movieGenre + '\'' +
                ", movieOverview='" + movieOverview + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", movieRuntime='" + movieRuntime + '\'' +
                ", Artist='" + artist + '\'' +
                '}';
    }
}
