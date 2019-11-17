Arquitetura: MVVM
Linguagem: Kotlin

Data class, utilizei Gson lib para converter nome de atributos vindos da API.
Para tratamento de requisições, API utilizei Retrofit 2 com coroutines, garantindo um controle de respostas assincronas.
Utilizei Koin para injeção de dependências, pela simplicidade e otimo trabalho.
Para garantir o padrão MVVM, foi criado para os Fragmentos Upcoming e Popular a classe view Model, para separar a chamada de dados e levar até a view.
Criei uma activity base, que recebe dois fragmens upcoming e popular, para a seleção de catalogo de filmes.
Para a seleção de fragments coloquei um navigation bottom, para garantir simplicidade e beleza na UX.
Criei um search de pesquisa em no fragment Upcoming, para o usuario filtrar o catalogo.


 
