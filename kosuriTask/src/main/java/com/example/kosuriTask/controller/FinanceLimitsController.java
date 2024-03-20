package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.FinanceLimitsDto;
import com.example.kosuriTask.service.FinanceLimitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('FI') and hasAuthority('SUADM')")
@RequestMapping("/api/financeLimits")
public class FinanceLimitsController {
    @Autowired
    private FinanceLimitsService financeLimitsService;

    @PostMapping("/addFinanceLimits/{contactEmail}")
    public ResponseEntity<FinanceLimitsDto> addLimitsDetails(@RequestBody FinanceLimitsDto financeLimits,
                                                             @PathVariable String contactEmail){
        return  new ResponseEntity<>(financeLimitsService.addFinanceLimits(financeLimits,contactEmail), HttpStatus.CREATED);

    }

    @GetMapping("/getDetails/{contactEmail}")
    public ResponseEntity<List<FinanceLimitsDto>>   getFinLimtBymail(@PathVariable String contactEmail){

        return  new ResponseEntity<>(financeLimitsService.getByContactEmail(contactEmail),HttpStatus.OK);
    }

    @PutMapping("/updateFincLimit/{contactEmail}/{finLimtId}")
    public ResponseEntity<FinanceLimitsDto>  updateFinLimit(@RequestBody FinanceLimitsDto financeLimitsDto,
                                                            @PathVariable  String contactEmail,
                                                            @PathVariable long finLimtId){
        return new ResponseEntity<>(financeLimitsService.updateByContactEmail(financeLimitsDto, contactEmail, finLimtId),HttpStatus.OK);
    }
}
