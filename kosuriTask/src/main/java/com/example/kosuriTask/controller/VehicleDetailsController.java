package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.VehicleDetailsDto;
import com.example.kosuriTask.dto.VehicleDetailsDto2;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.service.VehicleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('FI') and hasAuthority('SUADM')")
@RequestMapping("/api/vehicleDetails")
public class VehicleDetailsController {
    @Autowired
    private VehicleDetailsService vehicleDetailsService;

    @PostMapping("/addVehicleDetails/{contactEmail}")
    public ResponseEntity<VehicleDetailsDto> saveVehicleDetails(@RequestBody VehicleDetailsDto vehicleDetailsDto,
                                                               @PathVariable String contactEmail) {
        return new ResponseEntity<>(vehicleDetailsService.saveVehicleDetails(vehicleDetailsDto,contactEmail), HttpStatus.CREATED);
    }

    @GetMapping("/getVehicleDetail/{contactEmail}")
    public ResponseEntity<List<VehicleDetailsDto>> getVehicleDetails(@PathVariable String contactEmail) {
        return new ResponseEntity<>(vehicleDetailsService.getVehicleDetails(contactEmail),HttpStatus.OK);
    }

    @GetMapping("/getAllVehicleDetails")
    public ResponseEntity<List<VehicleDetailsDto2>> getAllVehleDetls(){
        return new ResponseEntity<>(vehicleDetailsService.getAllVehicleDetails(),HttpStatus.OK);
    }

    @PutMapping("/updateVehicleDetail/{contactEmail}/{vehicleId}")
    public ResponseEntity<VehicleDetailsDto> updateVehicleDetail(@RequestBody VehicleDetailsDto vehicleDetailsDto,
                                                                 @PathVariable String contactEmail,
                                                                 @PathVariable String vehicleId){
        return new ResponseEntity<>(vehicleDetailsService.updateVehicleDetails(vehicleDetailsDto,contactEmail,vehicleId),HttpStatus.OK);
    }







}





