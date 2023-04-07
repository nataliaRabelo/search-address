package br.com.searchaddress.arquiteturabackend.service;

import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Aauthor Natália Bruno Rabelo
 * Classe responsável por implementar regras de negócio relacionadas aos ceps do sistema.
 */
@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public CepModel getCepJson(String cep) throws Exception {
        if (cep.matches("\\d{8}")) {
            return cepRepository.findByCepJson(cep);
        } else {
            throw new Exception("CEP inválido.");
        }
    }

    public CepModel getCepJsonP(String cep, String callback) throws Exception {
        if (cep.matches("\\d{8}")) {
            return cepRepository.findByCepJsonP(cep, callback);
        } else {
            throw new Exception("CEP inválido.");
        }
    }

    public CepModel getCepXml(String cep) throws Exception {
        if (cep.matches("\\d{8}")) {
            return cepRepository.findByCepXml(cep);
        } else {
            throw new Exception("CEP inválido.");
        }
    }
}
