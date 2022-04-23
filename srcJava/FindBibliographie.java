import java.util.Scanner;

public class FindBibliographie {
    public FindBibliographie(Parser parser, Scanner scanner) {
        String str;
        String references ="";
        boolean foundReferences = false;

        while (scanner.hasNextLine() && !foundReferences) {
            str = scanner.nextLine();

            if ((containsWord(str, "References") || containsWord(str, "REFERENCES")) && str.length() < 15) {
                foundReferences = true;
            }
        }

        if (foundReferences) {
            while (scanner.hasNextLine()) {
                references = references + scanner.nextLine();
            }
        } else {
            references = "La bibibliographie n'a pas pu être trouvée";
        }
        parser.setReferences(references);
    }


    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}
