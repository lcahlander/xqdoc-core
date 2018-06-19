// $ANTLR : "XQuery.g" -> "XQueryParser.java"$

	
/**  
 * Grammar definition for the May 2003 XQuery specification.
 */
	package org.xqdoc.xquery.parser.may2003;

	import antlr.debug.misc.*;
	import java.io.StringReader;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	import java.util.HashSet;
	import java.util.List;
	import java.util.Iterator;
	import java.util.Stack;

	import org.xqdoc.conversion.XQDocContext;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class XQueryParser extends antlr.LLkParser       implements XQueryParserTokenTypes
, org.xqdoc.conversion.XQDocParser {

	protected Stack globalStack= new Stack();
	protected Stack elementStack= new Stack();
	protected XQueryLexer lexer;
	protected XQDocContext context;

	boolean buildFuncBodyFlag = false;
	boolean buildFuncSigFlag = false;
	HashSet tokenSet = new HashSet();
	StringBuffer functionBody = new StringBuffer();
	StringBuffer functionSignature = new StringBuffer();
	
	
	/**
	
	*/
	public XQueryParser(XQueryLexer lexer) {
		this((TokenStream)lexer);
		this.lexer= lexer;
	}

	public void match (int t) throws MismatchedTokenException, TokenStreamException {
		if (buildFuncBodyFlag == true) {	
			String key = new String(LT(1).getLine() + "-" + LT(1).getColumn());
			if (tokenSet.contains(key)) {
				// do nothing, already processed the token
			} else {
				tokenSet.add(key);
				if (lexer.whiteSpaceBag.length() > 0) {
					functionBody.append(lexer.whiteSpaceBag);
					lexer.whiteSpaceBag = new StringBuffer();
				}
				functionBody.append(LT(1).getText()); 
			}			
		} else if (buildFuncSigFlag == true) {	
			String key = new String(LT(1).getLine() + "-" + LT(1).getColumn());
			if (tokenSet.contains(key)) {
				// do nothing, already processed the token
			} else {
				tokenSet.add(key);
				if (lexer.whiteSpaceBag.length() > 0) {
					functionSignature.append(lexer.whiteSpaceBag);
					lexer.whiteSpaceBag = new StringBuffer();
				}
				functionSignature.append(LT(1).getText()); 
			}			
		}
		super.match(t);
	}
	
	public void setContext(XQDocContext context) {
		this.context = context;
	}
	

protected XQueryParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public XQueryParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected XQueryParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public XQueryParser(TokenStream lexer) {
  this(lexer,1);
}

public XQueryParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void xpath() throws RecognitionException, TokenStreamException {
		
		
		{
		switch ( LA(1)) {
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case STRING_LITERAL:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_empty:
		case STAR:
		case PLUS:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case MINUS:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case SLASH:
		case DSLASH:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_nillable:
		case XQDOC_COMMENT:
		case XML_CDATA:
		case LITERAL_collection:
		{
			module();
			break;
		}
		case EOF:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(Token.EOF_TYPE);
	}
	
	public final void module() throws RecognitionException, TokenStreamException {
		
		
		{
		boolean synPredMatched8 = false;
		if (((LA(1)==LITERAL_module||LA(1)==XQDOC_COMMENT))) {
			int _m8 = mark();
			synPredMatched8 = true;
			inputState.guessing++;
			try {
				{
				{
				switch ( LA(1)) {
				case XQDOC_COMMENT:
				{
					xqdocComment();
					break;
				}
				case LITERAL_module:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				match(LITERAL_module);
				}
				}
			}
			catch (RecognitionException pe) {
				synPredMatched8 = false;
			}
			rewind(_m8);
			inputState.guessing--;
		}
		if ( synPredMatched8 ) {
			libraryModule();
		}
		else if ((_tokenSet_0.member(LA(1)))) {
			mainModule();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
	}
	
	public final void xqdocComment() throws RecognitionException, TokenStreamException {
		
		Token  x = null;
		
		x = LT(1);
		match(XQDOC_COMMENT);
		if ( inputState.guessing==0 ) {
			
					context.setXQDocBuffer(x.getText());
				
		}
	}
	
	public final void libraryModule() throws RecognitionException, TokenStreamException {
		
		
		{
		switch ( LA(1)) {
		case XQDOC_COMMENT:
		{
			xqdocComment();
			break;
		}
		case LITERAL_module:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		moduleDecl();
		{
		switch ( LA(1)) {
		case LITERAL_xquery:
		{
			match(LITERAL_xquery);
			match(LITERAL_version);
			match(STRING_LITERAL);
			break;
		}
		case EOF:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_default:
		case LITERAL_define:
		case LITERAL_validation:
		case XQDOC_COMMENT:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		prolog();
	}
	
	public final void mainModule() throws RecognitionException, TokenStreamException {
		
		
		{
		if ((LA(1)==XQDOC_COMMENT)) {
			xqdocComment();
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		if ( inputState.guessing==0 ) {
			context.buildMainModuleSection();
		}
		{
		if ((LA(1)==LITERAL_xquery)) {
			match(LITERAL_xquery);
			match(LITERAL_version);
			match(STRING_LITERAL);
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		prolog();
		queryBody();
	}
	
	public final void prolog() throws RecognitionException, TokenStreamException {
		
		
		{
		{
		_loop38:
		do {
			boolean synPredMatched21 = false;
			if (((LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT))) {
				int _m21 = mark();
				synPredMatched21 = true;
				inputState.guessing++;
				try {
					{
					{
					switch ( LA(1)) {
					case XQDOC_COMMENT:
					{
						xqdocComment();
						break;
					}
					case LITERAL_import:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(LITERAL_import);
					match(LITERAL_module);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched21 = false;
				}
				rewind(_m21);
				inputState.guessing--;
			}
			if ( synPredMatched21 ) {
				moduleImport();
			}
			else {
				boolean synPredMatched24 = false;
				if (((LA(1)==LITERAL_declare||LA(1)==LITERAL_default))) {
					int _m24 = mark();
					synPredMatched24 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_declare);
						{
						switch ( LA(1)) {
						case LITERAL_xmlspace:
						{
							match(LITERAL_xmlspace);
							break;
						}
						case LITERAL_namespace:
						{
							match(LITERAL_namespace);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						}
					}
					catch (RecognitionException pe) {
						synPredMatched24 = false;
					}
					rewind(_m24);
					inputState.guessing--;
				}
				if ( synPredMatched24 ) {
					setter();
				}
				else {
					boolean synPredMatched27 = false;
					if (((LA(1)==LITERAL_declare||LA(1)==LITERAL_default))) {
						int _m27 = mark();
						synPredMatched27 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_default);
							{
							switch ( LA(1)) {
							case LITERAL_collation:
							{
								match(LITERAL_collation);
								break;
							}
							case LITERAL_element:
							{
								match(LITERAL_element);
								break;
							}
							case LITERAL_function:
							{
								match(LITERAL_function);
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							}
						}
						catch (RecognitionException pe) {
							synPredMatched27 = false;
						}
						rewind(_m27);
						inputState.guessing--;
					}
					if ( synPredMatched27 ) {
						setter();
					}
					else {
						boolean synPredMatched30 = false;
						if (((LA(1)==LITERAL_define||LA(1)==XQDOC_COMMENT))) {
							int _m30 = mark();
							synPredMatched30 = true;
							inputState.guessing++;
							try {
								{
								{
								switch ( LA(1)) {
								case XQDOC_COMMENT:
								{
									xqdocComment();
									break;
								}
								case LITERAL_define:
								{
									break;
								}
								default:
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
								}
								}
								match(LITERAL_define);
								match(LITERAL_variable);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched30 = false;
							}
							rewind(_m30);
							inputState.guessing--;
						}
						if ( synPredMatched30 ) {
							varFunctionDecl();
						}
						else if ((LA(1)==LITERAL_validation)) {
							{
							match(LITERAL_validation);
							{
							switch ( LA(1)) {
							case LITERAL_lax:
							{
								match(LITERAL_lax);
								break;
							}
							case LITERAL_strict:
							{
								match(LITERAL_strict);
								break;
							}
							case LITERAL_skip:
							{
								match(LITERAL_skip);
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							}
						}
						else if ((LA(1)==LITERAL_import)) {
							{
							match(LITERAL_import);
							match(LITERAL_schema);
							{
							switch ( LA(1)) {
							case LITERAL_namespace:
							{
								{
								match(LITERAL_namespace);
								match(NCNAME);
								match(EQ);
								}
								break;
							}
							case LITERAL_default:
							{
								{
								match(LITERAL_default);
								match(LITERAL_element);
								match(LITERAL_namespace);
								match(EQ);
								}
								break;
							}
							case STRING_LITERAL:
							{
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							match(STRING_LITERAL);
							{
							if ((LA(1)==LITERAL_at)) {
								match(LITERAL_at);
								match(STRING_LITERAL);
							}
							else if ((_tokenSet_1.member(LA(1)))) {
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							
							}
							}
						}
						else {
							break _loop38;
						}
						}}}
					} while (true);
					}
					{
					_loop43:
					do {
						boolean synPredMatched42 = false;
						if (((LA(1)==LITERAL_define||LA(1)==XQDOC_COMMENT))) {
							int _m42 = mark();
							synPredMatched42 = true;
							inputState.guessing++;
							try {
								{
								{
								switch ( LA(1)) {
								case XQDOC_COMMENT:
								{
									xqdocComment();
									break;
								}
								case LITERAL_define:
								{
									break;
								}
								default:
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
								}
								}
								match(LITERAL_define);
								match(LITERAL_function);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched42 = false;
							}
							rewind(_m42);
							inputState.guessing--;
						}
						if ( synPredMatched42 ) {
							varFunctionDecl();
						}
						else {
							break _loop43;
						}
						
					} while (true);
					}
					}
				}
				
	public final void queryBody() throws RecognitionException, TokenStreamException {
		
		
		if ( inputState.guessing==0 ) {
			
					buildFuncBodyFlag=true;
					lexer.whiteSpaceBag = new StringBuffer();
				
		}
		expr();
		if ( inputState.guessing==0 ) {
			
					buildFuncBodyFlag=false;
					context.setFunctionName("local","xqDoc-main");
					context.setFunctionSignature(null);
					context.setFunctionBody(functionBody.toString());
					context.buildFunctionSection();
					functionBody = new StringBuffer();	
				
		}
	}
	
	public final void moduleDecl() throws RecognitionException, TokenStreamException {
		
		
			String uri = null;	
		
		
		match(LITERAL_module);
		uri=strippedStringLiteral();
		if ( inputState.guessing==0 ) {
			
					context.buildLibraryModuleSection(uri);
				
		}
	}
	
	public final String  strippedStringLiteral() throws RecognitionException, TokenStreamException {
		String strippedLiteral;
		
		Token  literal = null;
		
			strippedLiteral = null;
		
		
		literal = LT(1);
		match(STRING_LITERAL);
		if ( inputState.guessing==0 ) {
			
					strippedLiteral = literal.getText();
					if (strippedLiteral.length() <= 2) {
						strippedLiteral = "";
					} else {
						strippedLiteral = strippedLiteral.substring(1,strippedLiteral.length()-1);
					}
				
		}
		return strippedLiteral;
	}
	
	public final void moduleImport() throws RecognitionException, TokenStreamException {
		
		Token  junk = null;
		
			String prefix = null;
			String uri = null;	
		
		
		{
		switch ( LA(1)) {
		case XQDOC_COMMENT:
		{
			xqdocComment();
			break;
		}
		case LITERAL_import:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_import);
		match(LITERAL_module);
		{
		switch ( LA(1)) {
		case LITERAL_namespace:
		{
			match(LITERAL_namespace);
			prefix=ncnameOrKeyword();
			match(EQ);
			break;
		}
		case STRING_LITERAL:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		uri=strippedStringLiteral();
		{
		if ((LA(1)==LITERAL_at)) {
			match(LITERAL_at);
			junk = LT(1);
			match(STRING_LITERAL);
		}
		else if ((_tokenSet_1.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		if ( inputState.guessing==0 ) {
			
					if (prefix != null) {
						context.addPrefixAndURI(prefix, uri);
					}
					context.buildImportSection(uri);
				
		}
	}
	
	public final void setter() throws RecognitionException, TokenStreamException {
		
		
			String uri = null;	
		
		
		{
		boolean synPredMatched47 = false;
		if (((LA(1)==LITERAL_default))) {
			int _m47 = mark();
			synPredMatched47 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_default);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched47 = false;
			}
			rewind(_m47);
			inputState.guessing--;
		}
		if ( synPredMatched47 ) {
			match(LITERAL_default);
			{
			switch ( LA(1)) {
			case LITERAL_collation:
			{
				match(LITERAL_collation);
				match(EQ);
				match(STRING_LITERAL);
				break;
			}
			case LITERAL_element:
			{
				match(LITERAL_element);
				match(LITERAL_namespace);
				match(EQ);
				match(STRING_LITERAL);
				break;
			}
			case LITERAL_function:
			{
				match(LITERAL_function);
				match(LITERAL_namespace);
				match(EQ);
				uri=strippedStringLiteral();
				if ( inputState.guessing==0 ) {
					
									context.setDefaultModuleFunctionNamespace(uri);
								
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		else {
			boolean synPredMatched50 = false;
			if (((LA(1)==LITERAL_declare))) {
				int _m50 = mark();
				synPredMatched50 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_declare);
					match(LITERAL_xmlspace);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched50 = false;
				}
				rewind(_m50);
				inputState.guessing--;
			}
			if ( synPredMatched50 ) {
				match(LITERAL_declare);
				match(LITERAL_xmlspace);
				match(EQ);
				{
				switch ( LA(1)) {
				case LITERAL_preserve:
				{
					match(LITERAL_preserve);
					break;
				}
				case LITERAL_strip:
				{
					match(LITERAL_strip);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
			}
			else {
				boolean synPredMatched53 = false;
				if (((LA(1)==LITERAL_declare))) {
					int _m53 = mark();
					synPredMatched53 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_declare);
						match(LITERAL_namespace);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched53 = false;
					}
					rewind(_m53);
					inputState.guessing--;
				}
				if ( synPredMatched53 ) {
					namespaceDecl();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}}
				}
			}
			
	public final void varFunctionDecl() throws RecognitionException, TokenStreamException {
		
		
		{
		boolean synPredMatched59 = false;
		if (((LA(1)==LITERAL_define||LA(1)==XQDOC_COMMENT))) {
			int _m59 = mark();
			synPredMatched59 = true;
			inputState.guessing++;
			try {
				{
				{
				switch ( LA(1)) {
				case XQDOC_COMMENT:
				{
					xqdocComment();
					break;
				}
				case LITERAL_define:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LITERAL_define);
				match(LITERAL_function);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched59 = false;
			}
			rewind(_m59);
			inputState.guessing--;
		}
		if ( synPredMatched59 ) {
			functionDecl();
		}
		else {
			boolean synPredMatched62 = false;
			if (((LA(1)==LITERAL_define||LA(1)==XQDOC_COMMENT))) {
				int _m62 = mark();
				synPredMatched62 = true;
				inputState.guessing++;
				try {
					{
					{
					switch ( LA(1)) {
					case XQDOC_COMMENT:
					{
						xqdocComment();
						break;
					}
					case LITERAL_define:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(LITERAL_define);
					match(LITERAL_variable);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched62 = false;
				}
				rewind(_m62);
				inputState.guessing--;
			}
			if ( synPredMatched62 ) {
				varDecl();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		
	public final void namespaceDecl() throws RecognitionException, TokenStreamException {
		
		
			String prefix = null;
			String uri = null;
		
		
		match(LITERAL_declare);
		match(LITERAL_namespace);
		prefix=ncnameOrKeyword();
		match(EQ);
		uri=strippedStringLiteral();
		if ( inputState.guessing==0 ) {
			
					context.addPrefixAndURI(prefix, uri);
				
		}
	}
	
	public final String  ncnameOrKeyword() throws RecognitionException, TokenStreamException {
		String name;
		
		Token  n1 = null;
		name= null;
		
		switch ( LA(1)) {
		case NCNAME:
		{
			n1 = LT(1);
			match(NCNAME);
			if ( inputState.guessing==0 ) {
				name= n1.getText();
			}
			break;
		}
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_empty:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_document:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case LITERAL_nillable:
		case LITERAL_collection:
		{
			name=reservedKeywords();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return name;
	}
	
	public final void functionDecl() throws RecognitionException, TokenStreamException {
		
		
			String name= null;
			String localName = null; 
			String prefix = null;
		
		
		{
		switch ( LA(1)) {
		case XQDOC_COMMENT:
		{
			xqdocComment();
			break;
		}
		case LITERAL_define:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_define);
		match(LITERAL_function);
		name=qName();
		if ( inputState.guessing==0 ) {
			
					buildFuncSigFlag = true; 
					lexer.whiteSpaceBag = new StringBuffer();
				
		}
		match(LPAREN);
		{
		switch ( LA(1)) {
		case DOLLAR:
		{
			paramList();
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_as:
		{
			returnType();
			break;
		}
		case LCURLY:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
					buildFuncSigFlag = false; 
					String[] tmp = name.split(":", 2);
					localName = name;
					if (tmp.length > 1) {
						prefix = tmp[0];
						localName = tmp[1];
					}		
					context.setFunctionName(prefix, localName);
					context.setFunctionSignature("define function " + localName + functionSignature.toString());
					functionBody.append("define function " + name + functionSignature);
					functionSignature = new StringBuffer();
				
		}
		functionBody();
	}
	
	public final void varDecl() throws RecognitionException, TokenStreamException {
		
		
			String varName= null;
			String localName=null;
		
		
		{
		switch ( LA(1)) {
		case XQDOC_COMMENT:
		{
			xqdocComment();
			break;
		}
		case LITERAL_define:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_define);
		match(LITERAL_variable);
		match(DOLLAR);
		varName=qName();
		{
		switch ( LA(1)) {
		case LITERAL_as:
		{
			typeDeclaration();
			break;
		}
		case LCURLY:
		case LITERAL_external:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LCURLY:
		{
			{
			match(LCURLY);
			expr();
			match(RCURLY);
			}
			break;
		}
		case LITERAL_external:
		{
			match(LITERAL_external);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
					String[] tmp = varName.split(":", 2);
					localName = varName;
					if (tmp.length > 1) {
						localName = tmp[1];
					}
					context.buildVariableSection(localName);        
				
		}
	}
	
	public final String  qName() throws RecognitionException, TokenStreamException {
		String name;
		
		
			name= null;
			String name2;
		
		
		boolean synPredMatched316 = false;
		if (((_tokenSet_2.member(LA(1))))) {
			int _m316 = mark();
			synPredMatched316 = true;
			inputState.guessing++;
			try {
				{
				ncnameOrKeyword();
				match(COLON);
				ncnameOrKeyword();
				}
			}
			catch (RecognitionException pe) {
				synPredMatched316 = false;
			}
			rewind(_m316);
			inputState.guessing--;
		}
		if ( synPredMatched316 ) {
			name=ncnameOrKeyword();
			match(COLON);
			name2=ncnameOrKeyword();
			if ( inputState.guessing==0 ) {
				
						name= name + ':' + name2;
					
			}
		}
		else if ((_tokenSet_2.member(LA(1)))) {
			name=ncnameOrKeyword();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		return name;
	}
	
	public final void typeDeclaration() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_as);
		sequenceType();
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		exprSingle();
		{
		_loop112:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop112;
			}
			
		} while (true);
		}
	}
	
	public final void paramList() throws RecognitionException, TokenStreamException {
		
		
		param();
		{
		_loop80:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				param();
			}
			else {
				break _loop80;
			}
			
		} while (true);
		}
	}
	
	public final void returnType() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_as);
		sequenceType();
	}
	
	public final void functionBody() throws RecognitionException, TokenStreamException {
		
		
		if ( inputState.guessing==0 ) {
			
					buildFuncBodyFlag=true;
					lexer.whiteSpaceBag = new StringBuffer();
				
		}
		match(LCURLY);
		expr();
		match(RCURLY);
		if ( inputState.guessing==0 ) {
			
					buildFuncBodyFlag=false;
					context.setFunctionBody(functionBody.toString());
					context.buildFunctionSection();
					functionBody = new StringBuffer();
				
		}
	}
	
	public final void sequenceType() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched86 = false;
		if (((LA(1)==LITERAL_empty))) {
			int _m86 = mark();
			synPredMatched86 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_empty);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched86 = false;
			}
			rewind(_m86);
			inputState.guessing--;
		}
		if ( synPredMatched86 ) {
			match(LITERAL_empty);
			match(LPAREN);
			match(RPAREN);
		}
		else if ((_tokenSet_2.member(LA(1)))) {
			itemType();
			{
			switch ( LA(1)) {
			case QUESTION:
			case STAR:
			case PLUS:
			{
				occurrenceIndicator();
				break;
			}
			case EOF:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_at:
			case LCURLY:
			case RCURLY:
			case LITERAL_external:
			case RPAREN:
			case COMMA:
			case LITERAL_empty:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_where:
			case LITERAL_return:
			case LITERAL_in:
			case COLON:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LITERAL_instance:
			case RPPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void param() throws RecognitionException, TokenStreamException {
		
		
			String name = null;
		
		
		match(DOLLAR);
		name=qName();
		{
		switch ( LA(1)) {
		case LITERAL_as:
		{
			typeDeclaration();
			break;
		}
		case RPAREN:
		case COMMA:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void itemType() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched91 = false;
		if (((LA(1)==LITERAL_item))) {
			int _m91 = mark();
			synPredMatched91 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_item);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched91 = false;
			}
			rewind(_m91);
			inputState.guessing--;
		}
		if ( synPredMatched91 ) {
			match(LITERAL_item);
			match(LPAREN);
			match(RPAREN);
		}
		else {
			boolean synPredMatched93 = false;
			if (((_tokenSet_3.member(LA(1))))) {
				int _m93 = mark();
				synPredMatched93 = true;
				inputState.guessing++;
				try {
					{
					match(41);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched93 = false;
				}
				rewind(_m93);
				inputState.guessing--;
			}
			if ( synPredMatched93 ) {
				kindTest();
			}
			else {
				boolean synPredMatched95 = false;
				if (((_tokenSet_3.member(LA(1))))) {
					int _m95 = mark();
					synPredMatched95 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_element);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched95 = false;
					}
					rewind(_m95);
					inputState.guessing--;
				}
				if ( synPredMatched95 ) {
					kindTest();
				}
				else {
					boolean synPredMatched97 = false;
					if (((_tokenSet_3.member(LA(1))))) {
						int _m97 = mark();
						synPredMatched97 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_attribute);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched97 = false;
						}
						rewind(_m97);
						inputState.guessing--;
					}
					if ( synPredMatched97 ) {
						kindTest();
					}
					else {
						boolean synPredMatched99 = false;
						if (((_tokenSet_3.member(LA(1))))) {
							int _m99 = mark();
							synPredMatched99 = true;
							inputState.guessing++;
							try {
								{
								match(43);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched99 = false;
							}
							rewind(_m99);
							inputState.guessing--;
						}
						if ( synPredMatched99 ) {
							kindTest();
						}
						else {
							boolean synPredMatched101 = false;
							if (((_tokenSet_3.member(LA(1))))) {
								int _m101 = mark();
								synPredMatched101 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_comment);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched101 = false;
								}
								rewind(_m101);
								inputState.guessing--;
							}
							if ( synPredMatched101 ) {
								kindTest();
							}
							else {
								boolean synPredMatched103 = false;
								if (((_tokenSet_3.member(LA(1))))) {
									int _m103 = mark();
									synPredMatched103 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_text);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched103 = false;
									}
									rewind(_m103);
									inputState.guessing--;
								}
								if ( synPredMatched103 ) {
									kindTest();
								}
								else {
									boolean synPredMatched105 = false;
									if (((_tokenSet_3.member(LA(1))))) {
										int _m105 = mark();
										synPredMatched105 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_node);
											match(LPAREN);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched105 = false;
										}
										rewind(_m105);
										inputState.guessing--;
									}
									if ( synPredMatched105 ) {
										kindTest();
									}
									else if ((_tokenSet_2.member(LA(1)))) {
										atomicType();
									}
									else {
										throw new NoViableAltException(LT(1), getFilename());
									}
									}}}}}}}
								}
								
	public final void occurrenceIndicator() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case QUESTION:
		{
			match(QUESTION);
			break;
		}
		case STAR:
		{
			match(STAR);
			break;
		}
		case PLUS:
		{
			match(PLUS);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void kindTest() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_text:
		{
			textTest();
			break;
		}
		case LITERAL_node:
		{
			anyKindTest();
			break;
		}
		case LITERAL_element:
		{
			elementTest();
			break;
		}
		case LITERAL_attribute:
		{
			attributeTest();
			break;
		}
		case LITERAL_comment:
		{
			commentTest();
			break;
		}
		case 43:
		{
			piTest();
			break;
		}
		case 41:
		{
			documentTest();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void atomicType() throws RecognitionException, TokenStreamException {
		
		
			String name = null;
		
		
		name=qName();
	}
	
	public final void singleType() throws RecognitionException, TokenStreamException {
		
		
		atomicType();
		{
		switch ( LA(1)) {
		case QUESTION:
		{
			match(QUESTION);
			break;
		}
		case EOF:
		case LITERAL_default:
		case LITERAL_collation:
		case RCURLY:
		case RPAREN:
		case COMMA:
		case LITERAL_empty:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_treat:
		case LITERAL_castable:
		case RPPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void exprSingle() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched116 = false;
		if (((LA(1)==LITERAL_for||LA(1)==LITERAL_let))) {
			int _m116 = mark();
			synPredMatched116 = true;
			inputState.guessing++;
			try {
				{
				{
				switch ( LA(1)) {
				case LITERAL_for:
				{
					match(LITERAL_for);
					break;
				}
				case LITERAL_let:
				{
					match(LITERAL_let);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(DOLLAR);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched116 = false;
			}
			rewind(_m116);
			inputState.guessing--;
		}
		if ( synPredMatched116 ) {
			flworExpr();
		}
		else {
			boolean synPredMatched119 = false;
			if (((LA(1)==LITERAL_some||LA(1)==LITERAL_every))) {
				int _m119 = mark();
				synPredMatched119 = true;
				inputState.guessing++;
				try {
					{
					{
					switch ( LA(1)) {
					case LITERAL_some:
					{
						match(LITERAL_some);
						break;
					}
					case LITERAL_every:
					{
						match(LITERAL_every);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(DOLLAR);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched119 = false;
				}
				rewind(_m119);
				inputState.guessing--;
			}
			if ( synPredMatched119 ) {
				quantifiedExpr();
			}
			else {
				boolean synPredMatched121 = false;
				if (((LA(1)==LITERAL_typeswitch))) {
					int _m121 = mark();
					synPredMatched121 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_typeswitch);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched121 = false;
					}
					rewind(_m121);
					inputState.guessing--;
				}
				if ( synPredMatched121 ) {
					typeswitchExpr();
				}
				else {
					boolean synPredMatched123 = false;
					if (((LA(1)==LITERAL_if))) {
						int _m123 = mark();
						synPredMatched123 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_if);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched123 = false;
						}
						rewind(_m123);
						inputState.guessing--;
					}
					if ( synPredMatched123 ) {
						ifExpr();
					}
					else {
						boolean synPredMatched125 = false;
						if (((LA(1)==LITERAL_try))) {
							int _m125 = mark();
							synPredMatched125 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_try);
								match(LCURLY);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched125 = false;
							}
							rewind(_m125);
							inputState.guessing--;
						}
						if ( synPredMatched125 ) {
							tryCatchExpr();
						}
						else if ((_tokenSet_4.member(LA(1)))) {
							orExpr();
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						}}}}
					}
					
	public final void flworExpr() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt129=0;
		_loop129:
		do {
			switch ( LA(1)) {
			case LITERAL_for:
			{
				forClause();
				break;
			}
			case LITERAL_let:
			{
				letClause();
				break;
			}
			default:
			{
				if ( _cnt129>=1 ) { break _loop129; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			}
			_cnt129++;
		} while (true);
		}
		{
		switch ( LA(1)) {
		case LITERAL_where:
		{
			match(LITERAL_where);
			expr();
			break;
		}
		case LITERAL_return:
		case LITERAL_stable:
		case LITERAL_order:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_stable:
		case LITERAL_order:
		{
			orderByClause();
			break;
		}
		case LITERAL_return:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_return);
		exprSingle();
	}
	
	public final void quantifiedExpr() throws RecognitionException, TokenStreamException {
		
		
		{
		switch ( LA(1)) {
		case LITERAL_some:
		{
			match(LITERAL_some);
			break;
		}
		case LITERAL_every:
		{
			match(LITERAL_every);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		quantifiedInVarBinding();
		{
		_loop158:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				quantifiedInVarBinding();
			}
			else {
				break _loop158;
			}
			
		} while (true);
		}
		match(LITERAL_satisfies);
		exprSingle();
	}
	
	public final void typeswitchExpr() throws RecognitionException, TokenStreamException {
		
		
			String varName=null; 
		
		
		match(LITERAL_typeswitch);
		match(LPAREN);
		expr();
		match(RPAREN);
		{
		int _cnt163=0;
		_loop163:
		do {
			if ((LA(1)==LITERAL_case)) {
				caseClause();
			}
			else {
				if ( _cnt163>=1 ) { break _loop163; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt163++;
		} while (true);
		}
		match(LITERAL_default);
		{
		switch ( LA(1)) {
		case DOLLAR:
		{
			match(DOLLAR);
			varName=qName();
			break;
		}
		case LITERAL_return:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_return);
		exprSingle();
	}
	
	public final void ifExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_if);
		match(LPAREN);
		expr();
		match(RPAREN);
		match(LITERAL_then);
		expr();
		match(LITERAL_else);
		exprSingle();
	}
	
	public final void tryCatchExpr() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(LITERAL_try);
		match(LCURLY);
		expr();
		match(RCURLY);
		match(LITERAL_catch);
		match(LPAREN);
		match(DOLLAR);
		tmpStr=qName();
		match(RPAREN);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void orExpr() throws RecognitionException, TokenStreamException {
		
		
		andExpr();
		{
		_loop170:
		do {
			if ((LA(1)==LITERAL_or)) {
				match(LITERAL_or);
				andExpr();
			}
			else {
				break _loop170;
			}
			
		} while (true);
		}
	}
	
	public final void forClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_for);
		inVarBinding();
		{
		_loop134:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				inVarBinding();
			}
			else {
				break _loop134;
			}
			
		} while (true);
		}
	}
	
	public final void letClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_let);
		letVarBinding();
		{
		_loop137:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				letVarBinding();
			}
			else {
				break _loop137;
			}
			
		} while (true);
		}
	}
	
	public final void orderByClause() throws RecognitionException, TokenStreamException {
		
		
		{
		switch ( LA(1)) {
		case LITERAL_stable:
		{
			match(LITERAL_stable);
			break;
		}
		case LITERAL_order:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_order);
		match(LITERAL_by);
		orderSpecList();
	}
	
	public final void inVarBinding() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		match(DOLLAR);
		name=qName();
		{
		switch ( LA(1)) {
		case LITERAL_as:
		{
			typeDeclaration();
			break;
		}
		case LITERAL_at:
		case LITERAL_in:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_at:
		{
			positionalVar();
			break;
		}
		case LITERAL_in:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_in);
		exprSingle();
	}
	
	public final void letVarBinding() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		match(DOLLAR);
		name=qName();
		{
		switch ( LA(1)) {
		case LITERAL_as:
		{
			typeDeclaration();
			break;
		}
		case COLON:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(COLON);
		match(EQ);
		exprSingle();
	}
	
	public final void positionalVar() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		match(LITERAL_at);
		match(DOLLAR);
		name=qName();
	}
	
	public final void orderSpecList() throws RecognitionException, TokenStreamException {
		
		
		orderSpec();
		{
		_loop148:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				orderSpec();
			}
			else {
				break _loop148;
			}
			
		} while (true);
		}
	}
	
	public final void orderSpec() throws RecognitionException, TokenStreamException {
		
		
		exprSingle();
		orderModifier();
	}
	
	public final void orderModifier() throws RecognitionException, TokenStreamException {
		
		
		{
		switch ( LA(1)) {
		case LITERAL_ascending:
		{
			match(LITERAL_ascending);
			break;
		}
		case LITERAL_descending:
		{
			match(LITERAL_descending);
			break;
		}
		case LITERAL_collation:
		case COMMA:
		case LITERAL_empty:
		case LITERAL_return:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_empty:
		{
			match(LITERAL_empty);
			{
			switch ( LA(1)) {
			case LITERAL_greatest:
			{
				match(LITERAL_greatest);
				break;
			}
			case LITERAL_least:
			{
				match(LITERAL_least);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case LITERAL_collation:
		case COMMA:
		case LITERAL_return:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_collation:
		{
			match(LITERAL_collation);
			match(STRING_LITERAL);
			break;
		}
		case COMMA:
		case LITERAL_return:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void quantifiedInVarBinding() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		match(DOLLAR);
		name=qName();
		{
		switch ( LA(1)) {
		case LITERAL_as:
		{
			typeDeclaration();
			break;
		}
		case LITERAL_in:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_in);
		exprSingle();
	}
	
	public final void caseClause() throws RecognitionException, TokenStreamException {
		
		
			String varName=null; 
		
		
		match(LITERAL_case);
		{
		switch ( LA(1)) {
		case DOLLAR:
		{
			match(DOLLAR);
			varName=qName();
			match(LITERAL_as);
			break;
		}
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_empty:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_document:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case LITERAL_nillable:
		case LITERAL_collection:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		sequenceType();
		match(LITERAL_return);
		expr();
	}
	
	public final void andExpr() throws RecognitionException, TokenStreamException {
		
		
		instanceofExpr();
		{
		_loop173:
		do {
			if ((LA(1)==LITERAL_and)) {
				match(LITERAL_and);
				instanceofExpr();
			}
			else {
				break _loop173;
			}
			
		} while (true);
		}
	}
	
	public final void instanceofExpr() throws RecognitionException, TokenStreamException {
		
		
		treatExpr();
		{
		switch ( LA(1)) {
		case LITERAL_instance:
		{
			match(LITERAL_instance);
			match(LITERAL_of);
			sequenceType();
			break;
		}
		case EOF:
		case LITERAL_default:
		case LITERAL_collation:
		case RCURLY:
		case RPAREN:
		case COMMA:
		case LITERAL_empty:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case RPPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void treatExpr() throws RecognitionException, TokenStreamException {
		
		
		castableExpr();
		{
		switch ( LA(1)) {
		case LITERAL_treat:
		{
			match(LITERAL_treat);
			match(LITERAL_as);
			sequenceType();
			break;
		}
		case EOF:
		case LITERAL_default:
		case LITERAL_collation:
		case RCURLY:
		case RPAREN:
		case COMMA:
		case LITERAL_empty:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case RPPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void castableExpr() throws RecognitionException, TokenStreamException {
		
		
		castExpr();
		{
		switch ( LA(1)) {
		case LITERAL_castable:
		{
			match(LITERAL_castable);
			match(LITERAL_as);
			singleType();
			break;
		}
		case EOF:
		case LITERAL_default:
		case LITERAL_collation:
		case RCURLY:
		case RPAREN:
		case COMMA:
		case LITERAL_empty:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_treat:
		case RPPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void castExpr() throws RecognitionException, TokenStreamException {
		
		
		comparisonExpr();
		{
		switch ( LA(1)) {
		case LITERAL_cast:
		{
			match(LITERAL_cast);
			match(LITERAL_as);
			singleType();
			break;
		}
		case EOF:
		case LITERAL_default:
		case LITERAL_collation:
		case RCURLY:
		case RPAREN:
		case COMMA:
		case LITERAL_empty:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_treat:
		case LITERAL_castable:
		case RPPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void comparisonExpr() throws RecognitionException, TokenStreamException {
		
		
		rangeExpr();
		{
		switch ( LA(1)) {
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		{
			{
			{
			switch ( LA(1)) {
			case LITERAL_eq:
			{
				match(LITERAL_eq);
				break;
			}
			case LITERAL_ne:
			{
				match(LITERAL_ne);
				break;
			}
			case LITERAL_lt:
			{
				match(LITERAL_lt);
				break;
			}
			case LITERAL_le:
			{
				match(LITERAL_le);
				break;
			}
			case LITERAL_gt:
			{
				match(LITERAL_gt);
				break;
			}
			case LITERAL_ge:
			{
				match(LITERAL_ge);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			rangeExpr();
			}
			break;
		}
		case LITERAL_is:
		case LITERAL_isnot:
		{
			{
			{
			switch ( LA(1)) {
			case LITERAL_is:
			{
				match(LITERAL_is);
				break;
			}
			case LITERAL_isnot:
			{
				match(LITERAL_isnot);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			rangeExpr();
			}
			break;
		}
		case EOF:
		case LITERAL_default:
		case LITERAL_collation:
		case RCURLY:
		case RPAREN:
		case COMMA:
		case LITERAL_empty:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case RPPAREN:
		{
			break;
		}
		default:
			boolean synPredMatched185 = false;
			if (((LA(1)==LT))) {
				int _m185 = mark();
				synPredMatched185 = true;
				inputState.guessing++;
				try {
					{
					match(LT);
					match(LT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched185 = false;
				}
				rewind(_m185);
				inputState.guessing--;
			}
			if ( synPredMatched185 ) {
				match(LT);
				match(LT);
				rangeExpr();
			}
			else {
				boolean synPredMatched187 = false;
				if (((LA(1)==GT))) {
					int _m187 = mark();
					synPredMatched187 = true;
					inputState.guessing++;
					try {
						{
						match(GT);
						match(GT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched187 = false;
					}
					rewind(_m187);
					inputState.guessing--;
				}
				if ( synPredMatched187 ) {
					match(GT);
					match(GT);
					rangeExpr();
				}
				else if ((_tokenSet_5.member(LA(1)))) {
					{
					{
					switch ( LA(1)) {
					case EQ:
					{
						match(EQ);
						break;
					}
					case NEQ:
					{
						match(NEQ);
						break;
					}
					case GT:
					{
						match(GT);
						break;
					}
					case GTEQ:
					{
						match(GTEQ);
						break;
					}
					case LT:
					{
						match(LT);
						break;
					}
					case LTEQ:
					{
						match(LTEQ);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					rangeExpr();
					}
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}}
			}
		}
		
	public final void rangeExpr() throws RecognitionException, TokenStreamException {
		
		
		additiveExpr();
		{
		switch ( LA(1)) {
		case LITERAL_to:
		{
			match(LITERAL_to);
			additiveExpr();
			break;
		}
		case EOF:
		case LITERAL_default:
		case LITERAL_collation:
		case EQ:
		case RCURLY:
		case RPAREN:
		case COMMA:
		case LITERAL_empty:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LT:
		case GT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case NEQ:
		case GTEQ:
		case LTEQ:
		case LITERAL_is:
		case LITERAL_isnot:
		case RPPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void additiveExpr() throws RecognitionException, TokenStreamException {
		
		
		multiplicativeExpr();
		{
		_loop199:
		do {
			if ((LA(1)==PLUS||LA(1)==MINUS)) {
				{
				switch ( LA(1)) {
				case PLUS:
				{
					match(PLUS);
					break;
				}
				case MINUS:
				{
					match(MINUS);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				multiplicativeExpr();
			}
			else {
				break _loop199;
			}
			
		} while (true);
		}
	}
	
	public final void multiplicativeExpr() throws RecognitionException, TokenStreamException {
		
		
		unaryExpr();
		{
		_loop203:
		do {
			if ((_tokenSet_6.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case STAR:
				{
					match(STAR);
					break;
				}
				case LITERAL_div:
				{
					match(LITERAL_div);
					break;
				}
				case LITERAL_idiv:
				{
					match(LITERAL_idiv);
					break;
				}
				case LITERAL_mod:
				{
					match(LITERAL_mod);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				unaryExpr();
			}
			else {
				break _loop203;
			}
			
		} while (true);
		}
	}
	
	public final void unaryExpr() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop206:
		do {
			switch ( LA(1)) {
			case MINUS:
			{
				match(MINUS);
				break;
			}
			case PLUS:
			{
				match(PLUS);
				break;
			}
			default:
			{
				break _loop206;
			}
			}
		} while (true);
		}
		unionExpr();
	}
	
	public final void unionExpr() throws RecognitionException, TokenStreamException {
		
		
		intersectExceptExpr();
		{
		_loop210:
		do {
			if ((LA(1)==LITERAL_union||LA(1)==UNION)) {
				{
				switch ( LA(1)) {
				case LITERAL_union:
				{
					match(LITERAL_union);
					break;
				}
				case UNION:
				{
					match(UNION);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				intersectExceptExpr();
			}
			else {
				break _loop210;
			}
			
		} while (true);
		}
	}
	
	public final void intersectExceptExpr() throws RecognitionException, TokenStreamException {
		
		
		valueExpr();
		{
		_loop214:
		do {
			if ((LA(1)==LITERAL_intersect||LA(1)==LITERAL_except)) {
				{
				switch ( LA(1)) {
				case LITERAL_intersect:
				{
					match(LITERAL_intersect);
					break;
				}
				case LITERAL_except:
				{
					match(LITERAL_except);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				valueExpr();
			}
			else {
				break _loop214;
			}
			
		} while (true);
		}
	}
	
	public final void valueExpr() throws RecognitionException, TokenStreamException {
		
		
		pathExpr();
	}
	
	public final void pathExpr() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case STRING_LITERAL:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_empty:
		case STAR:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_nillable:
		case XML_CDATA:
		case LITERAL_collection:
		{
			relativePathExpr();
			break;
		}
		case DSLASH:
		{
			match(DSLASH);
			relativePathExpr();
			break;
		}
		default:
			boolean synPredMatched218 = false;
			if (((LA(1)==SLASH))) {
				int _m218 = mark();
				synPredMatched218 = true;
				inputState.guessing++;
				try {
					{
					match(SLASH);
					relativePathExpr();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched218 = false;
				}
				rewind(_m218);
				inputState.guessing--;
			}
			if ( synPredMatched218 ) {
				match(SLASH);
				relativePathExpr();
			}
			else if ((LA(1)==SLASH)) {
				match(SLASH);
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void relativePathExpr() throws RecognitionException, TokenStreamException {
		
		
		stepExpr();
		{
		_loop222:
		do {
			if ((LA(1)==SLASH||LA(1)==DSLASH)) {
				{
				switch ( LA(1)) {
				case SLASH:
				{
					match(SLASH);
					break;
				}
				case DSLASH:
				{
					match(DSLASH);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				stepExpr();
			}
			else {
				break _loop222;
			}
			
		} while (true);
		}
	}
	
	public final void stepExpr() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched226 = false;
		if (((_tokenSet_7.member(LA(1))))) {
			int _m226 = mark();
			synPredMatched226 = true;
			inputState.guessing++;
			try {
				{
				{
				switch ( LA(1)) {
				case LITERAL_text:
				{
					match(LITERAL_text);
					break;
				}
				case LITERAL_node:
				{
					match(LITERAL_node);
					break;
				}
				case LITERAL_element:
				{
					match(LITERAL_element);
					break;
				}
				case LITERAL_attribute:
				{
					match(LITERAL_attribute);
					break;
				}
				case LITERAL_comment:
				{
					match(LITERAL_comment);
					break;
				}
				case 43:
				{
					match(43);
					break;
				}
				case 41:
				{
					match(41);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched226 = false;
			}
			rewind(_m226);
			inputState.guessing--;
		}
		if ( synPredMatched226 ) {
			axisStep();
		}
		else {
			boolean synPredMatched229 = false;
			if (((_tokenSet_8.member(LA(1))))) {
				int _m229 = mark();
				synPredMatched229 = true;
				inputState.guessing++;
				try {
					{
					{
					switch ( LA(1)) {
					case LITERAL_element:
					{
						match(LITERAL_element);
						break;
					}
					case LITERAL_attribute:
					{
						match(LITERAL_attribute);
						break;
					}
					case LITERAL_text:
					{
						match(LITERAL_text);
						break;
					}
					case LITERAL_document:
					{
						match(LITERAL_document);
						break;
					}
					case 43:
					{
						match(43);
						break;
					}
					case LITERAL_comment:
					{
						match(LITERAL_comment);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(LCURLY);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched229 = false;
				}
				rewind(_m229);
				inputState.guessing--;
			}
			if ( synPredMatched229 ) {
				filterStep();
			}
			else {
				boolean synPredMatched232 = false;
				if (((_tokenSet_8.member(LA(1))))) {
					int _m232 = mark();
					synPredMatched232 = true;
					inputState.guessing++;
					try {
						{
						{
						switch ( LA(1)) {
						case LITERAL_element:
						{
							match(LITERAL_element);
							break;
						}
						case LITERAL_attribute:
						{
							match(LITERAL_attribute);
							break;
						}
						case 43:
						{
							match(43);
							break;
						}
						case LITERAL_namespace:
						{
							match(LITERAL_namespace);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						qName();
						match(LCURLY);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched232 = false;
					}
					rewind(_m232);
					inputState.guessing--;
				}
				if ( synPredMatched232 ) {
					filterStep();
				}
				else {
					boolean synPredMatched235 = false;
					if (((_tokenSet_8.member(LA(1))))) {
						int _m235 = mark();
						synPredMatched235 = true;
						inputState.guessing++;
						try {
							{
							switch ( LA(1)) {
							case DOLLAR:
							{
								match(DOLLAR);
								break;
							}
							case LITERAL_module:
							case LITERAL_xquery:
							case LITERAL_version:
							case LITERAL_import:
							case LITERAL_declare:
							case LITERAL_xmlspace:
							case LITERAL_namespace:
							case LITERAL_default:
							case LITERAL_collation:
							case LITERAL_element:
							case LITERAL_function:
							case LITERAL_define:
							case LITERAL_variable:
							case LITERAL_validation:
							case LITERAL_lax:
							case LITERAL_strict:
							case LITERAL_skip:
							case LITERAL_schema:
							case NCNAME:
							case LITERAL_at:
							case LITERAL_preserve:
							case LITERAL_strip:
							case LITERAL_external:
							case LITERAL_as:
							case LITERAL_empty:
							case LITERAL_item:
							case 41:
							case LITERAL_attribute:
							case 43:
							case LITERAL_comment:
							case LITERAL_text:
							case LITERAL_node:
							case LITERAL_for:
							case LITERAL_let:
							case LITERAL_some:
							case LITERAL_every:
							case LITERAL_typeswitch:
							case LITERAL_if:
							case LITERAL_try:
							case LITERAL_catch:
							case LITERAL_where:
							case LITERAL_return:
							case LITERAL_in:
							case LITERAL_stable:
							case LITERAL_order:
							case LITERAL_by:
							case LITERAL_ascending:
							case LITERAL_descending:
							case LITERAL_greatest:
							case LITERAL_least:
							case LITERAL_satisfies:
							case LITERAL_case:
							case LITERAL_then:
							case LITERAL_else:
							case LITERAL_or:
							case LITERAL_and:
							case LITERAL_instance:
							case LITERAL_of:
							case LITERAL_treat:
							case LITERAL_castable:
							case LITERAL_cast:
							case LITERAL_eq:
							case LITERAL_ne:
							case LITERAL_lt:
							case LITERAL_le:
							case LITERAL_gt:
							case LITERAL_ge:
							case LITERAL_is:
							case LITERAL_isnot:
							case LITERAL_to:
							case LITERAL_div:
							case LITERAL_idiv:
							case LITERAL_mod:
							case LITERAL_union:
							case LITERAL_intersect:
							case LITERAL_except:
							case LITERAL_document:
							case LITERAL_property:
							case LITERAL_child:
							case LITERAL_self:
							case LITERAL_descendant:
							case 113:
							case LITERAL_following:
							case 115:
							case LITERAL_parent:
							case LITERAL_ancestor:
							case 118:
							case LITERAL_preceding:
							case 120:
							case LITERAL_nillable:
							case LITERAL_collection:
							{
								{
								qName();
								match(LPAREN);
								}
								break;
							}
							case SELF:
							{
								match(SELF);
								break;
							}
							case LPAREN:
							{
								match(LPAREN);
								break;
							}
							case STRING_LITERAL:
							case DOUBLE_LITERAL:
							case DECIMAL_LITERAL:
							case INTEGER_LITERAL:
							{
								literal();
								break;
							}
							case XML_COMMENT:
							{
								match(XML_COMMENT);
								break;
							}
							case LT:
							{
								match(LT);
								break;
							}
							case XML_PI:
							{
								match(XML_PI);
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
						}
						catch (RecognitionException pe) {
							synPredMatched235 = false;
						}
						rewind(_m235);
						inputState.guessing--;
					}
					if ( synPredMatched235 ) {
						filterStep();
					}
					else if ((_tokenSet_7.member(LA(1)))) {
						axisStep();
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}}}
				}
				
	public final void axisStep() throws RecognitionException, TokenStreamException {
		
		
		{
		forwardOrReverseStep();
		}
		predicates();
	}
	
	public final void filterStep() throws RecognitionException, TokenStreamException {
		
		
		primaryExpr();
		predicates();
	}
	
	public final void literal() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case STRING_LITERAL:
		{
			match(STRING_LITERAL);
			break;
		}
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		{
			numericLiteral();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void forwardOrReverseStep() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched244 = false;
		if (((_tokenSet_9.member(LA(1))))) {
			int _m244 = mark();
			synPredMatched244 = true;
			inputState.guessing++;
			try {
				{
				forwardAxisSpecifier();
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched244 = false;
			}
			rewind(_m244);
			inputState.guessing--;
		}
		if ( synPredMatched244 ) {
			forwardAxis();
			nodeTest();
		}
		else {
			boolean synPredMatched246 = false;
			if ((((LA(1) >= LITERAL_parent && LA(1) <= 120)))) {
				int _m246 = mark();
				synPredMatched246 = true;
				inputState.guessing++;
				try {
					{
					reverseAxisSpecifier();
					match(COLON);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched246 = false;
				}
				rewind(_m246);
				inputState.guessing--;
			}
			if ( synPredMatched246 ) {
				reverseAxis();
				nodeTest();
			}
			else {
				boolean synPredMatched248 = false;
				if (((LA(1)==LITERAL_property))) {
					int _m248 = mark();
					synPredMatched248 = true;
					inputState.guessing++;
					try {
						{
						propertyAxisSpecifier();
						match(COLON);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched248 = false;
					}
					rewind(_m248);
					inputState.guessing--;
				}
				if ( synPredMatched248 ) {
					propertyAxis();
					nodeTest();
				}
				else if ((_tokenSet_7.member(LA(1)))) {
					abbrevStep();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}}
			}
			
	public final void predicates() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop240:
		do {
			if ((LA(1)==LPPAREN)) {
				predicate();
			}
			else {
				break _loop240;
			}
			
		} while (true);
		}
	}
	
	public final void predicate() throws RecognitionException, TokenStreamException {
		
		
		match(LPPAREN);
		expr();
		match(RPPAREN);
	}
	
	public final void forwardAxisSpecifier() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_child:
		{
			match(LITERAL_child);
			break;
		}
		case LITERAL_self:
		{
			match(LITERAL_self);
			break;
		}
		case LITERAL_attribute:
		{
			match(LITERAL_attribute);
			break;
		}
		case LITERAL_descendant:
		{
			match(LITERAL_descendant);
			break;
		}
		case 113:
		{
			match(113);
			break;
		}
		case LITERAL_following:
		{
			match(LITERAL_following);
			break;
		}
		case 115:
		{
			match(115);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void forwardAxis() throws RecognitionException, TokenStreamException {
		
		
		forwardAxisSpecifier();
		match(COLON);
		match(COLON);
	}
	
	public final void nodeTest() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched259 = false;
		if (((_tokenSet_3.member(LA(1))))) {
			int _m259 = mark();
			synPredMatched259 = true;
			inputState.guessing++;
			try {
				{
				match(41);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched259 = false;
			}
			rewind(_m259);
			inputState.guessing--;
		}
		if ( synPredMatched259 ) {
			kindTest();
		}
		else {
			boolean synPredMatched261 = false;
			if (((_tokenSet_3.member(LA(1))))) {
				int _m261 = mark();
				synPredMatched261 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_element);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched261 = false;
				}
				rewind(_m261);
				inputState.guessing--;
			}
			if ( synPredMatched261 ) {
				kindTest();
			}
			else {
				boolean synPredMatched263 = false;
				if (((_tokenSet_3.member(LA(1))))) {
					int _m263 = mark();
					synPredMatched263 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_attribute);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched263 = false;
					}
					rewind(_m263);
					inputState.guessing--;
				}
				if ( synPredMatched263 ) {
					kindTest();
				}
				else {
					boolean synPredMatched265 = false;
					if (((_tokenSet_3.member(LA(1))))) {
						int _m265 = mark();
						synPredMatched265 = true;
						inputState.guessing++;
						try {
							{
							match(43);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched265 = false;
						}
						rewind(_m265);
						inputState.guessing--;
					}
					if ( synPredMatched265 ) {
						kindTest();
					}
					else {
						boolean synPredMatched267 = false;
						if (((_tokenSet_3.member(LA(1))))) {
							int _m267 = mark();
							synPredMatched267 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_comment);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched267 = false;
							}
							rewind(_m267);
							inputState.guessing--;
						}
						if ( synPredMatched267 ) {
							kindTest();
						}
						else {
							boolean synPredMatched269 = false;
							if (((_tokenSet_3.member(LA(1))))) {
								int _m269 = mark();
								synPredMatched269 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_text);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched269 = false;
								}
								rewind(_m269);
								inputState.guessing--;
							}
							if ( synPredMatched269 ) {
								kindTest();
							}
							else {
								boolean synPredMatched271 = false;
								if (((_tokenSet_3.member(LA(1))))) {
									int _m271 = mark();
									synPredMatched271 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_node);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched271 = false;
									}
									rewind(_m271);
									inputState.guessing--;
								}
								if ( synPredMatched271 ) {
									kindTest();
								}
								else if ((_tokenSet_10.member(LA(1)))) {
									nameTest();
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}}}
							}
							
	public final void reverseAxisSpecifier() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_parent:
		{
			match(LITERAL_parent);
			break;
		}
		case LITERAL_ancestor:
		{
			match(LITERAL_ancestor);
			break;
		}
		case 118:
		{
			match(118);
			break;
		}
		case LITERAL_preceding:
		{
			match(LITERAL_preceding);
			break;
		}
		case 120:
		{
			match(120);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void reverseAxis() throws RecognitionException, TokenStreamException {
		
		
		reverseAxisSpecifier();
		match(COLON);
		match(COLON);
	}
	
	public final void propertyAxisSpecifier() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_property);
	}
	
	public final void propertyAxis() throws RecognitionException, TokenStreamException {
		
		
		propertyAxisSpecifier();
		match(COLON);
		match(COLON);
	}
	
	public final void abbrevStep() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_empty:
		case STAR:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_document:
		case AT:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case LITERAL_nillable:
		case LITERAL_collection:
		{
			{
			switch ( LA(1)) {
			case AT:
			{
				match(AT);
				break;
			}
			case LITERAL_module:
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_import:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_namespace:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_define:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
			case LITERAL_schema:
			case NCNAME:
			case LITERAL_at:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_external:
			case LITERAL_as:
			case LITERAL_empty:
			case STAR:
			case LITERAL_item:
			case 41:
			case LITERAL_attribute:
			case 43:
			case LITERAL_comment:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_where:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_isnot:
			case LITERAL_to:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_document:
			case LITERAL_property:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 113:
			case LITERAL_following:
			case 115:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 118:
			case LITERAL_preceding:
			case 120:
			case LITERAL_nillable:
			case LITERAL_collection:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			nodeTest();
			break;
		}
		case PARENT:
		{
			match(PARENT);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void nameTest() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
			String prefix = null;
		
		
		boolean synPredMatched275 = false;
		if (((_tokenSet_10.member(LA(1))))) {
			int _m275 = mark();
			synPredMatched275 = true;
			inputState.guessing++;
			try {
				{
				switch ( LA(1)) {
				case LITERAL_module:
				case LITERAL_xquery:
				case LITERAL_version:
				case LITERAL_import:
				case LITERAL_declare:
				case LITERAL_xmlspace:
				case LITERAL_namespace:
				case LITERAL_default:
				case LITERAL_collation:
				case LITERAL_element:
				case LITERAL_function:
				case LITERAL_define:
				case LITERAL_variable:
				case LITERAL_validation:
				case LITERAL_lax:
				case LITERAL_strict:
				case LITERAL_skip:
				case LITERAL_schema:
				case NCNAME:
				case LITERAL_at:
				case LITERAL_preserve:
				case LITERAL_strip:
				case LITERAL_external:
				case LITERAL_as:
				case LITERAL_empty:
				case LITERAL_item:
				case 41:
				case LITERAL_attribute:
				case 43:
				case LITERAL_comment:
				case LITERAL_text:
				case LITERAL_node:
				case LITERAL_for:
				case LITERAL_let:
				case LITERAL_some:
				case LITERAL_every:
				case LITERAL_typeswitch:
				case LITERAL_if:
				case LITERAL_try:
				case LITERAL_catch:
				case LITERAL_where:
				case LITERAL_return:
				case LITERAL_in:
				case LITERAL_stable:
				case LITERAL_order:
				case LITERAL_by:
				case LITERAL_ascending:
				case LITERAL_descending:
				case LITERAL_greatest:
				case LITERAL_least:
				case LITERAL_satisfies:
				case LITERAL_case:
				case LITERAL_then:
				case LITERAL_else:
				case LITERAL_or:
				case LITERAL_and:
				case LITERAL_instance:
				case LITERAL_of:
				case LITERAL_treat:
				case LITERAL_castable:
				case LITERAL_cast:
				case LITERAL_eq:
				case LITERAL_ne:
				case LITERAL_lt:
				case LITERAL_le:
				case LITERAL_gt:
				case LITERAL_ge:
				case LITERAL_is:
				case LITERAL_isnot:
				case LITERAL_to:
				case LITERAL_div:
				case LITERAL_idiv:
				case LITERAL_mod:
				case LITERAL_union:
				case LITERAL_intersect:
				case LITERAL_except:
				case LITERAL_document:
				case LITERAL_property:
				case LITERAL_child:
				case LITERAL_self:
				case LITERAL_descendant:
				case 113:
				case LITERAL_following:
				case 115:
				case LITERAL_parent:
				case LITERAL_ancestor:
				case 118:
				case LITERAL_preceding:
				case 120:
				case LITERAL_nillable:
				case LITERAL_collection:
				{
					{
					prefix=ncnameOrKeyword();
					match(COLON);
					match(STAR);
					}
					break;
				}
				case STAR:
				{
					match(STAR);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
			}
			catch (RecognitionException pe) {
				synPredMatched275 = false;
			}
			rewind(_m275);
			inputState.guessing--;
		}
		if ( synPredMatched275 ) {
			wildcard();
		}
		else if ((_tokenSet_2.member(LA(1)))) {
			name=qName();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void wildcard() throws RecognitionException, TokenStreamException {
		
		String name=null;
		
		boolean synPredMatched278 = false;
		if (((LA(1)==STAR))) {
			int _m278 = mark();
			synPredMatched278 = true;
			inputState.guessing++;
			try {
				{
				match(STAR);
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched278 = false;
			}
			rewind(_m278);
			inputState.guessing--;
		}
		if ( synPredMatched278 ) {
			match(STAR);
			match(COLON);
			name=ncnameOrKeyword();
		}
		else if ((_tokenSet_2.member(LA(1)))) {
			name=ncnameOrKeyword();
			match(COLON);
			match(STAR);
		}
		else if ((LA(1)==STAR)) {
			match(STAR);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void primaryExpr() throws RecognitionException, TokenStreamException {
		
		String name=null;
		
		switch ( LA(1)) {
		case LT:
		case XML_COMMENT:
		case XML_PI:
		case XML_CDATA:
		{
			constructor();
			break;
		}
		case SELF:
		{
			contextItemExpr();
			break;
		}
		case LPAREN:
		{
			parenthesizedExpr();
			break;
		}
		case DOLLAR:
		{
			match(DOLLAR);
			name=qName();
			if ( inputState.guessing==0 ) {
				
						context.setReferencedVariable(name);
					
			}
			break;
		}
		case STRING_LITERAL:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		{
			literal();
			break;
		}
		default:
			boolean synPredMatched283 = false;
			if (((_tokenSet_11.member(LA(1))))) {
				int _m283 = mark();
				synPredMatched283 = true;
				inputState.guessing++;
				try {
					{
					{
					switch ( LA(1)) {
					case LITERAL_element:
					{
						match(LITERAL_element);
						break;
					}
					case LITERAL_attribute:
					{
						match(LITERAL_attribute);
						break;
					}
					case LITERAL_text:
					{
						match(LITERAL_text);
						break;
					}
					case LITERAL_document:
					{
						match(LITERAL_document);
						break;
					}
					case 43:
					{
						match(43);
						break;
					}
					case LITERAL_comment:
					{
						match(LITERAL_comment);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(LCURLY);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched283 = false;
				}
				rewind(_m283);
				inputState.guessing--;
			}
			if ( synPredMatched283 ) {
				computedConstructor();
			}
			else {
				boolean synPredMatched286 = false;
				if (((_tokenSet_11.member(LA(1))))) {
					int _m286 = mark();
					synPredMatched286 = true;
					inputState.guessing++;
					try {
						{
						{
						switch ( LA(1)) {
						case LITERAL_element:
						{
							match(LITERAL_element);
							break;
						}
						case LITERAL_attribute:
						{
							match(LITERAL_attribute);
							break;
						}
						case 43:
						{
							match(43);
							break;
						}
						case LITERAL_namespace:
						{
							match(LITERAL_namespace);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						qName();
						match(LCURLY);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched286 = false;
					}
					rewind(_m286);
					inputState.guessing--;
				}
				if ( synPredMatched286 ) {
					computedConstructor();
				}
				else if ((_tokenSet_2.member(LA(1)))) {
					functionCall();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}}
		}
		
	public final void computedConstructor() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_element:
		{
			compElemConstructor();
			break;
		}
		case LITERAL_attribute:
		{
			compAttrConstructor();
			break;
		}
		case LITERAL_text:
		{
			compTextConstructor();
			break;
		}
		case LITERAL_document:
		{
			compDocumentConstructor();
			break;
		}
		case 43:
		{
			compXmlPI();
			break;
		}
		case LITERAL_comment:
		{
			compXmlComment();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void constructor() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LT:
		{
			elementConstructor();
			break;
		}
		case XML_COMMENT:
		{
			xmlComment();
			break;
		}
		case XML_PI:
		{
			xmlPI();
			break;
		}
		case XML_CDATA:
		{
			cdataSection();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void functionCall() throws RecognitionException, TokenStreamException {
		
		
			String fnName= null; 
		
		
		fnName=qName();
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case STRING_LITERAL:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_empty:
		case STAR:
		case PLUS:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case MINUS:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case SLASH:
		case DSLASH:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_nillable:
		case XML_CDATA:
		case LITERAL_collection:
		{
			functionParameters();
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
					context.setInvokedFunction(fnName);
				
		}
	}
	
	public final void contextItemExpr() throws RecognitionException, TokenStreamException {
		
		
		match(SELF);
	}
	
	public final void parenthesizedExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case STRING_LITERAL:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_empty:
		case STAR:
		case PLUS:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case MINUS:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case SLASH:
		case DSLASH:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_nillable:
		case XML_CDATA:
		case LITERAL_collection:
		{
			expr();
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
	}
	
	public final void numericLiteral() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case DOUBLE_LITERAL:
		{
			match(DOUBLE_LITERAL);
			break;
		}
		case DECIMAL_LITERAL:
		{
			match(DECIMAL_LITERAL);
			break;
		}
		case INTEGER_LITERAL:
		{
			match(INTEGER_LITERAL);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void functionParameters() throws RecognitionException, TokenStreamException {
		
		
		exprSingle();
		{
		_loop295:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop295;
			}
			
		} while (true);
		}
	}
	
	public final void textTest() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_text);
		match(LPAREN);
		match(RPAREN);
	}
	
	public final void anyKindTest() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_node);
		match(LPAREN);
		match(RPAREN);
	}
	
	public final void elementTest() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(LITERAL_element);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_empty:
		case STAR:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_document:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case LITERAL_nillable:
		case LITERAL_collection:
		{
			elementNameOrWildcard();
			{
			switch ( LA(1)) {
			case COMMA:
			{
				match(COMMA);
				tmpStr=qName();
				{
				switch ( LA(1)) {
				case LITERAL_nillable:
				{
					match(LITERAL_nillable);
					break;
				}
				case RPAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
	}
	
	public final void attributeTest() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(LITERAL_attribute);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case AT:
		{
			match(AT);
			attributeNameOrWildcard();
			{
			switch ( LA(1)) {
			case COMMA:
			{
				match(COMMA);
				tmpStr=qName();
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
	}
	
	public final void commentTest() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_comment);
		match(LPAREN);
		match(RPAREN);
	}
	
	public final void piTest() throws RecognitionException, TokenStreamException {
		
		
		match(43);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case STRING_LITERAL:
		{
			match(STRING_LITERAL);
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
	}
	
	public final void documentTest() throws RecognitionException, TokenStreamException {
		
		
		match(41);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_element:
		{
			elementTest();
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
	}
	
	public final void elementNameOrWildcard() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		switch ( LA(1)) {
		case STAR:
		{
			match(STAR);
			break;
		}
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_empty:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_document:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case LITERAL_nillable:
		case LITERAL_collection:
		{
			name=qName();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void attributeNameOrWildcard() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		switch ( LA(1)) {
		case STAR:
		{
			match(STAR);
			break;
		}
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_empty:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_document:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case LITERAL_nillable:
		case LITERAL_collection:
		{
			name=qName();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void elementConstructor() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched350 = false;
		if (((LA(1)==LT))) {
			int _m350 = mark();
			synPredMatched350 = true;
			inputState.guessing++;
			try {
				{
				match(LT);
				qName();
				{
				match(_tokenSet_12);
				}
				}
			}
			catch (RecognitionException pe) {
				synPredMatched350 = false;
			}
			rewind(_m350);
			inputState.guessing--;
		}
		if ( synPredMatched350 ) {
			elementWithAttributes();
		}
		else if ((LA(1)==LT)) {
			elementWithoutAttributes();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void xmlComment() throws RecognitionException, TokenStreamException {
		
		
		match(XML_COMMENT);
		match(XML_COMMENT_END);
	}
	
	public final void xmlPI() throws RecognitionException, TokenStreamException {
		
		
		match(XML_PI);
		match(XML_PI_END);
	}
	
	public final void cdataSection() throws RecognitionException, TokenStreamException {
		
		
		match(XML_CDATA);
		match(XML_CDATA_END);
	}
	
	public final void compElemConstructor() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		boolean synPredMatched322 = false;
		if (((LA(1)==LITERAL_element))) {
			int _m322 = mark();
			synPredMatched322 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_element);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched322 = false;
			}
			rewind(_m322);
			inputState.guessing--;
		}
		if ( synPredMatched322 ) {
			match(LITERAL_element);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_module:
			case LITERAL_xquery:
			case LITERAL_version:
			case STRING_LITERAL:
			case LITERAL_import:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_namespace:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_define:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
			case LITERAL_schema:
			case NCNAME:
			case LITERAL_at:
			case LITERAL_preserve:
			case LITERAL_strip:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_empty:
			case STAR:
			case PLUS:
			case LITERAL_item:
			case 41:
			case LITERAL_attribute:
			case 43:
			case LITERAL_comment:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_where:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_isnot:
			case LITERAL_to:
			case MINUS:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case SLASH:
			case DSLASH:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_property:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 113:
			case LITERAL_following:
			case 115:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 118:
			case LITERAL_preceding:
			case 120:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_nillable:
			case XML_CDATA:
			case LITERAL_collection:
			{
				expr();
				break;
			}
			case RCURLY:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RCURLY);
		}
		else if ((LA(1)==LITERAL_element)) {
			match(LITERAL_element);
			name=qName();
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_module:
			case LITERAL_xquery:
			case LITERAL_version:
			case STRING_LITERAL:
			case LITERAL_import:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_namespace:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_define:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
			case LITERAL_schema:
			case NCNAME:
			case LITERAL_at:
			case LITERAL_preserve:
			case LITERAL_strip:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_empty:
			case STAR:
			case PLUS:
			case LITERAL_item:
			case 41:
			case LITERAL_attribute:
			case 43:
			case LITERAL_comment:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_where:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_isnot:
			case LITERAL_to:
			case MINUS:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case SLASH:
			case DSLASH:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_property:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 113:
			case LITERAL_following:
			case 115:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 118:
			case LITERAL_preceding:
			case 120:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_nillable:
			case XML_CDATA:
			case LITERAL_collection:
			{
				expr();
				break;
			}
			case RCURLY:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RCURLY);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void compAttrConstructor() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		boolean synPredMatched336 = false;
		if (((LA(1)==LITERAL_attribute))) {
			int _m336 = mark();
			synPredMatched336 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_attribute);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched336 = false;
			}
			rewind(_m336);
			inputState.guessing--;
		}
		if ( synPredMatched336 ) {
			match(LITERAL_attribute);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_module:
			case LITERAL_xquery:
			case LITERAL_version:
			case STRING_LITERAL:
			case LITERAL_import:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_namespace:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_define:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
			case LITERAL_schema:
			case NCNAME:
			case LITERAL_at:
			case LITERAL_preserve:
			case LITERAL_strip:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_empty:
			case STAR:
			case PLUS:
			case LITERAL_item:
			case 41:
			case LITERAL_attribute:
			case 43:
			case LITERAL_comment:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_where:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_isnot:
			case LITERAL_to:
			case MINUS:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case SLASH:
			case DSLASH:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_property:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 113:
			case LITERAL_following:
			case 115:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 118:
			case LITERAL_preceding:
			case 120:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_nillable:
			case XML_CDATA:
			case LITERAL_collection:
			{
				expr();
				break;
			}
			case RCURLY:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RCURLY);
		}
		else if ((LA(1)==LITERAL_attribute)) {
			match(LITERAL_attribute);
			name=qName();
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_module:
			case LITERAL_xquery:
			case LITERAL_version:
			case STRING_LITERAL:
			case LITERAL_import:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_namespace:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_define:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
			case LITERAL_schema:
			case NCNAME:
			case LITERAL_at:
			case LITERAL_preserve:
			case LITERAL_strip:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_empty:
			case STAR:
			case PLUS:
			case LITERAL_item:
			case 41:
			case LITERAL_attribute:
			case 43:
			case LITERAL_comment:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_where:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_isnot:
			case LITERAL_to:
			case MINUS:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case SLASH:
			case DSLASH:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_property:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 113:
			case LITERAL_following:
			case 115:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 118:
			case LITERAL_preceding:
			case 120:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_nillable:
			case XML_CDATA:
			case LITERAL_collection:
			{
				expr();
				break;
			}
			case RCURLY:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RCURLY);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void compTextConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_text);
		match(LCURLY);
		{
		switch ( LA(1)) {
		case LITERAL_module:
		case LITERAL_xquery:
		case LITERAL_version:
		case STRING_LITERAL:
		case LITERAL_import:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_namespace:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_define:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_preserve:
		case LITERAL_strip:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_empty:
		case STAR:
		case PLUS:
		case LITERAL_item:
		case 41:
		case LITERAL_attribute:
		case 43:
		case LITERAL_comment:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_where:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_isnot:
		case LITERAL_to:
		case MINUS:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case SLASH:
		case DSLASH:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_property:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 113:
		case LITERAL_following:
		case 115:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 118:
		case LITERAL_preceding:
		case 120:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_nillable:
		case XML_CDATA:
		case LITERAL_collection:
		{
			expr();
			break;
		}
		case RCURLY:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RCURLY);
	}
	
	public final void compDocumentConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_document);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void compXmlPI() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		boolean synPredMatched344 = false;
		if (((LA(1)==43))) {
			int _m344 = mark();
			synPredMatched344 = true;
			inputState.guessing++;
			try {
				{
				match(43);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched344 = false;
			}
			rewind(_m344);
			inputState.guessing--;
		}
		if ( synPredMatched344 ) {
			match(43);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			expr();
			match(RCURLY);
		}
		else if ((LA(1)==43)) {
			match(43);
			name=qName();
			match(LCURLY);
			expr();
			match(RCURLY);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void compXmlComment() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_comment);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void compElemBody() throws RecognitionException, TokenStreamException {
		
		
		{
		boolean synPredMatched328 = false;
		if (((LA(1)==LITERAL_namespace))) {
			int _m328 = mark();
			synPredMatched328 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_namespace);
				ncnameOrKeyword();
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched328 = false;
			}
			rewind(_m328);
			inputState.guessing--;
		}
		if ( synPredMatched328 ) {
			localNamespaceDecl();
		}
		else if ((_tokenSet_4.member(LA(1)))) {
			exprSingle();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		_loop333:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				{
				boolean synPredMatched332 = false;
				if (((LA(1)==LITERAL_namespace))) {
					int _m332 = mark();
					synPredMatched332 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_namespace);
						ncnameOrKeyword();
						match(LCURLY);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched332 = false;
					}
					rewind(_m332);
					inputState.guessing--;
				}
				if ( synPredMatched332 ) {
					localNamespaceDecl();
				}
				else if ((_tokenSet_4.member(LA(1)))) {
					exprSingle();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else {
				break _loop333;
			}
			
		} while (true);
		}
	}
	
	public final void localNamespaceDecl() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		match(LITERAL_namespace);
		name=ncnameOrKeyword();
		match(LCURLY);
		match(STRING_LITERAL);
		match(RCURLY);
	}
	
	public final void elementWithAttributes() throws RecognitionException, TokenStreamException {
		
		
			String name= null; 
		
		
		match(LT);
		name=qName();
		attributeList();
		{
		switch ( LA(1)) {
		case SLASH:
		{
			{
			match(SLASH);
			match(GT);
			if ( inputState.guessing==0 ) {
				
								if (!elementStack.isEmpty())
									lexer.inElementContent= true;
							
			}
			}
			break;
		}
		case GT:
		{
			{
			match(GT);
			if ( inputState.guessing==0 ) {
				
								elementStack.push(name);
								lexer.inElementContent= true;
							
			}
			mixedElementContent();
			match(END_TAG_START);
			name=qName();
			match(GT);
			if ( inputState.guessing==0 ) {
				
								if (elementStack.isEmpty())
									{}
								String prev= (String) elementStack.pop();
								if (!prev.equals(name))
									{}
								if (!elementStack.isEmpty()) {
									lexer.inElementContent= true;
								}
							
			}
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void elementWithoutAttributes() throws RecognitionException, TokenStreamException {
		
		
			String name= null; 
		
		
		match(LT);
		name=qName();
		{
		switch ( LA(1)) {
		case SLASH:
		{
			{
			match(SLASH);
			match(GT);
			if ( inputState.guessing==0 ) {
				
								if (!elementStack.isEmpty())
									lexer.inElementContent= true;
							
			}
			}
			break;
		}
		case GT:
		{
			{
			match(GT);
			if ( inputState.guessing==0 ) {
				
								elementStack.push(name);
								lexer.inElementContent= true;
							
			}
			mixedElementContent();
			match(END_TAG_START);
			name=qName();
			match(GT);
			if ( inputState.guessing==0 ) {
				
								if (elementStack.isEmpty())
									{}
								String prev= (String) elementStack.pop();
								if (!prev.equals(name))
									{}
								if (!elementStack.isEmpty()) {
									lexer.inElementContent= true;
								}
							
			}
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void mixedElementContent() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop376:
		do {
			if ((_tokenSet_13.member(LA(1)))) {
				elementContent();
			}
			else {
				break _loop376;
			}
			
		} while (true);
		}
	}
	
	public final void attributeList() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt361=0;
		_loop361:
		do {
			if ((_tokenSet_2.member(LA(1)))) {
				attributeDef();
			}
			else {
				if ( _cnt361>=1 ) { break _loop361; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt361++;
		} while (true);
		}
	}
	
	public final void attributeDef() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
			lexer.parseStringLiterals= false;
		
		
		name=qName();
		match(EQ);
		attributeValue();
	}
	
	public final void attributeValue() throws RecognitionException, TokenStreamException {
		
		
		{
		switch ( LA(1)) {
		case QUOT:
		{
			match(QUOT);
			if ( inputState.guessing==0 ) {
				
							lexer.inAttributeContent= true; 
							lexer.attrDelimChar = '"';
						
			}
			{
			_loop366:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==QUOT_ATTRIBUTE_CONTENT)) {
					quotAttrValueContent();
				}
				else {
					break _loop366;
				}
				
			} while (true);
			}
			match(QUOT);
			if ( inputState.guessing==0 ) {
				
							lexer.parseStringLiterals= true;
							lexer.inAttributeContent= false;
						
			}
			break;
		}
		case APOS:
		{
			match(APOS);
			if ( inputState.guessing==0 ) {
				
							lexer.inAttributeContent= true; 
							lexer.attrDelimChar = '\'';
						
			}
			{
			_loop368:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==APOS_ATTRIBUTE_CONTENT)) {
					aposAttrValueContent();
				}
				else {
					break _loop368;
				}
				
			} while (true);
			}
			match(APOS);
			if ( inputState.guessing==0 ) {
				
							lexer.parseStringLiterals= true;
							lexer.inAttributeContent= false;
						
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void quotAttrValueContent() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case QUOT_ATTRIBUTE_CONTENT:
		{
			match(QUOT_ATTRIBUTE_CONTENT);
			break;
		}
		case LCURLY:
		case RCURLY:
		{
			attrCommonContent();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void aposAttrValueContent() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case APOS_ATTRIBUTE_CONTENT:
		{
			match(APOS_ATTRIBUTE_CONTENT);
			break;
		}
		case LCURLY:
		case RCURLY:
		{
			attrCommonContent();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void attrCommonContent() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched373 = false;
		if (((LA(1)==LCURLY))) {
			int _m373 = mark();
			synPredMatched373 = true;
			inputState.guessing++;
			try {
				{
				match(LCURLY);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched373 = false;
			}
			rewind(_m373);
			inputState.guessing--;
		}
		if ( synPredMatched373 ) {
			match(LCURLY);
			match(LCURLY);
			if ( inputState.guessing==0 ) {
					
						lexer.inAttributeContent= true;
						lexer.parseStringLiterals = false; 
					
			}
		}
		else if ((LA(1)==RCURLY)) {
			match(RCURLY);
			match(RCURLY);
		}
		else if ((LA(1)==LCURLY)) {
			attributeEnclosedExpr();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void attributeEnclosedExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LCURLY);
		if ( inputState.guessing==0 ) {
			
					lexer.inAttributeContent= false;
					lexer.parseStringLiterals = true;
				
		}
		expr();
		match(RCURLY);
		if ( inputState.guessing==0 ) {
			
					lexer.inAttributeContent= true;
					lexer.parseStringLiterals = false;
				
		}
	}
	
	public final void elementContent() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LT:
		{
			elementConstructor();
			break;
		}
		case RCURLY:
		{
			match(RCURLY);
			match(RCURLY);
			break;
		}
		case ELEMENT_CONTENT:
		{
			match(ELEMENT_CONTENT);
			break;
		}
		case XML_COMMENT:
		{
			xmlComment();
			break;
		}
		case XML_PI:
		{
			xmlPI();
			break;
		}
		case XML_CDATA:
		{
			cdataSection();
			break;
		}
		default:
			boolean synPredMatched379 = false;
			if (((LA(1)==LCURLY))) {
				int _m379 = mark();
				synPredMatched379 = true;
				inputState.guessing++;
				try {
					{
					match(LCURLY);
					match(LCURLY);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched379 = false;
				}
				rewind(_m379);
				inputState.guessing--;
			}
			if ( synPredMatched379 ) {
				match(LCURLY);
				match(LCURLY);
				if ( inputState.guessing==0 ) {
						
							lexer.inElementContent= true;
						
				}
			}
			else if ((LA(1)==LCURLY)) {
				enclosedExpr();
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void enclosedExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LCURLY);
		if ( inputState.guessing==0 ) {
			
					globalStack.push(elementStack);
					elementStack= new Stack();
					lexer.inElementContent= false;
				
		}
		expr();
		match(RCURLY);
		if ( inputState.guessing==0 ) {
			
					elementStack= (Stack) globalStack.pop();
					lexer.inElementContent= true;
				
		}
	}
	
	public final String  reservedKeywords() throws RecognitionException, TokenStreamException {
		String name;
		
		name= null;
		
		switch ( LA(1)) {
		case LITERAL_ancestor:
		{
			match(LITERAL_ancestor);
			if ( inputState.guessing==0 ) {
				name= "ancestor";
			}
			break;
		}
		case 118:
		{
			match(118);
			if ( inputState.guessing==0 ) {
				name= "ancestor-or-self";
			}
			break;
		}
		case LITERAL_and:
		{
			match(LITERAL_and);
			if ( inputState.guessing==0 ) {
				name= "and";
			}
			break;
		}
		case LITERAL_as:
		{
			match(LITERAL_as);
			if ( inputState.guessing==0 ) {
				name = "as";
			}
			break;
		}
		case LITERAL_ascending:
		{
			match(LITERAL_ascending);
			if ( inputState.guessing==0 ) {
				name = "ascending";
			}
			break;
		}
		case LITERAL_at:
		{
			match(LITERAL_at);
			if ( inputState.guessing==0 ) {
				name = "at";
			}
			break;
		}
		case LITERAL_attribute:
		{
			match(LITERAL_attribute);
			if ( inputState.guessing==0 ) {
				name= "attribute";
			}
			break;
		}
		case LITERAL_by:
		{
			match(LITERAL_by);
			if ( inputState.guessing==0 ) {
				name = "by";
			}
			break;
		}
		case LITERAL_case:
		{
			match(LITERAL_case);
			if ( inputState.guessing==0 ) {
				name = "case";
			}
			break;
		}
		case LITERAL_cast:
		{
			match(LITERAL_cast);
			if ( inputState.guessing==0 ) {
				name = "cast";
			}
			break;
		}
		case LITERAL_castable:
		{
			match(LITERAL_castable);
			if ( inputState.guessing==0 ) {
				name = "castable";
			}
			break;
		}
		case LITERAL_catch:
		{
			match(LITERAL_catch);
			if ( inputState.guessing==0 ) {
				name = "catch";
			}
			break;
		}
		case LITERAL_child:
		{
			match(LITERAL_child);
			if ( inputState.guessing==0 ) {
				name= "child";
			}
			break;
		}
		case LITERAL_collation:
		{
			match(LITERAL_collation);
			if ( inputState.guessing==0 ) {
				name = "collation";
			}
			break;
		}
		case LITERAL_collection:
		{
			match(LITERAL_collection);
			if ( inputState.guessing==0 ) {
				name= "collection";
			}
			break;
		}
		case LITERAL_comment:
		{
			match(LITERAL_comment);
			if ( inputState.guessing==0 ) {
				name= "comment";
			}
			break;
		}
		case LITERAL_declare:
		{
			match(LITERAL_declare);
			if ( inputState.guessing==0 ) {
				name = "declare";
			}
			break;
		}
		case LITERAL_default:
		{
			match(LITERAL_default);
			if ( inputState.guessing==0 ) {
				name= "default";
			}
			break;
		}
		case LITERAL_define:
		{
			match(LITERAL_define);
			if ( inputState.guessing==0 ) {
				name = "define";
			}
			break;
		}
		case LITERAL_descendant:
		{
			match(LITERAL_descendant);
			if ( inputState.guessing==0 ) {
				name= "descendant";
			}
			break;
		}
		case 113:
		{
			match(113);
			if ( inputState.guessing==0 ) {
				name= "descendant-or-self";
			}
			break;
		}
		case LITERAL_descending:
		{
			match(LITERAL_descending);
			if ( inputState.guessing==0 ) {
				name = "descending";
			}
			break;
		}
		case LITERAL_div:
		{
			match(LITERAL_div);
			if ( inputState.guessing==0 ) {
				name= "div";
			}
			break;
		}
		case LITERAL_document:
		{
			match(LITERAL_document);
			if ( inputState.guessing==0 ) {
				name= "document";
			}
			break;
		}
		case 41:
		{
			match(41);
			if ( inputState.guessing==0 ) {
				name= "document-node";
			}
			break;
		}
		case LITERAL_element:
		{
			match(LITERAL_element);
			if ( inputState.guessing==0 ) {
				name = "element";
			}
			break;
		}
		case LITERAL_else:
		{
			match(LITERAL_else);
			if ( inputState.guessing==0 ) {
				name= "else";
			}
			break;
		}
		case LITERAL_empty:
		{
			match(LITERAL_empty);
			if ( inputState.guessing==0 ) {
				name= "empty";
			}
			break;
		}
		case LITERAL_eq:
		{
			match(LITERAL_eq);
			if ( inputState.guessing==0 ) {
				name = "eq";
			}
			break;
		}
		case LITERAL_every:
		{
			match(LITERAL_every);
			if ( inputState.guessing==0 ) {
				name = "every";
			}
			break;
		}
		case LITERAL_except:
		{
			match(LITERAL_except);
			if ( inputState.guessing==0 ) {
				name = "except";
			}
			break;
		}
		case LITERAL_external:
		{
			match(LITERAL_external);
			if ( inputState.guessing==0 ) {
				name = "external";
			}
			break;
		}
		case LITERAL_following:
		{
			match(LITERAL_following);
			if ( inputState.guessing==0 ) {
				name = "following";
			}
			break;
		}
		case 115:
		{
			match(115);
			if ( inputState.guessing==0 ) {
				name= "following-sibling";
			}
			break;
		}
		case LITERAL_for:
		{
			match(LITERAL_for);
			if ( inputState.guessing==0 ) {
				name= "for";
			}
			break;
		}
		case LITERAL_function:
		{
			match(LITERAL_function);
			if ( inputState.guessing==0 ) {
				name= "function";
			}
			break;
		}
		case LITERAL_ge:
		{
			match(LITERAL_ge);
			if ( inputState.guessing==0 ) {
				name = "ge";
			}
			break;
		}
		case LITERAL_greatest:
		{
			match(LITERAL_greatest);
			if ( inputState.guessing==0 ) {
				name = "greatest";
			}
			break;
		}
		case LITERAL_gt:
		{
			match(LITERAL_gt);
			if ( inputState.guessing==0 ) {
				name = "gt";
			}
			break;
		}
		case LITERAL_idiv:
		{
			match(LITERAL_idiv);
			if ( inputState.guessing==0 ) {
				name = "idiv";
			}
			break;
		}
		case LITERAL_if:
		{
			match(LITERAL_if);
			if ( inputState.guessing==0 ) {
				name= "if";
			}
			break;
		}
		case LITERAL_import:
		{
			match(LITERAL_import);
			if ( inputState.guessing==0 ) {
				name = "import";
			}
			break;
		}
		case LITERAL_in:
		{
			match(LITERAL_in);
			if ( inputState.guessing==0 ) {
				name = "in";
			}
			break;
		}
		case LITERAL_instance:
		{
			match(LITERAL_instance);
			if ( inputState.guessing==0 ) {
				name = "instance";
			}
			break;
		}
		case LITERAL_intersect:
		{
			match(LITERAL_intersect);
			if ( inputState.guessing==0 ) {
				name = "intersect";
			}
			break;
		}
		case LITERAL_is:
		{
			match(LITERAL_is);
			if ( inputState.guessing==0 ) {
				name = "is";
			}
			break;
		}
		case LITERAL_isnot:
		{
			match(LITERAL_isnot);
			if ( inputState.guessing==0 ) {
				name = "isnot";
			}
			break;
		}
		case LITERAL_item:
		{
			match(LITERAL_item);
			if ( inputState.guessing==0 ) {
				name= "item";
			}
			break;
		}
		case LITERAL_lax:
		{
			match(LITERAL_lax);
			if ( inputState.guessing==0 ) {
				name = "lax";
			}
			break;
		}
		case LITERAL_le:
		{
			match(LITERAL_le);
			if ( inputState.guessing==0 ) {
				name = "le";
			}
			break;
		}
		case LITERAL_least:
		{
			match(LITERAL_least);
			if ( inputState.guessing==0 ) {
				name = "least";
			}
			break;
		}
		case LITERAL_let:
		{
			match(LITERAL_let);
			if ( inputState.guessing==0 ) {
				name= "let";
			}
			break;
		}
		case LITERAL_lt:
		{
			match(LITERAL_lt);
			if ( inputState.guessing==0 ) {
				name = "lt";
			}
			break;
		}
		case LITERAL_mod:
		{
			match(LITERAL_mod);
			if ( inputState.guessing==0 ) {
				name= "mod";
			}
			break;
		}
		case LITERAL_module:
		{
			match(LITERAL_module);
			if ( inputState.guessing==0 ) {
				name = "module";
			}
			break;
		}
		case LITERAL_namespace:
		{
			match(LITERAL_namespace);
			if ( inputState.guessing==0 ) {
				name= "namespace";
			}
			break;
		}
		case LITERAL_ne:
		{
			match(LITERAL_ne);
			if ( inputState.guessing==0 ) {
				name = "ne";
			}
			break;
		}
		case LITERAL_nillable:
		{
			match(LITERAL_nillable);
			if ( inputState.guessing==0 ) {
				name = "nillable";
			}
			break;
		}
		case LITERAL_node:
		{
			match(LITERAL_node);
			if ( inputState.guessing==0 ) {
				name= "node";
			}
			break;
		}
		case LITERAL_of:
		{
			match(LITERAL_of);
			if ( inputState.guessing==0 ) {
				name = "of";
			}
			break;
		}
		case LITERAL_or:
		{
			match(LITERAL_or);
			if ( inputState.guessing==0 ) {
				name= "or";
			}
			break;
		}
		case LITERAL_order:
		{
			match(LITERAL_order);
			if ( inputState.guessing==0 ) {
				name = "order";
			}
			break;
		}
		case LITERAL_parent:
		{
			match(LITERAL_parent);
			if ( inputState.guessing==0 ) {
				name= "parent";
			}
			break;
		}
		case LITERAL_preceding:
		{
			match(LITERAL_preceding);
			if ( inputState.guessing==0 ) {
				name = "preceding";
			}
			break;
		}
		case 120:
		{
			match(120);
			if ( inputState.guessing==0 ) {
				name= "preceding-sibling";
			}
			break;
		}
		case LITERAL_preserve:
		{
			match(LITERAL_preserve);
			if ( inputState.guessing==0 ) {
				name = "preserve";
			}
			break;
		}
		case 43:
		{
			match(43);
			if ( inputState.guessing==0 ) {
				name = "processing-instruction";
			}
			break;
		}
		case LITERAL_property:
		{
			match(LITERAL_property);
			if ( inputState.guessing==0 ) {
				name = "property";
			}
			break;
		}
		case LITERAL_return:
		{
			match(LITERAL_return);
			if ( inputState.guessing==0 ) {
				name = "return";
			}
			break;
		}
		case LITERAL_satisfies:
		{
			match(LITERAL_satisfies);
			if ( inputState.guessing==0 ) {
				name = "satisfies";
			}
			break;
		}
		case LITERAL_schema:
		{
			match(LITERAL_schema);
			if ( inputState.guessing==0 ) {
				name = "schema";
			}
			break;
		}
		case LITERAL_self:
		{
			match(LITERAL_self);
			if ( inputState.guessing==0 ) {
				name= "self";
			}
			break;
		}
		case LITERAL_skip:
		{
			match(LITERAL_skip);
			if ( inputState.guessing==0 ) {
				name = "skip";
			}
			break;
		}
		case LITERAL_some:
		{
			match(LITERAL_some);
			if ( inputState.guessing==0 ) {
				name = "some";
			}
			break;
		}
		case LITERAL_stable:
		{
			match(LITERAL_stable);
			if ( inputState.guessing==0 ) {
				name = "stable";
			}
			break;
		}
		case LITERAL_strict:
		{
			match(LITERAL_strict);
			if ( inputState.guessing==0 ) {
				name =  "strict";
			}
			break;
		}
		case LITERAL_strip:
		{
			match(LITERAL_strip);
			if ( inputState.guessing==0 ) {
				name = "strip";
			}
			break;
		}
		case LITERAL_text:
		{
			match(LITERAL_text);
			if ( inputState.guessing==0 ) {
				name= "text";
			}
			break;
		}
		case LITERAL_then:
		{
			match(LITERAL_then);
			if ( inputState.guessing==0 ) {
				name= "then";
			}
			break;
		}
		case LITERAL_to:
		{
			match(LITERAL_to);
			if ( inputState.guessing==0 ) {
				name = "to";
			}
			break;
		}
		case LITERAL_treat:
		{
			match(LITERAL_treat);
			if ( inputState.guessing==0 ) {
				name = "treat";
			}
			break;
		}
		case LITERAL_try:
		{
			match(LITERAL_try);
			if ( inputState.guessing==0 ) {
				name = "try";
			}
			break;
		}
		case LITERAL_typeswitch:
		{
			match(LITERAL_typeswitch);
			if ( inputState.guessing==0 ) {
				name = "typeswitch";
			}
			break;
		}
		case LITERAL_xmlspace:
		{
			match(LITERAL_xmlspace);
			if ( inputState.guessing==0 ) {
				name = "xmlspace";
			}
			break;
		}
		case LITERAL_xquery:
		{
			match(LITERAL_xquery);
			if ( inputState.guessing==0 ) {
				name= "xquery";
			}
			break;
		}
		case LITERAL_union:
		{
			match(LITERAL_union);
			if ( inputState.guessing==0 ) {
				name = "union";
			}
			break;
		}
		case LITERAL_validation:
		{
			match(LITERAL_validation);
			if ( inputState.guessing==0 ) {
				name = "validation";
			}
			break;
		}
		case LITERAL_variable:
		{
			match(LITERAL_variable);
			if ( inputState.guessing==0 ) {
				name= "variable";
			}
			break;
		}
		case LITERAL_version:
		{
			match(LITERAL_version);
			if ( inputState.guessing==0 ) {
				name= "version";
			}
			break;
		}
		case LITERAL_where:
		{
			match(LITERAL_where);
			if ( inputState.guessing==0 ) {
				name = "where";
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return name;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"module\"",
		"\"xquery\"",
		"\"version\"",
		"STRING_LITERAL",
		"\"import\"",
		"\"declare\"",
		"\"xmlspace\"",
		"\"namespace\"",
		"\"default\"",
		"\"collation\"",
		"\"element\"",
		"\"function\"",
		"\"define\"",
		"\"variable\"",
		"\"validation\"",
		"\"lax\"",
		"\"strict\"",
		"\"skip\"",
		"\"schema\"",
		"NCNAME",
		"EQ",
		"\"at\"",
		"\"preserve\"",
		"\"strip\"",
		"DOLLAR",
		"LCURLY",
		"RCURLY",
		"\"external\"",
		"'('",
		"')'",
		"\"as\"",
		"COMMA",
		"\"empty\"",
		"QUESTION",
		"STAR",
		"PLUS",
		"\"item\"",
		"\"document-node\"",
		"\"attribute\"",
		"\"processing-instruction\"",
		"\"comment\"",
		"\"text\"",
		"\"node\"",
		"\"for\"",
		"\"let\"",
		"\"some\"",
		"\"every\"",
		"\"typeswitch\"",
		"\"if\"",
		"\"try\"",
		"\"catch\"",
		"\"where\"",
		"\"return\"",
		"\"in\"",
		"COLON",
		"\"stable\"",
		"\"order\"",
		"\"by\"",
		"\"ascending\"",
		"\"descending\"",
		"\"greatest\"",
		"\"least\"",
		"\"satisfies\"",
		"\"case\"",
		"\"then\"",
		"\"else\"",
		"\"or\"",
		"\"and\"",
		"\"instance\"",
		"\"of\"",
		"\"treat\"",
		"\"castable\"",
		"\"cast\"",
		"LT",
		"GT",
		"\"eq\"",
		"\"ne\"",
		"\"lt\"",
		"\"le\"",
		"\"gt\"",
		"\"ge\"",
		"NEQ",
		"GTEQ",
		"LTEQ",
		"\"is\"",
		"\"isnot\"",
		"\"to\"",
		"MINUS",
		"\"div\"",
		"\"idiv\"",
		"\"mod\"",
		"\"union\"",
		"UNION",
		"\"intersect\"",
		"\"except\"",
		"SLASH",
		"DSLASH",
		"\"document\"",
		"SELF",
		"XML_COMMENT",
		"XML_PI",
		"LPPAREN",
		"RPPAREN",
		"AT",
		"PARENT",
		"\"property\"",
		"\"child\"",
		"\"self\"",
		"\"descendant\"",
		"\"descendant-or-self\"",
		"\"following\"",
		"\"following-sibling\"",
		"\"parent\"",
		"\"ancestor\"",
		"\"ancestor-or-self\"",
		"\"preceding\"",
		"\"preceding-sibling\"",
		"DOUBLE_LITERAL",
		"DECIMAL_LITERAL",
		"INTEGER_LITERAL",
		"\"nillable\"",
		"END_TAG_START",
		"QUOT",
		"APOS",
		"QUOT_ATTRIBUTE_CONTENT",
		"APOS_ATTRIBUTE_CONTENT",
		"ELEMENT_CONTENT",
		"XML_COMMENT_END",
		"XQDOC_COMMENT",
		"XML_CDATA",
		"XML_CDATA_END",
		"XML_PI_END",
		"\"collection\"",
		"SEMICOLON",
		"ANDEQ",
		"OREQ",
		"XML_PI_START",
		"LETTER",
		"DIGITS",
		"HEX_DIGITS",
		"NMSTART",
		"NMCHAR",
		"WS",
		"EXPR_COMMENT",
		"PRAGMA",
		"PRAGMA_CONTENT",
		"PRAGMA_QNAME",
		"PREDEFINED_ENTITY_REF",
		"CHAR_REF",
		"NEXT_TOKEN",
		"CHAR",
		"BASECHAR",
		"IDEOGRAPHIC",
		"COMBINING_CHAR",
		"DIGIT",
		"EXTENDER"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { -288230558167728144L, 2305836407834263551L, 304L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -288230558167728142L, 2305836407834263551L, 304L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -288231387364851856L, 1297001675665416191L, 256L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 138538465116160L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { -288230558167728144L, 2305836407834263551L, 288L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 16777216L, 14704640L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 274877906944L, 1879048192L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { -288231112486944912L, 1297028063944482815L, 256L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { -288231382801448976L, 2305809916341764095L, 288L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 4398046511104L, 4433230883192832L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { -288231112486944912L, 1297001675665416191L, 256L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 65970697682944L, 137438953472L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = new long[8];
		data[0]=-16L;
		data[1]=-34359754753L;
		data[2]=4294967295L;
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { 1610612736L, 1649267449856L, 36L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	
	}
