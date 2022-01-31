import 'dart:ui';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

import '../../../common/constants/languages.dart';
import '../../../repositories/entities/language_entity.dart';
import '../../../repositories/entities/no_params.dart';
import '../../../repositories/usecases/get_preferred_language.dart';
import '../../../repositories/usecases/update_language.dart';

part 'language_state.dart';

class LanguageCubit extends Cubit<Locale> {
  final GetPreferredLanguage getPreferredLanguage;
  final UpdateLanguage updateLanguage;

  LanguageCubit({
    required this.getPreferredLanguage,
    required this.updateLanguage,
  }) : super(
          Locale(Languages.languages[0].code),
        );

  Future<void> toggleLanguage(LanguageEntity language) async {
    await updateLanguage(language.code);
    loadPreferredLanguage();
  }

  void loadPreferredLanguage() async {
    final response = await getPreferredLanguage(NoParams());
    emit(response.fold(
      (l) => Locale(Languages.languages[0].code),
      (r) => Locale(r),
    ));
  }
}
