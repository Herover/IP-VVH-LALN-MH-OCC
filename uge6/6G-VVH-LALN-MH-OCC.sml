(* 6G1 *)
structure IO = TextIO;


fun readSudoku filename =
    let
      fun textSplit str =
          map explode
              (String.tokens (fn x => x = #"\n")
                             (IO.inputAll (IO.openIn filename)));
    in
      textSplit filename
    end;

readSudoku "sudoku-tilstand.txt";
