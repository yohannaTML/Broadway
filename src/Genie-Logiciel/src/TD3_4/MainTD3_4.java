package TD3_4;


import TD3_4.TextAnalyzer.WordCounter;


public class MainTD3_4 {
    public static void main(String[] args){
        String filename = "1080.txt";
        try {
            TextAnalyzer loader = new TextAnalyzer(filename);
            WordCounter finale[] = loader.topWords(2);
            for (int read = 0; read < finale.length; read ++){
                System.out.println(finale[read]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

