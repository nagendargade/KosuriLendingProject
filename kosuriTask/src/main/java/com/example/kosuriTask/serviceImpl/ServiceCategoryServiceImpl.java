package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kosuriTask.dto.ServiceCategoryDto;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.ServiceCategory;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.repository.ServiceCategoryRepo;
import com.example.kosuriTask.service.ServiceCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    private ServiceCategoryRepo  serviceCategoryRepo;
    @Autowired
    private BusinessDetailsRepo businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceCategoryDto addServiceCategory(ServiceCategoryDto serviceCategoryDto, String contactEmail) {
        BusinessDetails businessDetails= businessDetailsRepo.findByContactEmail(contactEmail).get();
        if(businessDetails!=null){
            ServiceCategory serviceCategory= modelMapper.map(serviceCategoryDto, ServiceCategory.class);
            serviceCategory.setBusinessDetails(businessDetails);
            ServiceCategory serviceCategory1=serviceCategoryRepo.save(serviceCategory);
            return modelMapper.map(serviceCategory1, ServiceCategoryDto.class);
        }else{
            throw  new ExceptionHandling("ServiceCategory not found for contact email: " + contactEmail);
        }
    }

    @Override
    public List<ServiceCategoryDto> getServiceCatgryByContactEmail(String contactEmail) {
        BusinessDetails details=businessDetailsRepo.findByContactEmail(contactEmail).orElse(null);
        List<ServiceCategory> serviceCategories=serviceCategoryRepo.findByBusinessDetails(details);
        if(serviceCategories!=null){
            return serviceCategories.stream().map(
                    serviceCategory -> modelMapper.map(serviceCategories, ServiceCategoryDto.class)
            ).collect(Collectors.toList());
        }else{
            throw  new ExceptionHandling("ServiceCategory not found for contact email: " + contactEmail);
        }
    }

    @Override
    public ServiceCategoryDto updateServiceByContEmail(ServiceCategoryDto serviceCategoryDto, String contactEmail, long servCategryId) {
        ServiceCategory serviceCategory=serviceCategoryRepo.findByBusinessDetailsContactEmailAndServcCatrgyId(contactEmail,servCategryId);
        if(serviceCategory!=null){
            serviceCategory.setServiceCategory(serviceCategoryDto.getServiceCategory());
            serviceCategory.setServiceName(serviceCategoryDto.getServiceName());
            serviceCategory.setServiceSubCategory(serviceCategoryDto.getServiceSubCategory());
            ServiceCategory serviceCategory1=serviceCategoryRepo.save(serviceCategory);
            return modelMapper.map(serviceCategory1, ServiceCategoryDto.class);
        }else{
            throw new ExceptionHandling("ServiceCategory not found for contact email: " + contactEmail);
        }
    }
}
