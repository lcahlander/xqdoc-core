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

import java.util.*;

/**
 * 
 * This class serves as the 'go between' for XQDocController and the parser. The
 * XQDocController creates (and initializes) the XQDocContext and the parser
 * sets numerous member variables via callbacks. The XQDocContext also has the
 * responsibility for creating the correct XQDocXML and XQDocComment object(s)
 * which eventually constructs the resultant xqDoc XML. Finally, XQDocContext
 * creates the XQDocPaylaod (and sets the member variables) that is returned to
 * XQDocController.
 * 
 * @author Darin McBeath
 * @version 1.0
 */
public class XQDocContext {

	// Namespace for generated xqdoc xml
	public static final String XQDOC_NAMESPACE = "http://www.xqdoc.org/1.0";

	// Version of the xqDoc tool
	private static final String XQDOC_VERSION = "1.1";

	// Object for creating xqDoc XML
	private XQDocXML xqDocXML;

	// HashMap of predefined function namespaces (set by XQDocController via
	// init())
	private HashMap predefinedFunctionNamespaces;

	// Default function namespace (set by XQDocController via init())
	private String defaultFunctionNamespace;

	// Base to use for modules (set by XQDocController via init())
	private String moduleBase;

	// Common name for modules (set by XQDocController via init())
	private String commonName;

	// Source code for the entire module (set by XQDocController via init())
	private String moduleBody;

	// Flag to indicate whether document URIs should be encoded (set by
	// XQDocController via init())
	private boolean encodeURIs = false;

	// Module URI
	private String moduleURI;

	// HashMap of prefixes to uris (set by Parser)
	private HashMap uriModuleMap = new HashMap();

	// Default module specified function namespace (set by Parser)
	private String defaultModuleFunctionNamespace;

	// Hash for holding the invoked functions for the current function
	private HashSet invokedFunctions = new HashSet();

	// Hash for holding the referenced variables for the current function
	private HashSet referencedVariables = new HashSet();

	// Buffer for holding the current function name
	private String functionName;

	// Flag to indicate a function (within a main module) has been processed
	private boolean functionPrefixSet = false;

	// Flag to indicate processing a main module
	private boolean mainModuleFound = false;

	// Buffer for holding the current function signature
	private String functionSignature;

	// Buffer for holding the current function body
	private String functionBody;

	// Buffer for holding the current xqdoc comment
	private XQDocComment xqDocComment = new XQDocComment();

	private String annotationName = null;
	private LinkedList annotationList = new LinkedList();
    protected LinkedList annotationBody = new LinkedList();
    protected boolean processingAnnotations = false;

    /**
	 * Constructor. Currently, the namespace value is ignored since there is
	 * only one 'version' of XQDoc XML generated. Eventually, this value could
	 * be used to determine what supporting objects need to be created to build
	 * the requested version of XQDoc XML.
	 * 
	 * @param namespace
	 *            The namespace to use for the generated XQDoc XML.
	 */
	public XQDocContext(String namespace) {
	}

	/**
	 * The initialize method for XQDocContext. This method will be invoked by
	 * XQDocController prior to parsing the requested module.
	 * 
	 * @param base
	 *            The module base (mostly used for main modules)
	 * @param name
	 *            The common name associated with the module
	 * @param source
	 *            The source code for the module
	 * @param map
	 *            The map of predefined function namespaces (URIs) to prefixes
	 * @param uri
	 *            The default function namespace URI
	 * @param encode
	 *            The document URI encode flag
	 *  
	 */
	public void init(String base, String name, String source, HashMap map,
			String uri, boolean encode) {
		moduleBase = base;
		commonName = name;
		moduleBody = source;
		
		if (map != null) {
			predefinedFunctionNamespaces = (HashMap) map.clone();
		} else {
			predefinedFunctionNamespaces = new HashMap();
		}
		
		defaultFunctionNamespace = uri;
		encodeURIs = encode;

		xqDocXML = new XQDocXML(XQDOC_NAMESPACE);
		xqDocComment.clear();
		invokedFunctions = new HashSet();
		referencedVariables = new HashSet();
		xqDocXML.buildControlSection(XQDOC_VERSION);
		uriModuleMap = new HashMap();
		defaultModuleFunctionNamespace = null;
		mainModuleFound = false;
		functionPrefixSet = false;

	}

