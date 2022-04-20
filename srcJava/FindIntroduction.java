import java.util.Scanner;

public class FindIntroduction {
    public FindIntroduction(Parser parser, Scanner scanner) {
        String str;
        String introduction ="";
        boolean foundIntroduction = false;
        boolean foundAfterIntro = false;

        while (scanner.hasNextLine() && !foundIntroduction) {
            str = scanner.nextLine();

            if ((containsWord(str, "Introduction") || containsWord(str, "INTRODUCTION") || containsWord(str, "1.") || containsWord(str, "I."))) {
                foundIntroduction = true;
            }
        }

        if (foundIntroduction) {
            while (scanner.hasNextLine() && !foundAfterIntro) {
                String scan = scanner.nextLine();
                introduction += scan + "\n";
                if ((containsWord(scan, "2.") || containsWord(scan, "II."))) {
                    foundAfterIntro = true;

                }
            }
//            System.out.println("Introduction+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++: " + introduction);

        } else {
            introduction = null;
//            introduction = "L'introduction n'a pas pu être trouvée";
//            System.out.println("INTRODUCTION : NOPE");
        }
        parser.setIntroduction(introduction);
        //System.out.println("INTRODUCTION : " + introduction);
    }


    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}