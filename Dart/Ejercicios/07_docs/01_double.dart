main() {
  double numero = 3.1416;
  double infinito = double.infinity;

  print('Firma: ${numero.sign} :: $numero');
  print('Es finito: ${numero.isFinite} :: $numero');
  print('Es finito: ${infinito.isFinite} :: $infinito');
  print('Absoluto: ${numero.abs()} :: $numero');
  print('Techo: ${numero.ceil()} :: $numero');
  // print("Techo: ${infinito.ceil()} :: $infinito"); // ! Error
  print('Techo a entero: ${numero.ceilToDouble()} :: $numero');
  print('Redondeo: ${numero.round()} :: $numero');
  print('Redondeo a double: ${numero.roundToDouble()} :: $numero');
  print('Clamp: ${numero.clamp(1, 3)} :: $numero');
}
