package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.FinanceDataDto;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.FinanceData;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.repository.FinanceDataRepo;
import com.example.kosuriTask.service.FinanceDataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class FinanceDataServiceImpl implements FinanceDataService {
    @Autowired
    private FinanceDataRepo financeDataRepo;
    @Autowired
    private BusinessDetailsRepo businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public FinanceDataDto addFinanceData(FinanceDataDto financeDataDto, String contactEmail) {
        BusinessDetails businessDetails= businessDetailsRepo.findByContactEmail(contactEmail).
                orElseThrow(()-> new NoSuchElementException("BusinessDetails not found for contact email:" + contactEmail));

            FinanceData financeData= modelMapper.map(financeDataDto, FinanceData.class);
            financeData.setBusinessDetails(businessDetails);
            financeData=financeDataRepo.save(financeData);
            businessDetails.getFinanceDataList().add(financeData);
            return modelMapper.map(financeData, FinanceDataDto.class);

    }

    @Override
    public List<FinanceDataDto> getFinanceDataByContactEmail(String contactEmail) {
        BusinessDetails details=businessDetailsRepo.findByContactEmail(contactEmail).
                orElseThrow(() -> new ExceptionHandling("BusinessDetails not found for contact email: " + contactEmail));

        List<FinanceData> financeData = financeDataRepo.findByBusinessDetails(details);
        if(financeData!=null){
            return financeData.stream().map(
                    financeData1 -> modelMapper.map(financeData1, FinanceDataDto.class)
            ).collect(Collectors.toList());
        }else{
            throw  new ExceptionHandling("FinanceData not found for contact email: " + contactEmail);
        }
    }

    @Override
    public FinanceDataDto updateFinanceDataByContEmail(FinanceDataDto financeDataDto, String contactEmail, long fincnId) {
        FinanceData financeData=financeDataRepo.findByBusinessDetailsContactEmailAndFincnId(contactEmail,fincnId);
        if(financeData!=null){
            financeData.setFinanceCategory(financeDataDto.getFinanceCategory());
            financeData.setFinanceSegment(financeDataDto.getFinanceSegment());
            financeData.setFinanceSubSegment(financeDataDto.getFinanceSubSegment());
            FinanceData financeData1=financeDataRepo.save(financeData);
            return modelMapper.map(financeData1, FinanceDataDto.class);
        }else{
            throw new ExceptionHandling("FinanceData not found for contact email: " + contactEmail);
        }
    }


}
