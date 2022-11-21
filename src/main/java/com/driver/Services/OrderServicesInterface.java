package com.driver.Services;

import java.util.List;
import com.driver.Models.Order;

public interface OrderServicesInterface {

    //1
    public void addOrder(Order order);

    //3
    public void addOrderPartnerPair(String orderId, String partnerId);

    //4
    public Order getOrderbyId(String orderId);

    //6
    public int getOrderContByParentId(String partnerId);

    //8
    public List<String> getAllOrders();

    //9
    public int getCountOfUnassignedOrders();

    public void deleteOrderById(String orderId);
}