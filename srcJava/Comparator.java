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
            }
        } else {
            authors = "Aucun auteur n'a pu être trouvé.";
        }

        parser.setTitleMeta(titleMeta);
        parser.setTitle(title);
        parser.setAuthorsTab(metaAuthorsTab);
        parser.setAuthors(authors);
    }

    public Comparator() {}

    /**
     * Retourne le nom/prénom correspondant à un mail
     * @param mail - un seul mail
     * @param auteurs - tous les auteurs
     * @return Retourne le nom/prénom correspondant à un mail
     */
    public String compareMailAndName(String mail, String auteurs){

        if(!mail.equals("Impossible de trouver les emails des auteurs")){
            String[] mailSplit = mail.split("@");
            String frontPart = mailSplit[0];
            frontPart = frontPart.toUpperCase();
            if (!auteurs.equals("Aucun auteur n'a pu être trouvé.")){
                String[] auteursSpliTab = auteurs.split(";");
                int i = 0;
                for (String s: auteursSpliTab) {
                    auteursSpliTab[i] = s.toUpperCase();
                    i++;
                }

                String[] frontPartSplit = frontPart.split("\\.");

                if (frontPartSplit.length > 1) {

                    //Moins opti pour + de resultats
                    String nameV1 = frontPartSplit[0];
                    String nameV2 = frontPartSplit[1];

                    for (int j = 0; j < auteursSpliTab.length; j++) {
                        if (auteursSpliTab[j].contains(nameV1) || auteursSpliTab[j].contains(nameV2)) {
                            return nameV1 + " " + nameV2;
                        }
                    }
                } else if(!auteurs.contains("@")){
                    String[] test = auteurs.split(";");
                    for(int j = 0 ; j < test.length ; j++){
                        if((test[j].toUpperCase()).contains(frontPart)){
                            return test[j];
                        }
                    }
                }
                return "No match found";

            }else {
                return "No authors";
            }
        }
        return "Mail null";
    }

    public boolean isInTheKey(String key, String nomAuteur) {
        boolean contain = false;
        if(key.equalsIgnoreCase(nomAuteur)) {
            contain = true;
        }
        return contain;
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
