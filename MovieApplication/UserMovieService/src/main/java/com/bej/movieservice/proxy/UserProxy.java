package com.bej.movieservice.proxy;

import com.bej.movieservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="UserAuthenticationService",url="localhost:8083")
public interface UserProxy {
    @PostMapping("/api/v1/saveUser")
    ResponseEntity<?>  saveUser(@RequestBody User user);


}
