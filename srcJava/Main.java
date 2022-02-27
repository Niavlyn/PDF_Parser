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


        File currentDirectory = new File(System.getProperty("user.dir"));
        File dossierTxt = new File(currentDirectory + "/Corpus_2022_txt");
        File dossierMeta = new File(currentDirectory + "/Corpus_2022_meta");


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
            System.out.println("fichier meta : " + fichiersMeta.get(i));
            System.out.println("fichier txt : " + fichiersTxt.get(i) + '\n');
            //Parser fichier = new Parser(fichiersTxt.get(i), fichiersMeta.get(i));

            //Writer ecriture = new Writer(dossierFinalProdAbsolu, fichier.getFileName(), fichier.getTitle(), fichier.getAbstract(), fichier.getAuthors());
        }
    }
}