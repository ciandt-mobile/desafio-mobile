import 'dart:async';
import 'package:http/http.dart' show Client;
import 'package:movieapp/core/models/list.dart';
import 'package:movieapp/core/models/movies/movie.dart';
import 'dart:convert';

import 'package:movieapp/core/models/state.dart';

class MovieApiProvider {
  Client client = Client();
  final _apiKey = '741592557ccdf9eceb61beefb44017b6';

  Future<State> fetchMovieList({page, ListType listType}) async {
    var url = listType == ListType.Popular
        ? "http://api.themoviedb.org/3/movie/popular?api_key=$_apiKey&page=$page"
        : "http://api.themoviedb.org/3/movie/upcoming?api_key=$_apiKey&page=$page";
    final response = await client.get(url);
    if (response.statusCode == 200) {
      var body = json.decode(response.body);
      List<Movie> list =
          List<Movie>.from(body['results'].map((val) => Movie.fromJson(val)));

      return State<List<Movie>>.success(list);
    } else {
      return State<String>.error(response.statusCode.toString());
    }
  }

  Future<State> fetchMovie({id}) async {
    final response = await client.get(
        "http://api.themoviedb.org/3/movie/$id?api_key=$_apiKey&language=en-US&append_to_response=credits");
    if (response.statusCode == 200) {
      // If the call to the server was successful, parse the JSON
      var body = json.decode(response.body);
      var movie = Movie.fromJson(body);

      return State<Movie>.success(movie);
    } else {
      return State<String>.error(response.statusCode.toString());
    }
  }
}
