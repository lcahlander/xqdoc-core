// $ANTLR : "XQuery.g" -> "XQueryParser.java"$

	
/**  
 * Grammar definition for the April 2005 XQuery specification.
 */
	package org.xqdoc.xquery.parser.apr2005;

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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case PRAGMA:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case XQDOC_COMMENT:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		boolean synPredMatched1240 = false;
		if (((LA(1)==LITERAL_xquery))) {
			int _m1240 = mark();
			synPredMatched1240 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_xquery);
				match(LITERAL_version);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1240 = false;
			}
			rewind(_m1240);
			inputState.guessing--;
		}
		if ( synPredMatched1240 ) {
			versionDecl();
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		boolean synPredMatched1245 = false;
		if (((LA(1)==LITERAL_module||LA(1)==XQDOC_COMMENT))) {
			int _m1245 = mark();
			synPredMatched1245 = true;
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
				synPredMatched1245 = false;
			}
			rewind(_m1245);
			inputState.guessing--;
		}
		if ( synPredMatched1245 ) {
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
		_loop1294:
		do {
			if ((LA(1)==LITERAL_declare||LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT)) {
				{
				boolean synPredMatched1257 = false;
				if (((LA(1)==LITERAL_declare))) {
					int _m1257 = mark();
					synPredMatched1257 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_declare);
						match(12);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1257 = false;
					}
					rewind(_m1257);
					inputState.guessing--;
				}
				if ( synPredMatched1257 ) {
					boundarySpaceDecl();
				}
				else {
					boolean synPredMatched1259 = false;
					if (((LA(1)==LITERAL_declare))) {
						int _m1259 = mark();
						synPredMatched1259 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_declare);
							match(LITERAL_default);
							match(LITERAL_collation);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1259 = false;
						}
						rewind(_m1259);
						inputState.guessing--;
					}
					if ( synPredMatched1259 ) {
						defaultCollationDecl();
					}
					else {
						boolean synPredMatched1261 = false;
						if (((LA(1)==LITERAL_declare))) {
							int _m1261 = mark();
							synPredMatched1261 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_declare);
								match(15);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched1261 = false;
							}
							rewind(_m1261);
							inputState.guessing--;
						}
						if ( synPredMatched1261 ) {
							baseUriDecl();
						}
						else {
							boolean synPredMatched1263 = false;
							if (((LA(1)==LITERAL_declare))) {
								int _m1263 = mark();
								synPredMatched1263 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_declare);
									match(LITERAL_construction);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched1263 = false;
								}
								rewind(_m1263);
								inputState.guessing--;
							}
							if ( synPredMatched1263 ) {
								constructionDecl();
							}
							else {
								boolean synPredMatched1265 = false;
								if (((LA(1)==LITERAL_declare))) {
									int _m1265 = mark();
									synPredMatched1265 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_declare);
										match(LITERAL_ordering);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched1265 = false;
									}
									rewind(_m1265);
									inputState.guessing--;
								}
								if ( synPredMatched1265 ) {
									orderingModeDecl();
								}
								else {
									boolean synPredMatched1267 = false;
									if (((LA(1)==LITERAL_declare))) {
										int _m1267 = mark();
										synPredMatched1267 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_declare);
											match(LITERAL_default);
											match(LITERAL_order);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched1267 = false;
										}
										rewind(_m1267);
										inputState.guessing--;
									}
									if ( synPredMatched1267 ) {
										emptyOrderingDecl();
									}
									else {
										boolean synPredMatched1269 = false;
										if (((LA(1)==LITERAL_declare))) {
											int _m1269 = mark();
											synPredMatched1269 = true;
											inputState.guessing++;
											try {
												{
												match(LITERAL_declare);
												match(19);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched1269 = false;
											}
											rewind(_m1269);
											inputState.guessing--;
										}
										if ( synPredMatched1269 ) {
											copyNamespacesDecl();
										}
										else {
											boolean synPredMatched1271 = false;
											if (((LA(1)==LITERAL_import))) {
												int _m1271 = mark();
												synPredMatched1271 = true;
												inputState.guessing++;
												try {
													{
													match(LITERAL_import);
													match(LITERAL_schema);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched1271 = false;
												}
												rewind(_m1271);
												inputState.guessing--;
											}
											if ( synPredMatched1271 ) {
												schemaImport();
											}
											else {
												boolean synPredMatched1273 = false;
												if (((LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT))) {
													int _m1273 = mark();
													synPredMatched1273 = true;
													inputState.guessing++;
													try {
														{
														match(LITERAL_import);
														match(LITERAL_module);
														}
													}
													catch (RecognitionException pe) {
														synPredMatched1273 = false;
													}
													rewind(_m1273);
													inputState.guessing--;
												}
												if ( synPredMatched1273 ) {
													moduleImport();
												}
												else {
													boolean synPredMatched1276 = false;
													if (((LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT))) {
														int _m1276 = mark();
														synPredMatched1276 = true;
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
															synPredMatched1276 = false;
														}
														rewind(_m1276);
														inputState.guessing--;
													}
													if ( synPredMatched1276 ) {
														moduleImport();
													}
													else {
														boolean synPredMatched1278 = false;
														if (((LA(1)==LITERAL_declare))) {
															int _m1278 = mark();
															synPredMatched1278 = true;
															inputState.guessing++;
															try {
																{
																match(LITERAL_declare);
																match(LITERAL_namespace);
																}
															}
															catch (RecognitionException pe) {
																synPredMatched1278 = false;
															}
															rewind(_m1278);
															inputState.guessing--;
														}
														if ( synPredMatched1278 ) {
															namespaceDecl();
														}
														else {
															boolean synPredMatched1281 = false;
															if (((LA(1)==LITERAL_declare))) {
																int _m1281 = mark();
																synPredMatched1281 = true;
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
																	synPredMatched1281 = false;
																}
																rewind(_m1281);
																inputState.guessing--;
															}
															if ( synPredMatched1281 ) {
																setterOld();
															}
															else {
																boolean synPredMatched1283 = false;
																if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																	int _m1283 = mark();
																	synPredMatched1283 = true;
																	inputState.guessing++;
																	try {
																		{
																		match(LITERAL_declare);
																		match(LITERAL_variable);
																		}
																	}
																	catch (RecognitionException pe) {
																		synPredMatched1283 = false;
																	}
																	rewind(_m1283);
																	inputState.guessing--;
																}
																if ( synPredMatched1283 ) {
																	varDecl();
																}
																else {
																	boolean synPredMatched1286 = false;
																	if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																		int _m1286 = mark();
																		synPredMatched1286 = true;
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
																			synPredMatched1286 = false;
																		}
																		rewind(_m1286);
																		inputState.guessing--;
																	}
																	if ( synPredMatched1286 ) {
																		varDecl();
																	}
																	else {
																		boolean synPredMatched1288 = false;
																		if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																			int _m1288 = mark();
																			synPredMatched1288 = true;
																			inputState.guessing++;
																			try {
																				{
																				match(LITERAL_declare);
																				match(LITERAL_function);
																				}
																			}
																			catch (RecognitionException pe) {
																				synPredMatched1288 = false;
																			}
																			rewind(_m1288);
																			inputState.guessing--;
																		}
																		if ( synPredMatched1288 ) {
																			functionDecl();
																		}
																		else {
																			boolean synPredMatched1291 = false;
																			if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																				int _m1291 = mark();
																				synPredMatched1291 = true;
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
																					synPredMatched1291 = false;
																				}
																				rewind(_m1291);
																				inputState.guessing--;
																			}
																			if ( synPredMatched1291 ) {
																				functionDecl();
																			}
																			else {
																				boolean synPredMatched1293 = false;
																				if (((LA(1)==LITERAL_declare))) {
																					int _m1293 = mark();
																					synPredMatched1293 = true;
																					inputState.guessing++;
																					try {
																						{
																						match(LITERAL_declare);
																						match(LITERAL_option);
																						}
																					}
																					catch (RecognitionException pe) {
																						synPredMatched1293 = false;
																					}
																					rewind(_m1293);
																					inputState.guessing--;
																				}
																				if ( synPredMatched1293 ) {
																					optionDecl();
																				}
																				else {
																					throw new NoViableAltException(LT(1), getFilename());
																				}
																				}}}}}}}}}}}}}}}}
																				}
																				separator();
																			}
																			else {
																				break _loop1294;
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case LITERAL_void:
		case LITERAL_item:
		case LITERAL_lax:
		case LITERAL_strict:
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
	
	public final void boundarySpaceDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(12);
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
	
	public final void copyNamespacesDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(19);
		{
		switch ( LA(1)) {
		case LITERAL_preserve:
		{
			match(LITERAL_preserve);
			break;
		}
		case 34:
		{
			match(34);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(COMMA);
		{
		switch ( LA(1)) {
		case LITERAL_inherit:
		{
			match(LITERAL_inherit);
			break;
		}
		case 37:
		{
			match(37);
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
			_loop1335:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(STRING_LITERAL);
				}
				else {
					break _loop1335;
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
			_loop1344:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(STRING_LITERAL);
				}
				else {
					break _loop1344;
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
	
	public final void optionDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(LITERAL_option);
		qName();
		match(STRING_LITERAL);
	}
	
	public final void setter() throws RecognitionException, TokenStreamException {
		
		
		{
		{
		boolean synPredMatched1299 = false;
		if (((LA(1)==LITERAL_declare))) {
			int _m1299 = mark();
			synPredMatched1299 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_declare);
				match(12);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1299 = false;
			}
			rewind(_m1299);
			inputState.guessing--;
		}
		if ( synPredMatched1299 ) {
			boundarySpaceDecl();
		}
		else {
			boolean synPredMatched1301 = false;
			if (((LA(1)==LITERAL_declare))) {
				int _m1301 = mark();
				synPredMatched1301 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_declare);
					match(LITERAL_default);
					match(LITERAL_collation);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1301 = false;
				}
				rewind(_m1301);
				inputState.guessing--;
			}
			if ( synPredMatched1301 ) {
				defaultCollationDecl();
			}
			else {
				boolean synPredMatched1303 = false;
				if (((LA(1)==LITERAL_declare))) {
					int _m1303 = mark();
					synPredMatched1303 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_declare);
						match(15);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1303 = false;
					}
					rewind(_m1303);
					inputState.guessing--;
				}
				if ( synPredMatched1303 ) {
					baseUriDecl();
				}
				else {
					boolean synPredMatched1305 = false;
					if (((LA(1)==LITERAL_declare))) {
						int _m1305 = mark();
						synPredMatched1305 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_declare);
							match(LITERAL_construction);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1305 = false;
						}
						rewind(_m1305);
						inputState.guessing--;
					}
					if ( synPredMatched1305 ) {
						constructionDecl();
					}
					else {
						boolean synPredMatched1307 = false;
						if (((LA(1)==LITERAL_declare))) {
							int _m1307 = mark();
							synPredMatched1307 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_declare);
								match(LITERAL_ordering);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched1307 = false;
							}
							rewind(_m1307);
							inputState.guessing--;
						}
						if ( synPredMatched1307 ) {
							orderingModeDecl();
						}
						else {
							boolean synPredMatched1309 = false;
							if (((LA(1)==LITERAL_declare))) {
								int _m1309 = mark();
								synPredMatched1309 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_declare);
									match(LITERAL_default);
									match(LITERAL_order);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched1309 = false;
								}
								rewind(_m1309);
								inputState.guessing--;
							}
							if ( synPredMatched1309 ) {
								emptyOrderingDecl();
							}
							else {
								boolean synPredMatched1311 = false;
								if (((LA(1)==LITERAL_declare))) {
									int _m1311 = mark();
									synPredMatched1311 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_declare);
										match(19);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched1311 = false;
									}
									rewind(_m1311);
									inputState.guessing--;
								}
								if ( synPredMatched1311 ) {
									copyNamespacesDecl();
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}}}
								}
								separator();
								}
							}
							
	public final String  qName() throws RecognitionException, TokenStreamException {
		String name;
		
		
			name= null;
			String name2;
		
		
		boolean synPredMatched1671 = false;
		if (((_tokenSet_1.member(LA(1))))) {
			int _m1671 = mark();
			synPredMatched1671 = true;
			inputState.guessing++;
			try {
				{
				ncnameOrKeyword();
				match(COLON);
				ncnameOrKeyword();
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1671 = false;
			}
			rewind(_m1671);
			inputState.guessing--;
		}
		if ( synPredMatched1671 ) {
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
	
	public final void typeDeclaration() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_as);
		sequenceType();
	}
	
	public final void exprSingle() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched1372 = false;
		if (((LA(1)==LITERAL_for||LA(1)==LITERAL_let))) {
			int _m1372 = mark();
			synPredMatched1372 = true;
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
				synPredMatched1372 = false;
			}
			rewind(_m1372);
			inputState.guessing--;
		}
		if ( synPredMatched1372 ) {
			flworExpr();
		}
		else {
			boolean synPredMatched1375 = false;
			if (((LA(1)==LITERAL_some||LA(1)==LITERAL_every))) {
				int _m1375 = mark();
				synPredMatched1375 = true;
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
					synPredMatched1375 = false;
				}
				rewind(_m1375);
				inputState.guessing--;
			}
			if ( synPredMatched1375 ) {
				quantifiedExpr();
			}
			else {
				boolean synPredMatched1377 = false;
				if (((LA(1)==LITERAL_typeswitch))) {
					int _m1377 = mark();
					synPredMatched1377 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_typeswitch);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1377 = false;
					}
					rewind(_m1377);
					inputState.guessing--;
				}
				if ( synPredMatched1377 ) {
					typeswitchExpr();
				}
				else {
					boolean synPredMatched1379 = false;
					if (((LA(1)==LITERAL_if))) {
						int _m1379 = mark();
						synPredMatched1379 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_if);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1379 = false;
						}
						rewind(_m1379);
						inputState.guessing--;
					}
					if ( synPredMatched1379 ) {
						ifExpr();
					}
					else {
						boolean synPredMatched1381 = false;
						if (((LA(1)==LITERAL_try))) {
							int _m1381 = mark();
							synPredMatched1381 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_try);
								match(LCURLY);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched1381 = false;
							}
							rewind(_m1381);
							inputState.guessing--;
						}
						if ( synPredMatched1381 ) {
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
		_loop1361:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				param();
			}
			else {
				break _loop1361;
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
		
		
		boolean synPredMatched1624 = false;
		if (((LA(1)==LITERAL_void))) {
			int _m1624 = mark();
			synPredMatched1624 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_void);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1624 = false;
			}
			rewind(_m1624);
			inputState.guessing--;
		}
		if ( synPredMatched1624 ) {
			match(LITERAL_void);
			match(LPAREN);
			match(RPAREN);
		}
		else if ((_tokenSet_1.member(LA(1)))) {
			itemType();
			{
			if ((LA(1)==PLUS||LA(1)==STAR||LA(1)==QUESTION)) {
				occurrenceIndicator();
			}
			else if ((_tokenSet_3.member(LA(1)))) {
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
		_loop1368:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop1368;
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
		int _cnt1385=0;
		_loop1385:
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
				if ( _cnt1385>=1 ) { break _loop1385; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			}
			_cnt1385++;
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
		_loop1415:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				quantifiedInVarBinding();
			}
			else {
				break _loop1415;
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
		int _cnt1420=0;
		_loop1420:
		do {
			if ((LA(1)==LITERAL_case)) {
				caseClause();
			}
			else {
				if ( _cnt1420>=1 ) { break _loop1420; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt1420++;
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
		_loop1427:
		do {
			if ((LA(1)==LITERAL_or)) {
				match(LITERAL_or);
				andExpr();
			}
			else {
				break _loop1427;
			}
			
		} while (true);
		}
	}
	
	public final void forClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_for);
		inVarBinding();
		{
		_loop1390:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				inVarBinding();
			}
			else {
				break _loop1390;
			}
			
		} while (true);
		}
	}
	
	public final void letClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_let);
		letVarBinding();
		{
		_loop1397:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				letVarBinding();
			}
			else {
				break _loop1397;
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
		_loop1405:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				orderSpec();
			}
			else {
				break _loop1405;
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		_loop1430:
		do {
			if ((LA(1)==LITERAL_and)) {
				match(LITERAL_and);
				comparisonExpr();
			}
			else {
				break _loop1430;
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
			boolean synPredMatched1434 = false;
			if (((LA(1)==LT))) {
				int _m1434 = mark();
				synPredMatched1434 = true;
				inputState.guessing++;
				try {
					{
					match(LT);
					match(LT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1434 = false;
				}
				rewind(_m1434);
				inputState.guessing--;
			}
			if ( synPredMatched1434 ) {
				match(LT);
				match(LT);
				rangeExpr();
			}
			else {
				boolean synPredMatched1436 = false;
				if (((LA(1)==GT))) {
					int _m1436 = mark();
					synPredMatched1436 = true;
					inputState.guessing++;
					try {
						{
						match(GT);
						match(GT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1436 = false;
					}
					rewind(_m1436);
					inputState.guessing--;
				}
				if ( synPredMatched1436 ) {
					match(GT);
					match(GT);
					rangeExpr();
				}
				else if ((_tokenSet_4.member(LA(1)))) {
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
		_loop1448:
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
				break _loop1448;
			}
			
		} while (true);
		}
	}
	
	public final void multiplicativeExpr() throws RecognitionException, TokenStreamException {
		
		
		unionExpr();
		{
		_loop1452:
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
				break _loop1452;
			}
			
		} while (true);
		}
	}
	
	public final void unionExpr() throws RecognitionException, TokenStreamException {
		
		
		intersectExceptExpr();
		{
		_loop1456:
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
				break _loop1456;
			}
			
		} while (true);
		}
	}
	
	public final void intersectExceptExpr() throws RecognitionException, TokenStreamException {
		
		
		instanceofExpr();
		{
		_loop1460:
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
				break _loop1460;
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
		_loop1471:
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
				break _loop1471;
			}
			}
		} while (true);
		}
		valueExpr();
	}
	
	public final void valueExpr() throws RecognitionException, TokenStreamException {
		
		
		if ((LA(1)==LITERAL_validate)) {
			validateExpr();
		}
		else if ((_tokenSet_5.member(LA(1)))) {
			pathExpr();
		}
		else if ((LA(1)==PRAGMA)) {
			extensionExpr();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
			boolean synPredMatched1481 = false;
			if (((LA(1)==SLASH))) {
				int _m1481 = mark();
				synPredMatched1481 = true;
				inputState.guessing++;
				try {
					{
					match(SLASH);
					relativePathExpr();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1481 = false;
				}
				rewind(_m1481);
				inputState.guessing--;
			}
			if ( synPredMatched1481 ) {
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
	
	public final void extensionExpr() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt1477=0;
		_loop1477:
		do {
			if ((LA(1)==PRAGMA)) {
				match(PRAGMA);
			}
			else {
				if ( _cnt1477>=1 ) { break _loop1477; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt1477++;
		} while (true);
		}
		match(LCURLY);
		{
		switch ( LA(1)) {
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case STRING_LITERAL:
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case PRAGMA:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		_loop1485:
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
				break _loop1485;
			}
			
		} while (true);
		}
	}
	
	public final void stepExpr() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched1489 = false;
		if (((_tokenSet_6.member(LA(1))))) {
			int _m1489 = mark();
			synPredMatched1489 = true;
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
				case 104:
				{
					match(104);
					break;
				}
				case 105:
				{
					match(105);
					break;
				}
				case 106:
				{
					match(106);
					break;
				}
				case 107:
				{
					match(107);
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
				synPredMatched1489 = false;
			}
			rewind(_m1489);
			inputState.guessing--;
		}
		if ( synPredMatched1489 ) {
			axisStep();
		}
		else {
			boolean synPredMatched1492 = false;
			if (((_tokenSet_7.member(LA(1))))) {
				int _m1492 = mark();
				synPredMatched1492 = true;
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
					case 104:
					{
						match(104);
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
					synPredMatched1492 = false;
				}
				rewind(_m1492);
				inputState.guessing--;
			}
			if ( synPredMatched1492 ) {
				filterExpr();
			}
			else {
				boolean synPredMatched1495 = false;
				if (((_tokenSet_7.member(LA(1))))) {
					int _m1495 = mark();
					synPredMatched1495 = true;
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
						qName();
						match(LCURLY);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1495 = false;
					}
					rewind(_m1495);
					inputState.guessing--;
				}
				if ( synPredMatched1495 ) {
					filterExpr();
				}
				else {
					boolean synPredMatched1498 = false;
					if (((_tokenSet_7.member(LA(1))))) {
						int _m1498 = mark();
						synPredMatched1498 = true;
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
							case LITERAL_encoding:
							case LITERAL_declare:
							case 12:
							case LITERAL_default:
							case LITERAL_collation:
							case 15:
							case LITERAL_construction:
							case LITERAL_ordering:
							case LITERAL_order:
							case 19:
							case LITERAL_import:
							case LITERAL_schema:
							case LITERAL_element:
							case LITERAL_function:
							case LITERAL_variable:
							case LITERAL_option:
							case LITERAL_preserve:
							case LITERAL_strip:
							case LITERAL_ordered:
							case LITERAL_unordered:
							case LITERAL_empty:
							case LITERAL_greatest:
							case LITERAL_least:
							case 34:
							case LITERAL_inherit:
							case 37:
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
							case LITERAL_validate:
							case LITERAL_text:
							case LITERAL_node:
							case LITERAL_attribute:
							case LITERAL_comment:
							case 104:
							case 105:
							case 106:
							case 107:
							case LITERAL_document:
							case LITERAL_child:
							case LITERAL_self:
							case LITERAL_descendant:
							case 117:
							case LITERAL_following:
							case 119:
							case LITERAL_parent:
							case LITERAL_ancestor:
							case 122:
							case LITERAL_preceding:
							case 124:
							case LITERAL_void:
							case LITERAL_item:
							case NCNAME:
							case LITERAL_lax:
							case LITERAL_strict:
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
							synPredMatched1498 = false;
						}
						rewind(_m1498);
						inputState.guessing--;
					}
					if ( synPredMatched1498 ) {
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
		
		
		boolean synPredMatched1503 = false;
		if (((_tokenSet_8.member(LA(1))))) {
			int _m1503 = mark();
			synPredMatched1503 = true;
			inputState.guessing++;
			try {
				{
				forwardAxisSpecifier();
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1503 = false;
			}
			rewind(_m1503);
			inputState.guessing--;
		}
		if ( synPredMatched1503 ) {
			forwardAxis();
			nodeTest();
		}
		else {
			boolean synPredMatched1505 = false;
			if ((((LA(1) >= LITERAL_parent && LA(1) <= 124)))) {
				int _m1505 = mark();
				synPredMatched1505 = true;
				inputState.guessing++;
				try {
					{
					reverseAxisSpecifier();
					match(COLON);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1505 = false;
				}
				rewind(_m1505);
				inputState.guessing--;
			}
			if ( synPredMatched1505 ) {
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
		_loop1541:
		do {
			if ((LA(1)==LPPAREN)) {
				predicate();
			}
			else {
				break _loop1541;
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
		case 117:
		{
			match(117);
			break;
		}
		case LITERAL_following:
		{
			match(LITERAL_following);
			break;
		}
		case 119:
		{
			match(119);
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
		
		
		boolean synPredMatched1514 = false;
		if (((_tokenSet_9.member(LA(1))))) {
			int _m1514 = mark();
			synPredMatched1514 = true;
			inputState.guessing++;
			try {
				{
				match(105);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1514 = false;
			}
			rewind(_m1514);
			inputState.guessing--;
		}
		if ( synPredMatched1514 ) {
			kindTest();
		}
		else {
			boolean synPredMatched1516 = false;
			if (((_tokenSet_9.member(LA(1))))) {
				int _m1516 = mark();
				synPredMatched1516 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_element);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1516 = false;
				}
				rewind(_m1516);
				inputState.guessing--;
			}
			if ( synPredMatched1516 ) {
				kindTest();
			}
			else {
				boolean synPredMatched1518 = false;
				if (((_tokenSet_9.member(LA(1))))) {
					int _m1518 = mark();
					synPredMatched1518 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_attribute);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1518 = false;
					}
					rewind(_m1518);
					inputState.guessing--;
				}
				if ( synPredMatched1518 ) {
					kindTest();
				}
				else {
					boolean synPredMatched1520 = false;
					if (((_tokenSet_9.member(LA(1))))) {
						int _m1520 = mark();
						synPredMatched1520 = true;
						inputState.guessing++;
						try {
							{
							match(104);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1520 = false;
						}
						rewind(_m1520);
						inputState.guessing--;
					}
					if ( synPredMatched1520 ) {
						kindTest();
					}
					else {
						boolean synPredMatched1522 = false;
						if (((_tokenSet_9.member(LA(1))))) {
							int _m1522 = mark();
							synPredMatched1522 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_comment);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched1522 = false;
							}
							rewind(_m1522);
							inputState.guessing--;
						}
						if ( synPredMatched1522 ) {
							kindTest();
						}
						else {
							boolean synPredMatched1524 = false;
							if (((_tokenSet_9.member(LA(1))))) {
								int _m1524 = mark();
								synPredMatched1524 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_text);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched1524 = false;
								}
								rewind(_m1524);
								inputState.guessing--;
							}
							if ( synPredMatched1524 ) {
								kindTest();
							}
							else {
								boolean synPredMatched1526 = false;
								if (((_tokenSet_9.member(LA(1))))) {
									int _m1526 = mark();
									synPredMatched1526 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_node);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched1526 = false;
									}
									rewind(_m1526);
									inputState.guessing--;
								}
								if ( synPredMatched1526 ) {
									kindTest();
								}
								else {
									boolean synPredMatched1528 = false;
									if (((_tokenSet_9.member(LA(1))))) {
										int _m1528 = mark();
										synPredMatched1528 = true;
										inputState.guessing++;
										try {
											{
											match(106);
											match(LPAREN);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched1528 = false;
										}
										rewind(_m1528);
										inputState.guessing--;
									}
									if ( synPredMatched1528 ) {
										kindTest();
									}
									else {
										boolean synPredMatched1530 = false;
										if (((_tokenSet_9.member(LA(1))))) {
											int _m1530 = mark();
											synPredMatched1530 = true;
											inputState.guessing++;
											try {
												{
												match(107);
												match(LPAREN);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched1530 = false;
											}
											rewind(_m1530);
											inputState.guessing--;
										}
										if ( synPredMatched1530 ) {
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
		case 122:
		{
			match(122);
			break;
		}
		case LITERAL_preceding:
		{
			match(LITERAL_preceding);
			break;
		}
		case 124:
		{
			match(124);
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case AT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
			case LITERAL_encoding:
			case LITERAL_declare:
			case 12:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_construction:
			case LITERAL_ordering:
			case LITERAL_order:
			case 19:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_option:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case 34:
			case LITERAL_inherit:
			case 37:
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
			case LITERAL_validate:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case 106:
			case 107:
			case LITERAL_document:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 117:
			case LITERAL_following:
			case 119:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 122:
			case LITERAL_preceding:
			case 124:
			case LITERAL_void:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
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
		case 105:
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
		case 107:
		{
			schemaElementTest();
			break;
		}
		case 106:
		{
			schemaAttributeTest();
			break;
		}
		case 104:
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
		
		
		boolean synPredMatched1534 = false;
		if (((_tokenSet_10.member(LA(1))))) {
			int _m1534 = mark();
			synPredMatched1534 = true;
			inputState.guessing++;
			try {
				{
				switch ( LA(1)) {
				case LITERAL_xquery:
				case LITERAL_version:
				case LITERAL_module:
				case LITERAL_namespace:
				case LITERAL_encoding:
				case LITERAL_declare:
				case 12:
				case LITERAL_default:
				case LITERAL_collation:
				case 15:
				case LITERAL_construction:
				case LITERAL_ordering:
				case LITERAL_order:
				case 19:
				case LITERAL_import:
				case LITERAL_schema:
				case LITERAL_element:
				case LITERAL_function:
				case LITERAL_variable:
				case LITERAL_option:
				case LITERAL_preserve:
				case LITERAL_strip:
				case LITERAL_ordered:
				case LITERAL_unordered:
				case LITERAL_empty:
				case LITERAL_greatest:
				case LITERAL_least:
				case 34:
				case LITERAL_inherit:
				case 37:
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
				case LITERAL_validate:
				case LITERAL_text:
				case LITERAL_node:
				case LITERAL_attribute:
				case LITERAL_comment:
				case 104:
				case 105:
				case 106:
				case 107:
				case LITERAL_document:
				case LITERAL_child:
				case LITERAL_self:
				case LITERAL_descendant:
				case 117:
				case LITERAL_following:
				case 119:
				case LITERAL_parent:
				case LITERAL_ancestor:
				case 122:
				case LITERAL_preceding:
				case 124:
				case LITERAL_void:
				case LITERAL_item:
				case NCNAME:
				case LITERAL_lax:
				case LITERAL_strict:
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
				synPredMatched1534 = false;
			}
			rewind(_m1534);
			inputState.guessing--;
		}
		if ( synPredMatched1534 ) {
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
		
		boolean synPredMatched1537 = false;
		if (((LA(1)==STAR))) {
			int _m1537 = mark();
			synPredMatched1537 = true;
			inputState.guessing++;
			try {
				{
				match(STAR);
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1537 = false;
			}
			rewind(_m1537);
			inputState.guessing--;
		}
		if ( synPredMatched1537 ) {
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
			if ((LA(1)==LITERAL_ordered)) {
				orderedExpr();
			}
			else if ((LA(1)==LITERAL_unordered)) {
				unorderedExpr();
			}
			else {
				boolean synPredMatched1546 = false;
				if (((_tokenSet_11.member(LA(1))))) {
					int _m1546 = mark();
					synPredMatched1546 = true;
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
						case 104:
						{
							match(104);
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
						synPredMatched1546 = false;
					}
					rewind(_m1546);
					inputState.guessing--;
				}
				if ( synPredMatched1546 ) {
					computedConstructor();
				}
				else {
					boolean synPredMatched1549 = false;
					if (((_tokenSet_11.member(LA(1))))) {
						int _m1549 = mark();
						synPredMatched1549 = true;
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
							qName();
							match(LCURLY);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1549 = false;
						}
						rewind(_m1549);
						inputState.guessing--;
					}
					if ( synPredMatched1549 ) {
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
				}}}
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
		case 104:
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
		case 104:
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case PRAGMA:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case PRAGMA:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		_loop1561:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop1561;
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
		
		
		boolean synPredMatched1567 = false;
		if (((LA(1)==LT))) {
			int _m1567 = mark();
			synPredMatched1567 = true;
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
				synPredMatched1567 = false;
			}
			rewind(_m1567);
			inputState.guessing--;
		}
		if ( synPredMatched1567 ) {
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
		_loop1578:
		do {
			if ((_tokenSet_14.member(LA(1)))) {
				dirElemContent();
			}
			else {
				break _loop1578;
			}
			
		} while (true);
		}
	}
	
	public final void attributeList() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt1582=0;
		_loop1582:
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				attributeDef();
			}
			else {
				if ( _cnt1582>=1 ) { break _loop1582; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt1582++;
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
			_loop1586:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==QUOT_ATTRIBUTE_CONTENT)) {
					quotAttrValueContent();
				}
				else {
					break _loop1586;
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
			_loop1588:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==APOS_ATTRIBUTE_CONTENT)) {
					aposAttrValueContent();
				}
				else {
					break _loop1588;
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
		
		
		boolean synPredMatched1593 = false;
		if (((LA(1)==LCURLY))) {
			int _m1593 = mark();
			synPredMatched1593 = true;
			inputState.guessing++;
			try {
				{
				match(LCURLY);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1593 = false;
			}
			rewind(_m1593);
			inputState.guessing--;
		}
		if ( synPredMatched1593 ) {
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
		
		
		boolean synPredMatched1603 = false;
		if (((LA(1)==LITERAL_element))) {
			int _m1603 = mark();
			synPredMatched1603 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_element);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1603 = false;
			}
			rewind(_m1603);
			inputState.guessing--;
		}
		if ( synPredMatched1603 ) {
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
			case LITERAL_encoding:
			case LITERAL_declare:
			case 12:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_construction:
			case LITERAL_ordering:
			case LITERAL_order:
			case 19:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_option:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case 34:
			case LITERAL_inherit:
			case 37:
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
			case PRAGMA:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case 106:
			case 107:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 117:
			case LITERAL_following:
			case 119:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 122:
			case LITERAL_preceding:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_void:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
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
			case LITERAL_encoding:
			case LITERAL_declare:
			case 12:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_construction:
			case LITERAL_ordering:
			case LITERAL_order:
			case 19:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_option:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case 34:
			case LITERAL_inherit:
			case 37:
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
			case PRAGMA:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case 106:
			case 107:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 117:
			case LITERAL_following:
			case 119:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 122:
			case LITERAL_preceding:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_void:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
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
		
		
		boolean synPredMatched1609 = false;
		if (((LA(1)==LITERAL_attribute))) {
			int _m1609 = mark();
			synPredMatched1609 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_attribute);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1609 = false;
			}
			rewind(_m1609);
			inputState.guessing--;
		}
		if ( synPredMatched1609 ) {
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
			case LITERAL_encoding:
			case LITERAL_declare:
			case 12:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_construction:
			case LITERAL_ordering:
			case LITERAL_order:
			case 19:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_option:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case 34:
			case LITERAL_inherit:
			case 37:
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
			case PRAGMA:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case 106:
			case 107:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 117:
			case LITERAL_following:
			case 119:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 122:
			case LITERAL_preceding:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_void:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
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
			case LITERAL_encoding:
			case LITERAL_declare:
			case 12:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_construction:
			case LITERAL_ordering:
			case LITERAL_order:
			case 19:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_option:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case 34:
			case LITERAL_inherit:
			case 37:
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
			case PRAGMA:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case 106:
			case 107:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 117:
			case LITERAL_following:
			case 119:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 122:
			case LITERAL_preceding:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_void:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
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
		
		
		boolean synPredMatched1616 = false;
		if (((LA(1)==104))) {
			int _m1616 = mark();
			synPredMatched1616 = true;
			inputState.guessing++;
			try {
				{
				match(104);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1616 = false;
			}
			rewind(_m1616);
			inputState.guessing--;
		}
		if ( synPredMatched1616 ) {
			match(104);
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
			case LITERAL_encoding:
			case LITERAL_declare:
			case 12:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_construction:
			case LITERAL_ordering:
			case LITERAL_order:
			case 19:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_option:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case 34:
			case LITERAL_inherit:
			case 37:
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
			case PRAGMA:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case 106:
			case 107:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 117:
			case LITERAL_following:
			case 119:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 122:
			case LITERAL_preceding:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_void:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
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
		else if ((LA(1)==104)) {
			match(104);
			name=ncnameOrKeyword();
			match(LCURLY);
			{
			switch ( LA(1)) {
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case STRING_LITERAL:
			case LITERAL_encoding:
			case LITERAL_declare:
			case 12:
			case LITERAL_default:
			case LITERAL_collation:
			case 15:
			case LITERAL_construction:
			case LITERAL_ordering:
			case LITERAL_order:
			case 19:
			case LITERAL_import:
			case LITERAL_schema:
			case LITERAL_element:
			case LITERAL_function:
			case LITERAL_variable:
			case LITERAL_option:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_ordered:
			case LITERAL_unordered:
			case LITERAL_empty:
			case LITERAL_greatest:
			case LITERAL_least:
			case 34:
			case LITERAL_inherit:
			case 37:
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
			case PRAGMA:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case 106:
			case 107:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 117:
			case LITERAL_following:
			case 119:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 122:
			case LITERAL_preceding:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case LITERAL_void:
			case LITERAL_item:
			case NCNAME:
			case LITERAL_lax:
			case LITERAL_strict:
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
		
		
		boolean synPredMatched1629 = false;
		if (((LA(1)==LITERAL_item))) {
			int _m1629 = mark();
			synPredMatched1629 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_item);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched1629 = false;
			}
			rewind(_m1629);
			inputState.guessing--;
		}
		if ( synPredMatched1629 ) {
			match(LITERAL_item);
			match(LPAREN);
			match(RPAREN);
		}
		else {
			boolean synPredMatched1631 = false;
			if (((_tokenSet_9.member(LA(1))))) {
				int _m1631 = mark();
				synPredMatched1631 = true;
				inputState.guessing++;
				try {
					{
					match(105);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1631 = false;
				}
				rewind(_m1631);
				inputState.guessing--;
			}
			if ( synPredMatched1631 ) {
				kindTest();
			}
			else {
				boolean synPredMatched1633 = false;
				if (((_tokenSet_9.member(LA(1))))) {
					int _m1633 = mark();
					synPredMatched1633 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_element);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1633 = false;
					}
					rewind(_m1633);
					inputState.guessing--;
				}
				if ( synPredMatched1633 ) {
					kindTest();
				}
				else {
					boolean synPredMatched1635 = false;
					if (((_tokenSet_9.member(LA(1))))) {
						int _m1635 = mark();
						synPredMatched1635 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_attribute);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1635 = false;
						}
						rewind(_m1635);
						inputState.guessing--;
					}
					if ( synPredMatched1635 ) {
						kindTest();
					}
					else {
						boolean synPredMatched1637 = false;
						if (((_tokenSet_9.member(LA(1))))) {
							int _m1637 = mark();
							synPredMatched1637 = true;
							inputState.guessing++;
							try {
								{
								match(104);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched1637 = false;
							}
							rewind(_m1637);
							inputState.guessing--;
						}
						if ( synPredMatched1637 ) {
							kindTest();
						}
						else {
							boolean synPredMatched1639 = false;
							if (((_tokenSet_9.member(LA(1))))) {
								int _m1639 = mark();
								synPredMatched1639 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_comment);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched1639 = false;
								}
								rewind(_m1639);
								inputState.guessing--;
							}
							if ( synPredMatched1639 ) {
								kindTest();
							}
							else {
								boolean synPredMatched1641 = false;
								if (((_tokenSet_9.member(LA(1))))) {
									int _m1641 = mark();
									synPredMatched1641 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_text);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched1641 = false;
									}
									rewind(_m1641);
									inputState.guessing--;
								}
								if ( synPredMatched1641 ) {
									kindTest();
								}
								else {
									boolean synPredMatched1643 = false;
									if (((_tokenSet_9.member(LA(1))))) {
										int _m1643 = mark();
										synPredMatched1643 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_node);
											match(LPAREN);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched1643 = false;
										}
										rewind(_m1643);
										inputState.guessing--;
									}
									if ( synPredMatched1643 ) {
										kindTest();
									}
									else {
										boolean synPredMatched1645 = false;
										if (((_tokenSet_9.member(LA(1))))) {
											int _m1645 = mark();
											synPredMatched1645 = true;
											inputState.guessing++;
											try {
												{
												match(106);
												match(LPAREN);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched1645 = false;
											}
											rewind(_m1645);
											inputState.guessing--;
										}
										if ( synPredMatched1645 ) {
											kindTest();
										}
										else {
											boolean synPredMatched1647 = false;
											if (((_tokenSet_9.member(LA(1))))) {
												int _m1647 = mark();
												synPredMatched1647 = true;
												inputState.guessing++;
												try {
													{
													match(107);
													match(LPAREN);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched1647 = false;
												}
												rewind(_m1647);
												inputState.guessing--;
											}
											if ( synPredMatched1647 ) {
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
		
		
		match(105);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_element:
		{
			elementTest();
			break;
		}
		case 107:
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		
		
		match(107);
		match(LPAREN);
		tmpStr=qName();
		match(RPAREN);
	}
	
	public final void schemaAttributeTest() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(106);
		match(LPAREN);
		tmpStr=qName();
		match(RPAREN);
	}
	
	public final void piTest() throws RecognitionException, TokenStreamException {
		
		
		match(104);
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		case LITERAL_encoding:
		case LITERAL_declare:
		case 12:
		case LITERAL_default:
		case LITERAL_collation:
		case 15:
		case LITERAL_construction:
		case LITERAL_ordering:
		case LITERAL_order:
		case 19:
		case LITERAL_import:
		case LITERAL_schema:
		case LITERAL_element:
		case LITERAL_function:
		case LITERAL_variable:
		case LITERAL_option:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_ordered:
		case LITERAL_unordered:
		case LITERAL_empty:
		case LITERAL_greatest:
		case LITERAL_least:
		case 34:
		case LITERAL_inherit:
		case 37:
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
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case 106:
		case 107:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 117:
		case LITERAL_following:
		case 119:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 122:
		case LITERAL_preceding:
		case 124:
		case LITERAL_void:
		case LITERAL_item:
		case NCNAME:
		case LITERAL_lax:
		case LITERAL_strict:
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
		case 122:
		{
			match(122);
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
		case 12:
		{
			match(12);
			if ( inputState.guessing==0 ) {
				name = "boundary-space";
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
		case LITERAL_comment:
		{
			match(LITERAL_comment);
			if ( inputState.guessing==0 ) {
				name= "comment";
			}
			break;
		}
		case LITERAL_construction:
		{
			match(LITERAL_construction);
			if ( inputState.guessing==0 ) {
				name = "construction";
			}
			break;
		}
		case 19:
		{
			match(19);
			if ( inputState.guessing==0 ) {
				name = "copy-namespaces";
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
		case LITERAL_descendant:
		{
			match(LITERAL_descendant);
			if ( inputState.guessing==0 ) {
				name= "descendant";
			}
			break;
		}
		case 117:
		{
			match(117);
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
		case 105:
		{
			match(105);
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
		case LITERAL_encoding:
		{
			match(LITERAL_encoding);
			if ( inputState.guessing==0 ) {
				name = "encoding";
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
		case 119:
		{
			match(119);
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
		case LITERAL_inherit:
		{
			match(LITERAL_inherit);
			if ( inputState.guessing==0 ) {
				name = "inherit";
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
		case LITERAL_node:
		{
			match(LITERAL_node);
			if ( inputState.guessing==0 ) {
				name= "node";
			}
			break;
		}
		case 37:
		{
			match(37);
			if ( inputState.guessing==0 ) {
				name = "no-inherit";
			}
			break;
		}
		case 34:
		{
			match(34);
			if ( inputState.guessing==0 ) {
				name = "no-preserve";
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
		case LITERAL_option:
		{
			match(LITERAL_option);
			if ( inputState.guessing==0 ) {
				name = "option";
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
		case LITERAL_ordered:
		{
			match(LITERAL_ordered);
			if ( inputState.guessing==0 ) {
				name = "ordered";
			}
			break;
		}
		case LITERAL_ordering:
		{
			match(LITERAL_ordering);
			if ( inputState.guessing==0 ) {
				name = "ordering";
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
		case 124:
		{
			match(124);
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
		case 104:
		{
			match(104);
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
		case 106:
		{
			match(106);
			if ( inputState.guessing==0 ) {
				name="schema-attribute";
			}
			break;
		}
		case 107:
		{
			match(107);
			if ( inputState.guessing==0 ) {
				name = "schema-element";
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
		case LITERAL_typeswitch:
		{
			match(LITERAL_typeswitch);
			if ( inputState.guessing==0 ) {
				name = "typeswitch";
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
		case LITERAL_unordered:
		{
			match(LITERAL_unordered);
			if ( inputState.guessing==0 ) {
				name = "unordered";
			}
			break;
		}
		case LITERAL_validate:
		{
			match(LITERAL_validate);
			if ( inputState.guessing==0 ) {
				name = "validate";
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
		case LITERAL_void:
		{
			match(LITERAL_void);
			if ( inputState.guessing==0 ) {
				name = "void";
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
		"\"boundary-space\"",
		"\"default\"",
		"\"collation\"",
		"\"base-uri\"",
		"\"construction\"",
		"\"ordering\"",
		"\"order\"",
		"\"copy-namespaces\"",
		"\"import\"",
		"\"schema\"",
		"\"element\"",
		"\"function\"",
		"\"variable\"",
		"\"option\"",
		"SEMICOLON",
		"\"preserve\"",
		"\"strip\"",
		"\"ordered\"",
		"\"unordered\"",
		"\"empty\"",
		"\"greatest\"",
		"\"least\"",
		"\"no-preserve\"",
		"COMMA",
		"\"inherit\"",
		"\"no-inherit\"",
		"\"at\"",
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
		"PRAGMA",
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
		"\"void\"",
		"\"item\"",
		"NCNAME",
		"\"lax\"",
		"\"strict\"",
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
		long[] data = { -115483147764752L, -6917529027657887777L, 508163L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -120430950090000L, 2304752233531674575L, 507904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -115483147764752L, -6917529027657887777L, 507907L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { -611949377660623870L, 4611686018695823358L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 1024L, 28720L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { -115483147764752L, -6917529036248215585L, 507907L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { -120430950090000L, 2305596658462330831L, 507904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { -115483147764752L, -6918373512718479393L, 507907L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 0L, 70931969008992256L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 4194304L, 17523466567680L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { -120430950090000L, 2304752233532198863L, 507904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 4194304L, 19585050869760L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 4194304L, 230691283402768L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = new long[8];
		data[0]=-16L;
		data[1]=-17179869217L;
		data[2]=1099511627775L;
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 35184372088832L, 211106232533008L, 2056L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	
	}
