FROM debian:buster-slim

RUN apt update
RUN apt install openjdk-11-jdk -y

Add out/artifacts/QuteQuotes_jar/QuteQuotes.jar /quteserver1.jar
Add QuteQuoteDataBase.db QuteQuoteDataBase.db

EXPOSE 5001

CMD ["java", "-jar","quteserver1.jar"]