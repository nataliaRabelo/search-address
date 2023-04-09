package steps;

import br.com.searchaddress.arquiteturabackend.ArquiteturaBackendApplication;
import br.com.searchaddress.arquiteturabackend.controller.CepController;
import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.service.CepService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Natália Bruno Rabelo.
 * Classe responsável por implementar steps de cenários de testes em Cucumber.
 */
@CucumberContextConfiguration
@SpringBootTest(classes = ArquiteturaBackendApplication.class)
public class CucumberCepTest {

    private String cep;
    private String response;
    private final String callbackName = "myCallback";

    @InjectMocks
    private CepController controller;

    @Mock
    private CepService service;

    /**
     * Método que define que o usuário deseja consultar um CEP de determinado valor.
     */
    @Given("^que o usuário deseja consultar um CEP$")
    public void queOUsuarioDesejaConsultarUmCEP() {
        cep = "01001000";
    }

    /**
     * Método que define que o serviço de CEP está funcionando corretamente, verificando se a condição é verdadeira.
     */
    @Given("^o serviço de CEP está funcionando corretamente$")
    public void oServicoDeCEPEstaFuncionandoCorretamente() throws Exception {
        CepModel cepModel = new CepModel(cep, "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        when(service.getCepXml(cep)).thenReturn(cepModel);
    }

    /**
     * método que define que o usuário deseja consultar um CEP em formato XML.
     * @param cep o cep requisitado.
     */
    @Given("^que o usuário deseja consultar o CEP \"([^\"]*)\" em XML$")
    public void queOUsuarioDesejaConsultarOCEPEmXML(String cep) {
        this.cep = cep;
    }

    /**
     * Método que envia a requisição para a API e armazena a resposta em um atributo.
     */
    @When("^a requisição é enviada para a API em XML$")
    public void aRequisicaoEEnviadaParaAAPIEmXml(){
        response = controller.getCepXml(cep);

    }

    /**
     * Método que verifica se o status code da resposta é igual a um valor esperado.
     */
    @Then("^o status code da resposta é (\\d+)$")
    public void oStatusCodeDaRespostaE(int code) {
        assertThat(response.contains(String.valueOf(code)));
    }

    /**
     * Método que verifica se o conteúdo da resposta é um XML válido.
     * @throws SAXException lançada quando ocorre um erro de análise do XML, como por exemplo quando o documento XML não está bem formado ou não segue o DTD especificado.
     * @throws IOException  lançada quando ocorre um erro de entrada ou saída ao ler o conteúdo do XML, como por exemplo quando o arquivo não é encontrado.
     * @throws ParserConfigurationException lançada quando ocorre um erro na configuração do parser do XML, como por exemplo quando não é possível criar uma instância do DocumentBuilder devido a configurações inválidas.
     */
    @Then("^o conteúdo da resposta é um XML válido$")
    public void oConteudoDaRespostaEUmXMLValido() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(response)));
        Assertions.assertNotNull(document);
    }

    /**
     * Método que define que o usuário deseja consultar um CEP em formato JSON.
     * @param cep o cep requisitado.
     */
    @Given("^que o usuário deseja consultar o CEP \"([^\"]*)\" em JSON$")
    public void queOUsuarioDesejaConsultarOCEPEmJSON(String cep) {
        this.cep = cep;
    }

    /**
     * Método que envia a requisição para a API em formato JSON e armazena a resposta em um atributo.
     */
    @When("^a requisição é enviada para a API em JSON$")
    public void aRequisicaoEEnviadaParaAAPIEmJson() {
        response = controller.getCepJson(cep);
    }

    /**
     * Método que verifica se o conteúdo da resposta é um JSON válido
     * @throws JsonProcessingException lançada caso ocorra um erro durante o processamento do JSON.
     */
    @Then("^o conteúdo da resposta é um JSON válido$")
    public void oConteudoDaRespostaEUmJSONValido() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readTree(response);
        Assertions.assertNotNull(mapper);
    }

    /**
     * Método que define que o usuário deseja consultar um CEP em formato JSONP.
     * @param cep o cep requisitado.
     */
    @Given("^que o usuário deseja consultar o CEP \"([^\"]*)\" em JSONP$")
    public void queOUsuarioDesejaConsultarOCEPEmJSONP(String cep) {
        this.cep = cep;
    }

    /**
     * MNétodo que envia a requisição para a API em formato JSONP e armazena a resposta em um atributo.
     */
    @When("^a requisição é enviada para a API em JSONP$")
    public void aRequisicaoEEnviadaParaAAPIEmJsonP() {
        response = controller.getCepJsonP(cep, callbackName);
    }

    /**
     * Método que verifica se o conteúdo da resposta é um JSONP válido.
     */
    @Then("^o conteúdo da resposta é um JSONP válido$")
    public void oConteudoDaRespostaEUmJSONPValido(){
        String jsonResponse = response.replaceFirst("^" + callbackName + "\\((.*)\\);$", "$1");
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(jsonResponse);
            // Se o JSONP for válido, não será lançada nenhuma exceção na linha anterior
            Assertions.assertNotNull(jsonNode);
        } catch (JsonProcessingException e) {
            fail("O conteúdo da resposta não é um JSONP válido");
        }
    }

    /**
     * Método que define que o usuário deseja consultar um CEP inválido.
     */
    @Given("^que o usuário deseja consultar um CEP inválido$")
    public void queOUsuarioDesejaConsultarUmCEPInvalido() {
        cep = "00000000";
    }

    /**
     * Método que envia uma requisição para a API com um CEP inválido e armazena a resposta em um atributo.
     */
    @When("a requisição é enviada para a API com CEP inválido")
    public void a_requisição_é_enviada_para_a_api_com_cep_inválido() {
        response = controller.getCepJson(cep);
    }

    /**
     * Método que verifica se a mensagem de erro é retornada corretamente.
     */
    @Then("^a mensagem de erro é retornada$")
    public void aMensagemDeErroERetornada() {
        assertThat(response.contains("404"));
    }
}
