package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.ServiceCategoryDto;
import com.example.kosuriTask.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@PreAuthorize("hasAuthority('FI') and hasAuthority('SUADM')")
@RequestMapping("/api/serviceCategory")
public class ServiceCategoryController {

    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @PostMapping("/addSrvicCtgry/{contactEmail}")
    public ResponseEntity<ServiceCategoryDto> addTenureDetails(@RequestBody ServiceCategoryDto serviceCategoryDto,
                                                               @PathVariable String contactEmail){
        return  new ResponseEntity<>(serviceCategoryService.addServiceCategory(serviceCategoryDto, contactEmail), HttpStatus.CREATED);

    }

    @GetMapping("/getSrvicCtgryeDetails/{contactEmail}")
    public ResponseEntity<List<ServiceCategoryDto>>   getFinceTenureBymail(@PathVariable String contactEmail){

        return  new ResponseEntity<>(serviceCategoryService.getServiceCatgryByContactEmail(contactEmail),HttpStatus.OK);
    }

    @PutMapping("/updateSrvicCtgry/{contactEmail}/{id}")
    public ResponseEntity<ServiceCategoryDto>  updateFinTenure(@RequestBody ServiceCategoryDto serviceCategoryDto,
                                                             @PathVariable  String contactEmail,
                                                             @PathVariable long id){
        return new ResponseEntity<>(serviceCategoryService.updateServiceByContEmail(serviceCategoryDto, contactEmail,id),HttpStatus.OK);
    }


}
