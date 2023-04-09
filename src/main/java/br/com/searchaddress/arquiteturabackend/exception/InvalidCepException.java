package br.com.searchaddress.arquiteturabackend.exception;

/**
 * Classe que representa a exceção de um cep inválido.
 * @author Natália Bruno Rabelo
 */
public class InvalidCepException extends Exception{

    /**
     * Construtor padrão da exceção.
     * @param cep Cep invalidado.
     */
    public InvalidCepException(String cep){
        super("The cep: " + cep + ". Is not validing according to the standard accepted by the API.");
    }
}
