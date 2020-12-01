package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eccomerce.diegocebollero.eccomerce.Model.Order;
import com.eccomerce.diegocebollero.eccomerce.Model.OrderProduct;

import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ArrayList<OrderProduct> getOrderProductsById(@PathVariable("id") int id) {
        ArrayList<OrderProduct> result = new ArrayList<>();
        NotFoundControl(id);
        for (int i = 0; i < orderProducts.size(); i++) {
            if (orderProducts.get(i).getIdorder() == id){
                result.add(orderProducts.get(i));
            }
        }
        return result;
    }

    @PostMapping("order")
    public int postOrder(@RequestParam(name="products", required = false) ArrayList<OrderProduct> products,
                    @RequestParam(name="username", required = true, defaultValue = "") String username){
        Order order = new Order(username, 1);
        int orderId = order.getId();
        orders.add(order);
        for (int i = 0; i < orderProducts.size(); i++) {
            products.get(i).setId(orderId);
            orderProducts.add(products.get(i));
        }
        return orderId;
    }

    @DeleteMapping("order/{id}")
    public int deleteOrder(@PathVariable int id){
        NotFoundControl(id);
        Order order = findById(id);
        orders.remove(order);
        for (int i = 0; i < orderProducts.size(); i++) {
            if(orderProducts.get(i).getIdorder() == id) orderProducts.remove(i);
        }
        return 1;
    }

    public Order findById(int id){
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getId() == id) return orders.get(i);
        }
        return null;
    }

    public void NotFoundControl(int id){
        if(findById(id) == null){
            throw new ElementNotFoundException();
        }
    }

    public void NotFoundControl2(int id){
        if(findById(id) == null){
            throw new ImATeapotException();
        }
    }
}