package com.mirkogrcic.exporters;

import com.mirkogrcic.Data;
import com.mirkogrcic.calculator.Result;
import org.xml.sax.SAXException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BaseExporter implements Exporter {
    Data data;
    Result result;

    public BaseExporter(Data data, Result result) {
        this.data = data;
        this.result = result;
    }

    @Override
    public void exportFile(String filePath) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String data = exportString();
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        try {
            fw.write(data);
        } finally {
            fw.close();
        }
    }

    @Override
    public String exportString() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        throw new NotImplementedException();
    }
}
