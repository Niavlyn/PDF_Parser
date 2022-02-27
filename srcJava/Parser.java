import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Parser {

    private static String fileName;
    private static String title;
    private static String[] metaAuthorsTab;
    private static String titleMeta;
    private static String authors = "";
    private static String[] authorsTab;
    private static String fileAbstract;


    public Parser(String filePath, String metaPath) {
        readerMeta(metaPath);
        reader(filePath);
        comparator(filePath);
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


            //TODO Récupérer les auteurs
            //TODO Récupérer le résumé
            String str ="";
            boolean foundAbstract = false;
            while (scanner.hasNextLine() && !foundAbstract){
                str = scanner.nextLine();
                if(containsWord(str,"Abstract") || containsWord(str,"ABSTRACT")){
                    foundAbstract = true;
                }
            }
            if(foundAbstract){
                if(str.length() > 9){
                    fileAbstract = str;
                }
                boolean foundIntroduction = false;
                while (scanner.hasNextLine() && !foundIntroduction){
                    str = scanner.nextLine();
                    if (containsWord(str,"Introduction") || containsWord(str,"INTRODUCTION")){
                        foundIntroduction = true;
                    }else{
                        fileAbstract = fileAbstract + str;
                    }
                }
                System.out.println("ABSTRACT : "+ fileAbstract);
            }else{
                System.out.println("Impossible de trouver le résumé.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readerMeta(String metaPath) {
        File file = new File(metaPath);
        try {
            Scanner scanner = new Scanner(file);
            fileName = scanner.nextLine();
            if (scanner.hasNextLine())
                titleMeta = scanner.nextLine();
            if (scanner.hasNextLine()) {
                String allAuthors = scanner.nextLine();
                String[] metaAuthorsTab = allAuthors.split(";");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void comparator(String filePath) {
        if (titleMeta != null && !titleMeta.isEmpty()) {
            if (!title.equals(titleMeta)) {
                if (isInTheText(filePath, titleMeta)) {
                    title = titleMeta;
                }
            }
        }

        boolean useAuthorsTab = false;
        if (metaAuthorsTab.length == authorsTab.length) {
            int i = 0;
            while (i < metaAuthorsTab.length && !useAuthorsTab) {
                if (!metaAuthorsTab[i].equals(authorsTab[i])) {
                    useAuthorsTab = true;
                }
                i++;
            }
            //TODO Chercher les auteurs contenus dans les meta données dans le texte

        } else {
            useAuthorsTab = true;
        }

        if (useAuthorsTab) {
            for (String s : authorsTab) {
                authors = authors + s;
            }
        }

    }

    public static boolean isInTheText(String filePath, String word) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equals(word)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
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

    public static String getAuthors() {
        return authors;
    }

    public static String getFileAbstract() {
        return fileAbstract;
    }
}
