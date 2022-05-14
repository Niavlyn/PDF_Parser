import java.util.Scanner;

public class FindIntroduction {
    public FindIntroduction(Parser parser, Scanner scanner) {
        Scanner secondScanner = scanner;
        String str;
        String introduction = "";
        boolean foundIntroduction = false;
        boolean foundAfterIntro = false;

        boolean enter = false;
        boolean enter2 = false;

        while (scanner.hasNextLine() && !foundIntroduction) {
            str = scanner.nextLine();

            if (containsWord(str, "Introduction") || containsWord(str, "INTRODUCTION")){
                foundIntroduction = true;
            }
        }

        scanner = secondScanner;

        while (scanner.hasNextLine() && !foundIntroduction) {
            str = scanner.nextLine();

            if (((str.contains("1.") && str.charAt(0) == '1') || str.contains(" 1. ")|| (str.contains("I.") && str.charAt(0) == 'I') || str.contains(" I. ") || (str.contains("1 ") && str.charAt(0) == '1')) && enter){
                foundIntroduction = true;
            }else if(str.isBlank()){
                enter = true;
            }
        }

        if (foundIntroduction) {
            while (scanner.hasNextLine() && !foundAfterIntro) {
                String scan = scanner.nextLine();
                introduction += scan;
                if (((scan.contains("2.") && scan.charAt(0) == '2') || scan.contains(" 2. ")|| (scan.contains("II.") && scan.charAt(0) == 'I') || scan.contains(" II. ") || (scan.contains("2 ") && scan.charAt(0) == '2')) && enter2) {
                    foundAfterIntro = true;
                }else if(scan.isBlank()){
                    enter2 = true;
                }
            }
        } else {
            introduction = null;
        }
        parser.setIntroduction(introduction);
    }


    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}