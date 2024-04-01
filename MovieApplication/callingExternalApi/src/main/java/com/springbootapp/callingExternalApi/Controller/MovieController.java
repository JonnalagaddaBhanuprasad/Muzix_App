package com.springbootapp.callingExternalApi.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootapp.callingExternalApi.Service.TmdbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/v1")
public  class MovieController {

    @Autowired
    private TmdbServiceImpl tmdbSrvice;

    public MovieController(TmdbServiceImpl tmdbSrvice) {
        this.tmdbSrvice = tmdbSrvice;
    }

    @GetMapping("/getMovie")
    public Object getTmdbData() {
        return tmdbSrvice.getTmdbdata();
    }

    @GetMapping("/getTopRatedMovie")
    public Object getTopRated() {
        return tmdbSrvice.getTopRated();
    }

    @GetMapping("/getTv")
    public Object getTv() {
        return tmdbSrvice.getTv();
    }

    @GetMapping("/getUpcoming")
    public Object getUpcoming() {
        return tmdbSrvice.getUpcoming();
    }

    @GetMapping("/getMovie/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(tmdbSrvice.getMovieById(id));
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Movie with ID " + id + " not found");
        }
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<Object> searchMovieByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(tmdbSrvice.searchMovieByName(name));
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Movie " + name + " not found");
        }
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<Object> getVideoKey(@PathVariable int id) {
        try {

            return ResponseEntity.ok(tmdbSrvice.getMovieByIdForVideo(id));
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("key for" + id + " not found");
        }
    }


    @GetMapping("/video/key/{id}")
    public ResponseEntity<Object> getVideoKeyExtract(@PathVariable int id) {
        try {
            ResponseEntity<Object> response = ResponseEntity.ok(tmdbSrvice.getVideoKeys(id));
            HttpStatus statusCode = response.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                return response;
            } else if (statusCode == HttpStatus.NOT_FOUND) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Key for " + id + " not found");
            } else {
                // Handle other status codes if needed
                return ResponseEntity.status(statusCode).body(response.getBody());
            }
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Key for " + id + " not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




        //  String videoKey = (String) tmdbSrvice.getMovieByIdForVideo(id);
        //        return ResponseEntity.ok(videoKey);










   @PostMapping("/postMovie/{id}")
   public ResponseEntity<Object> postMovieById(@RequestBody Object movie,@PathVariable int id) {
       try {
           return ResponseEntity.ok(tmdbSrvice.postMovieById(id));
       } catch (HttpClientErrorException.NotFound e) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("not updated"+id);

       }
   }


}
