package com.lcwd.hotel.services;

import com.lcwd.hotel.entites.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel createHotel(Hotel hotel);

    public Hotel findSingleHotel(String hotelId);

    public List<Hotel> findAllHotels();




}
