package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.ServiceCategoryDto;

import java.util.List;

public interface ServiceCategoryService {
    ServiceCategoryDto addServiceCategory(ServiceCategoryDto serviceCategoryDto, String contactEmail);
    List<ServiceCategoryDto> getServiceCatgryByContactEmail(String contactEmail);

    ServiceCategoryDto updateServiceByContEmail(ServiceCategoryDto serviceCategoryDto,String contactEmail,long ServCategryId);

}
