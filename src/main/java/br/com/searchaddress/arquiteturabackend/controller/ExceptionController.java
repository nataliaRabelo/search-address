package br.com.searchaddress.arquiteturabackend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.com.searchaddress.arquiteturabackend.ArquiteturaBackendApplication;
import br.com.searchaddress.arquiteturabackend.exception.CepNotFoundException;
import br.com.searchaddress.arquiteturabackend.exception.HttpRequestException;
import br.com.searchaddress.arquiteturabackend.exception.InvalidCepException;
import br.com.searchaddress.arquiteturabackend.view.ExceptionView;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Controlador responsável por interceptar as exceções atiradas pelos controllers e retorná-las como View em Json.
 * @author Natália Bruno Rabelo
 */
@ControllerAdvice
@RestController
@ApiIgnore
public class ExceptionController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ArquiteturaBackendApplication.class);

    /**
     * Método de fallback para retornar uma view genérica para quando a exceção não está mapeada
     * ou quando a URL requisitada não existe.
     * @return View com a informação da exceção gerada.
     */
    @RequestMapping("/error")
    public String error(){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        ExceptionView view = null;
        if(response.getStatus() == HttpStatus.NOT_FOUND.value()){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            view = new ExceptionView(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Request " + request.getRequestURI() + " does not exist.");
        }
        else
            view = new ExceptionView(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Send a message to the development team with code 8991.");
        return view.toJson();
    }

    /**
     * Método que intercepta todas as exceções que devem retornar erro interno do servidor.
     * @param exception A exceção que foi intercepta.
     * @return A view da exceção com o estado 500 de erro interno.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RuntimeException.class, HttpRequestException.class, IOException.class})
    public ResponseEntity<String> internalError(Exception exception){
        ExceptionView view = new ExceptionView(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage());
        logger.warn("Status Response: " + HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(view.toJson(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Método que retorna a view de todas as exceções que devem ser mapeados para "não encontrado".
     * @param exception A exceção mapeado.
     * @return A view da exceção com o estado 404 de not found.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {CepNotFoundException.class})
    public ResponseEntity<String>  notFound(Exception exception){
        ExceptionView view = new ExceptionView(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), exception.getMessage());
        return new ResponseEntity<>(view.toJson(), HttpStatus.NOT_FOUND);
    }

    /**
     * Método que mapeia as exceções de má requisição.
     * @param exception A exceção tratada.
     * @return A view da exceção com o estado 400 de bad request.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidCepException.class})
    public ResponseEntity<String>  badRequest(Exception exception){
        ExceptionView view = new ExceptionView(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), exception.getMessage());
        return new ResponseEntity<>(view.toJson(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Método que retorna a view de todas as requisições que não são suportadas.
     * O erro 405 deve ser atirado quando a API entende a URL solicitada, mas simplesmente não
     * suporta a requisição pelo tipo chamado (GET, POST, DELETE).
     * @param exception A exceção tratada.
     * @return A view da exceção com o estado 405 de method not allowed.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<String> methodNotAllowed(Exception exception){
        ExceptionView view = new ExceptionView(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.name(), exception.getMessage());
        return new ResponseEntity<>(view.toJson(), HttpStatus.METHOD_NOT_ALLOWED);
    }

}
