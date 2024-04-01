package com.springbootapp.callingExternalApi.Service;

import java.io.IOException;

public interface ITmdbService {

    Object getTmdbdata();
    Object getMovieById(int id);
    Object getTopRated();
    Object getUpcoming();
    Object getTv();

   //Object searchMovieByName(int id);
    Object searchMovieByName(String title);
Object getVideoKeys(int id) throws IOException;
   Object getMovieByIdForVideo(int id);
   Object postMovieById(int id);
}
