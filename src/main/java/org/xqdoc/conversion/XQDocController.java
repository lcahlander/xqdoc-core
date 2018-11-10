/*
 * Copyright (c)2005 Elsevier, Inc.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The use of the Apache License does not indicate that this project is
 * affiliated with the Apache Software Foundation.
 */

package org.xqdoc.conversion;

import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 
 * This class is the mainline controller for processing library and main
 * modules. Drivers for various XML database vendors would interact with this
 * class to parse a module and use the generated XML to store into their
 * database. The following would be the typical interactions with this class.
 * 
 * <ul>
 * <li>Construct the controller with the appropriate W3C XQuery Working Draft
 * version parameter.</li>
 * <li>Set the defaults by calling:</li>
 * <ul>
 * <li>setPredefinedFunctionNamespaces</li>
 * <li>setDefaultFunctionNamespace</li>
 * <li>setModuleBase</li>
 * </ul>
 * <li>Process a module by invoking one of the process() methods.</li>
 * <li>Use the returned XQDocPayload from the process() method to get the
 * serialized xqDoc XML and xqDoc URI to store the document into the XML
 * database.</li>
 * <li>Iteratively invoke process() for the modules to be processed.</li>
 * </ul>
 * 
 * @author Darin McBeath
 * @version 1.0
 */
public class XQDocController {

	// Supported W3C XQuery Draft Versions
	public static final String MAY2003 = "may2003";

	public static final String NOV2003 = "nov2003";

	public static final String OCT2004 = "oct2004";

	public static final String APR2005 = "apr2005";

	public static final String SEP2005 = "sep2005";

	public static final String NOV2005 = "nov2005";

	public static final String JAN2007 = "jan2007";

	public static final String JUL2017 = "jul2017";

	// The xquery collection to use when inserting documents
	public static final String COLLECTION = "xqdoc";

	// XQuery spec
	private String xquerySpec;

	// Context (passed to the parser)
	private XQDocContext context;

	// HashMap of predefined function namespaces
	private HashMap predefinedFunctionNamespaces;

	// Default function namespace
	private String defaultFunctionNamespace;

	// Base to use for modules
	private String moduleBase;

	// Lexer (ANTLR)
	private antlr.CharScanner lexer = null;

	// Parser (ANTLR)
	private XQDocParser parser = null;

	// Flag to indicate whether document URIs should be encoded
	private boolean encodeURIs = false;

	/**
	 * Constructor.
	 * 
	 * @param spec
	 *            The version of the W3C XQuery specification that should be
	 *            used to parse the library and main modules. Valid values
	 *            include the following: may2003, nov2003, oct2004, apr2005,
	 *            sep2005, and nov2005.
	 * @throws XQDocException
	 */
	public XQDocController(String spec) throws XQDocException {
		if (spec.compareTo(MAY2003) == 0 || spec.compareTo(NOV2003) == 0
				|| spec.compareTo(OCT2004) == 0 || spec.compareTo(APR2005) == 0
				|| spec.compareTo(SEP2005) == 0 || spec.compareTo(NOV2005) == 0
				|| spec.compareTo(JAN2007) == 0 || spec.compareTo(JUL2017) == 0) {
			xquerySpec = spec;
			context = new XQDocContext(XQDocContext.XQDOC_NAMESPACE);
		} else {
			throw new XQDocException("Unsupported W3C XQuery Specification.");
		}
	}

	/**
	 * Set any predefined function namespaces that should be used when parsing a
	 * library or main module. Many XQuery implementations support the concept
	 * of predefined namespaces (with a matching prefix) so they don't have to
	 * be repeated in the prolog section of library and main modules. Common
	 * predefined namespaces are the XPath F&O and proprietary vendor library
	 * modules. If a duplicate namespace prefix is specified in a library or
	 * main module, it will take precedence.
	 * 
	 * @param map
	 *            A mapping of namespace prefixes and namespace URIs.
	 */
	public void setPredefinedFunctionNamespaces(HashMap map) {
		predefinedFunctionNamespaces = map;
	}

	/**
	 * Return the predefined function namespaces that should be used when
	 * parsing a library or main module.
	 * 
	 * @return A mapping of namespace prefixes and namespace URIs.
	 */
	public HashMap getPredefinedFunctionNamespaces() {
		return predefinedFunctionNamespaces;
	}

