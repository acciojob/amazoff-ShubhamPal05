package com.driver.Controllers;

import java.util.List;

import com.driver.Models.DeliveryPartner;
import com.driver.Models.Order;
import com.driver.Services.Implementation.DeliveryPartnerSevices;
import com.driver.Services.Implementation.OrdersServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    DeliveryPartnerSevices partnerServices;

    @Autowired
    OrdersServices orderServices;

    //done
    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestBody Order order){

        orderServices.addOrder(order);
        return new ResponseEntity<>("New order added successfully", HttpStatus.CREATED);
    }

    //done
    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addPartner(@PathVariable String partnerId){

        partnerServices.addPartner(partnerId);

        return new ResponseEntity<>("New delivery partner added successfully", HttpStatus.CREATED);
    }

    //done
    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId, @RequestParam String partnerId){


        orderServices.addOrderPartnerPair(orderId, partnerId);
        //This is basically assigning that order to that partnerId

        return new ResponseEntity<>("New order-partner pair added successfully", HttpStatus.CREATED);
    }

    //done
    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId){

        Order order = orderServices.getOrderbyId(orderId);
        //order should be returned with an orderId.

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    //done
    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId){

        DeliveryPartner dp = partnerServices.getPartnerById(partnerId);
        //deliveryPartner should contain the value given by partnerId

        return new ResponseEntity<>(dp, HttpStatus.CREATED);
    }

    //done
    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId){

        Integer orderCount = orderServices.getOrderContByParentId(partnerId);

        //orderCount should denote the orders given by a partner-id

        return new ResponseEntity<>(orderCount, HttpStatus.CREATED);
    }

    //done
    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<String>> getOrdersByPartnerId(@PathVariable String partnerId){

        
        List<String> orders = partnerServices.getOrderbyPartnerId(partnerId);
        //orders should contain a list of orders by PartnerId

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    //done
    @GetMapping("/get-all-orders")
    public ResponseEntity<List<String>> getAllOrders(){

        List<String> orders = orderServices.getAllOrders();

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    //done
    @GetMapping("/get-count-of-unassigned-orders")
    public ResponseEntity<Integer> getCountOfUnassignedOrders(){
        Integer countOfOrders = orderServices.getCountOfUnassignedOrders();
        //Count of orders that have not been assigned to any DeliveryPartner
        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }

    //done
    @GetMapping("/get-count-of-orders-left-after-given-time/{partnerId}")
    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId){

        Integer countOfOrders = partnerServices.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);

        //countOfOrders that are left after a particular time of a DeliveryPartner

        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }
    
    //done
    @GetMapping("/get-last-delivery-time/{partnerId}")
    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId){
        String time = partnerServices.getLastDeliveryTimeByPartnerId(partnerId);

        //Return the time when that partnerId will deliver his last delivery order.
        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }

    //done
    @DeleteMapping("/delete-partner-by-id/{partnerId}")
    public ResponseEntity<String> deletePartnerById(@PathVariable String partnerId){

        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.
        partnerServices.deletePartnerById(partnerId);

        return new ResponseEntity<>(partnerId + " removed successfully", HttpStatus.CREATED);
    }

    //working
    @DeleteMapping("/delete-order-by-id/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId){

        //Delete an order and also
        // remove it from the assigned order of that partnerId
        orderServices.deleteOrderById(orderId);

        return new ResponseEntity<>(orderId + " removed successfully", HttpStatus.CREATED);
    }
}
