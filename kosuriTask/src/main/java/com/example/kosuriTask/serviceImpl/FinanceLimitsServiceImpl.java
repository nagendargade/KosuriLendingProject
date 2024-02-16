package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.FinanceLimitsDto;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.FinanceLimits;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.repository.FinanceLimitsRepo;
import com.example.kosuriTask.service.BusinessDetailsService;
import com.example.kosuriTask.service.FinanceLimitsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceLimitsServiceImpl implements FinanceLimitsService {

    @Autowired
    private FinanceLimitsRepo financeLimitsRepo;
    @Autowired
    private BusinessDetailsService businessDetailsService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BusinessDetailsRepo businessDetailsRepo;
    @Override
    public FinanceLimitsDto addFinanceLimits(FinanceLimitsDto financeLimitsDto, String contactEmail) {
        BusinessDetails details=businessDetailsRepo.findByContactEmail(contactEmail).get();
        if (details!=null){
            FinanceLimits financeLimits=modelMapper.map(financeLimitsDto, FinanceLimits.class);
            financeLimits.setBusinessDetails(details);
            FinanceLimits financeLimits1=financeLimitsRepo.save(financeLimits);
            return modelMapper.map(financeLimits1, FinanceLimitsDto.class);
        }else{
            throw new ExceptionHandling("FinanceLimits not found for contact email: " + contactEmail);
        }

    }

    @Override
    public List<FinanceLimitsDto> getByContactEmail(String contactEmail) {
        BusinessDetails businessDetails=businessDetailsRepo.findByContactEmail(contactEmail).orElse(null);
            List<FinanceLimits> financeLimits = financeLimitsRepo.findByBusinessDetails(businessDetails);

            if (financeLimits != null && !financeLimits.isEmpty()) {
                return financeLimits.stream()
                        .map(financeLimit -> modelMapper.map(financeLimit, FinanceLimitsDto.class))
                        .collect(Collectors.toList());
        } else {
            throw new ExceptionHandling("financeLimits not found for contact email: " + contactEmail);
        }
    }


    @Override
    public FinanceLimitsDto updateByContactEmail(FinanceLimitsDto updatedFinanceLimits, String contactEmail,long finLimtId) {
        FinanceLimits existingFinanceLimits = financeLimitsRepo.findByBusinessDetailsContactEmailAndFinceLimitId(contactEmail,finLimtId).orElse(null);
        if (existingFinanceLimits != null) {
            existingFinanceLimits.setAssetCategory(updatedFinanceLimits.getAssetCategory());
            existingFinanceLimits.setSubCategory(updatedFinanceLimits.getSubCategory());
            existingFinanceLimits.setModel(updatedFinanceLimits.getModel());
            existingFinanceLimits.setMinLending(updatedFinanceLimits.getMinLending());
            existingFinanceLimits.setMaxLending(updatedFinanceLimits.getMaxLending());
            FinanceLimits financeLimits = financeLimitsRepo.save(existingFinanceLimits);
            return modelMapper.map(financeLimits, FinanceLimitsDto.class);
        } else {
            throw new ExceptionHandling("FinanceLimits not found for contact email: " + contactEmail);
        }
    }

}
