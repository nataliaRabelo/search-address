import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.repository.CepRepository;
import br.com.searchaddress.arquiteturabackend.service.CepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CepServiceTest {

    @Mock
    private CepRepository cepRepository;

    private CepService cepService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.cepService.cepRepository = cepRepository;
    }

    @Test
    void testGetCepJson() throws Exception {
        // Given
        String cep = "12345678";
        CepModel cepModel = new CepModel();
        when(cepRepository.findByCepJson(cep)).thenReturn(cepModel);

        // When
        CepModel result = cepService.getCepJson(cep);

        // Then
        assertEquals(cepModel, result);
    }

    @Test
    void testGetCepJsonP() throws Exception {
        // Given
        String cep = "12345678";
        String callback = "myCallback";
        CepModel cepModel = new CepModel();
        when(cepRepository.findByCepJsonP(cep, callback)).thenReturn(cepModel);

        // When
        CepModel result = cepService.getCepJsonP(cep, callback);

        // Then
        assertEquals(cepModel, result);
    }

    @Test
    void testGetCepXml() throws Exception {
        // Given
        String cep = "12345678";
        CepModel cepModel = new CepModel();
        when(cepRepository.findByCepXml(cep)).thenReturn(cepModel);

        // When
        CepModel result = cepService.getCepXml(cep);

        // Then
        assertEquals(cepModel, result);
    }
}
