package org.korobko.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by Вова on 15.04.2016.
 */
public class XmlUtils {
    public static Element getElement(Document doc, String tagName,
                                     int index) {

        NodeList rows = doc.getDocumentElement().getElementsByTagName(
                tagName);
        return (Element) rows.item(index);
    }

    public static int getSize(Document doc, String tagName) {

        NodeList rows = doc.getDocumentElement().getElementsByTagName(
                tagName);
        return rows.getLength();
    }

    public static String getValue(Element e, String tagName) {
        try {

            NodeList elements = e.getElementsByTagName(tagName);
            Node node = elements.item(0);
            NodeList nodes = node.getChildNodes();
            String s;
            for (int i = 0; i < nodes.getLength(); i++) {
                s = (nodes.item(i)).getNodeValue().trim();
                if (s.equals("") || s.equals("\r")) {
                    continue;
                } else return s;
            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        return null;

    }

}

