package com.mirkogrcic.exporters;

import com.mirkogrcic.Data;
import com.mirkogrcic.calculator.Result;
import com.mirkogrcic.utils.Util;
import com.mirkogrcic.utils.xml.XmlDomUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class XmlExporter extends BaseExporter {
    public XmlExporter(Data data, Result result) {
        super(data, result);
    }

    @Override
    public String exportString() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        InputStream stream = System.class.getResourceAsStream("/templates/joppd_template.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(stream);

        doc.getDocumentElement().normalize();

        XmlDomUtils xmlDomUtils = new XmlDomUtils(doc);


        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.Metapodaci.Autor", this.data.getFullName().toUpperCase());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.Metapodaci.Identifikator", UUID.randomUUID());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.Metapodaci.Datum",
                new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(this.data.getDate()));

        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.DatumIzvjesca",
                new SimpleDateFormat("yyyy-MM-dd").format(this.data.getDate()));
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.OznakaIzvjesca",
                Util.getMark(this.data.getDate()));
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PodnositeljIzvjesca.Ime", data.getName().toUpperCase());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PodnositeljIzvjesca.Prezime", data.getSurname().toUpperCase());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PodnositeljIzvjesca.Adresa.Mjesto", data.getAddressPlace().toUpperCase());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PodnositeljIzvjesca.Adresa.Ulica", data.getAddressStreet().toUpperCase());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PodnositeljIzvjesca.Adresa.Broj", String.format("%04d", data.getAddressNumber()));
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PodnositeljIzvjesca.Email", data.getEmail());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PodnositeljIzvjesca.OIB", data.getOib());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PodnositeljIzvjesca.Email", data.getEmail());

        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.PredujamPoreza.P5", result.getTaxSurtaxSum());

        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.Doprinosi.GeneracijskaSolidarnost.P2", result.getPension1());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.Doprinosi.KapitaliziranaStednja.P2", result.getPension2());

        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.IzvjesceSastavio.Ime", data.getName().toUpperCase());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaA.IzvjesceSastavio.Prezime", data.getSurname().toUpperCase());

        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P2", data.getCityCodeResidence());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P3", data.getCityCodeWork());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P4", data.getOib());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P5", data.getFullName());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].101", String.format("%1$tY-01-01", data.getDate()));
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].102", String.format("%1$tY-12-31", data.getDate()));
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P11", data.getGrossIncome());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P12", data.getGrossIncome());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P121", result.getPension1());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P122", result.getPension2());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P132", result.getPension());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P133", result.getPension());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P135", result.getGrossPensionSub());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P141", result.getTax());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P142", result.getSurtax());
        xmlDomUtils.updateByTagNamePath("ObrazacJOPPD.StranaB.Primatelji.P[0].P162", result.getNetIncome());


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(outputStream);
        transformer.transform(source, result);
        return outputStream.toString();
    }
}
