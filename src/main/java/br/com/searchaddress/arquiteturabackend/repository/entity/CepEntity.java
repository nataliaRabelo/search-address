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

    public CepEntity() {}

    public CepEntity(String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String ibge, String gia, String ddd, String siafi) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }


    public String getCep() {
        return cep;
    }

}

