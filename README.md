# StayhomeApp

#### Beschreibung
Die StayHome App motiviert dich Zuhause zu bleiben und dabei deinen Alltag spannender zu gestalten. Du bekommst Empfehlungen, wie "rufe mal deine Oma an", "gehe spazieren" oder "Nachrichten lesen" die du freiwillig erledigen kannst. Falls du dich besonders gut verhälst bekommst du Auszeichnungen, zum Beispiel wenn du 3 Tage Zuhause geblieben bist. Dies erkennt die App über dein Heim-Netzwerk und kann dir so hilfreiche Tipps geben, wie du Ausgänge auf ein Minimum reduzieren kannst. Dadurch erweitert die StayHome App nicht nur Social-Distancing, sondern hilft dir auch mit dieser ungewohnten Situation klar zu kommen

#### Features
- Erkennt das der Benutzer nicht mehr im Home-Wlan ist und sendet eine Benachrichtigung, wenn er wieder nach Hause zurück kehrt. Beim nächsten Öffnen der App wird der Benutzer gefragt, was er getan hat (Dies is bisher noch experimentell und funktioniert nicht zu 100%).
- Gibt den Benutzer Quests, die er freiwillig erledigen kann.
- Falls bestimmte Bedingungen erfüllt sind kann der Benutzer Archievements bekommen, zum Beispiel falls er 3 Tage nicht das Haus verlassen hat.

#### Installation
Lade das neuste [Release](https://github.com/Paulpanther/StayhomeApp/releases) herunter und [installiere es](https://www.lifewire.com/install-apk-on-android-4177185)

#### Architektur

##### Achievements
Alle relevanten Aktionen des Benutzers werden im `ActionLog` gespeichert. Sobald dieser verändert wird überprüfen die Archievements, ob sie erreicht sind.

##### Wifi
Beim Starten der `MainActivity` startet der `WifiHelper` den `WifiChangeWorker`. Dieser schaut alle 15 Minuten, ob der Benutzer im Home-Netzwerk ist. Falls der Benutzer gerade zurück nach Hause kommt wird dies lokal gespeichert. Das nächste Mal wenn die App geöffnet wird löst das die `NotHomeQuestionActivity` aus, die den Benutzer fragt warum er draußen war. Die Antwort wird im `ActionLog` gespeichert

##### Quests
Jedes Mal, wenn die App gestartet wird überprüfen die `QuestBuilder` im `ActionLog`, ob ihre Bedingungen erfüllt sind und bauen gegebenenfalls neue Quests. Diese können übersprungen, oder erfolgreich beendet werden, was auch im `ActionLog` gespeichert wird.

##### Bibliotheken
Wir haben [Paper](https://github.com/pilgr/Paper) verwendet, um Objekte zu speichern
