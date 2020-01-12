import 'package:flutter/material.dart';
import 'package:movieapp/core/blocs/movie_list_bloc.dart';
import 'package:movieapp/core/models/list.dart';
import 'package:movieapp/core/models/movies/movie.dart';
import 'package:movieapp/ui/screens/movie_list_screen.dart';

class MoviesList extends StatefulWidget {
  final ListType listType;

  MoviesList({this.listType});

  @override
  _MoviesListState createState() => _MoviesListState();
}

class _MoviesListState extends State<MoviesList> {
  ScrollController _controller = ScrollController();
  int _page = 1;
  @override
  void initState() {
    _controller.addListener(_scrollListener);
    widget.listType == ListType.Popular
        ? bloc.fetchPopularMovies()
        : bloc.fetchUpcomingMovies();
    super.initState();
  }

  _scrollListener() {
    if (_controller.offset >= _controller.position.maxScrollExtent &&
        !_controller.position.outOfRange) {
      setState(() {
        _page += 1;
        widget.listType == ListType.Popular
            ? bloc.fetchPopularMovies(page: _page)
            : bloc.fetchUpcomingMovies(page: _page);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: StreamBuilder<List<Movie>>(
        stream: bloc.allMovies,
        builder: (ctx, snapshot) {
          if (snapshot.hasData) {
            final list = snapshot.data;

            return Container(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Text(
                      widget.listType == ListType.Popular
                          ? "Popular Movies"
                          : "Upcoming Movies",
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: 32,
                          fontWeight: FontWeight.w700),
                    ),
                  ),
                  MovieItem(
                    list: list,
                    controller: _controller,
                  )
                ],
              ),
            );
          }
          return Align(
            alignment: Alignment.center,
            child: SizedBox(
                height: 32,
                width: 32,
                child: CircularProgressIndicator(
                  valueColor: AlwaysStoppedAnimation<Color>(Colors.red),
                )),
          );
        },
      ),
    );
  }
}
