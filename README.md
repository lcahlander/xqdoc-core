# xqdoc-core
Core module to process XQuery code source to an xqDoc XML file

## Building

Run the command: ```mvn clean install assembly:single```

### Dependencies

* antlr
* commons-cli

### Results

* target/xqdoc-core-0.8-jar-with-dependencies.jar
* target/xqdoc-core-0.8.jar

## Command Line Call

```java -jar xqdoc-core-0.8-jar-with-dependencies.jar -Dprefix=uri -Dprefix=uri -f filepath```

The prefix/uri combination is for the prefixes that are not needed in an import module namespace for the implementation.

e.g.  ```-Dfn=http://www.w3.org/2003/05/xpath-functions``` 
is for the default XPath function library.  This prefix/namespace is included by default.

The *filepath* is the path name to the file with the XQuery source.

## Calling from java

    HashMap uriMap = new HashMap();
    uriMap.put(XPathDriver.XPATH_PREFIX, XPathDriver.XPATH_URI);
    InputStream is = Files.newInputStream(Paths.get(cmd.getOptionValue("f")));
    controller = new XQDocController(XQDocController.JUL2017);
    controller.setPredefinedFunctionNamespaces(uriMap);

    XQDocPayload payload = controller.process(is, "");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    InputSource isOut = new InputSource();
    isOut.setCharacterStream(new StringReader(payload.getXQDocXML()));

    Document doc = db.parse(isOut);

