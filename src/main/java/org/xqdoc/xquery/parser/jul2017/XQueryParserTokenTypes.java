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
	int ARROW = 48;
	int BANG = 49;
	int LITERAL_for = 50;
	int LITERAL_let = 51;
	int LITERAL_some = 52;
	int LITERAL_every = 53;
	int LITERAL_switch = 54;
	int LITERAL_typeswitch = 55;
	int LITERAL_update = 56;
	int LITERAL_replace = 57;
	int LITERAL_value = 58;
	int LITERAL_insert = 59;
	int LITERAL_delete = 60;
	int LITERAL_rename = 61;
	int LITERAL_if = 62;
	int LITERAL_try = 63;
	int LITERAL_catch = 64;
	int LITERAL_with = 65;
	int LITERAL_into = 66;
	int LITERAL_preceding = 67;
	int LITERAL_following = 68;
	int LITERAL_return = 69;
	int LITERAL_in = 70;
	int LITERAL_where = 71;
	int LITERAL_stable = 72;
	int LITERAL_by = 73;
	int LITERAL_ascending = 74;
	int LITERAL_descending = 75;
	int LITERAL_satisfies = 76;
	int LITERAL_case = 77;
	int LITERAL_then = 78;
	int LITERAL_else = 79;
	int LITERAL_or = 80;
	int LITERAL_and = 81;
	int LT = 82;
	int GT = 83;
	int LITERAL_eq = 84;
	int LITERAL_ne = 85;
	int LITERAL_lt = 86;
	int LITERAL_le = 87;
	int LITERAL_gt = 88;
	int LITERAL_ge = 89;
	int NEQ = 90;
	int GTEQ = 91;
	int LTEQ = 92;
	int LITERAL_is = 93;
	int CONCAT = 94;
	int LITERAL_to = 95;
	int PLUS = 96;
	int MINUS = 97;
	int STAR = 98;
	int LITERAL_div = 99;
	int LITERAL_idiv = 100;
	int LITERAL_mod = 101;
	int LITERAL_union = 102;
	int UNION = 103;
	int LITERAL_intersect = 104;
	int LITERAL_except = 105;
	int LITERAL_instance = 106;
	int LITERAL_of = 107;
	int LITERAL_treat = 108;
	int LITERAL_castable = 109;
	int LITERAL_cast = 110;
	int LITERAL_validate = 111;
	int PRAGMA = 112;
	int SLASH = 113;
	int DSLASH = 114;
	int LITERAL_text = 115;
	int LITERAL_node = 116;
	int LITERAL_attribute = 117;
	int LITERAL_comment = 118;
	// "processing-instruction" = 119
	// "document-node" = 120
	// "schema-attribute" = 121
	// "schema-element" = 122
	// "array-node" = 123
	// "object-node" = 124
	int LITERAL_document = 125;
	int SELF = 126;
	int XML_COMMENT = 127;
	int XML_PI = 128;
	int AT = 129;
	int PARENT = 130;
	int LITERAL_child = 131;
	int LITERAL_self = 132;
	int LITERAL_descendant = 133;
	// "descendant-or-self" = 134
	// "following-sibling" = 135
	int LITERAL_parent = 136;
	int LITERAL_ancestor = 137;
	// "ancestor-or-self" = 138
	// "preceding-sibling" = 139
	int LPPAREN = 140;
	int RPPAREN = 141;
	// "number-node" = 142
	// "boolean-node" = 143
	// "null-node" = 144
	int DOUBLE_LITERAL = 145;
	int DECIMAL_LITERAL = 146;
	int INTEGER_LITERAL = 147;
	int END_TAG_START = 148;
	int ELEMENT_CONTENT = 149;
	int QUOT = 150;
	int APOS = 151;
	int QUOT_ATTRIBUTE_CONTENT = 152;
	int APOS_ATTRIBUTE_CONTENT = 153;
	int XQDOC_COMMENT = 154;
	int XML_COMMENT_END = 155;
	int XML_PI_END = 156;
	int XML_CDATA = 157;
	int XML_CDATA_END = 158;
	int QUESTION = 159;
	// "empty-sequence" = 160
	int LITERAL_item = 161;
	int NCNAME = 162;
	int LITERAL_lax = 163;
	int LITERAL_strict = 164;
	int ANDEQ = 165;
	int OREQ = 166;
	int XML_PI_START = 167;
	int LETTER = 168;
	int DIGITS = 169;
	int HEX_DIGITS = 170;
	int NMSTART = 171;
	int NMCHAR = 172;
	int WS = 173;
	int EXPR_COMMENT = 174;
	int PRAGMA_CONTENT = 175;
	int PRAGMA_QNAME = 176;
	int PREDEFINED_ENTITY_REF = 177;
	int CHAR_REF = 178;
	int NEXT_TOKEN = 179;
	int CHAR = 180;
	int BASECHAR = 181;
	int IDEOGRAPHIC = 182;
	int COMBINING_CHAR = 183;
	int DIGIT = 184;
	int EXTENDER = 185;
}
