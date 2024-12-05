class Location {
  final double lat;
  final double lng;

  const Location(this.lat, this.lng);
}

main() {
  final sanFrancisco = Location(37.7749, -122.4194);
  final mexicoCity = Location(19.4326, -99.1332);
  final sanFrancisco2 = Location(37.7749, -122.4194);

  print(sanFrancisco == mexicoCity);
  print(sanFrancisco == sanFrancisco2);

  const sanFranciscoConst = Location(37.7749, -122.4194);
  const mexicoCityConst = Location(19.4326, -99.1332);
  const sanFrancisco2Const = Location(37.7749, -122.4194);

  print(sanFranciscoConst == mexicoCityConst);
  print(sanFranciscoConst == sanFrancisco2Const);

  // Nota: Los constructores constantes crean una instancia de la clase solo si no existe una instancia con los mismos valores.
}
