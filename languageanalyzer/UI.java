package languageAnalyzer;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public static void CLI(){

        DataReader reader = new DataReader();
        Statistics statistics = new Statistics();

        ArrayList<Language> languages = reader.readInLanguages();
        Scanner input = new Scanner(System.in);

        boolean quit = false;

        while(!quit){
            System.out.println("\n");
            System.out.println("-----------------");
            System.out.println("Speech Analyzer");
            System.out.println("-----------------");
            System.out.println("1. Analyze Text");
            System.out.println("2. Exit");

            int selected = input.nextInt();

            switch(selected) {
                case 1:
                    System.out.print("Enter Your Sentence Here: ");
                    Scanner read = new Scanner(System.in, "ISO-8859-1");
                    String userInput = read.nextLine();
                    Language inputLanguage = new Language(userInput);
                    statistics.analyzeLanguage(inputLanguage, languages);
                    break;
                case 2:
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid Selection");
                    break;
            }

        }
    }
}