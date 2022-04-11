import java.io.File;
import java.util.Scanner;
public class Parser {

    private String fileName = "";
    private String title = "";
    private String[] metaAuthorsTab = {""};
    private String titleMeta = "";
    private String authors = "";
    private String fileAbstract = "";
    private String references = "";
    private String email = "";
    private String abstracts = "";
    private Scanner scanner;


    public Parser(String filePath, String metaPath) {
        new ReaderMeta(this, filePath, metaPath);

        reader(filePath, metaPath);
        new Comparator(this, filePath);
    }

    public void reader(String filePath, String fileMeta) {

        System.out.println("FILENAME " + filePath);
        System.out.println("FILENAME META " + fileMeta);

        try {
            File file = new File(filePath);
            this.scanner = new Scanner(file);
            new FindTitle(this);
            new FindAbstract(this, scanner);
            System.out.println("TITRE : " + title);
            new FindBibliographie(this, scanner);
            new FindEmail(this, filePath);


            scanner.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleMeta() {
        return this.titleMeta;
    }

    public void setTitleMeta(String titleMeta) {
        this.titleMeta = titleMeta;
    }

    public String[] getMetaAuthorTab() {
        return this.metaAuthorsTab;
    }

    public void setMetaAuthorsTab(String[] metaAuthorsTab) {
        this.metaAuthorsTab = metaAuthorsTab;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getFileAbstract() {
        return fileAbstract;
    }

    public void setFileAbstract(String fileAbstract) {
        this.fileAbstract = fileAbstract;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbstracts() {
        return this.abstracts;
    }

    public void setAbstracts(String abs) {
        this.abstracts = abs;
    }


}
