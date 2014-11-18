(*Gruppe afl uge 2*)


(*Filer vedhæftes til programmet*)
use "uge2_distance.sml";
use "uge2_cphmarathon2014.sml";

(*2G1*)
(*
* Funktionen adderer et element og det næste element og sådan bliver den ved
* indtil listen
*)

fun sum [] = raise Empty
 | sum [_] = 0.0
 | sum (x :: xs) = distance(x, List.nth(xs, 0)) + sum xs; 


sum cphMarathon2014 ;
sum cphMarathon2014 - 42.9686446838;

(*2G2*)

use "uge2_dhl2014.sml";

val test = [((55.702412, 12.568260), 0), ((55.702385, 12.568090), 5858), ((55.702302, 12.567562), 11716)];

fun speeds [] = raise Empty
 | speeds [_] = []
 | speeds (x :: xs) = 
  let val (a,b) = x
      val (c, d) = List.nth(xs, 0) 
  in [distance(a, c) / (real((d-b)) / (1000.0*3600.0))]@speeds xs   
  end; 
  
 speeds dhl2014;
 
 
 
(*2G3*)

fun sum2 [] = 0.0
 | sum2 (x :: xs) = x + sum2 xs; 
fun snit xs = (sum2 xs) /  real (list.length xs);

snit(speeds(dhl2014));


(*2G4*)

fun averageSpeed n = snit(speeds(n));

averageSpeed(dhl2014);


(*2G5*)


 
 
