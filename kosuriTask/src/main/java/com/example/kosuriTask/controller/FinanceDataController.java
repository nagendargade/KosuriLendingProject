package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.FinanceDataDto;
import com.example.kosuriTask.service.FinanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('FI') and hasAuthority('SUADM')")
@RequestMapping("/api/financeData")
public class FinanceDataController {

    @Autowired
    private FinanceDataService financeDataService;


    @PostMapping("/addFinanceData/{contactEmail}")
    public ResponseEntity<FinanceDataDto> addFinceDataDetails(@RequestBody FinanceDataDto financeData,
                                                             @PathVariable String contactEmail){
        return  new ResponseEntity<>(financeDataService.addFinanceData(financeData,contactEmail), HttpStatus.CREATED);

    }

    @GetMapping("/getFinanceData/{contactEmail}")
    public ResponseEntity<List<FinanceDataDto>>   getFinDataBymail(@PathVariable String contactEmail){

        return  new ResponseEntity<>(financeDataService.getFinanceDataByContactEmail(contactEmail),HttpStatus.OK);
    }


    @PutMapping("/updateFinancData/{contactEmail}/{fincnId}")
    public ResponseEntity<FinanceDataDto> updateFinanceData(@RequestBody FinanceDataDto financeDataDto,
                                                            @PathVariable String contactEmail,
                                                            @PathVariable long fincnId){
        return new ResponseEntity<>(financeDataService.updateFinanceDataByContEmail(financeDataDto,contactEmail,fincnId),
                HttpStatus.OK);
    }
}