	/**
	 * Set the default function namespace that should be used when parsing a
	 * library or main module. Many XQuery implementations support the concept
	 * of predefined default namespace so it doesn't have to be repeated in the
	 * prolog section of library and modules. A common default function
	 * namespace is the XPath F&O . If a default function namespace is specified
	 * in a library or main module, it will take precedence.
	 * 
	 * @param uri
	 *            The uri for the default namespace.
	 */
	public void setDefaultFunctionNamespace(String uri) {
		defaultFunctionNamespace = uri;
	}

	/**
	 * Return the default function namespace that should be used when parsing a
	 * library or main module.
	 * 
	 * @return The uri for the default namespace.
	 */
	public String getDefaultFunctionNamespace() {
		return defaultFunctionNamespace;
	}

	/**
	 * Set the module base to use for main modules. This value is prepended to
	 * the name passed as the second parameter in the XQDocController process()
	 * and becomes the URI for storing the main module in the database.
	 * 
	 * @param base
	 *            The module base.
	 */
	public void setModuleBase(String base) {
		moduleBase = base;
	}

	/**
	 * Return the module base.
	 * 
	 * @return The module base
	 */
	public String getModuleBase() {
		return moduleBase;
	}

	/**
	 * Encoded document URIs. Currently, only a '/' is encoded. Some XML
	 * databases have problems if a document URI contains a '/'.
	 * 
	 * @param bool
	 *            flag to indicate whether URIs should be encoded
	 */
	public void setEncodeURIs(boolean bool) {
		encodeURIs = bool;
	}

	/**
	 * Return the encode document URI flag.
	 * 
	 * @return the encode flag
	 */
	public boolean getEncodeURIs() {
		return encodeURIs;
	}

	/**
	 * Process the library or main module identified by the InputStream.
	 * 
	 * @param is
	 *            The input stream for the library or main module
	 * @param name
	 *            The 'common name' for the library or main module
	 * @return Payload containing string of xqDoc XML and moduleURI
	 * @throws XQDocException
	 *             Problems while parsing the module
	 */
	public XQDocPayload process(InputStream is, String name)
			throws XQDocException {
		return parse(is, null, name);
	}

	/**
	 * Process the library or main module identified by the Reader.
	 *
	 * @param rdr
	 *            The reader for the library or main module
	 * @param name
	 *            The 'common name' for the library or main module
	 * @return Payload containing string of xqDoc XML and moduleURI
	 * @throws XQDocException
	 *             Problems while parsing the module
	 */
	public XQDocPayload process(Reader rdr, String name) throws XQDocException {
		return parse(null, rdr, name);
	}

	/**
	 * Process the library or main module identified by the Reader.
	 *
	 * @param text
	 *            The reader for the library or main module
	 * @param name
	 *            The 'common name' for the library or main module
	 * @return Payload containing string of xqDoc XML and moduleURI
	 * @throws XQDocException
	 *             Problems while parsing the module
	 */
	public XQDocPayload process(String text, String name) throws XQDocException {
		return parseString(text, name);
	}

	/**
	 * Initialize the parser and begin parsing the library or main module. Also,
	 * extract the source code for the entire module and set this in
	 * 'moduleBody' so it can be included in the xqDoc XML.
	 *
	 * @param is
	 *            The input stream for the library or main module
	 * @param rdr
	 *            The reader for the library or main module
	 * @param name
	 *            The 'common name' for the library or main module
	 * @return Payload containing string of xqDoc XML and moduleURI
	 * @throws XQDocException
	 *             Problems while parsing the module
	 */
	private XQDocPayload parse(InputStream is, Reader rdr, String name)
			throws XQDocException {

		try {
			// Create a temporary file
			File file = File.createTempFile("xqdoc", null);
			file.deleteOnExit();
			PrintWriter outputStream = new PrintWriter(new FileWriter(file));

			// Read the input stream or reader and write to the temporary file
			StringBuffer sb = new StringBuffer();
			int ch = 0;
			if (is != null) {
				while ((ch = is.read()) != -1)
					sb.append((char) ch);
			} else {
				while ((ch = rdr.read()) != -1)
					sb.append((char) ch);
			}
			outputStream.println(sb.toString());
			outputStream.close();

			// Read the file from the temporary file
			sb = new StringBuffer();
			FileReader fr = new FileReader(file);
			while ((ch = fr.read()) != -1)
				// This buffer contains the source code for the module
				sb.append((char) ch);
			fr.close();

			context.init(moduleBase, name, sb.toString(),
					predefinedFunctionNamespaces, defaultFunctionNamespace,
					encodeURIs);

			// Read the file again from the temporary file
			FileReader frdr = new FileReader(file);

			// Get the Parser via reflection
			parser = getParser("java.io.Reader", frdr);
			parser.setContext(context);
			parser.xpath();

			// Build Response
			return context.buildResponse();
		} catch (XQDocRuntimeException xqre) {
			throw new XQDocException(xqre);
		} catch (Exception ex) {
			throw new XQDocException(ex);
		}
	}

