package org.Weekend;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Salutation {

    public void utilisateur(String name){
        DateFormat df = new SimpleDateFormat("EEEE");
        Date dateobj = new Date();


    }



    public static void main(String[] args){
        Salutation salut = new Salutation();
        salut.utilisateur("jojo");

    }
}


