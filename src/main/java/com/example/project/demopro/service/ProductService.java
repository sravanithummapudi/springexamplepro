package com.example.project.demopro.service;

import com.example.project.demopro.model.Product;
import com.example.project.demopro.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
    public List<Product> getAllproducts(){
          return repo.findAll();
    }

    public Product getProduct(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        String mimeType = imageFile.getContentType();

        if (mimeType == null || mimeType.isEmpty()) {
            throw  InvalidMimeTypeException("MIME type is null or empty");
        }

        byte[] imageBytes = imageFile.getBytes();
        product.setImageDate(imageBytes);

        // Save the MIME type to the product object
        product.setImageType(mimeType);
      //  product.setImageType(imageFile.getContentType());
      //  product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    private IOException InvalidMimeTypeException(String s) {
        return null;
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {

        Optional<Product> existingProductOpt = repo.findById(id);

        if (!existingProductOpt.isPresent()) {
            // If product doesn't exist, return null or throw an exception
            return null; // or throw new RuntimeException("Product not found");
        }

        Product existingProduct = existingProductOpt.get();

        // Update the fields of the existing product
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setReleaseDate(product.getReleaseDate());
        // Update other fields as needed...

        // Handle the image file if provided
        if (imageFile != null && !imageFile.isEmpty()) {
            // Set image name, type, and data
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
            existingProduct.setImageData(imageFile.getBytes());
        }

        // Save and return the updated product
        return repo.save(existingProduct);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

}
