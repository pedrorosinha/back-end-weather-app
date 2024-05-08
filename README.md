# Projeto Weather App

O projeto Weather App é uma aplicação back-end desenvolvida em Spring Boot que fornece serviços relacionados à previsão do tempo. Ele utiliza o banco de dados MySQL para armazenar dados e disponibiliza uma API HTTP para consulta das previsões do tempo. Além disso, o WireMock é utilizado para testar a API em um ambiente simulado na porta 4433.
Este projeto foi proposto pela DBServer como parte do desafio final da equipe da Formação Academia.

## Como Rodar
1. **Configuração do MySQL:**
   - Certifique-se de ter o MySQL instalado e em execução na sua máquina.
   - A porta de conexão é `3306` e é `localhost`
   - Crie um banco de dados chamado `weather`.

2. **Clone o Repositório:**
   ```bash
   git clone https://github.com/seu-usuario/weather-app.git
3. **Como pegar a chave da API**
   - Vá ate o site [openweathermap.org](https://openweathermap.org/api) e cria uma conta.
   - Depois clica na sua conta e vai para My API Keys.
   - Já vai aparecer uma chave padrão ou tu podes criar uma nova. Tu vai colocar um nome da chave, por exemplo `chaveAPI` e clica em `Generate`.
   - Depois só adiciona no `application.properties`.
     
4. **Configurações do Spring Boot:**
  - Certifique-se de que a porta padrão do Spring Boot (server.port) está definida como 3306 no arquivo application.properties.
  - O arquivo application.properties também deve conter as configurações de conexão com o banco de dados MySQL, como o URL, nome de usuário e senha.
  ```bash
    spring.application.name=weather-app
    openweathermap.api.key=suachave
    spring.datasource.url=jdbc:mysql://localhost:3306/weather
    spring.datasource.username=seuusuario
    spring.datasource.password=suasenha
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
```
5. **Como executar o projeto**
    - Navegue até a classe `WeatherAppAplication` e faz o comando `Ctrl + Shift + F10`

## Fazendo requisições
   - Use o software Postman ou o Insomnia para fazer as requisições.
   
**Metódo GETTempoAtual (Porto Alegre) (Não precisa de JSON)**
  - `localhost:8080/tempo/previsao/hoje?cidade=Porto Alegre`<br>
  
**Método GETTempo7Dias (Porto Alegre) (Não precisa de JSON)**
  - `localhost:8080/tempo/previsao/semana?cidade=Porto Alegre`<br>
  
**Método GETAllTempo (Não precisa de JSON)**
  - `localhost:8080/tempo/previsao/previsoes`<br>

**Método POSTTempo (Precisa de JSON) (cidade e tempo)**
  - `localhost:8080/tempo/previsao/`
  ```bash
  {
	"cidade": "São Paulo",
	"tempo": "Chuvoso"
  }
  ```
**Método PUTTempo (Precisa de JSON) (cidade e tempo) O id na url**
  - `localhost:8080/tempo/previsao/1`
  ```bash
  {
	"cidade": "São Paulo",
	"tempo": "Ensolarado"
  }
  ```
**Métódo DELETETempo (Não precisa de JSON) o id na url**
  - `localhost:8080/tempo/previsao/1`
