import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.repository.CepRepository;
import br.com.searchaddress.arquiteturabackend.repository.entity.CepEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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


    @Test
    public void testFindByCepJson() {
        String cep = "01001000";
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        CepModel result = cepRepository.findByCepJson(cep);

        when(restTemplate.getForObject(url, CepModel.class)).thenReturn(result);
        assertNotNull(result);
        assertEquals(cep, result.cep);
    }

    @Test
    public void testFindByCepJsonP() throws IOException {
        String cep = "01001000";
        String callback = "callback";
        String url = "https://viacep.com.br/ws/" + cep + "/json/?callback=" + callback;

        String json = "{\"cep\":\"" + cep + "\"}";

        ResponseEntity<String> response = new ResponseEntity<>("callback(" + json + ")", HttpStatus.OK);

        when(restTemplate.exchange(url, HttpMethod.GET, null, String.class)).thenReturn(response);

        CepModel result = cepRepository.findByCepJsonP(cep, callback);

        assertNotNull(result);
        assertEquals(cep, result.cep);
    }

    @Test
    public void testFindByCepXml() {
        String cep = "01001000";
        String url = "https://viacep.com.br/ws/" + cep + "/xml/";

        CepEntity cepEntity = new CepEntity();

        ResponseEntity<CepEntity> response = new ResponseEntity<>(cepEntity, HttpStatus.OK);

        when(restTemplate.getForEntity(url, CepEntity.class)).thenReturn(response);

        CepModel result = cepRepository.findByCepXml(cep);

        assertNotNull(result);
        assertEquals(cep, result.cep);
    }

    @Test
    public void testFindByCepJson2() {
        CepEntity cepEntity = new CepEntity("01001000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP", "3550308", "1004", "11", "7107");

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(CepEntity.class)))
                .thenReturn(cepEntity);

        CepModel cepModel = cepRepository.findByCepJson("01001000");

        assertEquals("01001000", cepModel.cep);
        assertEquals("Praça da Sé", cepModel.rua);
        assertEquals("lado ímpar", cepModel.complemento);
        assertEquals("Sé", cepModel.bairro);
        assertEquals("São Paulo", cepModel.cidade);
        assertEquals("SP", cepModel.estado);
    }

    @Test
    public void testFindByCepJsonP2() {
        String callback = "myCallback";
        String jsonResponse = "myCallback({\"cep\":\"01001000\",\"logradouro\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"localidade\":\"São Paulo\",\"uf\":\"SP\",\"ibge\":\"3550308\",\"gia\":\"1004\",\"ddd\":\"11\",\"siafi\":\"7107\"});";
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.isNull(), Mockito.eq(String.class)))
                .thenReturn(new ResponseEntity<>(jsonResponse, HttpStatus.OK));

        CepModel cepModel = cepRepository.findByCepJsonP("01001000", callback);

        assertEquals("01001000", cepModel.cep);
        assertEquals("Praça da Sé", cepModel.rua);
        assertEquals("lado ímpar", cepModel.complemento);
        assertEquals("Sé", cepModel.bairro);
        assertEquals("São Paulo", cepModel.cidade);
        assertEquals("SP", cepModel.estado);
    }

    @Test
    public void testFindByCepXml2() {
        CepEntity cepEntity = new CepEntity("01001000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP", "3550308", "1004", "11", "7107");

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(CepEntity.class)))
                .thenReturn(cepEntity);

        CepModel cepModel = cepRepository.findByCepJson("01001000");

        assertEquals("01001000", cepModel.cep);
        assertEquals("Praça da Sé", cepModel.rua);
        assertEquals("lado ímpar", cepModel.complemento);
        assertEquals("Sé", cepModel.bairro);
        assertEquals("São Paulo", cepModel.cidade);
        assertEquals("SP", cepModel.estado);
    }
}