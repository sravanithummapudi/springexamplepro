package com.example.project.demopro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

//import static com.fasterxml.jackson.annotation.JsonFormat.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    public Product() {

    }
    private String name;



    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private boolean ProductAvailable;
    private int stockQuantity;
    private String imageType;
    private String imageName;
    @Lob
    private byte[] imageDate;

    public Product(String name, String brand, String description, BigDecimal price, String category, Date releaseDate, boolean productAvailable, int stockQuantity, String imageType, byte[] imageDate, String imageName, int id) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.category = category;
        this.releaseDate = releaseDate;
        ProductAvailable = productAvailable;
        this.stockQuantity = stockQuantity;
        this.imageType = imageType;
        this.imageDate = imageDate;
        this.imageName = imageName;
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageDate() {
        return imageDate;
    }

    public void setImageDate(byte[] imageDate) {
        this.imageDate = imageDate;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isProductAvailable() {
        return ProductAvailable;
    }

    public void setProductAvailable(boolean productAvailable) {
        ProductAvailable = productAvailable;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    public void setImageData(byte[] bytes) {
    }

    public void setImageName(String originalFilename) {
    }

    public void setImageType(String contentType) {
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", releaseDate=" + releaseDate +
                ", ProductAvailable=" + ProductAvailable +
                ", stockQuantity=" + stockQuantity +
                ", imageType='" + imageType + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageDate=" + Arrays.toString(imageDate) +
                '}';
    }


}
