# Desafio Mobile - 2019

Bem Vindo ao desafio mobile da CI&T, por favor siga as instruções a baixo para realizar o desafio 😀.

### Tecnologias Utilizadas

Este app foi feito utilizando MVVM-C, com uso das seguintes bibliotecas:

- RxSwift, RxCocoa, RxFlow, RxOptional e RxGesture para programação reativa
- Moya para as requisições da API
- KingFisher para dar fetch nas imagens
- Swinject e SwinjectAutoregistration para injeção de dependência

Known issues:
- Este app só faz fatch da primeira página de movies, pois não foi implementada paginação nesta parte do app.
- O filtro de upcoming só aparece um filme, visto que um dos requisitos do teste era fazer o filtro na lista já existente, sem fazer nenhuma outra requisição. Como buscamos apenas uma página, este é o único filme que ainda não foi lançado.
- O App não mostra nem os atores nem o tamanho do filme. Não encontrei uma forma de fazer esta requisição para a api do themoviedb.
- A página de Movie Details não funciona direito em landscape. O correto era desenvolver esta parte utilizando uma UITableViewController, porém não tive muito tempo para desenvolver esta página como deveria. Acabei deixando ela deste jeito mesmo.


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
 
# BOA SORTE!
 
 
