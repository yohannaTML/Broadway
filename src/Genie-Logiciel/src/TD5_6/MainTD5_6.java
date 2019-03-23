package TD5_6;


import TD5_6.TextAnalyzer.WordCounter;


public class MainTD5_6 {
    public static void main(String[] args){
        String filename = "1080.txt";
        String filename2 = "stopWords.txt";
        try {
            TextAnalyzer loader = new TextAnalyzer(filename, filename2);
            WordCounter finale[] = loader.topWords(100);
            /*for (int read = 0; read < finale.length; read ++){
                System.out.println(finale[read]);
            }*/
            System.out.println(loader.HTMLCloud(finale));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

