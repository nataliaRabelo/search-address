package br.com.searchaddress.arquiteturabackend.view;

import br.com.searchaddress.arquiteturabackend.model.CepModel;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author Natália Bruno Rabelo.
 * Classe responsável por implementar uma view de um cep.
 */
public class CepView {
    private final String cep;
    private final String rua;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String estado;
    private final String frete;

    /**
     * Construtor da view de um cep.
     * @param cepModel um modelo de cep.
     */
    public CepView(CepModel cepModel){
        this.cep = cepModel.cep;
        this.rua = cepModel.rua;
        this.complemento = cepModel.complemento;
        this.bairro = cepModel.bairro;
        this.cidade = cepModel.cidade;
        this.estado = cepModel.estado;
        this.frete = cepModel.frete;
    }

    /**
     * Retorna a view como String, porém em formato de Json.
     * @return o cep em forma de json.
     */
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Retorna a view como String em formato XML.
     * @return o cep em forma de XML.
     */
    public String toXml(){
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("cep", CepView.class);
        return xstream.toXML(this);
    }

    /**
     * Retorna a view como String em formato JSONP.
     * @param callback o nome da função de callback.
     * @return o cep em forma de JSONP.
     */
    public String toJsonP(String callback) {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return callback + "(" + json + ")";
    }

}
