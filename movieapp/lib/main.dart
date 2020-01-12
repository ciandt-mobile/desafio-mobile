import 'package:flutter/material.dart';
import 'package:movieapp/ui/screens/movie_details_screen.dart';
import 'package:movieapp/ui/screens/movie_list_screen.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MovieListScreen(),
      routes: {MovieDetailsScreen.routeName: (ctx) => MovieDetailsScreen()},
    );
  }
}
