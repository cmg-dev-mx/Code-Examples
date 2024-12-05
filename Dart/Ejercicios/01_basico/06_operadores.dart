main() {
  // Operadores de asignación
  int a = 10; // Asignación
  a ??= 20; // Asignar el valor únicamente si la variable es null
  print('Asignación: $a');

  // Operadores condicionales
  int b = 23;
  String resp = b > 25 ? 'b es mayor a 25' : 'b es menor a 25';
  print('Operador condicional: $resp');

  int d = b ??
      a ??
      100; // Si b es null, entonces asigna a, si a es null, entonces asigna 100
  print('Operador de nulabilidad: $d');

  // Operadores relacionales
  // Todos retornan un valor booleano
  /*
    > Mayor que
    < Menor que
    >= Mayor o igual que
    <= Menor o igual que
    == Igual que
    != Diferente de
  */
  String persona1 = 'Fernando';
  String persona2 = 'Alberto';

  print('Operador relacional: ${persona1 == persona2}');
  print('Operador relacional: ${persona1 != persona2}');

  int x = 20;
  int y = 30;

  print(x > y); // false
  print(x < y); // true
  print(x >= y); // false
  print(x <= y); // true

  // Operador de tipo
  int i = 10;
  String j = '10';

  print(i is int);
  print(j is! int);
}
