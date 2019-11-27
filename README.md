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
 - Nome do filme;
 - Generos do filme;
 - Ano de estreia;
 - Duração;
 - Elenco principal ;
 - Sinópse;
 
## Requisitos
 - O app deve ser desenvolvido para suportar as orientação Portrait e Landscape
 - Seja criativo, as imagens de referência são apenas exemplos, você pode criar seu próprio layout
 - Use libs e frameworks que você estiver mais acostumado
 - Teste o seu código ;D
 - Utilize a The Movie Database para realizar as consultas 
 -- TMDB (https://www.themoviedb.org)
 -- API (https://www.themoviedb.org/documentation/api)
 
# BOA SORTE!
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Conclusão do desafio

## O App
O Aplicativo desenvolvido contém:
- Arquitetura MVC-N de Networking
- Swift 5
- Auto layout portrait e landscape
- Layout conforme o requisito, com algumas pequenas modificações.
- Teste unitário de algumas funcionalidades(como não tinha muita lógica envolvida, não tinha muito o que fazer).

## Libs e Frameworks
- Cocoapods(Alamofire 5 - para chamadas de API/kingfisher - para download de imagens)
- Postman para testes da API e comparação de parâmetros

## Desenvolvimento
Conforme requisito do usuário o app apresenta o seguinte:

1º Tela de galeria de filmes com banner, nome e data
- Filtro de seleção por filmes: upcoming e popular

2º  Tela contendo os detalhes do filme como: 
- Uma imagem em alta resolução;
- Nome do filme
- Generos do filme
- Ano de estreia
- Duração
- Elenco principal 
- Sinópse
- contem swipe left para voltar para tela anterior

PS: Landscape não fez muito sentido a primórdio em uma idéia como essa de filmes mas até que não ficou tão ruim, achei que o layout ficou até mais bonito que o ingresso.com hehe!

O aplicativo foi desenvolvido de forma que atendesse as necessidades do usuário. Pensando em um sistema de trabalho onde tem um controle de organização maior, dado um time, métricas, ferramentas, designer, pirâmide de testes e etc... pode ser que tenha novos pedidos de melhorias e novas features, assim pensando sempre na qualidade de desenvolvimento.

## Features 
Segui conforme veio o requisito pelo usuário/cliente e como funcionalidades futuras da para implementar o seguinte:
- internacionalização para tradução(como não era requisito não foi implementado)
- testes automatizados(com um fluxo maior da para ser implementado, como é um fluxo pequeno não fazia sentido no momento)

Citei algumas features que podia ser implementadas, porém conforme o tempo foquei em desenvolver as funcionalidades requisitadas.
