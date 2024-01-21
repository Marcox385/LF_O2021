package Jflex;
import static Jflex.Token.*;
%%
%class NewLexer
%type Token

L = [a-z]+
D = [0-9]+
white=[ \t\r\n]+

%{
	public String lexeme;
%}
%%


[A-Z][a-z]+(((_([a-z]{1,3}))*)?_[A-Z][a-z]+){1,5} {lexeme=yytext(); return NOMBRE;}
(\([0-9][0-9]\)-)?([0-9][0-9]-){3}([0-9][0-9]) {lexeme=yytext(); return LOCAL;}
(\(?[0-9][0-9][0-9]\)?-)?([0-9][0-9]-?){5} {lexeme=yytext(); return CELULAR;}
[a-z]+:\/\/([a-z]*[0-9]*_*-*\.?)+(\/([A-z0-9_\-%\?]*)\/?)? {lexeme=yytext(); return URL;}
^[a-z]([a-z]*[0-9]*_*-*\.*)*@([a-z]\.?)+ {lexeme=yytext(); return EMAIL;}
(https?:\/\/)?www\.youtube\.com\/watch\?v=([A-z0-9])+ {lexeme=yytext(); return VIDEO;}
. {return ERROR;}