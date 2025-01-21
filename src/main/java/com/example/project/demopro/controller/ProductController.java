package com.example.project.demopro.controller;

import com.example.project.demopro.model.Product;
import com.example.project.demopro.repo.ProductRepo;
import com.example.project.demopro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepo repo;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllproducts(){

        return new ResponseEntity<>(service.getAllproducts(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product=service.getProduct(id);

        if(product!=null)
            return  new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile)
    {

        try{
            Product product1=service.addProduct(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = repo.findById(productId).orElseThrow();

        byte[] imageFile = product.getImageDate();
        String imageType = product.getImageType();

        if (imageFile == null || imageType == null || imageType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if no valid image found
        }

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(imageType))
                    .body(imageFile);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null); // Handle invalid media type
            //   Product product2 = service.getProduct(productId);
            // byte[] imageFile = product2.getImageDate();

            //return ResponseEntity.ok().contentType(MediaType.valueOf(product2.getImageType())).body(imageFile);

        }


    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {

        try {
            Product updatedProduct = service.updateProduct(id, product, imageFile);
            if (updatedProduct != null) {
                return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update product", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Error updating product: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = service.getProduct(id);
        if (product != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

    }


}

//contentType(MediaType.valueOf(product2.getImageType()))