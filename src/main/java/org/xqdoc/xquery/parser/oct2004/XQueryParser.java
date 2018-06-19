// $ANTLR : "XQuery.g" -> "XQueryParser.java"$

	
/**  
 * Grammar definition for the October 2004 XQuery specification.
 */
	package org.xqdoc.xquery.parser.oct2004;

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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case STRING_LITERAL:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case PLUS:
		case MINUS:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_validate:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case 103:
		case 104:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case XQDOC_COMMENT:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
		boolean synPredMatched6 = false;
		if (((LA(1)==LITERAL_xquery))) {
			int _m6 = mark();
			synPredMatched6 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_xquery);
				match(LITERAL_version);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched6 = false;
			}
			rewind(_m6);
			inputState.guessing--;
		}
		if ( synPredMatched6 ) {
			versionDecl();
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		boolean synPredMatched11 = false;
		if (((LA(1)==LITERAL_module||LA(1)==XQDOC_COMMENT))) {
			int _m11 = mark();
			synPredMatched11 = true;
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
				match(LITERAL_namespace);
				}
				}
			}
			catch (RecognitionException pe) {
				synPredMatched11 = false;
			}
			rewind(_m11);
			inputState.guessing--;
		}
		if ( synPredMatched11 ) {
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
	
	public final void versionDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_xquery);
		match(LITERAL_version);
		match(STRING_LITERAL);
		{
		switch ( LA(1)) {
		case LITERAL_encoding:
		{
			match(LITERAL_encoding);
			match(STRING_LITERAL);
			break;
		}
		case SEMICOLON:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		separator();
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
		prolog();
		queryBody();
	}
	
	public final void separator() throws RecognitionException, TokenStreamException {
		
		
		match(SEMICOLON);
	}
	
	public final void prolog() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop58:
		do {
			if ((LA(1)==LITERAL_declare||LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT)) {
				{
				boolean synPredMatched23 = false;
				if (((LA(1)==LITERAL_declare))) {
					int _m23 = mark();
					synPredMatched23 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_declare);
						match(LITERAL_xmlspace);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched23 = false;
					}
					rewind(_m23);
					inputState.guessing--;
				}
				if ( synPredMatched23 ) {
					xmlSpaceDecl();
				}
				else {
					boolean synPredMatched25 = false;
					if (((LA(1)==LITERAL_declare))) {
						int _m25 = mark();
						synPredMatched25 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_declare);
							match(LITERAL_default);
							match(LITERAL_collation);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched25 = false;
						}
						rewind(_m25);
						inputState.guessing--;
					}
					if ( synPredMatched25 ) {
						defaultCollationDecl();
					}
					else {
						boolean synPredMatched27 = false;
						if (((LA(1)==LITERAL_declare))) {
							int _m27 = mark();
							synPredMatched27 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_declare);
								match(15);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched27 = false;
							}
							rewind(_m27);
							inputState.guessing--;
						}
						if ( synPredMatched27 ) {
							baseUriDecl();
						}
						else {
							boolean synPredMatched29 = false;
							if (((LA(1)==LITERAL_declare))) {
								int _m29 = mark();
								synPredMatched29 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_declare);
									match(LITERAL_construction);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched29 = false;
								}
								rewind(_m29);
								inputState.guessing--;
							}
							if ( synPredMatched29 ) {
								constructionDecl();
							}
							else {
								boolean synPredMatched31 = false;
								if (((LA(1)==LITERAL_declare))) {
									int _m31 = mark();
									synPredMatched31 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_declare);
										match(LITERAL_ordering);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched31 = false;
									}
									rewind(_m31);
									inputState.guessing--;
								}
								if ( synPredMatched31 ) {
									orderingModeDecl();
								}
								else {
									boolean synPredMatched33 = false;
									if (((LA(1)==LITERAL_declare))) {
										int _m33 = mark();
										synPredMatched33 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_declare);
											match(LITERAL_default);
											match(LITERAL_order);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched33 = false;
										}
										rewind(_m33);
										inputState.guessing--;
									}
									if ( synPredMatched33 ) {
										emptyOrderingDecl();
									}
									else {
										boolean synPredMatched35 = false;
										if (((LA(1)==LITERAL_declare))) {
											int _m35 = mark();
											synPredMatched35 = true;
											inputState.guessing++;
											try {
												{
												match(LITERAL_declare);
												match(19);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched35 = false;
											}
											rewind(_m35);
											inputState.guessing--;
										}
										if ( synPredMatched35 ) {
											inheritNamespacesDecl();
										}
										else {
											boolean synPredMatched37 = false;
											if (((LA(1)==LITERAL_import))) {
												int _m37 = mark();
												synPredMatched37 = true;
												inputState.guessing++;
												try {
													{
													match(LITERAL_import);
													match(LITERAL_schema);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched37 = false;
												}
												rewind(_m37);
												inputState.guessing--;
											}
											if ( synPredMatched37 ) {
												schemaImport();
											}
											else {
												boolean synPredMatched39 = false;
												if (((LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT))) {
													int _m39 = mark();
													synPredMatched39 = true;
													inputState.guessing++;
													try {
														{
														match(LITERAL_import);
														match(LITERAL_module);
														}
													}
													catch (RecognitionException pe) {
														synPredMatched39 = false;
													}
													rewind(_m39);
													inputState.guessing--;
												}
												if ( synPredMatched39 ) {
													moduleImport();
												}
												else {
													boolean synPredMatched42 = false;
													if (((LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT))) {
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
															synPredMatched42 = false;
														}
														rewind(_m42);
														inputState.guessing--;
													}
													if ( synPredMatched42 ) {
														moduleImport();
													}
													else {
														boolean synPredMatched44 = false;
														if (((LA(1)==LITERAL_declare))) {
															int _m44 = mark();
															synPredMatched44 = true;
															inputState.guessing++;
															try {
																{
																match(LITERAL_declare);
																match(LITERAL_namespace);
																}
															}
															catch (RecognitionException pe) {
																synPredMatched44 = false;
															}
															rewind(_m44);
															inputState.guessing--;
														}
														if ( synPredMatched44 ) {
															namespaceDecl();
														}
														else {
															boolean synPredMatched47 = false;
															if (((LA(1)==LITERAL_declare))) {
																int _m47 = mark();
																synPredMatched47 = true;
																inputState.guessing++;
																try {
																	{
																	match(LITERAL_declare);
																	match(LITERAL_default);
																	{
																	switch ( LA(1)) {
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
																	synPredMatched47 = false;
																}
																rewind(_m47);
																inputState.guessing--;
															}
															if ( synPredMatched47 ) {
																setterOld();
															}
															else {
																boolean synPredMatched49 = false;
																if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																	int _m49 = mark();
																	synPredMatched49 = true;
																	inputState.guessing++;
																	try {
																		{
																		match(LITERAL_declare);
																		match(LITERAL_variable);
																		}
																	}
																	catch (RecognitionException pe) {
																		synPredMatched49 = false;
																	}
																	rewind(_m49);
																	inputState.guessing--;
																}
																if ( synPredMatched49 ) {
																	varDecl();
																}
																else {
																	boolean synPredMatched52 = false;
																	if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																		int _m52 = mark();
																		synPredMatched52 = true;
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
																			case LITERAL_declare:
																			{
																				break;
																			}
																			default:
																			{
																				throw new NoViableAltException(LT(1), getFilename());
																			}
																			}
																			}
																			match(LITERAL_declare);
																			match(LITERAL_variable);
																			}
																		}
																		catch (RecognitionException pe) {
																			synPredMatched52 = false;
																		}
																		rewind(_m52);
																		inputState.guessing--;
																	}
																	if ( synPredMatched52 ) {
																		varDecl();
																	}
																	else {
																		boolean synPredMatched54 = false;
																		if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																			int _m54 = mark();
																			synPredMatched54 = true;
																			inputState.guessing++;
																			try {
																				{
																				match(LITERAL_declare);
																				match(LITERAL_function);
																				}
																			}
																			catch (RecognitionException pe) {
																				synPredMatched54 = false;
																			}
																			rewind(_m54);
																			inputState.guessing--;
																		}
																		if ( synPredMatched54 ) {
																			functionDecl();
																		}
																		else {
																			boolean synPredMatched57 = false;
																			if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																				int _m57 = mark();
																				synPredMatched57 = true;
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
																					case LITERAL_declare:
																					{
																						break;
																					}
																					default:
																					{
																						throw new NoViableAltException(LT(1), getFilename());
																					}
																					}
																					}
																					match(LITERAL_declare);
																					match(LITERAL_function);
																					}
																				}
																				catch (RecognitionException pe) {
																					synPredMatched57 = false;
																				}
																				rewind(_m57);
																				inputState.guessing--;
																			}
																			if ( synPredMatched57 ) {
																				functionDecl();
																			}
																			else {
																				throw new NoViableAltException(LT(1), getFilename());
																			}
																			}}}}}}}}}}}}}}}
																			}
																			separator();
																		}
																		else {
																			break _loop58;
																		}
																		
																	} while (true);
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
					context.setFunctionName("local", "xqDoc-main");
					context.setFunctionSignature(null);
					context.setFunctionBody(functionBody.toString());
					context.buildFunctionSection();
					functionBody = new StringBuffer();	
				
		}
	}
	
	public final void moduleDecl() throws RecognitionException, TokenStreamException {
		
		
			String prefix = null;
			String uri = null;	
		
		
		match(LITERAL_module);
		match(LITERAL_namespace);
		prefix=ncnameOrKeyword();
		match(EQ);
		uri=strippedStringLiteral();
		separator();
		if ( inputState.guessing==0 ) {
			
					context.buildLibraryModuleSection(uri);
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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case LITERAL_item:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
	
	public final void xmlSpaceDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(LITERAL_xmlspace);
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
	
	public final void defaultCollationDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(LITERAL_default);
		match(LITERAL_collation);
		match(STRING_LITERAL);
	}
	
	public final void baseUriDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(15);
		match(STRING_LITERAL);
	}
	
	public final void constructionDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(LITERAL_construction);
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
	
	public final void orderingModeDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(LITERAL_ordering);
		{
		switch ( LA(1)) {
		case LITERAL_ordered:
		{
			match(LITERAL_ordered);
			break;
		}
		case LITERAL_unordered:
		{
			match(LITERAL_unordered);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void emptyOrderingDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(LITERAL_default);
		match(LITERAL_order);
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
	}
	
	public final void inheritNamespacesDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(19);
		{
		switch ( LA(1)) {
		case LITERAL_yes:
		{
			match(LITERAL_yes);
			break;
		}
		case LITERAL_no:
		{
			match(LITERAL_no);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void schemaImport() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_import);
		match(LITERAL_schema);
		{
		switch ( LA(1)) {
		case LITERAL_namespace:
		case LITERAL_default:
		{
			schemaPrefix();
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
		switch ( LA(1)) {
		case LITERAL_at:
		{
			match(LITERAL_at);
			match(STRING_LITERAL);
			{
			_loop97:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(STRING_LITERAL);
				}
				else {
					break _loop97;
				}
				
			} while (true);
			}
			break;
		}
		case SEMICOLON:
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
		switch ( LA(1)) {
		case LITERAL_at:
		{
			match(LITERAL_at);
			junk = LT(1);
			match(STRING_LITERAL);
			{
			_loop106:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(STRING_LITERAL);
				}
				else {
					break _loop106;
				}
				
			} while (true);
			}
			break;
		}
		case SEMICOLON:
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
			
					if (prefix != null) {
						context.addPrefixAndURI(prefix, uri);
					}
					context.buildImportSection(uri);
				
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
	
	public final void setterOld() throws RecognitionException, TokenStreamException {
		
		
			String uri = null;	
		
		
		{
		match(LITERAL_declare);
		match(LITERAL_default);
		{
		switch ( LA(1)) {
		case LITERAL_element:
		{
			{
			match(LITERAL_element);
			match(LITERAL_namespace);
			match(STRING_LITERAL);
			}
			break;
		}
		case LITERAL_function:
		{
			{
			match(LITERAL_function);
			match(LITERAL_namespace);
			uri=strippedStringLiteral();
			}
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
		case LITERAL_declare:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_declare);
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
		case COLON:
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
		case COLON:
		{
			{
			match(COLON);
			match(EQ);
			exprSingle();
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
		case LITERAL_declare:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LITERAL_declare);
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
		case LITERAL_external:
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
					context.setFunctionSignature("declare function " + localName + functionSignature.toString());
					functionBody.append("declare function " + name + functionSignature);
					functionSignature = new StringBuffer();
				
		}
		{
		switch ( LA(1)) {
		case LCURLY:
		{
			functionBody();
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
	}
	
	public final void setter() throws RecognitionException, TokenStreamException {
		
		
		{
		{
		boolean synPredMatched63 = false;
		if (((LA(1)==LITERAL_declare))) {
			int _m63 = mark();
			synPredMatched63 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_declare);
				match(LITERAL_xmlspace);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched63 = false;
			}
			rewind(_m63);
			inputState.guessing--;
		}
		if ( synPredMatched63 ) {
			xmlSpaceDecl();
		}
		else {
			boolean synPredMatched65 = false;
			if (((LA(1)==LITERAL_declare))) {
				int _m65 = mark();
				synPredMatched65 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_declare);
					match(LITERAL_default);
					match(LITERAL_collation);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched65 = false;
				}
				rewind(_m65);
				inputState.guessing--;
			}
			if ( synPredMatched65 ) {
				defaultCollationDecl();
			}
			else {
				boolean synPredMatched67 = false;
				if (((LA(1)==LITERAL_declare))) {
					int _m67 = mark();
					synPredMatched67 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_declare);
						match(15);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched67 = false;
					}
					rewind(_m67);
					inputState.guessing--;
				}
				if ( synPredMatched67 ) {
					baseUriDecl();
				}
				else {
					boolean synPredMatched69 = false;
					if (((LA(1)==LITERAL_declare))) {
						int _m69 = mark();
						synPredMatched69 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_declare);
							match(LITERAL_construction);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched69 = false;
						}
						rewind(_m69);
						inputState.guessing--;
					}
					if ( synPredMatched69 ) {
						constructionDecl();
					}
					else {
						boolean synPredMatched71 = false;
						if (((LA(1)==LITERAL_declare))) {
							int _m71 = mark();
							synPredMatched71 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_declare);
								match(LITERAL_ordering);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched71 = false;
							}
							rewind(_m71);
							inputState.guessing--;
						}
						if ( synPredMatched71 ) {
							orderingModeDecl();
						}
						else {
							boolean synPredMatched73 = false;
							if (((LA(1)==LITERAL_declare))) {
								int _m73 = mark();
								synPredMatched73 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_declare);
									match(LITERAL_default);
									match(LITERAL_order);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched73 = false;
								}
								rewind(_m73);
								inputState.guessing--;
							}
							if ( synPredMatched73 ) {
								emptyOrderingDecl();
							}
							else {
								boolean synPredMatched75 = false;
								if (((LA(1)==LITERAL_declare))) {
									int _m75 = mark();
									synPredMatched75 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_declare);
										match(19);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched75 = false;
									}
									rewind(_m75);
									inputState.guessing--;
								}
								if ( synPredMatched75 ) {
									inheritNamespacesDecl();
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}}}
								}
								separator();
								}
							}
							
	public final void schemaPrefix() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		switch ( LA(1)) {
		case LITERAL_namespace:
		{
			{
			match(LITERAL_namespace);
			tmpStr=ncnameOrKeyword();
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
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final String  qName() throws RecognitionException, TokenStreamException {
		String name;
		
		
			name= null;
			String name2;
		
		
		boolean synPredMatched429 = false;
		if (((_tokenSet_1.member(LA(1))))) {
			int _m429 = mark();
			synPredMatched429 = true;
			inputState.guessing++;
			try {
				{
				ncnameOrKeyword();
				match(COLON);
				ncnameOrKeyword();
				}
			}
			catch (RecognitionException pe) {
				synPredMatched429 = false;
			}
			rewind(_m429);
			inputState.guessing--;
		}
		if ( synPredMatched429 ) {
			name=ncnameOrKeyword();
			match(COLON);
			name2=ncnameOrKeyword();
			if ( inputState.guessing==0 ) {
				
						name= name + ':' + name2;
					
			}
		}
		else if ((_tokenSet_1.member(LA(1)))) {
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
	
	public final void exprSingle() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched134 = false;
		if (((LA(1)==LITERAL_for||LA(1)==LITERAL_let))) {
			int _m134 = mark();
			synPredMatched134 = true;
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
				synPredMatched134 = false;
			}
			rewind(_m134);
			inputState.guessing--;
		}
		if ( synPredMatched134 ) {
			flworExpr();
		}
		else {
			boolean synPredMatched137 = false;
			if (((LA(1)==LITERAL_some||LA(1)==LITERAL_every))) {
				int _m137 = mark();
				synPredMatched137 = true;
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
					synPredMatched137 = false;
				}
				rewind(_m137);
				inputState.guessing--;
			}
			if ( synPredMatched137 ) {
				quantifiedExpr();
			}
			else {
				boolean synPredMatched139 = false;
				if (((LA(1)==LITERAL_typeswitch))) {
					int _m139 = mark();
					synPredMatched139 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_typeswitch);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched139 = false;
					}
					rewind(_m139);
					inputState.guessing--;
				}
				if ( synPredMatched139 ) {
					typeswitchExpr();
				}
				else {
					boolean synPredMatched141 = false;
					if (((LA(1)==LITERAL_if))) {
						int _m141 = mark();
						synPredMatched141 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_if);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched141 = false;
						}
						rewind(_m141);
						inputState.guessing--;
					}
					if ( synPredMatched141 ) {
						ifExpr();
					}
					else {
						boolean synPredMatched143 = false;
						if (((LA(1)==LITERAL_try))) {
							int _m143 = mark();
							synPredMatched143 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_try);
								match(LCURLY);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched143 = false;
							}
							rewind(_m143);
							inputState.guessing--;
						}
						if ( synPredMatched143 ) {
							tryCatchExpr();
						}
						else if ((_tokenSet_2.member(LA(1)))) {
							orExpr();
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						}}}}
					}
					
	public final void paramList() throws RecognitionException, TokenStreamException {
		
		
		param();
		{
		_loop123:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				param();
			}
			else {
				break _loop123;
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
					// Put the separator back on the end of the body, since the 
					// separator is outside of the functionBody rule.
					functionBody.append(";");
					context.setFunctionBody(functionBody.toString());
					context.buildFunctionSection();
					functionBody = new StringBuffer();
				
		}
	}
	
	public final void sequenceType() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched382 = false;
		if (((LA(1)==LITERAL_empty))) {
			int _m382 = mark();
			synPredMatched382 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_empty);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched382 = false;
			}
			rewind(_m382);
			inputState.guessing--;
		}
		if ( synPredMatched382 ) {
			match(LITERAL_empty);
			match(LPAREN);
			match(RPAREN);
		}
		else if ((_tokenSet_3.member(LA(1)))) {
			itemType();
			{
			if ((LA(1)==PLUS||LA(1)==STAR||LA(1)==QUESTION)) {
				occurrenceIndicator();
			}
			else if ((_tokenSet_4.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		exprSingle();
		{
		_loop130:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop130;
			}
			
		} while (true);
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
		case COMMA:
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
	
	public final void flworExpr() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt147=0;
		_loop147:
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
				if ( _cnt147>=1 ) { break _loop147; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			}
			_cnt147++;
		} while (true);
		}
		{
		switch ( LA(1)) {
		case LITERAL_where:
		{
			whereClause();
			break;
		}
		case LITERAL_order:
		case LITERAL_return:
		case LITERAL_stable:
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
		case LITERAL_order:
		case LITERAL_stable:
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
		_loop177:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				quantifiedInVarBinding();
			}
			else {
				break _loop177;
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
		int _cnt182=0;
		_loop182:
		do {
			if ((LA(1)==LITERAL_case)) {
				caseClause();
			}
			else {
				if ( _cnt182>=1 ) { break _loop182; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt182++;
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
		exprSingle();
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
		_loop189:
		do {
			if ((LA(1)==LITERAL_or)) {
				match(LITERAL_or);
				andExpr();
			}
			else {
				break _loop189;
			}
			
		} while (true);
		}
	}
	
	public final void forClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_for);
		inVarBinding();
		{
		_loop152:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				inVarBinding();
			}
			else {
				break _loop152;
			}
			
		} while (true);
		}
	}
	
	public final void letClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_let);
		letVarBinding();
		{
		_loop159:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				letVarBinding();
			}
			else {
				break _loop159;
			}
			
		} while (true);
		}
	}
	
	public final void whereClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_where);
		exprSingle();
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
	
	public final void positionalVar() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		match(LITERAL_at);
		match(DOLLAR);
		name=qName();
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
	
	public final void orderSpecList() throws RecognitionException, TokenStreamException {
		
		
		orderSpec();
		{
		_loop167:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				orderSpec();
			}
			else {
				break _loop167;
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
		case LITERAL_empty:
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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case 103:
		case 104:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
		exprSingle();
	}
	
	public final void andExpr() throws RecognitionException, TokenStreamException {
		
		
		comparisonExpr();
		{
		_loop192:
		do {
			if ((LA(1)==LITERAL_and)) {
				match(LITERAL_and);
				comparisonExpr();
			}
			else {
				break _loop192;
			}
			
		} while (true);
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
		{
			{
			{
			match(LITERAL_is);
			}
			rangeExpr();
			}
			break;
		}
		case EOF:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_order:
		case SEMICOLON:
		case LITERAL_empty:
		case COMMA:
		case RPAREN:
		case RCURLY:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
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
			boolean synPredMatched196 = false;
			if (((LA(1)==LT))) {
				int _m196 = mark();
				synPredMatched196 = true;
				inputState.guessing++;
				try {
					{
					match(LT);
					match(LT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched196 = false;
				}
				rewind(_m196);
				inputState.guessing--;
			}
			if ( synPredMatched196 ) {
				match(LT);
				match(LT);
				rangeExpr();
			}
			else {
				boolean synPredMatched198 = false;
				if (((LA(1)==GT))) {
					int _m198 = mark();
					synPredMatched198 = true;
					inputState.guessing++;
					try {
						{
						match(GT);
						match(GT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched198 = false;
					}
					rewind(_m198);
					inputState.guessing--;
				}
				if ( synPredMatched198 ) {
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
		case EQ:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_order:
		case SEMICOLON:
		case LITERAL_empty:
		case COMMA:
		case RPAREN:
		case RCURLY:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
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
		_loop210:
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
				break _loop210;
			}
			
		} while (true);
		}
	}
	
	public final void multiplicativeExpr() throws RecognitionException, TokenStreamException {
		
		
		unionExpr();
		{
		_loop214:
		do {
			if (((LA(1) >= STAR && LA(1) <= LITERAL_mod))) {
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
				unionExpr();
			}
			else {
				break _loop214;
			}
			
		} while (true);
		}
	}
	
	public final void unionExpr() throws RecognitionException, TokenStreamException {
		
		
		intersectExceptExpr();
		{
		_loop218:
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
				break _loop218;
			}
			
		} while (true);
		}
	}
	
	public final void intersectExceptExpr() throws RecognitionException, TokenStreamException {
		
		
		instanceofExpr();
		{
		_loop222:
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
				instanceofExpr();
			}
			else {
				break _loop222;
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
		case EQ:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_order:
		case SEMICOLON:
		case LITERAL_empty:
		case COMMA:
		case RPAREN:
		case RCURLY:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
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
		case LITERAL_to:
		case PLUS:
		case MINUS:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case UNION:
		case LITERAL_intersect:
		case LITERAL_except:
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
		case EQ:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_order:
		case SEMICOLON:
		case LITERAL_empty:
		case COMMA:
		case RPAREN:
		case RCURLY:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
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
		case LITERAL_to:
		case PLUS:
		case MINUS:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case UNION:
		case LITERAL_intersect:
		case LITERAL_except:
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
		case EQ:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_order:
		case SEMICOLON:
		case LITERAL_empty:
		case COMMA:
		case RPAREN:
		case RCURLY:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
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
		case LITERAL_to:
		case PLUS:
		case MINUS:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case UNION:
		case LITERAL_intersect:
		case LITERAL_except:
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
		
		
		unaryExpr();
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
		case EQ:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_order:
		case SEMICOLON:
		case LITERAL_empty:
		case COMMA:
		case RPAREN:
		case RCURLY:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
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
		case LITERAL_to:
		case PLUS:
		case MINUS:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case UNION:
		case LITERAL_intersect:
		case LITERAL_except:
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
		case EQ:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_order:
		case SEMICOLON:
		case LITERAL_empty:
		case COMMA:
		case RPAREN:
		case RCURLY:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
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
		case LITERAL_to:
		case PLUS:
		case MINUS:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case UNION:
		case LITERAL_intersect:
		case LITERAL_except:
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
	
	public final void unaryExpr() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop233:
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
				break _loop233;
			}
			}
		} while (true);
		}
		valueExpr();
	}
	
	public final void valueExpr() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_validate:
		{
			validateExpr();
			break;
		}
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case STRING_LITERAL:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case 103:
		case 104:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
		{
			pathExpr();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void validateExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_validate);
		{
		switch ( LA(1)) {
		case LITERAL_lax:
		case LITERAL_strict:
		{
			validationMode();
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
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void pathExpr() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case STRING_LITERAL:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case 103:
		case 104:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
			boolean synPredMatched239 = false;
			if (((LA(1)==SLASH))) {
				int _m239 = mark();
				synPredMatched239 = true;
				inputState.guessing++;
				try {
					{
					match(SLASH);
					relativePathExpr();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched239 = false;
				}
				rewind(_m239);
				inputState.guessing--;
			}
			if ( synPredMatched239 ) {
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
	
	public final void validationMode() throws RecognitionException, TokenStreamException {
		
		
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
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void relativePathExpr() throws RecognitionException, TokenStreamException {
		
		
		stepExpr();
		{
		_loop243:
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
				break _loop243;
			}
			
		} while (true);
		}
	}
	
	public final void stepExpr() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched247 = false;
		if (((_tokenSet_6.member(LA(1))))) {
			int _m247 = mark();
			synPredMatched247 = true;
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
				case 101:
				{
					match(101);
					break;
				}
				case 102:
				{
					match(102);
					break;
				}
				case 103:
				{
					match(103);
					break;
				}
				case 104:
				{
					match(104);
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
				synPredMatched247 = false;
			}
			rewind(_m247);
			inputState.guessing--;
		}
		if ( synPredMatched247 ) {
			axisStep();
		}
		else {
			boolean synPredMatched250 = false;
			if (((_tokenSet_7.member(LA(1))))) {
				int _m250 = mark();
				synPredMatched250 = true;
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
					case 101:
					{
						match(101);
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
					synPredMatched250 = false;
				}
				rewind(_m250);
				inputState.guessing--;
			}
			if ( synPredMatched250 ) {
				filterExpr();
			}
			else {
				boolean synPredMatched253 = false;
				if (((_tokenSet_7.member(LA(1))))) {
					int _m253 = mark();
					synPredMatched253 = true;
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
						case 101:
						{
							match(101);
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
						synPredMatched253 = false;
					}
					rewind(_m253);
					inputState.guessing--;
				}
				if ( synPredMatched253 ) {
					filterExpr();
				}
				else {
					boolean synPredMatched256 = false;
					if (((_tokenSet_7.member(LA(1))))) {
						int _m256 = mark();
						synPredMatched256 = true;
						inputState.guessing++;
						try {
							{
							switch ( LA(1)) {
							case DOLLAR:
							{
								match(DOLLAR);
								break;
							}
							case LITERAL_xquery:
							case LITERAL_version:
							case LITERAL_module:
							case LITERAL_namespace:
							case LITERAL_declare:
							case LITERAL_xmlspace:
							case LITERAL_default:
							case LITERAL_collation:
							case 15:
							case LITERAL_order:
							case LITERAL_import:
							case LITERAL_schema:
							case LITERAL_element:
							case LITERAL_function:
							case LITERAL_variable:
							case LITERAL_preserve:
							case LITERAL_strip:
							case LITERAL_empty:
							case LITERAL_greatest:
							case LITERAL_least:
							case LITERAL_at:
							case LITERAL_external:
							case LITERAL_as:
							case LITERAL_for:
							case LITERAL_let:
							case LITERAL_some:
							case LITERAL_every:
							case LITERAL_typeswitch:
							case LITERAL_if:
							case LITERAL_try:
							case LITERAL_catch:
							case LITERAL_return:
							case LITERAL_in:
							case LITERAL_where:
							case LITERAL_stable:
							case LITERAL_by:
							case LITERAL_ascending:
							case LITERAL_descending:
							case LITERAL_satisfies:
							case LITERAL_case:
							case LITERAL_then:
							case LITERAL_else:
							case LITERAL_or:
							case LITERAL_and:
							case LITERAL_eq:
							case LITERAL_ne:
							case LITERAL_lt:
							case LITERAL_le:
							case LITERAL_gt:
							case LITERAL_ge:
							case LITERAL_is:
							case LITERAL_to:
							case LITERAL_div:
							case LITERAL_idiv:
							case LITERAL_mod:
							case LITERAL_union:
							case LITERAL_intersect:
							case LITERAL_except:
							case LITERAL_instance:
							case LITERAL_of:
							case LITERAL_treat:
							case LITERAL_castable:
							case LITERAL_cast:
							case LITERAL_text:
							case LITERAL_node:
							case LITERAL_attribute:
							case LITERAL_comment:
							case 101:
							case 102:
							case LITERAL_document:
							case LITERAL_child:
							case LITERAL_self:
							case LITERAL_descendant:
							case 114:
							case LITERAL_following:
							case 116:
							case LITERAL_parent:
							case LITERAL_ancestor:
							case 119:
							case LITERAL_preceding:
							case 121:
							case LITERAL_item:
							case NCNAME:
							case LITERAL_lax:
							case LITERAL_strict:
							case LITERAL_collection:
							case LITERAL_define:
							case LITERAL_isnot:
							case LITERAL_nillable:
							case LITERAL_type:
							case LITERAL_validation:
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
							synPredMatched256 = false;
						}
						rewind(_m256);
						inputState.guessing--;
					}
					if ( synPredMatched256 ) {
						filterExpr();
					}
					else if ((_tokenSet_6.member(LA(1)))) {
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
		predicateList();
	}
	
	public final void filterExpr() throws RecognitionException, TokenStreamException {
		
		
		primaryExpr();
		predicateList();
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
		
		
		boolean synPredMatched261 = false;
		if (((_tokenSet_8.member(LA(1))))) {
			int _m261 = mark();
			synPredMatched261 = true;
			inputState.guessing++;
			try {
				{
				forwardAxisSpecifier();
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched261 = false;
			}
			rewind(_m261);
			inputState.guessing--;
		}
		if ( synPredMatched261 ) {
			forwardAxis();
			nodeTest();
		}
		else {
			boolean synPredMatched263 = false;
			if ((((LA(1) >= LITERAL_parent && LA(1) <= 121)))) {
				int _m263 = mark();
				synPredMatched263 = true;
				inputState.guessing++;
				try {
					{
					reverseAxisSpecifier();
					match(COLON);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched263 = false;
				}
				rewind(_m263);
				inputState.guessing--;
			}
			if ( synPredMatched263 ) {
				reverseAxis();
				nodeTest();
			}
			else if ((_tokenSet_6.member(LA(1)))) {
				abbrevStep();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		
	public final void predicateList() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop299:
		do {
			if ((LA(1)==LPPAREN)) {
				predicate();
			}
			else {
				break _loop299;
			}
			
		} while (true);
		}
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
		case 114:
		{
			match(114);
			break;
		}
		case LITERAL_following:
		{
			match(LITERAL_following);
			break;
		}
		case 116:
		{
			match(116);
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
		
		
		boolean synPredMatched272 = false;
		if (((_tokenSet_9.member(LA(1))))) {
			int _m272 = mark();
			synPredMatched272 = true;
			inputState.guessing++;
			try {
				{
				match(102);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched272 = false;
			}
			rewind(_m272);
			inputState.guessing--;
		}
		if ( synPredMatched272 ) {
			kindTest();
		}
		else {
			boolean synPredMatched274 = false;
			if (((_tokenSet_9.member(LA(1))))) {
				int _m274 = mark();
				synPredMatched274 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_element);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched274 = false;
				}
				rewind(_m274);
				inputState.guessing--;
			}
			if ( synPredMatched274 ) {
				kindTest();
			}
			else {
				boolean synPredMatched276 = false;
				if (((_tokenSet_9.member(LA(1))))) {
					int _m276 = mark();
					synPredMatched276 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_attribute);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched276 = false;
					}
					rewind(_m276);
					inputState.guessing--;
				}
				if ( synPredMatched276 ) {
					kindTest();
				}
				else {
					boolean synPredMatched278 = false;
					if (((_tokenSet_9.member(LA(1))))) {
						int _m278 = mark();
						synPredMatched278 = true;
						inputState.guessing++;
						try {
							{
							match(101);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched278 = false;
						}
						rewind(_m278);
						inputState.guessing--;
					}
					if ( synPredMatched278 ) {
						kindTest();
					}
					else {
						boolean synPredMatched280 = false;
						if (((_tokenSet_9.member(LA(1))))) {
							int _m280 = mark();
							synPredMatched280 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_comment);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched280 = false;
							}
							rewind(_m280);
							inputState.guessing--;
						}
						if ( synPredMatched280 ) {
							kindTest();
						}
						else {
							boolean synPredMatched282 = false;
							if (((_tokenSet_9.member(LA(1))))) {
								int _m282 = mark();
								synPredMatched282 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_text);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched282 = false;
								}
								rewind(_m282);
								inputState.guessing--;
							}
							if ( synPredMatched282 ) {
								kindTest();
							}
							else {
								boolean synPredMatched284 = false;
								if (((_tokenSet_9.member(LA(1))))) {
									int _m284 = mark();
									synPredMatched284 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_node);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched284 = false;
									}
									rewind(_m284);
									inputState.guessing--;
								}
								if ( synPredMatched284 ) {
									kindTest();
								}
								else {
									boolean synPredMatched286 = false;
									if (((_tokenSet_9.member(LA(1))))) {
										int _m286 = mark();
										synPredMatched286 = true;
										inputState.guessing++;
										try {
											{
											match(103);
											match(LPAREN);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched286 = false;
										}
										rewind(_m286);
										inputState.guessing--;
									}
									if ( synPredMatched286 ) {
										kindTest();
									}
									else {
										boolean synPredMatched288 = false;
										if (((_tokenSet_9.member(LA(1))))) {
											int _m288 = mark();
											synPredMatched288 = true;
											inputState.guessing++;
											try {
												{
												match(104);
												match(LPAREN);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched288 = false;
											}
											rewind(_m288);
											inputState.guessing--;
										}
										if ( synPredMatched288 ) {
											kindTest();
										}
										else if ((_tokenSet_10.member(LA(1)))) {
											nameTest();
										}
										else {
											throw new NoViableAltException(LT(1), getFilename());
										}
										}}}}}}}}
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
		case 119:
		{
			match(119);
			break;
		}
		case LITERAL_preceding:
		{
			match(LITERAL_preceding);
			break;
		}
		case 121:
		{
			match(121);
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
	
	public final void abbrevStep() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case 103:
		case 104:
		case LITERAL_document:
		case AT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
		{
			{
			switch ( LA(1)) {
			case AT:
			{
				match(AT);
				break;
			}
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_order:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_at:
			case LITERAL_external:
			case LITERAL_as:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_to:
			case STAR:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 101:
			case 102:
			case 103:
			case 104:
			case LITERAL_document:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 114:
			case LITERAL_following:
			case 116:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 119:
			case LITERAL_preceding:
			case 121:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_collection:
			case LITERAL_define:
			case LITERAL_isnot:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_validation:
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
	
	public final void kindTest() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case 102:
		{
			documentTest();
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
		case 104:
		{
			schemaElementTest();
			break;
		}
		case 103:
		{
			schemaAttributeTest();
			break;
		}
		case 101:
		{
			piTest();
			break;
		}
		case LITERAL_comment:
		{
			commentTest();
			break;
		}
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
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void nameTest() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
			String prefix= null;
		
		
		boolean synPredMatched292 = false;
		if (((_tokenSet_10.member(LA(1))))) {
			int _m292 = mark();
			synPredMatched292 = true;
			inputState.guessing++;
			try {
				{
				switch ( LA(1)) {
				case LITERAL_xquery:
				case LITERAL_version:
				case LITERAL_module:
				case LITERAL_namespace:
				case LITERAL_declare:
				case LITERAL_xmlspace:
				case LITERAL_default:
				case LITERAL_collation:
				case 15:
				case LITERAL_order:
				case LITERAL_import:
				case LITERAL_schema:
				case LITERAL_element:
				case LITERAL_function:
				case LITERAL_variable:
				case LITERAL_preserve:
				case LITERAL_strip:
				case LITERAL_empty:
				case LITERAL_greatest:
				case LITERAL_least:
				case LITERAL_at:
				case LITERAL_external:
				case LITERAL_as:
				case LITERAL_for:
				case LITERAL_let:
				case LITERAL_some:
				case LITERAL_every:
				case LITERAL_typeswitch:
				case LITERAL_if:
				case LITERAL_try:
				case LITERAL_catch:
				case LITERAL_return:
				case LITERAL_in:
				case LITERAL_where:
				case LITERAL_stable:
				case LITERAL_by:
				case LITERAL_ascending:
				case LITERAL_descending:
				case LITERAL_satisfies:
				case LITERAL_case:
				case LITERAL_then:
				case LITERAL_else:
				case LITERAL_or:
				case LITERAL_and:
				case LITERAL_eq:
				case LITERAL_ne:
				case LITERAL_lt:
				case LITERAL_le:
				case LITERAL_gt:
				case LITERAL_ge:
				case LITERAL_is:
				case LITERAL_to:
				case LITERAL_div:
				case LITERAL_idiv:
				case LITERAL_mod:
				case LITERAL_union:
				case LITERAL_intersect:
				case LITERAL_except:
				case LITERAL_instance:
				case LITERAL_of:
				case LITERAL_treat:
				case LITERAL_castable:
				case LITERAL_cast:
				case LITERAL_text:
				case LITERAL_node:
				case LITERAL_attribute:
				case LITERAL_comment:
				case 101:
				case 102:
				case LITERAL_document:
				case LITERAL_child:
				case LITERAL_self:
				case LITERAL_descendant:
				case 114:
				case LITERAL_following:
				case 116:
				case LITERAL_parent:
				case LITERAL_ancestor:
				case 119:
				case LITERAL_preceding:
				case 121:
				case LITERAL_item:
				case NCNAME:
				case LITERAL_lax:
				case LITERAL_strict:
				case LITERAL_collection:
				case LITERAL_define:
				case LITERAL_isnot:
				case LITERAL_nillable:
				case LITERAL_type:
				case LITERAL_validation:
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
				synPredMatched292 = false;
			}
			rewind(_m292);
			inputState.guessing--;
		}
		if ( synPredMatched292 ) {
			wildcard();
		}
		else if ((_tokenSet_1.member(LA(1)))) {
			name=qName();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void wildcard() throws RecognitionException, TokenStreamException {
		
		String name=null;
		
		boolean synPredMatched295 = false;
		if (((LA(1)==STAR))) {
			int _m295 = mark();
			synPredMatched295 = true;
			inputState.guessing++;
			try {
				{
				match(STAR);
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched295 = false;
			}
			rewind(_m295);
			inputState.guessing--;
		}
		if ( synPredMatched295 ) {
			match(STAR);
			match(COLON);
			name=ncnameOrKeyword();
		}
		else if ((_tokenSet_1.member(LA(1)))) {
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
		case LITERAL_ordered:
		{
			orderedExpr();
			break;
		}
		case LITERAL_unordered:
		{
			unorderedExpr();
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
			boolean synPredMatched304 = false;
			if (((_tokenSet_11.member(LA(1))))) {
				int _m304 = mark();
				synPredMatched304 = true;
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
					case 101:
					{
						match(101);
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
					synPredMatched304 = false;
				}
				rewind(_m304);
				inputState.guessing--;
			}
			if ( synPredMatched304 ) {
				computedConstructor();
			}
			else {
				boolean synPredMatched307 = false;
				if (((_tokenSet_11.member(LA(1))))) {
					int _m307 = mark();
					synPredMatched307 = true;
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
						case 101:
						{
							match(101);
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
						synPredMatched307 = false;
					}
					rewind(_m307);
					inputState.guessing--;
				}
				if ( synPredMatched307 ) {
					computedConstructor();
				}
				else if ((_tokenSet_12.member(LA(1)))) {
					constructor();
				}
				else if ((_tokenSet_1.member(LA(1)))) {
					functionCall();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}}
		}
		
	public final void predicate() throws RecognitionException, TokenStreamException {
		
		
		match(LPPAREN);
		expr();
		match(RPPAREN);
	}
	
	public final void orderedExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_ordered);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void unorderedExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_unordered);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void computedConstructor() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_document:
		{
			compDocConstructor();
			break;
		}
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
		case LITERAL_comment:
		{
			compCommentConstructor();
			break;
		}
		case 101:
		{
			compPIConstructor();
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
		case XML_COMMENT:
		case XML_PI:
		{
			directConstructor();
			break;
		}
		case LITERAL_element:
		case LITERAL_text:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case LITERAL_document:
		{
			computedConstructor();
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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case STRING_LITERAL:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case PLUS:
		case MINUS:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_validate:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case 103:
		case 104:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case STRING_LITERAL:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case DOLLAR:
		case LITERAL_external:
		case LPAREN:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LT:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case PLUS:
		case MINUS:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_validate:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case 103:
		case 104:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
		_loop319:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop319;
			}
			
		} while (true);
		}
	}
	
	public final void directConstructor() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LT:
		{
			dirElemConstructor();
			break;
		}
		case XML_COMMENT:
		{
			dirCommentConstructor();
			break;
		}
		case XML_PI:
		{
			dirPIConstructor();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void dirElemConstructor() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched325 = false;
		if (((LA(1)==LT))) {
			int _m325 = mark();
			synPredMatched325 = true;
			inputState.guessing++;
			try {
				{
				match(LT);
				qName();
				{
				match(_tokenSet_13);
				}
				}
			}
			catch (RecognitionException pe) {
				synPredMatched325 = false;
			}
			rewind(_m325);
			inputState.guessing--;
		}
		if ( synPredMatched325 ) {
			elementWithAttributes();
		}
		else if ((LA(1)==LT)) {
			elementWithoutAttributes();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void dirCommentConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(XML_COMMENT);
		match(XML_COMMENT_END);
	}
	
	public final void dirPIConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(XML_PI);
		match(XML_PI_END);
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
		_loop336:
		do {
			if ((_tokenSet_14.member(LA(1)))) {
				dirElemContent();
			}
			else {
				break _loop336;
			}
			
		} while (true);
		}
	}
	
	public final void attributeList() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt340=0;
		_loop340:
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				attributeDef();
			}
			else {
				if ( _cnt340>=1 ) { break _loop340; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt340++;
		} while (true);
		}
	}
	
	public final void dirElemContent() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LT:
		case XML_COMMENT:
		case XML_PI:
		{
			directConstructor();
			break;
		}
		case ELEMENT_CONTENT:
		{
			match(ELEMENT_CONTENT);
			break;
		}
		case LCURLY:
		{
			enclosedExpr();
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
	
	public final void cdataSection() throws RecognitionException, TokenStreamException {
		
		
		match(XML_CDATA);
		match(XML_CDATA_END);
	}
	
	public final void attributeDef() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
			lexer.parseStringLiterals= false;
		
		
		name=qName();
		match(EQ);
		attributeValue();
	}
	
	public final void attributeValue() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case QUOT:
		{
			match(QUOT);
			if ( inputState.guessing==0 ) {
				
						lexer.inAttributeContent= true; 
						lexer.attrDelimChar = '"';
					
			}
			{
			_loop344:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==QUOT_ATTRIBUTE_CONTENT)) {
					quotAttrValueContent();
				}
				else {
					break _loop344;
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
			_loop346:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==APOS_ATTRIBUTE_CONTENT)) {
					aposAttrValueContent();
				}
				else {
					break _loop346;
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
		
		
		boolean synPredMatched351 = false;
		if (((LA(1)==LCURLY))) {
			int _m351 = mark();
			synPredMatched351 = true;
			inputState.guessing++;
			try {
				{
				match(LCURLY);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched351 = false;
			}
			rewind(_m351);
			inputState.guessing--;
		}
		if ( synPredMatched351 ) {
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
	
	public final void compDocConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_document);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void compElemConstructor() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		boolean synPredMatched361 = false;
		if (((LA(1)==LITERAL_element))) {
			int _m361 = mark();
			synPredMatched361 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_element);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched361 = false;
			}
			rewind(_m361);
			inputState.guessing--;
		}
		if ( synPredMatched361 ) {
			match(LITERAL_element);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case STRING_LITERAL:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_order:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_at:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_to:
			case PLUS:
			case MINUS:
			case STAR:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 101:
			case 102:
			case 103:
			case 104:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 114:
			case LITERAL_following:
			case 116:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 119:
			case LITERAL_preceding:
			case 121:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_collection:
			case LITERAL_define:
			case LITERAL_isnot:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_validation:
			{
				contentExpr();
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
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case STRING_LITERAL:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_order:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_at:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_to:
			case PLUS:
			case MINUS:
			case STAR:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 101:
			case 102:
			case 103:
			case 104:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 114:
			case LITERAL_following:
			case 116:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 119:
			case LITERAL_preceding:
			case 121:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_collection:
			case LITERAL_define:
			case LITERAL_isnot:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_validation:
			{
				contentExpr();
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
		
		
		boolean synPredMatched367 = false;
		if (((LA(1)==LITERAL_attribute))) {
			int _m367 = mark();
			synPredMatched367 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_attribute);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched367 = false;
			}
			rewind(_m367);
			inputState.guessing--;
		}
		if ( synPredMatched367 ) {
			match(LITERAL_attribute);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case STRING_LITERAL:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_order:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_at:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_to:
			case PLUS:
			case MINUS:
			case STAR:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 101:
			case 102:
			case 103:
			case 104:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 114:
			case LITERAL_following:
			case 116:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 119:
			case LITERAL_preceding:
			case 121:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_collection:
			case LITERAL_define:
			case LITERAL_isnot:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_validation:
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
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case STRING_LITERAL:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_order:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_at:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_to:
			case PLUS:
			case MINUS:
			case STAR:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 101:
			case 102:
			case 103:
			case 104:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 114:
			case LITERAL_following:
			case 116:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 119:
			case LITERAL_preceding:
			case 121:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_collection:
			case LITERAL_define:
			case LITERAL_isnot:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_validation:
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
		expr();
		match(RCURLY);
	}
	
	public final void compCommentConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_comment);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void compPIConstructor() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		boolean synPredMatched374 = false;
		if (((LA(1)==101))) {
			int _m374 = mark();
			synPredMatched374 = true;
			inputState.guessing++;
			try {
				{
				match(101);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched374 = false;
			}
			rewind(_m374);
			inputState.guessing--;
		}
		if ( synPredMatched374 ) {
			match(101);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case STRING_LITERAL:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_order:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_at:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_to:
			case PLUS:
			case MINUS:
			case STAR:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 101:
			case 102:
			case 103:
			case 104:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 114:
			case LITERAL_following:
			case 116:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 119:
			case LITERAL_preceding:
			case 121:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_collection:
			case LITERAL_define:
			case LITERAL_isnot:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_validation:
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
		else if ((LA(1)==101)) {
			match(101);
			name=ncnameOrKeyword();
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case STRING_LITERAL:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_order:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case LITERAL_at:
			case DOLLAR:
			case LITERAL_external:
			case LPAREN:
			case LITERAL_as:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_satisfies:
			case LITERAL_case:
			case LITERAL_then:
			case LITERAL_else:
			case LITERAL_or:
			case LITERAL_and:
			case LT:
			case LITERAL_eq:
			case LITERAL_ne:
			case LITERAL_lt:
			case LITERAL_le:
			case LITERAL_gt:
			case LITERAL_ge:
			case LITERAL_is:
			case LITERAL_to:
			case PLUS:
			case MINUS:
			case STAR:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_instance:
			case LITERAL_of:
			case LITERAL_treat:
			case LITERAL_castable:
			case LITERAL_cast:
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 101:
			case 102:
			case 103:
			case 104:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 114:
			case LITERAL_following:
			case 116:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 119:
			case LITERAL_preceding:
			case 121:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_collection:
			case LITERAL_define:
			case LITERAL_isnot:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_validation:
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
	
	public final void contentExpr() throws RecognitionException, TokenStreamException {
		
		
		expr();
	}
	
	public final void atomicType() throws RecognitionException, TokenStreamException {
		
		
			String name = null;
		
		
		name=qName();
	}
	
	public final void itemType() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched387 = false;
		if (((LA(1)==LITERAL_item))) {
			int _m387 = mark();
			synPredMatched387 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_item);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched387 = false;
			}
			rewind(_m387);
			inputState.guessing--;
		}
		if ( synPredMatched387 ) {
			match(LITERAL_item);
			match(LPAREN);
			match(RPAREN);
		}
		else {
			boolean synPredMatched389 = false;
			if (((_tokenSet_9.member(LA(1))))) {
				int _m389 = mark();
				synPredMatched389 = true;
				inputState.guessing++;
				try {
					{
					match(102);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched389 = false;
				}
				rewind(_m389);
				inputState.guessing--;
			}
			if ( synPredMatched389 ) {
				kindTest();
			}
			else {
				boolean synPredMatched391 = false;
				if (((_tokenSet_9.member(LA(1))))) {
					int _m391 = mark();
					synPredMatched391 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_element);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched391 = false;
					}
					rewind(_m391);
					inputState.guessing--;
				}
				if ( synPredMatched391 ) {
					kindTest();
				}
				else {
					boolean synPredMatched393 = false;
					if (((_tokenSet_9.member(LA(1))))) {
						int _m393 = mark();
						synPredMatched393 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_attribute);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched393 = false;
						}
						rewind(_m393);
						inputState.guessing--;
					}
					if ( synPredMatched393 ) {
						kindTest();
					}
					else {
						boolean synPredMatched395 = false;
						if (((_tokenSet_9.member(LA(1))))) {
							int _m395 = mark();
							synPredMatched395 = true;
							inputState.guessing++;
							try {
								{
								match(101);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched395 = false;
							}
							rewind(_m395);
							inputState.guessing--;
						}
						if ( synPredMatched395 ) {
							kindTest();
						}
						else {
							boolean synPredMatched397 = false;
							if (((_tokenSet_9.member(LA(1))))) {
								int _m397 = mark();
								synPredMatched397 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_comment);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched397 = false;
								}
								rewind(_m397);
								inputState.guessing--;
							}
							if ( synPredMatched397 ) {
								kindTest();
							}
							else {
								boolean synPredMatched399 = false;
								if (((_tokenSet_9.member(LA(1))))) {
									int _m399 = mark();
									synPredMatched399 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_text);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched399 = false;
									}
									rewind(_m399);
									inputState.guessing--;
								}
								if ( synPredMatched399 ) {
									kindTest();
								}
								else {
									boolean synPredMatched401 = false;
									if (((_tokenSet_9.member(LA(1))))) {
										int _m401 = mark();
										synPredMatched401 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_node);
											match(LPAREN);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched401 = false;
										}
										rewind(_m401);
										inputState.guessing--;
									}
									if ( synPredMatched401 ) {
										kindTest();
									}
									else {
										boolean synPredMatched403 = false;
										if (((_tokenSet_9.member(LA(1))))) {
											int _m403 = mark();
											synPredMatched403 = true;
											inputState.guessing++;
											try {
												{
												match(103);
												match(LPAREN);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched403 = false;
											}
											rewind(_m403);
											inputState.guessing--;
										}
										if ( synPredMatched403 ) {
											kindTest();
										}
										else {
											boolean synPredMatched405 = false;
											if (((_tokenSet_9.member(LA(1))))) {
												int _m405 = mark();
												synPredMatched405 = true;
												inputState.guessing++;
												try {
													{
													match(104);
													match(LPAREN);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched405 = false;
												}
												rewind(_m405);
												inputState.guessing--;
											}
											if ( synPredMatched405 ) {
												kindTest();
											}
											else if ((_tokenSet_1.member(LA(1)))) {
												atomicType();
											}
											else {
												throw new NoViableAltException(LT(1), getFilename());
											}
											}}}}}}}}}
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
	
	public final void documentTest() throws RecognitionException, TokenStreamException {
		
		
		match(102);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_element:
		{
			elementTest();
			break;
		}
		case 104:
		{
			schemaElementTest();
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
	
	public final void elementTest() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(LITERAL_element);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
				case QUESTION:
				{
					match(QUESTION);
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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
		{
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
	
	public final void schemaElementTest() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(104);
		match(LPAREN);
		tmpStr=qName();
		match(RPAREN);
	}
	
	public final void schemaAttributeTest() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(103);
		match(LPAREN);
		tmpStr=qName();
		match(RPAREN);
	}
	
	public final void piTest() throws RecognitionException, TokenStreamException {
		
		
		match(101);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case NCNAME:
		{
			match(NCNAME);
			break;
		}
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
	
	public final void commentTest() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_comment);
		match(LPAREN);
		match(RPAREN);
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
	
	public final void attributeNameOrWildcard() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		switch ( LA(1)) {
		case STAR:
		{
			match(STAR);
			break;
		}
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
	
	public final void elementNameOrWildcard() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		switch ( LA(1)) {
		case STAR:
		{
			match(STAR);
			break;
		}
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_order:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case LITERAL_at:
		case LITERAL_external:
		case LITERAL_as:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_satisfies:
		case LITERAL_case:
		case LITERAL_then:
		case LITERAL_else:
		case LITERAL_or:
		case LITERAL_and:
		case LITERAL_eq:
		case LITERAL_ne:
		case LITERAL_lt:
		case LITERAL_le:
		case LITERAL_gt:
		case LITERAL_ge:
		case LITERAL_is:
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_instance:
		case LITERAL_of:
		case LITERAL_treat:
		case LITERAL_castable:
		case LITERAL_cast:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 101:
		case 102:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 114:
		case LITERAL_following:
		case 116:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 119:
		case LITERAL_preceding:
		case 121:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_collection:
		case LITERAL_define:
		case LITERAL_isnot:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_validation:
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
		case 119:
		{
			match(119);
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
		case 15:
		{
			match(15);
			if ( inputState.guessing==0 ) {
				name = "base-uri";
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
		case 114:
		{
			match(114);
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
		case 102:
		{
			match(102);
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
		case 116:
		{
			match(116);
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
		case 121:
		{
			match(121);
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
		case 101:
		{
			match(101);
			if ( inputState.guessing==0 ) {
				name = "processing-instruction";
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
		case LITERAL_type:
		{
			match(LITERAL_type);
			if ( inputState.guessing==0 ) {
				name = "type";
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
		"\"xquery\"",
		"\"version\"",
		"\"module\"",
		"\"namespace\"",
		"STRING_LITERAL",
		"\"encoding\"",
		"EQ",
		"\"declare\"",
		"\"xmlspace\"",
		"\"default\"",
		"\"collation\"",
		"\"base-uri\"",
		"\"construction\"",
		"\"ordering\"",
		"\"order\"",
		"\"inherit-namespaces\"",
		"\"import\"",
		"\"schema\"",
		"\"element\"",
		"\"function\"",
		"\"variable\"",
		"SEMICOLON",
		"\"preserve\"",
		"\"strip\"",
		"\"ordered\"",
		"\"unordered\"",
		"\"empty\"",
		"\"greatest\"",
		"\"least\"",
		"\"yes\"",
		"\"no\"",
		"\"at\"",
		"COMMA",
		"DOLLAR",
		"COLON",
		"\"external\"",
		"'('",
		"')'",
		"\"as\"",
		"LCURLY",
		"RCURLY",
		"\"for\"",
		"\"let\"",
		"\"some\"",
		"\"every\"",
		"\"typeswitch\"",
		"\"if\"",
		"\"try\"",
		"\"catch\"",
		"\"return\"",
		"\"in\"",
		"\"where\"",
		"\"stable\"",
		"\"by\"",
		"\"ascending\"",
		"\"descending\"",
		"\"satisfies\"",
		"\"case\"",
		"\"then\"",
		"\"else\"",
		"\"or\"",
		"\"and\"",
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
		"\"to\"",
		"PLUS",
		"MINUS",
		"STAR",
		"\"div\"",
		"\"idiv\"",
		"\"mod\"",
		"\"union\"",
		"UNION",
		"\"intersect\"",
		"\"except\"",
		"\"instance\"",
		"\"of\"",
		"\"treat\"",
		"\"castable\"",
		"\"cast\"",
		"\"validate\"",
		"SLASH",
		"DSLASH",
		"\"text\"",
		"\"node\"",
		"\"attribute\"",
		"\"comment\"",
		"\"processing-instruction\"",
		"\"document-node\"",
		"\"schema-attribute\"",
		"\"schema-element\"",
		"\"document\"",
		"SELF",
		"XML_COMMENT",
		"XML_PI",
		"AT",
		"PARENT",
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
		"LPPAREN",
		"RPPAREN",
		"DOUBLE_LITERAL",
		"DECIMAL_LITERAL",
		"INTEGER_LITERAL",
		"END_TAG_START",
		"ELEMENT_CONTENT",
		"QUOT",
		"APOS",
		"QUOT_ATTRIBUTE_CONTENT",
		"APOS_ATTRIBUTE_CONTENT",
		"XQDOC_COMMENT",
		"XML_COMMENT_END",
		"XML_PI_END",
		"XML_CDATA",
		"XML_CDATA_END",
		"QUESTION",
		"\"item\"",
		"NCNAME",
		"\"lax\"",
		"\"strict\"",
		"\"collection\"",
		"\"define\"",
		"\"isnot\"",
		"\"nillable\"",
		"\"type\"",
		"\"validation\"",
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
		"MU_EXTENSION",
		"EXTENSION_CONTENT",
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
		long[] data = { -28956703786512L, 8358680908395439095L, 2095136L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -30194459674384L, 288092379921802227L, 2095104L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -28956703786512L, 8358680908395439095L, 2095104L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { -30194459674384L, 288094029189243891L, 2095104L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { -4764673336518876158L, 576460752370532351L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 1024L, 7180L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { -30194459674384L, 288199582305641459L, 2095104L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { -28956703786512L, 8358573698495308791L, 2095104L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 0L, 8866496126124032L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 4194304L, 2190433320960L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { -30194459674384L, 288092379921933299L, 2095104L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 4194304L, 2448131358720L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 4194304L, 28836410425348L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = new long[8];
		data[0]=-16L;
		data[1]=-2147483657L;
		data[2]=17592186044415L;
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 8796093022208L, 26388279066628L, 257L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	
	}
