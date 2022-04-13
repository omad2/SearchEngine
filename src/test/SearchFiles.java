package test;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class SearchFiles {
    File f1;
    File[] files;
    List<String> allowedFiles;

    public SearchFiles(String path) {
        f1 = new File(path);
        files = f1.listFiles((dir, name) -> name.endsWith(".txt"));
        allowedFiles = new ArrayList<>();
    }

    public Map<String, Integer> Search(String searchQuery) throws FileNotFoundException {
        HashMap<String, Integer> fileOccurrences = new HashMap<>();

        for (File file : files) {
            if (!allowedFiles.contains(file.getName())) {
                continue;
            }

            int occurrences = 0;
            BufferedReader inputStream = new BufferedReader(new FileReader(file));

            try {
                String line;

                while ((line = inputStream.readLine()) != null) {
                    String[] splittedSearchQuery = searchQuery.split(" ");

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

        return fileOccurrences.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k, v) -> k, LinkedHashMap::new));
    }
}
