import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class OutputWriterXML {

    public OutputWriterXML(String nomFichier, String titre, String abstracts, String auteurs,
                           String references, String emails, String introduction, String corps, String conclusion,
                           String discussion, HashMap<String, String> affiliations) throws ParserConfigurationException, TransformerException, IOException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        //Element article
        Document doc = docBuilder.newDocument();
        Element article = doc.createElement("article");
        doc.appendChild(article);

        //Element nom du fichier
        Element nomFic = doc.createElement("preamble");
        nomFic.setTextContent(nomFichier);
        article.appendChild(nomFic);

        //Element titre du papier
        Element titreFic = doc.createElement("titre");
        titreFic.setTextContent(titre);
        article.appendChild(titreFic);

        //Element auteurs
        Element groupAuteurs = doc.createElement("auteurs");
        article.appendChild(groupAuteurs);

        String[] auteursTab = auteurs.split(";");
        String[] emailsTab = emails.split(";");


        if(emails.equals("Impossible de trouver les emails des auteurs") && auteurs.equals("Aucun auteur n'a pu être trouvé.") ){
            //Element baliseAuteur
            Element baliseAuteur = doc.createElement("auteur");
            groupAuteurs.appendChild(baliseAuteur);

            //Element auteurName
            Element auteurName = doc.createElement("name");
            auteurName.setTextContent(replaceChars(auteurs));
            baliseAuteur.appendChild(auteurName);

            //Element emails
            Element email = doc.createElement("email");
            email.setTextContent(replaceChars(emails));
            baliseAuteur.appendChild(email);

            //Element affiliation
            Element affiliation = doc.createElement("affiliation");
            affiliation.setTextContent(replaceChars(emails));
            baliseAuteur.appendChild(affiliation);

        }else if(emails.equals("Impossible de trouver les emails des auteurs")){
            for (String s : auteursTab) {

                //Element baliseAuteur
                Element baliseAuteur = doc.createElement("auteur");
                groupAuteurs.appendChild(baliseAuteur);

                //Element auteurName
                Element auteurName = doc.createElement("name");
                auteurName.setTextContent(replaceChars(s));
                baliseAuteur.appendChild(auteurName);

                //Element emails
                Element email = doc.createElement("mail");
                email.setTextContent("Impossible de trouver l'email correspondant à cet auteur.");
                baliseAuteur.appendChild(email);

                //Element affiliation
                Element affiliation = doc.createElement("affiliation");
                affiliation.setTextContent("Impossible de trouver l'affiliation correspondant à cet auteur");
                for (String keys : affiliations.keySet()) {
                    if (new Comparator().isInTheKey(keys, s)) {
                        affiliation.setTextContent(replaceChars(affiliations.get(keys)));
                        break;
                    }
                }
                baliseAuteur.appendChild(affiliation);
            }
        }else if(auteurs.equals("Aucun auteur n'a pu être trouvé.")){
            for (String s : emailsTab) {
                //Element baliseAuteur
                Element baliseAuteur = doc.createElement("auteur");
                groupAuteurs.appendChild(baliseAuteur);

                //Element auteurName
                Element auteurName = doc.createElement("name");
                auteurName.setTextContent("Impossible de trouver l'auteur correspondant à cet email.");
                baliseAuteur.appendChild(auteurName);

                //Element emails
                Element email = doc.createElement("mail");
                email.setTextContent(replaceChars(s));
                baliseAuteur.appendChild(email);

                //Element affiliation
                Element affiliation = doc.createElement("affiliation");
                affiliation.setTextContent("Impossible de trouver l'affiliation correspondant à cet auteur");
                //affiliation.setTextContent(emails);
                baliseAuteur.appendChild(affiliation);
            }
        }else{

            for (int i = 0; i < emailsTab.length; i++){
                //Element baliseAuteur
                Element baliseAuteur = doc.createElement("auteur");
                groupAuteurs.appendChild(baliseAuteur);


                String name = new Comparator().compareMailAndName(emailsTab[i], auteurs);

                Element auteurName = doc.createElement("name");
                if (name.equals("No match found")){
                    //Element auteurName
                    auteurName.setTextContent("Impossible de trouver l'auteur correspondant à cet email.");
                    baliseAuteur.appendChild(auteurName);

                    //Element emails
                    Element email = doc.createElement("mail");
                    email.setTextContent(replaceChars(emailsTab[i]));
                    baliseAuteur.appendChild(email);
                }else{
                    //Element auteurName
                    auteurName.setTextContent(replaceChars(name));
                    baliseAuteur.appendChild(auteurName);

                    //Element emails
                    Element email = doc.createElement("mail");
                    email.setTextContent(replaceChars(emailsTab[i]));
                    baliseAuteur.appendChild(email);

                    //Element affiliation
                    Element affiliation = doc.createElement("affiliation");
                    affiliation.setTextContent("Impossible de trouver l'affiliation correspondant à cet auteur");
                    for (String keys : affiliations.keySet()) {
                        if(new Comparator().isInTheKey(keys, auteursTab[i])) {
                            affiliation.setTextContent(affiliations.get(keys));
                            break;
                        }
                    }
                    //affiliation.setTextContent(emails);
                    baliseAuteur.appendChild(affiliation);
                }
            }
        }

        //Element abstract
        Element abstractFic = doc.createElement("abstract");
        abstractFic.setTextContent(replaceChars(abstracts));
        article.appendChild(abstractFic);

        //Element introduction
        Element intro = doc.createElement("introduction");
        intro.setTextContent(replaceChars(introduction));
        article.appendChild(intro);

        //Element corps
        Element corp = doc.createElement("corps");
        corp.setTextContent(replaceChars(corps));
        article.appendChild(corp);

        //Element conclusion
        Element conclu = doc.createElement("conclusion");
        conclu.setTextContent(replaceChars(conclusion));
        article.appendChild(conclu);

        //Element discussion
        Element discuss = doc.createElement("discussion");
        discuss.setTextContent(replaceChars(discussion));
        article.appendChild(discuss);

        //Element biblio
        Element biblio = doc.createElement("biblio");
        biblio.setTextContent(replaceChars(references));
        article.appendChild(biblio);

        // print XML to system console
        writeXml(doc, nomFichier);

    }

    // write doc to output stream
    private static void writeXml(Document doc, String nomFichier)
            throws TransformerException, IOException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();


        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);


        File currentDirectory = new File(System.getProperty("user.dir"));
        File dossierXML = new File(currentDirectory + "/FinalProductionXML");


        FileWriter writer = new FileWriter(dossierXML + "/" + nomFichier + ".xml");
        StreamResult result = new StreamResult(writer);

        transformer.transform(source, result);

        System.out.println("Fichier XML généré avec succès pour " + nomFichier);


        String xmlString = result.getWriter().toString();
        System.out.println(xmlString);

    }

    private static String replaceChars(String xml)
    {
        xml = xml.replace("&", "&amp;");
        xml = xml.replaceAll("\"<([^<]*)>", "\"&lt;$1&gt;");
        xml = xml.replaceAll("</([^<]*)>\"", "&lt;/$1&gt;\"");
        xml = xml.replaceAll("\"([^<]*)<([^<]*)>([^<]*)\"", "\"$1&lt;$2&gt;$3\"");
        return xml;
    }
}