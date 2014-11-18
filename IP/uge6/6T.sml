(* 6T1 *)

(* Funktionen listLines åbner filen s og indlæser hele filens indhold. Derefter
   splittes dette efter newlines med String.fields. Bemærk der bruges
   String.fields for at bevare "tomme" linjer i den indlæste fil. *)
fun listLines filename =
    let
      val is = TextIO.openIn(filename);
      val all = TextIO.inputAll is;
    in
      String.fields (fn x => #"\n" = x) all
        before TextIO.closeIn is
    end

val test_6T0_1 = listLines "indgangsfil.txt" =
                 ["Se solen der skinner på kalv og på kid",
                  "Se parken der dufter af vår",
                  "Nu sammen vi hilser den nye tid",
                  "I morgen er verden vor",
                  ""];
val test_6T0_2 = listLines "testfil.txt" =
                 ["En fil",
                  "",
                  "med en blank linje.",
                  ""];


(* Mere effektivt: Løsning hvor linjerne indlæses enkeltvis, så vi
   sparer String.fields kaldet: *)
fun listLines' filename =
    let
      val is = TextIO.openIn(filename);
      fun removeLast l = substring(l,0,size l - 1) 
      fun readLines is =
        case TextIO.inputLine is of
            NONE    => []
          | SOME l  => removeLast(l) :: readLines is
    in
      readLines is before TextIO.closeIn is
    end

val test_6T0_3 = listLines' "indgangsfil.txt" =
                 ["Se solen der skinner på kalv og på kid",
                  "Se parken der dufter af vår",
                  "Nu sammen vi hilser den nye tid",
                  "I morgen er verden vor"];

val test_6T0_4 = listLines' "testfil.txt" =
                 ["En fil",
                  "",
                  "med en blank linje."];


(* Bemærk forskellen i resultatet: Den første løsning returnerer det
   sidste linjeskift som en tom linje, den sidste løsning opdager slet
   ikke den sidste linje pga. opførslen af inputLines *)



(* 6T2 *)

(* Vi bruger map til at anvende 'explode' på hvert element i listen af linier i
   filen f *)

fun listChars f = map explode (listLines' f)


(* 6T3 *)
fun interactive () =
  (print "Tast 'q' for at afslutte\n"
  ; case TextIO.input1 TextIO.stdIn of
       NONE => print "Slut paa inddata!\n"
     | SOME #"q" => print "Farveller!\n"
     | _ => interactive ())

(* Alternativ løsning der ikke printer "Tast 'q' for at afslutte"
   flere gange når man har tastet et andet tegn end #"q" *)
fun interactive' () =
  let (* Spring næste tegn i streamen over, mutual recursion! *)
      fun skipNext () = (TextIO.input1 TextIO.stdIn;
                         interactive' ())
  in
      (print "Tast 'q' for at afslutte\n"
      ; case TextIO.input1 TextIO.stdIn of
            NONE => print "Slut paa inddata!\n"
          | SOME #"q"  => print "Farveller!\n"
          | SOME #"\n" => interactive' ()
          | SOME _     => skipNext ())
  end 

(* Prøv evt. at fjerne 'SOME #"\n"' tilfældet og se hvad det ændrer
   når du taster helt blanke linjer *)
