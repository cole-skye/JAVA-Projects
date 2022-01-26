import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'add_quote.dart';
import 'detail.dart';
import 'main.dart';

class Homepage extends StatelessWidget {

  const Homepage({Key? key}) : super(key: key);
  static late Future futureAlbum;

  void initState(){
    futureAlbum = fetchAlbum();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Qute Quotes'),
      ),
      body: Center(
        child: Column(
          children: [
            Expanded(
              child: FutureBuilder(
                future: fetchAlbum(),
                builder: (context, AsyncSnapshot snapshot) {
                  return ListView.builder(
                      itemCount: snapshot.data.length,
                      itemBuilder: (context, i) {
                        if (snapshot.hasData) {
                          return Card(
                            child: ListTile(
                              title: Text(snapshot.data[i]["text"]),
                              leading: const Icon(Icons.arrow_right),
                              // subtitle: Text(snapshot.data[i]["name"]),
                              onTap: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => detailPage(
                                            snapshot.data[i]["name"],
                                            snapshot.data[i]["text"])
                                    ));
                              }, // onTap
                            ),
                          );
                        } else if (snapshot.hasError) {
                          return Text('${snapshot.error}');
                        }
                        // By default, show a loading spinner.
                        return const CircularProgressIndicator();
                      });
                }, // builder
              ),
            ),
            const AddQuotePage()
          ],
        ),
      ),
    );
  }
}
