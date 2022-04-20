import java.util.Scanner;

public class FindCorps {
    public FindCorps(Parser parser, Scanner scanner) {
        String str;
        String corps ="";
        boolean startReading = false;
        boolean foundConclusionOrDiscussion = false;

        while (scanner.hasNextLine() && !foundConclusionOrDiscussion) {

            str = scanner.nextLine();
            corps += str + "\n";

            //if (containsWord(str, "Conclusion"))

            if (containsWord(str, "Conclusion") || containsWord(str, "CONCLUSION") || containsWord(str, "Discussion") || containsWord(str, "DISCUSSION")  ) {
                foundConclusionOrDiscussion = true;
            }
        }

        if(!foundConclusionOrDiscussion) {
            corps = "Le corps n'a pas pu être trouvé";
        }
        //System.out.println("CORPS : " + corps);
        parser.setCorps(corps);

    }

    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}