	/**
	 * Initialize the parser and begin parsing the library or main module. Also,
	 * extract the source code for the entire module and set this in
	 * 'moduleBody' so it can be included in the xqDoc XML.
	 * 
	 * @param text
	 *            The text of the library or main module
	 * @param name
	 *            The 'common name' for the library or main module
	 * @return Payload containing string of xqDoc XML and moduleURI
	 * @throws XQDocException
	 *             Problems while parsing the module
	 */
	private XQDocPayload parseString(String text, String name)
			throws XQDocException {

		try {
			// Create a temporary file
			File file = File.createTempFile("xqdoc", null);
			file.deleteOnExit();
			PrintWriter outputStream = new PrintWriter(new FileWriter(file));

			// Read the input stream or reader and write to the temporary file
			StringBuffer sb = new StringBuffer(text);
			outputStream.println(sb.toString());
			outputStream.close();

			// Read the file from the temporary file
			sb = new StringBuffer();
			int ch = 0;
			FileReader fr = new FileReader(file);
			while ((ch = fr.read()) != -1)
				// This buffer contains the source code for the module
				sb.append((char) ch);
			fr.close();

			context.init(moduleBase, name, sb.toString(),
					predefinedFunctionNamespaces, defaultFunctionNamespace,
					encodeURIs);

			// Read the file again from the temporary file
			FileReader frdr = new FileReader(file);

			// Get the Parser via reflection
			parser = getParser("java.io.Reader", frdr);
			parser.setContext(context);
			parser.xpath();

			// Build Response
			return context.buildResponse();
		} catch (XQDocRuntimeException xqre) {
			throw new XQDocException(xqre);
		} catch (Exception ex) {
			throw new XQDocException(ex);
		}
	}

	/**
	 * Create a parser (and lexer) to process the current XQuery module. This
	 * class uses Java reflection to dynamically create both the Parser and
	 * Lexer ... there is a separate Parser and Lexer (plus supporting ANTLR
	 * generated classes) for each version of the W3C XQuery Working Draft
	 * supported (may2003, nov2003, oct2004, apr2005, sep2005, nov2005, and
	 * jan2007).
	 * 
	 * @param className
	 *            A String value containing either 'java.io.InputStream' or
	 *            'java.io.Reader'. This identifies the base class of the object
	 *            that will 'read' the module to be parsed. In other words, it
	 *            must be of type 'java.io.InputStream'. or 'java.io.Reader'.
	 * @param obj
	 *            This is the actual object which 'wraps' the module to be
	 *            parsed.
	 * @return A parser object that implements the XQDocParser interface.
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private XQDocParser getParser(String className, Object obj)
			throws InstantiationException, ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Class lexClass = Class.forName("org.xqdoc.xquery.parser." + xquerySpec
				+ ".XQueryLexer");
		Class isClass = Class.forName(className);
		Class[] lexConstParms = new Class[1];
		lexConstParms[0] = isClass;
		Constructor lexConstructor = lexClass.getConstructor(lexConstParms);
		Object[] lexParms = new Object[1];
		lexParms[0] = obj;
		lexer = (antlr.CharScanner) lexConstructor.newInstance(lexParms);

		// Get the Parser via reflection
		Class parseClass = Class.forName("org.xqdoc.xquery.parser."
				+ xquerySpec + ".XQueryParser");
		Class[] parseConstParms = new Class[1];
		parseConstParms[0] = lexClass;
		Constructor parseConstructor = parseClass
				.getConstructor(parseConstParms);
		Object[] parseParms = new Object[1];
		parseParms[0] = lexer;

		return (XQDocParser) parseConstructor.newInstance(parseParms);
	}

}