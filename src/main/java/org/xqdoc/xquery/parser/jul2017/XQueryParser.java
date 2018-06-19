// $ANTLR 2.7.7 (2006-11-01): "XQuery.g" -> "XQueryParser.java"$

	
/**  
 * Grammar definition for the November 2005 XQuery specification.
 */
	package org.xqdoc.xquery.parser.jul2017;

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
		if ((_tokenSet_0.member(LA(1)))) {
			module();
		}
		else if ((LA(1)==EOF)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
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
		_loop62:
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
						match(12);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched23 = false;
					}
					rewind(_m23);
inputState.guessing--;
				}
				if ( synPredMatched23 ) {
					boundarySpaceDecl();
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
											copyNamespacesDecl();
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
																		boolean synPredMatched55 = false;
																		if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																			int _m55 = mark();
																			synPredMatched55 = true;
																			inputState.guessing++;
																			try {
																				{
																				match(LITERAL_declare);
																				{
																				if ((LA(1)==LITERAL_function||LA(1)==MOD)) {
																					annotations();
																				}
																				else if ((LA(1)==LITERAL_function)) {
																				}
																				else {
																					throw new NoViableAltException(LT(1), getFilename());
																				}
																				
																				}
																				match(LITERAL_function);
																				}
																			}
																			catch (RecognitionException pe) {
																				synPredMatched55 = false;
																			}
																			rewind(_m55);
inputState.guessing--;
																		}
																		if ( synPredMatched55 ) {
																			functionDecl();
																		}
																		else {
																			boolean synPredMatched59 = false;
																			if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
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
																					{
																					if ((LA(1)==LITERAL_function||LA(1)==MOD)) {
																						annotations();
																					}
																					else if ((LA(1)==LITERAL_function)) {
																					}
																					else {
																						throw new NoViableAltException(LT(1), getFilename());
																					}
																					
																					}
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
																				boolean synPredMatched61 = false;
																				if (((LA(1)==LITERAL_declare))) {
																					int _m61 = mark();
																					synPredMatched61 = true;
																					inputState.guessing++;
																					try {
																						{
																						match(LITERAL_declare);
																						match(LITERAL_option);
																						}
																					}
																					catch (RecognitionException pe) {
																						synPredMatched61 = false;
																					}
																					rewind(_m61);
inputState.guessing--;
																				}
																				if ( synPredMatched61 ) {
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
																				break _loop62;
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
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case 119:
		case 120:
		case 121:
		case 122:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 134:
		case 135:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 138:
		case 139:
		case 160:
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
			_loop103:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(STRING_LITERAL);
				}
				else {
					break _loop103;
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
			_loop112:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(STRING_LITERAL);
				}
				else {
					break _loop112;
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
	
	public final void annotations() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop122:
		do {
			if ((LA(1)==MOD)) {
				annotation();
			}
			else {
				break _loop122;
			}
			
		} while (true);
		}
	}
	
	public final void functionDecl() throws RecognitionException, TokenStreamException {
		
		
			String name= null;
			String localName = null; 
			String prefix = null;
			
			context.resetAnnotations();
		
		
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
		{
		if ((LA(1)==LITERAL_function||LA(1)==MOD)) {
			annotations();
		}
		else if ((LA(1)==LITERAL_function)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
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
		boolean synPredMatched67 = false;
		if (((LA(1)==LITERAL_declare))) {
			int _m67 = mark();
			synPredMatched67 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_declare);
				match(12);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched67 = false;
			}
			rewind(_m67);
inputState.guessing--;
		}
		if ( synPredMatched67 ) {
			boundarySpaceDecl();
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
					match(LITERAL_default);
					match(LITERAL_collation);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched69 = false;
				}
				rewind(_m69);
inputState.guessing--;
			}
			if ( synPredMatched69 ) {
				defaultCollationDecl();
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
						match(15);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched71 = false;
					}
					rewind(_m71);
inputState.guessing--;
				}
				if ( synPredMatched71 ) {
					baseUriDecl();
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
							match(LITERAL_construction);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched73 = false;
						}
						rewind(_m73);
inputState.guessing--;
					}
					if ( synPredMatched73 ) {
						constructionDecl();
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
								match(LITERAL_ordering);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched75 = false;
							}
							rewind(_m75);
inputState.guessing--;
						}
						if ( synPredMatched75 ) {
							orderingModeDecl();
						}
						else {
							boolean synPredMatched77 = false;
							if (((LA(1)==LITERAL_declare))) {
								int _m77 = mark();
								synPredMatched77 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_declare);
									match(LITERAL_default);
									match(LITERAL_order);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched77 = false;
								}
								rewind(_m77);
inputState.guessing--;
							}
							if ( synPredMatched77 ) {
								emptyOrderingDecl();
							}
							else {
								boolean synPredMatched79 = false;
								if (((LA(1)==LITERAL_declare))) {
									int _m79 = mark();
									synPredMatched79 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_declare);
										match(19);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched79 = false;
									}
									rewind(_m79);
