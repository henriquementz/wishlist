[![CircleCI](https://circleci.com/gh/henriquementz/wishlist/tree/main.svg?style=svg&circle-token=474393a6e100a934dd80c4f3347accb9d58bb10f)](https://circleci.com/gh/henriquementz/wishlist/tree/main)

# Wishlist - Desafio técnico
> API responsável pelo gerenciamento de uma lista de desejos. 

Neste projeto usei o modelo arquitetural Clean Architecture, com a motivação de manter a regra de negócio e os domínios isolados de qualquer comunicação, banco de dados ou cliente externo. 

![1_O4pMWCi5kZi20SNOR6V33Q](https://user-images.githubusercontent.com/8506455/124397806-79dd4600-dce8-11eb-8097-e3b91c9442c8.png)


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

