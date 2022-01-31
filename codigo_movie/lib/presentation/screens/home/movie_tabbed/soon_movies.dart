import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:movieapp/common/constants/size_constants.dart';
import 'package:movieapp/presentation/blocs/movie_tabbed/movie_tabbed_cubit.dart';
import 'package:movieapp/presentation/screens/home/movie_tabbed/movie_tabbed_widget.dart';
import '../../../../common/extensions/size_extensions.dart';
import '../../../../common/extensions/string_extensions.dart';
import '../../../themes/theme_color.dart';
import '../../../themes/theme_text.dart';

class TrendingMovies extends StatefulWidget {
  const TrendingMovies({Key? key}) : super(key: key);

  @override
  _TrendingMoviesState createState() => _TrendingMoviesState();
}

class _TrendingMoviesState extends State<MovieTabbedWidget>
    with SingleTickerProviderStateMixin {
  MovieTabbedCubit get movieTabbedCubit =>
      BlocProvider.of<MovieTabbedCubit>(context);

  late String title;
  int currentTabIndex = 0;

  @override
  void initState() {
    super.initState();
    movieTabbedCubit.movieTabChanged(currentTabIndex: currentTabIndex);
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<MovieTabbedCubit, MovieTabbedState>(
        builder: (context, state) {
      return Padding(
        padding: EdgeInsets.only(top: Sizes.dimen_4.h),
        child: Column(
          children: [
            Container(
              decoration: BoxDecoration(
                color: Colors.transparent,
                border: Border(
                  bottom: BorderSide(
                    color: AppColor.royalBlue,
                    width: Sizes.dimen_1.h,
                  ),
                ),
              ),
              child: Text(title.t(context), //'popular', 'now', 'soon'
                  style: Theme.of(context).textTheme.royalBlueSubtitle1),
            ),
          ],
        ),
      );
    });
  }
}