	/**
	 * Build the library module section for the returned xqDoc XML (for library
	 * modules). This will consist of the library module uri, friendly name,
	 * xqDoc comment block, and the source code for the entire library module.
	 * Either this method (or the one for main modules) will be invoked once
	 * (from the Parser) for each module processed.
	 * 
	 * @param uri
	 *            The library module uri.
	 *  
	 */
	public void buildLibraryModuleSection(String uri) {
		moduleURI = uri;
		if (encodeURIs) {
			moduleURI = encodeURI(moduleURI);
		}
		xqDocXML.buildLibraryModuleSection(moduleURI, commonName, xqDocComment,
				moduleBody);
		xqDocComment.clear();
	}

	/**
	 * Build the main module section for the returned xqDoc XML (for main
	 * modules). This will consist of the main module uri, friendly name, xqDoc
	 * comment block, and the source code for the entire main module. Either
	 * this method (or the one for library modules) will be invoked once (from
	 * the Parser) for each module processed.
	 *  
	 */
	public void buildMainModuleSection() {
		String newUri = commonName;
		if (moduleBase != null)
			newUri = moduleBase + "/" + commonName;
		moduleURI = newUri;
		if (encodeURIs) {
			moduleURI = encodeURI(moduleURI);
		}
		xqDocXML.buildMainModuleSection(moduleURI, commonName, xqDocComment,
				moduleBody);
		xqDocComment.clear();
		mainModuleFound = true;
	}

	/**
	 * Append information to the import section for the returned xqDoc XML. This
	 * information will include the uri for the import as well as the xqDoc
	 * comment block associated with the import. This method will be called
	 * (from the Parser) once for each module imported by either a library or
	 * main module.
	 * 
	 * @param uri
	 *            The uri for the module imported.
	 *  
	 */
	public void buildImportSection(String uri) {
		if (encodeURIs) {
			xqDocXML.buildImportSection(encodeURI(uri), xqDocComment);
		} else {
			xqDocXML.buildImportSection(uri, xqDocComment);
		}
		xqDocComment.clear();
	}

	/**
	 * Append information to the global variable section for the returned xqDoc
	 * XML. This information will include the defined global variable uri as
	 * well as the xqDoc comment block associated with the global variable. This
	 * method will be called (from the Parser) once for each global variable
	 * declared by either a library or main module.
	 * 
	 * @param uri
	 *            The uri for the global variable.
	 *  
	 */
	public void buildVariableSection(String uri) {
		xqDocXML.buildVariableSection(uri, xqDocComment);
		xqDocComment.clear();
		invokedFunctions = new HashSet();
		referencedVariables = new HashSet();
	}

	/**
	 * Append information to the function section for the returned xqDoc XML.
	 * This information will include the function name, the xqDoc comment block
	 * associated with the function, function signature, function source code,
	 * referenced global variables, and list of invoked functions. This method
	 * will be called (from the Parser) once for each declared function by
	 * either a library or main module.
	 *  
	 */
	public void buildFunctionSection() {
		xqDocXML.buildFunctionSection(functionName, functionSignature,
				xqDocComment, functionBody, invokedFunctions,
				referencedVariables, annotationList);
		xqDocComment.clear();
		invokedFunctions = new HashSet();
		referencedVariables = new HashSet();
		resetAnnotations();
	}

	/**
	 * Construct the response payload consisting of serialized xqDoc XML and the
	 * module URI. This meethod will be called by XQDocController.
	 * 
	 * @return Payload containing string of xqDoc XML and moduleURI
	 */
	public XQDocPayload buildResponse() {
		XQDocPayload payload = new XQDocPayload();
		payload.setXQDocXML(xqDocXML.getXML());
		payload.setModuleURI(moduleURI);
		return payload;
	}

	/**
	 * Add the namespace prefix and uri to a HashMap. The HashMap will be used
	 * when processing invoked functions and referenced variables to associate
	 * the correct uri with the specified prefix in the xqDoc XML for the
	 * referenced variable or invoked function. This will be called by the
	 * parser when a module is imported (and namespace prefix is specified) or a
	 * namespace is declared. If a duplicate prefix is found (from the list
	 * specified by XQDocController), this prefix will override any of the
	 * 'predefined' values set by XQDocController.
	 * 
	 * @param prefix
	 *            The namespace prefix
	 * @param uri
	 *            The namespace uri
	 */
	public void addPrefixAndURI(String prefix, String uri) {
		uriModuleMap.put(prefix, uri);
	}

