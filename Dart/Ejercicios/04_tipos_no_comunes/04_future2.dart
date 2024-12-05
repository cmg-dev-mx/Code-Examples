import 'dart:io';

main() {
  // Lectura del archivo de manera síncrona
  // String f2 = file.readAsStringSync();
  // print(f2);
  // print('Fin del main');

  // Lectura del archivo de manera asíncrona
  File file = new File(Directory.current.path + '/assets/personas.txt');
  Future<String> f = file.readAsString();
  // f.then((data) => print(data));
  f.then(print);

  print('Fin del main');
}
