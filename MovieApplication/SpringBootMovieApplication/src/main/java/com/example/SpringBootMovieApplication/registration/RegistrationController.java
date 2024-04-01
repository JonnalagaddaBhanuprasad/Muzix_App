package com.example.SpringBootMovieApplication.registration;


import com.example.SpringBootMovieApplication.event.RegistrationCompleteEvent;
import com.example.SpringBootMovieApplication.registration.token.VerificationToken;
import com.example.SpringBootMovieApplication.registration.token.VerificationTokenRepository;
import com.example.SpringBootMovieApplication.user.User;
import com.example.SpringBootMovieApplication.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    public RegistrationController(UserService userService, ApplicationEventPublisher publisher, VerificationTokenRepository tokenRepository) {
        this.userService = userService;
        this.publisher = publisher;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success!  Please, check your email for to complete your registration";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token";
    }



    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
