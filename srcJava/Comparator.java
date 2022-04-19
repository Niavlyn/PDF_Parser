import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Comparator {
    public Comparator(Parser parser, String filePath) {
        String titleMeta = parser.getTitleMeta();
        String title = parser.getTitle();
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
        String[] metaAuthorsTab = parser.getAuthorsTab();
        String authors = parser.getAuthors();
        if (metaAuthorsTab.length > 0) {
            for (String s : metaAuthorsTab) {
                if (authors.isBlank()) {
                    authors = authors + s;
                } else {
                    authors = authors + ";" + s;
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

        parser.setTitleMeta(titleMeta);
        parser.setTitle(title);
        parser.setAuthorsTab(metaAuthorsTab);
        parser.setAuthors(authors);

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
}
