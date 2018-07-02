package org.exquery.xqdoc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.*;
import org.xmlunit.util.Nodes;
import org.xmlunit.util.Predicate;
import org.xqdoc.conversion.XQDocController;
import org.xqdoc.conversion.XQDocException;
import org.xqdoc.conversion.XQDocPayload;
import org.xqdoc.drivers.XPathDriver;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
public class AppTest
{

    @Parameters(name = "{index}: xqDoc({0})={1}")
    public static Iterable<String[]> data() {

        AppTest obj = new AppTest("", "");
        ClassLoader classLoader = obj.getClass().getClassLoader();
        String url = classLoader.getResource("XQuery").getFile();
        File directory = new File(url);

        File[] files = directory.listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.

        String[][] arrayOfArrays = new String[files.length][];

        for(int i=0; i<files.length; i++)
        {
            if (files[i].isFile()) {
                String filename = files[i].getName();
                String[] parts = filename.split("\\.");
                String file1 = "XQuery/" + filename;
                String file2 = "xqDoc/" + parts[0] + ".xml";
                String[] pair = new String[] { file1, file2 };
                arrayOfArrays[i] = pair;
            }
        }

        return Arrays.asList(arrayOfArrays);
    }

    private String input;
    private String expected;

    public AppTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() throws IOException, XQDocException, ParserConfigurationException, SAXException {
        XQDocController controller = null;
        ClassLoader classLoader = getClass().getClassLoader();
        String source = classLoader.getResource(input).getFile();
        String target = classLoader.getResource(expected).getFile();
        HashMap uriMap = new HashMap();
        uriMap.put(XPathDriver.XPATH_PREFIX, XPathDriver.XPATH_URI);
        InputStream is = Files.newInputStream(Paths.get(source));
        controller = new XQDocController(
                XQDocController.JUL2017);
        controller.setPredefinedFunctionNamespaces(uriMap);

        XQDocPayload payload = controller.process(is, "");
        String xqDocXML = payload.getXQDocXML();
        DifferenceEngine diff = new DOMDifferenceEngine();
        diff.addDifferenceListener(new ComparisonListener() {
            public void comparisonPerformed(Comparison comparison, ComparisonResult outcome) {
                assertTrue("found a difference: " + comparison, false);
            }
        });
        Source test = Input.fromString(xqDocXML).build();
        Source control = Input.fromFile(target).build();
        Diff myDiff = DiffBuilder.compare(control).withTest(test)
                .withNodeFilter(new Predicate<Node>() {
                    @Override
                    public boolean test(Node n) {
                        return !(n instanceof Element &&
                                "date".equals(Nodes.getQName(n).getLocalPart()));
                    }
                })
                .checkForSimilar()
                .ignoreWhitespace()
                .build();
        assertFalse("XML similar " + myDiff.toString(), myDiff.hasDifferences());    }
}
