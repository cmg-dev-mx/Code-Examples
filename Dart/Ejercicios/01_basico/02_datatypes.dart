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

  // Booleanos

  bool activo = true;
  activo = !activo;

  print(activo);

  // Listas

  List<String> personajes = ['Superman', 'Batman'];
  personajes.add('Flash');
  personajes.addAll(['Acuaman', 'Mujer Maravilla']);

  List<String> villanos = new List.filled(3, '');
  villanos[0] = 'Lex Luthor';
  villanos[1] = 'Doomsday';
  villanos[2] = 'Darkseid';

  // Sets (Los sets no permiten elementos duplicados)

  Set<String> villanos2 = {'Lex Luthor', 'Doomsday', 'Darkseid'};
  villanos2.add('Flash Reverso');

  List<String> heroes = ['Superman', 'Batman', 'Flash'];
  heroes.add("Batman");
  heroes.add("Batman");
  heroes.add("Batman");
  heroes.add("Batman");

  var heroesSet = heroes.toSet(); // De esta forma eliminamos los duplicados
  print(heroesSet.toList());

  // Maps (Diccionarios, objetos literales)

  Map<String, String> ironman = {
    'nombre': 'Tony Stark',
    'poder': 'Inteligencia y dinero',
  };

  print(ironman);
}
