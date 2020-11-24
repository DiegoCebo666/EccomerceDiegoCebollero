package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;
import java.util.Arrays;

import com.eccomerce.diegocebollero.eccomerce.Model.Order;
import com.eccomerce.diegocebollero.eccomerce.Model.OrderProduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
    private static ArrayList<Order> orders = new ArrayList<>(Arrays.asList(new Order("01/01/2020", "PIPO", 2),
            new Order("02/01/2020", "PIPO", 2), new Order("03/01/2020", "PIPO", 2)));

    private static ArrayList<OrderProduct> orderProducts = new ArrayList<>(
            Arrays.asList(new OrderProduct(1, 1, 5), new OrderProduct(2, 4, 7281), new OrderProduct(3, 2, 39)));

    @GetMapping("orders")
    public ArrayList<Order> getOrders() {
        return orders;
    }

    @GetMapping("orders/{id}")
    public ArrayList<OrderProduct> getOrderProductsById(@PathVariable(name = "id", required = true) int id) {
        ArrayList<OrderProduct> result = new ArrayList<>();
        for (int i = 0; i < orderProducts.size(); i++) {
            if (orderProducts.get(i).getIdorder() == id)
                result.add(orderProducts.get(i));
        }
        return result;
    }
}
