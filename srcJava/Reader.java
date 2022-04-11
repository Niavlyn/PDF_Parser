import java.io.File;
import java.util.Scanner;

public class Reader {

    public Reader(Parser parser, String filePath, String fileMeta) {

        System.out.println("FILENAME " + filePath);
        System.out.println("FILENAME META " + fileMeta);

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            //findTitle(scanner);
            //System.out.println("TITRE : " + title);
            //parser.setAbstracts(findAbstract(scanner));
            //findBibliographie(scanner, str);


            scanner.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

}
