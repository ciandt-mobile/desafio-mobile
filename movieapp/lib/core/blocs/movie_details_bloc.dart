import 'package:movieapp/core/models/movies/movie.dart';
import 'package:movieapp/core/models/state.dart';
import 'package:movieapp/core/resources/repository.dart';
import 'package:rxdart/rxdart.dart';

class MovieDetailsBloc {
  final _repository = Repository();
  final _movieFetcher = BehaviorSubject<Movie>();

  Stream<Movie> get movie => _movieFetcher.stream;

  dispose() {
    _movieFetcher.close();
  }

  getMovie({id}) async {
    State state = await _repository.fetchMovie(id: id);
    if (state is SuccessState) {
      _movieFetcher.sink.add(state.value);
    } else if (state is ErrorState) {
      _movieFetcher.addError(state.msg);
    }
  }
}

final blocDetails = MovieDetailsBloc();
