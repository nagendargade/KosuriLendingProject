package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.AssetCategoryDto;
import com.example.kosuriTask.service.AssetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@PreAuthorize("hasAuthority('FI') and hasAuthority('SUADM')")
@RequestMapping("/api/assetCategory")
public class AssetCategoryController {

    private final AssetCategoryService assetCategoryService;

    @Autowired
    public AssetCategoryController(AssetCategoryService assetCategoryService){
        this.assetCategoryService=assetCategoryService;
    }



    @PostMapping("/addAsetCtgry/{contactEmail}")
    public ResponseEntity<AssetCategoryDto> addAssetCtrgry(@RequestBody AssetCategoryDto assetCategoryDto,
                                                             @PathVariable String contactEmail){
        return  new ResponseEntity<>(assetCategoryService.addAsetCtgry(assetCategoryDto, contactEmail), HttpStatus.CREATED);

    }

    @GetMapping("/getAsetCtgryDetails")
    public ResponseEntity<List<AssetCategoryDto>>   getAssetCtrgryBymail(@RequestParam(name="contactEmail") String contactEmail){

        return  new ResponseEntity<>(assetCategoryService.getAsetCtgryByContactEmail(contactEmail),HttpStatus.OK);
    }

    @PutMapping("/updateAsetCtgry/{contactEmail}/{id}")
    public ResponseEntity<AssetCategoryDto>  updateAssetCtrgry(@RequestBody AssetCategoryDto assetCategoryDto,
                                                               @PathVariable  String contactEmail,
                                                               @PathVariable long id){
        return new ResponseEntity<>(assetCategoryService.updateAsetCtgryByContEmail(assetCategoryDto, contactEmail,id),HttpStatus.OK);
    }





}
