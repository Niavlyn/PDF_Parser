import java.util.Scanner;

public class FindNomAuteur {

    public FindNomAuteur(Parser parser) {
        String authors ="";
        if(parser.getAuthorsTab()[0].equals("")) {
            if(parser.getEmail() != null) {
                String[] emails = parser.getEmail().split(";");
                String[] noms = new String[emails.length];
                String[] prenoms = new String[emails.length];
                String[] authorTab = new String[emails.length];
                for(int i = 0; i < emails.length; i++) {
//                System.out.println(emails[i]);
                    String nomPrenom = emails[i].split("@")[0];

                    //System.out.println("Prenom : " + nomPrenom.split("\\.")[0]);
                    prenoms[i] = nomPrenom.split("\\.")[0];
                    try {
                        noms[i] = nomPrenom.split("\\.")[1];
                    } catch (Exception ignored){}
                    if(noms[i] != null) {
                        authorTab[i] = prenoms[i] + " " + noms[i];
                        //authors += prenoms[i] + " " + noms[i] + " ; ";
                    } else {
                        //authors += prenoms[i]+ " ; ";
                        authorTab[i] = prenoms[i];
                    }


                }
                parser.setAuthorsTab(authorTab);
                //parser.setAuthors(authors);
            }
        }


    }


    public FindNomAuteur(){}

    private boolean containsWord(String str, String word) {
        int i = str.indexOf(word);
        return i >= 0;
    }

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
}