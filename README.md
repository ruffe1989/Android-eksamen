# Android-eksamen
Dette er eksamen som jeg gjennomførte i faget 6120 Applikasjonsutvikling for mobile enheter ved Universitetet i Sørøst-Norge avd Bø

## Oppgaveteksten:
Universitetet i Sørøst-Norge ønsker å gjøre det lettere å redusere bilbruken for studenter og ansatte. Du skal derfor lage en «fungerende prototype» av en Andoid-app for å avtale samkjøring fra hjem til studiested, og mellom studiesteder. Brukere som kjører bil til studiestedet skal kunne registrere hvor han kjører fra/til. Andre brukere skal kunne se aktuelle turer/ruter og melde interesse for å samkjøre / være passasjer.

Hovedfunksjonaliteten i appen skal omfatte:
* Registrere en «tur» med bl.a. tidspunkt, avreisested, turmål, sjåfør og antall ledige plasser
* Se registrerte turer (egne og andres) og «melde seg på» en tur
* Se alle deltakere på en tur og melde seg av / slette turen
* Kunne få vist plasseringen av avreisested og turmålet (og evt. kjøreruten) i et kart (f.eks. Google Maps)

I tillegg til enkeltturer på en gitt dato, hadde det vært ønskelig å kunne registrere «faste turer», dvs. turer på faste ukedager, men dette skal ikke løses i denne prototypen.
Se mer detaljert beskrivelse under overskriften funksjonalitet.
### Datagrunnlag:
Om hver kjøretur skal appen minst lagre følgende data:
* Dato og tidspunkt for avreise fra startsted
* Forventet (ca) tidspunkt for ankomst til målet
* Adresse/navn og (evt. posisjon) på målet for kjøreturen
* Adresse/navn og (evt. posisjon) på startsted for kjøreturen
* Eventuelt adresse/navn på «kjente» passeringspunkt/stopp/pickup-point for kjøreturen («via»).
* Antall ledige passasjerplasser
* En beskrivelse av kjøretøyet, f.eks. bilmerke/årsmodell
* Navn, telefonnummer og e-post til sjåfør. Ta evt. med andre opplysninger du mener er relevant

De av opplysningene som ofte vil være like fra tur til tur, bør sjåføren kunne lagre permanent som «standardverdier», f.eks. under brukerinnstillinger, slik at han slipper å registrere dem på nytt for hver tur. Se pkt g under funksjonalitet.
Når en passasjer registrerer ønske om å sitte på, bør følgende kunne lagres:
* Navn, telefonnummer og e-post på passasjer.
* Eventuelt ønsket pickup-point (sted / adresse)
* Evt. siste frist for ankomst til målet (hvis passasjeren må rekke noe)

Om du ser behov for andre relevante opplysninger i datagrunnlaget, kan du gjerne utvide dette selv.
Du står fritt til å velge database og aksessmetode for dataene, men de må lages et sted der alle brukere/enheter har tilgang til dem.
Du står fritt til å velge om du vil lage APIer/tjenester for dataaksess selv, eller bruke det PHP-baserte REST-APIet som har vært brukt i undervisningen (PHP-CRUD-API). PHP-filen med APIet ligger i samme mappe som denne oppgaven. Se dokumentasjon her: https://github.com/mevdschee/php-crud-api
Du kan f.eks. bygge databasen i MySQL på tjeneren itfag.usn.no, og bruke CRUD-API, men vær obs på at http-metodene PUT og DELETE ikke fungerer mot Apache på denne tjeneren.

### Funksjonalitet:
App’en skal la brukeren utføre funksjonene nedenfor. For oversikten skyld er funksjonaliteten spesifisert i nummererte punkter, men du behøver ikke følge denne rekkefølgen når du løser oppgaven. Du står fritt til å designe GUI’et selv. Hvis du ikke klarer / rekker å implementere all funksjonaliteten, bør du prioritere å få det du programmerer til å fungere.

1. Registrere en ny tur.
Brukeren skal kunne registrere en ny tur som vedkommende planlegger å kjøre, med de opplysningene som er angitt under Datagrunnlag. Du vil bli premiert for gode/enkle måter å registrere avreisested/mål på, f.eks. bruk av Google Maps og/eller Places/Geocoding API.
2. Søke og vise turer med ønsket mål.
Brukeren bør kunne søke etter turer til ønsket turmål. Søket bør kunne baseres på et «fast» mål (studiested) som brukeren reiser til fast, men han bør også kunne velge et annet mål for turen hvis ønskelig. Resultatet av søket skal vises som en liste med registrerte turer (fram i tid) som ennå ikke er «fullbooket». Det bør være mulig å begrense søket på dato for turen.
Du blir belønnet hvis du klarer å sortere listen slik at turer som starter nærmest brukerens posisjon vises først i listen. En forenkling av dette vil være å kunne velge å vise bare turer som starter i samme by / sted som passasjeren velger/bor i. (Det ville selvsagt være ønskelig å også vise turer som passerer det avreisestedet passasjeren velger, men dette er en langt mer krevende funksjon å løse, og forventes ikke)
3. Velge en tur
Når brukeren klikker på en tur i listen, skal appen gi mulighet for å registrere seg som passasjer. Dette skal gjøres på enklest mulig måte for brukeren, med mest mulig «forhåndsutfylt» informasjon. Når brukeren melder seg på en tur skal det samtidig opprettes en kalenderhendelse for turen i brukerens kalender. Tips: Se Intents med action-koden ACTION_INSERT.
4. Visning av kjørerute i kart, f.eks. Google Maps
Fra turlisten (eller vinduet med detaljopplysninger om et turmål) skal det være mulig å vise ruten for turen i et kart slik at brukeren kan se om turen passerer nær ønsket avreisested. Bruk Google Maps Android API for å vise kjørerute fra startsted til målet. Se f.eks: https://developers.google.com/maps/documentation/android-api/marker
5. Mine turer
Brukere skal kunne se en liste over turer som man selv er involvert i. Dvs. turer man selv har registrert som sjåfør, eller turer der man er passasjer. Her bør man kunne slette/avlyse en tur og melde seg av turer på en enkel måte. Hvis en sjåfør avlyser en tur, skal det automatisk sendes en tekstmelding (SMS) til alle passasjerer.
5. Se passasjerliste
Sjåføren (og passasjerene) skal ha mulighet for å liste alle passasjerer (inkludert sjåfør) som har valgt en tur. Det er naturlig at denne listen kan nås fra «Mine turer», men om du finner andre og bedre løsninger kan du gjerne det.
Passasjerlisten bør vise navn, telefonnummer, e-post og evt. ønsket pickup-point for hver av passasjerene på turen.
7. Innstillinger / Settings
App’en skal ha et valg for Innstillinger (Settings) der bruker kan legge inn noen faste opplysninger, bl.a:
* Fast reisemål (studiested)
* Navn, telefonnummer, e-post
* Fast by / sted / adresse for «pickup»
* Evt. brukernavn og passord
* Andre opplysninger som forenkler registrering av nye turer

Disse opplysningene kan lagres lokalt på enheten eller i en sentral database.
Du kan fritt velge om du vil ha noen påloggingsfunksjon til appen som krever at brukeren registrere seg. Hvis du lager det, må du i dokumentasjonen oppgi et brukernavn som faglærer og sensor kan logge inn med for å teste appen.

## Gjennomføring av oppgave og resultat
Selve oppgaven skulle utføres i løpet av en uke. Jeg fikk desverre ikke gjort meg ferdig på den tiden som følge av at jeg hadde noen problemer i starten. Likevel ble resultetet på denne eksamen en C.
