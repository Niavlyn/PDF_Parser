import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private static String fileName;
    private static String title;
    private static ArrayList<String> authors;
    private static ArrayList<String> fileAbstract;


    public Parser(String filePath, String metaPath) {
        reader(filePath);
    }

    public static void reader(String filePath) {

        System.out.println("********************************************************\n\n");
        System.out.println("FILENAME " + filePath);
        System.out.println("********************************************************");

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            //************************************************************************************************//
            //RECUPERATION DU TITRE

            title = scanner.nextLine();

            if (Character.isUpperCase(title.charAt(0)) && !isAllUpper(title)) {
                String line2 = scanner.nextLine();
                if (!line2.equals("\n"))
                    title = title + " " + line2;
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

            System.out.println("TITRE : " + title);

            //************************************************************************************************//
            //RECUPERATION DES AUTEURS










        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isAllUpper(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) && Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }

    public static String getFileName() {
        return fileName;
    }

    public static String getTitle() {
        return title;
    }

    public static ArrayList<String> getAuthors() {
        return authors;
    }

    public static ArrayList<String> getFileAbstract() {
        return fileAbstract;
    }
}
