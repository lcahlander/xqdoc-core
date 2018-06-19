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

public interface XQueryParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int LITERAL_xquery = 4;
	int LITERAL_version = 5;
	int LITERAL_module = 6;
	int LITERAL_namespace = 7;
	int EQ = 8;
	int LITERAL_declare = 9;
	int LITERAL_xmlspace = 10;
	int LITERAL_import = 11;
	int LITERAL_default = 12;
	int LITERAL_collation = 13;
	int LITERAL_element = 14;
	int LITERAL_function = 15;
	// "base-uri" = 16
	int LITERAL_variable = 17;
	int LITERAL_validation = 18;
	int LITERAL_schema = 19;
	int NCNAME = 20;
	int STRING_LITERAL = 21;
	int LITERAL_at = 22;
	int LITERAL_option = 23;
	int SEMICOLON = 24;
	int DOLLAR = 25;
	int LCURLY = 26;
	int RCURLY = 27;
	int LITERAL_external = 28;
	int COMMA = 29;
	int LITERAL_for = 30;
	int LITERAL_let = 31;
	int LITERAL_some = 32;
	int LITERAL_every = 33;
	int LITERAL_typeswitch = 34;
	int LPAREN = 35;
	int LITERAL_update = 36;
	int LITERAL_replace = 37;
	int LITERAL_value = 38;
	int LITERAL_insert = 39;
	int LITERAL_delete = 40;
	int LITERAL_rename = 41;
	int LITERAL_if = 42;
	int LITERAL_try = 43;
	int LITERAL_catch = 44;
	int RPAREN = 45;
	int LITERAL_as = 46;
	int LITERAL_with = 47;
	int LITERAL_into = 48;
	int LITERAL_preceding = 49;
	int LITERAL_following = 50;
	int LITERAL_return = 51;
	int LITERAL_in = 52;
	int COLON = 53;
	int LITERAL_where = 54;
	int LITERAL_stable = 55;
	int LITERAL_order = 56;
	int LITERAL_by = 57;
	int LITERAL_ascending = 58;
	int LITERAL_descending = 59;
	int LITERAL_empty = 60;
	int LITERAL_greatest = 61;
	int LITERAL_least = 62;
	int LITERAL_satisfies = 63;
	int LITERAL_case = 64;
	int LITERAL_then = 65;
	int LITERAL_else = 66;
	int LITERAL_or = 67;
	int LITERAL_and = 68;
	int LITERAL_instance = 69;
	int LITERAL_of = 70;
	int LITERAL_treat = 71;
	int LITERAL_castable = 72;
	int LITERAL_cast = 73;
	int LT = 74;
	int GT = 75;
	int LITERAL_eq = 76;
	int LITERAL_ne = 77;
	int LITERAL_lt = 78;
	int LITERAL_le = 79;
	int LITERAL_gt = 80;
	int LITERAL_ge = 81;
	int NEQ = 82;
	int GTEQ = 83;
	int LTEQ = 84;
	int LITERAL_is = 85;
	int LITERAL_to = 86;
	int PLUS = 87;
	int MINUS = 88;
	int STAR = 89;
	int LITERAL_div = 90;
	int LITERAL_idiv = 91;
	int LITERAL_mod = 92;
	int LITERAL_union = 93;
	int UNION = 94;
	int LITERAL_intersect = 95;
	int LITERAL_except = 96;
	int LITERAL_validate = 97;
	int SLASH = 98;
	int DSLASH = 99;
	int LITERAL_text = 100;
	int LITERAL_node = 101;
	int LITERAL_attribute = 102;
	int LITERAL_comment = 103;
	// "processing-instruction" = 104
	// "document-node" = 105
	int LITERAL_document = 106;
	int SELF = 107;
	int XML_COMMENT = 108;
	int XML_PI = 109;
	int LPPAREN = 110;
	int RPPAREN = 111;
	int LITERAL_global = 112;
	int LITERAL_context = 113;
	int AT = 114;
	int PARENT = 115;
	int LITERAL_child = 116;
	int LITERAL_self = 117;
	int LITERAL_descendant = 118;
	// "descendant-or-self" = 119
	// "following-sibling" = 120
	int LITERAL_parent = 121;
	int LITERAL_ancestor = 122;
	// "ancestor-or-self" = 123
	// "preceding-sibling" = 124
	int DOUBLE_LITERAL = 125;
	int DECIMAL_LITERAL = 126;
	int INTEGER_LITERAL = 127;
	int END_TAG_START = 128;
	int XQDOC_COMMENT = 129;
	int XML_CDATA = 130;
	int XML_CDATA_END = 131;
	int XML_PI_END = 132;
	int XML_COMMENT_END = 133;
	int ELEMENT_CONTENT = 134;
	int QUOT = 135;
	int APOS = 136;
	int QUOT_ATTRIBUTE_CONTENT = 137;
	int APOS_ATTRIBUTE_CONTENT = 138;
	int LITERAL_preserve = 139;
	int LITERAL_strip = 140;
	int QUESTION = 141;
	int LITERAL_item = 142;
	int LITERAL_nillable = 143;
	int LITERAL_type = 144;
	int LITERAL_lax = 145;
	int LITERAL_strict = 146;
	int LITERAL_skip = 147;
	int LITERAL_collection = 148;
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
	int PRAGMA_CONTENT = 160;
	int PRAGMA_QNAME = 161;
	int PREDEFINED_ENTITY_REF = 162;
	int CHAR_REF = 163;
	int NEXT_TOKEN = 164;
	int CHAR = 165;
	int BASECHAR = 166;
	int IDEOGRAPHIC = 167;
	int COMBINING_CHAR = 168;
	int DIGIT = 169;
	int EXTENDER = 170;
}
