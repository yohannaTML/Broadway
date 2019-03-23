package org.tp.restaurant;

import org.tp.logger.Logger;
import org.tp.logger.LoggerFactory;

public class Menu {

    public void printHelp() {
        Logger logger = LoggerFactory.getLogger();

        logger.info("OUTPUT", "Commandes disponibles :");
        logger.info("OUTPUT", "    a : ajouter un produit (nom, prix, stock)");
        logger.info("OUTPUT", "    l : afficher la liste des produits à la vente");
        logger.info("OUTPUT", "    p : afficher la liste des clients présents");
        logger.info("OUTPUT", "    o : ouvrir la note d'un client");
        logger.info("OUTPUT", "    e : enregistrer la vente d'un produit sur la note d'un client");
        logger.info("OUTPUT", "    c : clôturer la note d'un client");
        logger.info("OUTPUT", "    d : afficher les données comptables");
        logger.info("OUTPUT", "    h : affiche la liste des commandes disponibles");
    }

}
