# prg2-slutprojekt

TeamViewer-klon, den styrda datorn skickar en bild av skärmen hela tiden som datorn som styr tar emot. Datorn som styr skickar musens nuvarande position/om den klickar, detta gör att datorn som styrs gör något med Robot-klassen. Ta en bild av skärmen med Robot.createScreenCapture(), konvertera sedan det till en videoström som skickas genom en socket.