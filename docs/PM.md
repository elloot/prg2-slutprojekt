# PRG2 slutprojekt

Elliot Duchek 01/06/2021

## Inledning

Syftet med arbetet var att skapa en TeamViewer-klon som låter en användare styra en annan användares dator. Den dator 
som styrs skickar en ström av bilder på datorns skärm som den styrande datorn tar emot och visar upp. Den styrande 
datorn skickar mus-information till den styrda datorn som översätter detta med Javas `Robot`-klass.

Arbetssättet bestod i att skapa en idébeskrivning och sedan en planering från den beskrivningen. Under arbetets gång 
följdes och uppdaterades denna planering.

## Bakgrund

Som jag nämnde i inledningen var tanken med detta projekt att skapa ett program som skulle låta den ena användaren 
styra den andra användarens dator utifrån en bildström av datorns skärm. Datorerna skulle upprätta en koppling 
mellan varandra genom en `Socket` och på så vis kunna kommunicera med varandra. Den styrda datorn skulle ta en bild 
av sin skärm och sedan skicka den till den styrande datorn som i sin tur skulle visa upp den i ett UI. Den styrande 
datorn skulle under tiden skicka information om var dess mus befann sig i förhållande till den styrda datorns skärm. 
När den styrda datorn fick den informationen skulle den översätta den till mus-input med Javas `Robot`-klass. 

## Positiva erfarenheter

Något som gått bra i mitt projekt har varit att strukturera upp det och dela in koden i logiska klasser. Jag har 
till exempel en klass som skickar musdata och en motsvarande klass som tar emot och hanterar den datan (detsamma 
gäller för bilderna av datorskärmen). Det gjorde det väldigt lätt för mig att avgränsa mina sökområden när jag 
upptäckte fel och jag kunde snabbt förstå var felet uppstod och varför. Att kunna leta buggar i en klass i taget är 
betydligt lättare än att försöka hitta dem i en enda stor klass då det ofta är olika delar av programmet som alla 
bidrar till ett fel. Detta gick bra då jag tidigare haft erfarenhet av att jobba med ett MVC-tänk och under min tid 
i Programmering 2 har jag fokuserat på att hålla en objektorienterad stil i mina program. Jag har även genom 
utmaningar som Advent of Code lärt mig att ostrukturerad kod lätt blir svår att felsöka, vilket gör att man snabbt 
tappar den tid man sparade genom att inte skriva strukturerad kod. För att upprepa detta i framtiden ska jag 
fortsätta att hålla en tydlig struktur i mina program och jobba med MVC för att bli ännu bättre på det.

Något annat som gick bra var att jobba med nätverksdelen av programmet. Jag hade inga större svårigheter med att 
skapa en koppling mellan datorerna och kunde lösa de flesta felen som uppstod gällande nätverksbiten utan hjälp från 
andra. Detta gick bra då jag tidigare fått göra ett test-projekt där jag byggde en chatt. Det hjälpte mig förstå hur 
`Socket`s fungerar i Java och hur man använder dem för att skicka data. Hade jag inte haft tidigare erfarenhet av 
den här tekniken hade jag till exempel inte vetat hur man kopplar en ut-ström till en `Socket` och jag hade 
spenderat onödigt mycket tid på att lösa problem som jag istället kunde lösa snabbt. För att upprepa detta tänker 
jag inför kommande projekt testa nya tekniker i en mer avgränsad miljö och leka runt med dem för att få en 
uppfattning om hur de fungerar innan jag ger mig in i större projekt. Att läsa dokumentation är bra, men att testa 
vad klasser kan göra och hur man kan utnyttja dem brukar ge mig en bättre förståelse för dem än att läsa sidor med text.

## Negativa erfarenheter

Här beskriver du det som du anser har gått mindre bra med ditt projekt och analyserar hur du kan undvika detta i 
framtida projekt.

