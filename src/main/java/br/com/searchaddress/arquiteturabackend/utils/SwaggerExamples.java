package br.com.searchaddress.arquiteturabackend.utils;

/**
 * @author Natália Bruno Rabelo.
 * Classe que armazena os exemplos para anotações do swagger da API.
 */
public class SwaggerExamples {

    /**
     * HomeController.
     * Página html utilizada para o redirecionamento ao Swagger da API.
     */
    public static final String REDIRECTPAGE = """
            <!DOCTYPE html>
            	<html lang="en">
            		<head>
            		<meta http-equiv="refresh" content="1; URL=/swagger-ui/">
            		</head>
            		<body bgcolor="#7FFFD4">
                    <center><h1><p>Você será redirecionado automaticamente, aguarde...<p>&#8987</p><a href="doxygen/html/index.html"</a></p></h1></center>
            		</body>
            	</html>
            """;

    public static final String JSONEXAMPLE = """
                {
                  "cep": string,
                  "logradouro": string,
                  "complemento": string,
                  "bairro": string,
                  "localidade": string,
                  "uf": string,
                  "ibge": string,
                  "gia": string,
                  "ddd": string,
                  "siafi": string
                }
            """;

    public static final String JSONPEXAMPLE = """
                callback_name({
                  "cep": string,
                  "logradouro": string,
                  "complemento": string,
                  "bairro": string,
                  "localidade": string,
                  "uf": string,
                  "ibge": string,
                  "gia": string,
                  "ddd": string,
                  "siafi": string
                });
            """;

    public static final String XMLEXAMPLE = """
                <?xml version="1.0" encoding="UTF-8"?>
                <xmlcep>
                    <cep>string</cep>
                    <logradouro>string</logradouro>
                    <complemento>string</complemento>
                    <bairro>string</bairro>
                    <localidade>string</localidade>
                    <uf>string</uf>
                    <ibge>string</ibge>
                    <gia>string</gia>
                    <ddd>string</ddd>
                    <siafi>string</siafi>
                </xmlcep>
            """;
}