inputState.guessing--;
								}
								if ( synPredMatched79 ) {
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
		
		
		boolean synPredMatched506 = false;
		if (((_tokenSet_1.member(LA(1))))) {
			int _m506 = mark();
			synPredMatched506 = true;
			inputState.guessing++;
			try {
				{
				ncnameOrKeyword();
				match(COLON);
				ncnameOrKeyword();
				}
			}
			catch (RecognitionException pe) {
				synPredMatched506 = false;
			}
			rewind(_m506);
inputState.guessing--;
		}
		if ( synPredMatched506 ) {
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
		
		
		boolean synPredMatched158 = false;
		if (((LA(1)==LITERAL_for||LA(1)==LITERAL_let))) {
			int _m158 = mark();
			synPredMatched158 = true;
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
				synPredMatched158 = false;
			}
			rewind(_m158);
inputState.guessing--;
		}
		if ( synPredMatched158 ) {
			flworExpr();
		}
		else {
			boolean synPredMatched161 = false;
			if (((LA(1)==LITERAL_some||LA(1)==LITERAL_every))) {
				int _m161 = mark();
				synPredMatched161 = true;
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
					synPredMatched161 = false;
				}
				rewind(_m161);
inputState.guessing--;
			}
			if ( synPredMatched161 ) {
				quantifiedExpr();
			}
			else {
				boolean synPredMatched163 = false;
				if (((LA(1)==LITERAL_switch))) {
					int _m163 = mark();
					synPredMatched163 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_switch);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched163 = false;
					}
					rewind(_m163);
inputState.guessing--;
				}
				if ( synPredMatched163 ) {
					switchExpr();
				}
				else {
					boolean synPredMatched165 = false;
					if (((LA(1)==LITERAL_typeswitch))) {
						int _m165 = mark();
						synPredMatched165 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_typeswitch);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched165 = false;
						}
						rewind(_m165);
