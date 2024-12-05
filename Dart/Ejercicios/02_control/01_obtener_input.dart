import 'dart:io';

main() {
  stdout.writeln('¿Cuál es tu nombre?');
  String? nombre = stdin.readLineSync();
  // String nombre = stdin.readLineSync() ?? 'No se ingresó nada';
  stdout.writeln('Hola $nombre');
}
