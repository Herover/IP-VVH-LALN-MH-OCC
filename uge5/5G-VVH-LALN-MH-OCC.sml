use "InstagraML.sml";
use "Effects.sml";
structure I = InstagraML;

(* 5G1 *)
type point = real * real
datatype 'col figure
  = Circle of 'col * point * real
  | Rectangle of 'col * point * point
  | Over of 'col figure * 'col figure;

datatype primaryColours = Black | Red | Green | Blue
                          | White | Cyan | Magenta | Yellow;

fun toRGB Black   = (0, 0, 0)
  | toRGB White   = (255, 255, 255)
  | toRGB Red     = (255, 0, 0)
  | toRGB Green   = (0, 255, 0)
  | toRGB Blue    = (0, 0, 255)
  | toRGB Yellow  = (255, 255, 0)
  | toRGB Cyan    = (0, 255, 255)
  | toRGB Magenta = (255, 0, 255);

(* 5G2 *)
fun reColour fig f =
    let fun unPack (Over(a, b)) =
            Over(unPack(a), unPack(b))
          | unPack (Rectangle(col, a, b)) =
            Rectangle(f col, a, b)
          | unPack (Circle(col, a, b)) =
            Circle(f col, a, b)
    in unPack fig end;


(* reColour test:
*
*)
val fig1 = Over(Circle (Yellow, (0.0, 0.0), 1.8),
                  Over(Circle(Magenta, (0.0, 1.0), 2.0),
                       Rectangle(Black, (3.0, 5.0), (3.0, 4.0))));

val fig2 = Over(Circle (Yellow, (0.0, 0.0), 1.8),
                  Over(Circle(Magenta, (0.0, 1.0), 2.0),
                       Over(Circle (Yellow, (0.0, 0.0), 1.8),
                            Over(Circle(Magenta, (0.0, 1.0), 2.0),
                                 Rectangle(Black, (3.0, 5.0), (3.0, 4.0))))));

val reColour_test = reColour fig1 toRGB =
                    Over(Circle ((255, 255, 0), (0.0, 0.0), 1.8),
                         Over(Circle((255, 0, 255), (0.0, 1.0), 2.0),
                              Rectangle((0, 0, 0), (3.0, 5.0), (3.0, 4.0))));

val reColour_test2 =
    reColour fig2 toRGB =
    Over(Circle((255, 255, 0), (0.0, 0.0), 1.8),
         Over(Circle((255, 0, 255), (0.0, 1.0), 2.0),
              Over(Circle((255, 255, 0), (0.0, 0.0), 1.8),
                   Over(Circle((255, 0, 255), (0.0, 1.0), 2.0),
                        Rectangle((0, 0, 0), (3.0, 5.0), (3.0, 4.0))))));

val reColour_test3 = reColour (Circle(Blue, (0.0, 0.0), 2.0)) toRGB
                     = Circle((0, 0, 255), (0.0, 0.0), 2.0);

(* 5G3 *)


fun isIn (Circle (c, (cx, cy), r)) (x, y) =
    (x-cx)*(x-cx) + (y-cy)*(y-cy) <= r*r
  | isIn (Rectangle (c, (x0, y0), (x1, y1))) (x,y) =
    x0 <= x andalso x <= x1 andalso
    y0 <= y andalso y <= y1;

fun colourOf (Over(a, b)) p =
    (case colourOf a p of
         SOME c => SOME c
       | NONE => colourOf b p)
  | colourOf (Circle(c, xy, r)) p =
    if isIn (Circle(c, xy, r)) p then SOME c else NONE
  | colourOf (Rectangle(c, xy1, xy2)) p =
    if isIn (Rectangle(c, xy1, xy2)) p then SOME c else NONE;

val redCircleOverBlueSquare =
    Over ( Circle (Red , (0.0 , 0.0) , 1.8) ,
           Rectangle (Blue , (~1.5 , ~1.5) , (1.5 , 1.5)));

val colourOf_test = colourOf redCircleOverBlueSquare (0.0, 0.0) = SOME Red;
val colourOf_test2 = colourOf redCircleOverBlueSquare (3.0, 0.0) = NONE;

(* 5G4 *)

fun hasAColour fig p =
    if colourOf fig p = NONE then false else true;

val hasAColour_test = hasAColour redCircleOverBlueSquare (0.0, 0.0) = true;
val hasAColour_test2 = hasAColour redCircleOverBlueSquare (3.0, 0.0) = false;

(* 5G5 *)
fun hasAColour2 fig p =
    if colourOf2 fig p = NONE then false else true

and colourOf2 (Over(a, b)) p =
    if hasAColour2 a p then colourOf2 a p else colourOf2 b p
  | colourOf2 (Circle(c, xy, r)) p =
    if isIn (Circle(c, xy, r)) p then SOME c else NONE
  | colourOf2 (Rectangle(c, xy1, xy2)) p =
    if isIn (Rectangle(c, xy1, xy2)) p then SOME c else NONE;

val colourOf2_test = colourOf2 redCircleOverBlueSquare (0.0, 0.0) = SOME Red;
val colourOf2_test2 = colourOf2 redCircleOverBlueSquare (3.0, 0.0) = NONE;
val hasAColour2_test = hasAColour redCircleOverBlueSquare (0.0, 0.0) = true;
val hasAColour2_test2 = hasAColour redCircleOverBlueSquare (3.0, 0.0) = false;

(* 5G6 *)

fun toInstagraML (fig, (px, py), b, h, s) =
    I.fromFunction (b, h, fn (x, y) =>
                             case
                               colourOf fig (px + (real x) * s,
                                             py + (real  y) * s)
                              of
                                 SOME c => valOf(SOME c)
                               | NONE => (255, 255, 255));

val circ = Circle((255, 0, 0), (50.0, 50.0), 50.0);

val torben = toInstagraML (circ, (50.0, 50.0), 100, 100, 2.0);

I.writeBMP ("torben.BMP", torben);




