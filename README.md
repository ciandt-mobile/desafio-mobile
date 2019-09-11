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
 - Uma imagem em alta resolução;
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
 
 ## Tecnologias utilizadas no projeto
 
 -- Linguagem
 A aplicação foi inteiramente construída na linguage Kotlin na qual auxilía na manutenabilidade do app e do código visto que a linguaguem abstrai a verbosidade de java e ainda incrementa pontos de melhoria como a garantia de valores não nulos (null safety).
 
 -- Arquitetura
  A arquitetura segue o conceito MVVM onde o ViewModel não conhece a camada de View, e a comunicação de valores é através de LiveData. 
  No que se refere ao Model foi utilizado o Repository Pattern para a criação de camadas de dados, existindo uma camada responsável pelo forncimento de dados ( Respository ) e outra que de fato obtem os dados de um local específico sendo local ou remote ( DataSources ). Ao solicitar um dado ao repository, ele possui essa lógica de escolha se os dados virão de forma remota ou de forma local. Além destas 2 camadas existe uma terceira que não foi necessária neste projeto mas que é recurso do "Repository Patter" que é o "UseCase", esta camada fica antes do "Repository" e nela existem regras de negócio específicas do aplicativo e ele é quem retorna os dados para um ViewModel. Através de um fluxograma a arquitetura ficaria da seguinte forma:
                                                   
                                                 / LocalDataSource
      View -> ViewModel -> UseCase -> Resository
                                                 \ RemoteDataSource


                                                 


  
 
# BOA SORTE!
 
 
