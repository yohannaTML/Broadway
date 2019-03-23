package org.tp.restaurant;

import org.tp.logger.LoggerFactory;
import org.tp.logger.Logger;
import java.util.*;

public class Restaurant {
    private Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        restaurant.start();
    }

    private void start() {
        logger.info("OUTPUT", "Bienvenue chez BagelYoMa♥ [entrer h pour afficher la liste des opérations possibles]");
        Scanner scan = new Scanner(System.in);
        Comptabilite compta = new Comptabilite(0,0);
        Menu menu = new Menu();
        FunctionProduct functionProduct = new FunctionProduct();
        FunctionClient functionClient = new FunctionClient();
        String choice;
        functionProduct.initialize();
        Map<String, RowProduct> stock = functionProduct.stock;
        Map<String,Client> clients = functionClient.clients;
        boolean q = true;
        while (q) {
            choice = scan.next();
            if (choice.length() > 0) {
                switch (choice.charAt(0)) {
                    case 'h':
                        menu.printHelp();
                        break;

                    case 'q':
                        q = false;
                        logger.info("OUTPUT", "bye bye!\n");
                        logger.debug("PROGRAM","error");
                        break;

                    case 'a':
                        functionProduct.addProduct();
                        break;

                    case 'l':
                        functionProduct.list();
                        break;

                    case 'p':
                        functionClient.list();
                        break;

                    case 'o':
                        functionClient.openNote();
                        break;

                    case 'e':
                        logger.info("OUTPUT", "enregistrer la vente d'un produit sur la note d'un client");
                        logger.info("OUTPUT", "Quel est le nom du client :");
                        String clientName = scan.next();
                        Client client2 = clients.get(clientName);
                        if (client2 == null) {
                            logger.error("OUTPUT", "Le client n'existe pas, retour au menu principal");
                            break;
                        }
                        logger.info("OUTPUT", "Quel produit achète-t-il ?");
                        String productName = scan.next();
                        RowProduct rowProduct2 = stock.get(productName);
                        if (rowProduct2 != null && rowProduct2.getQuantities() != 0) {
                                Product product = rowProduct2.consomme();
                                client2.addProduct(product);
                            }
                            else {
                            logger.error("OUTPUT", "Le produit n'existe pas ou n'est plus en vente, retour au menu principal.");
                            break;
                        }
                        break;

                    case 'c':
                        Client client3 = functionClient.clientNote();
                        if(client3 == null){
                            break;
                        }
                        clientName = client3.getClientName();
                        logger.info("OUTPUT","Voulez-vous faire une remise de 10% pour " + clientName + "? (oui ou non)");
                        String test = scan.next();
                        double discount = client3.totalprice();
                        if(test.equals("oui") || test.equals("non")){
                            if(test.equals("oui")) {
                                discount = discount - (client3.totalprice() * 0.1);
                                logger.info("OUTPUT","Le prix avec remise est désormais de " + discount + "€");
                                compta.addMoney(discount);
                            } else { compta.addMoney(client3.totalprice()); }
                            functionClient.closeClient(client3);
                            break;

                        } else {
                            logger.error("OUTPUT","vous n'avez pas écrit correctement, retour au menu principal");
                            break;
                        }

                    case 'd':
                        compta.printData();
                        break;

                    default:
                        logger.info("OUTPUT","Commande introuvable, appuyer sur h pour plus d'option.");
                        break;
                }
            }
        }
    }
}
