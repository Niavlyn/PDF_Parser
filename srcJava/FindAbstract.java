import java.util.Scanner;

public class FindAbstract {

    public FindAbstract(Parser parser, Scanner scanner) {
        String fileAbstract = parser.getFileAbstract();
        String str = "";
        boolean foundAbstract = false;

        while (scanner.hasNextLine() && !foundAbstract) {
            str = scanner.nextLine();
            if (containsWord(str, "Abstract") || containsWord(str, "ABSTRACT") || containsWord(str, "a b s t r a c t")){
                foundAbstract = true;
            }
        }

        boolean enter = false;

        if (foundAbstract) {
            if (str.length() > 9) {
                fileAbstract = str;
            }
            boolean foundIntroduction = false;
            while (scanner.hasNextLine() && !foundIntroduction) {
                str = scanner.nextLine();
                if (containsWord(str, "Introduction") || containsWord(str, "INTRODUCTION") || (containsWord(str, "1.") || containsWord(str, "I.") || containsWord(str, "1 ") && enter) ){
                    foundIntroduction = true;
                }else if(str.isBlank()){
                    enter = true;
                }
                else {
                    fileAbstract = fileAbstract + str;
                    enter = false;
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