	/**
	 * Set the default function namespace as specified in the module. This
	 * method will be called by the parser. This value will override any
	 * 'predefined' default function namespace that might have been specified
	 * from XQDocController.
	 * 
	 * @param uri
	 *            The uri for the default function namespace
	 */
	public void setDefaultModuleFunctionNamespace(String uri) {
		defaultModuleFunctionNamespace = uri;
	}

	/**
	 * Set the function name for the function declaration so that it can be
	 * included when generating the xqDoc XML. This method will be called by the
	 * parser once for each function declaration.  If it is a 
	 * 'main module' the namespace mappings may need to be adjusted in order
	 * to allow local linking between functions defined in the main
	 * module.
	 * 
	 * @param name
	 *            The function name (without the uri prefix)
	 */
	public void setFunctionName(String prefix, String name) {
		// Is this a main module (and have we already processed a function)
		// Assume all functions in a main module are declared with the same
		// prefix (or no prefix).
		if (!functionPrefixSet && mainModuleFound) {
			functionPrefixSet = true;
			// If no prefix for the function name
			if (prefix == null) {
				// Was a default function namespace specified in the module
				if (defaultModuleFunctionNamespace != null) {
					adjustNamespaceMapping(defaultModuleFunctionNamespace);
					defaultModuleFunctionNamespace = moduleURI;
				// Was a default function namespace predefined
				} else if (defaultFunctionNamespace != null) {
					adjustNamespaceMapping(defaultFunctionNamespace);
					defaultFunctionNamespace = moduleURI;
				}
			} else {
				// Need to do the same for when there was a prefix
				String tmp = (String) uriModuleMap.get(prefix);
				if (tmp != null) {
					adjustDefaultNamespace(tmp);
					adjustNamespaceMapping(tmp);
				}
				tmp = (String) predefinedFunctionNamespaces.get(prefix);
				if (tmp != null) {
					adjustDefaultNamespace(tmp);
					adjustNamespaceMapping(tmp);
				}
			}
		}

		functionName = name;
	}

	/**
	 * Adjust any namespaces that were either predefined
	 * or found in the module.  This method will only be called
	 * for main modules to adjust the namespace for a module to
	 * enable local linking of functions defined within the
	 * main module.
	 * 
	 * @param uri the namespace URI 
	 */
	private void adjustNamespaceMapping(String uri) {
		Set ks = uriModuleMap.keySet();
		Iterator i = ks.iterator();
		// Adjust namespace URIs found in the module (to moduleURI) 
		// if they equal the specified URI
		while (i.hasNext()) {
			String key = (String) i.next();
			String tmp = (String) uriModuleMap.get(key);
			if (tmp.equals(uri))
				uriModuleMap.put(key, moduleURI);
		}
		ks = predefinedFunctionNamespaces.keySet();
		i = ks.iterator();
		// Adjust namespace URIs that were predefined (to moduleURI)
		// if they equal the specified URI
		while (i.hasNext()) {
			String key = (String) i.next();
			String tmp = (String) predefinedFunctionNamespaces
					.get(key);
			if (tmp.equals(uri))
				predefinedFunctionNamespaces.put(key, moduleURI);
		}		
	}
	
	/**
	 * Adjust any default namespaces that were either predefined
	 * or found in the module.  This method will only be called
	 * for main modules to adjust the default namespace for a module to
	 * enable local linking of functions defined within the
	 * main module.
	 * @param uri the namespace uri
	 */
	private void adjustDefaultNamespace(String uri) {
		if (defaultModuleFunctionNamespace != null) {
			if (uri.equals(defaultModuleFunctionNamespace)) {
				defaultModuleFunctionNamespace = moduleURI;
			}
		}
		if (defaultFunctionNamespace != null) {
			if (uri.equals(defaultFunctionNamespace)) {
				defaultFunctionNamespace = moduleURI;
			}
		}		
	}
	
	/**
	 * Set the function signature for the function delcaration so that it can be
	 * included when generating the xqDoc XML. This method will be called by the
	 * parser once for each function signature.
	 * 
	 * @param signature
	 *            The function signature
	 */
	public void setFunctionSignature(String signature) {
		functionSignature = signature;
	}

	/**
	 * Set the source code for the function body so that it can be included when
	 * generating the xqDoc XML. This method will be called by the parser once
	 * for each function body.
	 * 
	 * @param body
	 *            The source code for a function body
	 */
	public void setFunctionBody(String body) {
		functionBody = body;
	}

