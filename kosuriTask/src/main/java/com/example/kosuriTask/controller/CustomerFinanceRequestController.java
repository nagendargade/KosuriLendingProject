package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.CustomerFinanceRequestDto;
import com.example.kosuriTask.service.CustomerFinanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('FI') and hasAuthority('SUADM')")
@RequestMapping("/api/customerFinance")
public class CustomerFinanceRequestController {
    private final CustomerFinanceRequestService customerFinanceRequestService;

    @Autowired
    public CustomerFinanceRequestController (CustomerFinanceRequestService customerFinanceRequestService){
        this.customerFinanceRequestService=customerFinanceRequestService;
    }


    @PostMapping("/addCustmrFincReqstDetails/{financierId}")
    public ResponseEntity<CustomerFinanceRequestDto>  addDetails(@RequestBody CustomerFinanceRequestDto customerFinanceRequestDto,
                                                                 @PathVariable String financierId){
        return new ResponseEntity<>(customerFinanceRequestService.addCustmrFincrReqst(customerFinanceRequestDto,
                financierId), HttpStatus.CREATED);
    }

    @GetMapping("/getCustmrFincReqstDetails/{financierId}")
    public ResponseEntity<List<CustomerFinanceRequestDto>> getDetails(@PathVariable String financierId){

        return new ResponseEntity<>(customerFinanceRequestService.getCustmrFincrDetails(financierId),
                HttpStatus.OK);
    }

    @PutMapping("/updateCustmrFincReqstDetails/{financierId}/{cuUserId}")
    public ResponseEntity<CustomerFinanceRequestDto> updateCustmerFinanc(@RequestBody CustomerFinanceRequestDto customerFinanceRequestDto,
                                                                         @PathVariable String financierId,
                                                                         @PathVariable String cuUserId){

        return new ResponseEntity<>(customerFinanceRequestService.updateCustmrFincrDetails(customerFinanceRequestDto,
                financierId,cuUserId),HttpStatus.OK);
    }
}
