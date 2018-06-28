package org.exquery.xqdoc;

import org.apache.commons.cli.*;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xqdoc.conversion.XQDocController;
import org.xqdoc.conversion.XQDocException;
import org.xqdoc.conversion.XQDocPayload;
import org.xqdoc.drivers.XPathDriver;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String getStringFromDoc(org.w3c.dom.Document doc)    {
        DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
        LSSerializer lsSerializer = domImplementation.createLSSerializer();
        lsSerializer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
        LSOutput lsOutput =  domImplementation.createLSOutput();
        lsOutput.setEncoding("UTF-8");
        Writer stringWriter = new StringWriter();
        lsOutput.setCharacterStream(stringWriter);
        lsSerializer.write(doc, lsOutput);
        String result = stringWriter.toString();

        return result;
    }

    public static void main( String[] args ) throws ParseException, IOException, XQDocException, ParserConfigurationException, SAXException {
        XQDocController controller = null;
        Options options = new Options();

        Option propertyOption   = Option.builder()
                .longOpt("D")
                .argName("property=value" )
                .hasArgs()
                .valueSeparator()
                .numberOfArgs(2)
                .desc("use value for given properties" )
                .build();

        options.addOption(propertyOption);
        options.addOption("f", true, "file name");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);
        HashMap uriMap = new HashMap();
        uriMap.put(XPathDriver.XPATH_PREFIX, XPathDriver.XPATH_URI);

        if(cmd.hasOption("D")) {
            Properties properties = cmd.getOptionProperties("D");
            Enumeration<String> prefixes = (Enumeration<String>) properties.propertyNames();
            while (prefixes.hasMoreElements()) {
                String prefix = prefixes.nextElement();
                uriMap.put(prefix, properties.getProperty(prefix));
            }
        }

        if (cmd.hasOption("f")) {
            InputStream is = Files.newInputStream(Paths.get(cmd.getOptionValue("f")));
            controller = new XQDocController(
                    XQDocController.JUL2017);
            controller.setPredefinedFunctionNamespaces(uriMap);

            XQDocPayload payload = controller.process(is, "");
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource isOut = new InputSource();
            isOut.setCharacterStream(new StringReader(payload.getXQDocXML()));

            Document doc = db.parse(isOut);
            System.out.println(App.getStringFromDoc(doc));
        }
    }
}
