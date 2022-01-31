import 'package:flutter/material.dart';
import 'package:movieapp/presentation/screens/movie_detail/movie_detail_arguments.dart';
import 'package:movieapp/presentation/screens/watch_video/watch_video_arguments.dart';

import '../common/constants/route_constants.dart';
import 'screens/favorite/favorite_screen.dart';
import 'screens/home/home_screen.dart';
import 'screens/login/login_screen.dart';
import 'screens/movie_detail/movie_detail_screen.dart';
import 'screens/watch_video/watch_video_screen.dart';

class Routes {
  static Map<String, WidgetBuilder> getRoutes(RouteSettings setting) => {
        RouteList.initial: (context) => LoginScreen(),
        RouteList.home: (context) => HomeScreen(),
        RouteList.movieDetail: (context) => MovieDetailScreen(
              movieDetailArguments: setting.arguments as MovieDetailArguments,
            ),
        RouteList.watchTrailer: (context) => WatchVideoScreen(
              watchVideoArguments: setting.arguments as WatchVideoArguments,
            ),
        RouteList.favorite: (context) => FavoriteScreen(),
      };
}
