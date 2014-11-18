(* 6G1 *)
(* Indl�ser en sudoku fil ved at splitte r�kkerne til chars *)
structure IO = TextIO;

fun readSudoku filename =
    let
      fun textSplit str =
          map explode
              (String.tokens (fn x => x = #"\n")
                             (IO.inputAll (IO.openIn str)));
    in
      textSplit filename
    end;

val sudoku_tilstand = [[#"5", #"3", #"*", #"*", #"7", #"*", #"*", #"*", #"*"],
                       [#"6", #"*", #"*", #"1", #"9", #"5", #"*", #"*", #"*"],
                       [#"*", #"9", #"8", #"*", #"*", #"*", #"*", #"6", #"*"],
                       [#"8", #"*", #"*", #"*", #"6", #"*", #"*", #"*", #"3"],
                       [#"4", #"*", #"*", #"8", #"*", #"3", #"*", #"*", #"1"],
                       [#"7", #"*", #"*", #"*", #"2", #"*", #"*", #"*", #"6"],
                       [#"*", #"6", #"*", #"*", #"*", #"*", #"2", #"8", #"*"],
                       [#"*", #"*", #"*", #"4", #"1", #"9", #"*", #"*", #"5"],
                       [#"*", #"*", #"*", #"*", #"8", #"*", #"*", #"7", #"9"]];

val readSudoku_test = readSudoku "sudoku-tilstand.txt" = sudoku_tilstand;

(* 6G2 *)
(* Tager en sudoku tilstand og printer den som en r�kke per linje *)
fun showSudoku lst =
    print (foldl (fn (x, g) => g ^ x ^ "\n") "" (map implode lst));

showSudoku sudoku_tilstand;

(* 6G3 *)
(* K�rer igennem r�kker og elementerne i en suduko tilstand, n�r den kommer til
*det angivne element bliver det erstattet *)
fun modifySudoku sudokuState (r, s, c) =
    let
      fun charLooper (count, []) = []
        | charLooper (count, (x :: xs)) =
          if count = s then c :: charLooper(count + 1, xs)
          else x :: charLooper(count + 1, xs)

      fun listLooper (count, []) = []
        | listLooper (count, (x :: xs)) =
          if count = r then charLooper(0, x) :: listLooper(count + 1, xs)
          else x :: listLooper(count + 1, xs)
    in
      listLooper(0, sudokuState)
    end;

modifySudoku sudoku_tilstand (0, 2, #"7");

(* 6G4 *)
(*
 * listLooper bruger vi til at loope igennem listerne og checke hvilken række,
 * vi er på, vi kalder så charLooper som looper igennem en liste.
 * alle elementer der passer på charLooper og listLooper kriterierne
 * bliver så tilføjet til en liste.
 *)

fun regionList sudokuState q =
    let
      val r = fn q => q div 3 * 3
      val s = fn q => q mod 3 * 3
      fun charLooper (count, []) = []
        | charLooper (count, (x :: xs)) =
          if ((s q) = count orelse (s q) + 1 = count orelse (s q) + 2 = count)
          then x :: charLooper(count + 1, xs)
          else charLooper(count + 1, xs)

      fun listLooper (count, []) = []
        | listLooper (count, (x :: xs)) =
          if ((r q) = count orelse (r q) + 1 = count orelse (r q) + 2 = count)
          then charLooper(0, x) @ listLooper(count + 1, xs)
          else listLooper(count + 1, xs)
    in
      listLooper(0, sudokuState)
    end;

val regionList_test = regionList sudoku_tilstand 3 =
                 [#"8", #"*", #"*", #"4", #"*", #"*", #"7", #"*", #"*"];

(* 6G5 *)
(*
 * funktionen input bruger vi til at tage input, og sørger for det er
 * rigtig formateret. funktionen fileChooser returnerer en sudoku tilstand
 * hvorefter vi så anvender playGame til at blive ved med at prompte brugeren
 * for input og få dem til at udfylde spillet.
 *)
fun playSudoku () =
    (print ("Welcome to the sudoku game!\n" ^
            "Please type the name of a sudoku file\n");
     let
       fun input() =
           case IO.inputLine IO.stdIn of
           SOME inStr => if substring (inStr, size inStr - 1, 1) = "\n"
                         then substring(inStr, 0, size inStr - 1)
                         else inStr
            | NONE => ""


       fun fileChooser() =
           (print "Please choose a file: ";
            case input() of
                "" => fileChooser()
              | filename =>
                (readSudoku filename handle _ => fileChooser()
                                          | _ => readSudoku filename));


       fun playGame state =
           (print ("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" ^
                  "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            showSudoku state;
           let val inp = String.tokens (fn x => x = #" ") (input())
               val (r, s, c) =
                   (valOf(Int.fromString(List.nth(inp, 0))),
                    valOf(Int.fromString(List.nth(inp, 1))),
                    valOf(Char.fromString(List.nth(inp, 2))))
                   handle _ => (print "try again\n"; playGame state)
           in playGame(modifySudoku state (r, s, c)) end)
     in
       playGame(fileChooser())
     end
    );

playSudoku();



