# MauMau

## Voraussetzungen
* Docker
* Maven
* Java

## Schritte
1. Führen Sie den Befehl `docker-compose up` aus oder installieren Sie MySQL.

2. Erstellen/ NeuErstellen des Schemas und der Daten
`````
mysql -u root --password=admin_123 -e "

DROP SCHEMA IF EXISTS maumau;

create database maumau;

use maumau;
"
``````
3. Führen Sie den Befehl `mvn clean install` aus, um das Projekt zu bauen. 
4. Führen Sie die [App.java](Configuration/src/main/java/de/htwberlin/kbe/gruppe4/App.java) aus um das Spiel zu starten. 
5. Sehen sie sich bei Bedarf die Datensätze in mysql mit den Zugangsdaten in der [persistence.xml](Configuration/src/main/resources/META-INF/persistence.xml) an.
6. Das allgemeine Logging kann im [RootLog.log](RootLog.log) angesehen werden
7. Anwendungsfehler können im [MauMauLog.log](MauMauLog.log) angesehen werden