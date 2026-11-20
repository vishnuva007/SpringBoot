package com.springboot.ecom_proj.service;

import com.springboot.ecom_proj.model.Product;
import com.springboot.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {

        return productRepo.findAll();
    }

    public List<Product> setAllProducts(List<Product> product) {

        return productRepo.saveAll(product);
    }

    public Product setSingleProduct(Product product) {

        return productRepo.save(product);
    }

    public Optional<Product> getSingleProduct(int id) {

        return productRepo.findById(id);
    }

    public Product updateProduct(int id,Product product) {

        return productRepo.save(product);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
