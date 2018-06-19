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

public interface XQueryParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int LITERAL_xquery = 4;
	int LITERAL_version = 5;
	int LITERAL_module = 6;
	int LITERAL_namespace = 7;
	int STRING_LITERAL = 8;
	int LITERAL_encoding = 9;
	int EQ = 10;
	int LITERAL_declare = 11;
	int LITERAL_xmlspace = 12;
	int LITERAL_default = 13;
	int LITERAL_collation = 14;
	// "base-uri" = 15
	int LITERAL_construction = 16;
	int LITERAL_ordering = 17;
	int LITERAL_order = 18;
	// "inherit-namespaces" = 19
	int LITERAL_import = 20;
	int LITERAL_schema = 21;
	int LITERAL_element = 22;
	int LITERAL_function = 23;
	int LITERAL_variable = 24;
	int SEMICOLON = 25;
	int LITERAL_preserve = 26;
	int LITERAL_strip = 27;
	int LITERAL_ordered = 28;
	int LITERAL_unordered = 29;
	int LITERAL_empty = 30;
	int LITERAL_greatest = 31;
	int LITERAL_least = 32;
	int LITERAL_yes = 33;
	int LITERAL_no = 34;
	int LITERAL_at = 35;
	int COMMA = 36;
	int DOLLAR = 37;
	int COLON = 38;
	int LITERAL_external = 39;
	int LPAREN = 40;
	int RPAREN = 41;
	int LITERAL_as = 42;
	int LCURLY = 43;
	int RCURLY = 44;
	int LITERAL_for = 45;
	int LITERAL_let = 46;
	int LITERAL_some = 47;
	int LITERAL_every = 48;
	int LITERAL_typeswitch = 49;
	int LITERAL_if = 50;
	int LITERAL_try = 51;
	int LITERAL_catch = 52;
	int LITERAL_return = 53;
	int LITERAL_in = 54;
	int LITERAL_where = 55;
	int LITERAL_stable = 56;
	int LITERAL_by = 57;
	int LITERAL_ascending = 58;
	int LITERAL_descending = 59;
	int LITERAL_satisfies = 60;
	int LITERAL_case = 61;
	int LITERAL_then = 62;
	int LITERAL_else = 63;
	int LITERAL_or = 64;
	int LITERAL_and = 65;
	int LT = 66;
	int GT = 67;
	int LITERAL_eq = 68;
	int LITERAL_ne = 69;
	int LITERAL_lt = 70;
	int LITERAL_le = 71;
	int LITERAL_gt = 72;
	int LITERAL_ge = 73;
	int NEQ = 74;
	int GTEQ = 75;
	int LTEQ = 76;
	int LITERAL_is = 77;
	int LITERAL_to = 78;
	int PLUS = 79;
	int MINUS = 80;
	int STAR = 81;
	int LITERAL_div = 82;
	int LITERAL_idiv = 83;
	int LITERAL_mod = 84;
	int LITERAL_union = 85;
	int UNION = 86;
	int LITERAL_intersect = 87;
	int LITERAL_except = 88;
	int LITERAL_instance = 89;
	int LITERAL_of = 90;
	int LITERAL_treat = 91;
	int LITERAL_castable = 92;
	int LITERAL_cast = 93;
	int LITERAL_validate = 94;
	int SLASH = 95;
	int DSLASH = 96;
	int LITERAL_text = 97;
	int LITERAL_node = 98;
	int LITERAL_attribute = 99;
	int LITERAL_comment = 100;
	// "processing-instruction" = 101
	// "document-node" = 102
	// "schema-attribute" = 103
	// "schema-element" = 104
	int LITERAL_document = 105;
	int SELF = 106;
	int XML_COMMENT = 107;
	int XML_PI = 108;
	int AT = 109;
	int PARENT = 110;
	int LITERAL_child = 111;
	int LITERAL_self = 112;
	int LITERAL_descendant = 113;
	// "descendant-or-self" = 114
	int LITERAL_following = 115;
	// "following-sibling" = 116
	int LITERAL_parent = 117;
	int LITERAL_ancestor = 118;
	// "ancestor-or-self" = 119
	int LITERAL_preceding = 120;
	// "preceding-sibling" = 121
	int LPPAREN = 122;
	int RPPAREN = 123;
	int DOUBLE_LITERAL = 124;
	int DECIMAL_LITERAL = 125;
	int INTEGER_LITERAL = 126;
	int END_TAG_START = 127;
	int ELEMENT_CONTENT = 128;
	int QUOT = 129;
	int APOS = 130;
	int QUOT_ATTRIBUTE_CONTENT = 131;
	int APOS_ATTRIBUTE_CONTENT = 132;
	int XQDOC_COMMENT = 133;
	int XML_COMMENT_END = 134;
	int XML_PI_END = 135;
	int XML_CDATA = 136;
	int XML_CDATA_END = 137;
	int QUESTION = 138;
	int LITERAL_item = 139;
	int NCNAME = 140;
	int LITERAL_lax = 141;
	int LITERAL_strict = 142;
	int LITERAL_collection = 143;
	int LITERAL_define = 144;
	int LITERAL_isnot = 145;
	int LITERAL_nillable = 146;
	int LITERAL_type = 147;
	int LITERAL_validation = 148;
	int ANDEQ = 149;
	int OREQ = 150;
	int XML_PI_START = 151;
	int LETTER = 152;
	int DIGITS = 153;
	int HEX_DIGITS = 154;
	int NMSTART = 155;
	int NMCHAR = 156;
	int WS = 157;
	int EXPR_COMMENT = 158;
	int PRAGMA = 159;
	int MU_EXTENSION = 160;
	int EXTENSION_CONTENT = 161;
	int PRAGMA_QNAME = 162;
	int PREDEFINED_ENTITY_REF = 163;
	int CHAR_REF = 164;
	int NEXT_TOKEN = 165;
	int CHAR = 166;
	int BASECHAR = 167;
	int IDEOGRAPHIC = 168;
	int COMBINING_CHAR = 169;
	int DIGIT = 170;
	int EXTENDER = 171;
}
