# Desafio Mobile - 2019

 - Arquitetura: MVVM.
 - Linguagem: Kotlin.

## Soluções técnicas

### Network
 - Para tratamento de requisições, API utilizei Retrofit 2 com coroutines, garantindo um controle de respostas assincronas.
 - Utilizei Koin para injeção de dependências.
 
### Modelo
 - Utilização de Data class e Gson para mspeamento de atributos.
 
### ViewModel
 - Comunicação com a camada network, sendo responsavel pela comunicação com a views.

### Views
 - HomeActivity : Menu de navegação (NavigationBottom) e um container para suportar os fragmentos upcoming e popular.
 - UpcomingFragment : Search para pesquisa no recyclerview, recyclerview com (data estreia, imagem poster e titulo).
 - PopularFragment : Recyclerview, recyclerview com (data estreia, imagem poster e titulo).
 - Detail : Imagem em alta definição, titulo filme, ano estreia, tempo de duração, genero, recyclerview horizontal (nome ator e imagem) e sinopse.
 - Para requisição e tratamento de imagens, utilizei Lib Glide 
 - Construção de UX : Lib RecycvlerView, cardView, constraintlayout e appcompat
 - Controle do ciclo de vida Lib lifecycle
 - Tratamento de erros, com imagem e mensagem e caso de falha na requisição ou, falha de conexão.
 
### Tests
 - Utilizado para testes unitarios mockitokotlin2
 - Injeção de dependecias, utilizado Koin-Test
 - Teste de interface com espresso
 
