// $ANTLR 2.7.7 (2006-11-01): "XQuery.g" -> "XQueryParser.java"$

	
/**  
 * Grammar definition for the November 2005 XQuery specification.
 */
	package org.xqdoc.xquery.parser.jan2007;

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
	int MOD = 42;
	int LPAREN = 43;
	int RPAREN = 44;
	int LITERAL_as = 45;
	int LCURLY = 46;
	int RCURLY = 47;
	int BANG = 48;
	int LITERAL_for = 49;
	int LITERAL_let = 50;
	int LITERAL_some = 51;
	int LITERAL_every = 52;
	int LITERAL_typeswitch = 53;
	int LITERAL_update = 54;
	int LITERAL_replace = 55;
	int LITERAL_value = 56;
	int LITERAL_insert = 57;
	int LITERAL_delete = 58;
	int LITERAL_rename = 59;
	int LITERAL_if = 60;
	int LITERAL_try = 61;
	int LITERAL_catch = 62;
	int LITERAL_with = 63;
	int LITERAL_into = 64;
	int LITERAL_preceding = 65;
	int LITERAL_following = 66;
	int LITERAL_return = 67;
	int LITERAL_in = 68;
	int LITERAL_where = 69;
	int LITERAL_stable = 70;
	int LITERAL_by = 71;
	int LITERAL_ascending = 72;
	int LITERAL_descending = 73;
	int LITERAL_satisfies = 74;
	int LITERAL_case = 75;
	int LITERAL_then = 76;
	int LITERAL_else = 77;
	int LITERAL_or = 78;
	int LITERAL_and = 79;
	int LT = 80;
	int GT = 81;
	int LITERAL_eq = 82;
	int LITERAL_ne = 83;
	int LITERAL_lt = 84;
	int LITERAL_le = 85;
	int LITERAL_gt = 86;
	int LITERAL_ge = 87;
	int NEQ = 88;
	int GTEQ = 89;
	int LTEQ = 90;
	int LITERAL_is = 91;
	int LITERAL_to = 92;
	int PLUS = 93;
	int MINUS = 94;
	int STAR = 95;
	int LITERAL_div = 96;
	int LITERAL_idiv = 97;
	int LITERAL_mod = 98;
	int LITERAL_union = 99;
	int UNION = 100;
	int LITERAL_intersect = 101;
	int LITERAL_except = 102;
	int LITERAL_instance = 103;
	int LITERAL_of = 104;
	int LITERAL_treat = 105;
	int LITERAL_castable = 106;
	int LITERAL_cast = 107;
	int LITERAL_validate = 108;
	int PRAGMA = 109;
	int SLASH = 110;
	int DSLASH = 111;
	int LITERAL_text = 112;
	int LITERAL_node = 113;
	int LITERAL_attribute = 114;
	int LITERAL_comment = 115;
	// "processing-instruction" = 116
	// "document-node" = 117
	// "schema-attribute" = 118
	// "schema-element" = 119
	int LITERAL_document = 120;
	int SELF = 121;
	int XML_COMMENT = 122;
	int XML_PI = 123;
	int AT = 124;
	int PARENT = 125;
	int LITERAL_child = 126;
	int LITERAL_self = 127;
	int LITERAL_descendant = 128;
	// "descendant-or-self" = 129
	// "following-sibling" = 130
	int LITERAL_parent = 131;
	int LITERAL_ancestor = 132;
	// "ancestor-or-self" = 133
	// "preceding-sibling" = 134
	int LPPAREN = 135;
	int RPPAREN = 136;
	int DOUBLE_LITERAL = 137;
	int DECIMAL_LITERAL = 138;
	int INTEGER_LITERAL = 139;
	int END_TAG_START = 140;
	int ELEMENT_CONTENT = 141;
	int QUOT = 142;
	int APOS = 143;
	int QUOT_ATTRIBUTE_CONTENT = 144;
	int APOS_ATTRIBUTE_CONTENT = 145;
	int XQDOC_COMMENT = 146;
	int XML_COMMENT_END = 147;
	int XML_PI_END = 148;
	int XML_CDATA = 149;
	int XML_CDATA_END = 150;
	int QUESTION = 151;
	// "empty-sequence" = 152
	int LITERAL_item = 153;
	int NCNAME = 154;
	int LITERAL_lax = 155;
	int LITERAL_strict = 156;
	int ANDEQ = 157;
	int OREQ = 158;
	int CONCAT = 159;
	int XML_PI_START = 160;
	int LETTER = 161;
	int DIGITS = 162;
	int HEX_DIGITS = 163;
	int NMSTART = 164;
	int NMCHAR = 165;
	int WS = 166;
	int EXPR_COMMENT = 167;
	int PRAGMA_CONTENT = 168;
	int PRAGMA_QNAME = 169;
	int PREDEFINED_ENTITY_REF = 170;
	int CHAR_REF = 171;
	int NEXT_TOKEN = 172;
	int CHAR = 173;
	int BASECHAR = 174;
	int IDEOGRAPHIC = 175;
	int COMBINING_CHAR = 176;
	int DIGIT = 177;
	int EXTENDER = 178;
}
