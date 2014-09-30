(* Lavet af Leon, Mirza og Victor *)

use "InstagraML.sml";
structure I = InstagraML;

(* 3G1 *)

(* Forstå problemet:
 *
 * Hvilke tilfælde skal der kunne håndteres?
 * Hvis billederne er lige høje, så skal funktionen sætte bilederne ved siden
 * af hindanden uden at indsætte nogle sorte bjælker.
 *
 * Hvis det ene billede er højere end det andet, skal vi indsætte
 * sorte bjælker, over og under det laveste billede, der svarer til
 * forskellen i højden divideret med 2. På den måde bliver billederne
 * lige høje.
 *
 * Hvis det ene billede er et ulige antal pixels højt og det andet
 * billede er et lige antal pixels højt, så vil forskellen være
 * et ulige antal pixels, hvilket ikke kan vises.
 * vi bliver derfor nødt til at lave en heltals division
 * og tilføje en til en af bjælkerne.
 *)


(* 3G2 *)

(* Plan for afprøvning af problemet:
 *
 * For at foretage test undersøger vi om billede størrelsen er som forventet
 * og ved visuel inspektion undersøger vi om billederne er sat korrekt ved
 * siden af hindanden og om det laveste billede har fået sorte barer, så
 * billederne passer sammen.
 *)

(* 3G3 *)

(* Kode kommentar:
 *
 * Funktionen solid tager en højde, en bredde og en farve.
 * Funktionen laver en pixel med den ønskede farve og scalerer
 * den op så den har den ønskede højde og bredde og returnerer derefter
 * et billede med den ønskede højde, bredde og farve.
 *
 * Funktionen padVertical tager en højde og et billede.
 * Funktionen vender billedet om på siden, indsætter 1 bjælke på hver side,
 * vender billedet om igen og returnerer billedet.
 *)

(* 3G4 *)

fun solid colour (w, h) =
    I.scale (real w) (real h) (I.pixel colour);


(* solid test:
 *
 * Vi tester funktionen solid, ved at lave et billede med solid og derefter
 * teste om dimensionerne giver det forventede output.
 *)

val black = solid(0, 0, 0);

val scaleImg = black(150, 200);
val solid_scaleImg_test1 = (I.width scaleImg, I.height scaleImg) = (150, 200);

val scaleImg2 = black(0, 0);
val solid_scaleImg2_test1 = (I.width scaleImg2, I.height scaleImg2) = (0, 0);


(* 3G5 *)

val counterClockwise = I.clockwise o I.clockwise o I.clockwise;

fun above (img1, img2) =
    if I.width img1 <> I.width img2 then raise Domain
    else counterClockwise (I.beside(I.clockwise img1, I.clockwise img2));

fun padVertical height img =
    if height < I.height img
    then raise Domain
    else
      let val imgHeight = height - I.height img
          val (lowerBarHeight, upperBarHeight) =
              (imgHeight div 2 + imgHeight mod 2, imgHeight div 2)
          val blackBar = solid(0, 0, 0)
          val lowerBar = blackBar(I.width img, lowerBarHeight)
          val upperBar = blackBar(I.width img, upperBarHeight)
      in
        above(above(upperBar, img), lowerBar)
      end;


(* padVertical Test
 *
 * Vi har foretaget visuelle test på pad_test1 og 2 for at se om
 * den lægger bjælkerne oven og nedenunder billedet.
 * I pad_test1 har vi talt antallet af pixels af højden af bjælkerne
 * og vi kan konkluderer at når forskellen i højden er et ulige tal
 * så bliver den nederste bjælke 1 pixel større.
 *)


val torben = I.readBMP "torben.bmp";
val size1 = padVertical 189;
val size2 = padVertical 200;

val pad_test1 = size1 torben;
val pad_test2 = size2 torben;
I.writeBMP("pad.bmp", pad_test1);
I.writeBMP("pad2.bmp", pad_test2);

val pad_test3 = (padVertical 1 torben; false) handle Domain => true
                                                    | _ => false;
val pad_test4 = I.height (size1 torben) = 189;
val pad_test5 = I.height (size2 torben) = 200;


(* 3G6 *)

(* Kode kommentar:
 *
 * Funktionen safeBeside checker hvilket billede der er det højeste,
 * og sætter de to billeder sammen hvor den kalder padVertical på
 * billedet med den laveste højde.
 * Billederne bliver sat i den rækkefølge de kaldes i.
 * Dvs. at img1 er til venstre og img2 er til højre.
 *)

fun safeBeside (img1, img2) =
    if I.height img1 > I.height img2
    then I.beside(img1, padVertical (I.height img1) img2)
    else I.beside(padVertical (I.height img2) img1, img2);


(* safeBeside test:
 *
 * Vi laver 2 check i vores test. Vi tester om dimensionerne for billederne
 * passer og det returner en boolean værdi.
 * Vi laver også en visuel test, hvor vi gemmer billederne
 * og checker om det laveste billede har fået sorte barer så højden
 * er lige store og billederne står i den rigtige rækkefølge
 * ved siden af hinanden.
 *)

val ralf = I.readBMP "ralf.bmp";
val martin = I.readBMP "martin.bmp";

val safeBeside_test1 = safeBeside(ralf, torben);
val safeBeside_test2 = safeBeside(torben, ralf);
val safeBeside_test3 = safeBeside(martin, ralf);
val safeBeside_test4 = safeBeside(martin, torben);
I.writeBMP("safeBeside_test1.bmp", safeBeside_test1);
I.writeBMP("safeBeside_test2.bmp", safeBeside_test2);
I.writeBMP("safeBeside_test3.bmp", safeBeside_test3);
I.writeBMP("safeBeside_test4.bmp", safeBeside_test4);

val safeBeside_test5 =
    (I.height safeBeside_test1, I.width safeBeside_test1) =
    (395, 479);
val safeBeside_test6 =
    (I.height safeBeside_test2, I.width safeBeside_test2) =
    (395, 479);
val safeBeside_test7 =
    (I.height safeBeside_test3, I.width safeBeside_test3) =
    (395, 479);
val safeBeside_test8 =
    (I.height safeBeside_test4, I.width safeBeside_test4) =
    (180, 360);
