package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.FinanceTenureDto;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.FinanceTenure;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.repository.FinanceTenureRepo;
import com.example.kosuriTask.service.FinanceTenureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceTenureServiceImpl implements FinanceTenureService {

    @Autowired
    private FinanceTenureRepo financeTenureRepo;
    @Autowired
    private BusinessDetailsRepo businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public FinanceTenureDto addFinanceTenure(FinanceTenureDto financeTenureDto, String contactEmail) {
        BusinessDetails businessDetails= businessDetailsRepo.findByContactEmail(contactEmail).get();
        if(businessDetails!=null){
            FinanceTenure financeTenure= modelMapper.map(financeTenureDto, FinanceTenure.class);
            financeTenure.setBusinessDetails(businessDetails);
            FinanceTenure financeTenure1=financeTenureRepo.save(financeTenure);
            return modelMapper.map(financeTenure1, FinanceTenureDto.class);
        }else{
         throw  new ExceptionHandling("BusinessDetails not found for contact email: " + contactEmail);
        }
    }

    @Override
    public List<FinanceTenureDto> getFinceTenureByContactEmail(String contactEmail) {
        BusinessDetails details=businessDetailsRepo.findByContactEmail(contactEmail).orElse(null);
        List<FinanceTenure> financeTenures= financeTenureRepo.findByBusinessDetails(details);
        if(financeTenures!=null){
            return financeTenures.stream().map(
                financeTenure -> modelMapper.map(financeTenure, FinanceTenureDto.class)
            ).collect(Collectors.toList());
        }else{
            throw  new ExceptionHandling("FinanceTenure not found for contact email: " + contactEmail);
        }

    }

    @Override
    public FinanceTenureDto updateByContEmail(FinanceTenureDto financeTenureDto, String contactEmail,long fincTenurId) {
        FinanceTenure financeTenure=financeTenureRepo.findByBusinessDetailsContactEmailAndFinceTenureId(contactEmail,fincTenurId);
        if(financeTenure!=null){
            financeTenure.setAssetCategory(financeTenureDto.getAssetCategory());
            financeTenure.setSubCategory(financeTenure.getSubCategory());
            financeTenure.setModel(financeTenure.getModel());
            financeTenure.setMinMonth(financeTenure.getMinMonth());
            financeTenure.setMaxMonth(financeTenure.getMaxMonth());
            FinanceTenure financeTenure1=financeTenureRepo.save(financeTenure);
            return modelMapper.map(financeTenure1, FinanceTenureDto.class);
        }else{
            throw new ExceptionHandling("FinanceTenure not found for contact email: " + contactEmail);
        }

    }




}
