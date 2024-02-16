package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.VehicleDetailsDto;
import com.example.kosuriTask.dto.VehicleDetailsDto2;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.VehicleDetails;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.repository.VehicleDetailsRepo;
import com.example.kosuriTask.service.VehicleDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleDetailsServiceImpl implements VehicleDetailsService {

    @Autowired
    private VehicleDetailsRepo vehicleDetailsRepo;

    @Autowired
    private BusinessDetailsRepo businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VehicleDetailsDto saveVehicleDetails(VehicleDetailsDto vehicleDetailsDTO,String contactEmail) {
        BusinessDetails  businessDetails= businessDetailsRepo.findByContactEmail(contactEmail).get();
        if(businessDetails!=null){
            VehicleDetails vehicleDetails=modelMapper.map(vehicleDetailsDTO, VehicleDetails.class);
            vehicleDetails.setBusinessDetails(businessDetails);
            VehicleDetails vehicleDetails1= vehicleDetailsRepo.save(vehicleDetails);
            return modelMapper.map(vehicleDetails1, VehicleDetailsDto.class);
        }else {
            throw  new ExceptionHandling("VehicleDetails not found for contact email: " + contactEmail);
        }
    }

    @Override
    public List<VehicleDetailsDto> getVehicleDetails(String contactEmail){
        BusinessDetails details=businessDetailsRepo.findByContactEmail(contactEmail).orElse(null);
        List<VehicleDetails> vehicleDetails=vehicleDetailsRepo.findByBusinessDetails(details);
        if(vehicleDetails!=null){
            return vehicleDetails.stream().map(
                    vehicleDetails1 -> modelMapper.map(vehicleDetails1, VehicleDetailsDto.class)
            ).collect(Collectors.toList());
        }else{
            throw  new ExceptionHandling("vehicleDetails not found for contact email: " + contactEmail);
        }
    }

    @Override
    public List<VehicleDetailsDto2> getAllVehicleDetails() {
        List<VehicleDetails> vehicleDetails=vehicleDetailsRepo.findAll();
        return vehicleDetails.stream().map(
            vehicleDetails1 -> modelMapper.map(vehicleDetails1, VehicleDetailsDto2.class)
        ).collect(Collectors.toList());
    }

    @Override
    public VehicleDetailsDto updateVehicleDetails(VehicleDetailsDto vehicleDetailsDto, String contactEmail, String vehicleId) {
        VehicleDetails vehicleDetails= vehicleDetailsRepo.findByBusinessDetailsContactEmailAndVehicleId(contactEmail, vehicleId);
        if(vehicleDetails!=null){
            vehicleDetails.setVehicleCategory(vehicleDetailsDto.getVehicleCategory());
            vehicleDetails.setVehicleType(vehicleDetailsDto.getVehicleType());
            vehicleDetails.setSpecification(vehicleDetailsDto.getSpecification());
            vehicleDetails.setSubCategory(vehicleDetailsDto.getSubCategory());
            vehicleDetails.setStatus(vehicleDetailsDto.getStatus());
            vehicleDetails.setModel(vehicleDetailsDto.getModel());
            vehicleDetails.setBrand(vehicleDetailsDto.getBrand());
            VehicleDetails vehicleDetails1=vehicleDetailsRepo.save(vehicleDetails);
            return modelMapper.map(vehicleDetails1, VehicleDetailsDto.class);
        }else{
            throw new ExceptionHandling("vehicleDetails not found for contact email: " + contactEmail);
        }

    }







}
