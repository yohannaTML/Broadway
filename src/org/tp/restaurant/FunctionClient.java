package org.tp.restaurant;

import org.tp.logger.Logger;
import org.tp.logger.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FunctionClient extends Restaurant {

    public final Map<String, Client> clients = new HashMap<>();

    Logger logger = LoggerFactory.getLogger();
    Scanner scan = new Scanner(System.in);

    public void list() {
        logger.info("OUTPUT", "Liste des clients en cours :");
        for (Client cl : clients.values()) {
            logger.info("OUTPUT", cl.getClientName());
        }
        logger.info("OUTPUT", "Retour au menu principal.");
    }

    public void openNote() {
        logger.info("OUTPUT", "Note du client");
        logger.info("OUTPUT", "Entrez le nom du client :");
        String clientName = scan.next();
        Client client = clients.get(clientName);
        if (client == null) {
            logger.info("OUTPUT", "Le client n'existe pas, voulez vous créer une nouvelle note ? (oui ou non)");
            String test = scan.next();
            if (test.equals("oui") || test.equals("non")) {
                if (test.equals("oui")) {
                    logger.info("INPUT", test);
                    clients.put(clientName, new Client(clientName));
                    client = clients.get(clientName);
                } else {
                    logger.info("OUTPUT", "retour au menu principal");
                    return;
                }
            } else {
                logger.error("OUTPUT", "vous n'avez pas écrit correctement, retour au menu principal");
                return;
            }
        }
        logger.info("OUTPUT", "La note de " + clientName + " a été ouverte, voici la liste de ses produits :");
        client.listProducts();
        logger.info("OUTPUT", "Pour un total de " + client.totalprice() + "€ \nRetour au menu principal.");

    }

    public Client clientNote() {
        logger.info("OUTPUT", "clôturer la note d'un client");
        logger.info("OUTPUT", "Quel est le nom du client :");
        String clientName2 = scan.next();
        Client client3 = clients.get(clientName2);
        if (client3 == null) {
            logger.info("INPUT", clientName2);
            logger.error("OUTPUT", "Le client n'existe pas, retour au menu principal");

        } else {
            logger.info("OUTPUT", "Voici la note de " + clientName2);
            client3.totalnote();
            logger.info("OUTPUT", "Pour un total de " + (client3.totalprice() - (client3.totalprice() * 0.1)) + "€ ht");
            logger.info("OUTPUT", "La TVA est de : " + client3.tva() + "€");
            logger.info("OUTPUT", "Pour un total final de " + client3.totalprice() + "€");
        }
        return client3;
    }

    public void closeClient(Client client){
        client.clear();
        clients.remove(client.getClientName());
        logger.info("OUTPUT","La note du client a été cloturée, retour au menu principal");
        return;
    }
}
