import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.searchaddress.arquiteturabackend.controller.CepController;
import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.service.CepService;
import br.com.searchaddress.arquiteturabackend.view.CepView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CepControllerTest {
    @InjectMocks
    private CepController controller;

    @Mock
    private CepService service;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCepXml() throws Exception {
        // Given
        String cep = "01001000";
        final CepModel cepModel = new CepModel(cep, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");

        when(service.getCepXml(cep)).thenReturn(cepModel);

        // When
        final String response = controller.getCepXml(cep);

        // Then
        assertThat(ResponseEntity.ok(response).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response).getBody()).isEqualTo(new CepView(cepModel).toXml());
        assertEquals("<cep>\n" +
                "  <cep>01001000</cep>\n" +
                "  <rua>Praça da Sé</rua>\n" +
                "  <complemento>lado ímpar</complemento>\n" +
                "  <bairro>Sé</bairro>\n" +
                "  <cidade>São Paulo</cidade>\n" +
                "  <estado>SP</estado>\n" +
                "  <frete>7.85</frete>\n" +
                "</cep>", response);
    }


    @Test
    void testGetCepJson() throws Exception {
        // Given
        String cep = "01001-000";
        final CepModel cepModel = new CepModel(cep, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");

        when(service.getCepJson(cep)).thenReturn(cepModel);

        // When
        String response = controller.getCepJson(cep);

        // Then
        assertThat(ResponseEntity.ok(response).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response).getBody()).isEqualTo(new CepView(cepModel).toJson());
        assertEquals("{\"cep\":\"01001-000\",\"rua\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"cidade\":\"São Paulo\",\"estado\":\"SP\",\"frete\":\"7.85\"}", response);
    }

    @Test
    public void testGetCepJsonP() throws Exception {
        String callBack = "myCall";
        String cep = "01001-000";
        CepModel cepModel = new CepModel(cep, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");

        when(service.getCepJsonP(cep, callBack)).thenReturn(cepModel);

        String response = controller.getCepJsonP(cep, callBack);

        assertThat(ResponseEntity.ok(response).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response).getBody()).isEqualTo(new CepView(cepModel).toJsonP(callBack));
        assertEquals(callBack + "({\"cep\":\"01001-000\",\"rua\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"cidade\":\"São Paulo\",\"estado\":\"SP\",\"frete\":\"7.85\"})", response);
    }


}
