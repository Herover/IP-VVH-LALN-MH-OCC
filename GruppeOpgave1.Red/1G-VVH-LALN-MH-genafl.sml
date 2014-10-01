(*
 * 1G1
 * Funktionen løser en andengradsligning.
 * Den går ud fra at der er en løsning, er der ikke vil vi få en fejl.
 *)

load "Math";

fun solve2 (a, b, c) =
  (
     (~b - Math.sqrt( b * b - 4.0 * a * c )) / ( 2.0 * a ),
     (~b + Math.sqrt( b * b - 4.0 * a * c )) / ( 2.0 * a )
  );

(*
 * 1G2
 * Kør funktionen med parametrer 2, 3 og 1.
 * Vi regnede resultatet ud i hånden ved at bruge samme formel
 * og fik så et resultat som vi satte test_solve2_1 lig med
 * hvis test_solve2_1 evaluerer til true gør funktionen det rigtigt
 *)
solve2(2.0, 3.0, 1.0);
val test_solve2_1 = solve2(2.0, 3.0, 1.0) = ( ~1.0, ~0.5);

(*
 * 1G3
 * Køres solve2 med argumenterne (2.0, 3.0, 4.0) er der ingen løsning, og
 * vi får en domain error.
 *)
val test_solve2_2 = (solve2(2.0, 3.0, 4.0); false) handle Domain => true
                                                           | _ => false;

(*
 * 1G4
 * Potensen udregnes ved at num ganges med sig selv n gange.
 * Når n = 0 stopper rekursionen og vi retunerer 1 som ikke betyder noget
 * for resultatet.
 *)
fun powerNew (a, 0) = 1
  | powerNew (a, n) = if n mod 2 = 0
    then powerNew(a, n div 2) * powerNew(a, n div 2)
    else a * powerNew(a, n div 2) * powerNew(a, n div 2);

powerNew(2, 21);

(*
 * 1G5
 * Funktionen returnere en tupel med resultatet af powerunktionen
 * og antallet af brugte multiplikationer
*)

fun powerCount (a, 0) = (1, 0)
  | powerCount (a, n) = let val (v, counter) = powerCount(a, n div 2)
    in if n mod 2 = 0
    then (v * v, counter + 1)
    else (a * v * v, counter + 2)
    end;
powerCount(2, 21);
