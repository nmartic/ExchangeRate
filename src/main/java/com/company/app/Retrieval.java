package com.company.app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Retrieval implements RetrieveData {

    @Override
    public String[] getData(String sourceUrl) {
        String retrievalExchangeRateDate = null;
        String retrievalDateTime = null;
        String retrievalExchangeRate = null;

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(sourceUrl);
            document.getDocumentElement().normalize();

            Node rootElement = document.getFirstChild();
            NodeList ndList1 = rootElement.getChildNodes();
            for (int i = 0; i < ndList1.getLength(); i++) {
                Node child1 = ndList1.item(i);
                if (child1.getNodeName().equalsIgnoreCase("Cube")) {
                    NodeList ndList2 = child1.getChildNodes();
                    for (int j = 0; j < ndList2.getLength(); j++) {
                        Node child2 = ndList2.item(j);

                        if (child2.getNodeName().equalsIgnoreCase("Cube")) {
                            Element cubeTime =(Element)child2;
                            retrievalExchangeRateDate = cubeTime.getAttribute("time");

                            NodeList ndlist3 = child2.getChildNodes();
                            for (int k = 0; k < ndlist3.getLength(); k++) {
                                Node child3 = ndlist3.item(k);

                                if (child3.getNodeName().equalsIgnoreCase("Cube") && child3.getNodeType() == 1) {
                                    Element cubeObject = (Element)child3;
                                    if (cubeObject.getAttribute("currency").equalsIgnoreCase("USD")) {
                                        LocalDateTime currentDateTime = LocalDateTime.now();
                                        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                                        retrievalDateTime = dtf.format(currentDateTime);
                                        retrievalExchangeRate = cubeObject.getAttribute("rate");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] extractedData = {retrievalDateTime, retrievalExchangeRateDate, retrievalExchangeRate};
        return extractedData;

    }
}
