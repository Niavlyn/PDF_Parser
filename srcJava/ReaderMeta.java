import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderMeta {

    public ReaderMeta(Parser parser, String filePath, String metaPath) {
        if (!metaPath.equals("null")) {
            File file = new File(metaPath);
            try {
                Scanner scanner = new Scanner(file);
                parser.setFileName(scanner.nextLine());
                if (scanner.hasNextLine())
                    parser.setTitleMeta(scanner.nextLine());
                if (scanner.hasNextLine()) {
                    String allAuthors = scanner.nextLine();
                    parser.setAuthorsTab(allAuthors.split(";"));
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String splitFichierTxtName = filePath.split("Corpus_2022_txt")[1];
            parser.setFileName(splitFichierTxtName.substring(1, splitFichierTxtName.length() - 4));
        }
    }
}
