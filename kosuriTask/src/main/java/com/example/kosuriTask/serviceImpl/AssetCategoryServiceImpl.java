package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.AssetCategoryDto;
import com.example.kosuriTask.entity.AssetCategory;
import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.AssetCategoryRepo;
import com.example.kosuriTask.repository.BusinessDetailsRepo;
import com.example.kosuriTask.service.AssetCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {
    @Autowired
    private AssetCategoryRepo assetCategoryRepo;
    @Autowired
    private BusinessDetailsRepo businessDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssetCategoryDto addAsetCtgry(AssetCategoryDto assetCategoryDto, String contactEmail) {
        BusinessDetails businessDetails= businessDetailsRepo.findByContactEmail(contactEmail).get();
        if(businessDetails!=null){
            AssetCategory assetCategory= modelMapper.map(assetCategoryDto, AssetCategory.class);
            assetCategory.setBusinessDetails(businessDetails);
            AssetCategory financeTenure1=assetCategoryRepo.save(assetCategory);
            return modelMapper.map(financeTenure1, AssetCategoryDto.class);
        }else{
            throw  new ExceptionHandling("AssetCategory not found for contact email: " + contactEmail);
        }

    }

    @Override
    public List<AssetCategoryDto> getAsetCtgryByContactEmail(String contactEmail) {
        BusinessDetails details=businessDetailsRepo.findByContactEmail(contactEmail).orElse(null);
        List<AssetCategory> assetCategory= assetCategoryRepo.findByBusinessDetails(details);
        if(assetCategory!=null){
            return assetCategory.stream().map(
                    assetCategory1 -> modelMapper.map(assetCategory1, AssetCategoryDto.class)
            ).collect(Collectors.toList());
        }else{
            throw  new ExceptionHandling("ServiceCategory not found for contact email: " + contactEmail);
        }
    }

    @Override
    public AssetCategoryDto updateAsetCtgryByContEmail(AssetCategoryDto assetCategoryDto, String contactEmail, long assetCatgryId) {
        AssetCategory assetCategory=assetCategoryRepo.findByBusinessDetailsContactEmailAndAstCatgryId(contactEmail,assetCatgryId);
        if(assetCategory!=null){
            assetCategory.setAssetCategory(assetCategoryDto.getAssetCategory());
            assetCategory.setAssetSubCategory(assetCategoryDto.getAssetSubCategory());
            assetCategory.setAssetModel(assetCategoryDto.getAssetModel());
            assetCategory.setBrand(assetCategoryDto.getBrand());
            AssetCategory assetCategory1=assetCategoryRepo.save(assetCategory);
            return modelMapper.map(assetCategory1, AssetCategoryDto.class);
        }else{
            throw new ExceptionHandling("ServiceCategory not found for contact email: " + contactEmail);
        }
    }

}
