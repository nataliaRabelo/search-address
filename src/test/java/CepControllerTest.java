import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.searchaddress.arquiteturabackend.controller.CepController;
import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.service.CepService;
import br.com.searchaddress.arquiteturabackend.view.CepView;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CepControllerTest {

    private CepController controller;

    @Mock
    private CepService service;


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
    }


    @Test
    void testGetCepJson() throws Exception {
        // Given
        String cep = "01001000";
        final CepModel cepModel = new CepModel(cep, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");

        when(service.getCepJson(cep)).thenReturn(cepModel);

        // When
        final String response = controller.getCepJson(cep);

        // Then
        assertThat(ResponseEntity.ok(response).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ResponseEntity.ok(response).getBody()).isEqualTo(new CepView(cepModel).toJson());
    }

    @Test
    public void testGetCepJsonP() throws Exception {
        // Mocking the service
        String callBack = "myCall";
        String cep = "01001000";
        CepService cepService = mock(CepService.class);
        CepModel cepModel = new CepModel(cep, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        when(cepService.getCepJsonP(cep, callBack)).thenReturn(cepModel);

        // Testing the controller
        CepController cepController = new CepController();
        cepController.cepService = this.service;
        String response = cepController.getCepJsonP("01001000", "callback");

        // Checking the response
        String expectedResponse = "callback(" + new CepView(cepModel).toJson() + ");";
        assertEquals(expectedResponse, response);
    }


}
