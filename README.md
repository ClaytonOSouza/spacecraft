# spacecraft

## Configurações necessárias após clonar o projeto

    - Criar diretório "/opt/spacecraft" e atribuir permissão chmod -R 777 no diretório spacecraft.
    - Em seguida criar um link simbólico dentro do diretorio spacecraft que aponta para o diretorio src/main/resources 
    do submodulo spacecraft-services do projeto spacecraft. Este link deve ser chamado de conf.
    Desta forma seu diretório ficará da seguinte forma: "/opt/spacecraft/conf"
    Este diretório será responsável pelos arquivos de configuração do seu projeto.
    
    - Criar diretório "/var/log/spacecraft" e atribuir permissão chmod -R 777 no diretório spacecraft.
    Neste diretório irão ficar os logs da aplicação.

## Instancia do Redis

	- O projeto spacecraft utiliza uma instancia do redis, por tanto basta subir uma instancia simples do redis na
	porta 6379.

## Executar o buil e start do projeto

	- O projeto spacecraft utiliza o maven, por tanto para fazer o build basta o simples comando do maven 
	"mvn clean install" na raiz do projeto.
	
	- O projeto está configurado para subir uma instância do jetty. Para isso na raiz do submodulo 
	spacecraft-services executar o seguinte comando: "mvn jetty:run -Djetty.port=8080" 


## Arquivo settings.xml do maven

	- Para facilitar caso haja a necessidade de configurar seu arquivo com repositórios específicos do maven,
	segue as configurações padrões que foram utilizadas durante o desenvolvimento.
###
	 <profiles>
    <profile>
      <id>maven-repository</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <repositories>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>java.net-Public</id>
          <name>Maven Java Net Snapshots and Releases</name>
          <url>https://maven.java.net/content/groups/public/</url>
        </repository>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>jboss.org-maven</id>
          <url>http://repository.jboss.org/maven2/</url>
        </repository>
      </repositories>
    </profile>
  </profiles>

## Executando a aplicação

	- Após todas as configurações realizadas e realizado o start do jetty já podemos executar a aplicação. 
	
	- Para isso você precisá realizar uma chamada REST utilizando o verbo POST na seguinte URL.
	
	- localhost:8080/application/spacecraft/commands
	
	- Incluir no HEADER da chamada o content-type - application/json;charset=UTF-8
	
	- Seguem exemplos de JSON que devem ser enviados no body da chamada

### Exemplo 1

	{"listSpacecraft": [
    {
      "coordinateX" : 2,
      "coordinateY" : 1,
	  "cardinalPoint" : "S",
	  "commands" : "LMLMLMLMM",
	  "upperRightX" : 5,
      "upperRightY" : 5
    },
    {
      "coordinateX" : 2,
      "coordinateY" : 4,
	  "cardinalPoint" : "W",
	  "commands" : "MMRMMRMRRM",
	  "upperRightX" : 7,
      "upperRightY" : 7
    }]}

### Exemplo 2
	
	{"listSpacecraft": [
    {
      "coordinateX" : 1,
      "coordinateY" : 2,
	  "cardinalPoint" : "N",
	  "commands" : "LMLMLMLMM",
	  "upperRightX" : 5,
      "upperRightY" : 5
    }]}
