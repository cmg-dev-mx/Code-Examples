main() {
  final persona = {'nombre': 'Fernando', 'apellido': 'Herrera', 'edad': 35};
  final direccion = {'ciudad': 'Ottawa', 'pais': 'Canada'};

  print('Persona: $persona');
  print('Length: ${persona.length}');
  print('Keys: ${persona.keys}');
  print('Values: ${persona.values}');
  print('Nombre: ${persona['nombre']}');

  persona.addAll(direccion);
  print('AddAll: $persona');

  persona.remove('pais');
  print('Remove: $persona');

  persona.removeWhere((key, value) => key != 'nombre');
  print('RemoveWhere: $persona');

  persona.forEach((key, value) {
    print('key: $key value: $value');
  });

  final nuevoMapa = persona
      .map((key, value) => MapEntry(key, value.toString().toUpperCase()));
  print('Nuevo Mapa: $nuevoMapa');
}
