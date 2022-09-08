package com.bzone.ecomm.controller;

import com.bzone.ecomm.dto.BZoneResponse;
import com.bzone.ecomm.entiry.Products;
import com.bzone.ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = {})
    public ResponseEntity<BZoneResponse> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> addProduct(@RequestBody Products product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @GetMapping(value = "/search/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BZoneResponse> getProductSearch(@PathVariable("keyword") String keyword) {
        BZoneResponse search = productService.searchProduct(keyword);
        return ResponseEntity.ok(search);
    }

}
