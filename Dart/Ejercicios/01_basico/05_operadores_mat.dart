/**
 * Un operador es un símbolo que
 * le dice al compilador qué debe
 * realizar una tarea matemática o
 * lógica y debe de producir un
 * resultado.
 */

main() {
  int a = 10 + 5; // 15
  a = 20 - 10; // 10
  a = 10 * 2; // 20

  double b = 10 / 2; // 5.0
  b = 10.0 % 3; // 1.0
  b = -b; // -1.0

  int c = 10 ~/ 3; // 3, division entera

  int d = 1;
  d++; // 2
  d--; // 1

  d += 2; // 3
  d -= 2; // 1
  d *= 2; // 2
  //d /= 2; // 1 No se puede usar con enteros
  d %= 2; // 1
}