	/**
	 * Set the xqDoc comment block. The XqDocComment object will then further
	 * process this block of text to separate the values into the appropriate
	 * xqDoc buckets (i.e. author, version, see, since, etc.). This method will
	 * be called by the parser once for each xqDoc comment block.
	 * 
	 * @param text
	 *            The xqDoc comment block
	 */
	public void setXQDocBuffer(String text) {
		xqDocComment.setComment(text);
	}

	/**
	 * Check if the invoked function (from the current function) has already
	 * been identified. If so, simply ignore this reference. Otherwise, add this
	 * function name (uri and local name) to the invokedFunctions HashSet so the
	 * proper xqDoc XML will be generated. This method will be called by the
	 * parser once for each invoked function.
	 * 
	 * @param fName
	 *            The function invoked from within the current function
	 */
	public void setInvokedFunction(String fName) {
		// Separate the function name into namspace prefix and localname
		String namespacePrefix = null;
		String namespace = null;
		String localName = null;
		String[] tmp = fName.split(":", 2);
		if (tmp.length > 1) {
			namespacePrefix = tmp[0];
			localName = tmp[1];
		} else {
			localName = tmp[0];
		}

		// Get the actual namespace
		if (namespacePrefix == null) {
			if (defaultModuleFunctionNamespace != null) {
				namespace = defaultModuleFunctionNamespace;
			} else if (defaultFunctionNamespace != null) {
				namespace = defaultFunctionNamespace;
			}
		} else {
			namespace = (String) (uriModuleMap.get(namespacePrefix));
			if (namespace == null) {
				namespace = (String) (predefinedFunctionNamespaces
						.get(namespacePrefix));
			}
		}

		// References a namespace we don't know about
		if (namespace == null)
			return;

		if (encodeURIs) {
			namespace = encodeURI(namespace);
		}

		// Check the invokedFunctions (to see if it is already there)
		if (!invokedFunctions.contains(namespace + " " + localName)) {
			invokedFunctions.add(namespace + " " + localName);
		}
	}

	/**
	 * Check if the referenced global variable (from the current function) has
	 * already been identified. If so, simply ignore this reference. Otherwise,
	 * add this variable name (uri and local name) to the referencedVariables
	 * HashSet so the proper xqDoc XML will be generated. This method will be
	 * called by the parser once for each referenced global variable. It is
	 * currently not possible to find variables referenced if the variable
	 * resides within the default function namespace.
	 * 
	 * @param vName
	 *            The variable referenced from within the current function
	 */
	public void setReferencedVariable(String vName) {
		// Separate the variable name into namspace prefix and localname
		String namespacePrefix = null;
		String namespace = null;
		String localName = null;
		String[] tmp = vName.split(":", 2);
		if (tmp.length > 1) {
			namespacePrefix = tmp[0];
			localName = tmp[1];
		} else {
			return;
		}

		namespace = (String) (uriModuleMap.get(namespacePrefix));
		if (namespace == null) {
			namespace = (String) (predefinedFunctionNamespaces
					.get(namespacePrefix));
		}

		// References a namespace we don't know about
		if (namespace == null)
			return;

		if (encodeURIs) {
			namespace = encodeURI(namespace);
		}

		if (!referencedVariables.contains(namespace + " " + localName)) {
			referencedVariables.add(namespace + " " + localName);
		}

	}

	/**
	 * Encode the URI. Calls will be made to this method depending on the value
	 * (encodeURI) set in the init(). Currently, only the "/" is encoded.
	 * 
	 * @param uri
	 *            The string to encode.
	 * @return The encoded string.
	 */
	private String encodeURI(String uri) {
		return uri.replaceAll("/", "~2F");
	}

    public String getAnnotationName() {
        return annotationName;
    }

    public void setAnnotationName(String annotationName) {
        this.annotationName = annotationName;
        annotationBody = new LinkedList();
        annotationBody.add(annotationName);
        annotationList.add(annotationBody);
        processingAnnotations = true;
    }

    public void resetAnnotations() {
	    annotationList = new LinkedList();
	    annotationName = null;
	    processingAnnotations = false;
    }

    public void addAnnotationLiteral(String annotationLiteral) {

        if (processingAnnotations) {
	        annotationBody.add(annotationLiteral);
        }
    }
}