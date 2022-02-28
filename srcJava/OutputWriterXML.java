import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class OutputWriterXML{

  public OutputWriterXML() throws ParserConfigurationException, TransformerException, IOException{

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    //Element article
    Document doc = docBuilder.newDocument();
    Element article = doc.createElement("article");
    doc.appendChild(article);

    //Element nom du fichier
    Element nomFichier = doc.createElement("preamble");
    //TODO : Ajout du nom du fichier d'origine
    nomFichier.setTextContent("Le nom du fichier d'origine");
    article.appendChild(nomFichier);

    //Element titre du papier
    Element titre = doc.createElement("titre");
    //TODO : Ajout du titre 
    titre.setTextContent("Le titre du papier");
    article.appendChild(titre);

    //Element auteurs 
    Element auteur = doc.createElement("auteur");
    //TODO : Ajout des auteurs et de leur adresse mail
    auteur.setTextContent("La section auteurs et leur adresse courriels");
    article.appendChild(auteur);

    //Element abstract
    Element abstracts = doc.createElement("abstract");
    //TODO : Ajout de l'abstract
    abstracts.setTextContent("Le resume de l'article");
    article.appendChild(abstracts);

    //Element biblio
    Element biblio = doc.createElement("biblio");
    //TODO : Ajout de la bibliographie
    biblio.setTextContent("Les references bibliographiques du papier");
    article.appendChild(biblio);

    // print XML to system console
    writeXml(doc);

}

  // write doc to output stream
  private static void writeXml(Document doc)
          throws TransformerException, IOException {

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();

      // pretty print
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(doc);
      File currentDirectory = new File(System.getProperty("user.dir"));
      File dossierXML = new File(currentDirectory + "/FinalProduction_XML");
      System.out.println(dossierXML.toString());

      //FileWriter writer = new FileWriter(dossierXML);
      //StreamResult result = new StreamResult(writer);

      //transformer.transform(source, result);

  }
}