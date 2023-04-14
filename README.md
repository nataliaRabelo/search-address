# Projeto Search Address <!-- omit in toc -->

O Search Address trata-se de um sistema desenvolvido para detalhar endereço e calcular frete a partir de um determinado CEP em diferentes tipos de formatos de retorno.

* Data: 14/04/2023
* Versão atual: 1.0 (manter o pom.xml atualizado também)

## 1. Pré-Requisitos

### 1.1. Para desenvolvimento

* Windows 10 ou 11.
* [IntelliJ Community na última versão](https://www.jetbrains.com/idea/download/#section=windows)
* [Java 11.0.17](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) - Distribuição: Oracle
* Springboot 2.7.5 (Obtido diretamente pela IDE em conjunto com os subitens abaixo)
    * Mockito 4.6.1
    * JUnit 5
    * SpringFox 2 v. 3.0.0
    * Spring Security v. 5.7.5
    * Outras menores configuradas no build do Maven (POM.xml)
* [Maven 3.8.6](https://maven.apache.org/download.cgi)

### 1.2. Somente para compilar e executar

* Em Sistema Operacional Windows
    * [Java 11.0.17](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) - Distribuição: Oracle
    * [Maven 3.8.6](https://maven.apache.org/download.cgi)

* Em Sistema Operacional Ubuntu
    * Java 11.0.1 - Distribuição: Oracle
        * Digitar comandos no terminal:
          ```
          sudo apt update
          sudo apt upgrade
          sudo add-apt-repository ppa:linuxuprising/java -y
          sudo apt update
          sudo apt-get install oracle-java11-installer oracle-java11-set-default
          ```
        * Testar com: `java --version`

    * Maven 3.8.6
        * Baixar o arquivo da versão correta do próprio site do Maven: [link](https://maven.apache.org/download.cgi).
        * Colocar o arquivo em algum diretório local que seja de fácil acesso no terminal.
        * Acesse a localização do instalador no terminal e exectue `scp apache-maven-3.8.6-bin.tar.gz <USER>@<IP>:~/` para enviar o instalador para o diretório home da máquina alvo. Substitua **USER** pelo usuário e **IP** pelo ip do ambiente que vai receber o back-end.
        * Acesse a máquina alvo e execute os seguintes comandos no local onde está o instalador:
          ``` 
          sudo tar xzf apache-maven-3.8.6-bin.tar.gz -C /opt
          sudo ln -s /opt/apache-maven-3.8.6 /opt/maven
          echo "export PATH=/opt/apache-maven-3.8.6/bin:\$PATH" > /etc/profile.d/maven.sh
          source /etc/profile.d/maven.sh
          echo "export JAVA_HOME=/usr/lib/jvm/java-17-oracle/" > /etc/profile.d/java-home.sh
          source /etc/profile.d/java-home.sh
          ```
        * Teste com `mvn --version`

## 2. Instruções de construção, execução e uso da API

### 2.1. Compilando o JAR para distribuição

Recomenda-se utilizar este modo de compilação para implantar o back-end em ambientes externos de produção, desenvolvidmento, controle de qualidade e etc. A compilação do pacote pode ser feita através do IntelliJ no Windows ou em qualquer ambiente com o comando:

```
mvn install
```

O executável da API será gerado no em `./target/search-address-<versao>.jar`. Automaticamente o maven irá agregar também a pasta de assets junto do `.jar`. Se você deseja contruir o projeto sem realizar os testes, então utilize:

```
mvn install -DskipTests
```

### 2.2. Construindo e Executando diretamente

**Atenção:** Como mencionado anteriormente, certifique-se de que o banco "test-MDC" existe no MongoDB instalado no ambiente e que esteja **sem autenticação**. Veja a seção abaixo de banco de dados para ver instruções de construção do banco.

Apesar do projeto ser facilmente construído e executado através do Maven, este modo de compilação e execução somente é recomendado para desenvolvimento local. Dessa forma, o desenvolvedor pode usar o IntelliJ para executar em modo de desenvolvimento com ou sem debugger direto na própria interface da IDE. Você também pode compilar e executar de uma vez através do terminal no **diretório do POM.xml**:

```
mvn clean compile exec:java
```

### 2.3. Contruindo e Executando os testes unitários

O código fonte dos testes unitários está em `./src/test/`. Você pode executar diretamente pelo IntelliJ com ou sem o debugger (recomendado). Porém, se você deseja compilar e executar os testes unitários através do console, execute o seguinte comando:

```
mvn clean test-compile
```

Para executar uma classe inteira do teste de unitário utilize o seguinte comando, onde `[CLASSE]` é a respectiva classe implementada dos testes:

```
mvn -Dtest=[CLASSE] test
```

Para executar um método específico de alguma classe dos testes unitários, utilize o seguinte comando, onde `[CLASSE]` é a respectiva classe e `[METODO]` o respectivo método do Junit.

```
mvn -Dtest=[CLASSE]$[METODO] test
```

### 2.4. Usando a API

Uma vez com a API levantada no sistema, o uso dela se da através de HTTP ou HTTPS dependendo da configuração utilizada. Acesse a página `/swagger-ui/` para visualizar os comandos da API.

```
<http>://<ip da api>:<porta da api>/swagger-ui/

http://localhost:8443/swagger-ui/
```

O formato do comando deve estar documentado no próprio swagger.

## 3. Testes Unitário do Projeto

O código deste projeto é testado através de uma componente de teste unitário que fazem a cobertura parcial das componentes presentes no sistema. O teste de unidade utiliza a biblioteca do JUnit integrada no SpringBoot e todo seu código se encontra em `src/test`. A documentação que fornece a modelagem e auxilia no planejamento dos testes é feita diretamente no código-fonte dos testes.

Os testes unitários são executados automaticamente na construção de qualquer JAR para a distribuição do projeto pelo *script* agregado. Qualquer outro detalhe para execução dos testes unitários pode ser vista na respectiva seção de execução dos testes relacionada anteriormente neste documento. Toda a implementação dos testes devem estar documentadas, seguindo fielmente a estrutura dos demais testes. Os seguintes princípios de boas práticas devem ser aplicados:

* Os testes devm utilizar o código source da aplicação como caixa preta.
    * As unidades são os métodos de alguma classe testada do código-fonte.
    * Cada método do código-fonte deve ser testado como caixa preta, visando o esquema de entrada/saída.
* Todos os testes devem:
    * Ser independentes entre si;
    * Ser determinísticos;
    * Introduzir pouco overhead de tempo de execução e uso de memória comparado ao código fonte testado.
* Uma classe do teste unitário deve implementar uma feature. Cada feature deve:
    * Ter uma chave única. Exemplo: F-CepController, F-CepService e F-CepRepository.
    * Cobrir uma respectiva classe do código-fonte.
    * Ser dividida em cenários.
        * Deve ser implementada através de um único método.
        * Deve possuir uma descrição com uma tabela que relaciona as entradas usadas e o resultado que está sendo validado.
* Qualquer teste que necessite utilizar uma informação externa deve:
    * Utilizar somente arquivos no disco localizados no diretório `./src/test/resources/`;

## 4. Estrutura de Pastas do Repositório

Este repositório está estruturado da seguinte forma:

* assets - Dependências externas da API. Precisa estar presente com o JAR para execução correta.
* src - Código fonte da aplicação de integração
    * src/main/ - Fonte principal da aplicação
    * src/test/ - Fonte do teste de unidade
* target - Contém os arquivos temporários gerados pelo maven durante construção da API.
* .idea - Contém os arquivos relacionados a IDE (IntelliJ)

