(*
1G1
Funktionen er ligeglad med at det kan ske at d < 0
*)

load "Math";

fun solve2 (a, b, c) = 
  (
     (~b + Math.sqrt( b * b - 4.0 * a * c )) / ( 2.0 * a ),
     (~b - Math.sqrt( b * b - 4.0 * a * c )) / ( 2.0 * a )
  );

(*
1G2
Kør funktionen med parametrer 2, 3 og 1.
Tester derefter imod svar fundet via Wolfram Alpha
*)
solve2(2.0, 3.0, 1.0);
solve2(2.0, 3.0, 1.0) = ( ~0.5, ~1.0);


(*
1G3
Køres solve2 med argumenterne (2.0, 3.0, 4.0) er der ingen løsning, og
vi får en domain error.
*)
(* solve2(2.0, 3.0, 4.0);*)

(*
1G4
Potensen udregnes ved at num ganges med sig selv n gange.
Når n = 0 stopper rekursionen og vi retunerer 1 som ikke betyder noget
for resultatet.
*)
fun powerNew (num, 0) = 1
  | powerNew (num, n) = num * powerNew(num, n-1);

powerNew(2, 3);

(*
1G5
Der skal nu tælles antallet af multiplikationer når potensen udregnes.
Der bruges en ekstra funktion som udfører næsten det samme som powerNew,
men returnerer 1.
På denne måde bruger vi en tidligere funktion samt en hjælpefunktion.

I powerCount2 bruger vi en let expression som en hjælpefunktion der kun 
er defineret inde i powerCount2. 

I powerCount2 og powerCount3 bruger vi at antalet af multiplikationer 
der bliver lavet altid er n, derfor retunerer vi bare n.
*)
fun powerCountCounter (0) = 0
  | powerCountCounter (n) = powerCountCounter(n-1)+1;

fun powerCount (num, n) = (powerNew(num, n), powerCountCounter(n));

fun powerCount2 (num, n) = 
  let fun count (num, 0) = 1
    | count (numC, nC) = numC * count(numC, nC-1)
  in
    (count(num, n), n)
  end;

fun powerCount3 (num, n) = (powerNew(num, n), n);

powerCount(3,4);
powerCount2(3,4);
powerCount3(3,4);
