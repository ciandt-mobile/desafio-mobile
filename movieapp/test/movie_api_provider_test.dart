import 'package:flutter_test/flutter_test.dart';
import 'package:movieapp/core/models/list.dart';
import 'package:movieapp/core/resources/movie_api_provider.dart';
import 'package:movieapp/core/models/state.dart';

void main() {
  group("MovieApiProvider test", () {
    test("fetchMovie success test", () async {
      var apiProvider = MovieApiProvider();

      expect(await apiProvider.fetchMovie(id: 443791),
          isInstanceOf<SuccessState>());
    });

    test("fetchPopularMovieList success test", () async {
      var apiProvider = MovieApiProvider();

      expect(
          await apiProvider.fetchMovieList(page: 1, listType: ListType.Popular),
          isInstanceOf<SuccessState>());
    });

    test("fetchUpcomingMovieList success test", () async {
      var apiProvider = MovieApiProvider();

      expect(
          await apiProvider.fetchMovieList(
              page: 1, listType: ListType.Upcoming),
          isInstanceOf<SuccessState>());
    });
    test("fetchPopularMovieList error test", () async {
      var apiProvider = MovieApiProvider();

      expect(
          await apiProvider.fetchMovieList(page: 0, listType: ListType.Popular),
          isInstanceOf<ErrorState>());
    });
    test("fetchUpcomingMovieList error test", () async {
      var apiProvider = MovieApiProvider();

      expect(
          await apiProvider.fetchMovieList(
              page: 0, listType: ListType.Upcoming),
          isInstanceOf<ErrorState>());
    });

    test("fetchMovie error test", () async {
      var apiProvider = MovieApiProvider();

      expect(await apiProvider.fetchMovie(id: 44379122),
          isInstanceOf<ErrorState>());
    });
  });
}
