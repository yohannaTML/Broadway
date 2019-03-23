package org.tp.restaurant;

public class RangeeProduit {

    private double price;
    private int quantities;
    private final String commonName;

    public RangeeProduit(String pNom, int pQuantite, double pPrix) {
        commonName = pNom;
        quantities = pQuantite;
        price = pPrix;
    }

    public String getCommonName() {
        return commonName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantities() {
        return quantities;
    }


    public void addProduits(int quantities) {
        if (quantities > 0) {
            this.quantities += quantities;
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Produit consomme() {
        if (quantities > 0) {
            quantities--;
            return new Produit(commonName, price);
        }
        return null;
    }

    @Override
    public String toString() {
        return commonName + "\t" + "\t" + price + "€" + "\t" + quantities;
    }
};


