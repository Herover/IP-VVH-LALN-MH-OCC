load "Int";

datatype pitchclass = C | Cis | D | Dis | E | F | Fis
                      | G | Gis | A | Ais | B;
type octave = int;
type pitch = pitchclass * octave;
type duration = int;
datatype music = Note of duration * pitch
               | Rest of duration;
type melody = music list;

type abspitch = int; (* Se 4G3 *)

val octavePitches = 12;

(* 4G1 *)
(*
 * Skriver music om til menneskeligt læseligt format.
 * Bruger hjælpefunktionen pitchclassToText til at omsætte note til tilsvarende
 * tekst.
 * Vi bruger to funktioner i en let til at lave markeringer af hvilken oktav vi
 * er i, og om den er positiv eller negativ.
 * Vi tester ikke pitchclassToText eftersom det er meget ligetil at læse hvordan
 * den opfører sig.
*)
fun pitchclassToText C = "c"
  | pitchclassToText Cis = "cis"
  | pitchclassToText D = "d"
  | pitchclassToText Dis = "dis"
  | pitchclassToText E = "e"
  | pitchclassToText F = "f"
  | pitchclassToText Fis = "fis"
  | pitchclassToText G = "g"
  | pitchclassToText Gis = "gis"
  | pitchclassToText A = "a"
  | pitchclassToText Ais = "ais"
  | pitchclassToText B = "b";

fun musicToString (Rest d) = "r"^Int.toString d
  | musicToString (Note (d, (p, oc))) =
    let
        fun mark _ 0 = ""
          | mark ch n = ch ^ mark ch (n-1)
        fun mkSpace oct = if oct > 0 then mark "'" oct
                          else mark "," (~oct)
    in
        (pitchclassToText p) ^ mkSpace(oc) ^ (Int.toString d)
    end;

val musicToString_test1 = musicToString (Rest 5) = "r5";
val musicToString_test2 = musicToString (Note (4, (Fis, 3))) = "fis'''4";
val musicToString_test3 = musicToString (Note (4, (Fis, ~2))) = "fis,,4";

(* 4G2 *)
(*
 * Skriver en melodi om til et format som kan læses af mennesker.
 * Bruger musicToString bliver brugt til at omsætte musik datatyper til tekst.
 * For at undgå et ekstra mellemrum i starten eller slutningen trækker vi første
 * element ud og bruger den som udgangspunkt i hvores foldl funktion.
*)
fun melodyToString [] = ""
  | melodyToString (frste::liste) =
    foldl (fn (ny, gammel) => gammel ^ " " ^ musicToString ny)
          (musicToString frste) liste;

val melodyToString_test1 = melodyToString
    [Note (16, (A, 0)), Note (16, (B, 0)), Note (4, (C, 1)), Note (8, (C, 1))]
                           = "a16 b16 c'4 c'8";
val melodyToString_test2 = melodyToString
    [Note (16, (A, 0)), Note (16, (B, 0)), Note (4, (C, 1)), Note (8, (C, 1))]
                           = "a16 b16 c'4 c'8";
val melodyToString_test3 = melodyToString []
                           = "";
(* 4G3 *)
(*
 * Omsæter pitch til absolut pitch.
 * Bruger hjælpefunktionen noteToPitch til at omsætte en note til tilsvarende
 * absolut pitch værdi.
 * For hver + oktav ligges 12 til, og - trækkes 12 fra.
*)

fun noteToPitch C = 0
  | noteToPitch Cis = 1
  | noteToPitch D = 2
  | noteToPitch Dis = 3
  | noteToPitch E = 4
  | noteToPitch F = 5
  | noteToPitch Fis = 6
  | noteToPitch G = 7
  | noteToPitch Gis = 8
  | noteToPitch A = 9
  | noteToPitch Ais = 10
  | noteToPitch B = 11;

fun absolutePitch (a, b) = noteToPitch a + octavePitches * b;

val absolutePitch_test1 = absolutePitch(C, 0) = 0;
val absolutePitch_test2 = absolutePitch(C, 1) = 12;
val absolutePitch_test3 = absolutePitch(B, 0) = 11;
val absolutePitch_test4 = absolutePitch(C, ~1) = ~12

(* 4G4 *)
(*
 * Laver absolute pitch om til pitch.
 * Bruger hjælpefunktionen pitchToNote som omsætter tal til tilsvarende pitch.
 * Tests af pitchToNote er ikke lavet, da det er meget lige til at aflæse den
 * fra koden.
 * Tests af pitch er baseret på at absolutePitch giver et korrekt resultat.
*)
fun pitchToNote 0 = C
  | pitchToNote 1 = Cis
  | pitchToNote 2 = D
  | pitchToNote 3 = Dis
  | pitchToNote 4 = E
  | pitchToNote 5 = F
  | pitchToNote 6 = Fis
  | pitchToNote 7 = G
  | pitchToNote 8 = Gis
  | pitchToNote 9 = A
  | pitchToNote 10 = Ais
  | pitchToNote 11 = B
  | pitchToNote _ = raise Domain;

fun pitch ap =
    let
        val p = ap mod 12
    in
        (pitchToNote p, ap div 12 )
    end;

