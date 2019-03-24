package languageAnalyzer;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.io.File;



public class DataReader {

    //Reads in  and returns all languages from the language folder
    public  ArrayList<Language> readInLanguages() {
        ArrayList<Language> languages = new ArrayList<>();
        URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
        File[] files = new File(location.getPath() + "languageAnalyzer/language").listFiles();

        for(File f : files) {
            String language = f.getName().substring(0, f.getName().indexOf("."));
            String path = f.getPath();
            String content = readFile(path);
            languages.add(new Language(content, language));
        }
        return languages;
    }

    private String readFile(String dir){

        String text = new String();

        try
        {
            //OPEN A FILE WITH SPECIFIC URL
            FileInputStream file = new FileInputStream(dir);

            //FOR STORING ASCII CODE FOR EVERY CHARACTER
            int tempChar;

            //READ TEXT FROM FILE FOR EVERY CHARACTER
            //file.read()RETURNS ASCII CODE RETURNS -1
            //IF EVERYTHING IS READ ALREADY
            while ((tempChar = file.read()) != -1) {

                //ADD CHARACTER TO STRING
                text += String.valueOf((char)tempChar);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File was not found: " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println("IOException occured: " + ex.getMessage());
        }

        return text;
    }

}