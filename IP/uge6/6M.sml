(* 6M1 *)

TextIO.output (TextIO.stdOut, "Denne tekst indeholder ikke et linjeskift");

TextIO.output (TextIO.stdOut, "Denne tekst indeholder ikke et linjeskift\n");

(* 6M2 *)

fun lssuffix s = s ^ "\n";

val test_6M2_0 = lssuffix "" = "\n";
val test_6M2_1 = lssuffix "Simonsen" = "Simonsen\n";
val test_6M2_2 = lssuffix "Simonsen\n" = "Simonsen\n\n";

fun lssuffixout s = TextIO.output (TextIO.stdOut, lssuffix s);

(* lssuffixout returnerer unit, thi TextIO.output er en funktion der
evalueres for sine side-effekter (udskrivning på f.eks. skærmen),
snarere end sin værdi. *)

(* 6M3 *)

(* Det må I selv prøve. *)

(* 6M4 *)

(* Min fil har 4 linjer. *)

val instrm = TextIO.openIn "indgangsfil.txt";
TextIO.inputLine instrm;
TextIO.inputLine instrm;
TextIO.inputLine instrm;
TextIO.inputLine instrm;
TextIO.inputLine instrm;  (* Giver NONE, da vi er nået slutningen af filen *)
TextIO.closeIn instrm;
