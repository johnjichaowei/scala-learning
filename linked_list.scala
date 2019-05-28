sealed trait LinkedList[A] {
  def fold[B](end: B, f: (A, B) => B): B = 
    this match {
      case End() => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }

  def map[B](fn: A => B): LinkedList[B] = 
    this match {
      case End() => End[B]()
      case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
    }
}
final case class End[A]() extends LinkedList[A]
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
