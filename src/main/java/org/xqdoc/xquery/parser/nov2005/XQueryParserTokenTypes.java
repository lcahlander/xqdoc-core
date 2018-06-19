// $ANTLR : "XQuery.g" -> "XQueryParser.java"$

	
/**  
 * Grammar definition for the November 2005 XQuery specification.
 */
	package org.xqdoc.xquery.parser.nov2005;

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
	// "boundary-space" = 12
	int LITERAL_default = 13;
	int LITERAL_collation = 14;
	// "base-uri" = 15
	int LITERAL_construction = 16;
	int LITERAL_ordering = 17;
	int LITERAL_order = 18;
	// "copy-namespaces" = 19
	int LITERAL_import = 20;
	int LITERAL_schema = 21;
	int LITERAL_element = 22;
	int LITERAL_function = 23;
	int LITERAL_variable = 24;
	int LITERAL_option = 25;
	int SEMICOLON = 26;
	int LITERAL_preserve = 27;
	int LITERAL_strip = 28;
	int LITERAL_ordered = 29;
	int LITERAL_unordered = 30;
	int LITERAL_empty = 31;
	int LITERAL_greatest = 32;
	int LITERAL_least = 33;
	// "no-preserve" = 34
	int COMMA = 35;
	int LITERAL_inherit = 36;
	// "no-inherit" = 37
	int LITERAL_at = 38;
	int DOLLAR = 39;
	int COLON = 40;
	int LITERAL_external = 41;
	int LPAREN = 42;
	int RPAREN = 43;
	int LITERAL_as = 44;
	int LCURLY = 45;
	int RCURLY = 46;
	int LITERAL_for = 47;
	int LITERAL_let = 48;
	int LITERAL_some = 49;
	int LITERAL_every = 50;
	int LITERAL_typeswitch = 51;
	int LITERAL_if = 52;
	int LITERAL_try = 53;
	int LITERAL_catch = 54;
	int LITERAL_return = 55;
	int LITERAL_in = 56;
	int LITERAL_where = 57;
	int LITERAL_stable = 58;
	int LITERAL_by = 59;
	int LITERAL_ascending = 60;
	int LITERAL_descending = 61;
	int LITERAL_satisfies = 62;
	int LITERAL_case = 63;
	int LITERAL_then = 64;
	int LITERAL_else = 65;
	int LITERAL_or = 66;
	int LITERAL_and = 67;
	int LT = 68;
	int GT = 69;
	int LITERAL_eq = 70;
	int LITERAL_ne = 71;
	int LITERAL_lt = 72;
	int LITERAL_le = 73;
	int LITERAL_gt = 74;
	int LITERAL_ge = 75;
	int NEQ = 76;
	int GTEQ = 77;
	int LTEQ = 78;
	int LITERAL_is = 79;
	int LITERAL_to = 80;
	int PLUS = 81;
	int MINUS = 82;
	int STAR = 83;
	int LITERAL_div = 84;
	int LITERAL_idiv = 85;
	int LITERAL_mod = 86;
	int LITERAL_union = 87;
	int UNION = 88;
	int LITERAL_intersect = 89;
	int LITERAL_except = 90;
	int LITERAL_instance = 91;
	int LITERAL_of = 92;
	int LITERAL_treat = 93;
	int LITERAL_castable = 94;
	int LITERAL_cast = 95;
	int LITERAL_validate = 96;
	int PRAGMA = 97;
	int SLASH = 98;
	int DSLASH = 99;
	int LITERAL_text = 100;
	int LITERAL_node = 101;
	int LITERAL_attribute = 102;
	int LITERAL_comment = 103;
	// "processing-instruction" = 104
	// "document-node" = 105
	// "schema-attribute" = 106
	// "schema-element" = 107
	int LITERAL_document = 108;
	int SELF = 109;
	int XML_COMMENT = 110;
	int XML_PI = 111;
	int AT = 112;
	int PARENT = 113;
	int LITERAL_child = 114;
	int LITERAL_self = 115;
	int LITERAL_descendant = 116;
	// "descendant-or-self" = 117
	int LITERAL_following = 118;
	// "following-sibling" = 119
	int LITERAL_parent = 120;
	int LITERAL_ancestor = 121;
	// "ancestor-or-self" = 122
	int LITERAL_preceding = 123;
	// "preceding-sibling" = 124
	int LPPAREN = 125;
	int RPPAREN = 126;
	int DOUBLE_LITERAL = 127;
	int DECIMAL_LITERAL = 128;
	int INTEGER_LITERAL = 129;
	int END_TAG_START = 130;
	int ELEMENT_CONTENT = 131;
	int QUOT = 132;
	int APOS = 133;
	int QUOT_ATTRIBUTE_CONTENT = 134;
	int APOS_ATTRIBUTE_CONTENT = 135;
	int XQDOC_COMMENT = 136;
	int XML_COMMENT_END = 137;
	int XML_PI_END = 138;
	int XML_CDATA = 139;
	int XML_CDATA_END = 140;
	int QUESTION = 141;
	// "empty-sequence" = 142
	int LITERAL_item = 143;
	int NCNAME = 144;
	int LITERAL_lax = 145;
	int LITERAL_strict = 146;
	int ANDEQ = 147;
	int OREQ = 148;
	int XML_PI_START = 149;
	int LETTER = 150;
	int DIGITS = 151;
	int HEX_DIGITS = 152;
	int NMSTART = 153;
	int NMCHAR = 154;
	int WS = 155;
	int EXPR_COMMENT = 156;
	int PRAGMA_CONTENT = 157;
	int PRAGMA_QNAME = 158;
	int PREDEFINED_ENTITY_REF = 159;
	int CHAR_REF = 160;
	int NEXT_TOKEN = 161;
	int CHAR = 162;
	int BASECHAR = 163;
	int IDEOGRAPHIC = 164;
	int COMBINING_CHAR = 165;
	int DIGIT = 166;
	int EXTENDER = 167;
}
