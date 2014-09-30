(* Gruppe afl uge 2 *)
(* Lavet af Victor, Leon og Mirza *)

(* Filer vedhæftes til programmet *)
load "Math";
use "uge2_distance.sml";
use "uge2_cphmarathon2014.sml";
use "uge2_dhl2014.sml";

(* 2G1 *)

(*
 * Funktionen finder afstanden mellem et punkt på ruten
 * og det næste punkt og lægger det sammen, så vi får
 * distancen af hele ruten.
 *)

fun totalDistance [] = raise Empty
  | totalDistance [_] = 0.0
  | totalDistance (x :: y :: xs) = distance(x, y) + totalDistance (y :: xs);

(* totalDistance test:
 *
 * Vi ved omtrænt hvad afstanden skal være
 * men vi skal tage højde for afrundingsfejl
 * vi tester så om forskellen er mindre end 0.01.
 * hvis det er sandt, så har vi det rigtige resultat.
 *)

val totalDistance_test = (totalDistance cphMarathon2014 - 42.9686446838) < 0.01;


(*2G2*)

(*
 * Grundet til at vi har speeds [_] skyldes at hvis der kun er et element i
 * skal man få en om liste, da der ikke kan findes en afstand mellem 1 punkt.
 * Da vi ikke ved hvad outputtet skal være, kan vi ikke foretage nogen
 * true | false test. Men vi kan se på værdien og afgøre om de ser realistiske
 * ud for hvad der er muligt at løbe.
 * Funktionen kan også tage en tom liste, men da det ikke giver logisk mening
 * at tale om hastigheden mellem ingenting og ingenting kaster den exception
 * Empty.
 *)

fun speeds [] = raise Empty
  | speeds [_] = []
  | speeds ((p1, t1) :: (p2, t2) :: xs) =
    distance(p1, p2) / (real((t2-t1)) /
                        (1000.0 * 60.0 * 60.0)) :: speeds ((p2, t2) :: xs);

speeds dhl2014;

(* En test case kan være storebæltsbroen, hvor vi kender hastigheden *)
val storebaelt = [((55.336145, 10.990714), 0),
                  ((55.349420, 11.095690), 1500000),
                  ((55.336145, 10.990714), 2100000)];

val speeds_test_sb = speeds storebaelt;

(*2G3*)

(*
 * vi finder summen af alle tallene og dividerer med længden af listen
 * vi får en gennemsnitshastighed på 15 km/t hvilket er realistisk
 *)


fun averageAll xs =
    let fun sum [] = 0.0
          | sum (x :: xs) = x + sum xs
    in
      (sum xs) /  real (length xs)
    end;

val dhl2014Average = averageAll(speeds(dhl2014));


(*2G4*)

(*
 * averageSpeed finder gennemsnitshastigheden af en rute.
 * Derudover fejler den hvis man enten giver en tom liste
 * eller en liste med kun et argument.
 *)

fun averageSpeed [] = raise Domain
  | averageSpeed [_] = raise Domain
  | averageSpeed n = averageAll(speeds(n));

val averageSpeeds = averageSpeed(dhl2014);

(* Test om funktionen fejler med tomme lister eller lister med kun et punkt *)
val averageSpeeds_test1 = (averageSpeed([]); false) handle Domain
                                                 => true | _ => false ;
val averageSpeed_test2 = (
    averageSpeed([((55.700401, 12.570336), 1019292)]); false) handle Domain
     => true | _ => false;


(* 2G5 *)

(*
 * Funktion gør ikke det, der er krævet af den. I første omgang tager den en
 * string * int -> string og int * int -> string. Når man tester koden med
 * mindre eller lig tegn som den givet længde, returnere funktionen
 * det det korrekte resultat.
 * Afprøvningen tester dog ikke alle relavante tilfælde,
 * da personen ikke har taget højde for hvad der sker,
 * hvis man har flere tegn end givet længde.
 * da vil programmet lave uendelig rekursion
 *
 * Funktionen er ikke skrevet på en pæn og læselig måde.
 * Personen skriver åbenlys kode og vælger at indsæte kommenterer inde i koden.
 * Man kunne næsten også sige at personen overkommenterer.
 * Der er linier på over 80 tegn, uden at der bliver lavet mellemrum.
 * Personen burde opdele lange eklæringer på flere linier.
 * Det som personen gør med kommentar, er hvad koden gør, men det behøves ikke
 * da man skal antage at afleveringen er til en som kan SML.
 * Hvis man giver funktionen et input "rightAlign(~17,2)"
 * laver man uendelig rekursion.
 * dette skyldes at personen antager s = w, men burde have skrevet s >= w.
 * Navngivningen er fornuftigt.
 * Måske kunne man have skrevet rightAlignH i stedet
 * for rightAlignHelper.
 *
 *)

