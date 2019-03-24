package languageAnalyzer;


import java.util.HashMap;

public class Data {

    private String languageLabel = "";
    private HashMap<String, Double> firstLetterOccurences;
    private HashMap<String, Double> allLetterOccurrences;
    private HashMap<String, Double> threeLetterOccurences;

    public Data(String content, String languageLabel){
        this.languageLabel = languageLabel;
        this.firstLetterOccurences = generateFirstLetterOccurrences(content);
        this.threeLetterOccurences = generateThreeLetterOccurrences(content);
        this.allLetterOccurrences = generateAllLetterOccurrences(content);
    }

    //Used for the user input
    public Data(String content){
        this.firstLetterOccurences = generateFirstLetterOccurrences(content);
        this.threeLetterOccurences = generateThreeLetterOccurrences(content);
        this.allLetterOccurrences = generateAllLetterOccurrences(content);
    }

    private HashMap<String, Double> generateAllLetterOccurrences(String text) {
        text = format(text);
        double textLength = text.length();
        HashMap<String, Double> occurrences = new HashMap<>();

        String[] letters = text.split("");

        for(String s : letters) {
            Double occurrence = occurrences.get(s);
            if (occurrence!= null) {
                occurrences.put(s, occurrence + (1/textLength));
            } else{
                occurrences.put(s, 1/textLength);
            }
        }
        return occurrences;
    }

    private HashMap<String, Double> generateThreeLetterOccurrences(String text) {
        text = format(text);
        HashMap<String, Double> occurrences = new HashMap<>();
        int textLength = text.length();

        for(int i = 0; i < textLength - 2; i++) {
            String threeChars = text.substring(i, i+3);
            Double percentage = occurrences.get(threeChars);
            if(percentage != null)
                occurrences.put(threeChars, percentage + (1.0/(textLength-2)));
            else
                occurrences.put(threeChars, 1.0/(textLength-2));
        }
        return occurrences;
    }

    private HashMap<String, Double> generateFirstLetterOccurrences(String text) {
        HashMap<String, Double> occurrences = new HashMap<>();
        String[] words = text.split(" ");

        for(String word : words) {
            String firstLetter = String.valueOf(word.charAt(0));
            Double percentage = occurrences.get(firstLetter);
            if(percentage != null)
                occurrences.put(firstLetter, percentage + (1.0/words.length));
            else
                occurrences.put(firstLetter, 1.0/words.length);
        }
        return occurrences;
    }

    public String getLanguageLabel() {
        return languageLabel;
    }

    public HashMap<String, Double> getFirstLetterOccurences() {
        return firstLetterOccurences;
    }

    public HashMap<String, Double> getAllLetterOccurrences() {
        return allLetterOccurrences;
    }

    public HashMap<String, Double> getThreeLetterOccurences() {
        return threeLetterOccurences;
    }

    private String format(String text){

        String formatted = text.replaceAll("\\(","")
                .replaceAll("\\]","")
                .replaceAll("\\¸","")
                .replaceAll("\\s+", "")
                .replaceAll("\\)","")
                .replaceAll("\\.", "")
                .replaceAll("\\,","")
                .replaceAll("\\.","")
                .replaceAll("\\[","")
                .replaceAll("\\‰","")
                .replaceAll("\\ˆ","")
                .replaceAll("\\*","")
                .replaceAll("\\:","")
                .replaceAll("\"", "")
                .toLowerCase();

        return formatted;
    }
}