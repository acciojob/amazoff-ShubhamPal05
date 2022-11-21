package com.driver.Services.Implementation;

import java.util.List;

import com.driver.Models.Order;
import com.driver.Repositories.DeliveryPartnerRepo;
import com.driver.Repositories.OrdersRepo;
import com.driver.Services.OrderServicesInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServices implements OrderServicesInterface{


    @Autowired
    OrdersRepo ordersPepo;

    @Autowired
    DeliveryPartnerRepo partnerRepo;

    @Override
    public void addOrder(Order order) {
        
        ordersPepo.addOrderEntry(order);
        
    }

    @Override
    public void addOrderPartnerPair(String orderId, String partnerId) {
        ordersPepo.addOrdersPartners(orderId, partnerId);
        partnerRepo.addpartnersOrders(orderId, partnerId);
    }

    @Override
    public Order getOrderbyId(String orderId) {

        Order order = ordersPepo.getOrderbyId(orderId);
        return order;
        
    }

    @Override
    public int getOrderContByParentId(String partnerId) {

        return partnerRepo.getOrderContByParentId(partnerId);

    }

    @Override
    public List<String> getAllOrders() {


        return ordersPepo.getAllOrders();
    }

    @Override
    public int getCountOfUnassignedOrders() {

        return ordersPepo.getCountOfUnassignedOrders();

    }

    public void deleteOrderById(String orderId){
        ordersPepo.deleteOrderById(orderId);
    }
    
}
