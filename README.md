# Desafio Mobile - 2019

Minha solução para o desafio mobile da CI&T para a plataforma Android

## Exemplos das telas
<img src="screenshots/ss01.png?raw=true" width="250"> <img src="screenshots/ss02.png?raw=true" width="250"> <img src="screenshots/ss03.png?raw=true" width="250">

### Recursos técnicos utilizados

* A linguagem de programação utilizada é Kotlin
* Foram utilizados os principais componentes da biblioteca Android Architecture Components, tais como ViewModel e LiveData. Com isso foi possível o desenvolvimento do projeto seguindo o padrão MVVM de arquitetura
* Para navegação entre as telas foi utilizado o Navigation Component
* Para exibição de lista de filmes e de elenco foi utilizada a biblioteca RecyclerView
* Para injeção de dependências foi utilizada a biblioteca Dagger2
* Para tarefas assíncronas foi utilizada coroutines
* Para acesso à API REST do The Movie Database foi utilizada a biblioteca Retrofit. O conteúdo json das responses foi parseado utilizando a biblioteca Gson
* Para download e gerenciamento de imagens foi utilizada a biblioteca Glide
* Para testes foram utilizados JUnit e Espresso

### Descrição da solução
O aplicativo é composto por uma única Activity onde é adicionada a primeira Fragment responsável por adicionar o módulo de Navigation, gerenciando a navegação entre as Fragments.

Existem quatro Fragments principais: 
- HomeFragment contém a view pager para exibir as duas opções de visualização: Upcoming ou Popular
- UpcomingFragment exibe a lista de filmes filtrados pelo critério 'Upcoming'
- PopularFragment exibe a lista de filmes filtrados pelo critério 'Popular''
- MovieDetailFragment exibe o detalhe de um filme selecionado

Com exceção da HomeFragment, todas as Fragments possuem suas respectivas classes ViewModel onde estão localizadas a regras de negócio que controla o que será exibido. Em cada ViewModel é injetada a dependência do repositório de filmes, a partir do qual é possível acessar a API REST de filmes.