inputState.guessing--;
					}
					if ( synPredMatched165 ) {
						typeswitchExpr();
					}
					else {
						boolean synPredMatched168 = false;
						if (((LA(1)==LITERAL_update))) {
							int _m168 = mark();
							synPredMatched168 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_update);
								{
								switch ( LA(1)) {
								case LITERAL_replace:
								{
									match(LITERAL_replace);
									break;
								}
								case LITERAL_value:
								{
									match(LITERAL_value);
									break;
								}
								case LITERAL_insert:
								{
									match(LITERAL_insert);
									break;
								}
								case LITERAL_delete:
								{
									match(LITERAL_delete);
									break;
								}
								case LITERAL_rename:
								{
									match(LITERAL_rename);
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
								synPredMatched168 = false;
							}
							rewind(_m168);
inputState.guessing--;
						}
						if ( synPredMatched168 ) {
							existUpdateExpr();
						}
						else {
							boolean synPredMatched170 = false;
							if (((LA(1)==LITERAL_if))) {
								int _m170 = mark();
								synPredMatched170 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_if);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched170 = false;
								}
								rewind(_m170);
inputState.guessing--;
							}
							if ( synPredMatched170 ) {
								ifExpr();
							}
							else {
								boolean synPredMatched172 = false;
								if (((LA(1)==LITERAL_try))) {
									int _m172 = mark();
									synPredMatched172 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_try);
										match(LCURLY);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched172 = false;
									}
									rewind(_m172);
inputState.guessing--;
								}
								if ( synPredMatched172 ) {
									tryCatchExpr();
								}
								else if ((_tokenSet_2.member(LA(1)))) {
									orExpr();
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}}}
							}
							
	public final void annotation() throws RecognitionException, TokenStreamException {
		
		
			String name= null;
			String localName = null; 
			String prefix = null;
		
		
		match(MOD);
		name=qName();
		if ( inputState.guessing==0 ) {
			
					String[] tmp = name.split(":", 2);
					localName = name;
					context.setAnnotationName(name);
					if (tmp.length > 1) {
						prefix = tmp[0];
						localName = tmp[1];
					}		
				
		}
		{
		switch ( LA(1)) {
		case LPAREN:
		{
			match(LPAREN);
			annotList();
			match(RPAREN);
			break;
		}
		case LITERAL_function:
		case MOD:
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
	
	public final void annotList() throws RecognitionException, TokenStreamException {
		
		
		annotationParam();
		{
		_loop127:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				annotationParam();
			}
			else {
				break _loop127;
			}
			
		} while (true);
		}
	}
	
	public final void annotationParam() throws RecognitionException, TokenStreamException {
		
		Token  value = null;
		
		value = LT(1);
		match(STRING_LITERAL);
		if ( inputState.guessing==0 ) {
			
				   String myText = value.getText();
				   context.addAnnotationLiteral(myText);
				
		}
	}
	
	public final void paramList() throws RecognitionException, TokenStreamException {
		
		
		param();
		{
		_loop139:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				param();
			}
			else {
				break _loop139;
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
		
		
		boolean synPredMatched459 = false;
		if (((LA(1)==160))) {
			int _m459 = mark();
			synPredMatched459 = true;
			inputState.guessing++;
			try {
				{
				match(160);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched459 = false;
			}
			rewind(_m459);
inputState.guessing--;
		}
		if ( synPredMatched459 ) {
			match(160);
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
		_loop146:
		do {
			if ((LA(1)==ARROW)) {
				match(ARROW);
				exprSingle();
			}
			else {
				break _loop146;
			}
			
		} while (true);
		}
		{
		_loop148:
		do {
			if ((LA(1)==BANG)) {
				match(BANG);
				exprSingle();
			}
			else {
				break _loop148;
			}
			
		} while (true);
		}
		{
		_loop154:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
				{
				_loop151:
				do {
					if ((LA(1)==ARROW)) {
						match(ARROW);
						exprSingle();
					}
					else {
						break _loop151;
					}
					
				} while (true);
				}
				{
				_loop153:
				do {
					if ((LA(1)==BANG)) {
						match(BANG);
						exprSingle();
					}
					else {
						break _loop153;
					}
					
				} while (true);
				}
			}
			else {
				break _loop154;
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
		int _cnt186=0;
		_loop186:
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
				if ( _cnt186>=1 ) { break _loop186; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			}
			_cnt186++;
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
		_loop220:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				quantifiedInVarBinding();
			}
			else {
				break _loop220;
			}
			
		} while (true);
		}
		match(LITERAL_satisfies);
		exprSingle();
	}
	
	public final void switchExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_switch);
		match(LPAREN);
		expr();
		match(RPAREN);
		{
		int _cnt225=0;
		_loop225:
		do {
			if ((LA(1)==LITERAL_case)) {
				switchCaseClause();
			}
			else {
				if ( _cnt225>=1 ) { break _loop225; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt225++;
		} while (true);
		}
		match(LITERAL_default);
		match(LITERAL_return);
		exprSingle();
	}
	
	public final void typeswitchExpr() throws RecognitionException, TokenStreamException {
		
		
			String varName=null; 
		
		
		match(LITERAL_typeswitch);
		match(LPAREN);
		expr();
		match(RPAREN);
		{
		int _cnt232=0;
		_loop232:
		do {
			if ((LA(1)==LITERAL_case)) {
				caseClause();
			}
			else {
				if ( _cnt232>=1 ) { break _loop232; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt232++;
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
	
	public final void existUpdateExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_update);
		{
		switch ( LA(1)) {
		case LITERAL_replace:
		{
			existReplaceExpr();
			break;
		}
		case LITERAL_value:
		{
			existValueExpr();
			break;
		}
		case LITERAL_insert:
		{
			existInsertExpr();
			break;
		}
		case LITERAL_delete:
		{
			existDeleteExpr();
			break;
		}
		case LITERAL_rename:
		{
			existRenameExpr();
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void ifExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_if);
		match(LPAREN);
		expr();
		match(RPAREN);
		match(LITERAL_then);
		exprSingle();
		{
		_loop238:
		do {
			if ((LA(1)==ARROW)) {
				match(ARROW);
				exprSingle();
			}
			else {
				break _loop238;
			}
			
		} while (true);
		}
		{
		_loop240:
		do {
			if ((LA(1)==BANG)) {
				match(BANG);
				exprSingle();
			}
			else {
				break _loop240;
			}
			
		} while (true);
		}
		match(LITERAL_else);
		exprSingle();
		{
		_loop242:
		do {
			if ((LA(1)==ARROW)) {
				match(ARROW);
				exprSingle();
			}
			else {
				break _loop242;
			}
			
		} while (true);
		}
		{
		_loop244:
		do {
			if ((LA(1)==BANG)) {
				match(BANG);
				exprSingle();
			}
			else {
				break _loop244;
			}
			
		} while (true);
		}
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
		_loop247:
		do {
			if ((LA(1)==LITERAL_or)) {
				match(LITERAL_or);
				andExpr();
			}
			else {
				break _loop247;
			}
			
		} while (true);
		}
	}
	
	public final void existReplaceExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_replace);
		expr();
		match(LITERAL_with);
		exprSingle();
	}
	
	public final void existValueExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_value);
		expr();
		match(LITERAL_with);
		exprSingle();
	}
	
	public final void existInsertExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_insert);
		exprSingle();
		{
		switch ( LA(1)) {
		case LITERAL_into:
		{
			match(LITERAL_into);
			break;
		}
		case LITERAL_preceding:
		{
			match(LITERAL_preceding);
			break;
		}
		case LITERAL_following:
		{
			match(LITERAL_following);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		exprSingle();
	}
	
	public final void existDeleteExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_delete);
		exprSingle();
	}
	
	public final void existRenameExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_rename);
		exprSingle();
		match(LITERAL_as);
		exprSingle();
	}
	
	public final void forClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_for);
		inVarBinding();
		{
		_loop191:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				inVarBinding();
			}
			else {
				break _loop191;
			}
			
		} while (true);
		}
	}
	
	public final void letClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_let);
		letVarBinding();
		{
		_loop198:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				letVarBinding();
			}
			else {
				break _loop198;
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
		{
		_loop202:
		do {
			if ((LA(1)==ARROW)) {
				match(ARROW);
				exprSingle();
			}
			else {
				break _loop202;
			}
			
		} while (true);
		}
		{
		_loop204:
		do {
			if ((LA(1)==BANG)) {
				match(BANG);
				exprSingle();
			}
			else {
				break _loop204;
			}
			
		} while (true);
		}
	}
	
	public final void orderSpecList() throws RecognitionException, TokenStreamException {
		
		
		orderSpec();
		{
		_loop210:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				orderSpec();
			}
			else {
				break _loop210;
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
	
	public final void switchCaseClause() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt228=0;
		_loop228:
		do {
			if ((LA(1)==LITERAL_case)) {
				match(LITERAL_case);
				switchCaseOperand();
			}
			else {
				if ( _cnt228>=1 ) { break _loop228; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt228++;
		} while (true);
		}
		match(LITERAL_return);
		exprSingle();
	}
	
	public final void switchCaseOperand() throws RecognitionException, TokenStreamException {
		
		
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
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case 119:
		case 120:
		case 121:
		case 122:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 134:
		case 135:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 138:
		case 139:
		case 160:
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
		_loop250:
		do {
			if ((LA(1)==LITERAL_and)) {
				match(LITERAL_and);
				comparisonExpr();
			}
			else {
				break _loop250;
			}
			
		} while (true);
		}
	}
	
	public final void comparisonExpr() throws RecognitionException, TokenStreamException {
		
		
		stringConcatExpr();
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
		case COLON:
		case RPAREN:
		case LITERAL_as:
		case RCURLY:
		case ARROW:
		case BANG:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
			boolean synPredMatched254 = false;
			if (((LA(1)==LT))) {
				int _m254 = mark();
				synPredMatched254 = true;
				inputState.guessing++;
				try {
					{
					match(LT);
					match(LT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched254 = false;
				}
				rewind(_m254);
inputState.guessing--;
			}
			if ( synPredMatched254 ) {
				match(LT);
				match(LT);
				rangeExpr();
			}
			else {
				boolean synPredMatched256 = false;
				if (((LA(1)==GT))) {
					int _m256 = mark();
					synPredMatched256 = true;
					inputState.guessing++;
					try {
						{
						match(GT);
						match(GT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched256 = false;
					}
					rewind(_m256);
inputState.guessing--;
				}
				if ( synPredMatched256 ) {
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
		
	public final void stringConcatExpr() throws RecognitionException, TokenStreamException {
		
		
		rangeExpr();
		{
		_loop265:
		do {
			if ((LA(1)==CONCAT)) {
				match(CONCAT);
				rangeExpr();
			}
			else {
				break _loop265;
			}
			
		} while (true);
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
		case COLON:
		case RPAREN:
		case LITERAL_as:
		case RCURLY:
		case ARROW:
		case BANG:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case CONCAT:
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
		_loop271:
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
				break _loop271;
			}
			
		} while (true);
		}
	}
	
	public final void multiplicativeExpr() throws RecognitionException, TokenStreamException {
		
		
		unionExpr();
		{
		_loop275:
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
				break _loop275;
			}
			
		} while (true);
		}
	}
	
	public final void unionExpr() throws RecognitionException, TokenStreamException {
		
		
		intersectExceptExpr();
		{
		_loop279:
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
				break _loop279;
			}
			
		} while (true);
		}
	}
	
	public final void intersectExceptExpr() throws RecognitionException, TokenStreamException {
		
		
		instanceofExpr();
		{
		_loop283:
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
				break _loop283;
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
		case COLON:
		case RPAREN:
		case LITERAL_as:
		case RCURLY:
		case ARROW:
		case BANG:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case CONCAT:
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
		case COLON:
		case RPAREN:
		case LITERAL_as:
		case RCURLY:
		case ARROW:
		case BANG:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case CONCAT:
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
		case COLON:
		case RPAREN:
		case LITERAL_as:
		case RCURLY:
		case ARROW:
		case BANG:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case CONCAT:
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
		case COLON:
		case RPAREN:
		case LITERAL_as:
		case RCURLY:
		case ARROW:
		case BANG:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case CONCAT:
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
		case COLON:
		case RPAREN:
		case LITERAL_as:
		case RCURLY:
		case ARROW:
		case BANG:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case CONCAT:
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
		_loop294:
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
				break _loop294;
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
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case 119:
		case 120:
		case 121:
		case 122:
		case 123:
		case 124:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 134:
		case 135:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 138:
		case 139:
		case 142:
		case 143:
		case 144:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case 160:
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
			boolean synPredMatched304 = false;
			if (((LA(1)==SLASH))) {
				int _m304 = mark();
				synPredMatched304 = true;
				inputState.guessing++;
				try {
					{
					match(SLASH);
					relativePathExpr();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched304 = false;
				}
				rewind(_m304);
inputState.guessing--;
			}
			if ( synPredMatched304 ) {
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
		int _cnt300=0;
		_loop300:
		do {
			if ((LA(1)==PRAGMA)) {
				match(PRAGMA);
			}
			else {
				if ( _cnt300>=1 ) { break _loop300; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt300++;
		} while (true);
		}
		match(LCURLY);
		{
		if ((_tokenSet_6.member(LA(1)))) {
			expr();
		}
		else if ((LA(1)==RCURLY)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
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
		_loop308:
		do {
			if ((_tokenSet_7.member(LA(1)))) {
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
				case BANG:
				{
					match(BANG);
					break;
				}
				case ARROW:
				{
					match(ARROW);
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
				break _loop308;
			}
			
		} while (true);
		}
	}
	
	public final void stepExpr() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched312 = false;
		if (((_tokenSet_8.member(LA(1))))) {
			int _m312 = mark();
			synPredMatched312 = true;
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
				case 119:
				{
					match(119);
					break;
				}
				case 120:
				{
					match(120);
					break;
				}
				case 121:
				{
					match(121);
					break;
				}
				case 122:
				{
					match(122);
					break;
				}
				case 123:
				{
					match(123);
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
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched312 = false;
			}
			rewind(_m312);
inputState.guessing--;
		}
		if ( synPredMatched312 ) {
			axisStep();
		}
		else {
			boolean synPredMatched315 = false;
			if (((_tokenSet_9.member(LA(1))))) {
				int _m315 = mark();
				synPredMatched315 = true;
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
					case 119:
					{
						match(119);
						break;
					}
					case LITERAL_comment:
					{
						match(LITERAL_comment);
						break;
					}
					case 123:
					{
						match(123);
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
					match(LCURLY);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched315 = false;
				}
				rewind(_m315);
inputState.guessing--;
			}
			if ( synPredMatched315 ) {
				filterExpr();
			}
			else {
				boolean synPredMatched318 = false;
				if (((_tokenSet_9.member(LA(1))))) {
					int _m318 = mark();
					synPredMatched318 = true;
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
						qName();
						match(LCURLY);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched318 = false;
					}
					rewind(_m318);
inputState.guessing--;
				}
				if ( synPredMatched318 ) {
					filterExpr();
				}
				else {
					boolean synPredMatched321 = false;
					if (((_tokenSet_9.member(LA(1))))) {
						int _m321 = mark();
						synPredMatched321 = true;
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
							case LITERAL_update:
							case LITERAL_replace:
							case LITERAL_value:
							case LITERAL_insert:
							case LITERAL_delete:
							case LITERAL_rename:
							case LITERAL_if:
							case LITERAL_try:
							case LITERAL_catch:
							case LITERAL_with:
							case LITERAL_into:
							case LITERAL_preceding:
							case LITERAL_following:
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
							case 119:
							case 120:
							case 121:
							case 122:
							case LITERAL_document:
							case LITERAL_child:
							case LITERAL_self:
							case LITERAL_descendant:
							case 134:
							case 135:
							case LITERAL_parent:
							case LITERAL_ancestor:
							case 138:
							case 139:
							case 160:
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
							synPredMatched321 = false;
						}
						rewind(_m321);
inputState.guessing--;
					}
					if ( synPredMatched321 ) {
						filterExpr();
					}
					else if ((_tokenSet_8.member(LA(1)))) {
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
		
		
		boolean synPredMatched326 = false;
		if (((_tokenSet_10.member(LA(1))))) {
			int _m326 = mark();
			synPredMatched326 = true;
			inputState.guessing++;
			try {
				{
				forwardAxisSpecifier();
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched326 = false;
			}
			rewind(_m326);
inputState.guessing--;
		}
		if ( synPredMatched326 ) {
			forwardAxis();
			nodeTest();
		}
		else {
			boolean synPredMatched328 = false;
			if (((_tokenSet_11.member(LA(1))))) {
				int _m328 = mark();
				synPredMatched328 = true;
				inputState.guessing++;
				try {
					{
					reverseAxisSpecifier();
					match(COLON);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched328 = false;
				}
				rewind(_m328);
inputState.guessing--;
			}
			if ( synPredMatched328 ) {
				reverseAxis();
				nodeTest();
			}
			else if ((_tokenSet_8.member(LA(1)))) {
				abbrevStep();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		
	public final void predicateList() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop368:
		do {
			if ((LA(1)==LPPAREN)) {
				predicate();
			}
			else {
				break _loop368;
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
		case 134:
		{
			match(134);
			break;
		}
		case LITERAL_following:
		{
			match(LITERAL_following);
			break;
		}
		case 135:
		{
			match(135);
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
		
		
		boolean synPredMatched337 = false;
		if (((_tokenSet_12.member(LA(1))))) {
			int _m337 = mark();
			synPredMatched337 = true;
			inputState.guessing++;
			try {
				{
				match(120);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched337 = false;
			}
			rewind(_m337);
inputState.guessing--;
		}
		if ( synPredMatched337 ) {
			kindTest();
		}
		else {
			boolean synPredMatched339 = false;
			if (((_tokenSet_12.member(LA(1))))) {
				int _m339 = mark();
				synPredMatched339 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_element);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched339 = false;
				}
				rewind(_m339);
inputState.guessing--;
			}
			if ( synPredMatched339 ) {
				kindTest();
			}
			else {
				boolean synPredMatched341 = false;
				if (((_tokenSet_12.member(LA(1))))) {
					int _m341 = mark();
					synPredMatched341 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_attribute);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched341 = false;
					}
					rewind(_m341);
inputState.guessing--;
				}
				if ( synPredMatched341 ) {
					kindTest();
				}
				else {
					boolean synPredMatched343 = false;
					if (((_tokenSet_12.member(LA(1))))) {
						int _m343 = mark();
						synPredMatched343 = true;
						inputState.guessing++;
						try {
							{
							match(119);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched343 = false;
						}
						rewind(_m343);
inputState.guessing--;
					}
					if ( synPredMatched343 ) {
						kindTest();
					}
					else {
						boolean synPredMatched345 = false;
						if (((_tokenSet_12.member(LA(1))))) {
							int _m345 = mark();
							synPredMatched345 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_comment);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched345 = false;
							}
							rewind(_m345);
inputState.guessing--;
						}
						if ( synPredMatched345 ) {
							kindTest();
						}
						else {
							boolean synPredMatched347 = false;
							if (((_tokenSet_12.member(LA(1))))) {
								int _m347 = mark();
								synPredMatched347 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_text);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched347 = false;
								}
								rewind(_m347);
inputState.guessing--;
							}
							if ( synPredMatched347 ) {
								kindTest();
							}
							else {
								boolean synPredMatched349 = false;
								if (((_tokenSet_12.member(LA(1))))) {
									int _m349 = mark();
									synPredMatched349 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_node);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched349 = false;
									}
									rewind(_m349);
inputState.guessing--;
								}
								if ( synPredMatched349 ) {
									kindTest();
								}
								else {
									boolean synPredMatched351 = false;
									if (((_tokenSet_12.member(LA(1))))) {
										int _m351 = mark();
										synPredMatched351 = true;
										inputState.guessing++;
										try {
											{
											match(121);
											match(LPAREN);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched351 = false;
										}
										rewind(_m351);
inputState.guessing--;
									}
									if ( synPredMatched351 ) {
										kindTest();
									}
									else {
										boolean synPredMatched353 = false;
										if (((_tokenSet_12.member(LA(1))))) {
											int _m353 = mark();
											synPredMatched353 = true;
											inputState.guessing++;
											try {
												{
												match(122);
												match(LPAREN);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched353 = false;
											}
											rewind(_m353);
inputState.guessing--;
										}
										if ( synPredMatched353 ) {
											kindTest();
										}
										else {
											boolean synPredMatched355 = false;
											if (((_tokenSet_12.member(LA(1))))) {
												int _m355 = mark();
												synPredMatched355 = true;
												inputState.guessing++;
												try {
													{
													match(123);
													match(LPAREN);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched355 = false;
												}
												rewind(_m355);
inputState.guessing--;
											}
											if ( synPredMatched355 ) {
												kindTest();
											}
											else {
												boolean synPredMatched357 = false;
												if (((_tokenSet_12.member(LA(1))))) {
													int _m357 = mark();
													synPredMatched357 = true;
													inputState.guessing++;
													try {
														{
														match(124);
														match(LPAREN);
														}
													}
													catch (RecognitionException pe) {
														synPredMatched357 = false;
													}
													rewind(_m357);
inputState.guessing--;
												}
												if ( synPredMatched357 ) {
													kindTest();
												}
												else if ((_tokenSet_13.member(LA(1)))) {
													nameTest();
												}
												else {
													throw new NoViableAltException(LT(1), getFilename());
												}
												}}}}}}}}}}
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
		case 138:
		{
			match(138);
			break;
		}
		case LITERAL_preceding:
		{
			match(LITERAL_preceding);
			break;
		}
		case 139:
		{
			match(139);
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
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case 119:
		case 120:
		case 121:
		case 122:
		case LITERAL_document:
		case AT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 134:
		case 135:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 138:
		case 139:
		case 160:
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
			case LITERAL_update:
			case LITERAL_replace:
			case LITERAL_value:
			case LITERAL_insert:
			case LITERAL_delete:
			case LITERAL_rename:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
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
			case 119:
			case 120:
			case 121:
			case 122:
			case LITERAL_document:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 134:
			case 135:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 138:
			case 139:
			case 160:
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
		case 120:
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
		case 122:
		{
			schemaElementTest();
			break;
		}
		case 121:
		{
			schemaAttributeTest();
			break;
		}
		case 119:
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
		
		
		boolean synPredMatched361 = false;
		if (((_tokenSet_13.member(LA(1))))) {
			int _m361 = mark();
			synPredMatched361 = true;
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
				case LITERAL_update:
				case LITERAL_replace:
				case LITERAL_value:
				case LITERAL_insert:
				case LITERAL_delete:
				case LITERAL_rename:
				case LITERAL_if:
				case LITERAL_try:
				case LITERAL_catch:
				case LITERAL_with:
				case LITERAL_into:
				case LITERAL_preceding:
				case LITERAL_following:
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
				case 119:
				case 120:
				case 121:
				case 122:
				case LITERAL_document:
				case LITERAL_child:
				case LITERAL_self:
				case LITERAL_descendant:
				case 134:
				case 135:
				case LITERAL_parent:
				case LITERAL_ancestor:
				case 138:
				case 139:
				case 160:
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
				synPredMatched361 = false;
			}
			rewind(_m361);
inputState.guessing--;
		}
		if ( synPredMatched361 ) {
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
		
		boolean synPredMatched364 = false;
		if (((LA(1)==STAR))) {
			int _m364 = mark();
			synPredMatched364 = true;
			inputState.guessing++;
			try {
				{
				match(STAR);
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched364 = false;
			}
			rewind(_m364);
inputState.guessing--;
		}
		if ( synPredMatched364 ) {
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
				boolean synPredMatched373 = false;
				if (((_tokenSet_14.member(LA(1))))) {
					int _m373 = mark();
					synPredMatched373 = true;
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
						case 119:
						{
							match(119);
							break;
						}
						case LITERAL_comment:
						{
							match(LITERAL_comment);
							break;
						}
						case 123:
						{
							match(123);
							break;
						}
						case 124:
						{
							match(124);
							break;
						}
						case 142:
						{
							match(142);
							break;
						}
						case 143:
						{
							match(143);
							break;
						}
						case 144:
						{
							match(144);
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
						synPredMatched373 = false;
					}
					rewind(_m373);
inputState.guessing--;
				}
				if ( synPredMatched373 ) {
					computedConstructor();
				}
				else {
					boolean synPredMatched376 = false;
					if (((_tokenSet_14.member(LA(1))))) {
						int _m376 = mark();
						synPredMatched376 = true;
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
							case 119:
							{
								match(119);
								break;
							}
							case 123:
							{
								match(123);
								break;
							}
							case 124:
							{
								match(124);
								break;
							}
							case 142:
							{
								match(142);
								break;
							}
							case 143:
							{
								match(143);
								break;
							}
							case 144:
							{
								match(144);
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
							synPredMatched376 = false;
						}
						rewind(_m376);
inputState.guessing--;
					}
					if ( synPredMatched376 ) {
						computedConstructor();
					}
					else if ((_tokenSet_15.member(LA(1)))) {
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
		case 119:
		{
			compPIConstructor();
			break;
		}
		case 123:
		{
			compArrayNodeConstructor();
			break;
		}
		case 124:
		{
			compObjectNodeConstructor();
			break;
		}
		case 142:
		{
			compNumberNodeConstructor();
			break;
		}
		case 143:
		{
			compBooleanNodeConstructor();
			break;
		}
		case 144:
		{
			compNullNodeConstructor();
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
		case 119:
		case 123:
		case 124:
		case LITERAL_document:
		case 142:
		case 143:
		case 144:
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
		if ((_tokenSet_6.member(LA(1)))) {
			functionParameters();
		}
		else if ((LA(1)==RPAREN)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
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
		if ((_tokenSet_6.member(LA(1)))) {
			expr();
		}
		else if ((LA(1)==RPAREN)) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
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
		_loop388:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop388;
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
		
		
		boolean synPredMatched394 = false;
		if (((LA(1)==LT))) {
			int _m394 = mark();
			synPredMatched394 = true;
			inputState.guessing++;
			try {
				{
				match(LT);
				qName();
				{
				match(_tokenSet_16);
				}
				}
			}
			catch (RecognitionException pe) {
				synPredMatched394 = false;
			}
			rewind(_m394);
inputState.guessing--;
		}
		if ( synPredMatched394 ) {
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
		_loop405:
		do {
			if ((_tokenSet_17.member(LA(1)))) {
				dirElemContent();
			}
			else {
				break _loop405;
			}
			
		} while (true);
		}
	}
	
	public final void attributeList() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt409=0;
		_loop409:
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				attributeDef();
			}
			else {
				if ( _cnt409>=1 ) { break _loop409; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt409++;
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
			_loop413:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==QUOT_ATTRIBUTE_CONTENT)) {
					quotAttrValueContent();
				}
				else {
					break _loop413;
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
			_loop415:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==APOS_ATTRIBUTE_CONTENT)) {
					aposAttrValueContent();
				}
				else {
					break _loop415;
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
		
		
		boolean synPredMatched420 = false;
		if (((LA(1)==LCURLY))) {
			int _m420 = mark();
			synPredMatched420 = true;
			inputState.guessing++;
			try {
				{
				match(LCURLY);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched420 = false;
			}
			rewind(_m420);
inputState.guessing--;
		}
		if ( synPredMatched420 ) {
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
		
		
		boolean synPredMatched438 = false;
		if (((LA(1)==LITERAL_element))) {
			int _m438 = mark();
			synPredMatched438 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_element);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched438 = false;
			}
			rewind(_m438);
inputState.guessing--;
		}
		if ( synPredMatched438 ) {
			match(LITERAL_element);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			{
			if ((_tokenSet_6.member(LA(1)))) {
				contentExpr();
			}
			else if ((LA(1)==RCURLY)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(RCURLY);
		}
		else if ((LA(1)==LITERAL_element)) {
			match(LITERAL_element);
			name=qName();
			match(LCURLY);
			{
			if ((_tokenSet_6.member(LA(1)))) {
				contentExpr();
			}
			else if ((LA(1)==RCURLY)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
		
		
		boolean synPredMatched444 = false;
		if (((LA(1)==LITERAL_attribute))) {
			int _m444 = mark();
			synPredMatched444 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_attribute);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched444 = false;
			}
			rewind(_m444);
inputState.guessing--;
		}
		if ( synPredMatched444 ) {
			match(LITERAL_attribute);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			{
			if ((_tokenSet_6.member(LA(1)))) {
				expr();
			}
			else if ((LA(1)==RCURLY)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(RCURLY);
		}
		else if ((LA(1)==LITERAL_attribute)) {
			match(LITERAL_attribute);
			name=qName();
			match(LCURLY);
			{
			if ((_tokenSet_6.member(LA(1)))) {
				expr();
			}
			else if ((LA(1)==RCURLY)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
		
		
		boolean synPredMatched451 = false;
		if (((LA(1)==119))) {
			int _m451 = mark();
			synPredMatched451 = true;
			inputState.guessing++;
			try {
				{
				match(119);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched451 = false;
			}
			rewind(_m451);
inputState.guessing--;
		}
		if ( synPredMatched451 ) {
			match(119);
			match(LCURLY);
			expr();
			match(RCURLY);
			match(LCURLY);
			{
			if ((_tokenSet_6.member(LA(1)))) {
				expr();
			}
			else if ((LA(1)==RCURLY)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(RCURLY);
		}
		else if ((LA(1)==119)) {
			match(119);
			name=ncnameOrKeyword();
			match(LCURLY);
			{
			if ((_tokenSet_6.member(LA(1)))) {
				expr();
			}
			else if ((LA(1)==RCURLY)) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(RCURLY);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void compArrayNodeConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(123);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void compObjectNodeConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(124);
		match(LCURLY);
		compObjectDecl();
		{
		_loop429:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				compObjectDecl();
			}
			else {
				break _loop429;
			}
			
		} while (true);
		}
		match(RCURLY);
	}
	
	public final void compNumberNodeConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(142);
		match(LCURLY);
		exprSingle();
		match(RCURLY);
	}
	
	public final void compBooleanNodeConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(143);
		match(LCURLY);
		exprSingle();
		match(RCURLY);
	}
	
	public final void compNullNodeConstructor() throws RecognitionException, TokenStreamException {
		
		
		match(144);
		match(LCURLY);
		match(RCURLY);
	}
	
	public final void compObjectDecl() throws RecognitionException, TokenStreamException {
		
		
		exprSingle();
		match(COLON);
		exprSingle();
	}
	
	public final void contentExpr() throws RecognitionException, TokenStreamException {
		
		
		expr();
	}
	
	public final void atomicType() throws RecognitionException, TokenStreamException {
		
		
			String name = null;
		
		
		name=qName();
	}
	
	public final void itemType() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched464 = false;
		if (((LA(1)==LITERAL_item))) {
			int _m464 = mark();
			synPredMatched464 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_item);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched464 = false;
			}
			rewind(_m464);
inputState.guessing--;
		}
		if ( synPredMatched464 ) {
			match(LITERAL_item);
			match(LPAREN);
			match(RPAREN);
		}
		else {
			boolean synPredMatched466 = false;
			if (((_tokenSet_12.member(LA(1))))) {
				int _m466 = mark();
				synPredMatched466 = true;
				inputState.guessing++;
				try {
					{
					match(120);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched466 = false;
				}
				rewind(_m466);
inputState.guessing--;
			}
			if ( synPredMatched466 ) {
				kindTest();
			}
			else {
				boolean synPredMatched468 = false;
				if (((_tokenSet_12.member(LA(1))))) {
					int _m468 = mark();
					synPredMatched468 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_element);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched468 = false;
					}
					rewind(_m468);
inputState.guessing--;
				}
				if ( synPredMatched468 ) {
					kindTest();
				}
				else {
					boolean synPredMatched470 = false;
					if (((_tokenSet_12.member(LA(1))))) {
						int _m470 = mark();
						synPredMatched470 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_attribute);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched470 = false;
						}
						rewind(_m470);
inputState.guessing--;
					}
					if ( synPredMatched470 ) {
						kindTest();
					}
					else {
						boolean synPredMatched472 = false;
						if (((_tokenSet_12.member(LA(1))))) {
							int _m472 = mark();
							synPredMatched472 = true;
							inputState.guessing++;
							try {
								{
								match(119);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched472 = false;
							}
							rewind(_m472);
inputState.guessing--;
						}
						if ( synPredMatched472 ) {
							kindTest();
						}
						else {
							boolean synPredMatched474 = false;
							if (((_tokenSet_12.member(LA(1))))) {
								int _m474 = mark();
								synPredMatched474 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_comment);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched474 = false;
								}
								rewind(_m474);
inputState.guessing--;
							}
							if ( synPredMatched474 ) {
								kindTest();
							}
							else {
								boolean synPredMatched476 = false;
								if (((_tokenSet_12.member(LA(1))))) {
									int _m476 = mark();
									synPredMatched476 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_text);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched476 = false;
									}
									rewind(_m476);
inputState.guessing--;
								}
								if ( synPredMatched476 ) {
									kindTest();
								}
								else {
									boolean synPredMatched478 = false;
									if (((_tokenSet_12.member(LA(1))))) {
										int _m478 = mark();
										synPredMatched478 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_node);
											match(LPAREN);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched478 = false;
										}
										rewind(_m478);
inputState.guessing--;
									}
									if ( synPredMatched478 ) {
										kindTest();
									}
									else {
										boolean synPredMatched480 = false;
										if (((_tokenSet_12.member(LA(1))))) {
											int _m480 = mark();
											synPredMatched480 = true;
											inputState.guessing++;
											try {
												{
												match(121);
												match(LPAREN);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched480 = false;
											}
											rewind(_m480);
inputState.guessing--;
										}
										if ( synPredMatched480 ) {
											kindTest();
										}
										else {
											boolean synPredMatched482 = false;
											if (((_tokenSet_12.member(LA(1))))) {
												int _m482 = mark();
												synPredMatched482 = true;
												inputState.guessing++;
												try {
													{
													match(122);
													match(LPAREN);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched482 = false;
												}
												rewind(_m482);
inputState.guessing--;
											}
											if ( synPredMatched482 ) {
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
		
		
		match(120);
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_element:
		{
			elementTest();
			break;
		}
		case 122:
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
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case 119:
		case 120:
		case 121:
		case 122:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 134:
		case 135:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 138:
		case 139:
		case 160:
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
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case 119:
		case 120:
		case 121:
		case 122:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 134:
		case 135:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 138:
		case 139:
		case 160:
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
		
		
		match(122);
		match(LPAREN);
		tmpStr=qName();
		match(RPAREN);
	}
	
	public final void schemaAttributeTest() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(121);
		match(LPAREN);
		tmpStr=qName();
		match(RPAREN);
	}
	
	public final void piTest() throws RecognitionException, TokenStreamException {
		
		
		match(119);
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
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case 119:
		case 120:
		case 121:
		case 122:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 134:
		case 135:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 138:
		case 139:
		case 160:
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
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
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
		case 119:
		case 120:
		case 121:
		case 122:
		case LITERAL_document:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 134:
		case 135:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 138:
		case 139:
		case 160:
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
		case 138:
		{
			match(138);
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
		case LITERAL_delete:
		{
			match(LITERAL_delete);
			if ( inputState.guessing==0 ) {
				name= "delete";
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
		case 134:
		{
			match(134);
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
		case 120:
		{
			match(120);
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
		case 160:
		{
			match(160);
			if ( inputState.guessing==0 ) {
				name = "empty-sequence";
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
		case 135:
		{
			match(135);
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
		case LITERAL_insert:
		{
			match(LITERAL_insert);
			if ( inputState.guessing==0 ) {
				name = "insert";
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
		case LITERAL_into:
		{
			match(LITERAL_into);
			if ( inputState.guessing==0 ) {
				name = "into";
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
		case 139:
		{
			match(139);
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
		case 119:
		{
			match(119);
			if ( inputState.guessing==0 ) {
				name = "processing-instruction";
			}
			break;
		}
		case LITERAL_rename:
		{
			match(LITERAL_rename);
			if ( inputState.guessing==0 ) {
				name = "rename";
			}
			break;
		}
		case LITERAL_replace:
		{
			match(LITERAL_replace);
			if ( inputState.guessing==0 ) {
				name = "replace";
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
		case 121:
		{
			match(121);
			if ( inputState.guessing==0 ) {
				name="schema-attribute";
			}
			break;
		}
		case 122:
		{
			match(122);
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
		case LITERAL_update:
		{
			match(LITERAL_update);
			if ( inputState.guessing==0 ) {
				name = "update";
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
		case LITERAL_value:
		{
			match(LITERAL_value);
			if ( inputState.guessing==0 ) {
				name = "value";
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
		case LITERAL_with:
		{
			match(LITERAL_with);
			if ( inputState.guessing==0 ) {
				name = "with";
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
		"MOD",
		"'('",
		"')'",
		"\"as\"",
		"LCURLY",
		"RCURLY",
		"ARROW",
		"BANG",
		"\"for\"",
		"\"let\"",
		"\"some\"",
		"\"every\"",
		"\"switch\"",
		"\"typeswitch\"",
		"\"update\"",
		"\"replace\"",
		"\"value\"",
		"\"insert\"",
		"\"delete\"",
		"\"rename\"",
		"\"if\"",
		"\"try\"",
		"\"catch\"",
		"\"with\"",
		"\"into\"",
		"\"preceding\"",
		"\"following\"",
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
		"CONCAT",
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
		"\"array-node\"",
		"\"object-node\"",
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
		"\"following-sibling\"",
		"\"parent\"",
		"\"ancestor\"",
		"\"ancestor-or-self\"",
		"\"preceding-sibling\"",
		"LPPAREN",
		"RPPAREN",
		"\"number-node\"",
		"\"boolean-node\"",
		"\"null-node\"",
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
		"\"empty-sequence\"",
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
		long[] data = { -1078655333696528L, -551299842049L, 133212131327L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -19102399692014864L, 2880332855315267583L, 133143990264L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -19093053843178512L, -551299842049L, 133145022463L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 4489617428734978L, 8796093005310L, 8192L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 1024L, 470548480L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { -19093053843178512L, -282039161454593L, 133145022463L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { -1078655333696528L, -551299842049L, 133145022463L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 844424930131968L, 1688849860263936L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { -19102399692014864L, 2880332872495136767L, 133143990270L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { -19093053843178512L, -1970906201587713L, 133145022457L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 0L, 9007199254741008L, 248L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 0L, 8L, 3840L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 4194304L, 574208952489738240L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { -19102399692014864L, 2880332872495136767L, 133143990264L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 4194304L, 4100527460720836608L, 114688L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { 4194304L, -5122844576133677056L, 114689L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = new long[8];
		data[0]=-16L;
		data[1]=-562949953945601L;
		data[2]=288230376151711743L;
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { 70368744177664L, -9223372036854513664L, 538968065L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	
	}
