package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;
import java.util.Arrays;

import com.eccomerce.diegocebollero.eccomerce.Model.Order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
    private static ArrayList<Order> orders = new ArrayList<>(Arrays.asList(
        new Order( "01/01/2020", "PIPO", 2),
        new Order( "01/01/2020", "PIPO", 2),
        new Order( "01/01/2020", "PIPO", 2)
    ));

    @GetMapping("orders")
    public ArrayList<Order> getOrders() {
        return orders;
    }
}
