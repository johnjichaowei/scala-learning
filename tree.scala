sealed trait Tree[A] {
  def fold[B](leaf: A => B, node: (B, B) => B): B =
    this match {
      case Node(left, right) => node(left.fold(leaf, node), right.fold(leaf, node))
      case Leaf(value) => leaf(value)
    }
}

final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

val tree: Tree[String] = 
  Node(Node(Leaf("To"), Leaf("iterate")),
       Node(Node(Leaf("is"), Leaf("human,")),
            Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

tree.fold[String](str => str, (a, b) => a + " " + b) 
