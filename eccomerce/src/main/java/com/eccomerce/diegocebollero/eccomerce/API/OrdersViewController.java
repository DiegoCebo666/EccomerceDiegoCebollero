package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;

import com.eccomerce.diegocebollero.eccomerce.Model.OrderProduct;
import com.eccomerce.diegocebollero.eccomerce.Model.Product;
import com.eccomerce.diegocebollero.eccomerce.Model.Support.ProductQuantity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersViewController {

    @GetMapping("productsIn/{id}")
    public ModelAndView getProductsInOrder(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("index");
        ArrayList<Product> result = new ArrayList<>();
        OrderProduct products = OrdersController.findOrderProductById(id);
        for (int i = 0; i < products.getProductCantidad().size(); i++) {
            for (int j = 0; j < ProductsController.products.size(); j++) {
                if(products.getProductCantidad().get(i).getIdproduct() == ProductsController.products.get(j).getId()) 
                    result.add(ProductsController.findProdById(ProductsController.products.get(j).getId()));
            }
        }
        mv.addObject("products", result);
        return mv;
    }

    @GetMapping("productsOut/{id}")
    public ModelAndView getProductsOutOrder(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("index");
        ArrayList<Product> result = new ArrayList<>();
        OrderProduct products = OrdersController.findOrderProductById(id);
        for (int j = 0; j < ProductsController.products.size(); j++) {
            for (int i = 0; i < products.getProductCantidad().size(); i++) {
                if(products.getProductCantidad().get(i).getIdproduct() != ProductsController.products.get(j).getId()){
                    result.add(ProductsController.findProdById(ProductsController.products.get(j).getId()));
                    break;
                }else{
                    break;
                }
            }
        }
        mv.addObject("products", result);
        return mv;
    }

    @PostMapping("productsIn/{id}")
    public void postProductsInOrder(@PathVariable("id") int id, @RequestParam(name = "idProd", required = true, defaultValue = "0") int idProd, 
                                            @RequestParam(name = "cantidad", required = true, defaultValue = "0") int cantidad) {
        OrdersController.findOrderProductById(id).getProductCantidad().add(new ProductQuantity(idProd, cantidad));
        ModelAndView mv = new ModelAndView("index");
        ArrayList<Product> result = new ArrayList<>();
        OrderProduct products = OrdersController.findOrderProductById(id);
        for (int i = 0; i < products.getProductCantidad().size(); i++) {
            for (int j = 0; j < ProductsController.products.size(); j++) {
                if(products.getProductCantidad().get(i).getIdproduct() == ProductsController.products.get(j).getId()) 
                    result.add(ProductsController.findProdById(ProductsController.products.get(j).getId()));
            }
        }
        mv.addObject("products", result);
        return mv;
    }
}
