package org.exquery.xqdoc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;

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
        return lsSerializer.writeToString(doc);
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
