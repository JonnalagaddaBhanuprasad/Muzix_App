package com.bej.movieservice.repository;

import com.bej.movieservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserMovieRepository extends MongoRepository<User,String> {
//     User findByUserId(String userId);
User findByUserId(String userId);

   // Optional<User> findByName(String fileName);
}
