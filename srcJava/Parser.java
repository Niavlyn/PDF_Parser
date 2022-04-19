import java.io.File;
import java.util.Scanner;
public class Parser {

    private String fileName = "";
    private String title = "";
    private String[] AuthorsTab = {""};
    private String titleMeta = "";
    private String authors = "";
    private String fileAbstract = "";
    private String references = "";
    private String email = "";
    private String abstracts = "";
    private Scanner scanner;
    private String conclusion;
    private String introduction;


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
            this.scanner = new Scanner(file);
            new FindAbstract(this, scanner);
            this.scanner = new Scanner(file);
            System.out.println("TITRE : " + title);
            new FindBibliographie(this, scanner);
            this.scanner = new Scanner(file);
            new FindEmail(this, filePath);
            this.scanner = new Scanner(file);
            new FindConclusion(this, scanner);
            this.scanner = new Scanner(file);
            new FindIntroduction(this, scanner);
            this.scanner = new Scanner(file);
            new FindNomAuteur(this, scanner);


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

    public String[] getAuthorsTab() {
        return this.AuthorsTab;
    }

    public void setAuthorsTab(String[] authorsTab) {
        this.AuthorsTab = authorsTab;
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

    public String getConclusion()  {
        return this.conclusion;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


}
