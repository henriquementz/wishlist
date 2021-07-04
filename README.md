[![CircleCI](https://circleci.com/gh/henriquementz/wishlist/tree/main.svg?style=svg&circle-token=474393a6e100a934dd80c4f3347accb9d58bb10f)](https://circleci.com/gh/henriquementz/wishlist/tree/main)

# Wishlist
> API responsável pelo gerenciamento de uma lista de desejos. 

Neste projeto usei o modelo arquitetural Clean Architecture, com a motivação de manter a regra de negócio e os domínios isolados de qualquer comunicação, banco de dados ou cliente externo. O ponto principal da arquitetura é que "os elementos de um círculo interno não podem saber nada sobre os elementos do circulo externo." (MARTIN, 2019). 

![68747470733a2f2f686162726173746f726167652e6f72672f7765622f6665382f6338322f6133322f66653863383261333262313534386231613239373138376532346165373535612e706e67](https://user-images.githubusercontent.com/8506455/124397827-a1341300-dce8-11eb-987b-e42df34d26af.png)

Na solução proposta existem 3 grupos principais: 
> core: responsável pelos casos de uso (regras de negócio) e entidades (regras de negócio e domínio). 


> dataprovider: responsável pela integração com a base de dados. 


> api: responsável pela porta de entrada dos serviços, nesse caso endpoint HTTP.


## Stack 
- Java 11 
- Maven 3.6
- Spring Boot 2.4.5
- Spring Data MongoDB
- Project Lombok
- Junit 5

## Testes

![image](https://user-images.githubusercontent.com/8506455/124397390-ea369800-dce5-11eb-937c-b712483ddd8b.png)

## Rodar serviço localmente 

### Requerimentos 
- [Docker](https://www.docker.com/products/docker-desktop)

### Clonar o serviço
`git clone https://github.com/henriquementz/wishlist`

### Subir o serviço 
> Acesse a pasta principal onde o arquivo docker-compose.yml esta salvo
> Rode o seguinte comando: 
`docker-compose up --build`

### Chamadas para a aplicação 
Você pode encontrar uma lista de request prontos nesta [Collection](https://github.com/henriquementz/wishlist/blob/main/WISHLIST.postman_collection.json)

