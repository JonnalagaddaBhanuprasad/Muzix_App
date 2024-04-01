package com.bej.movieservice.domain;

public class Artist {

    private String artistName;

    public Artist() {
    }

    @Override
    public String toString() {
        return "Artist{" +

                ", artistName='" + artistName + '\'' +
                '}';
    }

    public Artist(String artistName) {

        this.artistName = artistName;
    }



    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
