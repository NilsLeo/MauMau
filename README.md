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

CREATE TABLE Card (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
suit ENUM('CLUBS', 'DIAMONDS', 'HEARTS', 'SPADES'),
value ENUM('SEVEN', 'EIGHT', 'NINE', 'TEN', 'QUEEN', 'JACK', 'KING', 'ACE')
);

CREATE TABLE Player (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL
);

CREATE TABLE CardPlayer (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
card_id INT NOT NULL,
player_id INT NOT NULL,
FOREIGN KEY (card_id) REFERENCES Card(id),
FOREIGN KEY (player_id) REFERENCES Player(id)
);

CREATE TABLE Deck (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
card_id INT NOT NULL,
FOREIGN KEY (card_id) REFERENCES Card(id)
);

CREATE TABLE CardDeck (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
card_id INT NOT NULL,
deck_id INT NOT NULL,
FOREIGN KEY (card_id) REFERENCES Card(id),
FOREIGN KEY (deck_id) REFERENCES Deck(id)
);

CREATE TABLE Rules (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
draw_two_on_seven BOOLEAN NOT NULL,
choose_suit_on_jack BOOLEAN NOT NULL,
reverse_on_ace BOOLEAN NOT NULL,
reversed BOOLEAN NOT NULL,
suit ENUM('CLUBS', 'DIAMONDS', 'HEARTS', 'SPADES')
);

CREATE TABLE Game (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
player_id INT NOT NULL,
deck_id INT NOT NULL,
rules INT NOT NULL,
current_player INT NOT NULL,
FOREIGN KEY (player_id) REFERENCES Player(ID),
FOREIGN KEY (deck_id) REFERENCES Deck(ID),
FOREIGN KEY (rules) REFERENCES Rules(ID)
);

"
``````
3. Führen Sie den Befehl `mvn clean install` aus, um das Projekt zu bauen. 
4. Führen Sie die [inter.App](MauMauManagement\src\main\java\de\htwberlin\kbe\gruppe4\inter.App.java) aus um das Spiel zu starten. 
5. Sehen sie sich bei Bedarf die Datensätze in mysql mit den Zugangsdaten in der [pom.xml](pom.xml) an.