package com.springboot.ecom_proj.controller;

import com.springboot.ecom_proj.model.Product;
import com.springboot.ecom_proj.service.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Hidden
    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/product")
    public Product setSingleProduct(@RequestBody Product product){

        return productService.setSingleProduct(product);
    }

    @PostMapping("/products")
    public  List<Product> setAllProducts(@RequestBody List<Product> product){
        return productService.setAllProducts(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getSingleProduct(@PathVariable int id){

        Optional<Product> product = productService.getSingleProduct(id);
        if (product.isPresent())
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody Product product){
      Product product1 = productService.updateProduct(id,product);
        if (product1!=null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(product,HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Optional<Product> product = productService.getSingleProduct(id);
        if (product.isPresent()){
            productService.deleteProduct(id);
            return new ResponseEntity<>(" Deleted",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){

        System.out.println("searching with "+keyword);
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
