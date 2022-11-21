package com.driver.Models;

import com.driver.Converters.TimeConverter;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id =id;
        this.deliveryTime = TimeConverter.StringToInt(deliveryTime);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}