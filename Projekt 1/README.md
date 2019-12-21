# Zanim zaczniesz
Do poprawnego działania programu konieczne jest zainstalowanie biblioteki json-simple, który pozwala na otwieranie plików json.
W IntelIJ: Project Structure -> Modules -> Add Library from Maven. Pełna nazwa: "com.googlecode.json-simple:json-simple:1.1.1"


# Opis działania
Moja symulacja pozwala na wyświetlanie w okienku obecnego stanu mapy oraz generuje pod koniec symulacji plik z genomami zwierząt, które przeżyły.

# Dokumentacja
## Plik parameters.json
W pliku oprócz podstawowych wymagań znajdują się dwie wartości określające ilość kroków (dni) symulacji oraz czas w milisekundach - przerwa między wyświetlaniem kolejnych klatek

  ## Pakiet Entities
  Przechowuje klasy związane z obiektami znjadującymi się na mapie
  ### Animal
   Klasa oparata na wersji z laboratorium, lecz wszystkie atrybuty zwierzęcia przeniesione zostały do klasy AnimalAttributes. Posiada        konstruktor pozwalający na utworzenie nowego zwierzęta podając rodziców.
   
 ### AnimalAttributes
 Klasa przechowuje parametry zwierzęcia (nie atrybuty!). Rozróżnienie jest następujące: Parametry - wartości stałe dla wszystkich zwierząt na danej mapie, czyli moveEnergy, plantEnergy, startEnergy. Atrybuty to położenie, kierunek, genom itp. - każde zwierzę ma inne.
 
 ### Genome
 Przechowuje genom w postaci 32 elementowej tablicy enumów MapDirection, posiada metodę generującą zupełnie losowy kierunek lub ten oparty na genomie.
 
 ### Plant
 Jak na laboratorium.
 
 ## Pakiet Utility
 ### AnimalParams
 Przechowuje moveEnergy, plantEnergy, startEnergy
 
 ### MapDirection
 Jak na laboratorium, rozszerzone o pośrednie kierunki.
 
 ### MapParams
 Przechowuje parametry mapy - Lowerleft, Upperright itp.
 
 ### Vector2D
 Nowa metoda random between - losuje punkt pomiędzy danymi lowerleft i upperright
 
 ## Pakiet World
 
 ### AnimalBreeder
 Odpowiedzialna za rozmnażanie zwierząt. Sprawdza każde pole na którym jest jakieś zwierzę i rozmnaża dwa o największej energii
 
 ### AnimalSet
 Zwykła ArrayLista, z nadpisaną metodą toString. Przechowuje wszystkie zwierzęta, które są na danym polu mapy.
 
 ### JungleMap
 Tutaj wszystko się dzieje.
 Wiele ma wspólnego z GrassField. Metoda nextTurn wywołuje metody, które generują rośliny, przesuwają zwierzęta, zjadają rośliny i rozmnażają zwierzęta.
 
 ### PlantEater
 Klasa odpowiedzialna za zjadanie przez zwierzęta roślin.
 
 ### Interfejsy IAnimalObserver i IPlantObserver
 Konieczne głównie ze względów wydajnościowych - znacznie przyspieszają animację, gdyż dzięki nim nie trzeba za każdym razem od nowa rysować całej mapy.
 
 ### JSONWriter
 Zapisuje genomy zwierząt, które przeżyły symulację do pliku survivorsGenomes.json
 
 ### MapInitializer
 Wczytuje z pliku JSON dane początkowe. Posiada gettery z których korzystają MapParams, AnimalParams oraz World - do animacji.
 
 ### MapVisualizer2
 Oparty na swingu. Generuje mapę świata. Implementuje oba interfejsy, aktualizuje mapę gdy przemieści się zwierzę lub pojawi/zniknie roślina.
 
 ### World
 Tu znajduje się pętla odtwarzająca symulację
 
 
 
 
  
  



