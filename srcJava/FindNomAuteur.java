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


}