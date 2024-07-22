# Gerenciamento de Projetos e Tarefas

## Descrição

Este é um aplicativo web para gerenciamento de projetos e tarefas. Ele permite que os usuários visualizem, adicionem, editem e excluam projetos e suas respectivas tarefas. Cada projeto pode conter uma ou mais tarefas associadas.

## Tecnologias Utilizadas

- **Java Server Faces (JSF)** com RichFaces para a camada de apresentação.
- **Spring Framework** para a camada de negócio e injeção de dependência.
- **Hibernate** para mapeamento objeto-relacional (ORM) e acesso ao banco de dados.
- **HQL (Hibernate Query Language)** para consultas ao banco de dados.

## Funcionalidades

### Cadastro de Projetos

- Permitir aos usuários adicionar novos projetos, incluindo os campos de título, descrição e data de início.

### Listagem de Projetos

- Exibir uma lista de todos os projetos cadastrados, com opções para visualizar, editar e excluir cada um.

### Cadastro de Tarefas

- Permitir aos usuários adicionar novas tarefas para um projeto existente, incluindo os campos de título, descrição, prioridade (Muito Alta, Alta, Baixa e Muito Baixa) e estimativa em horas.

### Listagem de Tarefas

- Exibir uma lista de todas as tarefas associadas a um projeto, com opções para visualizar, editar e excluir cada uma.

## Requisitos Técnicos

- Utilize o padrão de projeto **MVC (Model-View-Controller)** para estruturar a aplicação.
- Utilize **RichFaces** para aprimorar a interface do usuário com componentes ricos.
- Configure o **Spring** para realizar a injeção de dependências nos beans gerenciados pelo JSF.
- Utilize o **Hibernate** para mapear as entidades Projeto e Tarefa para o banco de dados, estabelecendo o relacionamento entre elas.
- Implemente consultas **HQL** para as operações de adição, edição, remoção e listagem de projetos e tarefas.

## Estrutura do Projeto

```plaintext
src
├── main
│  ├── java
│  │  ├── config
│  │  │  ├── HibernateConfig.java
│  │  │  ├── SpringConfig.java
│  │  ├── controller
│  │  │  ├── ProjetoController.java
│  │  │  ├── TarefaController.java
│  │  ├── dto
│  │  │  ├── ProjetoDTO.java
│  │  │  ├── TarefaDTO.java
│  │  ├── model
│  │  │  ├── Projeto.java
│  │  │  ├── Tarefa.java
│  │  ├── repository
│  │  │  ├── ProjetoRepository.java
│  │  │  ├── TarefaRepository.java
│  │  ├── service
│  │  │  ├── ProjetoService.java
│  │  │  ├── TarefaService.java
│  ├── resources
│  │  ├── META-INF
│  │  │  ├── persistence.xml
│  ├── webapp
│  │  ├── WEB-INF
│  │  │  ├── projeto
│  │  │  │  ├── editarProjeto.xhtml
│  │  │  │  ├── projetoDetalhes.xhtml
│  │  │  │  ├── projetos.xhtml
│  │  │  ├── tarefa
│  │  │  │  ├── editarTarefa.xhtml
│  │  │  │  ├── tarefaDetalhes.xhtml
│  │  │  │  ├── tarefas.xhtml
│  │  │  ├── beans.xml
│  │  │  ├── faces-config.xml
│  │  │  ├── applicationContext.xml
│  │  │  ├── web.xml
│  │  ├── index.xhtml
│  │  ├── application.properties
```
# Gerenciamento de Projetos e Tarefas

## Configuração do Projeto

### Pré-requisitos

- JDK 8 ou superior
- Maven
- MySQL

### Banco de Dados

1. Crie um banco de dados no MySQL chamado `gerenciamento_projetos_tarefas`.

### Configuração do Driver MySQL no WildFly

1. Crie a estrutura de diretórios para o módulo do MySQL:
```sh
mkdir -p $JBOSS_HOME/modules/com/mysql/main
```
2. Baixe o driver MySQL (por exemplo, mysql-connector-java-8.0.28.jar) e coloque-o no diretório criado ($JBOSS_HOME/modules/com/mysql/main).
3. Crie o arquivo module.xml dentro do diretório main com o seguinte conteúdo:
```xml
   <?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.5" name="com.mysql">
    <resources>
        <resource-root path="mysql-connector-java-8.0.28.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```
Configuração do standalone.xml

1. Abra o arquivo standalone.xml que está localizado no diretório de configuração do WildFly ($JBOSS_HOME/standalone/configuration).
2. Adicione o datasource para MySQL no bloco <datasources>:
```xml
<datasources>
    <datasource jndi-name="java:jboss/datasources/gerenciamentoDS" pool-name="gerenciamentoDS" enabled="true" use-java-context="true">
        <connection-url>jdbc:mysql://localhost:3306/gerenciamento_projetos_tarefas?useSSL=false&amp;serverTimezone=UTC</connection-url>
        <driver>mysql</driver>
        <security>
            <user-name>SEU_USUARIO</user-name>
            <password>SUA_SENHA</password>
        </security>
        <validation>
            <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
            <validate-on-match>true</validate-on-match>
            <background-validation>false</background-validation>
        </validation>
        <timeout>
            <idle-timeout-minutes>5</idle-timeout-minutes>
            <set-tx-query-timeout>true</set-tx-query-timeout>
            <blocking-timeout-millis>5000</blocking-timeout-millis>
            <query-timeout>60</query-timeout>
            <use-try-lock>60</use-try-lock>
            <allocation-retry>2</allocation-retry>
            <allocation-retry-wait-millis>5000</allocation-retry-wait-millis>
        </timeout>
        <statement>
            <prepared-statement-cache-size>32</prepared-statement-cache-size>
            <share-prepared-statements>true</share-prepared-statements>
        </statement>
    </datasource>
    <drivers>
        <driver name="mysql" module="com.mysql">
            <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
            <xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
        </driver>
    </drivers>
</datasources>
```
Certifique-se de substituir SEU_USUARIO e SUA_SENHA pelas suas credenciais do MySQL.

Build e Execução

1. Clone o repositório:
```sh
git clone https://github.com/Leonardodevcoder/gerenciamento-projetos-tarefas.git
cd gerenciamento-projetos-tarefas
```
2. Compile o projeto usando Maven:
```sh
mvn clean install
```
3. Execute o servidor de aplicação WildFly e faça o deploy do arquivo WAR gerado.

4. Acesse a aplicação em:
```sh
http://localhost:8080/gerenciamento-projetos-tarefas
```
