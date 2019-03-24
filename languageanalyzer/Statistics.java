package languageAnalyzer;

import java.util.*;

public class Statistics {

    public void analyzeLanguage(Language input, ArrayList<Language> languages) {

        //Stores all the stats to be displayed to the user
        ArrayList<Result> stats = new ArrayList<>();

        for(Language l : languages) {
            String languageName = l.getLanguageLabel();
            double allLettersScore = calcPercentualDiff(input.getAllLetterOccurrences(), l.getAllLetterOccurrences());
            double threeLettersScore = calcPercentualDiff(input.getThreeLetterOccurences(), l.getThreeLetterOccurences());
            double firstLetterScore = calcPercentualDiff(input.getFirstLetterOccurences(), l.getFirstLetterOccurences());

            Result result = new Result(languageName, allLettersScore, threeLettersScore, firstLetterScore);
            stats.add(result);

            Collections.sort(stats, Comparator.comparingDouble(Result::getTotalScore));
        }

        printResults(stats);

    }

    private double calcPercentualDiff(HashMap<String, Double> userIn, HashMap<String, Double> language) {
        double diff = 0;
        for(Map.Entry<String, Double> entry : userIn.entrySet()) {
            if(language.get(entry.getKey())!= null)
                diff += Math.pow(entry.getValue() - language.get(entry.getKey()), 2);
        }
        return diff;
    }

    private void printResults(ArrayList<Result> stats) {

        // Print the list objects in tabular format
        System.out.println("----------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s", "SPRÅK", "ANALYS 1", "ANALYS 2", "ANALYS 3", "KOMBINERAT", "RANK");
        System.out.println();
        System.out.println("----------------------------------------------------");
        for(int i = 0; i < stats.size(); i++) {
            Result r = stats.get(i);
            System.out.format("%-10s %-10f %-10f %-10f %-10f %-10d",
                    r.getLanguage(), r.getAllLettersScore(), r.getThreeLettersScore(), r.getFirstLettersScore(), r.getTotalScore(), i+1);
            System.out.println();
        }
        System.out.println("----------------------------------------------------");

    }

    //Nested data class
    private class Result {

        private String language;
        private double allLettersScore;
        private double threeLettersScore;
        private double firstLettersScore;
        private double totalScore;

        public Result(String language, double allLettersScore, double threeLettersScore, double firstLetterScore) {
            this.language = language;
            this.allLettersScore = allLettersScore;
            this.threeLettersScore = threeLettersScore;
            this.firstLettersScore = firstLetterScore;
            this.totalScore = (allLettersScore + threeLettersScore + firstLetterScore) / 3;
        }

        public double getTotalScore() {
            return this.totalScore;
        }

        public String getLanguage() {
            return language;
        }

        public double getAllLettersScore() {
            return allLettersScore;
        }

        public double getThreeLettersScore() {
            return threeLettersScore;
        }

        public double getFirstLettersScore() {
            return firstLettersScore;
        }
    }



}
