import jdk.swing.interop.SwingInterOpUtils;

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

    public Comparator() {}

    /**
     * Retourne le nom/prénom correspondant à un mail
     * @param mail - un seul mail
     * @param auteurs - tous les auteurs
     * @return
     */
    public String compareMailAndName(String mail, String auteurs){
        //System.out.println("COMPARE MAIL AND NAMEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //Aucun auteur n'a pu être trouvé.
        //Impossible de trouver les emails des auteurs
        if(!mail.equals("Impossible de trouver les emails des auteurs")){
            String[] mailSplit = mail.split("@");
            String frontPart = mailSplit[0];
            frontPart = frontPart.toUpperCase();
//            System.out.println("FRONT PART : " + frontPart);
            if (!auteurs.equals("Aucun auteur n'a pu être trouvé.")){
                String[] auteursSpliTab = auteurs.split(";");
                int i = 0;
                for (String s: auteursSpliTab) {
                    auteursSpliTab[i] = s.toUpperCase();
//                    System.out.println("Auteurs Split Tab[i] : " + auteursSpliTab[i]);
                    i++;
                }

                String[] frontPartSplit = frontPart.split("\\.");

                if (frontPartSplit.length > 1) {

//                    String nameV1 = frontPartSplit[0] + " " + frontPartSplit[1];
//                    String nameV2 = frontPartSplit[1] + " " + frontPartSplit[0];
                    //Moins opti pour + de resultats
                    String nameV1 = frontPartSplit[0];
                    String nameV2 = frontPartSplit[1];
//                    System.out.println("NAME V1 : " + nameV1);
//                    System.out.println("NAME V2 : " + nameV2);

                    for (int j = 0; j < auteursSpliTab.length; j++) {
                        if(auteursSpliTab[j].contains(nameV1) || auteursSpliTab[j].contains(nameV2)) {
                            return nameV1 + " " + nameV2;
                        }
//                        if (nameV1.equals(auteursSpliTab[j])) {
//                            auteursSpliTab[j] = null;
//                            return nameV1;
//                        } else if (nameV2.equals(auteursSpliTab[j])) {
//                            auteursSpliTab[j] = null;
//                            return nameV2;
//                        }
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
        //System.out.println("IS IN THE KEY ???????????????????");
        String nameV1 ="";
        String nameV2 = "";
        boolean contain = false;
//        System.out.println("KEY : " + key);
//        System.out.println("NOM AUTEUR : " + nomAuteur);
        if(key.equalsIgnoreCase(nomAuteur)) {
            contain = true;
        }
//        try {
//            nameV1 = nomAuteur.split(" ")[0];
//            nameV2 = nomAuteur.split(" ")[1];
//        } catch (IndexOutOfBoundsException ignored){}
//
//        if(!nameV1.equals("") && !nameV2.equals("")) {
//            if(containsWord(key, nameV1) || containsWord(key, nameV2)) {
//                System.out.println("NAME V1 : " + nameV1);
//                System.out.println("NAME V2 : " + nameV2);
//                System.out.println("KEY : " + key);
//                System.out.println("TRUUUUUUUUUUUUUUUUUUUUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE CAS1");
//                contain = true;
//            }
//        } else if(!nameV1.equals("")) {
//            if(containsWord(key, nameV1)) {
//                System.out.println("NAME V1 : " + nameV1);
//                System.out.println("NAME V2 : " + nameV2);
//                System.out.println("KEY : " + key);
//                System.out.println("TRUUUUUUUUUUUUUUUUUUUUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE CAS2");
//                contain = true;
//            }
//        } else {
//            if(containsWord(key, nameV2)) {
//                System.out.println("\n\n");
//                System.out.println("NAME V1 : " + nameV1);
//                System.out.println("NAME V2 : " + nameV2);
//                System.out.println("KEY : " + key);
//                System.out.println("TRUUUUUUUUUUUUUUUUUUUUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE CAS3");
//                System.out.println("\n\n");
//                contain = true;
//            }
//        }

        return contain;
    }

    private boolean containsWord(String str, String word) {


        int i = str.indexOf(word);
        if(i >=0) {
            System.out.println("CONTAINS WORD");
            System.out.println("STR : " + str);
            System.out.println("WORD : " + word);
        }
        return i >= 0;
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
