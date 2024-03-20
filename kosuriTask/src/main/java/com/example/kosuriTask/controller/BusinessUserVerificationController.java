package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.BusinessUserVerificationDto;
import com.example.kosuriTask.service.BusinessUserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('FI') and hasAuthority('SUADM')")
@RequestMapping("/api/businessVerification")
public class BusinessUserVerificationController {

    private final BusinessUserVerificationService businessUserVerificationService;
    @Autowired
    public BusinessUserVerificationController(BusinessUserVerificationService  businessUserVerificationService){
        this.businessUserVerificationService=businessUserVerificationService;
    }



    @PostMapping("/addUserVerification/{financierId}")
    public ResponseEntity<BusinessUserVerificationDto> addDetails(@RequestBody BusinessUserVerificationDto businessUserVerificationDto,
                                                                  @PathVariable String financierId){
        return new ResponseEntity<>(businessUserVerificationService.addUserVerification(businessUserVerificationDto,financierId),
                HttpStatus.CREATED);
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<List<BusinessUserVerificationDto>>  getDetails(@RequestParam(name="financierId") String financierId){
        return new ResponseEntity<>(businessUserVerificationService.getUsersVeriftn(financierId),HttpStatus.OK);
    }

    @PutMapping("/updateUserVerification/{businsVerifctnId}")
    public ResponseEntity<BusinessUserVerificationDto>  updateUserVerification(@RequestBody BusinessUserVerificationDto dto,
                                                                               @PathVariable long businsVerifctnId){
        return new ResponseEntity<>(businessUserVerificationService.updateUserVeriftn(dto,businsVerifctnId),HttpStatus.OK);
    }


}
