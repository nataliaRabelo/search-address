package br.com.searchaddress.arquiteturabackend.controller;

import br.com.searchaddress.arquiteturabackend.model.CepModel;
import br.com.searchaddress.arquiteturabackend.repository.entity.CepEntity;
import br.com.searchaddress.arquiteturabackend.service.CepService;
import br.com.searchaddress.arquiteturabackend.utils.SwaggerExamples;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/cep")
@Tag(name = "cep-controller", description = "Controller responsável por requisições de endereço por cep.")
public class CepController {

    @Autowired
    private CepService cepService;

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
    public ResponseEntity<?> getCepXml(@ApiParam(name = "cep", type = "String", value = "Cep do endereço que está sendo solicitado.", example = "01001000", required = true) @PathVariable String cep) {
        try {
            CepModel cepResult = cepService.getCepXml(cep);
            if (cepResult != null) {
                return ResponseEntity.ok().body(cepResult);
            } else {
                return ResponseEntity.badRequest().body("CEP inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

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
    public ResponseEntity<?> getCepJson(@ApiParam(name = "cep", type = "String", value = "Cep do endereço que está sendo solicitado.", example = "01001000", required = true) @PathVariable String cep) {
        try {
            CepModel cepResult = cepService.getCepJson(cep);
            if (cepResult != null) {
                return ResponseEntity.ok().body(cepResult);
            } else {
                return ResponseEntity.badRequest().body("CEP inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

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
    public ResponseEntity<?> getCepJsonP(@ApiParam(name = "cep", type = "String", value = "Cep do endereço que está sendo solicitado.", example = "01001000", required = true) @PathVariable String cep, @ApiParam(name = "callback", type = "String", value = "Nome do callback.", example = "callback_name", required = true) @RequestParam(defaultValue = "callback") String callback) {
        try {
            CepModel cepResult = cepService.getCepJsonP(cep, callback);
            if (cepResult != null) {
                String json = new ObjectMapper().writeValueAsString(cepResult);
                String jsonp = callback + "(" + json + ");";
                return ResponseEntity.ok().body(jsonp);
            } else {
                return ResponseEntity.badRequest().body("CEP inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

}



