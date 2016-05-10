package org.korobko.utils;

import com.sun.xml.tree.XmlDocument;
import org.korobko.model.Command;
import org.korobko.model.CommandBookModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by Вова on 15.04.2016.
 */
public class IoUtils {

    public static String fileLocation;

    public static final String ROOT_ELEMENT_TAG = "COMMAND";

    public IoUtils() {

    }

    public static CommandBookModel getAddressBook(String file) {

        fileLocation = file;
        CommandBookModel addressBook = new CommandBookModel();
        Command command;

        try {

            InputStream is = new FileInputStream(file);
            Document doc = XmlDocument.createXmlDocument(is, true);
            int size = XmlUtils.getSize(doc, ROOT_ELEMENT_TAG);

            for (int i = 0; i < size; i++) {

                command = new Command();
                Element row = XmlUtils.getElement(doc, ROOT_ELEMENT_TAG, i);
                command.setNickname(XmlUtils.getValue(row, "NICKNAME"));
                command.setFullPath(XmlUtils.getValue(row, "FULLPATH"));
                addressBook.add(command);
            }

        } catch (Exception e) {
            System.out.println(e);
            return addressBook;

        }

        return addressBook;

    }

    public static void saveAddressBook(java.util.List data) {

        Command person;

        try {
            FileOutputStream fo = new FileOutputStream(fileLocation);
            PrintWriter pw = new PrintWriter(fo);
            pw.println("<?xml version= '1.0'?>");
            pw.println("<!DOCTYPE ADDRESSBOOK [");
            pw.println("<!ELEMENT ADDRESSBOOK (COMMAND)*>");
            pw.println("<!ELEMENT COMMAND (NICKNAME, FULLPATH)>");
            pw.println("<!ELEMENT NICKNAME (#PCDATA)>");
            pw.println("<!ELEMENT FULLPATH (#PCDATA)>");
            pw.println("]>");
            pw.println("<ADDRESSBOOK>");
            pw.println("");

            int size = data.size();
            for (int i = 0; i < size; i++) {

                person = (Command) data.get(i);
                pw.println("  <COMMAND>");
                pw.println("    <NICKNAME>" + person.getNickname() + "</NICKNAME>");
                pw.println("    <FULLPATH>" + person.getFullPath() + "</FULLPATH>");
                pw.println("  </COMMAND>");
                pw.println("");

            }

            pw.println("</ADDRESSBOOK>");

            pw.flush();
            pw.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

