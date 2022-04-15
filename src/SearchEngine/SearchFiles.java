/*
 *Project: This project is a simple search engine that will
 *search through a folder of text files with the words or sentence you enter.
 *It will then show which text file has the highest link. It will display from
 *highest to lowest.
 */
package SearchEngine;

//Imports
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

//Start of SearchFiles Class
public class SearchFiles {

    //initialize
    File file;
    File[] files;
    List<String> allowedFiles;

    //Constructor
    public SearchFiles(String path) {
        file = new File(path);
        files = file.listFiles((dir, name) -> name.endsWith(".txt"));
        allowedFiles = new ArrayList<>();
    }

    /*
     * We put the files into a hashmap
     * easier to filter and search through each word.
     */
    public Map<String, Integer> Search(String searchQuery) throws FileNotFoundException {
        HashMap<String, Integer> fileOccurrences = new HashMap<>();

        //Iterates through each file
        for (File file : files) {
            if (!allowedFiles.contains(file.getName())) {
                continue;
            }

            //Get occurrences for the word the user searched
            int occurrences = 0;
            int i = 0;
            BufferedReader inputStream = new BufferedReader(new FileReader(file));

            try {
                String line;

                while ((line = inputStream.readLine()) != null) {
                    String[] splittedSearchQuery = searchQuery.split(" ");

                    /*
                     * If user type a sentence it will search for that sentence
                     * instead of splitting it word by word.
                     */
                    if (splittedSearchQuery.length == 1) {
                        String[] splittedLine = line.split(" ");

                        for (String word : splittedLine) {
                            if (word.equalsIgnoreCase(searchQuery)) {
                                occurrences++;
                            }
                        }
                    } else {
                        Pattern pattern = Pattern.compile(searchQuery);
                        Matcher matcher = pattern.matcher(line);

                        while (matcher.find()) {
                            occurrences++;
                        }
                    }
                }

                fileOccurrences.put(file.getName(), occurrences);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
         * Sorting the occurrences from highest
         * to lowest.
         */
        return fileOccurrences.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k, v) -> k, LinkedHashMap::new));
    }
}
