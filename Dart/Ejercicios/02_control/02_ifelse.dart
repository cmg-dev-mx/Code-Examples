import 'dart:io';

main() {
  stdout.writeln('¿Cuál es tu edad?');
  int edad = int.parse(stdin.readLineSync() ?? '0');

  /**
   * Crear un programa en dart que:
   * 
   * - Si es mayor de 21 años, mostrar la palabra "Ciudadano"
   * - Si es mayor de 18 años, mostrar la palabra "Mayor de edad"
   * - Si es mayor de 13 años, mostrar la palabra "Adolescente"
   */

  if (edad >= 21) {
    print('Ciudadano');
  } else if (edad >= 18) {
    print('Mayor de edad');
  } else {
    print('Adolescente');
  }
}
