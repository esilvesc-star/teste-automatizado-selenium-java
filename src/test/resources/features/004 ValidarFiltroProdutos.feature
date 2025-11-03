Feature: 004 Validar Filtro de Produtos
  Como usuário do site SauceDemo
  Quero utilizar o filtro de ordenação de produtos
  Para visualizar os itens na ordem correta

  Background:
  Dado que eu acesse o site "https://www.saucedemo.com/"

  @regressivos
  Scenario Outline: Validar ordenação dos produtos
    Given que eu faça login com usuário "standard_user" e senha "secret_sauce"
    When eu selecionar o filtro "<Filtro>"
    Then os produtos devem ser exibidos na ordem "<OrdemEsperada>"

    Examples:
      | Filtro              | OrdemEsperada                                                                  |
      | Name (A to Z)       | Sauce Labs Backpack, Sauce Labs Bike Light, Sauce Labs Bolt T-Shirt            |
      | Name (Z to A)       | Test.allTheThings() T-Shirt (Red), Sauce Labs Onesie, Sauce Labs Fleece Jacket |
      | Price (low to high) | Sauce Labs Bike Light, Sauce Labs Bolt T-Shirt, Sauce Labs Backpack            |
      | Price (high to low) | Sauce Labs Fleece Jacket, Sauce Labs Backpack, Sauce Labs Bolt T-Shirt         |