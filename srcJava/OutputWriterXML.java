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

  public OutputWriterXML(String path, String nomFichier, String titre, String abstracts, String auteurs, String references) throws ParserConfigurationException, TransformerException, IOException{

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
    Element auteur = doc.createElement("auteur");
    auteur.setTextContent(auteurs);
    article.appendChild(auteur);

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
}