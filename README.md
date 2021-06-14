# gnengine

Das Programm ist mit dem build-tool gradle erstellt.
Bei Verwendung von Eclipse oder IDEA sollte das Setup einfach
funktionieren. Ansonsten sind die Standard Funktionen von
Gradle für das Initialisieren, Bauen des Projekts, sowie dem
Testen zu verwenden.

Der Ordner Guilib enthält das Projekt, sowie das Git-
Repository. ASE enthält die PDF-Dokumentation.

## Verwendung

Das Programm zeigt ein Bild, sowie 9 Eingabe-Felder. Diese
können verwendet werden um einen 3x3 Faltungskernel auf das
Bild anzuwenden. Beispiel hierfür sind:  
Laplace:  
&nbsp;1&nbsp;&nbsp;1&nbsp;&nbsp;1  
&nbsp;1&nbsp;-8&nbsp;&nbsp;1  
&nbsp;1&nbsp;&nbsp;1&nbsp;&nbsp;1  

Relief:  
&nbsp;2&nbsp;&nbsp;1&nbsp;&nbsp;0  
&nbsp;1&nbsp;&nbsp;1&nbsp;-1  
&nbsp;0&nbsp;-1&nbsp;-2  

Schärfung:  
&nbsp;0&nbsp;-1&nbsp;&nbsp;0  
-1&nbsp;&nbsp;5&nbsp;-1  
&nbsp;0&nbsp;-1&nbsp;&nbsp;0  

Apply Filter versucht aus der Eingabe einen Filter zu
erstellen.
