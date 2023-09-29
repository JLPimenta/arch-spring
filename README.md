# arch-absentApp-back
----
Arquitetura básica para o projeto AbsentApp (rascunho inicial, a ser transferido para o projeto principal)


## Estrutura
O projeto do backend é estruturado utilizando o Maven e possui dois módulos separando a camada de entidades, repositórios e serviços da camada web. Respectivamente, os modulos são domain e api.

### Desenho da arquitetura
![image](https://github.com/absent-project/back-end/assets/85958572/1586c5fc-5808-4a7f-a7cb-0ab1bdd52e30)
- **Application Layer**: Controllers, o que recebe o dado (R) provindo do front-end.
- **Domain Layer**: Serviços, Repositórios e Entidades, onde o dado é tratado para um formato (T) onde será possível trabalhá-lo e aplicar a regra de negócio. No fim do processo, será devolvido um dado de resposta (Q).
- **Infrastracture Layer**: Base de dados, liquibase, e conexões a base de dados. Basicamente, tudo que o sistema precisa para funcionar.

### Módulos

#### Domain
O módulo Domain contém a estrutura abaixo: </br>
![image](https://github.com/absent-project/back-end/assets/85958572/d94c0efd-26f3-4bc7-8a97-bfcc92759e23)

Segue tabela de descrição de cada pacote na camada de domínio:
|   Pacote   |                                                        Descrição                                                            |
| ---------- | --------------------------------------------------------------------------------------------------------------------------- |
| core       | O pacote contém classes genéricas que auxiliam os desenvolvedores a criarem os repositórios, serviços e controllers.        |
| entity     | Pacote que contém as entidades (JPA) da aplicação.                                                                          |
| model      | Pacote que armazena os modelos ou DTO.                                                                                      |
| repository | Pacote que contém as classes de repositórios para consulta no banco de dados. Os repositórios seguem o padrão do SpringData |
| service    | Pacote onde são armazenados as classes de serviços                                                                          |

#### API
- Existe o pacote Api com os controllers da aplicação, contendo futuramente itens relacionados ao Spring Security _(Não implementado ainda!)_.

### Links Importantes  
- Swagger (Documentação): http://localhost:8080/swagger-ui/index.html#/
     - Necessário que a aplicação esteja rodando!
- Variáveis de ambiente (dotenv): [Variáveis](https://dontpad.com/dontenv-content)
