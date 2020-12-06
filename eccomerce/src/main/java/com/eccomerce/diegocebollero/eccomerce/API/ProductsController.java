package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;
import java.util.Arrays;

import com.eccomerce.diegocebollero.eccomerce.Model.Product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH })
@Controller
@RequestMapping("/apiazure")
public class ProductsController {
    public static ArrayList<Product> products = new ArrayList<>(Arrays.asList(
        new Product("PIPELUCHE", 14.99, "https://cdn1.peluchilandia.es/3304-home_default/peluche-perrito-melancolico.jpg"),
        new Product("PIPOYOYO", 16.99, "https://www.jugarijugar.com/1057-large_default/yo-yo-de-madera-natural.jpg"),
        new Product("PIPOCAMELLO", 9.99, "https://www.oasysparquetematico.com/wp-content/uploads/2019/04/shutterstock_199591094.jpg"),
        new Product("PIPOSAURIO", 6.66, "https://www.mrbooks.com/mrbooks/portadas/9789506031718.jpg"),
        new Product("PIPOKIMON", 69.69, "https://images-na.ssl-images-amazon.com/images/I/81wESosz9eL._AC_SY355_.jpg")
    ));

    @GetMapping("products")
    public ModelAndView getProducts() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("products", products);
        return mv;
    }

    public static Product findProdById(int id){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id) return products.get(i);
        }
        throw new ElementNotFoundException();
    }
}
