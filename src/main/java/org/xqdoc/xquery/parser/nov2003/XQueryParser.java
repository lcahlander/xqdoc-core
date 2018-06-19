// $ANTLR : "XQuery.g" -> "XQueryParser.java"$

	
/**  
 * Grammar definition for the November 2003 XQuery specification.
 */
	package org.xqdoc.xquery.parser.nov2003;

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
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case STRING_LITERAL:
		case LITERAL_at:
		case LITERAL_option:
		case DOLLAR:
		case LITERAL_external:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LPAREN:
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_validate:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case LITERAL_global:
		case LITERAL_context:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case XQDOC_COMMENT:
		case XML_CDATA:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
	
	public final void prolog() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop53:
		do {
			if ((LA(1)==LITERAL_declare||LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT)) {
				{
				boolean synPredMatched21 = false;
				if (((LA(1)==LITERAL_declare))) {
					int _m21 = mark();
					synPredMatched21 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_declare);
						match(LITERAL_xmlspace);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched21 = false;
					}
					rewind(_m21);
					inputState.guessing--;
				}
				if ( synPredMatched21 ) {
					xmlSpaceDecl();
				}
				else {
					boolean synPredMatched23 = false;
					if (((LA(1)==LITERAL_declare))) {
						int _m23 = mark();
						synPredMatched23 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_declare);
							match(LITERAL_namespace);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched23 = false;
						}
						rewind(_m23);
						inputState.guessing--;
					}
					if ( synPredMatched23 ) {
						namespaceDecl();
					}
					else {
						boolean synPredMatched25 = false;
						if (((LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT))) {
							int _m25 = mark();
							synPredMatched25 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_import);
								match(LITERAL_module);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched25 = false;
							}
							rewind(_m25);
							inputState.guessing--;
						}
						if ( synPredMatched25 ) {
							moduleImport();
						}
						else {
							boolean synPredMatched27 = false;
							if (((LA(1)==LITERAL_import||LA(1)==XQDOC_COMMENT))) {
								int _m27 = mark();
								synPredMatched27 = true;
								inputState.guessing++;
								try {
									{
									xqdocComment();
									match(LITERAL_import);
									match(LITERAL_module);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched27 = false;
								}
								rewind(_m27);
								inputState.guessing--;
							}
							if ( synPredMatched27 ) {
								moduleImport();
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
										match(LITERAL_default);
										match(LITERAL_collation);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched29 = false;
									}
									rewind(_m29);
									inputState.guessing--;
								}
								if ( synPredMatched29 ) {
									defaultCollationDecl();
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
											match(LITERAL_default);
											match(LITERAL_element);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched31 = false;
										}
										rewind(_m31);
										inputState.guessing--;
									}
									if ( synPredMatched31 ) {
										setter();
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
												match(LITERAL_function);
												}
											}
											catch (RecognitionException pe) {
												synPredMatched33 = false;
											}
											rewind(_m33);
											inputState.guessing--;
										}
										if ( synPredMatched33 ) {
											setter();
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
													match(16);
													}
												}
												catch (RecognitionException pe) {
													synPredMatched35 = false;
												}
												rewind(_m35);
												inputState.guessing--;
											}
											if ( synPredMatched35 ) {
												baseUriDecl();
											}
											else {
												boolean synPredMatched37 = false;
												if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
													int _m37 = mark();
													synPredMatched37 = true;
													inputState.guessing++;
													try {
														{
														xqdocComment();
														match(LITERAL_declare);
														match(LITERAL_variable);
														}
													}
													catch (RecognitionException pe) {
														synPredMatched37 = false;
													}
													rewind(_m37);
													inputState.guessing--;
												}
												if ( synPredMatched37 ) {
													varFunctionDecl();
												}
												else {
													boolean synPredMatched39 = false;
													if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
														int _m39 = mark();
														synPredMatched39 = true;
														inputState.guessing++;
														try {
															{
															match(LITERAL_declare);
															match(LITERAL_variable);
															}
														}
														catch (RecognitionException pe) {
															synPredMatched39 = false;
														}
														rewind(_m39);
														inputState.guessing--;
													}
													if ( synPredMatched39 ) {
														varFunctionDecl();
													}
													else {
														boolean synPredMatched41 = false;
														if (((LA(1)==LITERAL_declare))) {
															int _m41 = mark();
															synPredMatched41 = true;
															inputState.guessing++;
															try {
																{
																match(LITERAL_declare);
																match(LITERAL_validation);
																}
															}
															catch (RecognitionException pe) {
																synPredMatched41 = false;
															}
															rewind(_m41);
															inputState.guessing--;
														}
														if ( synPredMatched41 ) {
															match(LITERAL_declare);
															match(LITERAL_validation);
															schemaMode();
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
															switch ( LA(1)) {
															case LITERAL_at:
															{
																match(LITERAL_at);
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
															}
														}
														else {
															boolean synPredMatched48 = false;
															if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																int _m48 = mark();
																synPredMatched48 = true;
																inputState.guessing++;
																try {
																	{
																	xqdocComment();
																	match(LITERAL_declare);
																	match(LITERAL_function);
																	}
																}
																catch (RecognitionException pe) {
																	synPredMatched48 = false;
																}
																rewind(_m48);
																inputState.guessing--;
															}
															if ( synPredMatched48 ) {
																varFunctionDecl();
															}
															else {
																boolean synPredMatched50 = false;
																if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
																	int _m50 = mark();
																	synPredMatched50 = true;
																	inputState.guessing++;
																	try {
																		{
																		match(LITERAL_declare);
																		match(LITERAL_function);
																		}
																	}
																	catch (RecognitionException pe) {
																		synPredMatched50 = false;
																	}
																	rewind(_m50);
																	inputState.guessing--;
																}
																if ( synPredMatched50 ) {
																	varFunctionDecl();
																}
																else {
																	boolean synPredMatched52 = false;
																	if (((LA(1)==LITERAL_declare))) {
																		int _m52 = mark();
																		synPredMatched52 = true;
																		inputState.guessing++;
																		try {
																			{
																			match(LITERAL_declare);
																			match(LITERAL_option);
																			}
																		}
																		catch (RecognitionException pe) {
																			synPredMatched52 = false;
																		}
																		rewind(_m52);
																		inputState.guessing--;
																	}
																	if ( synPredMatched52 ) {
																		existOptionDecl();
																	}
																	else {
																		throw new NoViableAltException(LT(1), getFilename());
																	}
																	}}}}}}}}}}}}}
																	}
																	separator();
																}
																else {
																	break _loop53;
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
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case LITERAL_at:
		case LITERAL_option:
		case LITERAL_external:
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
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case LITERAL_global:
		case LITERAL_context:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
	
	public final void separator() throws RecognitionException, TokenStreamException {
		
		
		match(SEMICOLON);
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
	
	public final void defaultCollationDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(LITERAL_default);
		match(LITERAL_collation);
		match(STRING_LITERAL);
	}
	
	public final void setter() throws RecognitionException, TokenStreamException {
		
		
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
	
	public final void baseUriDecl() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_declare);
		match(16);
		match(STRING_LITERAL);
	}
	
	public final void varFunctionDecl() throws RecognitionException, TokenStreamException {
		
		
		{
		boolean synPredMatched65 = false;
		if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
			int _m65 = mark();
			synPredMatched65 = true;
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
				synPredMatched65 = false;
			}
			rewind(_m65);
			inputState.guessing--;
		}
		if ( synPredMatched65 ) {
			functionDecl();
		}
		else {
			boolean synPredMatched68 = false;
			if (((LA(1)==LITERAL_declare||LA(1)==XQDOC_COMMENT))) {
				int _m68 = mark();
				synPredMatched68 = true;
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
					synPredMatched68 = false;
				}
				rewind(_m68);
				inputState.guessing--;
			}
			if ( synPredMatched68 ) {
				varDecl();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		
	public final void schemaMode() throws RecognitionException, TokenStreamException {
		
		
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
	
	public final void existOptionDecl() throws RecognitionException, TokenStreamException {
		
		
			String name=null;
		
		
		match(LITERAL_declare);
		match(LITERAL_option);
		name=qName();
		match(STRING_LITERAL);
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
		
		
		boolean synPredMatched421 = false;
		if (((_tokenSet_1.member(LA(1))))) {
			int _m421 = mark();
			synPredMatched421 = true;
			inputState.guessing++;
			try {
				{
				ncnameOrKeyword();
				match(COLON);
				ncnameOrKeyword();
				}
			}
			catch (RecognitionException pe) {
				synPredMatched421 = false;
			}
			rewind(_m421);
			inputState.guessing--;
		}
		if ( synPredMatched421 ) {
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
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		exprSingle();
		{
		_loop82:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop82;
			}
			
		} while (true);
		}
	}
	
	public final void exprSingle() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched86 = false;
		if (((LA(1)==LITERAL_for||LA(1)==LITERAL_let))) {
			int _m86 = mark();
			synPredMatched86 = true;
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
				synPredMatched86 = false;
			}
			rewind(_m86);
			inputState.guessing--;
		}
		if ( synPredMatched86 ) {
			flworExpr();
		}
		else {
			boolean synPredMatched89 = false;
			if (((LA(1)==LITERAL_some||LA(1)==LITERAL_every))) {
				int _m89 = mark();
				synPredMatched89 = true;
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
					synPredMatched89 = false;
				}
				rewind(_m89);
				inputState.guessing--;
			}
			if ( synPredMatched89 ) {
				quantifiedExpr();
			}
			else {
				boolean synPredMatched91 = false;
				if (((LA(1)==LITERAL_typeswitch))) {
					int _m91 = mark();
					synPredMatched91 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_typeswitch);
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
					typeswitchExpr();
				}
				else {
					boolean synPredMatched94 = false;
					if (((LA(1)==LITERAL_update))) {
						int _m94 = mark();
						synPredMatched94 = true;
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
							synPredMatched94 = false;
						}
						rewind(_m94);
						inputState.guessing--;
					}
					if ( synPredMatched94 ) {
						existUpdateExpr();
					}
					else {
						boolean synPredMatched96 = false;
						if (((LA(1)==LITERAL_if))) {
							int _m96 = mark();
							synPredMatched96 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_if);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched96 = false;
							}
							rewind(_m96);
							inputState.guessing--;
						}
						if ( synPredMatched96 ) {
							ifExpr();
						}
						else {
							boolean synPredMatched98 = false;
							if (((LA(1)==LITERAL_try))) {
								int _m98 = mark();
								synPredMatched98 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_try);
									match(LCURLY);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched98 = false;
								}
								rewind(_m98);
								inputState.guessing--;
							}
							if ( synPredMatched98 ) {
								tryCatchExpr();
							}
							else if ((_tokenSet_2.member(LA(1)))) {
								orExpr();
							}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							}}}}}
						}
						
	public final void flworExpr() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt112=0;
		_loop112:
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
				if ( _cnt112>=1 ) { break _loop112; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			}
			_cnt112++;
		} while (true);
		}
		{
		switch ( LA(1)) {
		case LITERAL_where:
		{
			whereClause();
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
		_loop142:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				quantifiedInVarBinding();
			}
			else {
				break _loop142;
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
		int _cnt147=0;
		_loop147:
		do {
			if ((LA(1)==LITERAL_case)) {
				caseClause();
			}
			else {
				if ( _cnt147>=1 ) { break _loop147; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt147++;
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
		_loop154:
		do {
			if ((LA(1)==LITERAL_or)) {
				match(LITERAL_or);
				andExpr();
			}
			else {
				break _loop154;
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
		_loop117:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				inVarBinding();
			}
			else {
				break _loop117;
			}
			
		} while (true);
		}
	}
	
	public final void letClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_let);
		letVarBinding();
		{
		_loop124:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				letVarBinding();
			}
			else {
				break _loop124;
			}
			
		} while (true);
		}
	}
	
	public final void whereClause() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_where);
		expr();
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
		_loop132:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				orderSpec();
			}
			else {
				break _loop132;
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
		case LITERAL_return:
		case LITERAL_empty:
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
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_option:
		case LITERAL_external:
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
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case LITERAL_global:
		case LITERAL_context:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
		exprSingle();
	}
	
	public final void sequenceType() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched369 = false;
		if (((LA(1)==LITERAL_empty))) {
			int _m369 = mark();
			synPredMatched369 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_empty);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched369 = false;
			}
			rewind(_m369);
			inputState.guessing--;
		}
		if ( synPredMatched369 ) {
			match(LITERAL_empty);
			match(LPAREN);
			match(RPAREN);
		}
		else if ((_tokenSet_1.member(LA(1)))) {
			itemType();
			{
			switch ( LA(1)) {
			case PLUS:
			case STAR:
			case QUESTION:
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
			case COMMA:
			case LITERAL_for:
			case LITERAL_let:
			case RPAREN:
			case LITERAL_as:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
			case LITERAL_return:
			case LITERAL_in:
			case COLON:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_empty:
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
	
	public final void andExpr() throws RecognitionException, TokenStreamException {
		
		
		instanceofExpr();
		{
		_loop157:
		do {
			if ((LA(1)==LITERAL_and)) {
				match(LITERAL_and);
				instanceofExpr();
			}
			else {
				break _loop157;
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
		case COMMA:
		case LITERAL_for:
		case LITERAL_let:
		case RPAREN:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case COMMA:
		case LITERAL_for:
		case LITERAL_let:
		case RPAREN:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case COMMA:
		case LITERAL_for:
		case LITERAL_let:
		case RPAREN:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case COMMA:
		case LITERAL_for:
		case LITERAL_let:
		case RPAREN:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case COMMA:
		case LITERAL_for:
		case LITERAL_let:
		case RPAREN:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case RCURLY:
		case COMMA:
		case LITERAL_for:
		case LITERAL_let:
		case RPAREN:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
			boolean synPredMatched169 = false;
			if (((LA(1)==LT))) {
				int _m169 = mark();
				synPredMatched169 = true;
				inputState.guessing++;
				try {
					{
					match(LT);
					match(LT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched169 = false;
				}
				rewind(_m169);
				inputState.guessing--;
			}
			if ( synPredMatched169 ) {
				match(LT);
				match(LT);
				rangeExpr();
			}
			else {
				boolean synPredMatched171 = false;
				if (((LA(1)==GT))) {
					int _m171 = mark();
					synPredMatched171 = true;
					inputState.guessing++;
					try {
						{
						match(GT);
						match(GT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched171 = false;
					}
					rewind(_m171);
					inputState.guessing--;
				}
				if ( synPredMatched171 ) {
					match(GT);
					match(GT);
					rangeExpr();
				}
				else if ((_tokenSet_3.member(LA(1)))) {
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
		case RCURLY:
		case COMMA:
		case LITERAL_for:
		case LITERAL_let:
		case RPAREN:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		_loop183:
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
				break _loop183;
			}
			
		} while (true);
		}
	}
	
	public final void multiplicativeExpr() throws RecognitionException, TokenStreamException {
		
		
		unaryExpr();
		{
		_loop187:
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
				unaryExpr();
			}
			else {
				break _loop187;
			}
			
		} while (true);
		}
	}
	
	public final void unaryExpr() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop190:
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
				break _loop190;
			}
			}
		} while (true);
		}
		unionExpr();
	}
	
	public final void unionExpr() throws RecognitionException, TokenStreamException {
		
		
		intersectExceptExpr();
		{
		_loop194:
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
				break _loop194;
			}
			
		} while (true);
		}
	}
	
	public final void intersectExceptExpr() throws RecognitionException, TokenStreamException {
		
		
		valueExpr();
		{
		_loop198:
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
				break _loop198;
			}
			
		} while (true);
		}
	}
	
	public final void valueExpr() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched201 = false;
		if (((LA(1)==LITERAL_validate))) {
			int _m201 = mark();
			synPredMatched201 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_validate);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched201 = false;
			}
			rewind(_m201);
			inputState.guessing--;
		}
		if ( synPredMatched201 ) {
			validateExpr();
		}
		else if ((_tokenSet_4.member(LA(1)))) {
			pathExpr();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void validateExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_validate);
		{
		switch ( LA(1)) {
		case LITERAL_global:
		{
			{
			match(LITERAL_global);
			}
			break;
		}
		case LITERAL_context:
		{
			{
			match(LITERAL_context);
			schemaContextLoc();
			}
			break;
		}
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		{
			{
			schemaMode();
			{
			switch ( LA(1)) {
			case LITERAL_global:
			case LITERAL_context:
			{
				schemaContext();
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
			}
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
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case STRING_LITERAL:
		case LITERAL_at:
		case LITERAL_option:
		case DOLLAR:
		case LITERAL_external:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LPAREN:
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case LITERAL_global:
		case LITERAL_context:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case XML_CDATA:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
			boolean synPredMatched204 = false;
			if (((LA(1)==SLASH))) {
				int _m204 = mark();
				synPredMatched204 = true;
				inputState.guessing++;
				try {
					{
					match(SLASH);
					relativePathExpr();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched204 = false;
				}
				rewind(_m204);
				inputState.guessing--;
			}
			if ( synPredMatched204 ) {
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
		_loop208:
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
				break _loop208;
			}
			
		} while (true);
		}
	}
	
	public final void stepExpr() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched212 = false;
		if (((_tokenSet_5.member(LA(1))))) {
			int _m212 = mark();
			synPredMatched212 = true;
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
				synPredMatched212 = false;
			}
			rewind(_m212);
			inputState.guessing--;
		}
		if ( synPredMatched212 ) {
			axisStep();
		}
		else {
			boolean synPredMatched215 = false;
			if (((_tokenSet_6.member(LA(1))))) {
				int _m215 = mark();
				synPredMatched215 = true;
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
					synPredMatched215 = false;
				}
				rewind(_m215);
				inputState.guessing--;
			}
			if ( synPredMatched215 ) {
				filterStep();
			}
			else {
				boolean synPredMatched218 = false;
				if (((_tokenSet_6.member(LA(1))))) {
					int _m218 = mark();
					synPredMatched218 = true;
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
						synPredMatched218 = false;
					}
					rewind(_m218);
					inputState.guessing--;
				}
				if ( synPredMatched218 ) {
					filterStep();
				}
				else {
					boolean synPredMatched221 = false;
					if (((_tokenSet_6.member(LA(1))))) {
						int _m221 = mark();
						synPredMatched221 = true;
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
							case LITERAL_import:
							case LITERAL_default:
							case LITERAL_collation:
							case LITERAL_element:
							case LITERAL_function:
							case 16:
							case LITERAL_variable:
							case LITERAL_validation:
							case LITERAL_schema:
							case NCNAME:
							case LITERAL_at:
							case LITERAL_option:
							case LITERAL_external:
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
							case LITERAL_as:
							case LITERAL_with:
							case LITERAL_into:
							case LITERAL_preceding:
							case LITERAL_following:
							case LITERAL_return:
							case LITERAL_in:
							case LITERAL_where:
							case LITERAL_stable:
							case LITERAL_order:
							case LITERAL_by:
							case LITERAL_ascending:
							case LITERAL_descending:
							case LITERAL_empty:
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
							case LITERAL_to:
							case LITERAL_div:
							case LITERAL_idiv:
							case LITERAL_mod:
							case LITERAL_union:
							case LITERAL_intersect:
							case LITERAL_except:
							case LITERAL_validate:
							case LITERAL_text:
							case LITERAL_node:
							case LITERAL_attribute:
							case LITERAL_comment:
							case 104:
							case 105:
							case LITERAL_document:
							case LITERAL_global:
							case LITERAL_context:
							case LITERAL_child:
							case LITERAL_self:
							case LITERAL_descendant:
							case 119:
							case 120:
							case LITERAL_parent:
							case LITERAL_ancestor:
							case 123:
							case 124:
							case LITERAL_preserve:
							case LITERAL_strip:
							case LITERAL_item:
							case LITERAL_nillable:
							case LITERAL_type:
							case LITERAL_lax:
							case LITERAL_strict:
							case LITERAL_skip:
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
							synPredMatched221 = false;
						}
						rewind(_m221);
						inputState.guessing--;
					}
					if ( synPredMatched221 ) {
						filterStep();
					}
					else if ((_tokenSet_5.member(LA(1)))) {
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
		
		
		boolean synPredMatched249 = false;
		if (((_tokenSet_7.member(LA(1))))) {
			int _m249 = mark();
			synPredMatched249 = true;
			inputState.guessing++;
			try {
				{
				forwardAxisSpecifier();
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched249 = false;
			}
			rewind(_m249);
			inputState.guessing--;
		}
		if ( synPredMatched249 ) {
			forwardAxis();
			nodeTest();
		}
		else {
			boolean synPredMatched251 = false;
			if (((_tokenSet_8.member(LA(1))))) {
				int _m251 = mark();
				synPredMatched251 = true;
				inputState.guessing++;
				try {
					{
					reverseAxisSpecifier();
					match(COLON);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched251 = false;
				}
				rewind(_m251);
				inputState.guessing--;
			}
			if ( synPredMatched251 ) {
				reverseAxis();
				nodeTest();
			}
			else if ((_tokenSet_5.member(LA(1)))) {
				abbrevStep();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		
	public final void predicates() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop235:
		do {
			if ((LA(1)==LPPAREN)) {
				predicate();
			}
			else {
				break _loop235;
			}
			
		} while (true);
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
			boolean synPredMatched229 = false;
			if (((_tokenSet_9.member(LA(1))))) {
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
					synPredMatched229 = false;
				}
				rewind(_m229);
				inputState.guessing--;
			}
			if ( synPredMatched229 ) {
				computedConstructor();
			}
			else {
				boolean synPredMatched232 = false;
				if (((_tokenSet_9.member(LA(1))))) {
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
						case 104:
						{
							match(104);
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
					computedConstructor();
				}
				else if ((_tokenSet_1.member(LA(1)))) {
					functionCall();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}}
		}
		
	public final void contextItemExpr() throws RecognitionException, TokenStreamException {
		
		
		match(SELF);
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
		case 104:
		{
			compXmlPI();
			break;
		}
		case LITERAL_comment:
		{
			compXmlComment();
			break;
		}
		case LITERAL_namespace:
		{
			compNsConstructor();
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
			dirElemConstructor();
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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case STRING_LITERAL:
		case LITERAL_at:
		case LITERAL_option:
		case DOLLAR:
		case LITERAL_external:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LPAREN:
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_validate:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case LITERAL_global:
		case LITERAL_context:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case XML_CDATA:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
	
	public final void parenthesizedExpr() throws RecognitionException, TokenStreamException {
		
		
		match(LPAREN);
		{
		switch ( LA(1)) {
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case STRING_LITERAL:
		case LITERAL_at:
		case LITERAL_option:
		case DOLLAR:
		case LITERAL_external:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LPAREN:
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_validate:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case LITERAL_global:
		case LITERAL_context:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case XML_CDATA:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
	
	public final void predicate() throws RecognitionException, TokenStreamException {
		
		
		match(LPPAREN);
		expr();
		match(RPPAREN);
	}
	
	public final void schemaContextLoc() throws RecognitionException, TokenStreamException {
		
		
			String str1 = null;
		
		
		boolean synPredMatched413 = false;
		if (((LA(1)==LITERAL_type))) {
			int _m413 = mark();
			synPredMatched413 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_type);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched413 = false;
			}
			rewind(_m413);
			inputState.guessing--;
		}
		if ( synPredMatched413 ) {
			schemaGlobalTypeName();
		}
		else if ((_tokenSet_1.member(LA(1)))) {
			{
			{
			if ((_tokenSet_1.member(LA(1)))) {
				schemaContextPath();
			}
			else if ((_tokenSet_1.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			str1=qName();
			}
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
	}
	
	public final void schemaContext() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LITERAL_context:
		{
			{
			match(LITERAL_context);
			schemaContextLoc();
			}
			break;
		}
		case LITERAL_global:
		{
			match(LITERAL_global);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
	public final void dirElemConstructor() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched292 = false;
		if (((LA(1)==LT))) {
			int _m292 = mark();
			synPredMatched292 = true;
			inputState.guessing++;
			try {
				{
				match(LT);
				qName();
				{
				match(_tokenSet_10);
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
		
		
		boolean synPredMatched304 = false;
		if (((LA(1)==LITERAL_element))) {
			int _m304 = mark();
			synPredMatched304 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_element);
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
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_import:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case 16:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_schema:
			case NCNAME:
			case STRING_LITERAL:
			case LITERAL_at:
			case LITERAL_option:
			case DOLLAR:
			case LITERAL_external:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LPAREN:
			case LITERAL_update:
			case LITERAL_replace:
			case LITERAL_value:
			case LITERAL_insert:
			case LITERAL_delete:
			case LITERAL_rename:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_as:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_empty:
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
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case LITERAL_global:
			case LITERAL_context:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 119:
			case 120:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 123:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case XML_CDATA:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_item:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
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
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_import:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case 16:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_schema:
			case NCNAME:
			case STRING_LITERAL:
			case LITERAL_at:
			case LITERAL_option:
			case DOLLAR:
			case LITERAL_external:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LPAREN:
			case LITERAL_update:
			case LITERAL_replace:
			case LITERAL_value:
			case LITERAL_insert:
			case LITERAL_delete:
			case LITERAL_rename:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_as:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_empty:
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
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case LITERAL_global:
			case LITERAL_context:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 119:
			case 120:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 123:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case XML_CDATA:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_item:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
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
		
		
		boolean synPredMatched310 = false;
		if (((LA(1)==LITERAL_attribute))) {
			int _m310 = mark();
			synPredMatched310 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_attribute);
				match(LCURLY);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched310 = false;
			}
			rewind(_m310);
			inputState.guessing--;
		}
		if ( synPredMatched310 ) {
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
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_import:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case 16:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_schema:
			case NCNAME:
			case STRING_LITERAL:
			case LITERAL_at:
			case LITERAL_option:
			case DOLLAR:
			case LITERAL_external:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LPAREN:
			case LITERAL_update:
			case LITERAL_replace:
			case LITERAL_value:
			case LITERAL_insert:
			case LITERAL_delete:
			case LITERAL_rename:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_as:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_empty:
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
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case LITERAL_global:
			case LITERAL_context:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 119:
			case 120:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 123:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case XML_CDATA:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_item:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
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
			case LITERAL_xquery:
			case LITERAL_version:
			case LITERAL_module:
			case LITERAL_namespace:
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_import:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case 16:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_schema:
			case NCNAME:
			case STRING_LITERAL:
			case LITERAL_at:
			case LITERAL_option:
			case DOLLAR:
			case LITERAL_external:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LPAREN:
			case LITERAL_update:
			case LITERAL_replace:
			case LITERAL_value:
			case LITERAL_insert:
			case LITERAL_delete:
			case LITERAL_rename:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_as:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_empty:
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
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case LITERAL_global:
			case LITERAL_context:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 119:
			case 120:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 123:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case XML_CDATA:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_item:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case STRING_LITERAL:
		case LITERAL_at:
		case LITERAL_option:
		case DOLLAR:
		case LITERAL_external:
		case LITERAL_for:
		case LITERAL_let:
		case LITERAL_some:
		case LITERAL_every:
		case LITERAL_typeswitch:
		case LPAREN:
		case LITERAL_update:
		case LITERAL_replace:
		case LITERAL_value:
		case LITERAL_insert:
		case LITERAL_delete:
		case LITERAL_rename:
		case LITERAL_if:
		case LITERAL_try:
		case LITERAL_catch:
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_validate:
		case SLASH:
		case DSLASH:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case SELF:
		case XML_COMMENT:
		case XML_PI:
		case LITERAL_global:
		case LITERAL_context:
		case AT:
		case PARENT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case DOUBLE_LITERAL:
		case DECIMAL_LITERAL:
		case INTEGER_LITERAL:
		case XML_CDATA:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
		
		
		boolean synPredMatched315 = false;
		if (((LA(1)==104))) {
			int _m315 = mark();
			synPredMatched315 = true;
			inputState.guessing++;
			try {
				{
				match(104);
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
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_import:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case 16:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_schema:
			case NCNAME:
			case STRING_LITERAL:
			case LITERAL_at:
			case LITERAL_option:
			case DOLLAR:
			case LITERAL_external:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LPAREN:
			case LITERAL_update:
			case LITERAL_replace:
			case LITERAL_value:
			case LITERAL_insert:
			case LITERAL_delete:
			case LITERAL_rename:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_as:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_empty:
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
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case LITERAL_global:
			case LITERAL_context:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 119:
			case 120:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 123:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case XML_CDATA:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_item:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
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
			case LITERAL_declare:
			case LITERAL_xmlspace:
			case LITERAL_import:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case 16:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_schema:
			case NCNAME:
			case STRING_LITERAL:
			case LITERAL_at:
			case LITERAL_option:
			case DOLLAR:
			case LITERAL_external:
			case LITERAL_for:
			case LITERAL_let:
			case LITERAL_some:
			case LITERAL_every:
			case LITERAL_typeswitch:
			case LPAREN:
			case LITERAL_update:
			case LITERAL_replace:
			case LITERAL_value:
			case LITERAL_insert:
			case LITERAL_delete:
			case LITERAL_rename:
			case LITERAL_if:
			case LITERAL_try:
			case LITERAL_catch:
			case LITERAL_as:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_empty:
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
			case LITERAL_validate:
			case SLASH:
			case DSLASH:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case LITERAL_document:
			case SELF:
			case XML_COMMENT:
			case XML_PI:
			case LITERAL_global:
			case LITERAL_context:
			case AT:
			case PARENT:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 119:
			case 120:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 123:
			case 124:
			case DOUBLE_LITERAL:
			case DECIMAL_LITERAL:
			case INTEGER_LITERAL:
			case XML_CDATA:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_item:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
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
	
	public final void compXmlComment() throws RecognitionException, TokenStreamException {
		
		
		match(LITERAL_comment);
		match(LCURLY);
		expr();
		match(RCURLY);
	}
	
	public final void compNsConstructor() throws RecognitionException, TokenStreamException {
		
		
			String tmpStr = null;	
		
		
		match(LITERAL_namespace);
		tmpStr=ncnameOrKeyword();
		match(LCURLY);
		expr();
		match(RCURLY);
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
		case 119:
		{
			match(119);
			break;
		}
		case LITERAL_following:
		{
			match(LITERAL_following);
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
	
	public final void forwardAxis() throws RecognitionException, TokenStreamException {
		
		
		forwardAxisSpecifier();
		match(COLON);
		match(COLON);
	}
	
	public final void nodeTest() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched260 = false;
		if (((_tokenSet_11.member(LA(1))))) {
			int _m260 = mark();
			synPredMatched260 = true;
			inputState.guessing++;
			try {
				{
				match(105);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched260 = false;
			}
			rewind(_m260);
			inputState.guessing--;
		}
		if ( synPredMatched260 ) {
			kindTest();
		}
		else {
			boolean synPredMatched262 = false;
			if (((_tokenSet_11.member(LA(1))))) {
				int _m262 = mark();
				synPredMatched262 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_element);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched262 = false;
				}
				rewind(_m262);
				inputState.guessing--;
			}
			if ( synPredMatched262 ) {
				kindTest();
			}
			else {
				boolean synPredMatched264 = false;
				if (((_tokenSet_11.member(LA(1))))) {
					int _m264 = mark();
					synPredMatched264 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_attribute);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched264 = false;
					}
					rewind(_m264);
					inputState.guessing--;
				}
				if ( synPredMatched264 ) {
					kindTest();
				}
				else {
					boolean synPredMatched266 = false;
					if (((_tokenSet_11.member(LA(1))))) {
						int _m266 = mark();
						synPredMatched266 = true;
						inputState.guessing++;
						try {
							{
							match(104);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched266 = false;
						}
						rewind(_m266);
						inputState.guessing--;
					}
					if ( synPredMatched266 ) {
						kindTest();
					}
					else {
						boolean synPredMatched268 = false;
						if (((_tokenSet_11.member(LA(1))))) {
							int _m268 = mark();
							synPredMatched268 = true;
							inputState.guessing++;
							try {
								{
								match(LITERAL_comment);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched268 = false;
							}
							rewind(_m268);
							inputState.guessing--;
						}
						if ( synPredMatched268 ) {
							kindTest();
						}
						else {
							boolean synPredMatched270 = false;
							if (((_tokenSet_11.member(LA(1))))) {
								int _m270 = mark();
								synPredMatched270 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_text);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched270 = false;
								}
								rewind(_m270);
								inputState.guessing--;
							}
							if ( synPredMatched270 ) {
								kindTest();
							}
							else {
								boolean synPredMatched272 = false;
								if (((_tokenSet_11.member(LA(1))))) {
									int _m272 = mark();
									synPredMatched272 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_node);
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
								else if ((_tokenSet_12.member(LA(1)))) {
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
		case 123:
		{
			match(123);
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
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_option:
		case LITERAL_external:
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
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case LITERAL_global:
		case LITERAL_context:
		case AT:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_collection:
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
			case LITERAL_import:
			case LITERAL_default:
			case LITERAL_collation:
			case LITERAL_element:
			case LITERAL_function:
			case 16:
			case LITERAL_variable:
			case LITERAL_validation:
			case LITERAL_schema:
			case NCNAME:
			case LITERAL_at:
			case LITERAL_option:
			case LITERAL_external:
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
			case LITERAL_as:
			case LITERAL_with:
			case LITERAL_into:
			case LITERAL_preceding:
			case LITERAL_following:
			case LITERAL_return:
			case LITERAL_in:
			case LITERAL_where:
			case LITERAL_stable:
			case LITERAL_order:
			case LITERAL_by:
			case LITERAL_ascending:
			case LITERAL_descending:
			case LITERAL_empty:
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
			case LITERAL_to:
			case STAR:
			case LITERAL_div:
			case LITERAL_idiv:
			case LITERAL_mod:
			case LITERAL_union:
			case LITERAL_intersect:
			case LITERAL_except:
			case LITERAL_validate:
			case LITERAL_text:
			case LITERAL_node:
			case LITERAL_attribute:
			case LITERAL_comment:
			case 104:
			case 105:
			case LITERAL_document:
			case LITERAL_global:
			case LITERAL_context:
			case LITERAL_child:
			case LITERAL_self:
			case LITERAL_descendant:
			case 119:
			case 120:
			case LITERAL_parent:
			case LITERAL_ancestor:
			case 123:
			case 124:
			case LITERAL_preserve:
			case LITERAL_strip:
			case LITERAL_item:
			case LITERAL_nillable:
			case LITERAL_type:
			case LITERAL_lax:
			case LITERAL_strict:
			case LITERAL_skip:
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
			String prefix=null;
		
		
		boolean synPredMatched276 = false;
		if (((_tokenSet_12.member(LA(1))))) {
			int _m276 = mark();
			synPredMatched276 = true;
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
				case LITERAL_import:
				case LITERAL_default:
				case LITERAL_collation:
				case LITERAL_element:
				case LITERAL_function:
				case 16:
				case LITERAL_variable:
				case LITERAL_validation:
				case LITERAL_schema:
				case NCNAME:
				case LITERAL_at:
				case LITERAL_option:
				case LITERAL_external:
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
				case LITERAL_as:
				case LITERAL_with:
				case LITERAL_into:
				case LITERAL_preceding:
				case LITERAL_following:
				case LITERAL_return:
				case LITERAL_in:
				case LITERAL_where:
				case LITERAL_stable:
				case LITERAL_order:
				case LITERAL_by:
				case LITERAL_ascending:
				case LITERAL_descending:
				case LITERAL_empty:
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
				case LITERAL_to:
				case LITERAL_div:
				case LITERAL_idiv:
				case LITERAL_mod:
				case LITERAL_union:
				case LITERAL_intersect:
				case LITERAL_except:
				case LITERAL_validate:
				case LITERAL_text:
				case LITERAL_node:
				case LITERAL_attribute:
				case LITERAL_comment:
				case 104:
				case 105:
				case LITERAL_document:
				case LITERAL_global:
				case LITERAL_context:
				case LITERAL_child:
				case LITERAL_self:
				case LITERAL_descendant:
				case 119:
				case 120:
				case LITERAL_parent:
				case LITERAL_ancestor:
				case 123:
				case 124:
				case LITERAL_preserve:
				case LITERAL_strip:
				case LITERAL_item:
				case LITERAL_nillable:
				case LITERAL_type:
				case LITERAL_lax:
				case LITERAL_strict:
				case LITERAL_skip:
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
				synPredMatched276 = false;
			}
			rewind(_m276);
			inputState.guessing--;
		}
		if ( synPredMatched276 ) {
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
		
		boolean synPredMatched279 = false;
		if (((LA(1)==STAR))) {
			int _m279 = mark();
			synPredMatched279 = true;
			inputState.guessing++;
			try {
				{
				match(STAR);
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched279 = false;
			}
			rewind(_m279);
			inputState.guessing--;
		}
		if ( synPredMatched279 ) {
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
		_loop288:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				exprSingle();
			}
			else {
				break _loop288;
			}
			
		} while (true);
		}
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
		_loop327:
		do {
			if ((_tokenSet_13.member(LA(1)))) {
				elementContent();
			}
			else {
				break _loop327;
			}
			
		} while (true);
		}
	}
	
	public final void attributeList() throws RecognitionException, TokenStreamException {
		
		
		{
		int _cnt333=0;
		_loop333:
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				attributeDef();
			}
			else {
				if ( _cnt333>=1 ) { break _loop333; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt333++;
		} while (true);
		}
	}
	
	public final void elementContent() throws RecognitionException, TokenStreamException {
		
		
		switch ( LA(1)) {
		case LT:
		{
			dirElemConstructor();
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
			boolean synPredMatched330 = false;
			if (((LA(1)==LCURLY))) {
				int _m330 = mark();
				synPredMatched330 = true;
				inputState.guessing++;
				try {
					{
					match(LCURLY);
					match(LCURLY);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched330 = false;
				}
				rewind(_m330);
				inputState.guessing--;
			}
			if ( synPredMatched330 ) {
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
			_loop337:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==QUOT_ATTRIBUTE_CONTENT)) {
					quotAttrValueContent();
				}
				else {
					break _loop337;
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
			_loop339:
			do {
				if ((LA(1)==LCURLY||LA(1)==RCURLY||LA(1)==APOS_ATTRIBUTE_CONTENT)) {
					aposAttrValueContent();
				}
				else {
					break _loop339;
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
		
		
		boolean synPredMatched344 = false;
		if (((LA(1)==LCURLY))) {
			int _m344 = mark();
			synPredMatched344 = true;
			inputState.guessing++;
			try {
				{
				match(LCURLY);
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
	
	public final void paramList() throws RecognitionException, TokenStreamException {
		
		
		param();
		{
		_loop361:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				param();
			}
			else {
				break _loop361;
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
	
	public final void atomicType() throws RecognitionException, TokenStreamException {
		
		
			String name = null;
		
		
		name=qName();
	}
	
	public final void itemType() throws RecognitionException, TokenStreamException {
		
		
		boolean synPredMatched374 = false;
		if (((LA(1)==LITERAL_item))) {
			int _m374 = mark();
			synPredMatched374 = true;
			inputState.guessing++;
			try {
				{
				match(LITERAL_item);
				match(LPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched374 = false;
			}
			rewind(_m374);
			inputState.guessing--;
		}
		if ( synPredMatched374 ) {
			match(LITERAL_item);
			match(LPAREN);
			match(RPAREN);
		}
		else {
			boolean synPredMatched376 = false;
			if (((_tokenSet_11.member(LA(1))))) {
				int _m376 = mark();
				synPredMatched376 = true;
				inputState.guessing++;
				try {
					{
					match(105);
					match(LPAREN);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched376 = false;
				}
				rewind(_m376);
				inputState.guessing--;
			}
			if ( synPredMatched376 ) {
				kindTest();
			}
			else {
				boolean synPredMatched378 = false;
				if (((_tokenSet_11.member(LA(1))))) {
					int _m378 = mark();
					synPredMatched378 = true;
					inputState.guessing++;
					try {
						{
						match(LITERAL_element);
						match(LPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched378 = false;
					}
					rewind(_m378);
					inputState.guessing--;
				}
				if ( synPredMatched378 ) {
					kindTest();
				}
				else {
					boolean synPredMatched380 = false;
					if (((_tokenSet_11.member(LA(1))))) {
						int _m380 = mark();
						synPredMatched380 = true;
						inputState.guessing++;
						try {
							{
							match(LITERAL_attribute);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched380 = false;
						}
						rewind(_m380);
						inputState.guessing--;
					}
					if ( synPredMatched380 ) {
						kindTest();
					}
					else {
						boolean synPredMatched382 = false;
						if (((_tokenSet_11.member(LA(1))))) {
							int _m382 = mark();
							synPredMatched382 = true;
							inputState.guessing++;
							try {
								{
								match(104);
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
							kindTest();
						}
						else {
							boolean synPredMatched384 = false;
							if (((_tokenSet_11.member(LA(1))))) {
								int _m384 = mark();
								synPredMatched384 = true;
								inputState.guessing++;
								try {
									{
									match(LITERAL_comment);
									match(LPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched384 = false;
								}
								rewind(_m384);
								inputState.guessing--;
							}
							if ( synPredMatched384 ) {
								kindTest();
							}
							else {
								boolean synPredMatched386 = false;
								if (((_tokenSet_11.member(LA(1))))) {
									int _m386 = mark();
									synPredMatched386 = true;
									inputState.guessing++;
									try {
										{
										match(LITERAL_text);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched386 = false;
									}
									rewind(_m386);
									inputState.guessing--;
								}
								if ( synPredMatched386 ) {
									kindTest();
								}
								else {
									boolean synPredMatched388 = false;
									if (((_tokenSet_11.member(LA(1))))) {
										int _m388 = mark();
										synPredMatched388 = true;
										inputState.guessing++;
										try {
											{
											match(LITERAL_node);
											match(LPAREN);
											}
										}
										catch (RecognitionException pe) {
											synPredMatched388 = false;
										}
										rewind(_m388);
										inputState.guessing--;
									}
									if ( synPredMatched388 ) {
										kindTest();
									}
									else if ((_tokenSet_1.member(LA(1)))) {
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
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_option:
		case LITERAL_external:
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
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case LITERAL_global:
		case LITERAL_context:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_collection:
		{
			elementNameOrWildcard();
			{
			switch ( LA(1)) {
			case COMMA:
			{
				match(COMMA);
				typeNameOrWildcard();
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
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_option:
		case LITERAL_external:
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
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case STAR:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case LITERAL_global:
		case LITERAL_context:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
		case LITERAL_collection:
		{
			attributeNameOrWildcard();
			{
			switch ( LA(1)) {
			case COMMA:
			{
				match(COMMA);
				typeNameOrWildcard();
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
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_option:
		case LITERAL_external:
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
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case LITERAL_global:
		case LITERAL_context:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
	
	public final void typeNameOrWildcard() throws RecognitionException, TokenStreamException {
		
		
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
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_option:
		case LITERAL_external:
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
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case LITERAL_global:
		case LITERAL_context:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
		case LITERAL_xquery:
		case LITERAL_version:
		case LITERAL_module:
		case LITERAL_namespace:
		case LITERAL_declare:
		case LITERAL_xmlspace:
		case LITERAL_import:
		case LITERAL_default:
		case LITERAL_collation:
		case LITERAL_element:
		case LITERAL_function:
		case 16:
		case LITERAL_variable:
		case LITERAL_validation:
		case LITERAL_schema:
		case NCNAME:
		case LITERAL_at:
		case LITERAL_option:
		case LITERAL_external:
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
		case LITERAL_as:
		case LITERAL_with:
		case LITERAL_into:
		case LITERAL_preceding:
		case LITERAL_following:
		case LITERAL_return:
		case LITERAL_in:
		case LITERAL_where:
		case LITERAL_stable:
		case LITERAL_order:
		case LITERAL_by:
		case LITERAL_ascending:
		case LITERAL_descending:
		case LITERAL_empty:
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
		case LITERAL_to:
		case LITERAL_div:
		case LITERAL_idiv:
		case LITERAL_mod:
		case LITERAL_union:
		case LITERAL_intersect:
		case LITERAL_except:
		case LITERAL_validate:
		case LITERAL_text:
		case LITERAL_node:
		case LITERAL_attribute:
		case LITERAL_comment:
		case 104:
		case 105:
		case LITERAL_document:
		case LITERAL_global:
		case LITERAL_context:
		case LITERAL_child:
		case LITERAL_self:
		case LITERAL_descendant:
		case 119:
		case 120:
		case LITERAL_parent:
		case LITERAL_ancestor:
		case 123:
		case 124:
		case LITERAL_preserve:
		case LITERAL_strip:
		case LITERAL_item:
		case LITERAL_nillable:
		case LITERAL_type:
		case LITERAL_lax:
		case LITERAL_strict:
		case LITERAL_skip:
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
	
	public final void schemaContextPath() throws RecognitionException, TokenStreamException {
		
		
			String str1 = null;	
		
		
		{
		if ((_tokenSet_1.member(LA(1)))) {
			str1=qName();
		}
		else if ((LA(1)==LITERAL_type)) {
			schemaGlobalTypeName();
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		match(SLASH);
		{
		_loop410:
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				str1=qName();
				match(SLASH);
			}
			else {
				break _loop410;
			}
			
		} while (true);
		}
	}
	
	public final void schemaGlobalTypeName() throws RecognitionException, TokenStreamException {
		
		
			String str1 = null;
		
		
		match(LITERAL_type);
		match(LPAREN);
		str1=qName();
		match(RPAREN);
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
		case 123:
		{
			match(123);
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
		case 16:
		{
			match(16);
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
		case LITERAL_context:
		{
			match(LITERAL_context);
			if ( inputState.guessing==0 ) {
				name = "context";
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
		case 119:
		{
			match(119);
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
		case 120:
		{
			match(120);
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
		case LITERAL_global:
		{
			match(LITERAL_global);
			if ( inputState.guessing==0 ) {
				name = "global";
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
		case LITERAL_option:
		{
			match(LITERAL_option);
			if ( inputState.guessing==0 ) {
				name= "option";
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
		case LITERAL_validation:
		{
			match(LITERAL_validation);
			if ( inputState.guessing==0 ) {
				name = "validation";
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
		"EQ",
		"\"declare\"",
		"\"xmlspace\"",
		"\"import\"",
		"\"default\"",
		"\"collation\"",
		"\"element\"",
		"\"function\"",
		"\"base-uri\"",
		"\"variable\"",
		"\"validation\"",
		"\"schema\"",
		"NCNAME",
		"STRING_LITERAL",
		"\"at\"",
		"\"option\"",
		"SEMICOLON",
		"DOLLAR",
		"LCURLY",
		"RCURLY",
		"\"external\"",
		"COMMA",
		"\"for\"",
		"\"let\"",
		"\"some\"",
		"\"every\"",
		"\"typeswitch\"",
		"'('",
		"\"update\"",
		"\"replace\"",
		"\"value\"",
		"\"insert\"",
		"\"delete\"",
		"\"rename\"",
		"\"if\"",
		"\"try\"",
		"\"catch\"",
		"')'",
		"\"as\"",
		"\"with\"",
		"\"into\"",
		"\"preceding\"",
		"\"following\"",
		"\"return\"",
		"\"in\"",
		"COLON",
		"\"where\"",
		"\"stable\"",
		"\"order\"",
		"\"by\"",
		"\"ascending\"",
		"\"descending\"",
		"\"empty\"",
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
		"\"validate\"",
		"SLASH",
		"DSLASH",
		"\"text\"",
		"\"node\"",
		"\"attribute\"",
		"\"comment\"",
		"\"processing-instruction\"",
		"\"document-node\"",
		"\"document\"",
		"SELF",
		"XML_COMMENT",
		"XML_PI",
		"LPPAREN",
		"RPPAREN",
		"\"global\"",
		"\"context\"",
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
		"DOUBLE_LITERAL",
		"DECIMAL_LITERAL",
		"INTEGER_LITERAL",
		"END_TAG_START",
		"XQDOC_COMMENT",
		"XML_CDATA",
		"XML_CDATA_END",
		"XML_PI_END",
		"XML_COMMENT_END",
		"ELEMENT_CONTENT",
		"QUOT",
		"APOS",
		"QUOT_ATTRIBUTE_CONTENT",
		"APOS_ATTRIBUTE_CONTENT",
		"\"preserve\"",
		"\"strip\"",
		"QUESTION",
		"\"item\"",
		"\"nillable\"",
		"\"type\"",
		"\"lax\"",
		"\"strict\"",
		"\"skip\"",
		"\"collection\"",
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
		long[] data = { -9042384381804816L, -211107308111873L, 2086918L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -9042418777194768L, 2302192577935569919L, 2086912L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -9042384381804816L, -211107308111873L, 2086916L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 256L, 1838080L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { -9042384381804816L, -211107333277697L, 2086916L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { -9042418777194768L, 2305570277689652223L, 2086912L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { -9042384381804816L, -3588858626967553L, 2086916L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 1125899906842624L, 139611863326392320L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 562949953421312L, 2161727821137838080L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 16512L, 6390911336448L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = new long[8];
		data[0]=-16L;
		data[1]=-17179871233L;
		data[2]=8796093022207L;
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 16384L, 4329327034368L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { -9042418777194768L, 2302192577969124351L, 2086912L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { 201326592L, 52776558134272L, 68L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	
	}
