import 'clases/Persona.dart';

main() {
  Persona persona = Persona()
    ..nombre = 'Cesar'
    ..edad = 37
    ..bio = 'Nació en la ciudad de México';

  print(persona);
}
