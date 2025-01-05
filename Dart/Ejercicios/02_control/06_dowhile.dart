import 'dart:io';

main() {
  String continuar = 'y';
  int contador = 0;

  do {
    contador++;
    print('Contador: $contador');
    stdout.write('Desea continuar? (y/n): ');
    continuar = stdin.readLineSync()!;
  } while (continuar == 'y');
}
