import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:movieapp/core/models/movies/movie.dart';
import 'package:movieapp/ui/screens/movie_list_screen.dart';

class MockNavigatorObserver extends Mock implements NavigatorObserver {}

void main() {
  testWidgets('Button is present and triggers navigation after tapped',
      (WidgetTester tester) async {
    final mockObserver = MockNavigatorObserver();

    await tester.pumpWidget(
      MaterialApp(
        navigatorObservers: [mockObserver],
        home: Scaffold(
          body: Column(
            children: <Widget>[
              MovieItem(
                list: [
                  Movie(
                    id: 01,
                    title: "Test Movie",
                    releaseDate: "2020-01-08",
                  )
                ],
                controller: null,
              )
            ],
          ),
        ),
      ),
    );
    expect(find.byType(GestureDetector), findsOneWidget);
    await tester.tap(find.byType(GestureDetector));

    /// Verify that a push event happened
    verify(mockObserver.didPush(any, any));
  });

  testWidgets('Check if the widget is rendering as expected',
      (WidgetTester tester) async {
    await tester.pumpWidget(
      MaterialApp(
        home: Scaffold(
          body: Column(
            children: <Widget>[
              MovieItem(
                list: [
                  Movie(
                    id: 01,
                    title: "Test Movie",
                    releaseDate: "2020-01-08",
                  )
                ],
                controller: null,
              )
            ],
          ),
        ),
      ),
    );
    final titleFinder = find.text('Test Movie');
    final releaseDateFinder = find.text('08/01/2020');

    expect(titleFinder, findsOneWidget);
    expect(releaseDateFinder, findsOneWidget);
  });
}
