import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String args[]) throws IOException {
        //Récupération des chemins relatifs des dossiers nécessaires
        File dossierMetaRel = new File("..\\Corpus_2022_meta");
        File dossierTxtRel = new File("..\\Corpus_2022_txt");
        File dossierFinalProdRel = new File("..\\FinalProduction");

        //Récupération des chemins absolus
        String dossierStringTxt = dossierTxtRel.getAbsolutePath().toString().replace("\\..", "");
        File dossierTxt = new File(dossierStringTxt);
        String dossierStringMeta = dossierMetaRel.getAbsolutePath().toString().replace("\\..", "");
        File dossierMeta = new File(dossierStringMeta);
        String dossierFinalProdAbsolu = dossierFinalProdRel.getAbsolutePath();

        String test1 = dossierTxt.toString();
        String test2 = test1.split("Corpus_2022_txt")[0];
        test1 = test2 + "\\PDF_Parser\\Corpus_2022_txt";
        dossierTxt = new File(test1);
        System.out.println(dossierTxt);

        String test3 = dossierMeta.toString();
        String test4 = test3.split("Corpus_2022_meta")[0];
        test3 = test4 + "\\PDF_Parser\\Corpus_2022_meta";
        dossierMeta = new File(test3);
        System.out.println(dossierMeta);

        //Place dans un tableau l'ensemble des fichiers des dossiers
        //System.out.println(Arrays.toString(dossierTxt.list()));


        //final Stream<Path> stream = Files.list(dirStream);

        File[] listeFichiersTxt = dossierTxt.listFiles();
        File[] listeFichiersMeta = dossierMeta.listFiles();
        
        //Création d'un tableau contenant les noms en Strng des fichiers
        ArrayList<String> fichiersTxt = new ArrayList<>();        
        ArrayList<String> fichiersMeta = new ArrayList<>();

        //Remplissage des tableaux
        for(File file : listeFichiersTxt) {
            fichiersTxt.add(file.getAbsolutePath().toString());
        }

        for(File file : listeFichiersMeta){
            fichiersMeta.add(file.getAbsolutePath().toString());
        }


        for(int i = 0 ; i < fichiersMeta.size() ; i++){
            //System.out.println("fichier meta : " + fichiersMeta.get(i));
            //System.out.println("fichier txt : " + fichiersTxt.get(i) + '\n');
            Parser fichier = new Parser(fichiersTxt.get(i), fichiersMeta.get(i));

            //Writer ecriture = new Writer(dossierFinalProdAbsolu, fichier.getFileName(), fichier.getTitle(), fichier.getAbstract(), fichier.getAuthors());
        }
    }
}