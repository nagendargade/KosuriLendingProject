package com.example.kosuriTask.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AssetCategoryDto {
    private long astCatgryId;
    private String  assetCategory;
    private String assetSubCategory;
    private String assetModel;
    private String brand;
    private Date RegistrationData;

}
