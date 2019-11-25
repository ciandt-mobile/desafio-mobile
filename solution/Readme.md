# desafio-mobile 2019

## MainActivity
Na MainActivity há um TabLayout integrado a um ViewPager que contém as seções a lista de filmes das categorias principais: 

![Main Actvity](https://raw.githubusercontent.com/eduardowgmendes/desafio-mobile/master/solution/screenshots/Screenshot_1574691877.png)

### Top Rated
Os filmes mais bem avaliados 

### Popular
Filmes populares 

### Upcoming
Os que estão em pós produção ou já em cartaz

### Now Playing
Os que estão sendo exibidos nos cinemas

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


