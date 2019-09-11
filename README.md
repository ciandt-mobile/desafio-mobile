# Desafio Mobile - 2019

Bem Vindo ao desafio mobile da CI&T, por favor siga as instruções a baixo para realizar o desafio 😀.

## Instruções

- Faça um fork desse repositório e crie sua solução para iOS ou Android;
- Ao terminar a solução realize um Pull Request;
- Comente no readme do repositório os métodos utilizados, tais como arquitetura, linguagem, soluções técnicas e etc.;

## O App

<img src="screenshots/ss01.png?raw=true" width="250"> <img src="screenshots/ss02.png?raw=true" width="250"> <img src="screenshots/ss03.png?raw=true" width="250">

#### Filmes Populares

Como usuário, gostaria de ver a lista de de filmes mais populares em cartaz nos cinemas. Os itens dessa lista deverão conter:
 - O banner do filme;
 - O nome do filme;
 - A data de estreia;

#### Em Breve

Como usuário, gostária de conseguir filtrar a lista de filmes populares para que agora mostre os filmes que entrarão em cartaz em breve:
 - O Filtro deverá ser aplicado na lista já existente, implementada na task anterior

#### Detalhes do filme

Como usuário, ao selecionar um item da lista, gostaria de ver os detalhes do filme:

 - Uma imagem em alta resolução
 - Nome do filme
 - Generos do filme
 - Ano de estreia
 - Duração
 - Elenco principal 
 - Sinópse
 
## Requisitos
 - O app deve ser desenvolvido para suportar as orientação Portrait e Landscape
 - Seja criativo, as imagens de referência são apenas exemplos, você pode criar seu próprio layout
 - Use libs e frameworks que você estiver mais acostumado
 - Teste o seu código ;D
 - Utilize a The Movie Database para realizar as consultas 
 -- TMDB (https://www.themoviedb.org)
 -- API (https://www.themoviedb.org/documentation/api)
 
 ## Tecnologias e soluções utilizadas no projeto
 
 - Linguagem
 
 A aplicação foi inteiramente construída na linguage Kotlin na qual auxilía na manutenabilidade do app e do código visto que a linguaguem abstrai a verbosidade de java e ainda incrementa pontos de melhoria como a garantia de valores não nulos (null safety).
 
 - Arquitetura
 
A arquitetura segue o conceito MVVM onde o ViewModel não conhece a camada de View, e a comunicação de valores é através de LiveData. 
No que se refere ao Model foi utilizado o Repository Pattern para a criação de camadas de dados, existindo uma camada responsável pelo forncimento de dados ( Respository ) e outra que de fato obtem os dados de um local específico sendo local ou remote ( DataSources ). Ao solicitar um dado ao repository, ele possui essa lógica de escolha se os dados virão de forma remota ou de forma local. Além destas 2 camadas existe uma terceira que não foi necessária neste projeto mas que é recurso do "Repository Patter" que é o "UseCase", esta camada fica antes do "Repository" e nela existem regras de negócio específicas do aplicativo e ele é quem retorna os dados para um ViewModel. Através de um fluxograma a arquitetura ficaria da seguinte forma:
  
```
                                                 / LocalDataSource
      View -> ViewModel -> UseCase -> Resository
                                                 \ RemoteDataSource
```
     
- Clean Artchitecture

Todas as implementações e segmentações seguem o conceito de implementação do "Clean Artchitecture", visando a separação de responsabilidades para cada camada e a somente a comunicação entre elas.
 
 - Injeção de dependência
 
Foi utilizando injeção de dependêcias através do Koin para facilitar e simplificar a necessidade de passagem parâmetros para classes com dependências. Para este projeto escolhi utilizar o Koin pois sua implementação é mais simples e possui menos boilerplate, este projeto não é um projeto grande na qual o Koin irá impactar na performance visto que ele realiza as injeções em tempo de execução ( isto pode ser um problema para grandes apps ). A alternativa para app's realmente grandes e que a performance deve ser realmente considerada é a utilização de Dagger 2 que apesar da sua complexidade no uso e no aumento de boilerplate oferece uma perfomance melhor visto que as injeções são feitas em tempo de compilação.
  
- Corotinas   

Corotinas são thread's mais leves que são executadas e gerenciadas em um pool de thread's, foram utilizadas nas realizações de chamadas assíncronas nas chamadas ao servidor de dados.
 
- Abstração de corotinas 

Neste projeto foram utilizadas corotinas utilizando uma abstração para que uma corotina possa ser chamada com uma quantidade menor de boilerplate e que além disso os Job's gerados para cada corotina iniciada podem ser cancelados ao finalizar o ciclo de vida de uma Activity, evitando possíveis "memory leak's". Dessa forma as request's realizadas neste projeto foram todas executadas dentro de um escopo de corotina.
  A abstração também cobre casos de erro onde é realizado um try catch dentro da abstração que gera um UseCaseException que também é uma abstração de erros e dessa forma o erro é recebido no ViewModel sem crashar e com tratamento de erro.

- Interceptor ( Retrofit )

O interceptor é utilizado para interceptar uma request antes de ser encaminhada para o Endpoint, sendo assim possível modificar os valores da request em tempo de execução. Isso facilita no ponto onde é necessário atrelar um token ou alguma informação em todas as requests ou em um conjunto delas, pois caso contrário seria necessário em cada endpoint colocar essa informação.  
Neste projeto o interceptor adiciona um parâmetro que é sempre necessário em todas as requests ao invés de adicionar o parâmetro de forma fixa no código em cada request.

  
- Bibliotecas Utilizadas

    ConstraintLayout ( Biblioteca utilizada para a utilização de layouts com constraints )
    
    RecyclerviewView ( Biblioteca utilizada para a utilização do componente lista reciclável do android )
    
    Picasso ( Biblioteca utilizada para o carregamento das url's de imagens )
    
    Retrofit ( Biblioteca utilizada para abstrair as requisições para a API )
    
    Okhttp ( Biblioteca utilizada para gerenciar requests com o retrofit )
    
    Interceptor ( Biblioteca utilizada para interceptar requests )
    
    Coroutines ( Biblioteca utilizada para o uso de coroutines )
    
    CoroutinesAdapter  ( Biblioteca utilizada para o uso de coroutines )
    
    Koin ( ( Biblioteca utilizada para injeção de dependências )
    
    lottie ( Biblioteca utilizada para realizar animações )


# BOA SORTE!
 
 
