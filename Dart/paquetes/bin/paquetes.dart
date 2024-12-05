/**
 * Primero van las importaciones de paquetes de google
 * Segundo las importaciones de paquetes de terceros
 * Tercero las importaciones de paquetes propios
 */

import 'dart:convert';

import 'package:http/http.dart' as http;

import 'package:paquetes/paquetes.dart' as paquetes;

void main(List<String> arguments) {
  final uri = Uri.parse('https://reqres.in/api/users?page=2');
  http.get(uri).then((res) {
    final body = jsonDecode(res.body);
    print(body);
    print('page: ${body['page']}');
    print('per_page: ${body['per_page']}');
    print('id del 3er elemento: ${body['data'][2]['id']}');
  });
}
