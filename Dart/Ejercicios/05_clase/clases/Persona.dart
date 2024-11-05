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
  // Persona() {
  //   // Constructor vacío
  //   print('Constructor');
  // }

  // Persona(int edad, String nombre) {
  //   this.edad = edad;
  //   this.nombre = nombre;
  // }
  Persona({this.edad = 0, this.nombre = 'Sin nombre'});

  // Métodos
  @override
  String toString() => '$nombre tiene $edad años y su biografía es: $_bio';
}
