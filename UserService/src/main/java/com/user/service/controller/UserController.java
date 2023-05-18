package com.user.service.controller;

import com.user.service.entities.User;
import com.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);



    //create
    //http://localhost:8081/users/create
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //http://localhost:8081/users/{userId}
    // Single user get


    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Get single User Handler: UserController");


        User user = userService.getUser(userId);
        return  ResponseEntity.ok(user);
    }


    //Creating fall back method for circuitBreaker



    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//        logger.info("Fallback is executed because service is down: " + ex.getMessage());


        User user = User.builder()
                .name("Dummy")
                .email("dummy@gmail.com")
                .about("This user is created dummy because some service is down")
                .userId("1233344")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);


    }

    //get all user
    //http://localhost:8081/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return  ResponseEntity.ok(allUser);
    }


}
