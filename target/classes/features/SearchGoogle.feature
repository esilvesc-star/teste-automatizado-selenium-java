#encoding: utf-8
Feature: Busca Google NTTDATA
  
  Marcelo irá fazer uma busca no google NTTDATA
  E quando estiver na pagina ira clicar em carreira e acessar junte-se a nossa equipe pesquisar Arquiteto.
  Para verificar o numero de oportunidades e se as vagas sao de Arquitetos
  @test
  Scenario: Validar a busca NTTDATA no Google
    Given que Marcelo faz uma busca no google "https://www.google.com.br" com pesquisa "NTTDATA BRASIL" e acessar o Primeiro link apresentado na pesquisa.
    When estiver na pagina, clicar em carreira e acessar junte-se a nossa equipe pesquisar "Arquiteto".
    Then devera conter 3 vagas e ser vaga de "Arquiteto".   
  