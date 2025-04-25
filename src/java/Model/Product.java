/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Product implements Serializable{
    private String prodID,
            prodName, 
            prodDescription, 
            prodImagePath, 
            prodCategory, 
            prodSupplier;
    private int prodQuantity;
    private double prodPrice;

    public Product() {
    }

    public Product(String prodID, String prodName, String prodDescription, String prodImagePath, String prodCategory, String prodSupplier, int prodQuantity, double prodPrice) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.prodImagePath = prodImagePath;
        this.prodCategory = prodCategory;
        this.prodSupplier = prodSupplier;
        this.prodQuantity = prodQuantity;
        this.prodPrice = prodPrice;
    }

    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public String getProdImagePath() {
        return prodImagePath;
    }

    public void setProdImagePath(String prodImagePath) {
        this.prodImagePath = prodImagePath;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    public String getProdSupplier() {
        return prodSupplier;
    }

    public void setProdSupplier(String prodSupplier) {
        this.prodSupplier = prodSupplier;
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }
}
