use "InstagraML.sml";
use "Effects.sml";

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

fun reColour figure f =
    let fun unPack (Over(a, b)) =
            Over(unPack(a), unPack(b))
          | unPack (Rectangle(col, a, b)) =
            Rectangle(f col, a, b)
          | unPack (Circle(col, a, b)) =
            Circle(f col, a, b)
    in unPack figure end;

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

val reColour_test3 = reColour (Circle(Blue, (0.0, 0.0), 2.0)) toRGBy
                     = Circle((0, 0, 255), (0.0, 0.0), 2.0);


