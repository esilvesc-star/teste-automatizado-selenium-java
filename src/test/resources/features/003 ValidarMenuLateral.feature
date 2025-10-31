Feature: 003 Validar Menu Lateral
  Como usuário do site SauceDemo
  Quero acessar o menu lateral após login
  Para visualizar todas as opções disponíveis

  Background:
  Dado que eu acesse o site "https://www.saucedemo.com/"

  @regressivo
  Scenario Outline: Validar exibição das opções do menu lateral
    Given que eu faça login com usuário "standard_user" e senha "secret_sauce"
    When eu abro o menu lateral
    Then devem ser exibidas as opções:
      | Opção           |
      | All Items       |
      | About           |
      | Logout          |
      | Reset App State |




