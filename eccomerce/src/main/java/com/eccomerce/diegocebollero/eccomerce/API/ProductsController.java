package com.eccomerce.diegocebollero.eccomerce.API;

import java.util.ArrayList;
import java.util.Arrays;

import com.eccomerce.diegocebollero.eccomerce.Model.Product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    private static ArrayList<Product> products = new ArrayList<>(Arrays.asList(
        new Product("PIPELUCHE", 14.99, "https://cdn1.peluchilandia.es/3304-home_default/peluche-perrito-melancolico.jpg"),
        new Product("PIPOYOYO", 16.99, "https://cdn1.peluchilandia.es/3304-home_default/peluche-perrito-melancolico.jpg"),
        new Product("PIPOCAMELLO", 9.99, "https://www.oasysparquetematico.com/wp-content/uploads/2019/04/shutterstock_199591094.jpg"),
        new Product("PIPOSAURIO", 6.66, "https://www.mrbooks.com/mrbooks/portadas/9789506031718.jpg"),
        new Product("PIPOKIMON", 69.69, "https://cdn1.peluchilandia.es/3304-home_default/peluche-perrito-melancolico.jpg")
    ));

    @GetMapping("products")
    public ArrayList<Product> getProducts() {
        return products;
    }
}
