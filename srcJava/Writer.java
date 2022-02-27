import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

    public Writer(String path, String nomFichier, String titre, String abstracts, String auteurs) {
        writeInFile(path, nomFichier, titre, abstracts, auteurs);
    }

    private void writeInFile(String path, String nomFichier, String titre, String abstracts, String auteurs) {
        try {
            FileWriter myWriter = new FileWriter(path + "/" + nomFichier);
            myWriter.write(nomFichier + "\n");
            myWriter.write(titre + "\n");
            myWriter.write(auteurs + "\n");
            myWriter.write(abstracts);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}