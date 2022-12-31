package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> getAllRating();

    List<Rating> getRating();


    // add delete  and update


    // get All by user
    List<Rating> getRatingByUserId(String userId);

    // get all by Hotel
    List<Rating> getRatingByHotelId(String hotelId);

}
