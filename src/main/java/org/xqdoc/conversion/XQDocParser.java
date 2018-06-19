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

import antlr.RecognitionException;
import antlr.TokenStreamException;

/**
 * This interface is implemented by the Parsers automatically generated from the
 * ANTLR grammar. The interface was needed because mulitple grammars have been
 * created ... one per XQuery W3C Working Draft. The interface allows the common
 * methods to be abstracted so that the correct Parser can be created through
 * Java reflection yet still referenced via this common interface.
 * 
 * @author Darin McBeath
 * @version 1.0
 */
public interface XQDocParser {

	/**
	 * The method that should be invoked to begin parsing a module.
	 * 
	 * @throws RecognitionException
	 * @throws TokenStreamException
	 */
	public void xpath() throws RecognitionException, TokenStreamException;

	/**
	 * Set the XQDocContext as a member variable within this Parser. This
	 * allows the Parser to populate member variables (through callbacks) within
	 * XQDocContext that will be needed to generate the xqDoc XML. In essence,
	 * the context becomes a big 'container' to hold pertinent information.
	 * 
	 * @param context
	 *            The object that should be populated as the module is parsed.
	 */
	public void setContext(XQDocContext context);

}