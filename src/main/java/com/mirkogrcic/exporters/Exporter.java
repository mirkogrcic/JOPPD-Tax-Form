package com.mirkogrcic.exporters;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface Exporter {
    void exportFile(String filePath) throws IOException, TransformerException, SAXException, ParserConfigurationException;
    String exportString() throws ParserConfigurationException, IOException, SAXException, TransformerException;
}
