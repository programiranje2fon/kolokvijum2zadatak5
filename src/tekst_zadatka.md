# Zadatak 1

Napraviti javnu klasu **StatistikaException** u paketu **popis.izuzeci** koja predstavlja proveravani izuzetak i ima:
- Javni **konstruktor** koji kao parametar prima poruku greške i poziva odgovarajući konstruktor nadklase prosleđujući mu parametar.

Napraviti javnu klasu **Domacinstvo** u paketu **popis** koja može da bude serijalizovana i ima:
- Privatni atribut **mesto** koji predstavlja naziv mesta u kojem se domaćinstvo nalazi.
- Privatni atribut **brojOdraslih** koja predstavlja broj odraslih članova domaćinstva.
- Privatni atribut **brojDece** koja predstavlja broj dece u domaćinstvu.
- Privatni atribut **mesečnaPrimanja** koji predstavlja iznos mesečnih primanja cele porodice u dinarima.
- Odgovarajuće javne **get i set metode** za ove atribute. Nedozvoljena vrednost za atribut mesto je null String i svaki String kraći od 5 znakova ili duži od 13 znakova, a ostali atributi moraju biti nula ili veći od nule. U slučaju unosa nedozvoljenih vrednosti, baciti izuzetak klase RuntimeException sa odgovarajućom porukom.
- Redefinisanu **toString** metodu klase Object koja vraća String sa svim podacima o Domaćinstvu uz odgovarajuću poruku, ali da se posle svakog atributa upiše u String i znak „tab“.
- Redefinisanu **equals** metodu klase Object. Metoda prvo proverava da li je uneti objekat klase **Domacinstvo**, pa ako nije baca izuzetak klase **StatistikaException**. Metoda vraća true ako su vrednosti svih atributa jednaki vrednostima odgovarajućih atributa unetog domaćinstva, a inače false.

Napraviti javnu klasu **PopisDomacinstava** u paketu **popis** koja ima:
- Privatni atribut **domacinstva** koji predstavlja listu objekata klase **Domacinstvo**.
- Javni **konstruktor** bez argumenata koji inicijalizuje listu.
- Javnu metodu **upisiDomacinstvaBezPrihoda** koja u tekstualni fajl "domacinstva\_bez\_prihoda.txt" upisuje samo podatke o onim domaćinstvima u kojima je iznos mesečnih primanja nula i to u formatu &lt;mesto&gt;#&lt;broj odraslih&gt;#&lt;broj dece&gt;. Ako je lista prazna, baciti izuzetak klase **StatistikaException**.
- Javnu metodu koja na osnovu podataka iz liste domaćinstava sastavlja izveštaj i upisuje ga u fajl "izvestaj.txt". U njemu treba da se nađe u posebnim redovima: ukupan broj domaćinstava, prosečan iznos mesečnih primanja po domaćinstvu, prosečan broj odraslih po domaćinstvu, prosečan broj dece po domaćinstvu i prosečan iznos mesečnih primanja po članu domaćinstva (računaju se i odrasli članovi i deca).
- Javnu metodu **ucitajDomacinstvaSaTastature** koja sa tastature učitava podatke o nekoliko domaćinstava i unosi ih u listu. Broj domaćinstava se unosi na početku. U slučaju pojavljivanja izuzetka pri unosu za neko domaćinstvo, uhvatiti izuzetak, ispisati njegovu poruku i pokušati još jednom unos sa tastature za to domaćinstvo.