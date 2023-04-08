package br.com.searchaddress.arquiteturabackend.view;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Classe responsável por implementar a View de uma mensagem de exceção.
 * @author Natália Bruno Rabelo.
 */
public class ExceptionView {

    private final int code;
    private final String status;
    private final String message;

    /**
     * Construtor padrão da view de exceções.
     * @param code Código da mensagem http.
     * @param status O estado em forma de String.
     * @param message A mensagem customizada da exceção.
     */
    public ExceptionView(int code, String status, String message){
        this.code = code;
        this.status = status;
        this.message = message;
    }

    /**
     * Método responsável por retornar um Json serializado de uma mensagem de exceção.
     * @return A resposta em formato de Json.
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
