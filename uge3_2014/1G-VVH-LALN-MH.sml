(* 3G1 *)
load "Int";
load "Word8";
load "Word8Vector";
load "BinIO";

use "InstagraML.sml";

val torben = InstagraML.readBMP "torben.bmp";
val ralf = InstagraML.readBMP "ralf.bmp";

val b_tr = InstagraML.beside(torben, ralf);
val b_rt = InstagraML.beside(ralf, torben);

InstagraML.writeBMP("b_tr.bmp", b_tr);
InstagraML.writeBMP("b_rt.bmp", b_rt);
