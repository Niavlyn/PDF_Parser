import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


public class Main {
    private final ArrayList<String> fichiersTxt = new ArrayList<>();
    private final ArrayList<String> fichiersTxtTika = new ArrayList<>();
    private final ArrayList<String> fichiersMeta = new ArrayList<>();
    private boolean withXML;
    private boolean withText;
    private String fileName = "";
    private String title = "";
    private String authors = "";
    private String fileAbstract = "";
    private String references = "";
    private String email = "";
    private String conclusion = "";
    private String introduction = "";
    private String corps = "";
    private String discussion ="";
    private HashMap<String, String> affiliation = new HashMap<>();

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {

        Main main = new Main();

        if (args[0].equals("-t")) {
            main.withText = true;
            main.withXML = false;
        } else if (args[0].equals("-x")) {
            main.withText = false;
            main.withXML = true;
        } else {
            main.withText = true;
            main.withXML = true;
        }


        File currentDirectory = new File(System.getProperty("user.dir"));
        File dossierTxt = new File(currentDirectory + "/Corpus_2022_txt");
        File dossierTika = new File(currentDirectory + "/Corpus_2022_txt_tika");
        File dossierMeta = new File(currentDirectory + "/Corpus_2022_meta");
        File dossierFinalProdAbsolu = new File(currentDirectory + "/FinalProduction");


        File[] listeFichiersTxt = dossierTxt.listFiles();
        File[] listeFichiersTxtTika = dossierTika.listFiles();
        File[] listeFichiersMeta = dossierMeta.listFiles();

        //Remplissage des tableaux
        assert listeFichiersTxt != null;
        for (File file : listeFichiersTxt) {
            main.fichiersTxt.add(file.getAbsolutePath());
        }

        assert listeFichiersMeta != null;
        for (File file : listeFichiersMeta) {
            main.fichiersMeta.add(file.getAbsolutePath());
        }

        assert listeFichiersTxtTika != null;
        for (File file : listeFichiersTxtTika) {
            main.fichiersTxtTika.add(file.getAbsolutePath());
        }

        for (int i = 0; i < main.fichiersTxt.size(); i++) {
            String splitFichierTxtName;
            splitFichierTxtName = main.fichiersTxt.get(i).split("Corpus_2022_txt")[1];
            splitFichierTxtName = splitFichierTxtName.substring(1, splitFichierTxtName.length() - 4);
            splitFichierTxtName = splitFichierTxtName + ".txt";
            int result = main.compareText(splitFichierTxtName);
            int indexTika = main.compareTikaAndParser2txt(main.fichiersTxt.get(i));
            if(indexTika == -1) {
                System.err.println("Erreur : index tika == -1"); //Normalement ne peut pas arriver
            } else {
                main.resultComparator(main.fichiersTxt.get(i), main.fichiersTxtTika.get(indexTika), result != -1, result);
                if (main.withText) {
                    new OutputWriter(dossierFinalProdAbsolu.toString(), main.fileName, main.title, main.fileAbstract, main.authors, main.references, main.email);
                }
                if (main.withXML) {
                    new OutputWriterXML(main.fileName, main.title, main.fileAbstract, main.authors, main.references, main.email, main.introduction, main.corps, main.conclusion, main.discussion, main.affiliation);
                }
            }
        }
        main.cleanXML();
    }

    //Compare les résultats du parseur sur les 2 convertisseurs
    private void resultComparator(String filePathPDF2TXT, String filePathTika, boolean meta, int result) {
        Parser parser2txt;
        if (meta) {
            parser2txt = new Parser(filePathPDF2TXT, fichiersMeta.get(result));
        } else {
            parser2txt = new Parser(filePathPDF2TXT, "null");
        }
        Parser parserTika = new Parser(filePathTika, "null");

        fileName = finalResult(parser2txt.getFileName(), parserTika.getFileName());
        title = finalResult(parser2txt.getTitle(), parserTika.getTitle());
        authors = finalResult(parser2txt.getAuthors(), parserTika.getAuthors());
        fileAbstract = finalResult(parser2txt.getFileAbstract(), parserTika.getFileAbstract());
        references = finalResult(parser2txt.getReferences(), parserTika.getReferences());
        email = finalResult(parser2txt.getEmail(), parserTika.getEmail());
        conclusion = finalResult(parser2txt.getConclusion(), parserTika.getConclusion());
        introduction = finalResult(parser2txt.getIntroduction(), parserTika.getIntroduction());
        corps = finalResult(parser2txt.getCorps(), parserTika.getCorps());
        discussion = finalResult(parser2txt.getCorps(), parserTika.getCorps());
        affiliation = parserTika.getAffilations();
    }


    private String finalResult(String resultPDF2Txt, String resultTika) {
        String result;
        if (resultTika == null && resultPDF2Txt == null)
            result = "L'item n'a pas pu être trouvé.";
        else if (resultPDF2Txt == null)
            result = resultTika;
        else if (resultTika == null)
            result = resultPDF2Txt;
        else if (resultTika.trim().equals(resultPDF2Txt.trim()))
            result = resultTika;
        else
            result = resultPDF2Txt;
        return result;
    }

    private int compareTikaAndParser2txt(String pathParser2txt) {
        String[] txtSplit = pathParser2txt.split("/");
        String txtTikaName;

        if(txtSplit.length > 0) {
            txtTikaName = txtSplit[txtSplit.length-1];
        } else {
            txtTikaName = pathParser2txt;
        }

        txtTikaName = txtTikaName.split("\\.")[0];


        for(int i = 0; i < this.fichiersTxtTika.size(); i++) {
            String[] path = fichiersTxtTika.get(i).split("/");
            String name = path[path.length-1].split("\\.")[0];
            if(name.equals(txtTikaName)) {
                return i;
            }
        }
        return -1;
    }

    private int compareText(String txtName) {
        int compare = -1;
        String split;
        txtName = txtName.substring(0, txtName.length() - 4);
        for (int i = 0; i < fichiersMeta.size(); i++) {
            split = fichiersMeta.get(i).split("Corpus_2022_meta")[1];
            split = split.substring(1, split.length() - 13);

            if (split.equals(txtName)) {
                compare = i;
                break;
            }
        }

        return compare;
    }

    private void cleanXML() {
        for (String file: fichiersTxt) {
            System.out.println(file);
        }
    }
}