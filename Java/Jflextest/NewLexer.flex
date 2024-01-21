package Jflextest;
import static Jflextest.Token.*;
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



"if"    { return IF;}
"then"	{ return THEN;}
"else"	{ return ELSE;}


{white} {/*Ignore*/}
"//" {/*Ignore*/}
"="	{return igual;}
"+"	{return suma;}
{L}	{lexeme =yytext(); return variable;}
{D} {lexeme=yytext(); return numero; }
[a-z]+ ([0-9]+ | [a-z]+)* {lexeme=yytext(); return ID;}
"*"	{return multiplicacion;}
"-" {return resta;}
"/"	{return division;}
. {return ERROR;}