package br.com.searchaddress.arquiteturabackend.service;
import br.com.searchaddress.arquiteturabackend.exception.CepNotFoundException;
import br.com.searchaddress.arquiteturabackend.exception.InvalidCepException;
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
    public CepRepository cepRepository;

    /**
     * Obtém um json com endereço completo a partir de um cep com condição de que possua oito dígitos.
     * @param cep o cep requisitado
     * @return cepModel o endereço completo de um determinado cep.
     * @throws InvalidCepException exceção lançada quando cep não foi encontrado.
     */
    public CepModel getCepJson(String cep) throws InvalidCepException {
        if (cep.matches("\\d{8}") && !(cep.isEmpty()) && cep.matches("\\d+")) { // verifica se o cep contém 8 caracteres, se não está vazio e se só contém dígitos
            return cepRepository.findByCepJson(cep);
        } else {
            throw new InvalidCepException(cep);
        }
    }

    /**
     * Obtém um jsonp com endereço completo a partir de um cep com condição de que possua oito dígitos.
     * @param cep o cep requisitado
     * @return cepModel o endereço completo de um determinado cep.
     * @throws InvalidCepException lançada quando cep é inválido.
     * @throws CepNotFoundException lançada quando cep não foi encontrado.
     */
    public CepModel getCepJsonP(String cep, String callback) throws InvalidCepException, CepNotFoundException {
        if (cep.matches("\\d{8}") && !(cep.isEmpty()) && cep.matches("\\d+")) {
            return cepRepository.findByCepJsonP(cep, callback);
        } else {
            throw new InvalidCepException(cep);
        }
    }

    /**
     * Obtém um xml com endereço completo a partir de um cep com condição de que possua oito dígitos.
     * @param cep o cep requisitado
     * @return cepModel o endereço completo de um determinado cep.
     * @throws InvalidCepException lançada quando cep é inválido.
     */
    public CepModel getCepXml(String cep) throws InvalidCepException {
        if (cep.matches("\\d{8}") && !(cep.isEmpty()) && cep.matches("\\d+")) {
            return cepRepository.findByCepXml(cep);
        } else {
            throw new InvalidCepException(cep);
        }
    }
}
