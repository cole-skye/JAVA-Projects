import 'package:flutter/material.dart';

class detailPage extends StatelessWidget {
  String Author;
  String Quote;

  detailPage(this.Author, this.Quote, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Quote'),
      ),
      body: Center(
        child: ListTile(
          title: Text(
            "'$Quote'",
            style: const TextStyle(fontSize: 28.0),
            textAlign: TextAlign.center,
          ),
          subtitle: Text(
            '-$Author',
            textAlign: TextAlign.center,
          ),
        ),
      ),
    );
  }
}
