# gnengine

Das Programm ist mit dem build-tool gradle erstellt.
Bei Verwendung von Eclipse oder IDEA sollte das Setup einfach
funktionieren. Ansonsten sind die Standard Funktionen von
Gradle für das Initialisieren, Bauen des Projekts, sowie dem
Testen zu verwenden.

Der Ordner Guilib enthält das Projekt, sowie das Git-
Repository. ASE enthält die PDF-Dokumentation.

##Verwendung

Das Programm zeigt ein Bild, sowie 9 Eingabe-Felder. Diese
können verwendet werden um einen 3x3 Faltungskernel auf das
Bild anzuwenden. Beispiel hierfür sind:
Laplace:
 1  1  1
 1 -8  1
 1  1  1

Relief:
 2  1  0
 1  1 -1
 0 -1 -2

Schärfung:
 0 -1  0
-1  5 -1
 0 -1  0

Apply Filter versucht aus der Eingabe einen Filter zu
erstellen.
