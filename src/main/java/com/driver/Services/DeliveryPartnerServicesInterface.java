package com.driver.Services;
import java.util.List;

import com.driver.Models.DeliveryPartner;


public interface DeliveryPartnerServicesInterface {
    //2
    public void addPartner(String partnerId);

    //5
    public DeliveryPartner getPartnerById(String partnerId);

    //12
    public void deleteOrderById(String partnerId);

    public List<String> getOrderbyPartnerId(String partnerId);

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId);

    public String getLastDeliveryTimeByPartnerId(String partnerId);

    public void deletePartnerById(String partnerId);
}