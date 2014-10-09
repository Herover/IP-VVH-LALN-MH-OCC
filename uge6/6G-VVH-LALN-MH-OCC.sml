(* 6G1 *)
structure IO = TextIO;

val instrm = TextIO.openIn "sudoku-tilstand.txt";
IO.inputAll instrm;
