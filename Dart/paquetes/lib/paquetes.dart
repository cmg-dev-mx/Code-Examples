/**
 * Primero van las importaciones de paquetes de google
 * Segundo las importaciones de paquetes de terceros
 * Tercero las importaciones de paquetes propios
 */
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:paquetes/clases/reqres_response.dart';

void getResponse() {
  final uri = Uri.parse('https://reqres.in/api/users?page=2');
  http.get(uri).then((res) {
    final body = jsonDecode(res.body);
    final usersResponse = UsersResponse.fromJson(body);
    print('page: ${usersResponse.page}');
    print('per_page: ${usersResponse.perPage}');
    print('id del 3er elemento: ${usersResponse.data[2].id}');
  });
}
