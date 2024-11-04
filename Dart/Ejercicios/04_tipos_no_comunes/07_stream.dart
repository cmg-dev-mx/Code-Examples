import 'dart:async';

main() {
  final streamController = StreamController();

  streamController.stream.listen((data) {
    print('Despegando! $data');
  });

  streamController.sink.add('Apollo 11');

  print('Fin del main');
}
