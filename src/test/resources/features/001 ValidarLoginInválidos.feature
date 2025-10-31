Feature: Validar login inválido
  Como usuário do site SauceDemo
  Quero validar mensagens de erro ao tentar logar com credenciais inválidas
  Para garantir que o sistema bloqueie acessos não autorizados

  Background:
    Given que eu acesse o site "https://www.saucedemo.com/"

  @regressivo
  Scenario Outline: Validar login inválido
    When o usuário tenta logar com "<usuario>" e "<senha>"
    Then o sistema deve exibir a mensagem "<mensagem>"

    Examples:
      | usuario          | senha        | mensagem                                                                  |
      |                  | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user    |              | Epic sadface: Password is required                                        |
      | usuario_invalido | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user  | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |