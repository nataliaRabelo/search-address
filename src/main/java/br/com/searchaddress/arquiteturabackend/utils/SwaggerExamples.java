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
    public static final String REDIRECTPAGE = "<!DOCTYPE html>\n" +
            "            \t<html lang=\"en\">\n" +
            "            \t\t<head>\n" +
            "            \t\t<meta http-equiv=\"refresh\" content=\"1; URL=/swagger-ui/\">\n" +
            "            \t\t</head>\n" +
            "            \t\t<body bgcolor=\"#7FFFD4\">\n" +
            "                    <center><h1><p>Você será redirecionado automaticamente, aguarde...<p>&#8987</p><a href=\"doxygen/html/index.html\"</a></p></h1></center>\n" +
            "            \t\t</body>\n" +
            "            \t</html>";

    /**
     * Exemplo de resposta em getJson de CepController.
     */
    public static final String JSONEXAMPLE = "                {\n" +
            "                  \"cep\": string,\n" +
            "                  \"rua\": string,\n" +
            "                  \"complemento\": string,\n" +
            "                  \"bairro\": string,\n" +
            "                  \"cidade\": string,\n" +
            "                  \"estado\": string,\n" +
            "                  \"frete\": string\n" +
            "                }";

    /**
     * Exemplo de resposta em getJsonP de CepController.
     */
    public static final String JSONPEXAMPLE = "callback_name({\n" +
            "                  \"cep\": string,\n" +
            "                  \"rua\": string,\n" +
            "                  \"complemento\": string,\n" +
            "                  \"bairro\": string,\n" +
            "                  \"cidade\": string,\n" +
            "                  \"estado\": string,\n" +
            "                  \"frete\": string\n" +
            "                });";

    /**
     * Exemplo de resposta em getXml de CepController.
     */
    public static final String XMLEXAMPLE = "                <cep>\n" +
            "                  <cep>string</cep>\n" +
            "                  <rua>string</rua>\n" +
            "                  <complemento>string</complemento>\n" +
            "                  <bairro>string</bairro>\n" +
            "                  <cidade>string</cidade>\n" +
            "                  <estado>string</estado>\n" +
            "                  <frete>string</frete>\n" +
            "                </cep>";
}
