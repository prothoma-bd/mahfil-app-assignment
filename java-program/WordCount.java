import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCount {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Please set file path in first args");
            return;
        }
        String filePath=args[0];

        File f = new File(filePath);
        if(!f.exists()) {
            System.out.println("File does not found");
            return;
        }

        showResultInAlphabeticalOrder(readDataFromFile(args[0]));
    }



    public static  Map<String, Integer>  readDataFromFile(String filePath){
        Map<String, Integer> wordCountMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into words using a regular expression
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        // Convert the word to lowercase
                        word = word.toLowerCase();
                        // Update the word count in the map
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordCountMap;
    }

    public static void showResultInAlphabeticalOrder( Map<String, Integer> wordCountMap){
       System.out.println("_______________________Result_________________________");
        TreeMap<String, Integer> sortedWordCountMap = new TreeMap<>(wordCountMap);
        for (Map.Entry<String, Integer> entry : sortedWordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("_______________________END_________________________");

    }
}