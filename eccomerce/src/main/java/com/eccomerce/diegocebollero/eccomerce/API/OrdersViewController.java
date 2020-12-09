package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;

import com.eccomerce.diegocebollero.eccomerce.Model.OrderProduct;
import com.eccomerce.diegocebollero.eccomerce.Model.Product;
import com.eccomerce.diegocebollero.eccomerce.Model.Support.ProductQuantity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH })
@Controller
public class OrdersViewController {

    @GetMapping("products/{id}")
    public ModelAndView getProductsInOrder(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("productsIn", productsIn(id));
        mv.addObject("productsOut", productsOut(id));
        return mv;
    }

    public ArrayList<Product> productsIn(int id){
        ArrayList<Product> result = new ArrayList<>();
        OrderProduct products = OrdersController.findOrderProductById(id);
        if (products.getProductCantidad().isEmpty()){
            return result;
        }else{
            for (int i = 0; i < products.getProductCantidad().size(); i++) {
                for (int j = 0; j < ProductsController.products.size(); j++) {
                    if(products.getProductCantidad().get(i).getIdproduct() == ProductsController.products.get(j).getId()) 
                        result.add(ProductsController.findProdById(ProductsController.products.get(j).getId()));
                }
            }
            return result;
        }
    }

    public ArrayList<Product> productsOut(int id){
        OrderProduct products = OrdersController.findOrderProductById(id);
        ArrayList<Product> result = new ArrayList<>();
        if (products.getProductCantidad().isEmpty()){
            return result;
        }else{
            boolean in = true;
            for (int j = 0; j < ProductsController.products.size(); j++) {
                for (int i = 0; i < products.getProductCantidad().size(); i++) {
                    if(products.getProductCantidad().get(i).getIdproduct() != ProductsController.products.get(j).getId()){
                        in = false;
                    }else{
                        in = true;
                        break;
                    }
                }
                if (!in) {
                    result.add(ProductsController.findProdById(ProductsController.products.get(j).getId()));
                }
            }
            return result;
        }
    }

    @GetMapping("productsIn/{id}")
    public ModelAndView postProductsInOrder(@PathVariable("id") int id, @RequestParam(name = "idProd", required = true, defaultValue = "0") int idProd, 
                                            @RequestParam(name = "cantidad", required = true, defaultValue = "0") int cantidad) {
        OrdersController.findOrderProductById(id).getProductCantidad().add(new ProductQuantity(idProd, cantidad));
        return getProductsInOrder(id);
    }

    @GetMapping("productsOut/{id}")
    public ModelAndView postProductsOutOrder(@PathVariable("id") int id, @RequestParam(name = "idProd", required = true, defaultValue = "0") int idProd) {
        OrdersController.findOrderProductById(id).getProductCantidad().remove(findProdCantByProdId(id, idProd));
        return getProductsInOrder(id);
    }

    public ProductQuantity findProdCantByProdId(int idOrder ,int idProd){
        ArrayList<ProductQuantity> list = OrdersController.findOrderProductById(idOrder).getProductCantidad();
        ProductQuantity result = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdproduct() == idProd) {
                result = list.get(i);
            }
        }
        if (result == null) {
            throw new ElementNotFoundException();
        }
        return result;
    }
}
