import 'dart:io';

main() {
  // for (var i = 0; i < 10; i++) {
  //   print("Hola mundo ($i)");
  // }

  /**
   * Dato de entrada: La base de la tabla de multiplicar
   * (Este dato debe ser ingresado por el usuario)
   * Ej: 2 => 2, 4, 6, 8, 10, 12, 14, 16, 18, 20
   * 
   * La salida esperada sería:
   * 2 * 1 = 2
   * 2 * 2 = 4
   * ...
   * 2 * 10 = 20
   */

  print("Ingrese la base de la tabla de multiplicar:");
  var base = int.parse(stdin.readLineSync()!);

  for (var i = 1; i <= 10; i++) {
    print("$base * $i = ${base * i}");
  }
}
