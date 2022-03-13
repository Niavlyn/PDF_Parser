import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {

    public OutputWriter(String path, String nomFichier, String titre, String abstracts, String auteurs, String references, String emails) {
        writeInFile(path, nomFichier, titre, abstracts, auteurs,references, emails);
    }

    private void writeInFile(String path, String nomFichier, String titre, String abstracts, String auteurs, String references, String emails) {
//        System.out.println(path + " " + nomFichier + " " + titre + " " + abstracts + " " + auteurs);
        try {
            FileWriter myWriter = new FileWriter(path + "/" + nomFichier + ".txt");
            myWriter.write(nomFichier + "\n");
            myWriter.write(titre + "\n");
            myWriter.write(auteurs + "\n");
            myWriter.write(emails + "\n");
            myWriter.write(abstracts + "\n");
            myWriter.write(references);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}