package com.senai.hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.senai.hello.entity.Product;

@RestController
@RequestMapping("products")
public class ProductController {
    private List<Product> list = new ArrayList<>();

    public ProductController() {
        list.add(new Product(1L, "product 1", 100.0));
        list.add(new Product(2L, "product 2", 200.0));
        list.add(new Product(3L, "product 3", 300.0));
    }

    @GetMapping
    public List<Product> getProducts() {
        return list;
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id) {
        return list.stream()
                   .filter(p -> p.getId().equals(id))
                   .findFirst()
                   .orElseThrow(() -> 
                       new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
                   );
    }

}