En sak som gick sämre i mitt projekt var att hantera bilderna som datorerna skickade mellan varandra. Det första 
problemet jag stötte på var att `BufferedImage` inte är en klass som implementerar `Serializable`-interfacet, vilket 
innebär att den inte går att skicka över nätverk. Detta var ett problem då `Robot`-klassen endast returnerade bilder 
av typen `BufferedImage`. Jag försökte först lösa detta genom att skapa en egen klass som extendar `BufferedImage` 
och implementerar `Serializable`, men det fungerade inte av någon anledning. Redan här har jag en sak som jag kan 
förbättra till kommande projekt, nämligen att undersöka de tekniker jag ska jobba med djupare innan jag kommer igång 
med kodandet. Jag hade tidigare jobbat lite med bilder och trodde att jag visste hur de fungerade, men jag hade inte 
koll på den här aspekten och kunde inte nog om `Serializable`-interfacet för att kunna lösa problemet. Istället fick 
jag använda mig av en `ImageIcon`, vilket fungerade men kändes som en dålig lösning.

Något annat som gick sämre var att komprimera bilderna och skicka dem i en hanterbar storlek. Jag hade inte koll på 
hur man hanterade bilders storlek och komprimeringsnivå när jag började jobba på det här projektet och spenderade 
därför väldigt mycket tid på att forska i hur man kan optimera bilder för att skicka dem snabbare. Detta visade sig 
ta för lång tid och jag fick sätta det åt sidan, när det troligtvis hade kunnat förbättra användarupplevelsen 
märkbart. I nuläget är strömmen av bilder som skickas väldigt seg och närmare 1 FPS än den eftertraktade 60 när 
datorerna inte ligger på samma nätverk, något som borde kunna lösas med bättre bildhantering. För att undvika detta 
ska jag innan mina projekt testa de individuella delarna av dem i mindre storlek för att se vad jag behöver lära mig 
mer om.

## Sammanfattning

En slutsats som jag drar av detta projekt är att det är viktigt att kunna den teknik man ska använda innan man 
börjar projektet på riktigt. Jag hade en sämre förståelse för bildhantering i Java än jag trodde och hade jag gjort 
ett test-projekt med mycket bildhantering innan mitt slutprojekt hade nog resultatet av det senare blivit betydligt 
bättre. Man kan tro att man har koll på hur saker och ting fungerar, men desto komplexare saker man jobbar med desto 
mer finns det att lära sig. Bilder är komplexa, och därför var det svårt för mig att veta hur mycket jag kunde eller 
inte kunde om dem.

Något annat jag lärt mig är att det är väldigt svårt att skicka data på ett snabbt och smart sätt i nätverk. Det är 
ett under att internet går så snabbt som det gör och hur flerspelarspel där man interagerar med andra spelare i 
realtid ens kan existera är ett mirakel i mina ögon. Det krävs väldigt goda kunskaper inom flera områden för att 
optimera dataöverföring, och det är orimligt att förvänta sig att man ska kunna skapa ett program som strömmar 
högkvalitetsvideo i hög uppdateringsfrekvens när det tagit stora företag flera år att göra samma sak.

Det går definitivt att jobba vidare på projektet och jag har en lista med sådant jag skulle vilja jobba vidare med i 
min [planering](https://github.com/elloot/prg2-slutprojekt/projects/1). Bland annat skulle jag som sagt vilja öka 
bildfrekvensen på bildströmmen som skickas från den ena datorn till den andra. För att göra detta skulle jag behöva 
hitta ett snabbare sätt att ta skärmdumpar av den ena datorn samt ett bättre sätt att faktiskt skicka dem (i mindre 
storlek, komprimerade etc.). För detta är nog Java inte ett särskilt väl lämpat programmeringsspråk dock. Java är 
plattformsoberoende, vilket gör att det är lätt att skriva appar som kan köras på flera olika hårdvaror och 
operativsystem, men svårt att skriva sådant som är väloptimerat och utnyttjar datorns kraft till fullo. När jag 
skulle försöka hitta ett snabbare sätt att ta skärmdumpar kom jag in på ett bibliotek som skrevs i C++ och sedan 
kompilerades ner till Java och använde en massa `dll`-filer, och vid det laget känns det som en bättre idé att helt 
enkelt byta programmeringsspråk. Till det här projektet hade nog till exempel C++ passat bättre, men då jag inte har 
några förkunskaper i det hade det varit orimligt att använda det.
