## Solução do desafio

Linguagem: Kotlin
Arquitetura: MVVM

Para realizar a requisição dos dados ao servidor do The Movie DB, utilizei o Retrofit em conjunto do ReactiveX para obter um recebimento assíncrono.
De forma a otimizar o carregamento dos dados, fiz uso da estrutura de paginação, tornando o carregamento sob demanda.
Por meio do uso do ViewModel provido pelo Architeture Component, fiz a separação da camada lógica da camada visual, como proposto pelo arquitetura MVVM. Com isso, tornando os dados Lifecycle Aware, mantendo as informações durante o processo de rotação da tela.
No processo de Injeção de dependência, utilizei o Koin. Que apesar de ser menos robusto, possui uma maior facilidade de configuração em relação ao Dagger2. E para a demanda do projeto supriu todas as necessidades.
Para a apresentação nas view fiz a integração do Data Binding com o LiveData para a redução do boilerplate na construção da view. E no carragemento das imagens, utilizei a biblioteca Picasso.
E para os testes, utilizei o Mockito para ter o controle sobre os objetos testados, além de testar somente a classe desejada. Para melhorar a legibilidade dos testes, utilizei as ferramentas MockitoKotlin2 e o Truth.

### Para a compilação do projeto é necessário alterar o arquivo Endpoint, e adicionar uma Key válida na variável API_KEY.




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
 
 
