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
import java.util.Locale;

public class OutputWriterXML {

    public OutputWriterXML(String path, String nomFichier, String titre, String abstracts, String auteurs, String references, String emails) throws ParserConfigurationException, TransformerException, IOException {

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
            auteurName.setTextContent(auteurs);
            baliseAuteur.appendChild(auteurName);

            //Element emails
            Element email = doc.createElement("email");
            email.setTextContent(emails);
            baliseAuteur.appendChild(email);

            //Element affiliation
            Element affiliation = doc.createElement("affiliation");
            affiliation.setTextContent(emails);
            baliseAuteur.appendChild(affiliation);

        }else if(emails.equals("Impossible de trouver les emails des auteurs")){
            for (int i = 0; i < auteursTab.length; i++){
                //Element baliseAuteur
                Element baliseAuteur = doc.createElement("auteur");
                groupAuteurs.appendChild(baliseAuteur);

                //Element auteurName
                Element auteurName = doc.createElement("name");
                auteurName.setTextContent(auteursTab[i]);
                baliseAuteur.appendChild(auteurName);

                //Element emails
                Element email = doc.createElement("mail");
                email.setTextContent("Impossible de trouver l'email correspondant à cet auteur.");
                baliseAuteur.appendChild(email);

                //Element affiliation
                Element affiliation = doc.createElement("affiliation");
                affiliation.setTextContent(emails);
                baliseAuteur.appendChild(affiliation);
            }
        }else if(auteurs.equals("Aucun auteur n'a pu être trouvé.")){
            for (int i = 0; i < emailsTab.length; i++){
                //Element baliseAuteur
                Element baliseAuteur = doc.createElement("auteur");
                groupAuteurs.appendChild(baliseAuteur);

                //Element auteurName
                Element auteurName = doc.createElement("name");
                auteurName.setTextContent("Impossible de trouver l'auteur correspondant à cet email.");
                baliseAuteur.appendChild(auteurName);

                //Element emails
                Element email = doc.createElement("mail");
                email.setTextContent(emailsTab[i]);
                baliseAuteur.appendChild(email);

                //Element affiliation
                Element affiliation = doc.createElement("affiliation");
                affiliation.setTextContent(emails);
                baliseAuteur.appendChild(affiliation);
            }
        }else{
            
            for (int i = 0; i < emailsTab.length; i++){
                //Element baliseAuteur
                Element baliseAuteur = doc.createElement("auteur");
                groupAuteurs.appendChild(baliseAuteur);


                String name = compareMailAndName(emailsTab[i], auteurs);

                Element auteurName = doc.createElement("name");
                if (name.equals("No match found")){
                    //Element auteurName
                    auteurName.setTextContent("Impossible de trouver l'auteur correspondant à cet email.");
                    baliseAuteur.appendChild(auteurName);

                    //Element emails
                    Element email = doc.createElement("mail");
                    email.setTextContent(emailsTab[i]);
                    baliseAuteur.appendChild(email);
                }else{
                    //Element auteurName
                    auteurName.setTextContent(name);
                    baliseAuteur.appendChild(auteurName);

                    //Element emails
                    Element email = doc.createElement("mail");
                    email.setTextContent(emailsTab[i]);
                    baliseAuteur.appendChild(email);
                }
            }
            //auteurs sans emails
            for (String s: auteursTab) {
                //Element baliseAuteur
                Element baliseAuteur = doc.createElement("auteur");
                groupAuteurs.appendChild(baliseAuteur);
                if (s != null){
                    //Element auteurName
                    Element auteurName = doc.createElement("name");
                    auteurName.setTextContent(s);
                    baliseAuteur.appendChild(auteurName);

                    //Element emails
                    Element email = doc.createElement("mail");
                    email.setTextContent("Impossible de trouver l'email correspondant à cet auteur.");
                    baliseAuteur.appendChild(email);
                }
            }
        }

        //Element abstract
        Element abstractFic = doc.createElement("abstract");
        abstractFic.setTextContent(abstracts);
        article.appendChild(abstractFic);

        //Element biblio
        Element biblio = doc.createElement("biblio");
        biblio.setTextContent(references);
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
        System.out.println(dossierXML.toString());

        FileWriter writer = new FileWriter(dossierXML + "/" + nomFichier + ".xml");
        StreamResult result = new StreamResult(writer);

        transformer.transform(source, result);

    }


    /**
     * Retourne le nom/prénom correspondant à un mail
     * @param mail - un seul mail
     * @param auteurs - tous les auteurs
     * @return
     */
    private String compareMailAndName(String mail, String auteurs){
        //Aucun auteur n'a pu être trouvé.
        //Impossible de trouver les emails des auteurs
        if(!mail.equals("Impossible de trouver les emails des auteurs")){
            String[] mailSplit = mail.split("@");
            String frontPart = mailSplit[0];
            frontPart = frontPart.toUpperCase();

            if (!auteurs.equals("Aucun auteur n'a pu être trouvé.")){
                String[] auteursSpliTab = auteurs.split(";");
                int i = 0;
                for (String s: auteursSpliTab) {
                    auteursSpliTab[i] = s.toUpperCase();
                    i++;
                }

                String[] frontPartSplit = frontPart.split("\\.");

                if (frontPartSplit.length > 1) {

                    String nameV1 = frontPartSplit[0] + " " + frontPartSplit[1];
                    String nameV2 = frontPartSplit[1] + " " + frontPartSplit[0];

                    for (int j = 0; j < auteursSpliTab.length; j++) {
                        if (nameV1.equals(auteursSpliTab[j])) {
                            auteursSpliTab[j] = null;
                            return nameV1;
                        } else if (nameV2.equals(auteursSpliTab[j])) {
                            auteursSpliTab[j] = null;
                            return nameV2;
                        }
                    }
                }
                return "No match found";

            }else {
                return "No authors";
            }
        }
        return "Mail null";
    }
}