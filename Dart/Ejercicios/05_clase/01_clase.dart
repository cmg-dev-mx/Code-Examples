main() {
  Persona persona = Persona();
  persona.nombre = 'Cesar';
  persona.edad = 37;
  persona.bio = 'Nació en la ciudad de México';

  print(persona);
}

class Persona {
  // Campos o propiedades
  String? nombre;
  int? edad;
  String? bio;

  // Get y Set

  // Constructores

  // Métodos
  @override
  String toString() => '$nombre tiene $edad años y su biografía es: $bio';
}
