## Estrutura de arquivos do Projeto

src.main.java.tartagliaeg.tmdb
- bootstrap - Arquivos de inicialização do Projeto 
  - Mesmo existindo um arquivo de Application criado, não foi necessário customizações para este projeto
- settings - Arquivos de configurações globais do ambiente
  - Somente um arquivo de environment foi usado para este projeto. Nele é agregado configurações constantes geradas em tempo de compilação.
- tools - Pacote para arquivos utilitários puros (Que não dependem de classes do android)
- tools_android - Pacote para arquivos utilitários impuros (Que dependem de classes do android)
- components - Pacote para arquivos de UI customizado e de uso genérico
- domain - Pacote raiz dos domínios da aplicação 
  - Todo e qualquer arquivo associado a um domínio de negócio estará abaixo deste diretório
- domain.catalog - Primeiro (e único, neste caso) pacote de domínio
  - Agrupador o domínio de negócio: Catálogo de Filmes. Nesta estrutura, todo conteúdo abaixo deste domínio só deve ser consumido por ele mesmo.
- domain.catalog.listing - Primeiro use-case do domínio de catálogo
  - Aqui são armazenados os arquivos responsáveis pelo funcionamento da tela de Listagem de Catálogo 
- domain.catalog.details - Segundo use-case do domínio de catálogo
  - Aqui são armazenados os arquivos responsáveis pelo funcionamento da tela de Detalhes de Item de Catálogo 
- domain.catalog.interactor - Agrupamento das regras de negócio do módulo de catálogo
  - Os componentes aqui devem ser puros, imutáveis, com uma única responsabilidade e desacoplados de qualquer framework. 
  - Todos os componentes aqui são implementados como Transformers do Rx

src.test.java.tartagliaeg.tmdb
- domain.catalog.interactor - Testes unitários para os Interactors
  - Nem todos os interactors foram testados. Existe um teste para a ordenação simples, um teste para interactor com acesso à repositório e um teste para interactor com acesso à cache


## Arquitetura 
Como arquitetura, foi escolhido uma simplificação da clean architecture com o Presenter (do MVP) fazendo o intermédio entre os interactors e views.

Temos então um fluxo iniciado na View que requisita/notifica o Presenter e este, por sua vez, utiliza um ou mais interactors para produzir o resultados para a View.

Todo use-case Possui um arquivo de contrato que expõem estruturas e interfaces utilizadas pelas suas implementações. Além disso, existe um arquivo de módulo que resolve todas as dependências desse fluxo. Por questões de simplicidade, nenhuma biblioteca para injeção de dependências foi usada neste projeto. 

Para acesso à dados, o padrão de repository foi usado. Neste caso, como o consumo de dados é feito exclusivamente na API do TMDB, os repositórios se tornaram uma camada fina em cima das APIs do Retrofit.

O RxJava foi usado como fundamento na construção de alguns componentes. Interactors, por exemplo, são expostos como Transformers para que seja possível encadear suas chamadas em um stream de dados:
```java
        Single.just(module.repository)
            .compose(retrieveCachedUpcomingCatalogPage(pageNumber, module.cache))
            .compose(resolveImagesForCatalogPage(imageDimensions))
            .compose(sortByDateCatalogPage())
            .subscribe(object:SingleObserver<TMDBPage<Movie>> {...}
    
```

Quanto ao ambiente do android: 
- Foi usado uma Activity por use-case
- Foi usado Views(android) customizadas como a base de toda tela e como a View (MVP) 
- Foi usado o estado da View (android) para armazenar dados na troca de configuração
- Não foi usado fragmentos como componentes visuais
  - Na verdade, não existems fragmentos em nenhuma parte do projeto, mas na ideia dessa arquitetura eles existiriam como holder do cache (retained instance) que preservam os dados na troca de configuração e, apesar de estar fora do escopo desse projeto, como recursos reutilizáveis que são dependentes do ambiente do android (como permissionamento, por exemplo).    

