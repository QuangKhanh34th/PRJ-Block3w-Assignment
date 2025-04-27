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
public class DetailedOrder implements Serializable{
    String detailedID;
    String detailedProdID;
    int detailedAmount;
    double detailedPrice;
    double detailedDiscount;
    double detailedTruePrice;

    public DetailedOrder() {
    }

    public DetailedOrder(String detailedID, String detailedProdID, int detailedAmount, double detailedPrice, double detailedDiscount, double detailedTruePrice) {
        this.detailedID = detailedID;
        this.detailedProdID = detailedProdID;
        this.detailedAmount = detailedAmount;
        this.detailedPrice = detailedPrice;
        this.detailedDiscount = detailedDiscount;
        this.detailedTruePrice = detailedTruePrice;
    }

    public String getDetailedID() {
        return detailedID;
    }

    public void setDetailedID(String detailedID) {
        this.detailedID = detailedID;
    }

    public String getDetailedProdID() {
        return detailedProdID;
    }

    public void setDetailedProdID(String detailedProdID) {
        this.detailedProdID = detailedProdID;
    }

    public int getDetailedAmount() {
        return detailedAmount;
    }

    public void setDetailedAmount(int detailedAmount) {
        this.detailedAmount = detailedAmount;
    }

    public double getDetailedPrice() {
        return detailedPrice;
    }

    public void setDetailedPrice(double detailedPrice) {
        this.detailedPrice = detailedPrice;
    }

    public double getDetailedDiscount() {
        return detailedDiscount;
    }

    public void setDetailedDiscount(double detailedDiscount) {
        this.detailedDiscount = detailedDiscount;
    }

    public double getDetailedTruePrice() {
        return detailedTruePrice;
    }

    public void setDetailedTruePrice(double detailedTruePrice) {
        this.detailedTruePrice = detailedTruePrice;
    }
    
    
    
}
