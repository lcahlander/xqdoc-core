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

/**
 * This class is a 'container' for information returned from XQDocController to
 * the various drivers. The 'container' contains the serialized xqDoc XML and the
 * xqDoc module URI associated with this XML.
 * 
 * @author Darin McBeath
 * @version 1.0
 *  
 */
public class XQDocPayload {

	// Serialized xqDoc XML for the module
	private String xqDocXML;

	// xqDoc URI for the module
	private String moduleURI;

	/**
	 * Set the serialized xqDoc XML in the response payload.
	 * 
	 * @param xml
	 *            Serialized xqDoc XML.
	 */
	public void setXQDocXML(String xml) {
		xqDocXML = xml;
	}

	/**
	 * Get the serialized xqDoc XML from the response payload.
	 * 
	 * @return Serialized xqDoc XML.
	 */
	public String getXQDocXML() {
		return xqDocXML;
	}

	/**
	 * Set the xqDoc module URI in the response payload.
	 * 
	 * @param uri
	 *            xqDoc module URI.
	 */
	public void setModuleURI(String uri) {
		moduleURI = uri;
	}

	/**
	 * Get the xqDoc module URI from the response payload.
	 * 
	 * @return xqDoc module URI.
	 */
	public String getModuleURI() {
		return moduleURI;
	}
}