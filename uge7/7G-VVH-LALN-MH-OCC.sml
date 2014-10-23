(* 7G1 *)
load "Listsort";
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
fun minus ([], []) = []
  | minus (mset1, []) = []
  | minus (mset1, mset2) =
    List.concat(map (fn x => List.tabulate(Int.max(0, multiplicity(mset1, x) -
                                                   multiplicity(mset2, x)),
                                           fn n => x)) (removeDub mset1));

end;

(* 7G3 *)
(* efter sammenligning med pdf filen, ser filen ud som vi forventede *)
val one = Mset.singleton 1;
val two = Mset.singleton 2;
val three = Mset.singleton 3;
val four = Mset.singleton 4;
val m1223 = Mset.union (one ,
                        Mset.union (two ,
                                    Mset.union (two , three )));
val m114 = Mset.union (one ,
                       Mset.union (one ,
                                   Mset.union (four , Mset.empty )));

(* 7G4 *)
(* svarerne stemmer overens med eksemplerne i opgavetemaet *)
val forening = Mset.union (m1223, m114);
val faelles = Mset.intersect (m1223, m114);
val minus = Mset.minus (m1223, m114);

(* 7G5 *)
(* svarerne stemmer overens med eksemplerne i
 * opgavetemaet, hvor  værdierne tælles *)
val forekomster1 = Mset.multiplicity (forening, 1);
val forekomster2 = Mset.multiplicity (faelles, 1);
val forekomster3 = Mset.multiplicity (minus, 1);
