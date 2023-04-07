package br.com.searchaddress.arquiteturabackend.model;

import br.com.searchaddress.arquiteturabackend.repository.entity.CepEntity;
import br.com.searchaddress.arquiteturabackend.utils.Regions;

/**
 * @author Natália Bruno Rabelo
 *
 * Classe responsável por modelar um cep.
 */
public class CepModel {
    public String cep;
    public String rua;
    public String complemento;
    public String bairro;
    public String cidade;
    public String estado;
    public String frete;

    /**
     * Construtor padrão de cep através da entidade obtida pela base de dados.
     * @param cepEntity
     */
    public CepModel(CepEntity cepEntity){
        this.cep = cepEntity.cep;
        this.rua = cepEntity.logradouro;
        this.complemento = cepEntity.complemento;
        this.bairro = cepEntity.bairro;
        this.cidade = cepEntity.localidade;
        this.estado = cepEntity.uf;
        String frete = "";
        if(Regions.regiaoSudeste.contains(cepEntity.uf)){
            frete = "7.85";
        }else if(Regions.regiaoCentroOeste.contains(cepEntity.uf)){
            frete = "12.50";
        }else if(Regions.regiaoNordeste.contains(cepEntity.uf)){
            frete = "15.98";
        }else if(Regions.regiaoSul.contains(cepEntity.uf)){
            frete = "17.30";
        }else if(Regions.regiaoNorte.contains(cepEntity.uf)){
            frete = "20.83";
        }
        this.frete = frete;
    }
}
