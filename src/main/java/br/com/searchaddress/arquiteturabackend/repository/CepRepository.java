package br.com.searchaddress.arquiteturabackend.repository;

import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.repository.entity.CepEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author Natália Bruno Rabelo
 *
 * Classe responsável por administrar os dados provenientes da base de dados ViaCEP
 */
@Repository
public class CepRepository {

    /**
     * Realiza uma requisição ge GET Rest API do ViaCEP cujo formato do retorno é em json.
     * @param cep o cep do endereço requisitado.
     * @return cepModel o endereço completo preenchido.
     */
    public CepModel findByCepJson(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        CepEntity response = restTemplate.getForObject(url, CepEntity.class);
        if (response != null && response.getCep() != null) {
            CepModel cepModel = new CepModel(response);
            return cepModel;
        }
        return null;
    }

    /**
     * Realiza uma requisição ge GET Rest API do ViaCEP cujo formato do retorno é em jsonp.
     * @param cep o cep do endereço requisitado.
     * @param callback o nome do método callback.
     * @return cepModel o endereço completo preenchido.
     */
    public CepModel findByCepJsonP(String cep, String callback) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/?callback=" + callback;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String jsonp = response.getBody();
        String json = jsonp.substring(jsonp.indexOf("(") + 1, jsonp.lastIndexOf(")"));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CepEntity cepEntity = objectMapper.readValue(json, CepEntity.class);
            if (cepEntity != null && cepEntity.getCep() != null) {
                CepModel cepModel = new CepModel(cepEntity);
                return cepModel;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Realiza uma requisição ge GET Rest API do ViaCEP cujo formato do retorno é em xml.
     * @param cep o cep do endereço requisitado.
     * @return cepModel o endereço completo preenchido.
     */
    public CepModel findByCepXml(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/xml/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter());
        try {
            ResponseEntity<CepEntity> response = restTemplate.getForEntity(url, CepEntity.class);
            if (response != null && response.getBody() != null && response.getBody().getCep() != null) {
                CepModel cepModel = new CepModel(response.getBody());
                return cepModel;
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
