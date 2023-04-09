package br.com.searchaddress.arquiteturabackend.exception;

/**
 * Deve ser jogado quando há um retorno que não é de série 200 em uma requisição http feita internamente na API.
 * @author Natália Bruno Rabelo
 * */
public class HttpRequestException extends RuntimeException {
    private final int response_code;

    /**
     * Construtor padrão da exceção.
     * @param text Texto que detalha o erro.
     * @param code Código http do erro.
     */
    public HttpRequestException(String text, int code){
        super(text);
        response_code = code;
    }

    /**
     * Retorna o código do erro http.
     * @return Código do erro http.
     */
    public int getCode(){
        return response_code;
    }

}
