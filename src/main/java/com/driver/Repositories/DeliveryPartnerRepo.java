package com.driver.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.driver.Converters.TimeConverter;
import com.driver.Models.DeliveryPartner;
import com.driver.Models.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryPartnerRepo {

    @Autowired
    OrdersRepo ordersPepo;

    private HashMap<String, DeliveryPartner> partners = new HashMap<>();

    private HashMap<DeliveryPartner, List<String>> partnersOrders = new HashMap<>();

    public void addEntry( String partnerId ){

        DeliveryPartner currPartner = new DeliveryPartner(partnerId);

        partners.put(currPartner.getId(), currPartner);
    
    }

    public void addpartnersOrders(String orderId, String partnerId){

        for(DeliveryPartner i: partnersOrders.keySet()){
            if(i.getId().equals(partnerId)){
                List<String> list = partnersOrders.get(i);
                list.add(orderId);
                partnersOrders.put(i, list);
                break;
            }
        }
    }

    public DeliveryPartner getDeliveryPartnerById(String partnerId){
        for(String i: partners.keySet()){
            if(i.equals(partnerId)){
                return partners.get(i);
            }
        }
        return null;
    }

    public int getOrderContByParentId(String parentId){
        for(DeliveryPartner i: partnersOrders.keySet()){
            if(i.getId().equals(parentId)){
                return partnersOrders.get(i).size();
            }
        }
        return 0;
    }
    
    public List<String> getOrderbyPartnerId(String partnerId){

        for(DeliveryPartner i: partnersOrders.keySet()){
            if(i.getId().equals(partnerId)){
                return partnersOrders.get(i);
            }
        }
        return null;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){

        List<String> list = new ArrayList<>();
        for(DeliveryPartner i: partnersOrders.keySet()){
            if(i.getId().equals(partnerId)){
                list = partnersOrders.get(i);
                break;
            }
        }
        int count = 0;
        int requiredTime = TimeConverter.StringToInt(time);
        HashMap<String, Order> order = ordersPepo.getOrders();
        for(String i : list){
            int deliveryTime = order.get(i).getDeliveryTime();
            if( deliveryTime > requiredTime){
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<String> list = new ArrayList<>();

        for(DeliveryPartner i: partnersOrders.keySet()){
            if(i.getId().equals(partnerId)){
                list = partnersOrders.get(i);
            }
        }
        String latestTime = "";
        for(String i: list){
            if(latestTime.compareTo(i) < 0){
                latestTime = i;
            }
        }
        return latestTime;
    }

    public void deletePartnerById(String partnerId){
        for(String i: partners.keySet()){
            if(i.equals(partnerId)){
                partners.remove(i);
                break;
            }
        }

        for(DeliveryPartner i: partnersOrders.keySet()){
            if(i.getId().equals(partnerId)){
                partnersOrders.remove(i);
            }
        }
    }

    //called from orders repo
    public void deleteOrderById(List<String> list, String orderId){
        for(String i: list){
            DeliveryPartner curr = partners.get(i);
            List<String> currlist = partnersOrders.get(curr);
            currlist.remove(orderId);
            partnersOrders.put(curr,currlist);
        }
    }
}