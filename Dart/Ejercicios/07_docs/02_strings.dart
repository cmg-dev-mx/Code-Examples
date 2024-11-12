main() {
  String nombre = 'Cesar';
  String apellido = 'Morales';

  String nombreCompleto = '$nombre' ' ' 'Morales';
  print('String: $nombreCompleto');

  print('Longitud: ${nombreCompleto.length}');
  print('Contiene C: ${nombreCompleto.contains('C', 0)}');
  print('Termina en s: ${nombreCompleto.endsWith('s')}');
  print('Llena a la izquierda: ${nombreCompleto.padLeft(20, '.')}');
  print('Llena a la derecha: ${nombreCompleto.padRight(20, '.')}');

  print('Operador []: ${nombreCompleto[5]}');
  print('Operador *: ${nombreCompleto * 2}');
  print('Operador *: ${'*' * 10}');

  print('Reemplaza todo: ${nombreCompleto.replaceAll(new RegExp(r'e'), 'a')}');
  print('SubString: ${nombreCompleto.substring(0, 5)}...');
  print('indexOf: ${nombreCompleto.indexOf(' ')}');
  print('Split: ${nombreCompleto.split(' ')}');
  print('Split: ${nombreCompleto.split(' ')[1]}');
  print(
      'Capitalizar: ${nombreCompleto[nombreCompleto.length - 1].toUpperCase()}');
}
