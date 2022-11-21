package com.driver.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.driver.Models.Order;
import com.driver.Services.Implementation.DeliveryPartnerSevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersRepo {
    //String: id, int deliverytime

    @Autowired
    DeliveryPartnerRepo partnerRepo;

    @Autowired
    DeliveryPartnerSevices partnerSevices;

    private HashMap<String, Order> orders = new HashMap<>();

    private HashMap<Order, List<String>> ordersPartners = new HashMap<>();

    public HashMap<String, Order> getOrders() {
        return orders;
    }

    public void addOrderEntry(Order order){
        orders.put(order.getId(), order);
    }

    public void addOrdersPartners (String orderId, String partnerId){

        for(Order i: ordersPartners.keySet()){
            if(i.getId().equals(orderId)){

                List<String> list = ordersPartners.get(i);
                list.add(partnerId);

                ordersPartners.put(i,list);
                break;
            }
        }
    }

    public Order getOrderbyId(String orderId){

        for(String i: orders.keySet()){
            if(i.equals(orderId)){
                return orders.get(i);
            }
        }

        return null;
    }

    public List<String> getAllOrders(){
        List<String> list = new ArrayList<>();
        for(String i: orders.keySet()){
            list.add(i);
        }
        return list;
    } 

    public int getCountOfUnassignedOrders(){

        int count=0;
        for(Order i: orders.values()){
            if(!ordersPartners.containsKey(i)){
                count++;
            }
        }

        return count;
    }

    public void deleteOrderById(String orderId){
        for(String i: orders.keySet()){
            if(i.equals(orderId)){
                orders.remove(i);
                break;
            }
        }
        List<String> list=null;
        for(Order i: ordersPartners.keySet()){
            if(i.getId().equals(orderId)){
                list = ordersPartners.get(i);
                ordersPartners.remove(i);
                break;
            }
        }

        //deleting orders from delvierypartner repo
        if(list !=null)
        partnerRepo.deleteOrderById(list, orderId);

    }

}