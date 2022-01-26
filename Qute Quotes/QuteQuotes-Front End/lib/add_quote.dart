import 'dart:convert';

import 'package:http/http.dart' as http;

import 'package:flutter/material.dart';
import 'package:qute_quotes/home_page.dart';

class AddQuotePage extends StatefulWidget {
  const AddQuotePage({Key? key}) : super(key: key);

  @override
  _QuoteAdd createState() => _QuoteAdd();
}

class _QuoteAdd extends State<AddQuotePage> {
  final authorController = TextEditingController();
  final textController = TextEditingController();

  void clear() {
    authorController.clear();
    textController.clear();
  }

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
        onPressed: () => showDialog<String>(
            context: context,
            builder: (BuildContext context) => AlertDialog(
                  title: const Text('New Quote'),
                  actions: <Widget>[
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 8),
                      child: TextFormField(
                        controller: textController,
                        decoration: const InputDecoration(
                          border: UnderlineInputBorder(),
                          hintText: 'Enter Quote',
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 8),
                      child: TextFormField(
                        controller: authorController,
                        decoration: const InputDecoration(
                          border: UnderlineInputBorder(),
                          hintText: 'Enter Author',
                        ),
                      ),
                    ),
                    TextButton(
                        onPressed: () => Navigator.pop(context, 'Cancel'),
                        child: const Text('Cancel')),
                    TextButton(
                        onPressed: () {
                          setState(() {
                            Homepage.futureAlbum = addQuote(
                                authorController.text,
                                textController.text);
                          });
                          Navigator.pop(context);
                        },
                        child: const Text('Add')),
                  ],
                )),
        child: const Text('Add Quote'
        )
    );
  }

  Future fetchQuote() async {
    var response = await http.get(Uri.parse('http://192.168.18.3:5001/quotes'));

    if (response.statusCode == 200) {
      return jsonDecode(response.body);
    } else {
      throw Exception('Failed to load Quote');
    }
  }

  Future addQuote(String user, String text) async {
    final response = await http.post(
      Uri.parse('http://192.168.18.3:5001/quotes'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
        'text': text,
        'name': user,
      }),
    );

    if (response.statusCode == 201 || response.statusCode == 200) {
      // If the server did return a 201 CREATED response,
      // then parse the JSON.
      return fetchQuote();

      // jsonDecode(response.body);

    } else {
      // If the server did not return a 201 CREATED response,
      // then throw an exception.
      throw Exception('Failed to Upload Quote.');
    }
  }
}
