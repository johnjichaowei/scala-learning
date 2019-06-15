package sandbox

import c3.tree._
import cats.Functor
import cats.syntax.show._
import cats.syntax.functor._

object Main extends App {
  val functorTree = Functor[Tree]
  val branch = Branch(Leaf(1), Leaf(2))
  val branch1 = functorTree.map(Leaf(2))(_ * 2)
  val tree = Tree.branch(Tree.leaf(1), Tree.leaf(2))
  println(s"tree: ${tree.show}")
  val tree1 = functorTree.map(tree)(_ * 2)
  println(s"tree1: ${tree1.show}")
  val tree2 = Tree.leaf(1).map(_ * 2)
  println(s"tree2: ${tree2.show}")
  val tree3 = Tree.branch(Tree.leaf(1), Tree.leaf(2)).map(_ * 2)
  println(s"tree3: ${tree3.show}")
}
