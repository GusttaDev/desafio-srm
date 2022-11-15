# Passos necessários para rodar o projeto

== **Testando localmente:**
1. Execute o comando mvn clean install, raiz do projeto onde está localizado o arquivo pom.xml
2. Para rodar o projeto basta executar a class Run do projeto : SrmApplication
3. Abrir o browser digitar a URL: http://localhost:8080/people

== **Pré-requisitos do projeto**
        -Java 8 ou superior
        -Utilizando banco H2 em memória
        -Maven 3.5.* ou superior
        -Configurar o projeto para utilizar os fontes no encode UTF-8
        
# Desafio programação - para vaga desenvolvedor

Solicitação

·         Criar um projeto que gerencia pessoa utilizando padrões de design.

·         Deve receber dados e efetuar o cadastro. Dados de uma pessoa: nome, identificador e tipo Identificador;

·         Verificar se a pessoa possui um CPF ou um CNPJ:

a)      Caso o identificador da pessoa tenha 11 posições deve indicar que um CPF.

b)      Se possuir 14 posições, deve indicar que um CNPJ.

·         Deve validar se o identificador foi informado antes de verificar os tamanhos;

·         A requisição deve ser tratada com método POST através de um endpoint gerado pelo Spring;

·         Adicionar documentação no endpoint;

·         Gravar a atualização no banco de dados.

