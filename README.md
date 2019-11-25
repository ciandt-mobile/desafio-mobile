# desafio-mobile 2019

## MainActivity
Na MainActivity há um TabLayout integrado a um ViewPager que contém as seções a lista de filmes das categorias principais: 

![Main Actvity](https://raw.githubusercontent.com/eduardowgmendes/desafio-mobile/master/solution/screenshots/Screenshot_1574691877.png )    

### Top Rated
Os filmes mais bem avaliados 

### Popular
Filmes populares 

### Upcoming
Os que estão em pós produção ou já em cartaz

### Now Playing
Os que estão sendo exibidos nos cinemas

![Movie Overview Activity](https://raw.githubusercontent.com/eduardowgmendes/desafio-mobile/master/solution/screenshots/Screenshot_1574688374.png)

## Movie Overview 
Ao clicar em algum filme na lista de filmes o usuário é levado à ActivityMovieOverivew
e tem diversas informações sobre o filme selecionado:

![Movie Overview Activity](https://raw.githubusercontent.com/eduardowgmendes/desafio-mobile/master/solution/screenshots/Screenshot_1574688374.png)
  
## Bibliotecas 
Para o desenvolvimento da aplicação foram utilizadas bibliotecas como o 
Picasso para cache, gerenciamento e controle de imagens, o Retrofit para consumo da API do TheMovieDatabase, GSON para parsing das requisições.   
  
## Suporte a orientação Landscape
Para dar suporte às duas orientações foram criados diversos layouts independentes com qualificadores de densidade de pixels, tamanhos e orientações. 
Sendo assim é esperado que em cada dispositivo haja uma visualização em correspondência e adequação. 

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
 
 
