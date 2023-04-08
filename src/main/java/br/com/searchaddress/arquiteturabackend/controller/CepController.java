package br.com.searchaddress.arquiteturabackend.controller;

import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.service.CepService;
import br.com.searchaddress.arquiteturabackend.utils.SwaggerExamples;
import br.com.searchaddress.arquiteturabackend.view.CepView;
import br.com.searchaddress.arquiteturabackend.view.ExceptionView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Natália Bruno Rabelo
 * Classe responsável por implementar o controller de /cep/.
 */
@RestController
@RequestMapping(value="/cep")
@Tag(name = "cep-controller", description = "Controller responsável por requisições de endereço por cep.")
public class CepController {

    @Autowired
    public CepService cepService;

    /**
     * Método que implementa a requisição de um endereço completo a partir de um cep em formato XML.
     * @param cep o cep requisitado.
     * @return uma view com o endereço completo.
     */
    @ApiOperation(value = "Retorna detalhes sobre o endereço a partir de um cep em formato XML.",
            notes = "Lista detalhes de um endereço específico seguindo o cep passado em xml.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida.", content = @Content(
                    mediaType = "application/xml",
                    schema = @Schema(implementation = String.class),
                    examples = {@ExampleObject(value = SwaggerExamples.XMLEXAMPLE)})),
            @ApiResponse(responseCode = "401", description = "Não utilizado."),
            @ApiResponse(responseCode = "403", description = "Não utilizado."),
            @ApiResponse(responseCode = "400", description = "Formato inválido."),
            @ApiResponse(responseCode = "404", description = "Cep não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno na requisição")})
    @GetMapping(value = "/xml/{cep}", produces = MediaType.APPLICATION_XML_VALUE)
    public String getCepXml(@ApiParam(name = "cep", type = "String", value = "Cep do endereço que está sendo solicitado.", example = "01001000", required = true) @PathVariable String cep) {
        try {
            CepModel cepResult;
            if(cep.contains("-")){ // verifica se entrada possui hífen e realiza a limpeza caso tenha.
                String cleanCep = cep.replace("-", "");
                cepResult = cepService.getCepXml(cleanCep);
            }else{
                cepResult = cepService.getCepXml(cep);
            }
            if (cepResult != null) {
                return new CepView(cepResult).toXml();
            } else {
                return new ExceptionView(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), "CEP não encontrado").toXml();
            }
        } catch (Exception e) {
            return new ExceptionView(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), "Erro interno no servidor.").toXml();
        }
    }

    /**
     * Método que implementa a requisição de um endereço completo a partir de um cep em formato JSON.
     * @param cep o cep requisitado.
     * @return uma view com o endereço completo.
     */
    @ApiOperation(value = "Retorna detalhes sobre o endereço a partir de um cep em formato JSON.",
            notes = "Lista detalhes de um endereço específico seguindo o cep passado em json.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida.", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = String.class),
                    examples = {@ExampleObject(value = SwaggerExamples.JSONEXAMPLE)})),
            @ApiResponse(responseCode = "401", description = "Não utilizado."),
            @ApiResponse(responseCode = "403", description = "Não utilizado."),
            @ApiResponse(responseCode = "400", description = "Formato inválido."),
            @ApiResponse(responseCode = "404", description = "Cep não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno na requisição")})
    @GetMapping(value = "/json/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCepJson(@ApiParam(name = "cep", type = "String", value = "Cep do endereço que está sendo solicitado.", example = "01001000", required = true) @PathVariable String cep) {
        try {
            CepModel cepResult;
            if(cep.contains("-")){ // verifica se entrada possui hífen e realiza a limpeza caso tenha.
                String cleanCep = cep.replace("-", "");
                cepResult = cepService.getCepJson(cleanCep);
            }else{
                cepResult = cepService.getCepJson(cep);
            }
            if (cepResult != null) {
                return new CepView(cepResult).toJson();
            } else {
                return new ExceptionView(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), "CEP não encontrado").toJson();
            }
        } catch (Exception e) {
            return new ExceptionView(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), "Erro interno no servidor.").toJson();
        }
    }

    /**
     * Método que implementa a requisição de um endereço completo a partir de um cep em formato JSON.
     * @param cep o cep requisitado.
     * @return uma view com o endereço completo.
     */
    @ApiOperation(value = "Retorna detalhes sobre o endereço a partir de um cep em formato JSONP.",
            notes = "Lista detalhes de um endereço específico seguindo o cep passado em jsonp.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida.", content = @Content(
                    mediaType = "application/javascript",
                    schema = @Schema(implementation = String.class),
                    examples = {@ExampleObject(value = SwaggerExamples.JSONPEXAMPLE)})),
            @ApiResponse(responseCode = "401", description = "Não utilizado."),
            @ApiResponse(responseCode = "403", description = "Não utilizado."),
            @ApiResponse(responseCode = "400", description = "Formato inválido."),
            @ApiResponse(responseCode = "404", description = "Cep não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno na requisição")})
    @GetMapping(value = "/jsonp/{cep}", produces = "application/javascript")
    public String getCepJsonP(@ApiParam(name = "cep", type = "String", value = "Cep do endereço que está sendo solicitado.", example = "01001000", required = true) @PathVariable String cep, @ApiParam(name = "callback", type = "String", value = "Nome do callback.", example = "callback_name", required = true) @RequestParam(defaultValue = "callback") String callback) {
        try {
            CepModel cepResult;
            if(cep.contains("-")){ // verifica se entrada possui hífen e realiza a limpeza caso tenha.
                String cleanCep = cep.replace("-", "");
                cepResult = cepService.getCepJsonP(cleanCep, callback);
            }else{
                cepResult = cepService.getCepJsonP(cep, callback);
            }
            if (cepResult != null) {
                return new CepView(cepResult).toJsonP(callback);
            } else {
                return new ExceptionView(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), "CEP não encontrado").toJson();
            }
        } catch (Exception e) {
            return new ExceptionView(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), "Erro interno no servidor.").toJson();
        }
    }

}



