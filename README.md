
# Projeto Seguradora de carros

O projeto de seguro de carros é uma iniciativa que visa oferecer proteção e tranquilidade aos proprietários de veículos. Através desse serviço, os segurados podem contar com cobertura em casos de acidentes, roubos, danos e outras eventualidades, evitando prejuízos financeiros. O projeto busca fornecer opções flexíveis de cobertura, adaptadas às necessidades individuais dos clientes, e oferecer um processo ágil e eficiente para a solicitação e a liquidação de sinistros. Além disso, o projeto busca promover a conscientização sobre a importância do seguro de carros como um elemento essencial para a segurança no trânsito.

## Modelo conceitual
![Modelo Conceitual](https://github.com/Wilson-Pedro/images/blob/main/seguradora-de-carros/Seguradora%20de%20carros%20(1).png)

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven

## Banco de dados
-Postgres

## Status do Projeto:
-Em desenvolvimento

## Fazendo a instalação do projeto

Para rodar o projeto localmente faça o download das seguintes ferramentas:

- [STS](https://spring.io.xy2401.com/tools3/sts/all/)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Postman](https://www.postman.com/downloads/)
- [Git](https://git-scm.com/downloads)

Depois de ter instalado as ferramentas, crie uma pasta e de um nome a ela. Então abra o seu terminal nessa pasta. e digite o seguinte comando:

```bash
git clone git@github.com:Wilson-Pedro/seguradora-de-carros.git
```

Após isso abra o STS ou uma IDE que suporte o SPRING e execute o projeto.

-> Com o STS vá em 'CarInsurenceApplication.java' e clique com o potão direito
-> Vá em 'Run As'
-> E clique em 'Spring Boot App'

![Excecutando projeto](https://github.com/Wilson-Pedro/images/blob/main/seguradora-de-carros/executar-projeto.png)


## Abra o seu Postman e coloque a URL de acordo com o verbo HTTP:

# POST
```
http://localhost:8080/clients
```
![POST](https://github.com/Wilson-Pedro/images/blob/main/seguradora-de-carros/POST.PNG)

# GET ALL
```
http://localhost:8080/clients
```
![GET ALL](https://github.com/Wilson-Pedro/images/blob/main/seguradora-de-carros/GET.PNG)

# GET ONE
```
http://localhost:8080/clients/2
```
![GET ONE](https://github.com/Wilson-Pedro/images/blob/main/seguradora-de-carros/GET-ONE.PNG)

# PUT
```
http://localhost:8080/clients/6
```
![PUT](https://github.com/Wilson-Pedro/images/blob/main/seguradora-de-carros/PUT.PNG)

# DELETE
```
http://localhost:8080/clients/7
```
![DELETE](https://github.com/Wilson-Pedro/images/blob/main/seguradora-de-carros/DELETE.PNG)


## Autor

- [@Wilson Pedro](https://github.com/Wilson-Pedro)
