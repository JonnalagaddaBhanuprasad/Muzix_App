package com.springbootapp.callingExternalApi.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootapp.callingExternalApi.Domain.Video;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.ErrorManager;

@Service
@Getter
@Setter
public class TmdbServiceImpl implements ITmdbService{


    private RestTemplate restTemplate;

    public TmdbServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String tmdbApiKey="086460204e528151b857887f6bb1a409";
    private String tmdbApiUrl="https://api.themoviedb.org/3/discover/movie";
    @Override
    public Object getTmdbdata() {
        String apiUrl=buildApiUrl();
        return restTemplate.getForObject(apiUrl, Object.class);
    }

    @Override
    public Object getMovieById(int id) {
        String apiUrl = "https://api.themoviedb.org/3/movie" + "/" + id + "?api_key=" + tmdbApiKey;
        try {
            return restTemplate.getForObject(apiUrl, Object.class);
        } catch (HttpClientErrorException e) {
            // Log the exception details
          //  System.out.println("Error calling TMDb API: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Object getTopRated() {
       String apiUrl=  "https://api.themoviedb.org/3/movie/top_rated?api_key=086460204e528151b857887f6bb1a409";
        return restTemplate.getForObject(apiUrl, Object.class);
    }

    @Override
    public Object getUpcoming() {
        String apiUrl="https://api.themoviedb.org/3/movie/upcoming?api_key=086460204e528151b857887f6bb1a409";
        return restTemplate.getForObject(apiUrl,Object.class);
    }

    @Override
    public Object getTv() {
        String apiUrl="https://api.themoviedb.org/3/discover/tv?api_key=086460204e528151b857887f6bb1a409";
        return restTemplate.getForObject(apiUrl,Object.class);
    }


    @Override
    public Object searchMovieByName(String name) {
       // String apiUrl = "https://api.themoviedb.org/3/search/movie" + "/" + id + "?api_key=" + tmdbApiKey;

         String apiUrl = "https://api.themoviedb.org/3/search/movie" + "?query=" + name + "&api_key=" + tmdbApiKey;
        try {
            Object result = restTemplate.getForObject(apiUrl, Object.class);

            // Check if the result is empty (movie not found)
            if (result == null || result.toString().isEmpty()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Movie not found");
            }

            return result;
        } catch (HttpClientErrorException e) {
            // Log the exception details

            System.out.println("Error calling TMDb API");

            // Rethrow the exception for other HTTP errors
            throw e;
        }
    }

    @Override
    public Object getVideoKeys(int id) throws IOException {
        String apiUrl = "https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=086460204e528151b857887f6bb1a409";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        return extractKeys(response.getBody());
    }


    public  static List<String> extractKeys(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        List<String> keys = new ArrayList<>();
        JsonNode resultsNode = jsonNode.get("results");

        if (resultsNode != null && resultsNode.isArray()) {
            for (JsonNode resultNode : resultsNode) {
                JsonNode keyNode = resultNode.get("key");
                if (keyNode != null) {
                    keys.add(keyNode.asText());
                }
            }
        }

        return keys;
    }

    @Override
    public Object getMovieByIdForVideo(int id) {
         // Define your TMDB API URL
            String apiUrl = "https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=086460204e528151b857887f6bb1a409";
          //  RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, Object.class);

    }



//@Override
//    public String getVideoKey(int movieId) {
//        String apiUrl = "https://api.themoviedb.org/3/movie/" + movieId +
//                "?api_key=" + tmdbApiKey + "&append_to_response=videos";
//
//        try {
//            ResponseEntity<Object> responseEntity = restTemplate.getForEntity(apiUrl, Object.class);
//
//            // Extract the video key from the response entity
//            String videoKey = extractVideoKey(responseEntity);
//
//            return videoKey;
//        } catch (Exception e) {
//            // Handle exceptions appropriately (e.g., log or throw)
//            throw new RuntimeException("Error calling TMDb API", e);
//        }
//    }
//
//    private String extractVideoKey(Object responseBody) {
//        //List<Map<String, String>> videos = (List<Map<String, String>>) ((Map<String, Object>) responseBody).get("videos");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(responseBody));
//
//            // Assuming 'videos' is a key in the JSON response
//            JsonNode videosNode = jsonNode.path("videos");
//
//            // Assuming video key is under 'results' array
//            JsonNode resultsNode = videosNode.path("results");
//
//            // Assuming you want the first video key
//            if (resultsNode.isArray() && resultsNode.size() > 0) {
//                return resultsNode.get(0).path("key").asText();
//            } else {
//                throw new RuntimeException("No video key found in the movie details");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error extracting video key from movie details", e);
//        }
//
//    }



    @Override
    public Object postMovieById(int id) {
        String apiUrl = "https://api.themoviedb.org/3/list" + "/" + id + "/add_item?api_key=" + tmdbApiKey;
        try {
            return restTemplate.getForObject(apiUrl, Object.class);
        } catch (HttpClientErrorException e) {
            // Log the exception details
            //  System.out.println("Error calling TMDb API: {}", e.getMessage());
            throw e;
        }
    }


//https://api.themoviedb.org/3/movie/297762/videos?api_key=086460204e528151b857887f6bb1a409
//    @Override
//    public Object getMovieByIdForVideo(int id) {
//        String apiUrl = "https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=" + tmdbApiKey ;
//
//        try {
//            Object videoObject = restTemplate.getForObject(apiUrl, Object.class);
//            System.out.println(videoObject);
//            return extractVideoKey(videoObject);
//        } catch (HttpClientErrorException e) {
//            // Log the exception details
//            throw e;
//        }
//    }





//    private String extractVideoKey(Object movieDetails) {
//
//       String video_key=movieDetails.toString();
//       return video_key;
//    }






    private String buildApiUrl(){
        return tmdbApiUrl+"?api_key="+tmdbApiKey;
    }
}
