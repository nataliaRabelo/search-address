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

    /**
     * Exemplo de resposta em getJson de CepController.
     */
    public static final String JSONEXAMPLE = """
                {
                  "cep": string,
                  "rua": string,
                  "complemento": string,
                  "bairro": string,
                  "cidade": string,
                  "estado": string,
                  "frete": string
                }
            """;

    /**
     * Exemplo de resposta em getJsonP de CepController.
     */
    public static final String JSONPEXAMPLE = """
                callback_name({
                  "cep": string,
                  "rua": string,
                  "complemento": string,
                  "bairro": string,
                  "cidade": string,
                  "estado": string,
                  "frete": string
                });
            """;

    /**
     * Exemplo de resposta em getXml de CepController.
     */
    public static final String XMLEXAMPLE = """
                <cep>
                  <cep>string</cep>
                  <rua>string</rua>
                  <complemento>string</complemento>
                  <bairro>string</bairro>
                  <cidade>string</cidade>
                  <estado>string</estado>
                  <frete>string</frete>
                </cep>
            """;
}
