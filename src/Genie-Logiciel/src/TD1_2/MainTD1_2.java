package TD1_2;

import java.util.ArrayList;

public class MainTD1_2 {

    public static void main(String[] args){
          ReadCSV loader = new ReadCSV();

        int rowsLoaded = loader.load("world_cities.csv",
                ',');
        System.out.println(rowsLoaded + "\t" + "rows loaded");
        ArrayList<ArrayList<String>> data = loader.getData();
        for (ArrayList<String> line: data) {
            System.out.println(line);
        }

    }
}
