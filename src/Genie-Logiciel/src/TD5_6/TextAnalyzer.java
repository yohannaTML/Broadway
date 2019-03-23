package TD5_6;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;


/**
 *	@author Taptue Yohanna
 *	@version 1.0
 */

public class TextAnalyzer{


    /**
     * Class WordCounter
     */
    public class WordCounter implements Comparable<Object>{
        private String _w;
        private int _c;
        /**
         * Constructeur WordCounter : String + Integer
         * Lors de la création, le nombre d'wordExist est égale à 1.
         * @param m : un _w
         * @param c : le nombre d'wordExist
         */
        public WordCounter(String m,int c){
            _w = m;
            _c = c;
        }
        /**
         * Getter _w
         *
         * @return _w
         */
        public String getWord(){
            return _w;
        }
        /**
         * Getter nombre d'wordExist
         * @return nombre d'wordExist
         */
        public int get_c(){
            return _c;
        }

        /**
         * Ecriture d'un WordCounter
         */
        @Override
        public String toString(){
            return ""+ _w +"="+ _c +" ";
        }

        /**
         * Comparer le WordCounter afin qu'il soit dans un ordre ordre décroissant
         */
        @Override
        public int compareTo(Object o) {
            if(this._c < ((WordCounter)o)._c){
                return 1;
            }
            else if(this._c > ((WordCounter)o)._c){
                return -1;
            }
            return 0;
        }
    }

    private HashMap<String,Integer> wordExist = new HashMap<>();
    private ArrayList<WordCounter> decreasingOrder = new ArrayList<>();

    /**
     * Premier constructeur qui ouvre le fichier texte, le lit, et traite les mots.
     * <p>
     * Rempli un tableau de WordCounter en ordre décroissant.
     * <p>
     * @param nomFichier : le nom du fichier
     * @throws IOException : Problème lors de la lecture du document texte
     */
    public TextAnalyzer(String nomFichier) throws IOException{
        this.Load(nomFichier);
        this.loadWordCounter();
    }

    /**
     * Deuxième constructeur qui ouvre le fichier texte et le fichier stopWords, les lits et les traites
     * Les mots dans stopWords sot retirer de la liste de WordCounter
     * @param nomFichier : le nom du fichier
     * @param stopWords : le nom du fichier stopWords
     * @throws IOException : Problème lors de la lecture du document texte
     */
    public TextAnalyzer(String nomFichier, String stopWords) throws IOException{

        String line;
        File file1 = new File(stopWords);
        BufferedReader br2 = this.openFile(file1);
        this.Load(nomFichier);
        while ((line = br2.readLine())!=null){
            if (wordExist.containsKey(line))
                wordExist.remove(line);
        }
        br2.close();
        this.loadWordCounter();
    }

    /**
     * @param file : un document texte
     * @return le BufferedReader correspondant au texte
     * @throws IOException : si le document texte n'existe pas
     */
    private BufferedReader openFile(File file) throws IOException{

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        return br;
    }

    /**
     * @param nomFichier : le nom du fichier
     * @throws IOException : si le document texte n'existe pas
     */
    private void Load(String nomFichier) throws IOException{
        File file = new File(nomFichier);
        BufferedReader br = this.openFile(file);
        String line;
        while((line = br.readLine())!=null ){
            this.createStringBuilder(line);
        }
        br.close();
    }

    /**
     * Décompose une ligne en _w. Chaque _w sera traité en direct.
     * <p>
     * Un _w est défini selon les contraintes du TD2
     * @param line : une ligne
     */
    private void createStringBuilder(String line){
        char eachCaractere;
        StringBuilder wordBuilder = new StringBuilder();
        boolean isANumber = false;
        for(int read = 0; read < line.length(); read++){

            eachCaractere = Character.toLowerCase(line.charAt(read));

            if(Character.isLetter(eachCaractere)){
                if(!isANumber){
                    wordBuilder.append(eachCaractere);
                    if(read == line.length()-1){
                        this.addToStringBuilder(wordBuilder);
                    }
                }
            }
            else{
                if(Character.isDigit(eachCaractere)){
                    isANumber = true;
                    wordBuilder = new StringBuilder();
                }
                else if(eachCaractere == '\''){
                    if(!isANumber){
                        wordBuilder.append(eachCaractere);
                    }
                }
                else{
                    if(!isANumber && wordBuilder.length()!=0){
                        this.addToStringBuilder(wordBuilder);
                        wordBuilder = new StringBuilder();
                    }
                    isANumber = false;
                }
            }

        }
    }

    /**
     * Methode qui crée un code html pour afficher avec différentes et px les mots les plus fréquents
     * @param words : les mots dans le wordCounter
     * @return : le code du fichier html
     */
    public String HTMLCloud(WordCounter[] words){
        List<WordCounter> wordlist= Arrays.asList(words);
        StringBuilder wordHTML= new StringBuilder();
        Collections.shuffle(wordlist);
        String[] color={"brown","black","red","orange","burgundy"};
        wordHTML.append("<!doctype html>\n").append("<body>\n");
        wordHTML.append("<div>");

        for(WordCounter mot:wordlist) {
            int colorid = new Random().nextInt(color.length);
            String random = (color[colorid]);
            wordHTML.append("<span style='color:").append(random)
                    .append(";font-size:").append(mot.get_c()*3).append("px").append("'>")
                    .append(mot.getWord()).append("</span>").append("&emsp;");
        }

        wordHTML.append("</div> </body>\n").append("</html>\n");
        return wordHTML.toString();
    }
    /**
     * Avoir les n premiers WordCounter trié par ordre décroissant de leur nombre de mot existant
     *
     * @param n : nombre de mot minimum qu'on veut
     * @return un tableau de WordCounter
     */
    public WordCounter[] topWords(int n){
        boolean seen = false;
        n--;

        while(!seen){
            if(decreasingOrder.size()-1==n){
                seen = true;
                n++;
            }
            else if(decreasingOrder.get(n).get_c()== decreasingOrder.get(n+1).get_c()){
                n++;
            }
            else{
                seen =true;
                n++;
            }
        }

        WordCounter[] finale = new WordCounter[n];

        for(int repetition = 0; repetition < n;repetition++){
            finale[repetition] = decreasingOrder.get(repetition);
        }
        return finale;
    }

    /**
     * Méthode de transition entre Hashmap de String et Integer à une ArrayList de WordCounter
     */
    private void loadWordCounter(){
        Set<Entry<String, Integer>> occurenceEntry = wordExist.entrySet();
        Object[] occurenceOfElements = occurenceEntry.toArray();
        String[] occurenceOfOneElement;
        for(int read = 0; read < wordExist.size(); read++){
            occurenceOfOneElement = occurenceOfElements[read].toString().split(""+'=');
            this.decreasingOrder.add(new WordCounter(occurenceOfOneElement[0], wordExist.get(occurenceOfOneElement[0])));
        }
        decreasingOrder.sort(null);
    }

    /**
     * Méthode qui va dans un premier temps chercher dans la hashmap si le _w motBuilder existe.
     * <p>
     * S'il existe, elle va incrémenter son nombre d'wordExist sinon elle va le rajouter à la hashmap
     * @param motBuilder : le _w
     */
    private void addToStringBuilder(StringBuilder motBuilder){
        String mot = new String(motBuilder);
        if(wordExist.containsKey(mot)){
            wordExist.replace(mot, wordExist.get(mot)+1);
        }
        else{
            wordExist.put(mot, 1);
        }
    }
}
