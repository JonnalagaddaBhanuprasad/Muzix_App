package com.example.SpringBootMovieApplication.registration;


public record RegistrationRequest(
         String firstName,
         String lastName,
         String email,
         String password,
         String role) {
}
}
