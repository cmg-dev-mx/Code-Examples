class MiServicio {
  static final MiServicio _singleton = MiServicio._();

  String url = 'https://api.miweb.com';
  String key = 'ABC123';

  // MiServicio._internal();
  MiServicio._();

  factory MiServicio() {
    return _singleton;
  }
}
