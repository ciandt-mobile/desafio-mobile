import 'package:flutter/material.dart';
import 'package:movieapp/core/blocs/movie_details_bloc.dart';
import 'package:movieapp/core/models/movies/movie.dart';

class MovieDetailsScreen extends StatefulWidget {
  static const routeName = "movies/details";

  @override
  _MovieDetailsScreenState createState() => _MovieDetailsScreenState();
}

class _MovieDetailsScreenState extends State<MovieDetailsScreen> {

  var _init = true;

  @override
  void initState() {
  
    super.initState();
  }

  @override
  void didChangeDependencies() {
    if (_init == true) {
      final movieId = ModalRoute.of(context).settings.arguments as int;
      blocDetails.getMovie(id: movieId);
    }
    _init = false;
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        height: MediaQuery.of(context).size.height,
        color: Colors.black87,
        child: SafeArea(
          child: StreamBuilder<Movie>(
            stream: blocDetails.movie,
            builder: (ctx, snapshot) {
              if (snapshot.hasData) {
                final movie = snapshot.data;
                return SingleChildScrollView(
                  child: Column(
                    mainAxisSize: MainAxisSize.max,
                    children: <Widget>[
                      DetailsHeader(movie: movie),
                      Container(
                        padding: const EdgeInsets.symmetric(horizontal: 16),
                        color: Colors.black12,
                        child: Padding(
                          padding: const EdgeInsets.symmetric(vertical: 8),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: <Widget>[
                              DetailsTitle(movie: movie),
                              DetailsGenres(movie: movie),
                            ],
                          ),
                        ),
                      ),
                      DetailsCast(movie: movie),
                      Container(
                        padding: const EdgeInsets.symmetric(horizontal: 16),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            Padding(
                              padding: const EdgeInsets.only(bottom: 4),
                              child: Text(
                                "Overview",
                                style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 18,
                                    fontWeight: FontWeight.w600),
                              ),
                            ),
                            Padding(
                              child: Divider(
                                height: 2,
                                color: Colors.white,
                              ),
                              padding: const EdgeInsets.only(bottom: 8),
                            ),
                            Text(
                              movie.overview,
                              style: TextStyle(color: Colors.white54),
                            ),
                          ],
                        ),
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
                        valueColor: AlwaysStoppedAnimation<Color>(Colors.red))),
              );
            },
          ),
        ),
      ),
    );
  }
}

class DetailsCast extends StatelessWidget {
  const DetailsCast({
    Key key,
    @required this.movie,
  }) : super(key: key);

  final Movie movie;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(bottom: 4),
            child: Text(
              "Cast",
              style: TextStyle(
                  color: Colors.white,
                  fontSize: 18,
                  fontWeight: FontWeight.w600),
            ),
          ),
          Padding(
            child: Divider(
              height: 2,
              color: Colors.white,
            ),
            padding: const EdgeInsets.only(bottom: 8),
          ),
          SizedBox(
            height: 200,
            child: ListView.separated(
              separatorBuilder: (ctx, int index) => SizedBox(
                width: 10,
              ),
              physics: ClampingScrollPhysics(),
              shrinkWrap: true,
              scrollDirection: Axis.horizontal,
              itemCount: movie.credits.cast.length,
              itemBuilder: (ctx, index) {
                final actor = movie.credits.cast[index];
                return Container(
                  height: 200,
                  child: Stack(
                    children: <Widget>[
                      Positioned.fill(
                        child: Align(
                          alignment: Alignment.center,
                          child: CircularProgressIndicator(
                              valueColor:
                                  AlwaysStoppedAnimation<Color>(Colors.red)),
                        ),
                      ),
                      if (actor.profilePath != null)
                        Image.network(
                          "https://image.tmdb.org/t/p/w200/${actor.profilePath}",
                          height: 200,
                        ),
                      Positioned.fill(
                          child: Align(
                        child: Container(
                          padding: const EdgeInsets.all(4),
                          decoration: BoxDecoration(
                              border: Border(
                                  bottom:
                                      BorderSide(color: Colors.red, width: 3)),
                              color: Colors.black45),
                          margin: const EdgeInsets.only(bottom: 6),
                          child: Text(
                            actor.name,
                            overflow: TextOverflow.ellipsis,
                            style: TextStyle(color: Colors.white),
                          ),
                        ),
                        alignment: Alignment.bottomCenter,
                      )),
                    ],
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

class DetailsGenres extends StatelessWidget {
  const DetailsGenres({
    Key key,
    @required this.movie,
  }) : super(key: key);

  final Movie movie;

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        ...movie.genres
            .map((genre) => Row(
                  children: <Widget>[
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 2),
                      child: Text(
                        genre.name,
                        style: TextStyle(color: Colors.grey),
                      ),
                    )
                  ],
                ))
            .toList(),
        SizedBox(
          width: 8,
        ),
        Text("${movie.runtime}m", style: TextStyle(color: Colors.grey)),
      ],
    );
  }
}

class DetailsTitle extends StatelessWidget {
  const DetailsTitle({
    Key key,
    @required this.movie,
  }) : super(key: key);

  final Movie movie;

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Flexible(
          child: Text(
            movie.title,
            maxLines: 1,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(
                color: Colors.white, fontSize: 28, fontWeight: FontWeight.w600),
          ),
        ),
        SizedBox(
          width: 8,
        ),
        Text(
          movie.formatedYear,
          textAlign: TextAlign.center,
          overflow: TextOverflow.fade,
          style: TextStyle(
              color: Colors.grey, fontSize: 18, fontWeight: FontWeight.w300),
        )
      ],
    );
  }
}

class DetailsHeader extends StatelessWidget {
  const DetailsHeader({
    Key key,
    @required this.movie,
  }) : super(key: key);

  final Movie movie;

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        Positioned.fill(
          child: Align(
            alignment: Alignment.center,
            child: CircularProgressIndicator(
                valueColor: AlwaysStoppedAnimation<Color>(Colors.red)),
          ),
        ),
        Container(
          child: Image(
            width: double.infinity,
            fit: BoxFit.contain,
            image: NetworkImage(
                "https://image.tmdb.org/t/p/w500/${movie.backdropPath}"),
          ),
        ),
        Row(
          children: <Widget>[
            IconButton(
              padding: EdgeInsets.only(left: 16.0),
              onPressed: () => Navigator.pop(context),
              icon: Icon(Icons.arrow_back),
              iconSize: 30.0,
              color: Colors.red,
            ),
          ],
        ),
      ],
    );
  }
}
