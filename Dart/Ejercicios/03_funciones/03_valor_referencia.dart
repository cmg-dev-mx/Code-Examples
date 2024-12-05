/**
 * Funciones: Valor o Referencia
 * Los objetos en Dart se pasan por referencia
 * Los tipos primitivos se pasan por valor
 */

String capitalizar(String texto) {
  texto = texto.toUpperCase();
  return texto;
}

Map<String, String> capitalizarMapa(Map<String, String> persona) {
  persona = {...persona}; // Operador de propagación para clonar el objeto
  persona['nombre'] = persona['nombre']?.toUpperCase() ?? 'No hay nombre';
  return persona;
}

main() {
  String nombre = 'Juan';
  String nombre2 = capitalizar(nombre);
  // print(nombre);
  // print(nombre2);

  Map<String, String> persona = {
    'nombre': 'Tony Stark',
  };
  Map<String, String> persona2 = capitalizarMapa(persona);
  print(persona);
  print(persona2);
}
