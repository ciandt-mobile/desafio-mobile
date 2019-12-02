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
 

## Comentários
- A arquitetura do aplicativo segue o modelo MVC, onde é separado o código em três camadas: interface, dados e controle.
- O aplicativo foi feito inteiramente com o Xcode 11.2.1, linguagem Swift 5.0 e como target o iOS 13.2
- Foi utilizado somente o framework Alamofire para a comunicação com a API
- Existe um problema sério de segurança: a API key está "hard coded" na aplicação. Eu armazenaria a mesma em um servidor com suporte a SSL. Assim que a aplicação for iniciada pela primeira vez, eu faria uma requisição para esse servidor e salvaria a chave no KeyChain do aparelho.
- Limitação: não houve tempo de fazer o armazenamento em cache das informações coletadas da API, então toda vez que o aplicativo é inicializado, ele irá fazer as requisições que necessita para a API.
- Gostaria de ter feito uma tab bar na tela inicial para que o usuário mude a lista de filmes populares para a de upcoming, mas como o termo de aceite é um filtro da lista previamente coletada, decidi usar o segment control
- O aplicativo suporta orientções portrait e landscape e também o dark mode do iOS 13