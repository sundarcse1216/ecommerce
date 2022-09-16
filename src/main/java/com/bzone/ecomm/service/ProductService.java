package com.bzone.ecomm.service;

import com.bzone.ecomm.dto.BZoneResponse;
import com.bzone.ecomm.dto.Search;
import com.bzone.ecomm.entiry.Categories;
import com.bzone.ecomm.entiry.Products;
import com.bzone.ecomm.entiry.Tags;
import com.bzone.ecomm.repo.CategoryRepository;
import com.bzone.ecomm.repo.ProductRepository;
import com.bzone.ecomm.repo.TagsRepository;
import com.bzone.ecomm.util.CommonConstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TagsRepository tagsRepository;

    @Autowired
    BZoneResponse response;

    public BZoneResponse getAllProducts() {
        List<Products> products = productRepository.findAll();
        if (!products.isEmpty()) {
            response.setCode(CommonConstance.SUCCESS_CODE);
            response.setData(products);
            response.setMessage("Success");
        } else {
            response.setCode(CommonConstance.NO_CONTENT);
            response.setMessage("No Product available.");
        }
        return response;
    }

    public BZoneResponse getProductById(Long productId) {
        Optional<Products> product = productRepository.findById(productId);
        if (product.isPresent()) {
            response.setCode(CommonConstance.SUCCESS_CODE);
            response.setData(product.get());
            response.setMessage("Success");
        } else {
            response.setCode(CommonConstance.NO_CONTENT);
            response.setMessage("No Product available.");
        }
        return response;
    }

    @Transactional
    public BZoneResponse searchProduct(String keyword) {
        List<Search> searches = productRepository.productSearch(keyword);
        response.setData(searches);
        response.setCode(CommonConstance.SUCCESS_CODE);
        return response;
    }

    @Transactional
    public BZoneResponse saveProduct(Products product) {
        for (Categories category : product.getCategories()) {
            category.setProduct(product);
        }
        for (Tags tag : product.getTags()) {
            tag.setProduct(product);
        }
        Products saveProduct = productRepository.save(product);
        response.setCode(CommonConstance.CREATE_SUCCESS);
        response.setData(saveProduct);
        response.setMessage("Product created successfully.");
        return response;
    }

    @Transactional
    public BZoneResponse updateProduct(Products product) {
        return response;
    }

    @Transactional
    public BZoneResponse deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        response.setCode(CommonConstance.SUCCESS_CODE);
        response.setMessage("Deleted Successfully.");
        return response;
    }
}
