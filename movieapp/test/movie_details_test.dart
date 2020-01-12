import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:movieapp/core/models/movies/movie.dart';
import 'package:movieapp/ui/screens/movie_details_screen.dart';

void main() {
  final movie = Movie(
      id: 01,
      title: "Test Movie",
      releaseDate: "2020-01-08",
      genres: [Genres(name: "Sci fi")],
      overview:
          "When renowned crime novelist Harlan Thrombey is found dead at his estate just after his 85th birthday, the inquisitive and debonair Detective Benoit Blanc is mysteriously enlisted to investigate. From Harlan's dysfunctional family to his devoted staff, Blanc sifts through a web of red herrings and self-serving lies to uncover the truth behind Harlan's untimely death.",
      runtime: 120,
      credits: Credits(cast: [Cast(name: "Henry Cavill")]));
  testWidgets('Check if title section is rendering as expected',
      (WidgetTester tester) async {
    await tester.pumpWidget(
      MaterialApp(
        home: Scaffold(
          body: Container(
            child: DetailsTitle(
              movie: movie,
            ),
          ),
        ),
      ),
    );
    final titleFinder = find.text('Test Movie');
    final releaseDateFinder = find.text('2020');

    expect(titleFinder, findsOneWidget);
    expect(releaseDateFinder, findsOneWidget);
  });

  testWidgets('Check if genres section is rendering as expected',
      (WidgetTester tester) async {
    await tester.pumpWidget(
      MaterialApp(
        home: Scaffold(
          body: Container(
            child: DetailsGenres(
              movie: movie,
            ),
          ),
        ),
      ),
    );
    final nameFinder = find.text('Sci fi');
    expect(nameFinder, findsOneWidget);
  });
}
