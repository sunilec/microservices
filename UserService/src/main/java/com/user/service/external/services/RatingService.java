package com.user.service.external.services;

import com.netflix.servo.util.Objects;
import com.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    //get

    //Post
    @PostMapping("/ratings/")
    public Rating createRating(Rating values);

    //Put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId")String ratingId, Rating rating);

    //Delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable("ratingId")String ratingId);
}
