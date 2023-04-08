import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.repository.CepRepository;
import br.com.searchaddress.arquiteturabackend.repository.entity.CepEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

public class CepRepositoryTest {

    @InjectMocks
    private CepRepository cepRepository;

    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByCepJson() {
        String cep1 = "01001-000";
        String cleanCep1 = "01001000";
        String cep2 = "20010-010";
        String cleanCep2 = "20010010";
        String cep3 = "70297-400";
        String cleanCep3 = "70297400";
        String url1 = "https://viacep.com.br/ws/" + cleanCep1 + "/json/";
        String url2 = "https://viacep.com.br/ws/" + cleanCep2 + "/json/";
        String url3 = "https://viacep.com.br/ws/" + cleanCep3 + "/json/";

        CepModel cepModel1 = new CepModel(cep1, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        CepModel cepModel2 = new CepModel(cep2, "Praça Quinze de Novembro", "", "Centro", "Rio de Janeiro", "RJ");
        CepModel cepModel3 = new CepModel(cep3, "EQS 414/415", "", "Asa Sul", "Brasília", "DF");

        CepModel result1 = cepRepository.findByCepJson(cleanCep1);
        CepModel result2 = cepRepository.findByCepJson(cleanCep2);
        CepModel result3 = cepRepository.findByCepJson(cleanCep3);

        when(restTemplate.getForObject(url1, CepModel.class)).thenReturn(result1);
        assertNotNull(result1);
        assertEquals(cepModel1.cep, result1.cep);
        assertEquals(cepModel1.complemento, result1.complemento);
        assertEquals(cepModel1.bairro, result1.bairro);
        assertEquals(cepModel1.cidade, result1.cidade);
        assertEquals(cepModel1.estado, result1.estado);
        assertEquals(cepModel1.frete, result1.frete);

        when(restTemplate.getForObject(url2, CepModel.class)).thenReturn(result2);
        assertNotNull(result2);
        assertEquals(cepModel2.cep, result2.cep);
        assertEquals(cepModel2.complemento, result2.complemento);
        assertEquals(cepModel2.bairro, result2.bairro);
        assertEquals(cepModel2.cidade, result2.cidade);
        assertEquals(cepModel2.estado, result2.estado);
        assertEquals(cepModel2.frete, result2.frete);

        when(restTemplate.getForObject(url3, CepModel.class)).thenReturn(result3);
        assertNotNull(result3);
        assertEquals(cepModel3.cep, result3.cep);
        assertEquals(cepModel3.complemento, result3.complemento);
        assertEquals(cepModel3.bairro, result3.bairro);
        assertEquals(cepModel3.cidade, result3.cidade);
        assertEquals(cepModel3.estado, result3.estado);
        assertEquals(cepModel3.frete, result3.frete);
    }

    @Test
    public void testFindByCepJsonP() throws IOException {
        String callback = "callback";
        String cep1 = "01001-000";
        String cleanCep1 = "01001000";
        String cep2 = "20010-010";
        String cleanCep2 = "20010010";
        String cep3 = "70297-400";
        String cleanCep3 = "70297400";
        String url1 = "https://viacep.com.br/ws/" + cleanCep1 + "/json/?callback=" + callback;
        String url2 = "https://viacep.com.br/ws/" + cleanCep2 + "/json/?callback=" + callback;
        String url3 = "https://viacep.com.br/ws/" + cleanCep3 + "/json/?callback=" + callback;

        CepModel cepModel1 = new CepModel(cep1, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        CepModel cepModel2 = new CepModel(cep2, "Praça Quinze de Novembro", "", "Centro", "Rio de Janeiro", "RJ");
        CepModel cepModel3 = new CepModel(cep3, "EQS 414/415", "", "Asa Sul", "Brasília", "DF");

        CepModel result1 = cepRepository.findByCepXml(cleanCep1);
        CepModel result2 = cepRepository.findByCepXml(cleanCep2);
        CepModel result3 = cepRepository.findByCepXml(cleanCep3);

        when(restTemplate.getForObject(url1, CepModel.class)).thenReturn(result1);
        assertNotNull(result1);
        assertEquals(cepModel1.cep, result1.cep);
        assertEquals(cepModel1.complemento, result1.complemento);
        assertEquals(cepModel1.bairro, result1.bairro);
        assertEquals(cepModel1.cidade, result1.cidade);
        assertEquals(cepModel1.estado, result1.estado);
        assertEquals(cepModel1.frete, result1.frete);

        when(restTemplate.getForObject(url2, CepModel.class)).thenReturn(result2);
        assertNotNull(result2);
        assertEquals(cepModel2.cep, result2.cep);
        assertEquals(cepModel2.complemento, result2.complemento);
        assertEquals(cepModel2.bairro, result2.bairro);
        assertEquals(cepModel2.cidade, result2.cidade);
        assertEquals(cepModel2.estado, result2.estado);
        assertEquals(cepModel2.frete, result2.frete);

        when(restTemplate.getForObject(url3, CepModel.class)).thenReturn(result3);
        assertNotNull(result3);
        assertEquals(cepModel3.cep, result3.cep);
        assertEquals(cepModel3.complemento, result3.complemento);
        assertEquals(cepModel3.bairro, result3.bairro);
        assertEquals(cepModel3.cidade, result3.cidade);
        assertEquals(cepModel3.estado, result3.estado);
        assertEquals(cepModel3.frete, result3.frete);
    }

    @Test
    public void testFindByCepXml() {
        String cep1 = "01001-000";
        String cleanCep1 = "01001000";
        String cep2 = "20010-010";
        String cleanCep2 = "20010010";
        String cep3 = "70297-400";
        String cleanCep3 = "70297400";
        String url1 = "https://viacep.com.br/ws/" + cleanCep1 + "/xml/";
        String url2 = "https://viacep.com.br/ws/" + cleanCep2 + "/xml/";
        String url3 = "https://viacep.com.br/ws/" + cleanCep3 + "/xml/";

        CepModel cepModel1 = new CepModel(cep1, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        CepModel cepModel2 = new CepModel(cep2, "Praça Quinze de Novembro", "", "Centro", "Rio de Janeiro", "RJ");
        CepModel cepModel3 = new CepModel(cep3, "EQS 414/415", "", "Asa Sul", "Brasília", "DF");

        CepModel result1 = cepRepository.findByCepXml(cleanCep1);
        CepModel result2 = cepRepository.findByCepXml(cleanCep2);
        CepModel result3 = cepRepository.findByCepXml(cleanCep3);

        when(restTemplate.getForObject(url1, CepModel.class)).thenReturn(result1);
        assertNotNull(result1);
        assertEquals(cepModel1.cep, result1.cep);
        assertEquals(cepModel1.complemento, result1.complemento);
        assertEquals(cepModel1.bairro, result1.bairro);
        assertEquals(cepModel1.cidade, result1.cidade);
        assertEquals(cepModel1.estado, result1.estado);
        assertEquals(cepModel1.frete, result1.frete);

        when(restTemplate.getForObject(url2, CepModel.class)).thenReturn(result2);
        assertNotNull(result2);
        assertEquals(cepModel2.cep, result2.cep);
        assertEquals(cepModel2.complemento, result2.complemento);
        assertEquals(cepModel2.bairro, result2.bairro);
        assertEquals(cepModel2.cidade, result2.cidade);
        assertEquals(cepModel2.estado, result2.estado);
        assertEquals(cepModel2.frete, result2.frete);

        when(restTemplate.getForObject(url3, CepModel.class)).thenReturn(result3);
        assertNotNull(result3);
        assertEquals(cepModel3.cep, result3.cep);
        assertEquals(cepModel3.complemento, result3.complemento);
        assertEquals(cepModel3.bairro, result3.bairro);
        assertEquals(cepModel3.cidade, result3.cidade);
        assertEquals(cepModel3.estado, result3.estado);
        assertEquals(cepModel3.frete, result3.frete);
    }
}