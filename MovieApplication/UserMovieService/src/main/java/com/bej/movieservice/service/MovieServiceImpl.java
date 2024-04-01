package com.bej.movieservice.service;

import com.bej.movieservice.domain.Movie;
import com.bej.movieservice.domain.User;

import com.bej.movieservice.exception.MovieAlreadyExistsException;
import com.bej.movieservice.exception.MovieNotFoundException;
import com.bej.movieservice.exception.UserAlreadyExistsException;
import com.bej.movieservice.exception.UserNotFoundException;
import com.bej.movieservice.proxy.UserProxy;
import com.bej.movieservice.repository.UserMovieRepository;
//import com.bej.movieservice.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class MovieServiceImpl implements IMovieService {

    private UserMovieRepository userMovieRepository;
    private UserProxy userProxy;
    // Autowire the UserTrackRepository using constructor autowiring
    @Autowired
    public MovieServiceImpl(UserMovieRepository userMovieRepository, UserProxy userProxy) {
        this.userMovieRepository = userMovieRepository;
        this.userProxy = userProxy;
    }

    private final String uploadDir = "C:\\Users\\BHANU PRASAD\\OneDrive\\Desktop\\myimages"; // Directory to store uploaded files


    @Transactional
    @Override
    public User registerUser(String email, String password, MultipartFile image) throws UserAlreadyExistsException, IOException {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        // Handle image upload
        if (image != null && !image.isEmpty()) {
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileExtension;
            Path uploadPath = Paths.get(uploadDir);

            // Create the upload directory if it doesn't exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file to the server
            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(image.getInputStream(), filePath);

            // Set the image path in the user entity
            user.setImagePath(filePath.toString());
        }

//         Save the user details to the database
        User savedUser = userMovieRepository.save(user);
        if(!savedUser.getUserId().isEmpty())
        {
            ResponseEntity<?> r = userProxy.saveUser(user);
            System.out.println(r.getBody());
        }
        return savedUser;
//        return userMovieRepository.save(user);
    }

    @Override
    public byte[] getImage(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        return Files.readAllBytes(filePath);
    }



//    @Override
//    public User registerUser(User user) throws UserAlreadyExistsException {
//        // Register a new user
////        if(user.getUserId().isEmpty()) {
//            String userId = UUID.randomUUID().toString();
//            user.setUserId(userId);
//
//        if(userMovieRepository.findById(user.getUserId()).isPresent()){
//            throw new UserAlreadyExistsException();
//        }
//        System.out.println(user);
//        User savedUser = userMovieRepository.save(user);
//        if(!savedUser.getUserId().isEmpty())
//        {
//            ResponseEntity<?> r = userProxy.saveUser(user);
//            System.out.println(r.getBody());
//        }
//        return savedUser;
//    }




//    public String uploadImage(MultipartFile file) throws IOException {
//
//        User imageData = userMovieRepository.save(User.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtil.compressImage(file.getBytes())).build());
//        if (imageData!=null) {
//            return "your registration is successfully completed : "+file.getOriginalFilename();
//        }
//        return null;
//    }
//    public byte[] downloadImage(String fileName) {
//        Optional<User> dbImageData = userMovieRepository.findByName(fileName);
//        byte[] images = ImageUtil.decompressImage(dbImageData.get().getImageData());
//        return images;
//    }



    @Override
    public User saveMovieToWishList(Movie movie,String userId) throws UserNotFoundException, MovieAlreadyExistsException {
        // Save the tracks to the play list of user.
//        System.out.println(userId);
//        if (userMovieRepository.findById(userId).isEmpty()) {
//            throw new UserNotFoundException();
//        }
//        //System.out.println("54");
//
//       User user = userMovieRepository.findById(userId).get();
//        List<Movie> movieList = user.getMovieList();
//        for (Movie t : movieList) {
//            if (Objects.equals(t.getMovieId(), movie.getMovieId())) {
//                throw new MovieAlreadyExistsException();
//            }
//        }
//        System.out.println("62");
//            if (user.getMovieList() == null) {
//                user.setMovieList(Arrays.asList(movie));
//            } else {
//               // List<Movie> movieList1 = user.getMovieList();
//                movieList.add(movie);
//                user.setMovieList(movieList);
//            }
        System.out.println("service data :"+movie);
        System.out.println("service data :"+userId);
//        System.out.println(userMovieRepository.findById(userId).get());
        if(userMovieRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userMovieRepository.findById(userId).get();
        if(user.getMovieList()==null){
            System.out.println("add to list");
            user.setMovieList(Arrays.asList(movie));
        }
        else {
            List<Movie> trackList = user.getMovieList();
            trackList.add(movie);
            user.setMovieList(trackList);
        }
            return userMovieRepository.save(user);
        }
    @Override
    public List<Movie> getAllUserMoviesFromWishList(String userId) throws MovieNotFoundException,UserNotFoundException {
        // Get all the tracks for a specific user
        if(userMovieRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userMovieRepository.findById(userId).get();
        List<Movie> movieList = user.getMovieList();
        if(movieList.isEmpty())
        {
            throw new MovieNotFoundException();
        }
        return userMovieRepository.findById(userId).get().getMovieList();
    }

    @Override
    public User deleteMovie(String userId, String movieId) throws MovieNotFoundException,UserNotFoundException {
        if(userMovieRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userMovieRepository.findById(userId).get();
        List<Movie> movieList = user.getMovieList();
        boolean isMoviePresent = movieList.removeIf(movie -> movie.getMovieId().equals(movieId));
        if(!isMoviePresent){
            throw new MovieNotFoundException();
        }
        // delete the user details specified
        user.setMovieList(movieList);
        return userMovieRepository.save(user);
    }

    }


//    @Override
//    public User updateUserMovieWishListWithGivenList(String userId, Movie movie) throws MovieAlreadyExistsException {
//        // Update the specific details of User
//  if(userMovieRepository.findById(userId).isEmpty())
//  {
//      throw new UserNotFoundException();
//  }
//        User user = userMovieRepository.findById(userId).get();
//        List<Movie> movieList = user.getMovieList();
//
//        Movie existingMovie = null;
//        for (Movie t: movieList){
//            if(Objects.equals(t.getMovieId(),movie.getMovieId())){
//                existingMovie = t;
//            }
//            if(existingMovie == null){
//                throw new TrackNotFoundException();
//            }
//            if(existingMovie.equals(track)){
//                throw new TrackAlreadyExistsException();
//            }
//        }
//
//        movieList.remove(existingMovie);
//        movieList.add(movie);
//        return userMovieRepository.save(user);
//    }

