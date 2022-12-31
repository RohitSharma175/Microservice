package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User u) {
        // generate unique id
        String randomUserId = UUID.randomUUID().toString();
        u.setUserId(randomUserId);
        return userRepository.save(u);
    }

    @Override
    public List<User> getAllUser() {

        List<User> all = userRepository.findAll();

        List<User> collect = all.stream().map(obj -> {
//            Rating[] ratingsofUsers = restTemplate.getForObject("Http://localhost:8080/ratings/users/" + obj.getUserId(), Rating[].class);
            Rating[] ratingsofUsers = restTemplate.getForObject("Http://RATING-SERVICE/ratings/users/" + obj.getUserId(), Rating[].class);

            List<Rating> ratings = Arrays.stream(ratingsofUsers).toList();
            List<Rating> ratingList = ratings.stream().map(rating -> {
//            Http://HOTEL-SERVICE/hotels/a570ab24-a8d7-49c6-880d-8e4937677284
//                ResponseEntity<Hotel> hotels = restTemplate.getForEntity("Http://localhost:8081/hotels/" + rating.getHotelId(), Hotel.class);
//                ResponseEntity<Hotel> hotels = restTemplate.getForEntity("Http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//                Hotel hotel = hotels.getBody();

                Hotel hotel = hotelService.getHotel(rating.getHotelId());
//                logger.info(" {} ", hotels.getStatusCode());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());


            obj.setRatings(ratingList);
            return obj;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found on server !! " + userId));
        // fetch the rating of above user using Rating Service
        Rating[] ratingsofUsers = restTemplate.getForObject("Http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);

        logger.info("{} ",ratingsofUsers);
        List<Rating> ratings = Arrays.stream(ratingsofUsers).toList();


        List<Rating> ratingList = ratings.stream().map(rating ->{
//            Http://HOTEL-SERVICE/hotels/a570ab24-a8d7-49c6-880d-8e4937677284
//            ResponseEntity<Hotel> hotels = restTemplate.getForEntity("Http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = hotels.getBody();
//            logger.info(" {} ",hotels.getStatusCode());

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

         user.setRatings(ratingList);
        return user;
    }
}
