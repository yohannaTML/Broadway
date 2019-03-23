package org.restaurant;

import org.logger.Logger;
import org.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final String clientName;
    private final List<Product> orderedProducts = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger();
    private double total = 0;

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void addProduct(Product product) {
        logger.info("OUTPUT", "le produit ajouté est : " + product.getName() + " à " + product.getPrice() +"€");
        boolean var = orderedProducts.add(product);
        if(!var){
            logger.info("OUTPUT", "Votre produit ne s'est pas ajouté.");
        } else {
            logger.info("OUTPUT", "Votre produit a été ajouté.");
        }
        total = total + product.getPrice();
    }

    public double totalprice(){
        return total;
    }

    public void totalnote(){
        for(Product prod: orderedProducts)
        {
            double prodht = prod.getPrice() - (prod.getPrice() * 0.10);
            System.out.println("- " + prod.getName() + " = " + prodht + "€");
        }
    }

    public double tva() {
        double tva = total * 0.1;
        return tva;
    }

    public void listProducts(){
        if(orderedProducts.isEmpty()){
            logger.info("OUTPUT","La liste est vide.");
        }
        for(Product prod: orderedProducts){
            logger.info("OUTPUT","- " + prod.getName() + " = " + prod.getPrice() + "€");
        }
    }

    public void clear() {
        orderedProducts.clear();
    }
}
