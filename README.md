# Desafio Mobile - 2019

Como no meu trabalho nós utilizamos Scrum (nem sempre, mas tentamos) deixarei para debater problemas e soluções adotadas ao descrever as "Tasks", que estão separadas por dia.
Primeiro,
Linguagem eu utilizei Kotlin. Porquê? Porque eu gosto bastante dessa linguagem (e é o trending topic de Android atualmente), e optei por ter o máximo de contato possível com ela.
Arquitetura eu tentei seguir MvvM, mas minha experiência com MvvM é zero (de arquitetura no meu trabalho utilizamos MvP, mas somente para 2 produtos em um total de 8). Nunca utilizei em projetos, por isso tem falhas nesse aspecto. Mesmo assim, decidi tentar seguir para ao menos ter algum ganho de experiência nesse aspecto também!
Ao escolher tipos de objeto
Agora vamos as soluções/problemas/planning:
Considerei o dia de trabalho como 4 horas, pois era o tempo que eu tinha para trabalhar no desafio. Também só possuo 4 dias de "Sprint" porque eu trabalhei no desafio nos períodos noturnos.

#Day1
Work Log: 4h 45m
Estimado: 4h
Main Goal:
Receber uma lista de filmes e popular um adapter com os filmes.
Layout básico com recyclerView para ao menos mostrar esses dados. 

Para receber a lista de filmes, foram utilizados o Retrofit, Moshi como conversor (eu utilizei o Moshi pois da última vez que fiz um projeto com Retrofit, eu utilizei o GSON, e eu acho bom ter contato com outras coisas), okHttp para fazer os requests.
Para pegar a lista de filmes foi seguido esse fluxo:
MovieViewModel -> @Repository.getPopularMovies -> retorna uma lista de filmes.

Ao criar e checkar o objeto Json eu vi de cara dois problemas. Um a data está como String (problema para fazer o filtro, pois teria que fazer sortByDate) e o elenco não estava presente no Json (problema no details). Como a data era mais simples e o elenco era algo para a activity que exibiria os detalhes, eu comecei arrumando a data.
Eu utilizei instâncias de calendário e etc, porque o parse de String para Data só funcionaria para API ≥ 26, e quando iniciei o projeto eu tive como meta o Lollipop (21) (tentei pensar caso fosse um app em produção, perderíamos muitos usuários).
#Day2
Work Log: 3h30m
Estimado: 4h
Main Goal:
Criar nova tela para exibir os detalhes dos filmes. 
Implementar métodos no repositório para que seja possível retornar somente um filme.
Exibir os detalhes dos filmes.

 Para implementar os novos métodos, foi bem simples. Apenas foi passado o id como property e adicionado no path da requisição. Para a resposta também foi colocado como resultado esperado o objeto "movie". O mais "chatinho" foi adaptar o BaseRepository para receber respostas de qualquer tipo (uso de Generics). 
Tudo certo, temos o id, o filme, só chamar a nova activity e pronto. Certo?
NÃO!
Um erro que eu não tinha visto, é que o gênero do filme, era na verdade um Array de objetos, e no ínicio eu havia colocado como String. Parece simples "Oras, basta trocar para um array de objetos", pois é, mas no momento me deu certa guerra pensar nisso.
Criado o objeto gênero, e tendo seu retorno corretamente, foi possível terminar o DetailsActivity. O layout das duas activities nesse ponto estavam bem crus, pois eu havia planejado arrumar tudo de uma vez no dia 3!
#Day3
Work Log: 3h45m
Estimado: 4h
Tasks
Arrumar os layouts das duas activities.
Implementar os clicks nos cardViews.
Criar o RecyclerView para o Elenco.
Receber corretamente o elenco como resposta.



"Arrumar Layouts" nesse caso seria, colocar todo mundo como constraints. Alinhas os elementos via Constraints e colocar cores e clicks.
Até esse momento eu achava que o elenco estava no Json, mas talvez com outro nome ou coisa do tipo. Procurando sobre como conseguir o elenco usando a API do TMDB, me deparei com o "append_to_results=credits" no fim de uma requisição. Testei esse append no fim da URL com o PostMan, e recebi com sucesso a lista.
A problemática nesse caso, foi, como inserir o append em uma requisição retrofit. Passei um bom tempo quebrando a cabeça com isso, pois achei que a requisição deveria estar sempre no fim da url, então não poderia usar o "addQueryParameter" para esse fim. No entanto estava enganado, pode sim.
Recebendo o elenco, foi necessário criar outros dois objetos o "CastPerson" e o "Credits". O Credits nada mais é que uma lista de "castPersons".
Ao implementar o elenco no recyclerView, foi notado um pequeno bug, ao estar acostumado a tanto trabalhar com recycler view e seu scroll automático não me atentei que os textView (principalmente para a sinopse) estavam sem scroll. Então foi necessário implementar o ScrollView, confesso que fiz meio "GoHorse" essa implementação, acredito que daria para deixar o layout mais simples (isso ficou como um "to the future").


#Day4
Work Log: 5h
Estimado: 4h
Task
Criar IntegrationTests para os métodos de requisição de filmes.
Criar EspressoTests para UI.

Eu não tenho muita experiência com UnitTests nem Integração (na verdade minha experiência com esse tipo de teste, vem só de manutenções pontuais nos mesmos).
Os testes de integração e Espresso eu deixei bem simples, até por conta do tempo restante para terminar a tarefa.
Concordo que deveria ter melhorado a cobertura dos testes no projeto.
Para criar os testes, eu tive bastante problemáticas no caminho. Desde libs que deveriam ser testImplementation ao invés de androidTestImplementation até mesmo para utilizar um observable "mockado".
===================================
Conclusão:
Eu gostei MUITO do desafio.

O que achei muito interessante foi o livre arbítrio para tomar qualquer decisão, ou usar qualquer implementação ao meu gosto.
Isso me trouxe um ganho de conhecimento muito grande, pois são coisas que quando estamos desenvolvendo algo de forma acadêmica/estudo não damos muita atenção (principalmente questões de arquitetura e TDD).
Antes do desafio eu não entendia tão bem o conceito de TDD, sempre pensei "Faz o código, depois faz os testes, é simples". Mas no fim, não é não, o que me trouxe desafios nos testes foi justamente essa pensata hehe.
Pois fiz todo o código para aí ir pensando em testes, porém meus métodos não eram testáveis, foi aí que tive que usar interfaces para repositório, e aí que tive aquele snap mental de "Isso que é TDD".
Como foi possível notar ao longo das minhas decisões, eu tenho uma tendência forte em tentar coisas novas. 
Tanto pelo desafio como também para aprender coisas novas!
Espero contato futuro!
Desde já, muito obrigado!
 
