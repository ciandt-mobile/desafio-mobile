# Desafio Mobile - 2019

## Execução do Desafio
-O desafio foi realizado no total de 16 horas em Kotlin com 'compileSdkVersion' e 'targetSdkVersion' = 29(Android Q).
-Devices testados: OnePlus 5T e Samsung S7

## Libs / Components
-SnackBar(Google Material)
-RecyclerView(AndroidX)
-ViewPager
-Lifecycle/ViewModel(AndroidX)
-RxJava/RxAndroid/rxbinding
-Dagger
-Retrofit
-Glide
-Timber
-SwipeRefresh

## Arquitetura
-MVVM
-Programação Reativa

## Descrição / Adicionais do Desafio

#### Home
-Listagem de 'Upcoming','TopRated'(adicional),'Popular'
-Busca de Filmes(adicional)
-Paginação(adicional)

#### Listagem - Item
-Poster
-Data de release
-Rating(adicional)
-Popularidade(adicional)
-Título

#### Detalhes do Filme
-Poster ampliado
-Ano
-Rating(adicional)
-Popularidade(adicional)
-Duração
-Genêros
-Sinopse


### Desenvolvimento
A estrutura base e auxiliar(paths 'projectStructure','utils' e 'factory') são classes e objects que recentemente formam a base dos meus projetos.
A arquitetura aplicada foi a MVVM onde a path 'features' contém a Home e as demais 'Telas/Passos' do Aplicativo. Dentro de cada feature esta organizada basicamente em 
3 paths: model, view, viewModel.

-model:
Models
Adapters
ViewHolders
Repository
Service

-view
Activities
Fragments

-viewModel
ViewModels

Activities extendem BaseActity() e Fragments extemdem BaseFragment().
Essa duas classe possuem metodos base:
-configureView() -> configura atributos da View. Ex: Listeners
-configureViewModel() -> configura a comunicação entre ViewModel e View. 
-bindView() -> Bind dos dados na View

A comunicação de ViewModel e View é feita a partir de um Observer, "escutando" modificações no ViewModelState(classe curtomizada - 'projectStructure') com status(Success e Error)


**SwipeRefresh desabilitado na tab de "Search".
**Pesquisa é realizada enquanto o usuário digita.
