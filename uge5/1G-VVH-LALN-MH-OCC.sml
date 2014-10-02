type point = real * real;
datatype 'col figure
  = Circle of 'col * point * real
  | Rectangle of 'col * point * point
  | Over of 'col figure * 'col figure;

datatype primaryColours = Black | Red | Green | Blue
                          | White | Cyan | Magenta | Yellow;

(* 5G1 *)

fun toRGB Black   = (0,0,0)
  | toRGB White   = (255,255,255)
  | toRGB Red     = (255,0,0)
  | toRGB Green   = (0,255,0)
  | toRGB Blue    = (0,0,255)
  | toRGB Yellow  = (255,255,0)
  | toRGB Cyan    = (0,255,255)
  | toRGB Magenta = (255,0,255);
  
(* test *)  


