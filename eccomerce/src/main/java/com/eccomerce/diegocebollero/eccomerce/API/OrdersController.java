package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;
import java.util.Arrays;

import com.eccomerce.diegocebollero.eccomerce.Model.Order;
import com.eccomerce.diegocebollero.eccomerce.Model.OrderProduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
    private static ArrayList<Order> orders = new ArrayList<>(Arrays.asList(new Order("PIPO", 2),
            new Order("PIPO", 2), new Order("PIPO", 2)));

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

    @PostMapping("order")
    public int postOrder(@RequestParam(name="products", required = false) ArrayList<OrderProduct> orderProducts,
                    @RequestParam(name="username", required = true, defaultValue = "") String username){
        Order order = new Order(username, 1);
        int orderId = order.getId();
        orders.add(order);
        for (int i = 0; i < orderProducts.size(); i++) {
            orderProducts.get(i).setId(orderId);
        }
        return orderId;
    }
}