import 'package:movieapp/core/models/movies/movie.dart';
import 'package:movieapp/core/models/state.dart';

import '../resources/repository.dart';
import 'package:rxdart/rxdart.dart';

class MoviesBloc {
  final _repository = Repository();
  final _moviesFetcher = BehaviorSubject<List<Movie>>();

  Stream<List<Movie>> get allMovies => _moviesFetcher.stream;

  fetchPopularMovies({page = 1}) async {
    State state = await _repository.fetchPopularMovies(page: page);
    if (state is SuccessState) {
      var list = state.value;
      if (page == 1) {
        _moviesFetcher.sink.add(list);
      } else {
        _moviesFetcher.sink.add([..._moviesFetcher.value, ...list]);
      }
    } else if (state is ErrorState) {
      _moviesFetcher.addError(state.msg);
    }
  }

  fetchUpcomingMovies({page = 1}) async {
    State state = await _repository.fetchUpcomingMovies(page: page);
    if (state is SuccessState) {
      var list = state.value;
      if (page == 1) {
        _moviesFetcher.sink.add(list);
      } else {
        _moviesFetcher.sink.add([..._moviesFetcher.value, ...list]);
      }
    } else if (state is ErrorState) {
      _moviesFetcher.addError(state.msg);
    }
  }

  dispose() {
    _moviesFetcher.close();
  }
}

final bloc = MoviesBloc();
