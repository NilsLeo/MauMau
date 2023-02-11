# MauMau

## Voraussetzungen
* Docker
* Maven
* Java

## Schritte
### Für MySQL
#### Erstellen/ NeuErstellen des Schemas
``````
mysql -u root --password=admin_123 -e "

DROP SCHEMA IF EXISTS maumau;

create database maumau;

use maumau;
"
``````
### Für MySQL mit Docker (Linux/Mac OS/ WSL Backend)
#### Erstellen/ NeuErstellen des Schemas
`sudo docker compose up -d`
#### Auf die CLI zugreifen
`docker exec -it maumau-mysql-1 bash -l`

`mysql -uroot -p`
#### Datensätze ansehen
`use maumau;`
3. Bauen Sie das Projekt.
4. Führen Sie die [App.java](Configuration/src/main/java/de/htwberlin/kbe/gruppe4/App.java) aus um das Spiel zu starten.
5. Sehen sie sich bei Bedarf die Datensätze in mysql mit den Zugangsdaten in der [persistence.xml](Configuration/src/main/resources/META-INF/persistence.xml) an.
6. Das allgemeine Logging kann im [RootLog.log](RootLog.log) angesehen werden
7. Anwendungsfehler können im [MauMauLog.log](MauMauLog.log) angesehen werden