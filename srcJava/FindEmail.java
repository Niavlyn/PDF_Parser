import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindEmail {
    public FindEmail(Parser parser, String filePath) {
        String email = "";
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            //[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+
            while (scanner.hasNextLine()) {
                Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(scanner.nextLine());
                while (m.find()) {
                    if (email.isBlank()) {
                        email = m.group();
                    } else {
                        email = email + ";" + m.group();
                    }
                }
            }

            if (!email.isBlank()) {
                System.out.println("EMAILS : " + email);
            } else {
                email = "Impossible de trouver les emails des auteurs";
                System.out.println("EMAILS : NOPE");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        parser.setEmail(email);
    }




}
