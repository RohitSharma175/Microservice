package com.lcwd.hotel.services.impl;

import com.lcwd.hotel.entites.Hotel;
import com.lcwd.hotel.exception.ResourceNotFoundException;
import com.lcwd.hotel.repositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomUserId = UUID.randomUUID().toString();
        hotel.setHotelId(randomUserId);
        Hotel save = hotelRepository.save(hotel);
        return save;
    }

    @Override
    public Hotel findSingleHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel with given Id is not found on server !! "+hotelId));
    }

    @Override
    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
    }
}
