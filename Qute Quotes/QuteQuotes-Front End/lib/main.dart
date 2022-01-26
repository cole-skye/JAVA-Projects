import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:qute_quotes/home_page.dart';
import 'home_page.dart';
import 'package:http/http.dart' as http;
import 'package:qute_quotes/detail.dart';

Future fetchAlbum() async {
  final response = await http.get(Uri.parse('http://192.168.18.3:5001/quotes'));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return (jsonDecode(response.body));
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load album');
  }
}

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Qute Quotes',
      theme: ThemeData(
        primarySwatch: Colors.blueGrey,
      ),
      home: const Homepage()
    );
  } //widget build
}
