package org.restaurant;

import org.logger.Logger;
import org.logger.LoggerFactory;

import java.util.Scanner;

public class ScanPriceAndQuantity {
    Scanner scan = new Scanner(System.in);
    Logger logger = LoggerFactory.getLogger();

    public int quantity(){
        logger.info("OUTPUT", "Quantité du produit à ajouter (un entier positif est attendu) :");

        while (!scan.hasNextInt())
        {
            scan.next();
            logger.error("OUTPUT","Entrez un nombre s'il vous plait : ");
        }
        int quantity = scan.nextInt();
        if(quantity <= 0){
            logger.error("OUTPUT","Vous ne pouvez pas entrer de quantité inférieur ou égale à 0. ");
            quantity();
        }
        System.out.println("TEST" + quantity);
        return quantity;
    }

    public double price(){
        logger.info("OUTPUT", "Prix du produit à ajouter (un nombre positif est attendu) :");

        while (!scan.hasNextDouble())
        {
            scan.next();
            logger.error("OUTPUT","Entrez un nombre s'il vous plait : ");
        }
        double price = scan.nextDouble();
        if(price <= 0){
            logger.error("OUTPUT","Vous ne pouvez pas entrer de prix inférieur ou égale à 0. ");
            quantity();
        }
        System.out.println("TEST" + price);
        return price;
    }
}
