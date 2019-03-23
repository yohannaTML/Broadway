package TD1_2;

//Nom de l'étudiante: TAPTUE Yohanna

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {

    ArrayList<ArrayList<String>> lignes = new ArrayList<ArrayList<String>>();
    //nombre de lignes chargées avec success
    public int load(String filename, char sep) {
        int count = 0;
        String line;

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                String[] phrase = line.split("" + sep);
                count++;

                ArrayList<String> tab = new ArrayList<String>();
                for (int i=0; i<phrase.length; i++){
                    tab.add(phrase[i]);
                }
                lignes.add(tab);
            }
            fr.close();
            br.close();

        } catch (IOException e) {
            System.out.println("Problème rencontré");
        }
        return count;
    }

    ArrayList<ArrayList<String>> getData(){
        return lignes;
    }

    int fieldCount(){
        return lignes.get(0).size();
    }

}
