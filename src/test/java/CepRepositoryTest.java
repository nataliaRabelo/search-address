import br.com.searchaddress.arquiteturabackend.exception.CepNotFoundException;
import br.com.searchaddress.arquiteturabackend.exception.InvalidCepException;
import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.repository.CepRepository;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author Natália Bruno Rabelo
 *
 * Classe responsável por implementar os testes unitários dos métodos de CepRepository.
 */
public class CepRepositoryTest {

    @InjectMocks
    private CepRepository cepRepository;

    @Mock
    private RestTemplate restTemplate;

    private CepModel cepModel1;
    private CepModel cepModel2;
    private CepModel cepModel3;

    private final String cep1 = "01001-000";
    private final String cleanCep1 = "01001000";
    private final String cep2 = "20010-010";
    private final String cleanCep2 = "20010010";
    private final String cep3 = "70297-400";
    private final String cleanCep3 = "70297400";

    private final String baseUrlCep1 = "https://viacep.com.br/ws/" + cleanCep1;
    private final String baseUrlCep2 = "https://viacep.com.br/ws/" + cleanCep2;
    private final String baseUrlCep3 = "https://viacep.com.br/ws/" + cleanCep3;

    private final String formatPath1 = "/json/";
    private final String formatPath2 = "json/?callback=";
    private final String formatPath3 = "/xml/";

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
     * Testa o método findByCepJson() da classe CepRepository.
     */
    @Test
    public void testFindByCepJson() {
        String url1 = baseUrlCep1 + formatPath1;
        String url2 = baseUrlCep2 + formatPath1;
        String url3 = baseUrlCep3 + formatPath1;

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

    /**
     * Testa o método findByCepJsonP() da classe CepRepository.
     */
    @Test
    public void testFindByCepJsonP() throws IOException, CepNotFoundException {
        String callback = "myCallback";
        String url1 = baseUrlCep1 + formatPath2 + callback;
        String url2 = baseUrlCep2 + formatPath2 + callback;
        String url3 = baseUrlCep3 + formatPath2 + callback;

        CepModel result1 = cepRepository.findByCepJsonP(cleanCep1, callback);
        CepModel result2 = cepRepository.findByCepJsonP(cleanCep2, callback);
        CepModel result3 = cepRepository.findByCepJsonP(cleanCep3, callback);

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

    /**
     * Testa o método findByCepXml() da classe CepRepository.
     */
    @Test
    public void testFindByCepXml() throws InvalidCepException {

        String url1 = baseUrlCep1 + formatPath3;
        String url2 = baseUrlCep2 + formatPath3;
        String url3 = baseUrlCep3 + formatPath3;

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