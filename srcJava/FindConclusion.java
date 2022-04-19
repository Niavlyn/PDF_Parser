import java.util.Scanner;

public class FindConclusion {
    public FindConclusion(Parser parser, Scanner scanner) {
        String str;
        String conclusion ="";
        boolean foundConclusion = false;
        boolean foundReferences = false;

        while (scanner.hasNextLine() && !foundConclusion) {
            str = scanner.nextLine();

            if ((containsWord(str, "Conclusion") || containsWord(str, "CONCLUSION")) && str.length() < 15) {
                foundConclusion = true;
            }
        }

        if (foundConclusion) {
            while (scanner.hasNextLine() && !foundReferences) {
                String scan = scanner.nextLine();
                conclusion = conclusion + scan;
                if ((containsWord(scan, "References") || containsWord(scan, "REFERENCES"))) {
                    foundReferences = true;
                }
            }
//            System.out.println("Conclusion+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++: " + conclusion);

        } else {
            conclusion = "La conclusion n'a pas pu être trouvée";
//            System.out.println("CONCLUSION : NOPE");
        }
        parser.setConclusion(conclusion);
    }


    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}