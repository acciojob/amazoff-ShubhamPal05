package com.driver.Services.Implementation;
import java.util.List;

import com.driver.Models.DeliveryPartner;
import com.driver.Repositories.DeliveryPartnerRepo;
import com.driver.Repositories.OrdersRepo;
import com.driver.Services.DeliveryPartnerServicesInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerSevices implements DeliveryPartnerServicesInterface {

    @Autowired
    DeliveryPartnerRepo partnerRepo;

    @Autowired
    OrdersRepo ordersPepo;

    @Override
    public void addPartner(String partnerId) {
        
        partnerRepo.addEntry(partnerId);

    }

    @Override
    public DeliveryPartner getPartnerById(String partnerId) {

        return partnerRepo.getDeliveryPartnerById(partnerId);

    }

    @Override
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {

        return partnerRepo.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    @Override
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        
        return partnerRepo.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId){

        partnerRepo.deletePartnerById(partnerId);
    }

    @Override
    public void deleteOrderById(String partnerId) {
        
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<String> getOrderbyPartnerId(String partnerId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
