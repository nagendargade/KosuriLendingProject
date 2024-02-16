package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.EmiPattern;
import com.example.kosuriTask.entity.VehicleDetails;
import com.example.kosuriTask.repository.VehicleDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchDetailsServiceImpl {

    @Autowired
    private VehicleDetailsRepo vehicleDetailsRepo;

    public List<BusinessDetails> searchDetailsOne(String brand, String model, String vehicleType, String subCategory, String location) {
        List<VehicleDetails> vehicleDetailsList = vehicleDetailsRepo.findByBrandAndModelAndVehicleTypeAndSubCategoryAndBusinessDetailsLocation(brand, model, vehicleType, subCategory,location);
        return getBusinessDetailsAndEmiPattern(vehicleDetailsList);
    }

    private List<BusinessDetails> getBusinessDetailsAndEmiPattern(List<VehicleDetails> vehicleDetailsList) {
        List<BusinessDetails> result = new ArrayList<>();

        for (VehicleDetails vehicleDetails : vehicleDetailsList) {
            BusinessDetails businessDetails = new BusinessDetails();
            businessDetails.setUserId(vehicleDetails.getBusinessDetails().getUserId());
            businessDetails.setName(vehicleDetails.getBusinessDetails().getName());
            businessDetails.setContactEmail(vehicleDetails.getBusinessDetails().getContactEmail());
            businessDetails.setFinancierId(vehicleDetails.getBusinessDetails().getFinancierId());
            businessDetails.setPhoneNumber(vehicleDetails.getBusinessDetails().getPhoneNumber());
            businessDetails.setBusinessName(vehicleDetails.getBusinessDetails().getBusinessName());
            businessDetails.setNbfcApproved(vehicleDetails.getBusinessDetails().getNbfcApproved());
            businessDetails.setNbfcRegistrationNumber(vehicleDetails.getBusinessDetails().getNbfcRegistrationNumber());
            businessDetails.setRegistrationDate(vehicleDetails.getBusinessDetails().getRegistrationDate());
            businessDetails.setGstRegistration(vehicleDetails.getBusinessDetails().getGstRegistration());
            businessDetails.setUpdatedDetails(vehicleDetails.getBusinessDetails().getUpdatedDetails());
            businessDetails.setLocation(vehicleDetails.getBusinessDetails().getLocation());

            List<EmiPattern> emiPattern = vehicleDetails.getBusinessDetails().getEmiPattern();
            if (emiPattern != null) {
                // Include EmiPattern details in BusinessDetails
                businessDetails.setEmiPattern(emiPattern);
            }

            result.add(businessDetails);
        }

        return result;
    }


}
