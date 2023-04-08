package br.com.searchaddress.arquiteturabackend.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Classe responsável por configurar o Swagger.
 * Acesso padrão com configuração de desenvolvimento: http://localhost:{PORT}/swagger-ui/
 * @author Natália Bruno Rabelo.
 */
@Configuration
public class Swagger2Configuration {


    /**
     * Método responsável por retornar o nome do contexto da API para ser inserido no layout.
     * @return Mensagem que aparece na tela inicial do swagger.
     */
    public String getApiContext(){
        boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().contains("-agentlib:jdwp");
        if (isDebug) {
            return "Debug Version. " + new String(Character.toChars(0x1F41E));
        } else {
            return "Release Version. " + new String(Character.toChars(0x1F407));
        }
    }

    /**
     * Método responsável por preencher os dados do layout do swagger.
     * @return usado por ferramenta interna do projeto.
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Search Address API" + new String(Character.toChars(0x1F36D)))
                .description(getApiContext())
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }
    /**
     * Método responsável por definir caminhos a serem incluídos por meio do swagger.
     * @return usado por ferramenta interna do projeto.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.searchaddress.arquiteturabackend"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

}
