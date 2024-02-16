package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.BusinessDetailsDto;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.service.BusinessDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessDetailsServiceImpl implements BusinessDetailsService {
    @Autowired
    BusinessDetailsRepo businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BusinessDetailsDto businessData(BusinessDetailsDto businessDetailsDto) {
        BusinessDetails businessDetails = modelMapper.map(businessDetailsDto, BusinessDetails.class);
        if (businessDetailsRepo.existsByContactEmail(businessDetails.getContactEmail())) {
            throw new ExceptionHandling("Email already exists: " + businessDetails.getContactEmail());
        }
        BusinessDetails savedBusinessDetails = businessDetailsRepo.save(businessDetails);
        return modelMapper.map(savedBusinessDetails, BusinessDetailsDto.class);
    }

    @Override
    public BusinessDetailsDto getDetails(String contactEmail,String location) {
        BusinessDetails details = businessDetailsRepo.findByContactEmailAndLocation(contactEmail,location).orElse(null);
        if (details != null) {
            return modelMapper.map(details, BusinessDetailsDto.class);
        } else {
            throw new ExceptionHandling("Details not found for contact email: " + contactEmail);
        }
    }

    @Override
    public BusinessDetailsDto updateBusnDetails(BusinessDetailsDto details, String contactEmail) {

        BusinessDetails existingDetails = businessDetailsRepo.findByContactEmail(contactEmail).orElse(null);
        if (existingDetails != null) {
            existingDetails.setName(details.getName());
            existingDetails.setBusinessName(details.getBusinessName());
            existingDetails.setNbfcApproved(details.getNbfcApproved());
            existingDetails.setPhoneNumber(details.getPhoneNumber());
            existingDetails.setGstRegistration(details.getGstRegistration());
            existingDetails.setNbfcRegistrationNumber(details.getNbfcRegistrationNumber());
            BusinessDetails updatedDetails = businessDetailsRepo.save(existingDetails);
            return modelMapper.map(updatedDetails, BusinessDetailsDto.class);
        } else {
            throw new ExceptionHandling("Details not found for contact email: " + contactEmail);
        }
    }


}
