Feature: Consulta de CEP

  Background:
    Given que o usuário deseja consultar um CEP
    And o serviço de CEP está funcionando corretamente

  Scenario: Consulta de CEP em XML
    Given que o usuário deseja consultar o CEP "01001000" em XML
    When a requisição é enviada para a API em XML
    Then o status code da resposta é 200
    And o conteúdo da resposta é um XML válido

  Scenario: Consulta de CEP em JSON
    Given que o usuário deseja consultar o CEP "01001000" em JSON
    When a requisição é enviada para a API em JSON
    Then o status code da resposta é 200
    And o conteúdo da resposta é um JSON válido

  Scenario: Consulta de CEP em JSONP
    Given que o usuário deseja consultar o CEP "01001000" em JSONP
    When a requisição é enviada para a API em JSONP
    Then o status code da resposta é 200
    And o conteúdo da resposta é um JSONP válido

  Scenario: Consulta de CEP inválido
    Given que o usuário deseja consultar um CEP inválido
    When a requisição é enviada para a API com CEP inválido
    Then o status code da resposta é 400
    And a mensagem de erro é retornada
