import 'clases/MiServicio.dart';

main() {
  final spotifyService1 = MiServicio();
  spotifyService1.url = 'https://api.spotify.com';

  final spotifyService2 = MiServicio();
  spotifyService2.url = 'https://api.spotify.com/v2';

  print(spotifyService1 == spotifyService2); // true
  print(spotifyService1.url); // https://api.spotify.com/
  print(spotifyService2.url); // https://api.spotify.com/
}
