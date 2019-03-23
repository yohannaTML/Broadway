package org.restaurant;

import org.logger.Logger;
import org.logger.LoggerFactory;

public class Menu {
    Logger logger = LoggerFactory.getLogger();
    public void printHelp() {
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

    public void error(){
        logger.error("OUTPUT","vous n'avez pas écrit correctement, retour au menu principal");
    }

}
