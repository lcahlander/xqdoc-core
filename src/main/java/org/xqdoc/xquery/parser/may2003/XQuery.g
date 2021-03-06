/*
 * Copyright (c)2005 Elsevier, Inc.
 *
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
 * 
 * This grammar file is partially based on work that was developed by 
 * by Wolfgang Meier(with his permission) for the eXist XML database.
 * Changes were made to remove the AST processing (as it wasn't needed),
 * to more fully conform to the XQuery W3C Working Draft, and to add
 * support for xqDoc comments and certain vendor specific extensions.
 */
header {
	
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
}

class XQueryParser extends Parser;

options {
	defaultErrorHandler= false;
	k= 1;
	buildAST= false;
	classHeaderSuffix=org.xqdoc.conversion.XQDocParser;
}

{
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
	
}

xpath 
:
	( module )? EOF
	;


module 
: 	
	( ((xqdocComment)? ( "module" )) => libraryModule
		| 
		mainModule
	)
	;

mainModule 
: 
	(xqdocComment)? 
	{ context.buildMainModuleSection(); }
	("xquery" "version" STRING_LITERAL)? 
	prolog
	queryBody	 
	;

libraryModule 
: 
	(xqdocComment)? moduleDecl ("xquery" "version" STRING_LITERAL)? prolog
	;

moduleDecl
{
	String uri = null;	
} 
: 
	"module" uri=strippedStringLiteral 
	{
		context.buildLibraryModuleSection(uri);
	}
	;

prolog 
:
	(
		(
			( (xqdocComment)? "import" "module" ) => moduleImport 
			|
			( "declare" ( "xmlspace" | "namespace" ) )  => setter 
			|
			( "default" ( "collation" | "element" | "function" ) )  => setter 
			|
			( (xqdocComment)? "define" "variable" ) => varFunctionDecl 
			|
			( "validation" ("lax" | "strict" | "skip") )		
			|
			( "import" "schema" ( ("namespace" NCNAME EQ) | ("default" "element" "namespace" EQ) )? STRING_LITERAL ( "at" STRING_LITERAL )?)
		)*
		(( (xqdocComment)? "define"  "function" ) => varFunctionDecl)*
	)
	;

	
setter
{
	String uri = null;	
}
:

	(
		( "default" ) => "default"
		(
			"collation" EQ STRING_LITERAL
			|
			"element" "namespace" EQ STRING_LITERAL
			|
			"function" "namespace" EQ uri=strippedStringLiteral
			{ 
				context.setDefaultModuleFunctionNamespace(uri);
			}			
		)
		|
		( "declare" "xmlspace" ) => "declare" "xmlspace" EQ ( "preserve" | "strip" )
		|		
		( "declare" "namespace" ) => namespaceDecl  								
	)
	;
	
namespaceDecl
{
	String prefix = null;
	String uri = null;
}
:
	"declare" "namespace" prefix=ncnameOrKeyword EQ uri=strippedStringLiteral
	{ 
		context.addPrefixAndURI(prefix, uri);
	}
	;

varFunctionDecl
:
	(
		( (xqdocComment)? "define" "function" ) => functionDecl
		|
		( (xqdocComment)? "define" "variable" ) => varDecl	
	)	
	;
	
varDecl 
{ 
	String varName= null;
	String localName=null;
}
:
	(xqdocComment)? "define" "variable" DOLLAR varName=qName( typeDeclaration )? ( (LCURLY expr RCURLY) | "external" ) 
	{ 
		String[] tmp = varName.split(":", 2);
		localName = varName;
		if (tmp.length > 1) {
			localName = tmp[1];
		}
		context.buildVariableSection(localName);        
	}
	;
	
moduleImport
{
	String prefix = null;
	String uri = null;	
}
:
	(xqdocComment)? "import" "module" ( "namespace" prefix=ncnameOrKeyword EQ )? uri=strippedStringLiteral ("at" junk:STRING_LITERAL )? 
	{
		if (prefix != null) {
			context.addPrefixAndURI(prefix, uri);
		}
		context.buildImportSection(uri);
	}		
	;
	
