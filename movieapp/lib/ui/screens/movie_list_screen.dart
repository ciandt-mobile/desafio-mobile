import 'package:flutter/material.dart';
import 'package:flutter_staggered_grid_view/flutter_staggered_grid_view.dart';
import 'package:intl/intl.dart';
import 'package:movieapp/core/models/list.dart';
import 'package:movieapp/ui/screens/movie_details_screen.dart';
import 'package:movieapp/ui/widgets/movie_list.dart';

class MovieListScreen extends StatefulWidget {
  static const routeName = "movies";
  final bloc;

  MovieListScreen({this.bloc});

  @override
  _MovieListScreenState createState() => _MovieListScreenState();
}

class _MovieListScreenState extends State<MovieListScreen>
    with SingleTickerProviderStateMixin {
  static final GlobalKey<ScaffoldState> scaffoldKey =
      new GlobalKey<ScaffoldState>();

  TabController _controller;

  @override
  void didChangeDependencies() async {
    super.didChangeDependencies();
  }

  @override
  void initState() {
    _controller = new TabController(
      length: 2,
      vsync: this,
    );

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Container(
          width: double.infinity,
          color: Colors.black87,
          child: Column(
            children: <Widget>[
              _tabBar,
              Expanded(
                child: TabBarView(controller: _controller, children: [
                  MoviesList(
                    listType: ListType.Upcoming,
                  ),
                  MoviesList(
                    listType: ListType.Popular,
                  )
                ]),
              )
            ],
          ),
        ),
      ),
    );
  }

  get _tabBar => Container(
        padding: const EdgeInsets.only(top: 16),
        width: MediaQuery.of(context).size.width * 0.5,
        height: 48,
        child: TabBar(
            controller: _controller,
            unselectedLabelColor: Colors.redAccent,
            indicatorSize: TabBarIndicatorSize.tab,
            indicator: BoxDecoration(
                borderRadius: BorderRadius.circular(25),
                color: Colors.redAccent),
            tabs: _tabs),
      );

  get _tabs => const <Tab>[
        const Tab(
          child: Align(
            alignment: Alignment.center,
            child: Text(
              "Upcoming",
            ),
          ),
        ),
        const Tab(
          child: Align(
            alignment: Alignment.center,
            child: Text("Popular"),
          ),
        ),
      ];
}

class MovieItem extends StatelessWidget {
  const MovieItem({
    Key key,
    @required this.list,
    this.controller,
  }) : super(key: key);

  final List<dynamic> list;
  final controller;

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: OrientationBuilder(
          builder: (ctx, orientation) {
            return StaggeredGridView.countBuilder(
              controller: controller,
              itemBuilder: (BuildContext context, int index) {
                final movie = list[index];
                return Container(
                  child: GestureDetector(
                    key: Key(movie.id.toString()),
                    onTap: () => Navigator.pushNamed(
                        context, MovieDetailsScreen.routeName,
                        arguments: movie.id),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Flexible(
                          child: Container(
                            height:
                                orientation == Orientation.portrait ? 180 : 240,
                            width:
                                orientation == Orientation.portrait ? 120 : 180,
                            child: Stack(
                              children: <Widget>[
                                Positioned.fill(
                                  child: Align(
                                    alignment: Alignment.center,
                                    child: CircularProgressIndicator(
                                        valueColor:
                                            AlwaysStoppedAnimation<Color>(
                                                Colors.red)),
                                  ),
                                ),
                                if (movie.posterPath != null)
                                  Image.network(
                                    "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                                    width: orientation == Orientation.portrait
                                        ? 120
                                        : 180,
                                    fit: BoxFit.cover,
                                  ),
                                Positioned.fill(
                                  child: Align(
                                    alignment: Alignment.bottomCenter,
                                    child: Container(
                                      padding: const EdgeInsets.all(4),
                                      decoration: BoxDecoration(
                                          border: Border(
                                              bottom: BorderSide(
                                                  color: Colors.red, width: 3)),
                                          color: Colors.black45),
                                      margin: const EdgeInsets.only(bottom: 6),
                                      child: Text(
                                        DateFormat("dd/MM/yyyy").format(
                                            DateTime.parse(movie.releaseDate)),
                                        style: TextStyle(color: Colors.white),
                                      ),
                                    ),
                                  ),
                                )
                              ],
                            ),
                          ),
                        ),
                        SizedBox(
                          height: 4,
                        ),
                        Text(
                          movie.title,
                          maxLines: 2,
                          style: TextStyle(
                              color: Colors.grey.shade300, fontSize: 14),
                        ),
                      ],
                    ),
                  ),
                );
              },
              crossAxisCount: 3,
              itemCount: list.length,
              staggeredTileBuilder: (int index) =>
                  orientation == Orientation.portrait
                      ? StaggeredTile.count(1, 1.7)
                      : StaggeredTile.count(1, 1),
              mainAxisSpacing: 8.0,
              crossAxisSpacing: 8.0,
            );
          },
        ),
      ),
    );
  }
}
