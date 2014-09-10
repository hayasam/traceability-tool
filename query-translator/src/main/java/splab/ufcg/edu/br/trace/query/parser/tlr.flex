import java_cup.runtime.*;

%%

%class Lexer
%cup
%column
%line


%{
	   private Symbol symbol(int type) {
		   return new Symbol(type, yyline, yycolumn);
   	   }
   	
 	   private Symbol symbol(int type, Object val) {
		   return new Symbol(type, yyline, yycolumn, val);
   	   }
%}

%%

//Navegacao / Operacao
"{" { return symbol(sym.LBRACKET); }
"}" { return symbol(sym.RBRACKET); }
"[" { return symbol(sym.LCOL); }
"]" { return symbol(sym.RCOL); }
"(" { return symbol(sym.LPAREN); }
")" { return symbol(sym.RPAREN); }
"|" { return symbol(sym.PIPE); }
"::" { return symbol(sym.DOUBLECOLON); }
".." { return symbol(sym.DOUBLEDOT); }
":" { return symbol(sym.COLON); } 
"." { return symbol(sym.DOT); }
"," { return symbol(sym.COMMA); }
";" { return symbol(sym.SEMICOLON); }
"->" { return symbol(sym.ARROW); }
"@" { return symbol(sym.AT); }

//RELOP
">=" { return symbol(sym.GE); }
"<=" { return symbol(sym.LE); }
"<>" { return symbol(sym.NE); }
"=" { return symbol(sym.EQ); }
">" { return symbol(sym.GT); }
"<" { return symbol(sym.LT); }

//Operadores aritmeticos
"-" { return symbol(sym.MINUS); }
"+" { return symbol(sym.PLUS); }
"/" { return symbol(sym.DIV); }
"*" { return symbol(sym.TIMES); }

//Operadores booleanos
"and" { return symbol(sym.AND); }
"or" { return symbol(sym.OR); }
"not" { return symbol(sym.NOT); }




// Tipos internos da linguagem
"requirement" { return symbol(sym.REQUIREMENT); }
"semantic" { return symbol(sym.SEMANTIC); }
"type" { return symbol(sym.TYPE); }
"query" { return symbol(sym.QUERY); }
"view_as" { return symbol(sym.VIEW); }
"artifact" { return symbol(sym.ARTIFACT); }
"result" { return symbol(sym.RESULT); }
"domain" { return symbol(sym.DOMAIN); }


//Identificador
[a-zA-Z_]([a-zA-Z0-9_])* { return symbol(sym.NAME, yytext()); }

//Numero
[0-9]([0-9])*(.[0-9]([0-9])*)?((e|E)("+"|_)?[0-9]([0-9])*)? { return symbol(sym.NUMBER, yytext()); }

//String
"'"([a-zA-Z][0-9]|`|\"|\'|\~|\!|\@|#|\$|\%|\^|&|\*|\(|\)|_|-|\+|=|\||\\|\]|\[|\}|\{|:|\;|\/|\?|.|,|<|>)*"'" { return symbol(sym.STRING, yytext()); }


//Vazio 
[ \r\n\t\f] { }
<<EOF>> { return symbol(sym.EOF); }


