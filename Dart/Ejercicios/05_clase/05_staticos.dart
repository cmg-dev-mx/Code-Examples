class Herramientas {
  static const List<String> listado = [
    'Martillo',
    'Llave inglesa',
    'Destornillador'
  ];

  static imprimirListado() => listado.forEach(print);
}

main() {
  // Herramientas.listado.add('Tenazas'); // ! No se puede agregar elementos a una lista constante
  // Herramientas.listado.forEach(print);
  Herramientas.imprimirListado();
}
