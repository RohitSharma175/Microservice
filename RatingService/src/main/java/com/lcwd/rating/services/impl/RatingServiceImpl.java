package com.lcwd.rating.services.impl;

import com.lcwd.rating.Repositories.RatingRepository;
import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
//import com.lcwd.user.service.entities.User;
//import com.lcwd.user.service.exceptions.ResourceNotFoundException;
//import com.lcwd.user.service.repositories.UserRepository;
//import com.lcwd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) {
        // generate unique id
        String randomUserId = UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRating() {
//        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given Id is not found on server !! "+userId));
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

}
