package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.EmiPatternDto;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.EmiPattern;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.repository.EmiPatternRepo;
import com.example.kosuriTask.service.EmiPatternService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmiPatternServiceImpl implements EmiPatternService {

    @Autowired
    private EmiPatternRepo emiPatternRepo;
    @Autowired
    private BusinessDetailsRepo businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EmiPatternDto saveEmiPattern(EmiPatternDto emiPatternDto, String contactEmail) {
        BusinessDetails businessDetails=businessDetailsRepo.findByContactEmail(contactEmail).get();
        if(businessDetails!=null){
            EmiPattern emiPattern=modelMapper.map(emiPatternDto, EmiPattern.class);
            emiPattern.setBusinessDetails(businessDetails);
            EmiPattern emiPattern1=emiPatternRepo.save(emiPattern);
            return modelMapper.map(emiPattern1, EmiPatternDto.class);
        }else{
            throw new ExceptionHandling("Emi not found for contact email: " + contactEmail);
        }

    }

    @Override
    public List<EmiPatternDto> getEmiPatternById(String contactEmail) {
        BusinessDetails details=businessDetailsRepo.findByContactEmail(contactEmail).orElse(null);
        List<EmiPattern> emiPatterns=emiPatternRepo.findByBusinessDetails(details);
       if(emiPatterns!=null){
           return emiPatterns.stream().map(
                   emiPattern -> modelMapper.map(emiPattern, EmiPatternDto.class)
           ).collect(Collectors.toList());
       }else {
           throw new ExceptionHandling("Emi not found for contact email: " + contactEmail);
       }
    }

    @Override
    public EmiPatternDto updateEmiPattern(long emiId, EmiPatternDto updatedEmiPattern, String contactEmail) {
        EmiPattern existingEmiPattern = emiPatternRepo.findByBusinessDetailsContactEmailAndEmiId(contactEmail,emiId);
        if (existingEmiPattern != null) {
            existingEmiPattern.setAssetCategory(updatedEmiPattern.getAssetCategory());
            existingEmiPattern.setSubCategory(updatedEmiPattern.getSubCategory());
            existingEmiPattern.setModel(updatedEmiPattern.getModel());
            existingEmiPattern.setMinMonth(updatedEmiPattern.getMinMonth());
            existingEmiPattern.setMaxMonth(updatedEmiPattern.getMaxMonth());
            existingEmiPattern.setMinAmount(updatedEmiPattern.getMinAmount());
            existingEmiPattern.setMaxAmount(updatedEmiPattern.getMaxAmount());
            existingEmiPattern.setRateOfInterest(updatedEmiPattern.getRateOfInterest());
            existingEmiPattern.setCutOverPeriod(updatedEmiPattern.getCutOverPeriod());
            existingEmiPattern.setPercentageIncrease(updatedEmiPattern.getPercentageIncrease());
            EmiPattern pattern=emiPatternRepo.save(existingEmiPattern);
            return modelMapper.map(pattern, EmiPatternDto.class);
        }else{
            throw new ExceptionHandling("Emi not found for contact email: " + contactEmail);
        }

    }

}
