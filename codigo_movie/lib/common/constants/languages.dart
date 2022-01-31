import '../../repositories/entities/language_entity.dart';

class Languages {
  const Languages._();

  static const languages = [
    LanguageEntity(code: 'pt', value: 'Portuguese'),
    LanguageEntity(code: 'es', value: 'Spanish'),
    LanguageEntity(code: 'en', value: 'English'),
  ];
}