functionDecl 
{ 
	String name= null;
 	String localName = null; 
 	String prefix = null;
}
:
	(xqdocComment)? 	
	"define" "function" 
	name=qName 
	{ 
		buildFuncSigFlag = true; 
		lexer.whiteSpaceBag = new StringBuffer();
	}	
	LPAREN ( paramList )?
	RPAREN ( returnType )?
	{ 
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
	functionBody
	;

functionBody 
: 
	{ 
		buildFuncBodyFlag=true;
  		lexer.whiteSpaceBag = new StringBuffer();
	}
	
	 LCURLY expr  RCURLY  
	
	{ 
		buildFuncBodyFlag=false;
		context.setFunctionBody(functionBody.toString());
		context.buildFunctionSection();
		functionBody = new StringBuffer();
	}
	;

returnType 
: 
	"as" sequenceType ;

paramList 
:
	param ( COMMA param )*
	;

param 
{
	String name = null;
}
:
	DOLLAR name=qName ( typeDeclaration )?
	;

typeDeclaration 
: 
	"as" sequenceType ;

sequenceType 
:
	( "empty" LPAREN ) => "empty" LPAREN RPAREN | itemType ( occurrenceIndicator )?
	;

occurrenceIndicator
:
	QUESTION | STAR | PLUS
	;

itemType 
:
	( "item" LPAREN ) => "item" LPAREN RPAREN 
	|
	( "document-node" LPAREN ) => kindTest
	|
	( "element" LPAREN ) => kindTest
	|
	( "attribute" LPAREN ) => kindTest
	|
	( "processing-instruction" LPAREN )  => kindTest
	|
	( "comment" LPAREN ) => kindTest
	| 
	( "text" LPAREN ) => kindTest
	|
	( "node" LPAREN ) => kindTest
	|
	atomicType
	;

singleType 
:
	atomicType ( QUESTION )?
	;

atomicType 
{
	String name = null;
}
:
	name=qName
	;

queryBody 
: 
	{ 
		buildFuncBodyFlag=true;
  		lexer.whiteSpaceBag = new StringBuffer();
	}
	expr 
	{ 
		buildFuncBodyFlag=false;
		context.setFunctionName("local","xqDoc-main");
		context.setFunctionSignature(null);
		context.setFunctionBody(functionBody.toString());
		context.buildFunctionSection();
		functionBody = new StringBuffer();	
	}
;

expr 
:
	exprSingle ( COMMA exprSingle )*
	;

exprSingle 
:
	( ( "for" | "let" ) DOLLAR ) => flworExpr
	| ( ( "some" | "every" ) DOLLAR ) => quantifiedExpr
	| ("typeswitch" LPAREN) => typeswitchExpr
	| ( "if" LPAREN ) => ifExpr 
	| ( "try" LCURLY ) => tryCatchExpr
	| orExpr
	;


tryCatchExpr
{
	String tmpStr = null;	
}
:
	"try" LCURLY expr RCURLY "catch" LPAREN DOLLAR tmpStr=qName RPAREN LCURLY expr RCURLY
	;
	
flworExpr 
:
	( forClause | letClause )+ ( "where" expr )? ( orderByClause )? "return" exprSingle
	;

forClause 
:
	"for" inVarBinding ( COMMA inVarBinding )*
	;

letClause 
:
	"let" letVarBinding ( COMMA letVarBinding )*
	;

inVarBinding 
{
	String name=null;
}
:
	DOLLAR name=qName ( typeDeclaration )? ( positionalVar )? "in" exprSingle
	;

positionalVar
{
	String name=null;
}
:
	"at" DOLLAR name=qName
	;

letVarBinding 
{
	String name=null;
}
:
	DOLLAR name=qName ( typeDeclaration )? COLON EQ exprSingle
	;

orderByClause 
:
	("stable")? "order" "by" orderSpecList
	;

orderSpecList 
:
	orderSpec ( COMMA orderSpec )*
	;

orderSpec 
:
 	exprSingle orderModifier 
 	;

orderModifier
:
	( "ascending" | "descending" )? ( "empty" ( "greatest" | "least" ) )? ( "collation" STRING_LITERAL )?
	;

quantifiedExpr 
:
	( "some" | "every" ) quantifiedInVarBinding ( COMMA quantifiedInVarBinding )* "satisfies" exprSingle
	;


quantifiedInVarBinding 
{
	String name=null;
}
:
	DOLLAR name=qName ( typeDeclaration )? "in" exprSingle
	;

typeswitchExpr 
{ 
	String varName=null; 
}
: 
	"typeswitch" LPAREN expr RPAREN (caseClause)+ "default" (DOLLAR varName=qName)? "return" exprSingle
	;

caseClause
{ 
	String varName=null; 
}
:
	"case" (DOLLAR varName=qName "as")? sequenceType "return" expr
	;
		
ifExpr 
: 
	"if" LPAREN expr RPAREN "then" expr "else" exprSingle 
	;

orExpr 
:
	andExpr ( "or" andExpr )*
	;

andExpr 
:
	instanceofExpr ( "and" instanceofExpr )*
	;

instanceofExpr 
:
	treatExpr ( "instance" "of" sequenceType )?
	;

treatExpr
:
	castableExpr ("treat" "as" sequenceType)?
	;
	
castableExpr 
:
	castExpr ( "castable" "as" singleType )?
	;
	
castExpr 
:
	comparisonExpr ( "cast" "as" singleType )?
	;

comparisonExpr 
:
	rangeExpr (
		( LT LT ) => LT LT rangeExpr 
		|
		( GT GT ) => GT GT rangeExpr
		| ( ( "eq" | "ne" | "lt" | "le" | "gt" | "ge" ) rangeExpr )
		| ( ( EQ | NEQ | GT | GTEQ | LT | LTEQ ) rangeExpr )
		| ( ( "is" | "isnot" ) rangeExpr )
	)?
	;

rangeExpr 
:
	additiveExpr ( "to" additiveExpr )?
	;

additiveExpr 
:
	multiplicativeExpr ( ( PLUS | MINUS ) multiplicativeExpr )*
	;

multiplicativeExpr 
:
	unaryExpr ( ( STAR | "div" | "idiv" | "mod" ) unaryExpr )*
	;

unaryExpr 
:
	(MINUS | PLUS)* unionExpr
	;

unionExpr 
:
	intersectExceptExpr
	( ( "union" | UNION ) intersectExceptExpr )*
	;

intersectExceptExpr 
:
	valueExpr
	( ( "intersect" | "except" ) valueExpr )*
	;

/* 
 * Currently the May 2003 Grammar is not set up to support
 * validateExpr within a valueExpr.
 */
valueExpr	
:
	pathExpr
	;

pathExpr 
:
	relativePathExpr
	|
	( SLASH relativePathExpr ) => SLASH relativePathExpr
	|
	SLASH
	|
	DSLASH relativePathExpr
	;

relativePathExpr 
:
	stepExpr ( ( SLASH | DSLASH ) stepExpr )*
	;

stepExpr 
:
	( ( "text" | "node" | "element" | "attribute" | "comment" | "processing-instruction" | "document-node" ) LPAREN ) => axisStep
	|
	( ( "element" | "attribute" | "text" | "document" | "processing-instruction" | "comment" ) LCURLY ) => filterStep
	|
	( ( "element" | "attribute" | "processing-instruction" | "namespace" ) qName LCURLY ) => filterStep
	|
	( DOLLAR | ( qName LPAREN ) | SELF | LPAREN | literal | XML_COMMENT | LT | XML_PI )=> filterStep
	|
	axisStep
	;

axisStep 
:
	( forwardOrReverseStep ) predicates
	;

predicates 
:
	( predicate )*
	;

predicate 
:
	LPPAREN expr RPPAREN
	;

forwardOrReverseStep 
:
	( forwardAxisSpecifier COLON ) => forwardAxis nodeTest
	|
	( reverseAxisSpecifier COLON ) => reverseAxis nodeTest
	|
	( propertyAxisSpecifier COLON ) => propertyAxis nodeTest
	|
	abbrevStep
	;

abbrevStep 
:
	( AT )? nodeTest | PARENT
	;

/*
 * The properties axis has been added to support the MarkLogic
 * properties axis extension
 */
propertyAxis
:
	propertyAxisSpecifier COLON COLON ;

propertyAxisSpecifier
:
	"property" ;
		
forwardAxis 
: 
	forwardAxisSpecifier COLON COLON ;

/*
 * The forwardAxisSpecifier allows more axis specifiers than 
 * designated in the spec. This is done to support XQuery from
 * some implemenations that supported these extended specifiers
 * for their May 2003 implementations. All of the specifiers 
 * are supported in the Oct 2004 spec.
 */
forwardAxisSpecifier
:
	"child" | "self" | "attribute" | "descendant" | "descendant-or-self" | "following" | "following-sibling"
	;

reverseAxis 
: 
	reverseAxisSpecifier COLON COLON ;

/*
 * The reverseAxisSpecifier allows more axis specifiers than 
 * designated in the spec. This is done to support XQuery from
 * some implemenations that supported these extended specifiers
 * for their May 2003 implementations. All of the specifiers 
 * are supported in the Oct 2004 spec.
 */
reverseAxisSpecifier
:
	"parent" | "ancestor" | "ancestor-or-self" | "preceding" | "preceding-sibling"
	;

nodeTest 
:
	( "document-node" LPAREN ) => kindTest
	|
	( "element" LPAREN ) => kindTest
	|
	( "attribute" LPAREN ) => kindTest
	|
	( "processing-instruction" LPAREN )  => kindTest
	|
	( "comment" LPAREN ) => kindTest
	| 
	( "text" LPAREN ) => kindTest
	|
	( "node" LPAREN ) => kindTest
	|
	nameTest
	;

nameTest 
{
	String name=null;
	String prefix = null;
}
:
	( ( prefix=ncnameOrKeyword COLON STAR ) | STAR ) => wildcard
	|
	name=qName
	;

wildcard
{String name=null;}
:
	// *:localname
	( STAR COLON ) => STAR COLON name=ncnameOrKeyword
	|
	// prefix:*
	name=ncnameOrKeyword COLON STAR
	|
	// *
	STAR
	;

filterStep 
: 
	primaryExpr predicates ;

primaryExpr 
{String name=null;}
:
	( ( "element" | "attribute" | "text" | "document" | "processing-instruction" | 	"comment" ) LCURLY ) => computedConstructor
	|
	( ( "element" | "attribute" | "processing-instruction" | "namespace" ) qName LCURLY ) => computedConstructor
	|
	constructor
	|
	functionCall
	|
	contextItemExpr
	|
	parenthesizedExpr
	|
	DOLLAR name=qName
	{
		context.setReferencedVariable(name);
	}
	|
	literal
	;

literal
:
	STRING_LITERAL | numericLiteral
	;

numericLiteral
:
	DOUBLE_LITERAL | DECIMAL_LITERAL | INTEGER_LITERAL
	;

parenthesizedExpr 
:
	LPAREN ( expr )? RPAREN
	;

functionCall 
{ 
	String fnName= null; 
}
:
	fnName=qName LPAREN ( params:functionParameters )? RPAREN
	{ 
		context.setInvokedFunction(fnName);
	}	
	;

functionParameters 
:
	exprSingle ( COMMA exprSingle )*
	;

contextItemExpr 
: 
	SELF ;

kindTest
:
	textTest | anyKindTest | elementTest | attributeTest | commentTest | piTest | documentTest
	;

textTest 
: 
	"text" LPAREN RPAREN ;

anyKindTest 
: 
	"node" LPAREN RPAREN ;

elementTest 
{
	String tmpStr = null;	
}
: 
	"element" LPAREN ( elementNameOrWildcard (COMMA tmpStr=qName ("nillable")?)? )? RPAREN ;

elementNameOrWildcard 
{
	String name=null;
}
:
	STAR 
	|
	name=qName 
	;

attributeTest 
{
	String tmpStr = null;	
}
: 
	"attribute" LPAREN ( AT attributeNameOrWildcard (COMMA tmpStr=qName)? ) ? RPAREN 
	;

attributeNameOrWildcard
{
	String name=null;
}
:
	STAR 
	|
	name=qName 
	;
	
commentTest 
: 
	"comment" LPAREN RPAREN ;

piTest 
: 
	"processing-instruction" LPAREN (STRING_LITERAL)? RPAREN ;

documentTest 
: 
	"document-node" LPAREN (elementTest)? RPAREN ;

qName returns [String name]
{
	name= null;
	String name2;
}
:
	( ncnameOrKeyword COLON ncnameOrKeyword ) => name=ncnameOrKeyword COLON name2=ncnameOrKeyword
	{ 
		name= name + ':' + name2;
	}
	|
	name=ncnameOrKeyword
	;

strippedStringLiteral returns [String strippedLiteral]
{
	strippedLiteral = null;
}
:
	literal:STRING_LITERAL
	{ 
		strippedLiteral = literal.getText();
		if (strippedLiteral.length() <= 2) {
			strippedLiteral = "";
		} else {
			strippedLiteral = strippedLiteral.substring(1,strippedLiteral.length()-1);
		}
	}
	;
	
/*
 * The constructor production is currently spilt into 2 productions:
 * constructor and computedConstructor. Additionally, the constructor
 * production does not support CDATA contents.
 */
constructor 
:
	elementConstructor
	| 
	xmlComment 
	| 
	xmlPI
	|
	cdataSection
	;

computedConstructor 
:
	compElemConstructor
	|
	compAttrConstructor
	|
	compTextConstructor
	|
	compDocumentConstructor
	|
	compXmlPI
	|
	compXmlComment
	;

compElemConstructor 
{
	String name=null;
}
:
	( "element" LCURLY ) =>	"element" LCURLY expr RCURLY LCURLY (expr)? RCURLY
	|
	"element" name=qName LCURLY (expr)? RCURLY
	;

compElemBody 
:
	( 
		( "namespace" ncnameOrKeyword LCURLY ) => localNamespaceDecl 
		| 
		exprSingle 
	)
	( COMMA (
		( "namespace" ncnameOrKeyword LCURLY ) => localNamespaceDecl 
		| 
		exprSingle ) 
	)*
	;
	
compAttrConstructor 
{
	String name=null;
}
:
	( "attribute" LCURLY ) => "attribute" LCURLY expr RCURLY LCURLY (expr)? RCURLY
	|
	"attribute" name=qName LCURLY (expr)? RCURLY
	;

compTextConstructor 
:
	"text" LCURLY (expr)? RCURLY
	;

compDocumentConstructor 
:
	"document" LCURLY expr RCURLY
	;

compXmlPI 
{
	String name=null;
}
:
	( "processing-instruction" LCURLY ) => "processing-instruction" LCURLY expr RCURLY LCURLY expr RCURLY
	|
	"processing-instruction" name=qName LCURLY expr RCURLY
	;

compXmlComment 
:
	"comment" LCURLY expr RCURLY
	;

localNamespaceDecl
{
	String name=null;
}
:
	"namespace" name=ncnameOrKeyword LCURLY STRING_LITERAL RCURLY
	;

elementConstructor 
:
	( LT qName ~( GT | SLASH ) ) => elementWithAttributes | elementWithoutAttributes
	;

elementWithoutAttributes 
{ 
	String name= null; 
}
:
	LT name=qName
	(
		(
			SLASH GT
			{
				if (!elementStack.isEmpty())
					lexer.inElementContent= true;
			}
		)
		|
		(
			GT
			{
				elementStack.push(name);
				lexer.inElementContent= true;
			}
			mixedElementContent END_TAG_START name=qName GT
			{
				if (elementStack.isEmpty())
					{}
				String prev= (String) elementStack.pop();
				if (!prev.equals(name))
					{}
				if (!elementStack.isEmpty()) {
					lexer.inElementContent= true;
				}
			}
		)
	)
	;

elementWithAttributes 
{ 
	String name= null; 
}
:
	LT name=qName attributeList
	(
		(
			SLASH GT
			{
				if (!elementStack.isEmpty())
					lexer.inElementContent= true;
			}
		)
		|
		(
			GT
			{
				elementStack.push(name);
				lexer.inElementContent= true;
			}
			mixedElementContent END_TAG_START name=qName GT
			{
				if (elementStack.isEmpty())
					{}
				String prev= (String) elementStack.pop();
				if (!prev.equals(name))
					{}
				if (!elementStack.isEmpty()) {
					lexer.inElementContent= true;
				}
			}
		)
	)
	;

attributeList 
:
	( attributeDef )+
	;

attributeDef 
{
	String name=null;
	lexer.parseStringLiterals= false;
}
:
	name=qName EQ 
	attributeValue
	;
	
attributeValue
:	
	( 
		QUOT
		{ 
			lexer.inAttributeContent= true; 
			lexer.attrDelimChar = '"';
		}
		( quotAttrValueContent )*
		QUOT
		{ 
			lexer.parseStringLiterals= true;
			lexer.inAttributeContent= false;
		}
		| 
		APOS 
		{ 
			lexer.inAttributeContent= true; 
			lexer.attrDelimChar = '\'';
		}
		( aposAttrValueContent )*
		APOS 
		{ 
			lexer.parseStringLiterals= true;
			lexer.inAttributeContent= false;
		}
	)
	;

quotAttrValueContent  
:
	QUOT_ATTRIBUTE_CONTENT | attrCommonContent
	;

aposAttrValueContent
:
	APOS_ATTRIBUTE_CONTENT | attrCommonContent
	;
	
attrCommonContent
:
	( LCURLY LCURLY )=> LCURLY LCURLY
	{ 	
		lexer.inAttributeContent= true;
		lexer.parseStringLiterals = false; 
	}
	|
	RCURLY RCURLY
	|
	attributeEnclosedExpr
	;

mixedElementContent 
:
	( elementContent )*
	;

elementContent 
:
	elementConstructor
	|
	( LCURLY LCURLY )=> LCURLY LCURLY
	{ 	
		lexer.inElementContent= true;
	}
	|
	RCURLY RCURLY
	|
	ELEMENT_CONTENT
	|
	xmlComment
	|
	xmlPI
	|
	enclosedExpr
	|
	cdataSection
	;

xmlComment 
: 
	XML_COMMENT XML_COMMENT_END 
	;

xqdocComment 
: 
	x:XQDOC_COMMENT 
	{  
		context.setXQDocBuffer(x.getText());
	}
	;

cdataSection
:
	XML_CDATA XML_CDATA_END
	;
	
xmlPI 
: 
	XML_PI XML_PI_END 
	;

enclosedExpr 
:
	LCURLY
	{
		globalStack.push(elementStack);
		elementStack= new Stack();
		lexer.inElementContent= false;
	}
	expr RCURLY
	{
		elementStack= (Stack) globalStack.pop();
		lexer.inElementContent= true;
	}
	;

attributeEnclosedExpr 
:
	LCURLY
	{
		lexer.inAttributeContent= false;
		lexer.parseStringLiterals = true;
	}
	expr RCURLY
	{
		lexer.inAttributeContent= true;
		lexer.parseStringLiterals = false;
	}
	;

/* All of the literals used in this grammar can also be
 * part of a valid QName. We thus have to test for each
 * of them below.
 */
ncnameOrKeyword returns [String name]
{ name= null; }
:
	n1:NCNAME { name= n1.getText(); }
	|
	name=reservedKeywords
	;

reservedKeywords returns [String name]
{ name= null; }
:
	"ancestor" { name= "ancestor"; }
	|
	"ancestor-or-self" { name= "ancestor-or-self"; }
	|
	"and" { name= "and"; }
	|
	"as" { name = "as"; }
	|
	"ascending" { name = "ascending"; }
	|
	"at" { name = "at"; }
	|
	"attribute" { name= "attribute"; }
	|
	"by" { name = "by"; }
	|
	"case" { name = "case"; }
	|
	"cast" { name = "cast"; }
	|
	"castable" { name = "castable"; }
	|
	"catch" { name = "catch"; }
	|
	"child" { name= "child"; }
	|
	"collation" { name = "collation"; }
	|
	"collection" { name= "collection"; }
	|	
	"comment" { name= "comment"; }
	|
	"declare" { name = "declare"; }
	|
	"default" { name= "default"; }
	|
	"define" {name = "define"; }
	|
	"descendant" { name= "descendant"; }
	|
	"descendant-or-self" { name= "descendant-or-self"; }
	|
	"descending" { name = "descending"; }
	|
	"div" { name= "div"; }
	|
	"document" { name= "document"; }
	|
	"document-node" { name= "document-node"; }
	|
	"element" { name = "element"; }
	|
	"else" { name= "else"; }
	|
	"empty" { name= "empty"; }
	|
	"eq" { name = "eq"; }
	|
	"every" { name = "every"; }
	|
	"except" { name = "except"; }
	|
	"external" { name = "external"; }
	|
	"following" { name = "following"; }
	|
	"following-sibling" { name= "following-sibling"; }
	|
	"for" { name= "for"; }
	|
	"function" { name= "function"; }
	|
	"ge" { name = "ge"; }
	|
	"greatest" { name = "greatest"; }
	|
	"gt" { name = "gt"; }
	|
	"idiv" { name = "idiv"; }
	|
	"if" { name= "if"; }
	|
	"import" { name = "import"; }
	|
	"in" { name = "in"; }
	|
	"instance" { name = "instance"; }
	|
	"intersect" { name = "intersect"; }
	|
	"is" { name = "is"; }
	|
	"isnot" { name = "isnot"; }
	|
	"item" { name= "item"; }
	|
	"lax" { name = "lax"; }
	|
	"le" { name = "le"; }
	|
	"least" { name = "least"; }
	|
	"let" { name= "let"; }
	|
	"lt" { name = "lt"; }
	|
	"mod" { name= "mod"; }
	|
	"module" { name = "module"; }
	|
	"namespace" { name= "namespace"; }
	|
	"ne" { name = "ne"; }
	|
	"nillable" { name = "nillable"; }
	|
	"node" { name= "node"; }
	|
	"of" { name = "of"; }
	|
	"or" { name= "or"; }
	|
	"order" { name = "order"; }
	|
	"parent" { name= "parent"; }
	|
	"preceding" { name = "preceding"; }
	|
	"preceding-sibling" { name= "preceding-sibling"; }
	|
	"preserve" { name = "preserve"; }
	|
	"processing-instruction" { name = "processing-instruction"; }
	|
	"property" { name = "property"; }
	|
	"return" { name = "return"; }
	|
	"satisfies" { name = "satisfies"; }
	|
	"schema" { name = "schema"; }
	|
	"self" { name= "self"; }
	|
	"skip" { name = "skip"; }
	|
	"some" { name = "some"; }
	|
	"stable" { name = "stable"; }
	|
	"strict" { name =  "strict"; }
	|
	"strip" { name = "strip"; }
	|
	"text" { name= "text"; }
	|
	"then" { name= "then"; }
	|
	"to" { name = "to"; }
	|
	"treat" { name = "treat"; }
	|
	"try" { name = "try"; }
	|
	"typeswitch" { name = "typeswitch"; }
	|
	"xmlspace" { name = "xmlspace"; }
	|
	"xquery" { name= "xquery"; }
	|
	"union" { name = "union"; }
	|
	"validation" { name = "validation"; }
	|
	"variable" { name= "variable"; }
	|
	"version" { name= "version"; }
	|
	"where" { name = "where"; }
	;
	


/**
 * The XQuery/XPath lexical analyzer.
 */
class XQueryLexer extends Lexer;

options {
	k = 4;
	testLiterals = false;
	charVocabulary = '\u0003'..'\uFFFE';
	codeGenBitsetTestThreshold = 20;
}

{
	protected boolean wsExplicit= false;
	protected boolean parseStringLiterals= true;
	protected boolean inElementContent= false;
	protected boolean inAttributeContent= false;
	protected char attrDelimChar = '"';
	protected boolean inComment= false;
	protected StringBuffer whiteSpaceBag = new StringBuffer();

	
} 

protected SLASH : '/' ;
protected DSLASH : '/' '/' ;
protected COLON : ':' ;
protected COMMA : ',' ;
protected SEMICOLON : ';' ;
protected STAR : '*' ;
protected QUESTION : '?' ;
protected PLUS : '+' ;
protected MINUS : '-' ;
protected LPPAREN : '[' ;
protected RPPAREN : ']' ;
protected LPAREN options { paraphrase="'('"; } : '(' ;
protected RPAREN options { paraphrase="')'"; } : ')' ;
protected SELF : '.' ;
protected PARENT : ".." ;
protected UNION : '|' ;
protected AT : '@' ;
protected DOLLAR : '$' ;
protected ANDEQ : "&=" ;
protected OREQ : "|=" ;
protected EQ : '=' ;
protected NEQ : "!=" ;
protected GT : '>' ;
protected GTEQ : ">=" ;
protected QUOT : '"' ;
protected APOS : "'";
protected LTEQ : "<=" ;

protected LT : '<' ;
protected END_TAG_START : "</" ;

protected LCURLY : '{' ;
protected RCURLY : '}' ;

protected XML_COMMENT_END : "-->" ;
protected XML_CDATA_END : "]]>" ;
protected XML_PI_START : "<?" ;
protected XML_PI_END : "?>" ;

protected LETTER
:
	( BASECHAR | IDEOGRAPHIC )
	;

protected DIGITS
:
	( DIGIT )+
	;

protected HEX_DIGITS
:
	( '0'..'9' | 'a'..'f' | 'A'..'F' )+
	;

protected NMSTART
:
	( LETTER | '_' )
	;

protected NMCHAR
:
	( LETTER | DIGIT | '.' | '-' | '_' | COMBINING_CHAR | EXTENDER )
	;

protected NCNAME
options {
	testLiterals=true;
}
:
	NMSTART ( NMCHAR )*
	;

protected WS
:
	(
		' '
		|
		'\t'
		|
		'\n' { newline(); }
		|
		'\r'
	)+
	;

protected EXPR_COMMENT
options {
	testLiterals=false;
}
:
	"(:" ( CHAR | ( ':' ~( ')' ) ) => ':' | ('(' ':') => EXPR_COMMENT )* ":)"
	;

protected XQDOC_COMMENT
options {
	testLiterals=false;
}
:
	"(:~" ( CHAR | ( ':' ~( ')' ) ) => ':' )* ":)"
	;
	
protected PRAGMA
options {
	testLiterals=false;
}
{ String content = null; }:
	"(::" "pragma"
	WS qn:PRAGMA_QNAME WS 
	( c:PRAGMA_CONTENT { content = c.getText(); } )? ':' ':' ')'
	;

protected PRAGMA_CONTENT
:
	( ~( ' ' | '\t' | '\n' | '\r' ) ) 
	( CHAR | (':' ~( ':' )) => ':' | (':' ':' ~(')') ) => ':' ':' )+
	;

protected PRAGMA_QNAME
:
	NCNAME ( ':' NCNAME )?
	;
	
protected INTEGER_LITERAL : 
	{ !(inElementContent || inAttributeContent) }? DIGITS ;

protected DOUBLE_LITERAL
:
	{ !(inElementContent || inAttributeContent) }?
	( ( '.' DIGITS ) | ( DIGITS ( '.' ( DIGIT )* )? ) ) ( 'e' | 'E' ) ( '+' | '-' )? DIGITS
	;

protected DECIMAL_LITERAL
:
	{ !(inElementContent || inAttributeContent) }?
	( '.' DIGITS ) | ( DIGITS ( '.' ( DIGIT )* )? )
	;

protected PREDEFINED_ENTITY_REF
:
	'&' ( "lt" | "gt" | "amp" | "quot" | "apos" ) ';'
	;

protected CHAR_REF
:
	'&' '#' ( DIGITS | ( 'x' HEX_DIGITS ) ) ';'
	;

protected STRING_LITERAL
options {
	testLiterals = false;
}
:
	'"' ( PREDEFINED_ENTITY_REF | CHAR_REF | ( '"' '"' ) | ~ ( '"' | '&' ) )*
	'"'
	|
	'\'' ( PREDEFINED_ENTITY_REF | CHAR_REF | ( '\'' '\'' ) | ~ ( '\'' | '&' ) )*
	'\''
	;

/**
 * The following definition differs from the spec by allowing the
 * '&' character, which is handled by the constructor classes.
 *
 * TODO: Allow escaped quotes in attribute content. Doesn't work.
 */	
protected QUOT_ATTRIBUTE_CONTENT
options {
	testLiterals=false;
}
:
	( ~( '"' | '{' | '}' | '<' ) )+
	;

/**
 * The following definition differs from the spec by allowing the
 * '&' character, which is handled by the constructor classes.
 *
 * TODO: Allow escaped quotes in attribute content. Doesn't work.
 */
protected APOS_ATTRIBUTE_CONTENT
options {
	testLiterals=false;
}
:
	( ~( '\'' | '{' | '}' | '<' ) )+
	;
/*
 * The definition for ELEMENT_CONTENT differs from the spec
 * in that it allows for the presense of the '&' character.
 * This can also impact any productions created from this rule.
 */
protected ELEMENT_CONTENT
options {
	testLiterals=false;
}
:
	( '\t' | '\r' | '\n' { newline(); } | '\u0020'..'\u003b' | '\u003d'..'\u007a' | '\u007c' | '\u007e'..'\uFFFD' )+
	;

protected XML_COMMENT
options {
	testLiterals=false;
}
:
	"<!--" ( ~ ( '-' ) | ( '-' ~ ( '-' ) ) => '-' )+
	;

protected XML_CDATA
options {
	testLiterals=false;
}
:
	"<![CDATA[" ( ~ ( ']' ) | ( ']' ~ ( ']' ) ) => ']' )+
	;
	
protected XML_PI
options {
	testLiterals=false;
}
:
	XML_PI_START NCNAME ' ' ( ~ ( '?' ) | ( '?' ~ ( '>' ) ) => '?' )+
	;

/**
 * Main method that decides which token to return next.
 * We need this as many things depend on the current
 * context. 
 */
NEXT_TOKEN
options {
	testLiterals = false;
}
:
	XML_COMMENT 
		{ $setType(XML_COMMENT); }
	|
	XML_CDATA
		{ $setType(XML_CDATA); }
	|
	( XML_PI_START ) => XML_PI 
		{ $setType(XML_PI); }
	|
	END_TAG_START
		{
			inElementContent= false;
			wsExplicit= false;
			$setType(END_TAG_START);
		}
	|
	LT
		{
			inElementContent= false;
			$setType(LT);
		}
	|
	LTEQ 
		{ $setType(LTEQ); }
	|
	LCURLY
		{
			inElementContent= false;
			inAttributeContent= false;
			$setType(LCURLY);
		}
	|
	RCURLY { $setType(RCURLY); }
	|
	{ inAttributeContent && attrDelimChar == '"' }?
	QUOT_ATTRIBUTE_CONTENT
		{ $setType(QUOT_ATTRIBUTE_CONTENT); }
	|
	{ inAttributeContent && attrDelimChar == '\'' }?
	APOS_ATTRIBUTE_CONTENT
		{ $setType(APOS_ATTRIBUTE_CONTENT); }
	|
	{ !(parseStringLiterals || inElementContent) }?
	QUOT
		{ $setType(QUOT); }
	|
	{ !(parseStringLiterals || inElementContent) }?
	APOS 
		{ $setType(APOS); }			
	|	
	{ inElementContent }?
	ELEMENT_CONTENT
		{ $setType(ELEMENT_CONTENT); }
	|
	WS
	{
		if (wsExplicit) {
			$setType(WS);
			//$setText("WS");
		} else {
			whiteSpaceBag.append($getText);
			$setType(Token.SKIP);
		}
	}
	|
	( "(::" ) => PRAGMA
		{ $setType(Token.SKIP); }
	|
	( "(:~" ) => XQDOC_COMMENT
		{ $setType(XQDOC_COMMENT); }
	|
	EXPR_COMMENT
		{ $setType(Token.SKIP); }
	|
	ncname:NCNAME 
		{ $setType(ncname.getType()); }
	|
	{ parseStringLiterals }?
	STRING_LITERAL 
		{ $setType(STRING_LITERAL); }
	|
	( '.' '.' ) =>
	{ !(inAttributeContent || inElementContent) }?
	PARENT 
		{ $setType(PARENT); }
	|
    ( '.' INTEGER_LITERAL ( 'e' | 'E' ) ) => DECIMAL_LITERAL 
		{ $setType(DECIMAL_LITERAL); }
    |
	( '.' INTEGER_LITERAL ) => DECIMAL_LITERAL 
		{ $setType(DECIMAL_LITERAL); }
	|
	( '.' ) => SELF 
		{ $setType(SELF); }
	|
	( INTEGER_LITERAL ( '.' ( INTEGER_LITERAL )? )? ( 'e' | 'E' ) ) => DOUBLE_LITERAL
		{ $setType(DOUBLE_LITERAL); }
	|
	( INTEGER_LITERAL '.' ) => DECIMAL_LITERAL
		{ $setType(DECIMAL_LITERAL); }
	|
	INTEGER_LITERAL 
		{ $setType(INTEGER_LITERAL); }
	|
	SLASH 
		{ $setType(SLASH); }
	|
	{ !(inAttributeContent || inElementContent) }?
	DSLASH 
		{ $setType(DSLASH); }
	|
	COLON 
		{ $setType(COLON); }
	|
	COMMA 
		{ $setType(COMMA); }
	|
	SEMICOLON 
		{ $setType(SEMICOLON); }
	|
	STAR 
		{ $setType(STAR); }
	|
	QUESTION 
		{ $setType(QUESTION); }
	|
	PLUS 
		{ $setType(PLUS); }
	|
	MINUS 
		{ $setType(MINUS); }
	|
	LPPAREN 
		{ $setType(LPPAREN); }
	|
	RPPAREN 
		{ $setType(RPPAREN); }
	|
	LPAREN 
		{ $setType(LPAREN); }
	|
	RPAREN 
		{ $setType(RPAREN); }
	|
	UNION 
		{ $setType(UNION); }
	|
	AT 
		{ $setType(AT); }
	|
	DOLLAR 
		{ $setType(DOLLAR); }
	|
	{ !(inAttributeContent || inElementContent) }?
	OREQ 
		{ $setType(OREQ); }
	|
	{ !(inAttributeContent || inElementContent) }?
	ANDEQ 
		{ $setType(ANDEQ); }
	|
	EQ 
		{ $setType(EQ); }
	|
	{ !(inAttributeContent || inElementContent) }?
	NEQ 
		{ $setType(NEQ); }
	|
	XML_COMMENT_END 
		{ $setType(XML_COMMENT_END); }
	|
	XML_CDATA_END 
		{ $setType(XML_CDATA_END); }
	|
	GT 
		{ $setType(GT); }
	|
	{ !(inAttributeContent || inElementContent) }?
	GTEQ 
		{ $setType(GTEQ); }
	|
	XML_PI_END 
		{ $setType(XML_PI_END); }
	;

/*
 * The definition for CHAR differs from the spec in that it 
 * disallows for the presense of the ':' character.
 */
protected CHAR
:
	( '\t' | '\n' { newline(); } | '\r' | '\u0020'..'\u0039' | '\u003B'..'\uD7FF' | '\uE000'..'\uFFFD' )
	;

protected BASECHAR
:
	(
		'\u0041'..'\u005a'
		|
		'\u0061'..'\u007a'
		|
		'\u00c0'..'\u00d6'
		|
		'\u00d8'..'\u00f6'
		|
		'\u00f8'..'\u00ff'
		|
		'\u0100'..'\u0131'
		|
		'\u0134'..'\u013e'
		|
		'\u0141'..'\u0148'
		|
		'\u014a'..'\u017e'
		|
		'\u0180'..'\u01c3'
		|
		'\u01cd'..'\u01f0'
		|
		'\u01f4'..'\u01f5'
		|
		'\u01fa'..'\u0217'
		|
		'\u0250'..'\u02a8'
		|
		'\u02bb'..'\u02c1'
		|
		'\u0386'
		|
		'\u0388'..'\u038a'
		|
		'\u038c'
		|
		'\u038e'..'\u03a1'
		|
		'\u03a3'..'\u03ce'
		|
		'\u03d0'..'\u03d6'
		|
		'\u03da'
		|
		'\u03dc'
		|
		'\u03de'
		|
		'\u03e0'
		|
		'\u03e2'..'\u03f3'
		|
		'\u0401'..'\u040c'
		|
		'\u040e'..'\u044f'
		|
		'\u0451'..'\u045c'
		|
		'\u045e'..'\u0481'
		|
		'\u0490'..'\u04c4'
		|
		'\u04c7'..'\u04c8'
		|
		'\u04cb'..'\u04cc'
		|
		'\u04d0'..'\u04eb'
		|
		'\u04ee'..'\u04f5'
		|
		'\u04f8'..'\u04f9'
		|
		'\u0531'..'\u0556'
		|
		'\u0559'
		|
		'\u0561'..'\u0586'
		|
		'\u05d0'..'\u05ea'
		|
		'\u05f0'..'\u05f2'
		|
		'\u0621'..'\u063a'
		|
		'\u0641'..'\u064a'
		|
		'\u0671'..'\u06b7'
		|
		'\u06ba'..'\u06be'
		|
		'\u06c0'..'\u06ce'
		|
		'\u06d0'..'\u06d3'
		|
		'\u06d5'
		|
		'\u06e5'..'\u06e6'
		|
		'\u0905'..'\u0939'
		|
		'\u093d'
		|
		'\u0958'..'\u0961'
		|
		'\u0985'..'\u098c'
		|
		'\u098f'..'\u0990'
		|
		'\u0993'..'\u09a8'
		|
		'\u09aa'..'\u09b0'
		|
		'\u09b2'
		|
		'\u09b6'..'\u09b9'
		|
		'\u09dc'..'\u09dd'
		|
		'\u09df'..'\u09e1'
		|
		'\u09f0'..'\u09f1'
		|
		'\u0a05'..'\u0a0a'
		|
		'\u0a0f'..'\u0a10'
		|
		'\u0a13'..'\u0a28'
		|
		'\u0a2a'..'\u0a30'
		|
		'\u0a32'..'\u0a33'
		|
		'\u0a35'..'\u0a36'
		|
		'\u0a38'..'\u0a39'
		|
		'\u0a59'..'\u0a5c'
		|
		'\u0a5e'
		|
		'\u0a72'..'\u0a74'
		|
		'\u0a85'..'\u0a8b'
		|
		'\u0a8d'
		|
		'\u0a8f'..'\u0a91'
		|
		'\u0a93'..'\u0aa8'
		|
		'\u0aaa'..'\u0ab0'
		|
		'\u0ab2'..'\u0ab3'
		|
		'\u0ab5'..'\u0ab9'
		|
		'\u0abd'
		|
		'\u0ae0'
		|
		'\u0b05'..'\u0b0c'
		|
		'\u0b0f'..'\u0b10'
		|
		'\u0b13'..'\u0b28'
		|
		'\u0b2a'..'\u0b30'
		|
		'\u0b32'..'\u0b33'
		|
		'\u0b36'..'\u0b39'
		|
		'\u0b3d'
		|
		'\u0b5c'..'\u0b5d'
		|
		'\u0b5f'..'\u0b61'
		|
		'\u0b85'..'\u0b8a'
		|
		'\u0b8e'..'\u0b90'
		|
		'\u0b92'..'\u0b95'
		|
		'\u0b99'..'\u0b9a'
		|
		'\u0b9c'
		|
		'\u0b9e'..'\u0b9f'
		|
		'\u0ba3'..'\u0ba4'
		|
		'\u0ba8'..'\u0baa'
		|
		'\u0bae'..'\u0bb5'
		|
		'\u0bb7'..'\u0bb9'
		|
		'\u0c05'..'\u0c0c'
		|
		'\u0c0e'..'\u0c10'
		|
		'\u0c12'..'\u0c28'
		|
		'\u0c2a'..'\u0c33'
		|
		'\u0c35'..'\u0c39'
		|
		'\u0c60'..'\u0c61'
		|
		'\u0c85'..'\u0c8c'
		|
		'\u0c8e'..'\u0c90'
		|
		'\u0c92'..'\u0ca8'
		|
		'\u0caa'..'\u0cb3'
		|
		'\u0cb5'..'\u0cb9'
		|
		'\u0cde'
		|
		'\u0ce0'..'\u0ce1'
		|
		'\u0d05'..'\u0d0c'
		|
		'\u0d0e'..'\u0d10'
		|
		'\u0d12'..'\u0d28'
		|
		'\u0d2a'..'\u0d39'
		|
		'\u0d60'..'\u0d61'
		|
		'\u0e01'..'\u0e2e'
		|
		'\u0e30'
		|
		'\u0e32'..'\u0e33'
		|
		'\u0e40'..'\u0e45'
		|
		'\u0e81'..'\u0e82'
		|
		'\u0e84'
		|
		'\u0e87'..'\u0e88'
		|
		'\u0e8a'
		|
		'\u0e8d'
		|
		'\u0e94'..'\u0e97'
		|
		'\u0e99'..'\u0e9f'
		|
		'\u0ea1'..'\u0ea3'
		|
		'\u0ea5'
		|
		'\u0ea7'
		|
		'\u0eaa'..'\u0eab'
		|
		'\u0ead'..'\u0eae'
		|
		'\u0eb0'
		|
		'\u0eb2'..'\u0eb3'
		|
		'\u0ebd'
		|
		'\u0ec0'..'\u0ec4'
		|
		'\u0f40'..'\u0f47'
		|
		'\u0f49'..'\u0f69'
		|
		'\u10a0'..'\u10c5'
		|
		'\u10d0'..'\u10f6'
		|
		'\u1100'
		|
		'\u1102'..'\u1103'
		|
		'\u1105'..'\u1107'
		|
		'\u1109'
		|
		'\u110b'..'\u110c'
		|
		'\u110e'..'\u1112'
		|
		'\u113c'
		|
		'\u113e'
		|
		'\u1140'
		|
		'\u114c'
		|
		'\u114e'
		|
		'\u1150'
		|
		'\u1154'..'\u1155'
		|
		'\u1159'
		|
		'\u115f'..'\u1161'
		|
		'\u1163'
		|
		'\u1165'
		|
		'\u1167'
		|
		'\u1169'
		|
		'\u116d'..'\u116e'
		|
		'\u1172'..'\u1173'
		|
		'\u1175'
		|
		'\u119e'
		|
		'\u11a8'
		|
		'\u11ab'
		|
		'\u11ae'..'\u11af'
		|
		'\u11b7'..'\u11b8'
		|
		'\u11ba'
		|
		'\u11bc'..'\u11c2'
		|
		'\u11eb'
		|
		'\u11f0'
		|
		'\u11f9'
		|
		'\u1e00'..'\u1e9b'
		|
		'\u1ea0'..'\u1ef9'
		|
		'\u1f00'..'\u1f15'
		|
		'\u1f18'..'\u1f1d'
		|
		'\u1f20'..'\u1f45'
		|
		'\u1f48'..'\u1f4d'
		|
		'\u1f50'..'\u1f57'
		|
		'\u1f59'
		|
		'\u1f5b'
		|
		'\u1f5d'
		|
		'\u1f5f'..'\u1f7d'
		|
		'\u1f80'..'\u1fb4'
		|
		'\u1fb6'..'\u1fbc'
		|
		'\u1fbe'
		|
		'\u1fc2'..'\u1fc4'
		|
		'\u1fc6'..'\u1fcc'
		|
		'\u1fd0'..'\u1fd3'
		|
		'\u1fd6'..'\u1fdb'
		|
		'\u1fe0'..'\u1fec'
		|
		'\u1ff2'..'\u1ff4'
		|
		'\u1ff6'..'\u1ffc'
		|
		'\u2126'
		|
		'\u212a'..'\u212b'
		|
		'\u212e'
		|
		'\u2180'..'\u2182'
		|
		'\u3041'..'\u3094'
		|
		'\u30a1'..'\u30fa'
		|
		'\u3105'..'\u312c'
		|
		'\uac00'..'\ud7a3'
	)
	;

protected IDEOGRAPHIC
:
	( '\u4e00'..'\u9fa5' | '\u3007' | '\u3021'..'\u3029' )
	;

protected COMBINING_CHAR
:
	(
		'\u0300'..'\u0345'
		|
		'\u0360'..'\u0361'
		|
		'\u0483'..'\u0486'
		|
		'\u0591'..'\u05a1'
		|
		'\u05a3'..'\u05b9'
		|
		'\u05bb'..'\u05bd'
		|
		'\u05bf'
		|
		'\u05c1'..'\u05c2'
		|
		'\u05c4'
		|
		'\u064b'..'\u0652'
		|
		'\u0670'
		|
		'\u06d6'..'\u06dc'
		|
		'\u06dd'..'\u06df'
		|
		'\u06e0'..'\u06e4'
		|
		'\u06e7'..'\u06e8'
		|
		'\u06ea'..'\u06ed'
		|
		'\u0901'..'\u0903'
		|
		'\u093c'
		|
		'\u093e'..'\u094c'
		|
		'\u094d'
		|
		'\u0951'..'\u0954'
		|
		'\u0962'..'\u0963'
		|
		'\u0981'..'\u0983'
		|
		'\u09bc'
		|
		'\u09be'
		|
		'\u09bf'
		|
		'\u09c0'..'\u09c4'
		|
		'\u09c7'..'\u09c8'
		|
		'\u09cb'..'\u09cd'
		|
		'\u09d7'
		|
		'\u09e2'..'\u09e3'
		|
		'\u0a02'
		|
		'\u0a3c'
		|
		'\u0a3e'
		|
		'\u0a3f'
		|
		'\u0a40'..'\u0a42'
		|
		'\u0a47'..'\u0a48'
		|
		'\u0a4b'..'\u0a4d'
		|
		'\u0a70'..'\u0a71'
		|
		'\u0a81'..'\u0a83'
		|
		'\u0abc'
		|
		'\u0abe'..'\u0ac5'
		|
		'\u0ac7'..'\u0ac9'
		|
		'\u0acb'..'\u0acd'
		|
		'\u0b01'..'\u0b03'
		|
		'\u0b3c'
		|
		'\u0b3e'..'\u0b43'
		|
		'\u0b47'..'\u0b48'
		|
		'\u0b4b'..'\u0b4d'
		|
		'\u0b56'..'\u0b57'
		|
		'\u0b82'..'\u0b83'
		|
		'\u0bbe'..'\u0bc2'
		|
		'\u0bc6'..'\u0bc8'
		|
		'\u0bca'..'\u0bcd'
		|
		'\u0bd7'
		|
		'\u0c01'..'\u0c03'
		|
		'\u0c3e'..'\u0c44'
		|
		'\u0c46'..'\u0c48'
		|
		'\u0c4a'..'\u0c4d'
		|
		'\u0c55'..'\u0c56'
		|
		'\u0c82'..'\u0c83'
		|
		'\u0cbe'..'\u0cc4'
		|
		'\u0cc6'..'\u0cc8'
		|
		'\u0cca'..'\u0ccd'
		|
		'\u0cd5'..'\u0cd6'
		|
		'\u0d02'..'\u0d03'
		|
		'\u0d3e'..'\u0d43'
		|
		'\u0d46'..'\u0d48'
		|
		'\u0d4a'..'\u0d4d'
		|
		'\u0d57'
		|
		'\u0e31'
		|
		'\u0e34'..'\u0e3a'
		|
		'\u0e47'..'\u0e4e'
		|
		'\u0eb1'
		|
		'\u0eb4'..'\u0eb9'
		|
		'\u0ebb'..'\u0ebc'
		|
		'\u0ec8'..'\u0ecd'
		|
		'\u0f18'..'\u0f19'
		|
		'\u0f35'
		|
		'\u0f37'
		|
		'\u0f39'
		|
		'\u0f3e'
		|
		'\u0f3f'
		|
		'\u0f71'..'\u0f84'
		|
		'\u0f86'..'\u0f8b'
		|
		'\u0f90'..'\u0f95'
		|
		'\u0f97'
		|
		'\u0f99'..'\u0fad'
		|
		'\u0fb1'..'\u0fb7'
		|
		'\u0fb9'
		|
		'\u20d0'..'\u20dc'
		|
		'\u20e1'
		|
		'\u302a'..'\u302f'
		|
		'\u3099'
		|
		'\u309a'
	)
	;

protected DIGIT
:
	(
		'\u0030'..'\u0039'
		|
		'\u0660'..'\u0669'
		|
		'\u06f0'..'\u06f9'
		|
		'\u0966'..'\u096f'
		|
		'\u09e6'..'\u09ef'
		|
		'\u0a66'..'\u0a6f'
		|
		'\u0ae6'..'\u0aef'
		|
		'\u0b66'..'\u0b6f'
		|
		'\u0be7'..'\u0bef'
		|
		'\u0c66'..'\u0c6f'
		|
		'\u0ce6'..'\u0cef'
		|
		'\u0d66'..'\u0d6f'
		|
		'\u0e50'..'\u0e59'
		|
		'\u0ed0'..'\u0ed9'
		|
		'\u0f20'..'\u0f29'
	)
	;

protected EXTENDER
:
	(
		'\u00b7'
		|
		'\u02d0'
		|
		'\u02d1'
		|
		'\u0387'
		|
		'\u0640'
		|
		'\u0e46'
		|
		'\u0ec6'
		|
		'\u3005'
		|
		'\u3031'..'\u3035'
		|
		'\u309d'..'\u309e'
		|
		'\u30fc'..'\u30fe'
	)
	;

