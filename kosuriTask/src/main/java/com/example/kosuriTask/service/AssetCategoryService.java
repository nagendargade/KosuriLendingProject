package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.AssetCategoryDto;

import java.util.List;

public interface AssetCategoryService {
    AssetCategoryDto addAsetCtgry(AssetCategoryDto assetCategoryDto, String contactEmail);
    List<AssetCategoryDto> getAsetCtgryByContactEmail(String contactEmail);
    AssetCategoryDto updateAsetCtgryByContEmail(AssetCategoryDto assetCategoryDto,String contactEmail,long assetCatgryId);

}
