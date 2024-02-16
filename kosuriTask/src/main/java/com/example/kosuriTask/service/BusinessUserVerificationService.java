package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.BusinessUserVerificationDto;
import com.example.kosuriTask.dto.ServiceCategoryDto;
import com.example.kosuriTask.entity.BusinessUserVerification;

import java.util.List;

public interface BusinessUserVerificationService {

    BusinessUserVerificationDto addUserVerification(BusinessUserVerificationDto businessUserVerificationDto, String financierId);

    List<BusinessUserVerificationDto> getUsersVeriftn(String financierId);

    BusinessUserVerificationDto updateUserVeriftn(BusinessUserVerificationDto businessUserVerificationDto,long BusinsVerifctnId);





}
