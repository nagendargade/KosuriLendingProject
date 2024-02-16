package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.FinanceTenureDto;
import com.example.kosuriTask.service.FinanceTenureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/financeTenure")
public class FinanceTenureController {

    @Autowired
    private FinanceTenureService financeTenureService;



    @PostMapping("/addFinanceTenure/{contactEmail}")
    public ResponseEntity<FinanceTenureDto> addTenureDetails(@RequestBody FinanceTenureDto financeLimits,
                                                             @PathVariable String contactEmail){
        return  new ResponseEntity<>(financeTenureService.addFinanceTenure(financeLimits,contactEmail), HttpStatus.CREATED);

    }

    @GetMapping("/getTenureDetails/{contactEmail}")
    public ResponseEntity<List<FinanceTenureDto>>   getFinceTenureBymail(@PathVariable String contactEmail){

        return  new ResponseEntity<>(financeTenureService.getFinceTenureByContactEmail(contactEmail),HttpStatus.OK);
    }

    @PutMapping("/updateFincTenure/{contactEmail}/{id}")
    public ResponseEntity<FinanceTenureDto>  updateFinTenure(@RequestBody FinanceTenureDto financeLimitsDto,
                                                            @PathVariable  String contactEmail,
                                                             @PathVariable long id){
        return new ResponseEntity<>(financeTenureService.updateByContEmail(financeLimitsDto, contactEmail,id),HttpStatus.OK);
    }



}
