package test;

import java.io.*;
import java.util.HashMap;

public class SearchFiles {
    File f1;

    public SearchFiles(String path) {
        f1 = new File(path);
    }

    public HashMap<String, Integer> Search(String searchQuery) throws FileNotFoundException {
        HashMap<String, Integer> fileOccurrences = new HashMap<>();
        File[] files = f1.listFiles((dir, name) -> name.endsWith(".txt"));

        for(File file : files) {
            int occurrences = 0;
            BufferedReader inputStream = new BufferedReader(new FileReader(file));

            try {
                String line;

                while ((line = inputStream.readLine()) != null) {
                    String[] splittedLine = line.split(" ");

                    for (String word : splittedLine) {
                        String[] splittedQuery = searchQuery.split(" ");

                        for (String queryWord : splittedQuery) {
                            if (word.equalsIgnoreCase(queryWord)) {
                                occurrences++;
                            }
                        }
                    }
                }

                fileOccurrences.put(file.getName(), occurrences);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return fileOccurrences;
    }
}
