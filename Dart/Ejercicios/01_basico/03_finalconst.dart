void main() {
  var a = 10;
  final b = 20;
  const c = 30;

  a = 15;
  // b = 25; // Error
  // c = 35; // Error

  final personasFinal = ['Juan', 'Pedro', 'Luis'];
  const personasConst = ['Juan', 'Pedro', 'Luis'];

  // final List<String> personasFinal = ['Juan', 'Pedro', 'Luis'];
  // List<String> personasConst = const ['Juan', 'Pedro', 'Luis'];

  personasFinal.add('Maria');
  // personasConst.add('Maria'); // Error

  late final double x; // Variable final que se inicializa después
  x = 10.0;
  print(x);
}
