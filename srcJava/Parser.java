import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
public class Parser {

    private String fileName = "";
    private String title = "";
    private String[] AuthorsTab = {""};
    private HashMap<String, String> affiliations;
    private String titleMeta = "";
    private String authors = "";
    private String fileAbstract = "";
    private String references = "";
    private String email = "";
    private Scanner scanner;
    private String conclusion;
    private String introduction;
    private File file;
    private String corps;
    private String discussion;


    public Parser(String filePath, String metaPath) {
        new ReaderMeta(this, filePath, metaPath);

        reader(filePath);
        new Comparator(this, filePath);
    }

    public void reader(String filePath) {
        try {
            this.file = new File(filePath);
            this.scanner = new Scanner(file);
            new FindTitle(this);
            this.scanner = new Scanner(file);
            new FindAbstract(this, scanner);
            this.scanner = new Scanner(file);
            new FindBibliographie(this, scanner);
            this.scanner = new Scanner(file);
            new FindEmail(this, filePath);
            this.scanner = new Scanner(file);
            new FindConclusion(this, scanner);
            this.scanner = new Scanner(file);
            new FindIntroduction(this, scanner);
            new FindCorps(this, scanner);
            this.scanner = new Scanner(file);
            new FindDiscussion(this, scanner);
            this.scanner = new Scanner(file);
            new FindNomAuteur(this, scanner);
            this.scanner = new Scanner(file);
            new FindAffiliation(this);

            scanner.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    public void resetScanner() throws FileNotFoundException {
        this.scanner = new Scanner(file);
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

    public HashMap<String, String> getAffilations() {
        return affiliations;
    }

    public void setAffilations(HashMap<String, String> affilations) {
        this.affiliations = affilations;
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

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public String getDiscussion() {
        return this.discussion;
    }
}
