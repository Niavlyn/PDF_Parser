import java.util.Scanner;

public class FindDiscussion {

    public FindDiscussion(Parser parser, Scanner scanner) {
        String str;
        String discussion ="";
        boolean foundDiscussion = false;
        boolean foundReferences = false;

        while (scanner.hasNextLine() && !foundDiscussion) {
            str = scanner.nextLine();

            if ((containsWord(str, "Discussion") || containsWord(str, "DISCUSSION"))) {
                foundDiscussion = true;
            }
        }

        if (foundDiscussion) {
            while (scanner.hasNextLine() && !foundReferences) {
                String scan = scanner.nextLine();
                discussion += scan;
                if ((containsWord(scan, "References") || containsWord(scan, "REFERENCES") || containsWord(scan, "Conclusion") || containsWord(scan, "CONCLUSION") )) {
                    foundReferences = true;
                }
            }
        } else {
            discussion = "La discussion n'a pas pu être trouvée";
        }
        parser.setDiscussion(discussion);
    }


    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}