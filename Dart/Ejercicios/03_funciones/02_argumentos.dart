/**
 * Argumento posicional: Los argumentos son obligatorios
 * void saludar(String mensaje) { ...
 * 
 * Argumento con nombre: Los argumentos son opcionales
 * void saludar({String mensaje}) { ...
 * 
 * Si se desea que un argumento sea opcional, se debe encerrar entre corchetes
 */

void saludar(String mensaje,
    [String nombre = '<insertar nombre>', int edad = 20]) {
  print('$mensaje $nombre - $edad');
}

void main(List<String> args) {
  saludar('Hola', 'Juan', 30);
}
