package test;

import java.io.*;
import java.util.*;

public class SearchFiles {

    int i;
    int lvl;

    //C:\Users\gamin\SearchEngine\src\test\Database
    String path = "C:\\Users\\gamin\\SearchEngine\\src\\test\\Database";
    File f1 = new File(path);




    public void Search() {
        File[] files = f1.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });


        for(File file : files){
            //System.out.println(file.getName());
            file.getAbsoluteFile();
            BufferedReader inputStream = null;
            try{
                inputStream = new BufferedReader(new FileReader(file));
                String line;

                while ((line = inputStream.readLine()) != null){
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
