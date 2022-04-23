import java.util.Scanner;

public class FindAbstract {

    public FindAbstract(Parser parser, Scanner scanner) {
        String fileAbstract = parser.getFileAbstract();
        String str = "";
        boolean foundAbstract = false;

        while (scanner.hasNextLine() && !foundAbstract) {
            str = scanner.nextLine();
            if (containsWord(str, "Abstract") || containsWord(str, "ABSTRACT")) {
                foundAbstract = true;
            }
        }
        if (foundAbstract) {
            if (str.length() > 9) {
                fileAbstract = str;
            }
            boolean foundIntroduction = false;
            while (scanner.hasNextLine() && !foundIntroduction) {
                str = scanner.nextLine();
                if (containsWord(str, "Introduction") || containsWord(str, "INTRODUCTION")) {
                    foundIntroduction = true;
                } else {
                    fileAbstract = fileAbstract + str;
                }
            }
            if (fileAbstract.length() > 3000) {
                fileAbstract = null;
            }
        } else {
            fileAbstract = null;
        }
        parser.setFileAbstract(fileAbstract);
    }

    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}
