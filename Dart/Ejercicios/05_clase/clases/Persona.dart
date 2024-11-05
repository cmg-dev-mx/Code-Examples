class Persona {
  // Campos o propiedades
  String? nombre;
  int? edad;
  String _bio = 'Hola, soy una propiedad privada';

  // Get y Set
  // String get info {
  //   return _bio;
  // }
  String get bio => _bio.toUpperCase();

  // set bio(String texto) {
  //   _bio = texto;
  // }
  set bio(String texto) => _bio = texto;

  // Constructores

  // Métodos
  @override
  String toString() => '$nombre tiene $edad años y su biografía es: $_bio';
}
