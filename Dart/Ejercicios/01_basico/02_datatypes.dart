void main() {
  // Números
  int a = 10;
  double b = 5.5;

  int? c;

  int _a = 30;
  double $b = 45.5;

  double resultado = _a + $b;

  print(resultado);

  // Strings

  String nombre = 'Tony';
  String nombre2 = "Tony";
  String nombre3 = 'O\'Connor';

  String apellido = 'Stark';

  String nombreCompleto = '$nombre $apellido'; // Interpolación de strings

  String multilinea = '''
  Hola mundo
  $nombre2    
  ¿Cómo están?
  ''';

  print(nombre2 == nombre);
}
