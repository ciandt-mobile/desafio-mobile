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
 
# BOA SORTE!

## OBSERVACOES FINAIS
 Fiz utilização das libs:
 - Retrofit e HTTP3: Consumo das API REST e Logging 
 - Lifecycle: databiding
 - Glide: carregamento de imagens
 - JUnit, Androidx, mockito e mockwebserver: Tests
 
 Fiz utilizaçao de Kotlin por ser a linguagem principal hoje para android, optei por utilizar MVVM como arquitetura pois alem de ser a mais recomendada para android tambem é uma boa opcao para atender a Regra de negocio onde o app deve perimitir ser utilizado em Landscape. Pensando na regra de negocio que eu deveria filtrar a listagem de upcoming para mostrar o popular, infelizmente nao pude fazer essa abordagem por limitacao da API The Movie DB, entao fiz um viewPager onde cada fragment chama um endpoint da api com os respectivos resultados, realizando assim para o usuario final a filtragem
 
 
 
