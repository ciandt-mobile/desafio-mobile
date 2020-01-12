import 'dart:async';
import 'package:movieapp/core/models/list.dart';
import 'package:movieapp/core/models/state.dart';
import 'movie_api_provider.dart';

class Repository {
  final moviesApiProvider = MovieApiProvider();

  Future<State> fetchPopularMovies({page}) =>
      moviesApiProvider.fetchMovieList(page: page, listType: ListType.Popular);

  Future<State> fetchUpcomingMovies({page}) =>
      moviesApiProvider.fetchMovieList(page: page, listType: ListType.Upcoming);

  Future<State> fetchMovie({id}) => moviesApiProvider.fetchMovie(id: id);
}
