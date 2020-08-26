----------------
<i> Utilizações e implementação</i>

- Volley - Requisições  
- Jackson - Json unmarshalling  
- Arquitetura - Model-View-Presenter  
- Linguagem - Java  
- LiveData, Databinding, RecyclerView 
 
---
<i> Considerações </i>

- A lista dos filmes que entrarão em cartaz em breve é obtida filtrando a lista dos filmes populares, já que é um requisito de implementação. Como a lista dos filmes populares é paginada, é necessário carregar várias páginas para popular a lista dos que ainda entrarão em cartaz. Seria mais interessante fazer as requisições dos filmes que entrarão em breve separadamente, já que a API disponibiliza essa opção.  
- São disponibilizadas as linguagens inglês e português, dependendo das configurações do celular.  
- A Stack utilizada pelo Volley (Cronet) está em desenvolvimento. Em versões posteriores seria interessante utilizar releases estáveis.  

----------------

# Desafio Mobile - 2019

Bem Vindo ao desafio mobile da CI&T, por favor siga as instruções a baixo para realizar o desafio 😀.

## Instruções

- Faça um fork desse repositório e crie sua solução para iOS ou Android;
- Ao terminar a solução realize um Pull Request;
- Comente no readme do repositório os métodos utilizados, tais como arquitetura, linguagem, soluções técnicas e etc.;

## O com.ciet.leogg.filmes.App

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