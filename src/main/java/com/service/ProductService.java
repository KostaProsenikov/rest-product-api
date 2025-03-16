package com.service;

import org.springframework.stereotype.Service;
import com.model.Product;
import com.web.dto.NewProductRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product createNewProduct(NewProductRequest productRequest) {

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .build();

        if (productRequest.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        } else {
           int indexOfProduct = products.indexOf(product);
            System.out.println("indexOfProduct: " + indexOfProduct);
            Product existingProduct = products.stream()
                    .filter(prod -> prod.getName().equals(product.getName()))
                    .findAny()
                    .orElse(null);
           if (existingProduct != null) {
               existingProduct.setQuantity(existingProduct.getQuantity() + productRequest.getQuantity());
           } else {
               products.add(product);
           }
        }

        return product;
    }
}