Feature: 002 Validar login válido
  Como usuário do site SauceDemo
  Quero realizar o login com sucesso
  Para acessar a área de produtos do sistema

  Background:
    Given que eu acesse o site "https://www.saucedemo.com/"

  @regressivo
  Scenario Outline: Efetuar login com credenciais válidas
    When o usuário faz login com "standard_user" e "secret_sauce"
    Then devo ser redirecionado para a página de produtos
