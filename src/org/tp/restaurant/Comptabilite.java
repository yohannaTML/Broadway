package org.tp.restaurant;

import org.tp.logger.Logger;
import org.tp.logger.LoggerFactory;

public class Comptabilite {
    private double totaltva;
    private double totalmoney;

    public Comptabilite (double totaltva, double totalmoney) {
        this.totalmoney = totalmoney;
        this.totaltva = totaltva;
    }

    public void addMoney(Double money) {
        totalmoney = totalmoney + money;
    }

    public double getTotaltva() {
        totaltva = totalmoney * 0.1;
        return totaltva;
    }

    public double getTotalmoney() {

        return totalmoney;
    }

    public void printData(){
        Logger logger = LoggerFactory.getLogger();
        logger.info("OUTPUT","afficher les données comptables");
        logger.info("OUTPUT","Voici les données enregistrées :");
        logger.info("OUTPUT","Total d'entrée d'argent : " + getTotalmoney() + "€");
        logger.info("OUTPUT","Total de TVA facturée : " + getTotaltva() + "€");
        logger.info("OUTPUT","Retour au menu principal.");
    }
}

