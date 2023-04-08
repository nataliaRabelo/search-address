import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.searchaddress.arquiteturabackend.view.CepView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.searchaddress.arquiteturabackend.model.CepModel;

/**
 * @author Natália Bruno Rabelo
 *
 * Classe responsável por implementar os testes unitários dos métodos de CepView.
 */
public class CepViewTest {

    private CepModel cepModel1;
    private CepModel cepModel2;
    private CepModel cepModel3;

    /**
     * Configura objetos necessários para a execução dos testes.
     */
    @BeforeEach
    void setUp() {
        cepModel1 = new CepModel("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        cepModel2 = new CepModel("20010-010", "Praça Quinze de Novembro", "", "Centro", "Rio de Janeiro", "RJ");
        cepModel3 = new CepModel("70297-400", "EQS 414/415", "", "Asa Sul", "Brasília", "DF");
    }

    /**
     * Deve retornar a view em formato JSON corretamente.
     */
    @Test
    void shouldReturnJsonView() {
        CepView cepView1 = new CepView(cepModel1);
        CepView cepView2 = new CepView(cepModel2);
        CepView cepView3 = new CepView(cepModel3);

        String expected1 = "{\"cep\":\"01001-000\",\"rua\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"cidade\":\"São Paulo\",\"estado\":\"SP\",\"frete\":\"7.85\"}";
        String actual1 = cepView1.toJson();
        assertEquals(expected1, actual1);

        String expected2 = "{\"cep\":\"20010-010\",\"rua\":\"Praça Quinze de Novembro\",\"complemento\":\"\",\"bairro\":\"Centro\",\"cidade\":\"Rio de Janeiro\",\"estado\":\"RJ\",\"frete\":\"7.85\"}";
        String actual2 = cepView2.toJson();
        assertEquals(expected2, actual2);

        String expected3 = "{\"cep\":\"70297-400\",\"rua\":\"EQS 414/415\",\"complemento\":\"\",\"bairro\":\"Asa Sul\",\"cidade\":\"Brasília\",\"estado\":\"DF\",\"frete\":\"12.50\"}";
        String actual3 = cepView3.toJson();
        assertEquals(expected3, actual3);
    }

    /**
     * Deve retornar a view em formato XML corretamente.
     */
    @Test
    void shouldReturnXmlView() {
        CepView cepView1 = new CepView(cepModel1);
        CepView cepView2 = new CepView(cepModel2);
        CepView cepView3 = new CepView(cepModel3);

        String expected1 = "<cep>\n" +
                "  <cep>01001-000</cep>\n" +
                "  <rua>Praça da Sé</rua>\n" +
                "  <complemento>lado ímpar</complemento>\n" +
                "  <bairro>Sé</bairro>\n" +
                "  <cidade>São Paulo</cidade>\n" +
                "  <estado>SP</estado>\n" +
                "  <frete>7.85</frete>\n" +
                "</cep>";
        String actual1 = cepView1.toXml();
        assertEquals(expected1, actual1);

        String expected2 = "<cep>\n" +
                "  <cep>20010-010</cep>\n" +
                "  <rua>Praça Quinze de Novembro</rua>\n" +
                "  <complemento></complemento>\n" +
                "  <bairro>Centro</bairro>\n" +
                "  <cidade>Rio de Janeiro</cidade>\n" +
                "  <estado>RJ</estado>\n" +
                "  <frete>7.85</frete>\n" +
                "</cep>";
        String actual2 = cepView2.toXml();
        assertEquals(expected2, actual2);

        String expected3 = "<cep>\n" +
                "  <cep>70297-400</cep>\n" +
                "  <rua>EQS 414/415</rua>\n" +
                "  <complemento></complemento>\n" +
                "  <bairro>Asa Sul</bairro>\n" +
                "  <cidade>Brasília</cidade>\n" +
                "  <estado>DF</estado>\n" +
                "  <frete>12.50</frete>\n" +
                "</cep>";
        String actual3 = cepView3.toXml();
        assertEquals(expected3, actual3);
    }

    /**
     * Deve retornar a view em formato JSONP corretamente.
     */
    @Test
    void shouldReturnJsonpView() {
        CepView cepView1 = new CepView(cepModel1);
        CepView cepView2 = new CepView(cepModel2);
        CepView cepView3 = new CepView(cepModel3);

        String expected1 = "myCallback({\"cep\":\"01001-000\",\"rua\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"cidade\":\"São Paulo\",\"estado\":\"SP\",\"frete\":\"7.85\"})";
        String actual1 = cepView1.toJsonP("myCallback");
        assertEquals(expected1, actual1);

        String expected2 = "myCallback({\"cep\":\"20010-010\",\"rua\":\"Praça Quinze de Novembro\",\"complemento\":\"\",\"bairro\":\"Centro\",\"cidade\":\"Rio de Janeiro\",\"estado\":\"RJ\",\"frete\":\"7.85\"})";
        String actual2 = cepView2.toJsonP("myCallback");
        assertEquals(expected2, actual2);

        String expected3 = "myCallback({\"cep\":\"70297-400\",\"rua\":\"EQS 414/415\",\"complemento\":\"\",\"bairro\":\"Asa Sul\",\"cidade\":\"Brasília\",\"estado\":\"DF\",\"frete\":\"12.50\"})";
        String actual3 = cepView3.toJsonP("myCallback");
        assertEquals(expected3, actual3);
    }
}
