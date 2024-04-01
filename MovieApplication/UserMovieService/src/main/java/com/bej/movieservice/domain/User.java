package com.bej.movieservice.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.List;

// Add the @Document to make User a collection in Mongo DB
@Document
@Builder
@Data
public class    User {
    //   Add  @Id annotation for userId

    private String email;
    private String password;
    private MultipartFile image;
    private String imagePath;
    private List<Movie> movieList;
    @Id
    private String userId;
//    private String name;
//    private String firstName;
//    private String type;
//
//
//    @Lob
//    @Column(name = "imagedata", length = 100000)
//    private byte[]  imageData;


    public User(String email, String password, MultipartFile image, String imagePath, List<Movie> movieList,String userId) {
        this.email = email;
        this.password = password;
        this.image = image;
        this.imagePath = imagePath;
        this.movieList = movieList;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", image=" + image +
                ", imagePath='" + imagePath + '\'' +
                ", movieList=" + movieList +
                ", userId='" + userId + '\'' +
                '}';
    }
}