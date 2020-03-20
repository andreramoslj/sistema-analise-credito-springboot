**Análise de Crédito**

Aplicação Spring BOot que faz a análise de Crédito de um cliente


**Banco de dados POSTGRESQL**
Utilizei o PostgreSQL, mas pode-se alterar para qualquer banco, basta adicionar as dependencias no pom.xml e alterar no application.properties.


**Tecnologias Utilizadas**

-SPring Boot
-Rest
-PostgreSQL
-Flyway
-Maven
-Docker


*Documentação SWAGGER 


## DOCKER
Sistema disponibilizado para rodar no docker
-Criar imagem docker a partir do comando "Docker.dockerfile"
-Subir a imagem com o comando 'Docker run...' ou subir


**Possiveis problemas**
Ao subir o projeto no Docker, pode-se ter problemas com o acesso ao banco
Se o banco nao estiver liberado pode dar erro de conexao

Pode-se subir o postgresql com o docker e adicionar um docker composer para subir o banco no container
Ou em 'application.properties' colocar o IP ao invés de 'localhost'



