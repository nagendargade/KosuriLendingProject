package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.BusinessUserVerificationDto;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.BusinessUserVerification;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.repository.BusinessUserVerificationRepo;
import com.example.kosuriTask.service.BusinessUserVerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessUserVerificationServiceImpl implements BusinessUserVerificationService {
    @Autowired
    private BusinessUserVerificationRepo businessUserVerificationRepo;
    @Autowired
    private BusinessDetailsRepo businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BusinessUserVerificationDto addUserVerification(BusinessUserVerificationDto businessUserVerificationDto, String financierId) {
        BusinessDetails businessDetails=businessDetailsRepo.findByFinancierId(financierId).get();
        if(businessDetails!=null){
            BusinessUserVerification businessUserVerification=modelMapper.map(businessUserVerificationDto, BusinessUserVerification.class);
            businessUserVerification.setBusinessDetails(businessDetails);
            BusinessUserVerification businessUserVerification1=businessUserVerificationRepo.save(businessUserVerification);
            return modelMapper.map(businessUserVerification1, BusinessUserVerificationDto.class);
        }else{
            throw  new ExceptionHandling("businessUserVerification not found for financierId: " + financierId);
        }

    }

    @Override
    public List<BusinessUserVerificationDto> getUsersVeriftn(String financierId) {
        BusinessDetails details=businessDetailsRepo.findByFinancierId(financierId).orElse(null);
        List<BusinessUserVerification> businessUserVerifications= businessUserVerificationRepo.findByBusinessDetails(details);
        if(businessUserVerifications!=null){
           return businessUserVerifications.stream().map(
                    businessUserVerification -> modelMapper.map(businessUserVerification, BusinessUserVerificationDto.class)
            ).toList();
        }else {
            throw  new ExceptionHandling("businessUserVerification not found for financierId: " + financierId);
        }
    }

    @Override
    public BusinessUserVerificationDto updateUserVeriftn(BusinessUserVerificationDto businessUserVerificationDto, long businsVerifctnId) {
       BusinessUserVerification  businessUserVerification=businessUserVerificationRepo.findByBusinsVerifctnId(businsVerifctnId);
        if(businessUserVerification!=null){
            businessUserVerification.setVerification(businessUserVerificationDto.getVerification());
            businessUserVerification.setDocumentVerification(businessUserVerificationDto.getDocumentVerification());
            businessUserVerification.setOfficeVerification(businessUserVerificationDto.getOfficeVerification());
            businessUserVerification.setUpdatedBy(businessUserVerification.getUpdatedBy());
            BusinessUserVerification businessUserVerification1=businessUserVerificationRepo.save(businessUserVerification);
            return modelMapper.map(businessUserVerification1,BusinessUserVerificationDto.class);
        }else{
            throw  new ExceptionHandling("businessUserVerification not found");
        }
    }


}
