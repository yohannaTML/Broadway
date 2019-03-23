package org.tp.restaurant;

import org.tp.logger.Logger;
import org.tp.logger.LoggerFactory;

import java.util.Scanner;
import java.util.Map;
import java.util.*;

public class FunctionProduct extends Restaurant{

    public final Map<String, RowProduct> stock = new HashMap<>();

    public void list(){
        Logger logger = LoggerFactory.getLogger();
        logger.info("OUTPUT", "Liste des produits à la vente :");
        for (RowProduct rp : stock.values()) {
            logger.info("OUTPUT", rp.getCommonName() + '\t' + '\t' + rp.getQuantities() + '\t' + '\t'+ rp.getPrice() + "€");
        }
        logger.info("OUTPUT", "Retour au menu principal.");
    }

    public void addProduct() {
        Scanner scan = new Scanner(System.in);
        Logger logger = LoggerFactory.getLogger();
        logger.info("OUTPUT", "ajouter un produit à la vente(nom, prix, stock)");
        logger.info("OUTPUT", "Nom du produit à ajouter :");
        String name = scan.next();
        logger.info("OUTPUT", "Quantité du produit à ajouter :");
        int quantity = scan.nextInt();
        logger.info("OUTPUT", "Prix du produit à ajouter :");
        String temp = scan.next();
        double price = Double.parseDouble(temp);
        RowProduct rowProduct = stock.get(name);
        if (rowProduct == null) {
            stock.put(name, new RowProduct(name, quantity, price));
        } else {
            rowProduct.addProducts(quantity);
        }
        logger.info("OUTPUT", "Votre produit a été ajouté, retour au menu principal.");
    }

    public void initialize() {
        stock.put("bagel", new RowProduct("bagel", 20, 5));
        stock.put("burger", new RowProduct("burger", 10, 8));
        stock.put("smoothie", new RowProduct("smoothie", 30, 4));
        double infinite = Double.POSITIVE_INFINITY;
        stock.put("cafe", new RowProduct("cafe", infinite,2));
    }
}

