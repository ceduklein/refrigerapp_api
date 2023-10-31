# REFRIGERAPP - API

Projeto Aplicado IV - 5ª Fase de Análise e Desenvolvimento de Sistemas SENAI


## Gerenciamento de produtos no catálogo de empresa de refrigeração

### Descrição Resumida

Desenvolver um sistema para que uma empresa revendedora de eletrodomésticos de refrigeração cadastre e gerencie os produtos disponíveis para venda.

### Benefícios Esperados

Com esse projeto, busca-se otimizar o controle das marcas e produtos revendidos pela empresa, facilitando assim o controle de revendas e de estoque.

### Detalhamento

Uma empresa de revenda de eletrodomésticos de refrigeração, especializada em geladeiras e freezers, está tendo dificuldades para gerenciar os produtos que estão ativos/inativos para venda, o que gera situações desconfortáveis, como oferta de produtos não mais disponíveis. Para sanar essa dificuldade, a empresa deseja que seja produzido um software web que permita o cadastro das marcas e produtos disponíveis para venda, que possa ser consultado por seus vendedores no momento de um pedido. Relatórios são bem vindos, bem como segurança de acesso através da autenticação dos funcionários.

## Preparação do ambiente
* JDK 17
* Maven
* PostgresSQL
* Após clonar o repositório, executar o comando `mvn clean install` para instalar todas as dependências necessárias.
* Criar o banco de dados e configurar a conexão.
* Executar `mvn spring-boot:run` para rodar a api em ambiente local.

## Documentaçao da api

Após rodar a aplicação em ambiente local é possível consultar a documentação com todos os endpoints no endereço abaixo.

`http://localhost:8080/swagger-ui/index.html`

Ou sua versão JSON em: 

`http://localhost:8080/api-docs`