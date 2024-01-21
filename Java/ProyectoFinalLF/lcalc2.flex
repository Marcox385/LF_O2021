
   
import java_cup.runtime.*;
      
%%
%class Lexer

%line
%column
%cup
   

%{   
    
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {	  
    	return new Symbol(type, yyline, yycolumn, value);
    }
    
%}
   
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
letra = [A-Za-z]
digito= [0-9]
 
%%  
   /* YYINITIAL is the state */
   
<YYINITIAL> {
   
    ";"                { return symbol(sym.SEMI); }
    ","                { return symbol(sym.UNION); }
    "*"                { return symbol(sym.KLEENE); }
    "("                { return symbol(sym.LPAREN); }
    ")"                { return symbol(sym.RPAREN); }
    "+"				   { return symbol(sym.POS); }
    "$"				   { return symbol(sym.EPSILON); }
   
    {letra}      	   { return symbol(sym.SYMB, new String(yytext())); }
    {digito}      	   { return symbol(sym.DIGIT, new String(yytext())); }

   
    {WhiteSpace}       { /* do nothing */ }   
}

[^]                    { throw new Error("Illegal character <"+yytext()+">"); }
