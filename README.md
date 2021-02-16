# Ultraje API

Não existe uma definição correta sobre o que será o Ultraje, ele nasce da necessidade de aprendizado de novas tecnologias e padrões. O projeto recebe esse nome porque será um ultraje as boas praticas de desenvolvimento, isso porque serão adotados os mais diversos padrões pra problemas similares.

# Get Started

Em breve teremos o script do *Milhouse* (DB do projeto ultraje), ou quem sabe até o Milhouse esteja disponivel na nuvem... Por hora você pode apenas configurar o projeto sem acesso ao banco na sua maquina, o que não serve de nada mas... é facinho, tá na mão o passo a passo:

***git clone https://github.com/rafael-novais/ultraje.git***
Clone o projeto no diretorio de sua preferencia.

***mvn install***
Rode o famigerado maven install no projeto.

***mvn spring-boot:run***
Depois desse comando já era, ultraje estará no ar em seu localhost!

#Tecnologias

Puramente JAVA, a não ser que o ultraje se torne um robusto projeto com arquitetura de micro serviços, mas por hora isso é só um devaneio. Spring Boot será utilizado por conveniência e Maven gerenciando os pacotes por preferencia daquele que vos escreve.

# Documentação e contratos detalhados

Todos os endpoints e contratos do projeto estarão disponiveis através do endereço */swagger-ui.html*. Por disponivel apenas no seu localhost, será ainda estudada a possibilidade de um deploy em nuvem.

# Endpoints

***PRODUTOS:*** Temos um endpoint completo de produtos, por hora é um crud básico, a persistência foi feita implementando o JDBC, temos também um pool de conexões usando C3PO e Mchange.
