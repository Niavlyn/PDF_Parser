import java.util.Scanner;

public class FindTitle {

    public FindTitle(Parser parser) {
        String title = parser.getTitle();
        Scanner scanner = parser.getScanner();
        if (scanner.hasNextLine()) {
            title = scanner.nextLine();

            if (Character.isUpperCase(title.charAt(0)) && !isAllUpper(title)) {
                String line2 = scanner.nextLine();
                if (!line2.equals("\n"))
                    title = (title + " " + line2);
            } else {
                String nextLine = scanner.nextLine();
                while (nextLine.length() == 0) {
                    nextLine = scanner.nextLine();
                }
                title = nextLine;
                String line2 = scanner.nextLine();
                if (!line2.equals("\n"))
                    title = title + " " + line2;
                if (containsWord(title, "Communicated") && containsWord(title, "by")) {
                    String nextLine2 = scanner.nextLine();
                    while (nextLine2.length() == 0) {
                        nextLine2 = scanner.nextLine();
                    }
                    title = nextLine2;
                    String line22 = scanner.nextLine();
                    if (!line22.equals("\n"))
                        title = title + " " + line22;
                }
            }
        }
        parser.setTitle(title);
    }

    private boolean isAllUpper(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) && Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }
}
