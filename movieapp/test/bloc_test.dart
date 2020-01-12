import 'package:flutter_test/flutter_test.dart';
import 'package:movieapp/core/blocs/movie_details_bloc.dart';
import 'package:movieapp/core/blocs/movie_list_bloc.dart';
import 'package:movieapp/core/models/movies/movie.dart';

void main() {
  group("Bloc test", () {
    test("Movie BLoC fetchPopularMovies testing", () async {
      bloc.fetchPopularMovies();
      bloc.allMovies.listen(expectAsync1((value) {
        expect(value, isInstanceOf<List>());
      }));
    });

    test("Movie BLoC fetchUpcomingMovies testing", () async {
      bloc.fetchUpcomingMovies();
      bloc.allMovies.listen(expectAsync1((value) {
        expect(value, isInstanceOf<List>());
      }));
    });

    test("Movie Details BLoC  testing", () async {
      blocDetails.getMovie(id: 443791);
      blocDetails.movie.listen(expectAsync1((value) {
        expect(value, isInstanceOf<Movie>());
      }));
    });
  });
}
