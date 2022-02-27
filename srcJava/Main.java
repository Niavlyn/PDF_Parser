import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    private ArrayList<String> fichiersTxt = new ArrayList<>();
    private ArrayList<String> fichiersMeta = new ArrayList<>();


    public static void main(String args[]) throws IOException {

        Main main = new Main();
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
                OutputWriter ecriture = new OutputWriter(dossierFinalProdAbsolu.toString(), fichier.getFileName(), fichier.getTitle(), fichier.getFileAbstract(), fichier.getAuthors());
            } else {
                Parser fichier = new Parser(main.fichiersTxt.get(i), "null");
                OutputWriter ecriture = new OutputWriter(dossierFinalProdAbsolu.toString(), fichier.getFileName(), fichier.getTitle(), fichier.getFileAbstract(), fichier.getAuthors());
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