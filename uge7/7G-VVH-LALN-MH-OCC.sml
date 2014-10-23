(* 7G1 *)
signature MSET =
sig
  type 'a mset (* typen af multimængder med elementer af type 'a *)
  val multiplicity : ''a mset * ''a -> int (* antal forekomster af element *)
  val empty : 'a mset (* den tomme multimængde *)
  val singleton : ''a -> ''a mset (* laver multimængde med et element *)
  val union : ''a mset * ''a mset -> ''a mset (* foreningsmængde *)
  val intersect : ''a mset * ''a mset -> ''a mset (* fællesmængde *)
  val minus : ''a mset * ''a mset -> ''a mset (* mængdedifferens *)
end;

(* 7G2 *)

(*structure Mset : MSET =
struct
type 'a mset = 'a list;
fun multiplicity (mset, a) = length(List.filter (fn x => x = e) set);
val empty = [];
fun singleton a = [a];
fun union (mset1, mset2) = mset1 @ mset2;
fun intersect
end;*)

fun multiplicity (mset, a) = length(List.filter (fn x => x = e) set);
fun exists (mset, a) = List.exists

fun intersect (mset1, mset2) =
    
