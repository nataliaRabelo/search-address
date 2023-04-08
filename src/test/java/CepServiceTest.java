import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.repository.CepRepository;
import br.com.searchaddress.arquiteturabackend.service.CepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Natália Bruno Rabelo
 *
 * Classe responsável por implementar os testes unitários dos métodos de CepService.
 */
@ExtendWith(MockitoExtension.class)
class CepServiceTest {

    @InjectMocks
    private CepService cepService;

    @Mock
    private CepRepository cepRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Testa o método GetCepJson de CepService.
     * @throws Exception exceção lançada quando cep não foi encontrado.
     */
    @Test
    void testGetCepJson() throws Exception {
        // Given
        String cep1 = "01001-000";
        String cleanCep1 = "01001000";
        String cep2 = "20010-010";
        String cleanCep2 = "20010010";
        String cep3 = "70297-400";
        String cleanCep3 = "70297400";

        CepModel cepModel1 = new CepModel(cep1, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        CepModel cepModel2 = new CepModel(cep2, "Praça Quinze de Novembro", "", "Centro", "Rio de Janeiro", "RJ");
        CepModel cepModel3 = new CepModel(cep3, "EQS 414/415", "", "Asa Sul", "Brasília", "DF");

        when(cepRepository.findByCepJson(cleanCep1)).thenReturn(cepModel1);
        when(cepRepository.findByCepJson(cleanCep2)).thenReturn(cepModel2);
        when(cepRepository.findByCepJson(cleanCep3)).thenReturn(cepModel3);

        // When
        CepModel result1 = cepService.getCepJson(cleanCep1);
        CepModel result2 = cepService.getCepJson(cleanCep2);
        CepModel result3 = cepService.getCepJson(cleanCep3);

        // Then
        assertEquals(cepModel1, result1);
        assertEquals(cepModel2, result2);
        assertEquals(cepModel3, result3);
    }

    /**
     * Testa o método GetCepJsonP de CepService.
     * @throws Exception exceção lançada quando cep não foi encontrado.
     */
    @Test
    void testGetCepJsonP() throws Exception {
        // Given
        String callback = "myCallback";
        String cep1 = "01001-000";
        String cleanCep1 = "01001000";
        String cep2 = "20010-010";
        String cleanCep2 = "20010010";
        String cep3 = "70297-400";
        String cleanCep3 = "70297400";

        CepModel cepModel1 = new CepModel(cep1, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        CepModel cepModel2 = new CepModel(cep2, "Praça Quinze de Novembro", "", "Centro", "Rio de Janeiro", "RJ");
        CepModel cepModel3 = new CepModel(cep3, "EQS 414/415", "", "Asa Sul", "Brasília", "DF");

        when(cepRepository.findByCepJsonP(cleanCep1, callback)).thenReturn(cepModel1);
        when(cepRepository.findByCepJsonP(cleanCep2, callback)).thenReturn(cepModel2);
        when(cepRepository.findByCepJsonP(cleanCep3, callback)).thenReturn(cepModel3);

        // When
        CepModel result1 = cepService.getCepJsonP(cleanCep1, callback);
        CepModel result2 = cepService.getCepJsonP(cleanCep2, callback);
        CepModel result3 = cepService.getCepJsonP(cleanCep3, callback);

        // Then
        assertEquals(cepModel1, result1);
        assertEquals(cepModel2, result2);
        assertEquals(cepModel3, result3);
    }

    /**
     * Testa o método GetCepXml de CepService.
     * @throws Exception exceção lançada quando cep não foi encontrado.
     */
    @Test
    void testGetCepXml() throws Exception {
        // Given
        String cep1 = "01001-000";
        String cleanCep1 = "01001000";
        String cep2 = "20010-010";
        String cleanCep2 = "20010010";
        String cep3 = "70297-400";
        String cleanCep3 = "70297400";

        CepModel cepModel1 = new CepModel(cep1, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        CepModel cepModel2 = new CepModel(cep2, "Praça Quinze de Novembro", "", "Centro", "Rio de Janeiro", "RJ");
        CepModel cepModel3 = new CepModel(cep3, "EQS 414/415", "", "Asa Sul", "Brasília", "DF");

        when(cepRepository.findByCepXml(cleanCep1)).thenReturn(cepModel1);
        when(cepRepository.findByCepXml(cleanCep2)).thenReturn(cepModel2);
        when(cepRepository.findByCepXml(cleanCep3)).thenReturn(cepModel3);

        // When
        CepModel result1 = cepService.getCepXml(cleanCep1);
        CepModel result2 = cepService.getCepXml(cleanCep2);
        CepModel result3 = cepService.getCepXml(cleanCep3);

        // Then
        assertEquals(cepModel1, result1);
        assertEquals(cepModel2, result2);
        assertEquals(cepModel3, result3);
    }
}
