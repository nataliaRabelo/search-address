import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.searchaddress.arquiteturabackend.controller.CepController;
import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.service.CepService;
import br.com.searchaddress.arquiteturabackend.view.CepView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Natália Bruno Rabelo
 *
 * Classe responsável por implementar os testes unitários dos métodos de CepController.
 */
class CepControllerTest {

    @InjectMocks
    private CepController controller;

    @Mock
    private CepService service;

    private CepModel cepModel1;
    private CepModel cepModel2;
    private CepModel cepModel3;

    private final String cep1 = "01001-000";
    private final String cleanCep1 = "01001000";
    private final String cep2 = "20010-010";
    private final String cleanCep2 = "20010010";
    private final String cep3 = "70297-400";
    private final String cleanCep3 = "70297400";

    /**
     * Configura objetos necessários para a execução dos testes.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cepModel1 = new CepModel(cep1, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        cepModel2 = new CepModel(cep2, "Praça Quinze de Novembro", "", "Centro", "Rio de Janeiro", "RJ");
        cepModel3 = new CepModel(cep3, "EQS 414/415", "", "Asa Sul", "Brasília", "DF");
    }

    /**
     * Testa o método GetCepXml de CepController.
     * @throws Exception exceção lançada quando cep não foi encontrado.
     */
    @Test
    public void testGetCepXml() throws Exception {
        // Given

        when(service.getCepXml(cleanCep1)).thenReturn(cepModel1);
        when(service.getCepXml(cleanCep2)).thenReturn(cepModel2);
        when(service.getCepXml(cleanCep3)).thenReturn(cepModel3);

        // When
        String response1 = controller.getCepXml(cep1);
        String response2 = controller.getCepXml(cep2);
        String response3 = controller.getCepXml(cep3);

        // Then
        assertThat(ResponseEntity.ok(response1).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response1).getBody()).isEqualTo(new CepView(cepModel1).toXml());
        assertEquals("<cep>\n" +
                "  <cep>01001-000</cep>\n" +
                "  <rua>Praça da Sé</rua>\n" +
                "  <complemento>lado ímpar</complemento>\n" +
                "  <bairro>Sé</bairro>\n" +
                "  <cidade>São Paulo</cidade>\n" +
                "  <estado>SP</estado>\n" +
                "  <frete>7.85</frete>\n" +
                "</cep>", response1);

        assertThat(ResponseEntity.ok(response2).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response2).getBody()).isEqualTo(new CepView(cepModel2).toXml());
        assertEquals("<cep>\n" +
                "  <cep>20010-010</cep>\n" +
                "  <rua>Praça Quinze de Novembro</rua>\n" +
                "  <complemento></complemento>\n" +
                "  <bairro>Centro</bairro>\n" +
                "  <cidade>Rio de Janeiro</cidade>\n" +
                "  <estado>RJ</estado>\n" +
                "  <frete>7.85</frete>\n" +
                "</cep>", response2);

        assertThat(ResponseEntity.ok(response3).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response3).getBody()).isEqualTo(new CepView(cepModel3).toXml());
        assertEquals("<cep>\n" +
                "  <cep>70297-400</cep>\n" +
                "  <rua>EQS 414/415</rua>\n" +
                "  <complemento></complemento>\n" +
                "  <bairro>Asa Sul</bairro>\n" +
                "  <cidade>Brasília</cidade>\n" +
                "  <estado>DF</estado>\n" +
                "  <frete>12.50</frete>\n" +
                "</cep>", response3);
    }

    /**
     * Testa o método GetCepJson de CepController.
     * @throws Exception exceção lançada quando cep não foi encontrado.
     */
    @Test
    public void testGetCepJson() throws Exception {
        // Given

        when(service.getCepJson(cleanCep1)).thenReturn(cepModel1);
        when(service.getCepJson(cleanCep2)).thenReturn(cepModel2);
        when(service.getCepJson(cleanCep3)).thenReturn(cepModel3);

        // When
        String response1 = controller.getCepJson(cep1);
        String response2 = controller.getCepJson(cep2);
        String response3 = controller.getCepJson(cep3);

        // Then
        assertThat(ResponseEntity.ok(response1).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response1).getBody()).isEqualTo(new CepView(cepModel1).toJson());
        assertEquals("{\"cep\":\"01001-000\",\"rua\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"cidade\":\"São Paulo\",\"estado\":\"SP\",\"frete\":\"7.85\"}", response1);

        assertThat(ResponseEntity.ok(response2).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response2).getBody()).isEqualTo(new CepView(cepModel2).toJson());
        assertEquals("{\"cep\":\"20010-010\",\"rua\":\"Praça Quinze de Novembro\",\"complemento\":\"\",\"bairro\":\"Centro\",\"cidade\":\"Rio de Janeiro\",\"estado\":\"RJ\",\"frete\":\"7.85\"}", response2);

        assertThat(ResponseEntity.ok(response3).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response3).getBody()).isEqualTo(new CepView(cepModel3).toJson());
        assertEquals("{\"cep\":\"70297-400\",\"rua\":\"EQS 414/415\",\"complemento\":\"\",\"bairro\":\"Asa Sul\",\"cidade\":\"Brasília\",\"estado\":\"DF\",\"frete\":\"12.50\"}", response3);
    }

    /**
     * Testa o método GetCepJsonP de CepController.
     * @throws Exception exceção lançada quando cep não foi encontrado.
     */
    @Test
    public void testGetCepJsonP() throws Exception {
        String callBack = "myCall";

        when(service.getCepJsonP(cleanCep1, callBack)).thenReturn(cepModel1);
        when(service.getCepJsonP(cleanCep2, callBack)).thenReturn(cepModel2);
        when(service.getCepJsonP(cleanCep3, callBack)).thenReturn(cepModel3);

        String response1 = controller.getCepJsonP(cep1, callBack);
        String response2 = controller.getCepJsonP(cep2, callBack);
        String response3 = controller.getCepJsonP(cep3, callBack);

        assertThat(ResponseEntity.ok(response1).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response1).getBody()).isEqualTo(new CepView(cepModel1).toJsonP(callBack));
        assertEquals(callBack + "({\"cep\":\"01001-000\",\"rua\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"cidade\":\"São Paulo\",\"estado\":\"SP\",\"frete\":\"7.85\"})", response1);

        assertThat(ResponseEntity.ok(response2).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response2).getBody()).isEqualTo(new CepView(cepModel2).toJsonP(callBack));
        assertEquals(callBack + "({\"cep\":\"20010-010\",\"rua\":\"Praça Quinze de Novembro\",\"complemento\":\"\",\"bairro\":\"Centro\",\"cidade\":\"Rio de Janeiro\",\"estado\":\"RJ\",\"frete\":\"7.85\"})", response2);

        assertThat(ResponseEntity.ok(response3).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response3).getBody()).isEqualTo(new CepView(cepModel3).toJsonP(callBack));
        assertEquals(callBack + "({\"cep\":\"70297-400\",\"rua\":\"EQS 414/415\",\"complemento\":\"\",\"bairro\":\"Asa Sul\",\"cidade\":\"Brasília\",\"estado\":\"DF\",\"frete\":\"12.50\"})", response3);
    }


}