val pitch_test1 = pitch (absolutePitch(C, 0)) = (C, 0);
val pitch_test2 = pitch (absolutePitch(C, ~2)) = (C, ~2);
val pitch_test3 = pitch (absolutePitch(B, 2)) = (B, 2);
val pitch_test4 = absolutePitch (pitch 0) = 0;

(* 4G5 *)
(*
 * Transponer toner i melodi.
 * Hvis tonen bliver for høj eller lav vil oktaven tilpasse sig.
*)
local
  fun transposer n (Rest d) = Rest d
    | transposer n (Note (d, (tone, oct))) = let
      val nyTone = noteToPitch tone + n
    in
      Note (
        d,
        (pitchToNote (nyTone mod octavePitches),nyTone div octavePitches + oct))
    end
in
fun transpose n mel =
    map (transposer n) mel
end;

val transpose_test1 = transpose 3 [Note (32, (A, ~1))] = [Note (32, (C, 0))];
val transpose_test2 = transpose 1 [Note (32, (B, ~1))] = [Note (32, (C, 0))];
val transpose_test3 = transpose 12 [Note (32, (A, 0))] = [Note (32, (A, 1))];
val transpose_test4 = transpose ~1 [Note (32, (C, 0))] = [Note (32, (B, ~1))];
val transpose_test4 = transpose ~12 [Note (32, (C, 0))] = [Note (32, (C, ~1))];
val transpose_test5 = transpose ~12 [Rest (32)] = [Rest (32)];

(* 4G6 *)
(*
 * Sammenligner music datatyper om hvem der har den højeste pitch, og retunerer
 * den højeste pitch.
 * Ideen er at omsætte pitch til absolute pitch værdier som er mulige at
 * at sammenligne. Til sidst omsættes den til en pitch igen.
 * Er melodien tom eller kun består af pauser vil den smide en Domain exception.
 *)
fun maxPitch [] = raise Domain
  | maxPitch ((Rest _)::mel) = maxPitch mel
  | maxPitch ((Note (_, fst))::mel) =
    let
      fun maxPitch_helper ((Rest _), max) = max
        | maxPitch_helper ( Note(_, p),  max) =
          if absolutePitch(p) > max
          then absolutePitch(p)
          else max
      val maxPitch_finder = foldl maxPitch_helper (absolutePitch fst) mel
    in
      pitch(maxPitch_finder)
    end;

val maxPitch_test1 =
    maxPitch [
      Note (12, (G, ~1)), Note (14,(B, ~2)), Note (14, (Fis, ~3))
    ] = (G, ~1);
val maxPitch_test2 =
    maxPitch [Note (12, (C, 99)), Note (14,(B, 0))] = (C, 99);
val maxPitch_test3 =
    maxPitch [
      Note (12, (G, 0)), Note (14,(B, 0)), Note (14, (Fis, 0))
    ] = (B, 0);
val maxPitch_test4 = (maxPitch []; false) handle Domain => true
                                               | _ => false;
val maxPitch_test5 = (maxPitch [(Rest 2)]; false) handle Domain => true
                                                       | _ => false;

(* 4G7 *)
(*
 * Vi har lavet to hjælpefunktioner: gcd og ratioAdd.
 * radtioAdd ligger to brøker sammen. For at forkorte brøken bruger vi gcd på
 * nævneren af resultatet, så det bliver lettere aflæseligt, eg. 4/4 = 1.
 *
 * Vi har tilføjet at en tid ikke kan være negativ eller lig med nul, eftersom
 * det ikke giver mening at tale om i dette tilfælde.
*)
datatype rational = Ratio of int * int;
fun gcd (0, n) = n
  | gcd (m ,n) = gcd (n mod m, m);

fun ratioAdd(Ratio (d1, n1), Ratio (d2, n2)) =
    let
      val (d3, n3) = ((d1*n2 + d2*n1), (n1 * n2))
      val gd = gcd(d3, n3)
    in
      Ratio (d3 div gd, n3 div gd)
    end;

ratioAdd(Ratio (3, 4), Ratio (1, 2));


fun duration mel = let
  fun add dur total =
      if dur <= 0
      then raise Domain
      else ratioAdd(Ratio (1, dur), total)
  fun counter (Rest dur, total) = add dur total
    | counter (Note (dur, _), total) = add dur total
in
  foldl counter (Ratio(0, 1)) mel
end;

val duration_test1 = duration [Note (1, (A, 1)), Note (1, (A, 1))] =
                     Ratio (2, 1);
val duration_test2 = duration [Note (2, (A, 1)), Note (2, (A, 1))]
                     = Ratio (1, 1);
val duration_test3 = duration [Note (2, (A, 1)), Rest 2, Note (2, (A, 1))]
                     = Ratio (3, 2);
val duration_test4 = duration [
      Note (1, (A, 1)),
      Note (2, (A, 1)),
      Note (4, (A, 1)),
      Note (8, (A, 1))
    ] = Ratio (15, 8);
val duration_test5 = (duration [Note (~1, (A, 1))]; false) handle Domain => true
                                                                | _ => false;
val duration_test6 = (duration [Note (0, (A, 1))]; false) handle Domain => true
                                                               | _ => false;
