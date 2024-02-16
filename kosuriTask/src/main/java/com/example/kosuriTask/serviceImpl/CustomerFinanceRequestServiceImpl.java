package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.CustomerFinanceRequestDto;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.CustomerFinanceRequest;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.repository.CustomerFinanceRequestRepo;
import com.example.kosuriTask.service.CustomerFinanceRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerFinanceRequestServiceImpl implements CustomerFinanceRequestService {

    @Autowired
    private CustomerFinanceRequestRepo customerFinanceRequestRepo;
    @Autowired
    private BusinessDetailsRepo  businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CustomerFinanceRequestDto addCustmrFincrReqst(CustomerFinanceRequestDto customerFinanceRequestDto, String financierId) {
        BusinessDetails businessDetails=businessDetailsRepo.findByFinancierId(financierId).get();

        if(businessDetails!=null){
            CustomerFinanceRequest customerFinanceRequest=modelMapper.map(customerFinanceRequestDto, CustomerFinanceRequest.class);
            customerFinanceRequest.setBusinessDetails(businessDetails);
            CustomerFinanceRequest customerFinanceRequest1=customerFinanceRequestRepo.save(customerFinanceRequest);
            return modelMapper.map(customerFinanceRequest1, CustomerFinanceRequestDto.class);
        }else{
                throw  new ExceptionHandling("CustomerFinance not found for the financierId: " + financierId);
        }
    }

    @Override
    public List<CustomerFinanceRequestDto> getCustmrFincrDetails(String financierId) {
        BusinessDetails details=businessDetailsRepo.findByFinancierId(financierId).
                orElseThrow(()-> new ExceptionHandling("BusinessDetails not found for financierId: " + financierId));
        List<CustomerFinanceRequest> customerFinanceRequests=customerFinanceRequestRepo.findByBusinessDetails(details);
        if(customerFinanceRequests!=null){
            return customerFinanceRequests.stream().map(
                    customerFinanceRequest -> modelMapper.map(customerFinanceRequest, CustomerFinanceRequestDto.class)
            ).collect(Collectors.toList());
        }else{
            throw new ExceptionHandling("CustomerFinance not found for the financierId: " + financierId);
        }
    }

    @Override
    public CustomerFinanceRequestDto updateCustmrFincrDetails(CustomerFinanceRequestDto customerFinanceRequestDto, String financierId, String cuUserId) {
        CustomerFinanceRequest customerFinanceRequest=customerFinanceRequestRepo.findByBusinessDetailsFinancierIdAndCuUserId(financierId,cuUserId);
        if(customerFinanceRequest!=null){
            modelMapper.map(customerFinanceRequestDto, customerFinanceRequest);
            CustomerFinanceRequest updatedCustomerFinanceRequest = customerFinanceRequestRepo.save(customerFinanceRequest);
            return modelMapper.map(updatedCustomerFinanceRequest, CustomerFinanceRequestDto.class);
        }else{
            throw new ExceptionHandling("CustomerFinance not found for the financierId");
        }


    }


}
