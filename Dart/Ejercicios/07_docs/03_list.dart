main() {
  List<int> numbers = [1, 2, 3, 4, 5];
  List<int>? lista2; // Null Safety
  List<int> lista3 = [3, 1, 2, 15, -10];
  List<String> names = ['Tony', 'Peter', 'Steve'];

  print('Length: ${numbers.length}');
  print('First: ${numbers[0]}');
  print('First: ${numbers.first}');
  print('Last: ${numbers.last}');
  print('isEmpty: ${numbers.isEmpty}');
  print('isEmpty?: ${lista2 == null}');

  print('asMap: ${numbers.asMap()[2]}');

  Map listaMapa = lista3.asMap();
  print('ListaMapa: ${listaMapa[4]}');

  Map namesMap = names.asMap();
  print('NamesMap: ${namesMap[1]}');

  print('indexOf: ${names.indexOf('Peter')}'); // Si no existe, devuelve -1

  int mayor3 = lista3.indexWhere((numero) => numero > 3);
  print('IndexWhere mayor 3: $mayor3');

  print('Remove: ${names.remove('Tony')}');
  print('Remove: ${names}');
  numbers.shuffle();
  print('Shuffle: ${numbers}');
  lista3.sort();
  print('Sort: ${lista3}');
  print(
      'Reverse: ${lista3.reversed.toList()}'); // lista3.reverse() regresa un iterable

  names.forEach((name) {
    name = name.toUpperCase();
    print(name);
  });
  print('Names: $names');

  final newList = names.map((name) => name.toUpperCase()).toList();
  print('NewList: $newList');
}
