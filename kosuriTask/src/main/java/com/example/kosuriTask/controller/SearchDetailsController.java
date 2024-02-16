package com.example.kosuriTask.controller;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.serviceImpl.SearchDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/searchDetails")
public class SearchDetailsController {

    @Autowired
    private SearchDetailsServiceImpl searchDetailsService;

//    @GetMapping("/searchOne")
//    public List<BusinessDetails> search(
//            @RequestParam(name = "brand") String brand,
//            @RequestParam(name = "model") String model,
//            @RequestParam(name = "vehicleType") String vehicleType,
//            @RequestParam(name = "subCategory") String subCategory,
//            @RequestParam(name = "location") String location) {
//        return searchDetailsService.searchDetailsOne(brand, model, vehicleType, subCategory,location);
//    }

    @GetMapping("/searchOne")
    public ResponseEntity<List<BusinessDetails>> search(
            @RequestParam(name = "brand") String brand,
            @RequestParam(name = "model") String model,
            @RequestParam(name = "vehicleType") String vehicleType,
            @RequestParam(name = "subCategory") String subCategory,
            @RequestParam(name = "location") String location) {

        try {
            return new ResponseEntity<>(searchDetailsService.searchDetailsOne(brand, model, vehicleType, subCategory, location), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception and return an error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
