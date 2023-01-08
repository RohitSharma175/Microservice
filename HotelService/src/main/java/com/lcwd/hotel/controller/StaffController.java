package com.lcwd.hotel.controller;

import com.lcwd.hotel.entites.Hotel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {


    @GetMapping
    public ResponseEntity<List<String>> getSingleHotel()
    {
//        List.of("Rohit","Abhishek","Rahul","Pawan");
        return ResponseEntity.ok(List.of("Rohit","Abhishek","Rahul","Pawan"));
    }
}
