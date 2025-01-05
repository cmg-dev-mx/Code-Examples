class Cuadrado {
  final int lado;
  final int area;

  // ! Error
  // Cuadrado(int lado) {
  //   this.lado = lado;
  //   this.area = lado * lado;
  // }

  // ! Solución permitida pero incorrecta
  // Cuadrado(this.lado, this.area);

  Cuadrado(int lado)
      : this.lado = lado,
        this.area = lado * lado;
}

main() {
  final cuadrado = Cuadrado(10);
  print('Lado: ${cuadrado.lado}');
  print('Área: ${cuadrado.area}');
}
