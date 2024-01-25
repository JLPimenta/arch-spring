# arch-spring
----
Arquitetura básica para projetos

## Executando o projeto
`Localhost` <br>
- Baixe ou realize o clone do projeto,
- Copie e cole o `.env.example` na pasta raiz do projeto, retirando o `.example` implementando as devidas conexões e afins,
- No terminal, escreva o comando ``docker compose up -d`` para subir o banco de dados (Container),
- Execute o projeto utilizando o comando `./mvnw spring-boot:run` ou execute o AppApplication

## Estrutura
O projeto do backend é estruturado utilizando o Maven e possui dois módulos separando a camada de entidades, repositórios e serviços da camada web. Respectivamente, os modulos são domain e api.

### Desenho da arquitetura
![image](https://github.com/JLPimenta/arch-spring/assets/85958572/46b73f05-054f-4c3b-95da-d91e83ab1706)
- **Application Layer**: Controllers, o que recebe o dado (R) provindo do front-end.
- **Domain Layer**: Serviços, Repositórios e Entidades, onde o dado é tratado para um formato (T) onde será possível trabalhá-lo e aplicar a regra de negócio. No fim do processo, será devolvido um dado de resposta (Q).
- **Infrastracture Layer**: Base de dados, liquibase, e conexões a base de dados. Basicamente, tudo que o sistema precisa para funcionar.

### Módulos

#### Domain
O módulo Domain contém a estrutura abaixo: </br>
![image](https://github.com/JLPimenta/arch-spring/assets/85958572/00787c98-55cc-4dd5-b41b-c9d06b87bcb2)

Segue tabela de descrição de cada pacote na camada de domínio:
|   Pacote   |                                                        Descrição                                                            |
| ---------- | --------------------------------------------------------------------------------------------------------------------------- |
| core       | O pacote contém classes genéricas que auxiliam os desenvolvedores a criarem os repositórios, serviços e controllers.        |
| entity     | Pacote que contém as entidades (JPA) da aplicação.                                                                          |
| model      | Pacote que armazena os modelos ou DTO.                                                                                      |
| repository | Pacote que contém as classes de repositórios para consulta no banco de dados. Os repositórios seguem o padrão do SpringData |
| service    | Pacote onde são armazenados as classes de serviços                                                                          |

#### API
- O pacote API contém a estrutura abaixo: </br>
![image](https://github.com/JLPimenta/arch-spring/assets/85958572/dcac84ac-4bd8-40a7-8256-16464c2f17ae)

Segue tabela de decrição de cada pacote na camada de API:
|   Pacote   |                                                        Descrição                                                                               |
| ---------- | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| config     | Pacote com classes de configurações inerentes à arquitetura, como handler de excessões, Spring Security (não implementado) e Swagger Doc.      |
| controller | Pacote que contém os controladores (endpoints) da aplicação.                                                                                   |

### Links Importantes  
- Documentação (Swagger 3.0):
     - http://localhost:8080
          - Ambiente local (dev)
          - ⚠️ Necessário que a aplicação esteja rodando!
