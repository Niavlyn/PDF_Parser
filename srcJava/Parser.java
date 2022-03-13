import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private String fileName = "";
    private String title = "";
    private String[] metaAuthorsTab = {""};
    private String titleMeta = "";
    private String authors = "";
    private String fileAbstract = "";
    private String references = "";
    private String email = "";


    public Parser(String filePath, String metaPath) {
        readerMeta(filePath, metaPath);
        reader(filePath, metaPath);
        findEmail(filePath);
        comparator(filePath);
    }

    public void reader(String filePath, String fileMeta) {

        System.out.println("FILENAME " + filePath);
        System.out.println("FILENAME META " + fileMeta);

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            //************************************************************************************************//
            //RECUPERATION DU TITRE

            if (scanner.hasNextLine()) {
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
                        fileAbstract = "Le résumé n'a pas pu être trouvé.";
                    }


                    System.out.println("ABSTRACT : " + fileAbstract);
                } else {
                    fileAbstract = "Le résumé n'a pas pu être trouvé.";
                    System.out.println("ABSTRACT : NOPE");
                }


                //************************************************************************
                //RECUPERATION DE LA BIBLIOGRAPHIE

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
                    System.out.println("BIBLIOGRAPHIE : " + references);

                } else {
                    references = "La bibibliographie n'a pas pu être trouvée";
                    System.out.println("BIBLIOGRAPHIE : NOPE");
                }
            } else {
                System.out.println("/!\\ --- LE FICHIER TXT EST VIDE ! --- /!\\");
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findEmail(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            //[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+
            while (scanner.hasNextLine()){
                Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(scanner.nextLine());
                while (m.find()) {
                    if (email.isBlank()){
                        email = m.group();
                    }else {
                        email = email + ";" + m.group();
                    }
                }
            }

            if (!email.isBlank()){
                System.out.println("EMAILS : " + email);
            }else{
                email = "Impossible de trouver les emails des auteurs";
                System.out.println("EMAILS : NOPE");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readerMeta(String filePath, String metaPath) {
        if (!metaPath.equals("null")) {
            File file = new File(metaPath);
            try {
                Scanner scanner = new Scanner(file);
                fileName = scanner.nextLine();
                if (scanner.hasNextLine())
                    titleMeta = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    String allAuthors = scanner.nextLine();
                    metaAuthorsTab = allAuthors.split(";");
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String splitFichierTxtName = filePath.split("Corpus_2022_txt")[1];
            fileName = splitFichierTxtName.substring(1, splitFichierTxtName.length() - 4);
        }
        System.out.println();
        System.out.println("#########################################################################");
        System.out.println();
        System.out.println("NOM DU FICHIER : " + fileName);
    }

    public void comparator(String filePath) {
        if (titleMeta != null && !titleMeta.isEmpty()) {
            if (!title.equals(titleMeta)) {
                if (isInTheText(filePath, titleMeta)) {
                    title = titleMeta;
                }
            }
        }
        /*
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
        */
        if (metaAuthorsTab.length > 0) {
            for (String s : metaAuthorsTab) {
                if (authors.isBlank()){
                    authors = authors + s;
                }else{
                    authors = authors + ";"+ s;
                }
            }
            if (authors.isBlank()) {
                authors = "Aucun auteur n'a pu être trouvé.";
                System.out.println("AUTEURS : NOPE");
            } else {
                System.out.println("AUTEURS : " + authors);
            }
        } else {
            authors = "Aucun auteur n'a pu être trouvé.";
            System.out.println("AUTEURS : NOPE");
        }


    }

    public boolean isInTheText(String filePath, String word) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equals(word)) {
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
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

    public String getFileName() {
        return fileName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getFileAbstract() {
        return fileAbstract;
    }

    public String getReferences() {
        return references;
    }

    public String getEmail() {
        return email;
    }
}
