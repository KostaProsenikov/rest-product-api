package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.Product;
import com.service.ProductService;
import com.web.dto.NewProductRequest;
import com.web.dto.ProductResponse;
import com.web.mapper.DtoMapper;

import java.util.List;

import static com.web.Paths.API_V1_BASE_PATH;

@RestController
@RequestMapping (API_V1_BASE_PATH + "/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> list = productService.getAllProducts()
                .stream()
                .map(DtoMapper::toProductResponse)
                .toList();
        System.out.println("List of products fetched: " + list);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @PostMapping("")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody NewProductRequest productRequest) {
        Product product = productService.createNewProduct(productRequest);
        ProductResponse productResponse = DtoMapper.toProductResponse(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Application-name", "Spring Application")
                .body(productResponse);
    }


}