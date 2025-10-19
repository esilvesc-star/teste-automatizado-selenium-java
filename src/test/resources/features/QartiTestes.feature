Feature: Acessar página inicial

  @testeQarti
  Scenario: Preencher formulario no site QuartiTestes

    Given que o usuário acessa a página "https://conteudo.qarti.com.br/conheca-a-qarti?utm_source=google&utm_medium=cpc&utm_campaign=hr-pesquisa-servicos&utm_term=testes-automatizados&utm_content=pesquisa&gad_source=1&gad_campaignid=22515379863&gbraid=0AAAAA-kBw5PO5OQH57-QB102QFeUpk6xj&gclid=Cj0KCQjwgKjHBhChARIsAPJR3xfW1e3LWPVQdcoVEijL2R8E1bLsWtWAKfdg5gfMEelmE4JoU4GKD58aAierEALw_wcB"
    When ele preenche os campos do formulário:
      | nome           | email           | celular      | empresa | cargo       |
      | Eloi Silvestre | eloi@gmail.com  | 11945996269  | NTTDATA | QA Enginner |
    And clica no botão "Quero entrar em contato"
    Then a página "Obrigada pelo interesse em nossos serviços" deve ser exibida