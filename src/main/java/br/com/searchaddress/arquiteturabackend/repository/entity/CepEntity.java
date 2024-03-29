package br.com.searchaddress.arquiteturabackend.repository.entity;

/**
 * @author Natália Bruno Rabelo
 *
 * Classe responsável por definir uma entidade de um cep.
 */
public class CepEntity {
    public String cep;
    public String logradouro;
    public String complemento;
    public String bairro;
    public String localidade;
    public String uf;
    public String ibge;
    public String gia;
    public String ddd;
    public String siafi;

    public String getCep() {
        return cep;
    }

}

