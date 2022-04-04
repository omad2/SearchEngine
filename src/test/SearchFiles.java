package test;

import java.io.*;
import java.util.HashMap;

public class SearchFiles {
    // TODO: path must be dynamic (i.e asking the user for the path.)
    String path = "C:\\Users\\gamin\\SearchEngine\\src\\test\\Database";
    File f1 = new File(path);

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
