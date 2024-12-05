main() {
  int a = 10, b = 20;
  // int resultado = sumar(a, b);
  // int resultado = sumarLambda(a, b);

  List<int> listado = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  // var nuevoListado = listado.where((numero) {
  //   return numero > 4;
  // });

  var nuevoListadoLambda = listado.where((numero) => numero > 4);
}

int sumar(int a, int b) {
  return a + b;
}

int sumarLambda(int a, int b) => a + b;
