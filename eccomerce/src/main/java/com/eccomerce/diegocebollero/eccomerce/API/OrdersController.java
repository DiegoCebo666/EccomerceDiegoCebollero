package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;
import java.util.Arrays;

import com.eccomerce.diegocebollero.eccomerce.Model.Support.EditDeleteOrders;
import com.eccomerce.diegocebollero.eccomerce.Model.Order;
import com.eccomerce.diegocebollero.eccomerce.Model.OrderProduct;
import com.eccomerce.diegocebollero.eccomerce.Model.Support.ProductQuantity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH })
@RestController
@RequestMapping("/orders")
public class OrdersController {
    public static ArrayList<Order> orders = new ArrayList<>(Arrays.asList(new Order("PIPO", 2),
            new Order("PIPO", 2), new Order("PIPO", 2)));

    public static ArrayList<OrderProduct> orderProducts = new ArrayList<>(
            Arrays.asList(new OrderProduct(1, new ArrayList<>(Arrays.asList(new ProductQuantity(1, 78), new ProductQuantity(3, 231)))), new OrderProduct(1, new ArrayList<>(Arrays.asList(new ProductQuantity(1, 78), new ProductQuantity(3, 231)))), new OrderProduct(2, new ArrayList<>(Arrays.asList(new ProductQuantity(1, 78), new ProductQuantity(3, 231)))), new OrderProduct(3, new ArrayList<>(Arrays.asList(new ProductQuantity(1, 78), new ProductQuantity(3, 231))))));

    @GetMapping
    public ArrayList<Order> getOrders() {
        return orders;
    }

    @GetMapping("/{id}")
    public OrderProduct getOrderProductsById(@PathVariable("id") int id) {
        OrderProduct result = findOrderProductById(id);
        return result;
    }

    @PostMapping
    public int postOrder(@RequestBody OrderProduct products,
                    @RequestParam(name="username", required = true, defaultValue = "") String username){
        Order order = new Order(username, 1);
        int orderId = order.getId();
        orders.add(order);
        products.setIdorder(orderId);
        orderProducts.add(products);
        return orderId;
    }

    @PutMapping("/{id}")
    public OrderProduct putOrder(@PathVariable("id") int id, @RequestBody EditDeleteOrders products){
        OrderProduct original = findOrderProductById(id);
        OrderProduct editProducts = products.getEdit();
        OrderProduct deleteProducts = products.getDelete();
        ArrayList<ProductQuantity> productsOriginal = original.getProductCantidad();
        ArrayList<ProductQuantity> productsEdit = editProducts.getProductCantidad();
        ArrayList<ProductQuantity> productsDelete = deleteProducts.getProductCantidad();
        ArrayList<Integer> eliminados = new ArrayList<>();
        if(productsDelete.isEmpty() && productsEdit.isEmpty()) return null;
        for (int i = 0; i < productsOriginal.size(); i++) {
            if(!productsDelete.isEmpty()){
                for (int j = 0; j < productsDelete.size(); j++) {
                    if(productsOriginal.get(i).getIdproduct() == productsDelete.get(j).getIdproduct()){
                        eliminados.add(i);
                    }
                }
            }
            if (!productsEdit.isEmpty()) {
                for (int j = 0; j < productsEdit.size(); j++) {
                    if(productsOriginal.get(i).getIdproduct() == productsEdit.get(j).getIdproduct()){
                        productsOriginal.get(i).setCantidad(productsEdit.get(j).getCantidad());
                    }
                }
            }
        }
        if(!eliminados.isEmpty()){
            for (int i = eliminados.size() - 1; i >= 0; i--) {
                productsOriginal.remove(eliminados.get(i).intValue());
            }
        }
        orderProducts.get(original.getId()).setProductCantidad(productsOriginal);
        return orderProducts.get(original.getId());
    }

    @DeleteMapping("/{id}")
    public int deleteOrder(@PathVariable int id){
        NotFoundControl(id);
        Order order = findById(id);
        orders.remove(order);
        for (int i = 0; i < orderProducts.size(); i++) {
            if(orderProducts.get(i).getIdorder() == id) orderProducts.remove(i);
        }
        return 1;
    }

    public static Order findById(int id){
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getId() == id) return orders.get(i);
        }
        throw new ElementNotFoundException();
    }

    public static OrderProduct findOrderProductById(int id){
        for (int i = 0; i < orderProducts.size(); i++) {
            if(orders.get(i).getId() == id) return orderProducts.get(i);
        }
        throw new ElementNotFoundException();
    }

    public void NotFoundControl(int id){
        if(findById(id) == null){
            throw new ElementNotFoundException();
        }
    }

    public void NotFoundControlOrderProduct(int id){
        if(findOrderProductById(id) == null){
            throw new ImATeapotException();
        }
    }
}