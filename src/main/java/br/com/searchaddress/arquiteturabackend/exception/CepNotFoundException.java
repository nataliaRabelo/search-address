package br.com.searchaddress.arquiteturabackend.exception;

/**
 * Exceção lançada quando um endereço com determinado cep não é encontrado.
 * @author Natália Bruno Rabelo.
 */
public class CepNotFoundException extends Exception {
    /**
     * Construtor padrão que recebe o cep usado para encontrar o endereço completo.
     * @param cep Parâmetro utilizado para tentar obter o endereço que não foi encontrado.
     */
    public CepNotFoundException(String cep){
        super("Address associated with cep: " + cep + " was found.");
    }
}
