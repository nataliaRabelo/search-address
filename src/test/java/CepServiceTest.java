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

@ExtendWith(MockitoExtension.class)
class CepServiceTest {

    @InjectMocks
    private CepRepository cepRepository;

    @Mock
    private CepService cepService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
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
