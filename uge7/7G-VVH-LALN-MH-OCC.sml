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
fun remove (_, []) = []
  | remove (x, y :: ys) = if x = y then remove(x, ys)
                          else y :: remove (x, ys);
fun removeDub [] = []
  | removeDub (x :: xs) = x :: removeDub(remove(x,xs));

structure Mset : MSET =
struct
type 'a mset = 'a list;
fun multiplicity (mset, a) = length(List.filter (fn x => x = a) mset);
val empty = [];
fun singleton a = [a];
fun union (mset1, mset2) = mset1 @ mset2;
fun intersect ([], []) = []
  | intersect (mset1, []) = []
  | intersect (mset1, mset2) =
    List.concat(map (fn x => List.tabulate(Int.min(multiplicity(mset1, x),
                                                   multiplicity(mset2, x)),
                                           fn n => x)) (removeDub mset1));

end;





fun multiplicity (mset, a) = length(List.filter (fn x => x = a) mset);



intersect([1, 1, 2,  2, 2, 3], [1, 1, 2, 4]);
