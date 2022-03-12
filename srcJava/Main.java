import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


public class Main {
    private ArrayList<String> fichiersTxt = new ArrayList<>();
    private ArrayList<String> fichiersMeta = new ArrayList<>();
    private boolean withXML;
    private boolean withText;


    public static void main(String args[]) throws IOException, ParserConfigurationException, TransformerException {
        System.out.println(args[0] + "----------------------------------------------------");

        Main main = new Main();
        if(args[0].equals("-t")) {
            main.withText = true;
            main.withXML = false;
        } else if(args[0].equals("-x")) {
            main.withText = false;
            main.withXML = true;
        } else {
            main.withText = true;
            main.withXML = true;
        }

        File currentDirectory = new File(System.getProperty("user.dir"));
        File dossierTxt = new File(currentDirectory + "/Corpus_2022_txt");
        File dossierMeta = new File(currentDirectory + "/Corpus_2022_meta");
        File dossierFinalProdAbsolu = new File(currentDirectory + "/FinalProduction");


        //Place dans un tableau l'ensemble des fichiers des dossiers
        //System.out.println(Arrays.toString(dossierTxt.list()));


        //final Stream<Path> stream = Files.list(dirStream);

        File[] listeFichiersTxt = dossierTxt.listFiles();
        File[] listeFichiersMeta = dossierMeta.listFiles();
        
        //Création d'un tableau contenant les noms en Strng des fichiers


        //Remplissage des tableaux
        for(File file : listeFichiersTxt) {
            main.fichiersTxt.add(file.getAbsolutePath().toString());
        }

        for(File file : listeFichiersMeta){
            main.fichiersMeta.add(file.getAbsolutePath().toString());
        }


        for(int i = 0 ; i < main.fichiersTxt.size() ; i++){
            //System.out.println("fichier txt : " + main.fichiersTxt.get(i) + '\n');
            String splitFichierTxtName = " ";
            splitFichierTxtName = main.fichiersTxt.get(i).split("Corpus_2022_txt")[1];
            splitFichierTxtName = splitFichierTxtName.substring(1,splitFichierTxtName.length()-4);

            int result = main.compareText(splitFichierTxtName);
            if(result != -1) {
                //System.out.println("fichier meta : " + main.fichiersMeta.get(result));
                Parser fichier = new Parser(main.fichiersTxt.get(i), main.fichiersMeta.get(result));
                if(main.withText){ OutputWriter ecriture = new OutputWriter(dossierFinalProdAbsolu.toString(), fichier.getFileName(), fichier.getTitle(), fichier.getFileAbstract(), fichier.getAuthors(), fichier.getReferences());}
                if(main.withXML){OutputWriterXML ecritureXML = new OutputWriterXML(dossierFinalProdAbsolu.toString(), fichier.getFileName(), fichier.getTitle(), fichier.getFileAbstract(), fichier.getAuthors(), fichier.getReferences());}
            } else {
                Parser fichier = new Parser(main.fichiersTxt.get(i), "null");
                if(main.withText){ OutputWriter ecriture = new OutputWriter(dossierFinalProdAbsolu.toString(), fichier.getFileName(), fichier.getTitle(), fichier.getFileAbstract(), fichier.getAuthors(), fichier.getReferences());}
                if(main.withXML){OutputWriterXML ecritureXML = new OutputWriterXML(dossierFinalProdAbsolu.toString(), fichier.getFileName(), fichier.getTitle(), fichier.getFileAbstract(), fichier.getAuthors(), fichier.getReferences());}
            }
        }
    }

    private int compareText(String txtName) {
        int compare = -1;
        String split = "";
        for(int i = 0; i < fichiersMeta.size(); i++) {
            split = fichiersMeta.get(i).split("Corpus_2022_meta")[1];
            split = split.substring(1,split.length()-9);
            if(split.equals(txtName)) {
                compare = i;
                break;
            }
        }

        return compare;
    }
}