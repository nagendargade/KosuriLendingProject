package com.example.kosuriTask.controller;


import com.example.kosuriTask.dto.BusinessDetailsDto;
import com.example.kosuriTask.service.BusinessDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;


@RestController
@RequestMapping("/api/businessDetails")
public class BusinessDetailsController {
    private final BusinessDetailsService  businessDetailsService;

    @Autowired
    public BusinessDetailsController(BusinessDetailsService businessDetailsService) {
        this. businessDetailsService =  businessDetailsService;
    }



    @PostMapping("/AddBusiness")
    public ResponseEntity<BusinessDetailsDto> saveBusinessData(@RequestBody BusinessDetailsDto businessDetailsDto) {
        try {
            BusinessDetailsDto savedDetails = businessDetailsService.businessData(businessDetailsDto);
            return new ResponseEntity<>(savedDetails, HttpStatus.CREATED);
        } catch (ExceptionHandling e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/getdetails")
    public  ResponseEntity<BusinessDetailsDto> getById(@RequestParam(name="contactEmail") String contactEmail,
                                                       @RequestParam(name="location") String location){
       try{
           return new ResponseEntity<>(businessDetailsService.getDetails(contactEmail,location),HttpStatus.OK);
       }catch (ExceptionHandling e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }

    @PutMapping("/update-details")
    public ResponseEntity<BusinessDetailsDto> updateBusinessDetails(@RequestBody BusinessDetailsDto businessDetailsDto,
                                                                    @RequestParam(name="contactEmail") String contactEmail) {
        try {
            BusinessDetailsDto updatedDetails = businessDetailsService.updateBusnDetails(businessDetailsDto, contactEmail);
            return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
        } catch (ExceptionHandling e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            // Handle the case where businessDetailsDto is null
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
